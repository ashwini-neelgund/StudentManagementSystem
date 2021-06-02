package org.perscholas.security;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.dao.AuthGroupRepository;
import org.perscholas.dao.StudentRepository;
import org.perscholas.models.AuthGroup;
import org.perscholas.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AppUserDetailsService implements UserDetailsService {
  private final StudentRepository studentRepo;
  private final AuthGroupRepository authGroupRepo;

  @Autowired
  public AppUserDetailsService(StudentRepository studentRepo, AuthGroupRepository authGroupRepo) {
    this.studentRepo = studentRepo;
    this.authGroupRepo = authGroupRepo;
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    Student student = studentRepo.findByStudentEmail(s);
    if (student == null) {
      throw new UsernameNotFoundException("User '" + s + "' not found.");
    }
    List<AuthGroup> authGroups = authGroupRepo.findByAuthUsername(s);
    return new AppUserPrincipal(student, authGroups);
  }
}
