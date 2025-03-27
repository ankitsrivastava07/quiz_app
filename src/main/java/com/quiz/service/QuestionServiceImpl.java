package com.quiz.service;

import com.quiz.dao.repository.*;
import com.quiz.dto.ApiResponse;
import com.quiz.dto.QuestionDto;

import java.util.Collections;
import java.util.NoSuchElementException;

import com.quiz.converter.CustomObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Override
    public ApiResponse addQuestion(String quizId, QuestionDto questionDto) {
        var quizEntity = quizRepository
                .findById(quizId)
                .orElseThrow(() ->
                        new NoSuchElementException("Quiz Entity not found"));
        var question = CustomObjectMapper.mapper(questionDto, QuestionEntity.class);
        quizEntity.setQuestions(Collections.singletonList(question));
        return new ApiResponse.ApiResponseBuilder()
                .setMsg("Success")
                .setFlag(Boolean.TRUE)
                .build();
    }
}
