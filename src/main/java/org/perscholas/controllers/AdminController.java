package org.perscholas.controllers;

import org.perscholas.models.Course;
import org.perscholas.models.Student;
import org.perscholas.services.CourseService;
import org.perscholas.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * This class provides admin functionality.
 */
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

    /**
     * Adds all available courses from the database to the model and returns the path to all-courses.html.
     * @param model
     * @return all-courses.html
     */
    @GetMapping("all-courses")
    public String showCourses(Model model) {

        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "all-courses";

    }

    /**
     * Adds all students from the database to the model and returns the path to all-students.html
     * @param model
     * @return path to all-students.html
     */
    @GetMapping("all-students")
    public String showStudents(Model model) {

        Iterable<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "all-students";

    }

    /**
     * Shows student-info.html, a form to enter an email address to retrieve student data.
     * @return path to student-info.html
     */
    @GetMapping("student-info")
    public String getStudent() {

        return "student-info";

    }

    /**
     * Post mapping for student-info.html. Looks up the student by the provided email address, adds that student
     * to the model, and returns the path back to student-info.html.
     * @param email Email address of student.
     * @param model The model
     * @return path to student-info.html
     */
    @PostMapping("/lookup")
    public String displayInfo(@ModelAttribute("email") String email, Model model) {

        Student student = studentService.getStudentByEmail(email);
        model.addAttribute("student", student);
        return "student-info";

    }
}
