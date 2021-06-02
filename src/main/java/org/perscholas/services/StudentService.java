package org.perscholas.services;

import org.perscholas.dao.AuthGroupRepository;
import org.perscholas.dao.CourseRepository;
import org.perscholas.dao.StudentRepository;
import org.perscholas.models.AuthGroup;
import org.perscholas.models.Course;
import org.perscholas.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentService {
  private final StudentRepository studentRepo;
  private final CourseRepository courseRepo;
  private final AuthGroupRepository authRepo;

  @Autowired
  public StudentService(
      StudentRepository studentRepo, CourseRepository courseRepo, AuthGroupRepository authRepo) {
    this.studentRepo = studentRepo;
    this.courseRepo = courseRepo;
    this.authRepo = authRepo;
  }

  public Iterable<Student> getAllStudents() {
    return studentRepo.findAll();
  }

  public Student getStudentByEmail(String email) {
    return studentRepo.findByStudentEmail(email);
  }

  public boolean checkIfStudentExists(String studentName, String studentEmail) {
    return studentRepo.existsByStudentNameAndStudentEmail(studentName, studentEmail);
  }

  public boolean isValid(String email, String password) {
    return studentRepo.existsByStudentEmailAndStudentPwd(email, password);
  }

  public boolean registerStudentToCourse(Long studentId, Long courseId) {
    Student student = studentRepo.getById(studentId);
    List<Course> courses = student.getStudentCourses();
    Course course = courseRepo.getById(courseId);
    if (courses.contains(course)) {
      return false;
    }
    courses.add(course);
    student.setStudentCourses(courses);
    studentRepo.save(student);
    return true;
  }

  public List<Course> getStudentCourses(Long studentId) {
    return studentRepo.getById(studentId).getStudentCourses();
  }

  public void addStudent(Student student) {
    student.setStudentPwd(new BCryptPasswordEncoder(4).encode(student.getStudentPwd()));
    studentRepo.save(student);
    authRepo.save(new AuthGroup(student.getStudentEmail(), "ROLE_STUDENT"));
  }

  public void updateStudent(Student student) {
    studentRepo.save(student);
  }
}
