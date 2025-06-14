package com.auth.securitystudy.config;

import static org.springframework.http.HttpMethod.GET;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
//    AuthenticationManager manager = builder.build();
//    HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();

    http
//        .csrf(csrf-> csrf.csrfTokenRepository(httpSessionCsrfTokenRepository))
        .authorizeHttpRequests(authorizeRequests -> authorizeRequests
            .requestMatchers(GET, "/login").permitAll()
            .anyRequest().authenticated())

        .formLogin(login -> login
            .loginPage("/login")
//            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/", true)
        )
        .rememberMe(httpSecurityRememberMeConfigurer -> httpSecurityRememberMeConfigurer
            .userDetailsService(userDetailsService()))
//        .authenticationManager(manager)
//        .addFilterBefore(customAuthenticationFilter(manager), UsernamePasswordAuthenticationFilter.class)
    ;

    return http.build();
  }

  private CustomAuthenticationFilter customAuthenticationFilter(
      AuthenticationManager authenticationManager) {
    CustomAuthenticationFilter authenticationFilter = new CustomAuthenticationFilter();
    authenticationFilter.setAuthenticationManager(authenticationManager);
    return authenticationFilter;
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return new InMemoryUserDetailsManager(
        User.withUsername("user").password("{noop}1111").roles("USER").build());
  }
}
