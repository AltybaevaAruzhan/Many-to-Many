package com.example.Lab2Relationship.controller;

import com.example.Lab2Relationship.model.Student;
import com.example.Lab2Relationship.service.StudentService;
import com.example.Lab2Relationship.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final SubjectService subjectService;
    @Autowired

    public StudentController(StudentService studentService, SubjectService subjectService) {
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    @GetMapping
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "student";
    }

    @GetMapping("/create")
    public String showCreateStudentForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("subjects", subjectService.getAllSubjects()); // Загружаем предметы
        return "createStudent";
    }


    @PostMapping("/create")
    public String createStudent(@ModelAttribute Student student, @RequestParam List<Long> subjectIds) {
        studentService.createStudent(student, subjectIds);
        return "redirect:/students";
    }





    @GetMapping("/{id}/update")
    public String showEditStudentForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + id));
        model.addAttribute("student", student);
        return "updateStudent";
    }

    @PostMapping("/{id}/update")
    public String updateStudent(@PathVariable long id,
                                @ModelAttribute Student student,
                                @RequestParam(required = false) List<Long> subjectIds) {
        studentService.updateStudent(id, student, subjectIds);
        return "redirect:/students";
    }



    @PostMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
    @GetMapping("/{id}/details")
    public String viewStudentSubjects(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + id));
        model.addAttribute("student", student);
        return "studentDetails";  // Create a separate Thymeleaf template for the student details
    }

}
