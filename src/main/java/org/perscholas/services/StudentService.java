package org.perscholas.services;

import org.perscholas.dao.ICourseRepo;
import org.perscholas.dao.IStudentRepo;
import org.perscholas.models.Course;
import org.perscholas.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentService {

    /*
            - add class annotations
            - add @Transactional on class or on each method
            - add crud methods
     */
    private IStudentRepo studentRepo;
    private ICourseRepo courseRepo;

    @Autowired
    public StudentService(IStudentRepo studentRepo, ICourseRepo courseRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
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
        studentRepo.save(student);
    }
}
