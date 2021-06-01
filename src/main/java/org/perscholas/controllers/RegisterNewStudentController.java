package org.perscholas.controllers;

import org.perscholas.models.Student;
import org.perscholas.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This class handles logic for registering a new student in the system.
 */
@Controller
@RequestMapping("/new-student")
@SessionAttributes("student")
public class RegisterNewStudentController {

    private StudentService studentService;

    public RegisterNewStudentController(StudentService studentService) {

        this.studentService = studentService;

    }

    /**
     * Adds a blank student object to the model and returns the path to the new-student sign up form.
     * @param model the model
     * @return path to new-student.html
     */
    @GetMapping
    public String showRegistrationForm(Model model) {

        model.addAttribute("student", new Student());
        return "new-student";

    }

    /**
     * Post mapping for the new-student page. Checks for a valid student object, checks that it is not a duplicate
     * sign up and saves the student to the database.
     * @param student Student to be persisted
     * @param result result to check for errors on
     * @param model the model
     * @return path to register.html if signup is successful, new-student if there are form errors, or home if the
     * student is already signed up.
     */
    @PostMapping
    public String addStudent(@Valid @ModelAttribute("student") Student student, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "new-student";
        }

        if (studentService.checkIfStudentExists(student.getStudentName(), student.getStudentEmail())) {
            model.addAttribute("error", "You are already in the system. Please login.");
            return "redirect:/home";
        }

        studentService.addStudent(student);
        model.addAttribute("success", "Account created.");
        model.addAttribute("student", student);
        return "redirect:/register";

    }

}
