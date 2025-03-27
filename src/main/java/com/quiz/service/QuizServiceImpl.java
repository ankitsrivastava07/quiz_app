package com.quiz.service;

import com.quiz.dto.ApiResponse;
import org.springframework.stereotype.Service;
import com.quiz.dao.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    private CategoryRepository categoryRepository;

    public ApiResponse getAllCategories() {
        return
                new ApiResponse
                        .ApiResponseBuilder()
                        .setData(categoryRepository.findAll())
                        .setFlag(Boolean.TRUE)
                        .setMsg("Success")
                        .build();
    }

    public ApiResponse getAllCategoriesById(String id) {
        return
                new ApiResponse
                        .ApiResponseBuilder()
                        .setData(categoryRepository.findById(id)
                                .get()
                                .getSubCategories())
                        .setFlag(true)
                        .build();
    }

}
