package com.quiz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/quiz/{quizId}/question")
public class QuestionController {

    @PostMapping("/save")
    public ResponseEntity<?> save(@PathVariable String quizId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{questionId}")
    public ResponseEntity<?> getAllQuestionById(@PathVariable String quizId, @PathVariable String questionId) {
        System.out.println(quizId + " question = " + questionId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
