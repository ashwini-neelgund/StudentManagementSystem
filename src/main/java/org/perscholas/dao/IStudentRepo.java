package org.perscholas.dao;

import org.perscholas.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepo extends JpaRepository<Student, Long> {

    Student findByStudentEmail(String email);

    boolean existsByStudentNameAndStudentEmail(String studentName, String studentEmail);

    boolean existsByStudentEmailAndStudentPwd(String email, String password);
}
