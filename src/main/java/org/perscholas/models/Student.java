package org.perscholas.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Student implements Serializable, UserDetails {
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

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getPassword() {
    return studentPwd;
  }

  @Override
  public String getUsername() {
    return studentEmail;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
