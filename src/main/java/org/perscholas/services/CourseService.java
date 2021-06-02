package org.perscholas.services;

import org.perscholas.dao.ICourseRepo;
import org.perscholas.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseService {

  /*
         - add class annotations
         - add @Transactional on class or on each method
         - add crud methods
  */
  private final ICourseRepo courseRepo;

  @Autowired
  public CourseService(ICourseRepo courseRepo) {
    this.courseRepo = courseRepo;
  }

  public List<Course> getAllCourses() {

    return courseRepo.findAll();
  }

  public Course getCourseById(Long id) {

    return courseRepo.getById(id);
  }
}
