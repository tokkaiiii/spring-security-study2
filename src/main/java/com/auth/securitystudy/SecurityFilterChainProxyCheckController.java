package com.auth.securitystudy;

import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SecurityFilterChainProxyCheckController {

  private final ObjectProvider<SecurityFilterChain> filterChainProvider;
  private final SecurityContextHolder contextHolder = new SecurityContextHolder();

  @GetMapping("/")
  public String index(@CurrentSecurityContext SecurityContext context) {

    return "index";
  }

  @GetMapping("/default/check")
  public String check() {
    SecurityFilterChain filterChain = filterChainProvider.getObject();
    SecurityContext context = SecurityContextHolder.getContextHolderStrategy().getContext();
    Authentication authentication = context.getAuthentication();
    System.out.println("authentication = " + authentication);
    SecurityContextHolder.getContextHolderStrategy().clearContext();
    return "ok";
  }


}