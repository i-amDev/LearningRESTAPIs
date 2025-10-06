package com.learning.LearningRESTAPIs.controller;

import com.learning.LearningRESTAPIs.dto.StudentDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @GetMapping("/student")
    public StudentDto getStudent() {
        return new StudentDto(101L, "Dev", "dev@gmail.com");
    }
}
