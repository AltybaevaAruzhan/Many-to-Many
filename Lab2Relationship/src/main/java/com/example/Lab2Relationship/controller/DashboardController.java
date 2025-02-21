package com.example.Lab2Relationship.controller;

import com.example.Lab2Relationship.model.Student;
import com.example.Lab2Relationship.model.Subject;
import com.example.Lab2Relationship.service.StudentService;
import com.example.Lab2Relationship.service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final StudentService studentService;
    private final SubjectService subjectService;

    public DashboardController(StudentService studentService, SubjectService subjectService) {
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    @GetMapping
    public String showDashboard(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "dashboard";
    }
    @GetMapping("/students/{id}")
    public String showStudentDetails(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + id));
        model.addAttribute("student", student);
        return "studentDetails";
    }

    @GetMapping("/subjects/{id}")
    public String showSubjectDetails(@PathVariable Long id, Model model) {
        Subject subject = subjectService.getSubjectById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subject not found with ID: " + id));
        model.addAttribute("subject", subject);
        return "subjectDetails";
    }

}
