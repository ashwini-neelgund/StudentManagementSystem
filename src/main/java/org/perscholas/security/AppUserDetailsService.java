package org.perscholas.security;

import org.perscholas.dao.IAuthGroup;
import org.perscholas.dao.IStudentRepo;
import org.perscholas.models.AuthGroup;
import org.perscholas.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserDetailsService implements UserDetailsService {

  private final IStudentRepo iStudentRepo;
  private final IAuthGroup iAuthGroup;

  @Autowired
  public AppUserDetailsService(IStudentRepo iStudentRepo, IAuthGroup iAuthGroup) {

    this.iStudentRepo = iStudentRepo;
    this.iAuthGroup = iAuthGroup;
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

    Student student = iStudentRepo.findByStudentEmail(s);

    if (student == null) {

      throw new UsernameNotFoundException("User '" + s + "' not found.");
    }

    List<AuthGroup> authGroups = iAuthGroup.findByAuthUsername(s);

    return new AppUserPrincipal(student, authGroups);
  }
}
