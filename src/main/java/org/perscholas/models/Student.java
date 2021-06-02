package org.perscholas.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** Represents a student at a school. */
@Data
@NoArgsConstructor
@Entity
@Component
public class Student implements Serializable {

  static final long serialVersionUID = 6381462249347345007L;

  @Fetch(FetchMode.JOIN)
  @ManyToMany(targetEntity = Course.class)
  List<Course> studentCourses;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long studentId;

  @NotBlank(message = "Please enter a name")
  @Length(max = 25, message = "Max name length is 25 characters")
  private String studentName;

  @NotBlank(message = "Please enter a password")
  @Pattern(
      regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",
      message =
          "- at least 8 characters\n"
              + "- must contain at least 1 uppercase letter, 1 lowercase letter, and 1 number\n"
              + "- Can contain special characters")
  private String studentPwd;

  @NotBlank(message = "Please enter an email")
  @Email
  private String studentEmail;

  public Student(String name, String email, String password) {

    studentName = name;
    studentEmail = email;
    studentPwd = password;
    studentCourses = new ArrayList<>();
  }

  /**
   * Adds a course to a student's course list.
   *
   * @param course the course to add
   */
  public void addCourse(Course course) {

    this.studentCourses.add(course);
  }

  @Override
  public String toString() {

    return "Student{"
        + "studentId="
        + studentId
        + ", studentName='"
        + studentName
        + '\''
        + ", studentPwd='"
        + studentPwd
        + '\''
        + ", studentEmail='"
        + studentEmail
        + '\''
        + '}';
  }
}
