package com.learning.LearningRESTAPIs.service;

import com.learning.LearningRESTAPIs.dto.AddStudentDto;
import com.learning.LearningRESTAPIs.dto.StudentDto;

import java.util.List;
import java.util.Map;

public interface StudentService {

    List<StudentDto> getAllStudents();

    StudentDto getStudentById(Long id);

    StudentDto createStudent(AddStudentDto addStudentDto);

    void deleteStudent(Long id);

    StudentDto updateStudent(Long id, AddStudentDto addStudentDto);

    StudentDto updateStudentPartially(Long id, Map<String, Object> updates);
}
