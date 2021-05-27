package org.perscholas.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

//lombok
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
//database
@Entity
//springboot
@Component
public class Student implements Serializable {
    static final long serialVersionUID = 6381462249347345007L;

    @NotBlank(message = "Please enter a name")
    @NotNull(message = "Please enter a name")
    @Length(max = 25, message = "Max name length is 25 characters")
    String studentName;

    @NotBlank(message = "Please enter a password")
    @NotNull(message = "Please enter a password")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",
            message = "- at least 8 characters\n" +
                    "- must contain at least 1 uppercase letter, 1 lowercase letter, and 1 number\n" +
                    "- Can contain special characters")
    String studentPwd;

    @NotBlank(message = "Please enter an email")
    @NotNull(message = "Please enter an email")
    @Email
    String studentEmail;

    @ManyToMany(targetEntity = Course.class)
    List<Course> studentCourses;

    /*
            note use annotation  '@ToString.Exclude' for Lists
            ----------------
            - add validation for fields
            - relationships
            - utilities methods if any
     */

    //fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long studentId;



}
