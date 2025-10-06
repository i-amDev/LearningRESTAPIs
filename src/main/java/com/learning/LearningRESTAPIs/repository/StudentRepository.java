package com.learning.LearningRESTAPIs.repository;

import com.learning.LearningRESTAPIs.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {


}
