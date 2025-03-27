package com.quiz.dao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepo extends MongoRepository<CourseEntity, String> {
}
