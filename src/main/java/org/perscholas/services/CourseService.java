package org.perscholas.services;

import org.perscholas.dao.CourseRepository;
import org.perscholas.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseService {
  private final CourseRepository courseRepo;

  @Autowired
  public CourseService(CourseRepository courseRepo) {
    this.courseRepo = courseRepo;
  }

  public List<Course> getAllCourses() {
    return courseRepo.findAll();
  }

  public Course getCourseById(Long id) {
    return courseRepo.getById(id);
  }
}
