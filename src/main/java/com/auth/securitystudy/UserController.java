package com.auth.securitystudy;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
  private final UserDetailsService userDetailsService;

  @GetMapping("/login")
  public String login() {
    return "login";
  }


}
