package com.learning.LearningRESTAPIs.controller;

import com.learning.LearningRESTAPIs.dto.AddStudentDto;
import com.learning.LearningRESTAPIs.dto.StudentDto;
import com.learning.LearningRESTAPIs.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/student")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
//        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping("/createStudent")
    public ResponseEntity<StudentDto> createStudent(@RequestBody @Valid AddStudentDto addStudentDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.createStudent(addStudentDto));
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id,
                                                    @RequestBody AddStudentDto addStudentDto) {
        return ResponseEntity.ok(studentService.updateStudent(id, addStudentDto));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<StudentDto> updateStudentPartially(@PathVariable Long id,
                                                             @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(studentService.updateStudentPartially(id, updates));
    }
}
