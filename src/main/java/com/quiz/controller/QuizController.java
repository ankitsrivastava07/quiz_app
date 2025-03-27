package com.quiz.controller;

import com.quiz.dto.QuizDto;
import com.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("api/v1/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping
    public ResponseEntity<?> saveQuiz(QuizDto quizDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*   @GetMapping
    public ResponseEntity<?> getAllQuiz() {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
*/
    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        return new ResponseEntity<>(quizService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<?> getCategoryById(@PathVariable String quizId) {
        return new ResponseEntity<>(quizService.getAllCategoriesById(quizId), HttpStatus.OK);
    }

}
