package com.quiz.service;

import com.quiz.dao.AdminDao;
import com.quiz.dao.repository.QuestionEntity;
import com.quiz.dto.ApiResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public ApiResponse save(MultipartFile multipartFile) throws IOException {

        InputStream inputStream = multipartFile.getInputStream();
        List<List<String>> rowdata = new ArrayList<>();

        Workbook workbook = new XSSFWorkbook(inputStream); // For .xlsx files
        Sheet sheet = workbook.getSheetAt(0); // Get the first sheet

        for (Row row : sheet) {
            Iterator<Cell> cellIterator = row.cellIterator();
            List<String> rowData = new ArrayList<>();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                rowData.add(getCellValueAsString(cell));
            }
            rowdata.add(rowData);
        }
        saveQuestions(rowdata);
        return new ApiResponse.ApiResponseBuilder().build();
    }

    private String getCellValueAsString(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }

    public List<QuestionEntity> saveQuestions(List<List<String>> data) {
        List<QuestionEntity> questionEntities = new ArrayList<>();
        for (List<String> fileData : data) {
            QuestionEntity question = new QuestionEntity();
            List<String> options = new ArrayList<>();
            for (int i = 0; i < fileData.size(); i++) {
                if (i == 0) question.setQuestion(fileData.get(i));
                options.add(fileData.get(i));
            }
            question.setOptions(options);
            question.setCorrectOption(1);
            questionEntities.add(question);
        }
        return questionEntities;
    }

}
