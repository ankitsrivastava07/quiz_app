package com.quiz.controller;

import java.io.IOException;
import com.quiz.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<?> saveFileData(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        adminService.save(multipartFile);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/")
    public ResponseEntity<?> saveData() {
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
