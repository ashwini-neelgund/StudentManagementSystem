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
    Student student = (Student) model.getAttribute("student");
    List<Course> currentCourses = student.getStudentCourses();
    log.info("" + model.containsAttribute("student"));
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
    studentService.updateStudent(student);
    model.addAttribute("currentCourses", student.getStudentCourses());
    model.addAttribute(
        "courses",
        filterEnrolledCourses(courseService.getAllCourses(), student.getStudentCourses()));
    return "finalize";
  }

  private Iterable<Course> filterEnrolledCourses(
      List<Course> allCourses, List<Course> studentCourses) {
    if (studentCourses == null || studentCourses.size() == 0) {
      return allCourses;
    }
    return allCourses.stream()
        .filter(course -> !studentCourses.contains(course))
        .collect(Collectors.toList());
  }
}
