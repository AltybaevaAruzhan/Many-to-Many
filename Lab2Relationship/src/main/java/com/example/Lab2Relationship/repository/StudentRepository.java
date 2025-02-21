package com.example.Lab2Relationship.repository;

import com.example.Lab2Relationship.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
