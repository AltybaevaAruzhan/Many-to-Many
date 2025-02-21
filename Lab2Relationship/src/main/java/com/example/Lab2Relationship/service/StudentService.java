package com.example.Lab2Relationship.service;

import com.example.Lab2Relationship.model.Student;
import com.example.Lab2Relationship.model.Subject;
import com.example.Lab2Relationship.repository.StudentRepository;
import com.example.Lab2Relationship.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public StudentService(StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student, List<Long> subjectIds) {
        if (subjectIds != null) {
            List<Subject> subjects = subjectRepository.findAllById(subjectIds);
            student.setSubjectList(subjects);
        } else {
            student.setSubjectList(new ArrayList<>());
        }
        return studentRepository.save(student);
    }



    public Student updateStudent(Long id, Student updatedStudent, List<Long> subjectIds) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + id));

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setAge(updatedStudent.getAge());
        existingStudent.setEmail(updatedStudent.getEmail());

        if (subjectIds != null) {
            List<Subject> subjects = subjectRepository.findAllById(subjectIds);
            existingStudent.setSubjectList(subjects);
        }

        return studentRepository.save(existingStudent);
    }



    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student with ID " + id + " not found.");
        }
        studentRepository.deleteById(id);
    }
}
