package org.perscholas.dao;

import org.perscholas.models.AuthGroup;
import org.perscholas.models.Course;
import org.perscholas.models.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DaoConfig {
  @Bean
  public CommandLineRunner dataLoader(
      CourseRepository courseRepo,
      StudentRepository studentRepo,
      AuthGroupRepository authRepo,
      PasswordEncoder encoder) {
    return args -> {
      courseRepo.save(new Course("Intro to CS", "Donnie Wahlberg"));
      courseRepo.save(new Course("Data Structures and Algorithms", "Mark Wahlberg"));
      courseRepo.save(new Course("Elementary Particle Physics", "Homer Simpson"));
      courseRepo.save(new Course("Hamburgers of 17th Century Denmark", "James Jamerson"));
      courseRepo.save(new Course("Pre-Pre-Calculus", "Duncan Davis"));
      courseRepo.save(new Course("Intro to Arson", "Frank Flames"));
      courseRepo.save(new Course("Flea Training", "Flopsy Bunny"));
      courseRepo.save(new Course("Skydiving", "Serge Koussevitsky"));
      courseRepo.save(new Course("Flying for Squirrels", "Sandra Squirrel"));
      courseRepo.save(new Course("31st Century Quantum Computing", "Betty Future"));
      courseRepo.save(new Course("Managing Software Projects Underwater", "Amanda Diver"));
      studentRepo.save(new Student("Dave Smith", "dave@email.com", encoder.encode("Password2")));
      studentRepo.save(new Student("Steve Palko", "steve@gmail.com", encoder.encode("Password1")));
      studentRepo.save(
          new Student("John Jefferies", "jjef@mail.com", encoder.encode("P@sswurd34")));
      studentRepo.save(
          new Student("Amelia Arthurs", "aarthurs@icloud.com", encoder.encode("G00dP@ssword")));
      studentRepo.save(
          new Student("Jane Jones", "jjones@hotmail.com", encoder.encode("N3wP@ssword")));
      studentRepo.save(
          new Student("Vanessa Vance", "vancinator@gamil.com", encoder.encode("Paaaaasword1")));
      studentRepo.save(new Student("Carol Kaye", "kayec@gmail.com", encoder.encode("BeachBoys22")));
      authRepo.save(new AuthGroup("dave@email.com", "ROLE_ADMIN"));
    };
  }
}
