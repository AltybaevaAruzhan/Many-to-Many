package com.example.Lab2Relationship.repository;

import com.example.Lab2Relationship.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
}
