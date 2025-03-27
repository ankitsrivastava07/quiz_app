package com.quiz.service;

import com.quiz.dto.ApiResponse;

public interface QuizService {

    ApiResponse getAllCategories();

    ApiResponse getAllCategoriesById(String id);
}
