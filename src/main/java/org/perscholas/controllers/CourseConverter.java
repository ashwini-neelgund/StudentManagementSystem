package org.perscholas.controllers;

import org.perscholas.dao.ICourseRepo;
import org.perscholas.models.Course;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class exists to provide automatic conversion between the id of a course provided by
 * register.html and an actual Course object in CourseRegistrationController.
 */
@Component
public class CourseConverter implements Converter<String, Course> {

  private final ICourseRepo repo;

  public CourseConverter(ICourseRepo repo) {

    this.repo = repo;
  }

  /**
   * This method is called automatically when conversion is needed between the model and an actual
   * object. At least that is my understanding of the situation. register.html sends back the ids of
   * the courses that are selected by the checkboxes in the form. This converter is called
   * implicitly to get the correct Course object to assign to the Student before being sent back to
   * CourseRegistrationController.
   *
   * @param id Id of course to convert
   * @return The corresponding Course from the database
   */
  @Override
  public Course convert(String id) {

    return repo.findById(Long.parseLong(id)).orElse(null);
  }
}
