package org.perscholas.controllers;

import org.perscholas.models.Course;
import org.perscholas.models.Student;
import org.perscholas.services.CourseService;
import org.perscholas.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@SessionAttributes({"student", "currentCourses"})
public class AdminController {
    private final CourseService courseService;
    private final StudentService studentService;

    @Autowired
    public AdminController(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @GetMapping("all-courses")
    public String showCourses(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);

        return "all-courses";
    }

    @GetMapping("all-students")
    public String showStudents(Model model) {
        Iterable<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);

        return "all-students";
    }

    @GetMapping("student-info")
    public String getStudent() {
        return "student-info";
    }

    @PostMapping("/lookup")
    public String displayInfo(@ModelAttribute("email") String email, Model model) {
        Student student = studentService.getStudentByEmail(email);
        model.addAttribute("student", student);

        return "student-info";
    }
}
