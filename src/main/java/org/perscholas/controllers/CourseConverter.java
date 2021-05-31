package org.perscholas.controllers;

import org.perscholas.dao.ICourseRepo;
import org.perscholas.models.Course;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CourseConverter implements Converter<String, Course> {

    private ICourseRepo repo;

    public CourseConverter(ICourseRepo repo) {

        this.repo = repo;

    }

    @Override
    public Course convert(String id) {

        return repo.findById(Long.parseLong(id)).orElse(null);

    }

}
