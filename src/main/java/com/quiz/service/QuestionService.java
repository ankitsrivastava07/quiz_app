package com.quiz.service;

import com.quiz.dto.ApiResponse;
import com.quiz.dto.QuestionDto;

public interface QuestionService {
    ApiResponse addQuestion(String quizId, QuestionDto questionDto);
}
