package org.perscholas.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Slf4j
@Controller
@SessionAttributes("student")
public class HomeController {

  @GetMapping("/403")
  public String accessDenied() { return "403"; }
}
