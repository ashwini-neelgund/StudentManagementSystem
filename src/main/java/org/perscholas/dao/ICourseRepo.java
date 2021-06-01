package org.perscholas.dao;

import org.perscholas.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is the repository interface for courses.
 */
@Repository
public interface ICourseRepo extends JpaRepository<Course, Long> {
}
