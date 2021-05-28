package org.perscholas.controllers;

import org.perscholas.models.Student;
import org.perscholas.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/register")
    public String showRegisterStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "new-student";
    }

    @GetMapping("/homepage")
    public String showHomepage() {

        return "homepage";
    }



    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        if (studentService.isValid(email, password)) {
            return "homepage";
        } else {
            model.addAttribute("error", "Invalid email/password");
            return "home";
        }
    }

}
