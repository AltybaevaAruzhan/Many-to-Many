package com.example.Lab2Relationship.controller;

import com.example.Lab2Relationship.model.Subject;
import com.example.Lab2Relationship.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public String getAllSubjects(Model model) {
        List<Subject> subjects = subjectService.getAllSubjects();
        model.addAttribute("subjects", subjects);
        return "subjects";
    }

    @GetMapping("/create")
    public String showCreateSubjectForm(Model model) {
        model.addAttribute("subject", new Subject());
        return "createSubject";
    }

    @PostMapping("/create")
    public String createSubject(@ModelAttribute Subject subject) {
        subjectService.saveSubject(subject);
        return "redirect:/subjects";
    }

    @GetMapping("/{id}/update")
    public String showEditSubjectForm(@PathVariable Long id, Model model) {
        Subject subject = subjectService.getSubjectById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subject not found with ID: " + id));
        model.addAttribute("subject", subject);
        return "updateSubject";
    }

    @PostMapping("/{id}/update")
    public String updateSubject(@PathVariable long id, @ModelAttribute Subject subject) {
        subjectService.updateSubject(id, subject);
        return "redirect:/subjects";
    }

    @PostMapping("/{id}/delete")
    public String deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return "redirect:/subjects";
    }
    @GetMapping("/{id}/details")
    public String viewSubjectStudents(@PathVariable Long id, Model model) {
        Subject subject = subjectService.getSubjectById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subject not found with ID: " + id));
        System.out.println("Students enrolled in the subject: " + subject.getStudentList());  // Add this line to log
        model.addAttribute("subject", subject);
        return "subjectDetails";
    }



}
