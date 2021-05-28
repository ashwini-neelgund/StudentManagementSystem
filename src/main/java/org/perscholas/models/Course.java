package org.perscholas.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Component
public class Course implements Serializable {

    static final long serialVersionUID = 6381462249347345007L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long courseId;

    @NotBlank(message = "Please enter a course name")
    @Length(max = 25, message = "Max name length is 25 characters")
    String courseName;

    @NotBlank(message = "Please enter a name")
    @Length(max = 25, message = "Max name length is 25 characters")
    String courseInstructor;

    @ManyToMany(mappedBy = "studentCourses", targetEntity = Student.class)
    List<Student> enrolledStudents;

    /**
     * Adds a student to a course's student roster.
     * @param student student to add to the course
     */
    public void addStudent(Student student) {

        this.enrolledStudents.add(student);

    }

}
