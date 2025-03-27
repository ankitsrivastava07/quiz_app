package com.quiz.dao;

import com.quiz.dao.repository.QuestionEntity;

public interface AdminDao {
    QuestionEntity save(QuestionEntity questionEntity);
}
