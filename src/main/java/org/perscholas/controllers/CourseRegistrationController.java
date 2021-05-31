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

    @ModelAttribute
    public void addCoursesToModel(Model model) {

        List<Course> courses = courseService.getAllCourses();
        Student student = studentService.getStudentByEmail("jjones@hotmail.com");
        List<Course> currentCourses = student.getStudentCourses();

        model.addAttribute("student", student);
        model.addAttribute("currentCourses", currentCourses);
        model.addAttribute("courses", filterEnrolledCourses(courses, currentCourses));

    }

    @GetMapping
    public String showRegistrationForm() {


        return "register";

    }

    @PostMapping
    public String processStudentRegistration(Student student, Model model) {

        studentService.saveStudent(student);
        model.addAttribute("currentCourses", student.getStudentCourses());
        model.addAttribute("courses", filterEnrolledCourses(courseService.getAllCourses(), student.getStudentCourses()));
        return "finalize";

    }

    /**
     * This filters out courses from a course list that an arbitrary student already has in their course list
     *
     * @param allCourses     list of all available courses
     * @param studentCourses list of courses that a student has enrolled in already
     * @return filtered list of courses
     */
    private Iterable<Course> filterEnrolledCourses(List<Course> allCourses, List<Course> studentCourses) {

        return allCourses.stream().filter(course -> !studentCourses.contains(course)).collect(Collectors.toList());

    }

}
