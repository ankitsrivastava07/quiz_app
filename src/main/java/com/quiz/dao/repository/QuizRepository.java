package com.quiz.dao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizRepository extends MongoRepository<QuizEntity, String> {
}
