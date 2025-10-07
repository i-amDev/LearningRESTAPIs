package com.learning.LearningRESTAPIs.service.impl;

import com.learning.LearningRESTAPIs.dto.AddStudentDto;
import com.learning.LearningRESTAPIs.dto.StudentDto;
import com.learning.LearningRESTAPIs.entity.StudentEntity;
import com.learning.LearningRESTAPIs.repository.StudentRepository;
import com.learning.LearningRESTAPIs.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<StudentDto> getAllStudents() {
        List<StudentEntity> students = studentRepository.findAll();
//        return students.
//                stream().
//                map(studentEntity -> new StudentDto(studentEntity.getId(), studentEntity.getName(), studentEntity.getEmail()))
//                .toList();
        return students
                .stream()
                .map(studentEntity -> modelMapper.map(studentEntity, StudentDto.class))
                .toList();
    }

    @Override
    public StudentDto getStudentById(Long id) {
        StudentEntity studentEntity = studentRepository.findById(id).orElse(null);
        return modelMapper.map(studentEntity, StudentDto.class);
    }

    @Override
    public StudentDto createStudent(AddStudentDto addStudentDto) {
        StudentEntity entity = modelMapper.map(addStudentDto, StudentEntity.class);
        StudentEntity studentEntity = studentRepository.save(entity);
        return modelMapper.map(studentEntity, StudentDto.class);
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student does not exist with id " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudent(Long id, AddStudentDto addStudentDto) {
        StudentEntity studentEntity = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student does not exist with id " + id));
        // this is actually updating all the fields from the given dto to the entity.
        modelMapper.map(addStudentDto, studentEntity);
        StudentEntity newEntity = studentRepository.save(studentEntity);
        return modelMapper.map(newEntity, StudentDto.class);
    }

    @Override
    public StudentDto updateStudentPartially(Long id, Map<String, Object> updates) {
        StudentEntity studentEntity = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student does not exist with id " + id));
        updates.forEach((field, value) -> {
            switch (field) {
                case "name" :
                    studentEntity.setName((String) value);
                    break;
                case "email" :
                    studentEntity.setEmail((String) value);
                    break;
                default : throw new IllegalArgumentException("Field is not supported!");
            }
        });
        StudentEntity entity = studentRepository.save(studentEntity);
        return modelMapper.map(entity, StudentDto.class);
    }
}
