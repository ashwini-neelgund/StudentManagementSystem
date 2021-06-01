package org.perscholas.controllers;

import org.perscholas.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * This class handles logic for the home page. Login logic is located here.
 */
@Controller
@SessionAttributes("student")
public class HomeController {

    StudentService studentService;

    public HomeController(StudentService studentService) {

        this.studentService = studentService;

    }

    /**
     * Displays home.html
     * @return path to home.html
     */
    @GetMapping("/")
    public String home() {

        return "home";

    }

    /**
     * Logs in student and sets session attribute so that student can be referenced across pages.
     * @param email Email of student
     * @param password Password of student
     * @param model the model
     * @return path to register if login is successful, otherwise returns path to home again.
     */
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
