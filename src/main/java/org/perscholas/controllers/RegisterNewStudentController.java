package org.perscholas.controllers;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.models.Student;
import org.perscholas.services.FileService;
import org.perscholas.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/new-student")
@SessionAttributes("student")
public class RegisterNewStudentController {
    private final StudentService studentService;
    private final FileService fileService;

    @Autowired
    public RegisterNewStudentController(StudentService studentService, FileService fileService) {
        this.studentService = studentService;
        this.fileService = fileService;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("student", new Student());

        return "new-student";
    }

    @PostMapping
    public String addStudent(
            @Valid @ModelAttribute("student") Student student, BindingResult result, Model model,
            @RequestParam("file") MultipartFile file) {
        if (result.hasErrors()) {
            return "new-student";
        }

        if (studentService.checkIfStudentExists(student.getStudentName(), student.getStudentEmail())) {
            model.addAttribute("error", "You are already in the system. Please login.");

            return "redirect:/home";
        }

        student.setStudentImage(fileService.uploadFile(file));
        studentService.addStudent(student);
        model.addAttribute("success", "Account created.");
        model.addAttribute("student", student);

        return "redirect:/register";
    }
}
