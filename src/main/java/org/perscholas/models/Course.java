package org.perscholas.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Slf4j
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Component
public class Course implements Serializable {
    static final long serialVersionUID = 6381462249347345007L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @NonNull
    @NotBlank(message = "Please enter a course name")
    private String courseName;

    @NonNull
    @NotBlank(message = "Please enter a name")
    @Length(max = 25, message = "Max name length is 25 characters")
    private String courseInstructor;

    @Fetch(FetchMode.JOIN)
    @ManyToMany(mappedBy = "studentCourses", targetEntity = Student.class)
    private List<Student> enrolledStudents;

    public void addStudent(Student student) {
        this.enrolledStudents.add(student);
    }
}
