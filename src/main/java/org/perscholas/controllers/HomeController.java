package org.perscholas.controllers;

import org.perscholas.models.Student;
import org.perscholas.services.StudentService;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/** This class handles logic for the home page. Login logic is located here. */
@Controller
@SessionAttributes("student")
public class HomeController {

  StudentService studentService;

  public HomeController(StudentService studentService) {

    this.studentService = studentService;
  }

  /**
   * Displays home.html
   *
   * @return path to home.html
   */
  @GetMapping("/")
  public String home() {

    return "home";
  }

  @PostMapping("/login")
  public String login(SecurityContext context, Model model) {

    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username;

    if (principal instanceof UserDetails) {
      username = ((UserDetails) principal).getUsername();
    } else {
      username = principal.toString();
    }

    Student student = studentService.getStudentByEmail(username);
    model.addAttribute("student", student);
    return "redirect:/register";

    /*        if (studentService.isValid(email, password)) {

        model.addAttribute("student", studentService.getStudentByEmail(email));
        return "redirect:/register";

    } else {

        model.addAttribute("error", "Invalid email/password");
        return "home";

    }*/

  }
}
