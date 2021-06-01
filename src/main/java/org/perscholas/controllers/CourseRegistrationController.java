package org.perscholas.controllers;


import lombok.extern.slf4j.Slf4j;
import org.perscholas.models.Course;
import org.perscholas.models.Student;
import org.perscholas.services.CourseService;
import org.perscholas.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class handles all of the course registration logic.
 */
@Slf4j
@Controller
@RequestMapping("/register")
@SessionAttributes("student")
public class CourseRegistrationController {

    private final CourseService courseService;
    private final StudentService studentService;

    @Autowired
    public CourseRegistrationController(CourseService courseService, StudentService studentService) {

        this.courseService = courseService;
        this.studentService = studentService;

    }

    /**
     * This adds the student, a list of the students courses, and a filtered list of all of the courses that the
     * students has not yet enrolled in.
     * @param model
     */
    @ModelAttribute
    public void addCoursesToModel(Model model) {

        List<Course> courses = courseService.getAllCourses();
//        Student student = studentService.getStudentByEmail("jjones@hotmail.com");
        Student student = (Student) model.getAttribute("student");
        List<Course> currentCourses = student.getStudentCourses();
        log.info("" + model.containsAttribute("student"));

        model.addAttribute("student", student);
        model.addAttribute("currentCourses", currentCourses);
        model.addAttribute("courses", filterEnrolledCourses(courses, currentCourses));

    }

    /**
     * Shows register.html, the page for enrolling in courses.
     * @return path to register.html
     */
    @GetMapping
    public String showRegistrationForm() {

        return "register";

    }

    /**
     * Persists student's courses to the database and updated the model to reflect those changes. Then
     * returns the path to finalize.html. Probably should be two methods.
     * @param student Student to save
     * @param model the model
     * @return path to finalize.html
     */
    @PostMapping
    public String processStudentRegistration(Student student, Model model) {

        studentService.saveStudent(student);
        model.addAttribute("currentCourses", student.getStudentCourses());
        model.addAttribute("courses", filterEnrolledCourses(courseService.getAllCourses(), student.getStudentCourses()));
        return "finalize";

    }

    /**
     * This filters out courses from a course list that an arbitrary student already has in their course list.
     *
     * @param allCourses     list of all available courses
     * @param studentCourses list of courses that a student has enrolled in already
     * @return filtered list of courses
     */
    private Iterable<Course> filterEnrolledCourses(List<Course> allCourses, List<Course> studentCourses) {

        if (studentCourses == null || studentCourses.size() == 0) {

            return allCourses;

        }

        return allCourses.stream().filter(course -> !studentCourses.contains(course)).collect(Collectors.toList());

    }

}
