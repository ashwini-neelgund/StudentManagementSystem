package org.perscholas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This class provides mapping for courses. It is not currently used for anything.
 */
@Controller
@RequestMapping("/courses")
public class CourseController {

    @GetMapping("/current")
    public String currentCourses(Model model) {

        // temp. hardcode
        return "courses";

    }
}
