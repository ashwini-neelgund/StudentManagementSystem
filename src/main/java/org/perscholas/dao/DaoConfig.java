package org.perscholas.dao;

import org.perscholas.models.Course;
import org.perscholas.models.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class pre-loads the database with some students and courses on application start-up.
 */
@Configuration
public class DaoConfig {

    @Bean
    public CommandLineRunner dataLoader(ICourseRepo courseRepo, IStudentRepo studentRepo) {

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
            studentRepo.save(new Student("Dave Smith", "dave@email.com", "Password2"));
            studentRepo.save(new Student("Steve Palko", "steve@gmail.com", "Password1"));
            studentRepo.save(new Student("John Jefferies", "jjef@mail.com", "P@sswurd34"));
            studentRepo.save(new Student("Amelia Arthurs", "aarthurs@icloud.com", "G00dP@ssword"));
            studentRepo.save(new Student("Jane Jones", "jjones@hotmail.com", "N3wP@ssword"));
            studentRepo.save(new Student("Vanessa Vance", "vancinator@gamil.com", "Paaaaasword1"));
            studentRepo.save(new Student("Carol Kaye", "kayec@gmail.com", "BeachBoys22"));

        };

    }



}
