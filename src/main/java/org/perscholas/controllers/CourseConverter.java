package org.perscholas.controllers;

import org.jetbrains.annotations.NotNull;
import org.perscholas.dao.CourseRepository;
import org.perscholas.models.Course;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CourseConverter implements Converter<String, Course> {

  private final CourseRepository repo;

  public CourseConverter(CourseRepository repo) {
    this.repo = repo;
  }

  @Override
  public Course convert(@NotNull String id) {
    return repo.findById(Long.parseLong(id)).orElse(null);
  }
}
