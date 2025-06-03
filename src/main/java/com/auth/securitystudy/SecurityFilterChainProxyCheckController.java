package com.auth.securitystudy;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SecurityFilterChainProxyCheckController {

  private final ObjectProvider<SecurityFilterChain> filterChainProvider;

  @GetMapping("/default/check")
  public String check() {
    SecurityFilterChain filterChain = filterChainProvider.getObject();
    filterChain.getFilters().forEach(System.out::println);
    return "ok";
  }

  @Bean
  public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
    return new InMemoryUserDetailsManager(User.withUsername("user")
        .password("{noop}1111")
        .build());
  }
}