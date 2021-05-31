package org.perscholas.controllers;

import org.perscholas.models.Course;
import org.perscholas.models.Student;
import org.perscholas.services.CourseService;
import org.perscholas.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
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
}
