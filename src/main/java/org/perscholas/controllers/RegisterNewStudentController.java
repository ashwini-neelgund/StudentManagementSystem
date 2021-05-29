package org.perscholas.controllers;

import org.hibernate.Session;
import org.perscholas.models.Student;
import org.perscholas.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/new-student")
@SessionAttributes("student")
public class RegisterNewStudentController {

    private StudentService studentService;

    public RegisterNewStudentController(StudentService studentService) {

        this.studentService = studentService;

    }

    @GetMapping
    public String showRegistrationForm(Model model) {

        model.addAttribute("student", new Student());
        return "new-student";

    }

    @PostMapping("/add-student")
    public String addStudent(@Valid @ModelAttribute("student") Student student, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "new-student";
        }

        if (studentService.checkIfStudentExists(student.getStudentName(), student.getStudentEmail())) {
            model.addAttribute("error", "You are already in the system. Please login.");
            return "home";
        }

        studentService.addStudent(student);
        model.addAttribute("success", "Account created.");
        return "homepage";

    }

}
