package com.quiz.dao;

import com.quiz.dao.repository.QuestionEntity;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl implements AdminDao {

    @Override
    public QuestionEntity save(QuestionEntity questionEntity) {
        return null;
    }
}
