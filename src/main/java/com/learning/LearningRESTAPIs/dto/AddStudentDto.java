package com.learning.LearningRESTAPIs.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class AddStudentDto {

    private String name;
    @Email
    private String email;
}
