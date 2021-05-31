package org.perscholas.controllers;

import org.perscholas.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("student")
public class HomeController {

    StudentService studentService;

    public HomeController(StudentService studentService) {

        this.studentService = studentService;

    }

    @GetMapping("/")
    public String home() {

        return "home";

    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {

        if (studentService.isValid(email, password)) {

            model.addAttribute("student", studentService.getStudentByEmail(email));
            return "redirect:/register";

        } else {

            model.addAttribute("error", "Invalid email/password");
            return "home";

        }

    }

}
