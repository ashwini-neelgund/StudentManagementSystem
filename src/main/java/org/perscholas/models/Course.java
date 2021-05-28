package org.perscholas.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


//Lombok
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
//Database
@Entity
//Spring Boot
@Component


public class Course implements Serializable {
    static final long serialVersionUID = 6381462249347345007L;

    @NotBlank(message = "Please enter a course name")
    @Length(max = 25, message = "Max name length is 25 characters")
    String courseName;

    @NotBlank(message = "Please enter a name")
    @Length(max = 25, message = "Max name length is 25 characters")
    String courseInstructor;

    @ManyToMany(mappedBy = "studentCourses", targetEntity = Student.class)
    List<Student> enrolledStudents;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long courseId;


}
