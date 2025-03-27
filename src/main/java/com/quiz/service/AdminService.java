package com.quiz.service;

import com.quiz.dto.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AdminService {
    ApiResponse save(MultipartFile multipartFile) throws IOException;
}
