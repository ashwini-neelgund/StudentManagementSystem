package org.perscholas.controllers;

import org.perscholas.models.Course;
import org.perscholas.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/register")
@SessionAttributes("student")
public class CourseRegistrationController {

    private final CourseService courseService;

    @Autowired
    public CourseRegistrationController(CourseService courseService) {

        this.courseService = courseService;

    }

    @ModelAttribute
    public void addCoursesToModel(Model model) {

        List<Course> courses = courseService.getAllCourses();

        // Temp. hardcoding
        List<Course> currentCourses = Arrays.asList(
                courses.get(1)
        );

        model.addAttribute("courses", filterEnrolledCourses(courses, currentCourses));

    }

    @GetMapping
    public String showRegistrationForm() {

        return "register";

    }

    @PostMapping
    public String processStudentRegistration(List<Course> courses) {

        // TODO: 5/28/2021
        return "redirect:/courses/current";

    }

    /**
     * This filters out courses from a course list that an arbitrary student already has in their course list
     * @param allCourses list of all available courses
     * @param studentCourses list of courses that a student has enrolled in already
     * @return
     */
    private Iterable<Course> filterEnrolledCourses(List<Course> allCourses, List<Course> studentCourses) {

        return allCourses.stream().filter(course -> !studentCourses.contains(course)).collect(Collectors.toList());

    }

}
