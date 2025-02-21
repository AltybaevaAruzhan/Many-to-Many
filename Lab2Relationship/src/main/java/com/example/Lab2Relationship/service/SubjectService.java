package com.example.Lab2Relationship.service;

import com.example.Lab2Relationship.model.Subject;
import com.example.Lab2Relationship.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> getSubjectById(Long id) {
        return subjectRepository.findById(id);
    }

    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }
    public Subject updateSubject(Long id, Subject updatedSubject) {
        Subject existingSubject = getSubjectById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subject not found with ID: " + id));
        existingSubject.setSubjectName(updatedSubject.getSubjectName());
        existingSubject.setStudentList(updatedSubject.getStudentList());
        return subjectRepository.save(existingSubject);
    }

    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
}
