package org.perscholas.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Slf4j
@Controller
@SessionAttributes("student")
@RequestMapping("/login")
public class HomeController {
  @GetMapping
  public String login() {
    return "login";
  }

  @PostMapping()
  public String authenticate() {
    return "register";
  }
}


