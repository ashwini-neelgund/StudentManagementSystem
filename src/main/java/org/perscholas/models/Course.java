package org.perscholas.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * Represents courses at a school.
 */
@Data
@NoArgsConstructor
@Entity
@Component
public class Course implements Serializable {

    static final long serialVersionUID = 6381462249347345007L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @NotBlank(message = "Please enter a course name")
    private String courseName;

    @NotBlank(message = "Please enter a name")
    @Length(max = 25, message = "Max name length is 25 characters")
    private String courseInstructor;

    @ManyToMany(mappedBy = "studentCourses", targetEntity = Student.class)
    private List<Student> enrolledStudents;

    public Course(String name, String instructor) {

        this.courseName = name;
        this.courseInstructor = instructor;

    }

    /**
     * Adds a student to a course's student roster.
     *
     * @param student student to add to the course
     */
    public void addStudent(Student student) {

        this.enrolledStudents.add(student);

    }

}
