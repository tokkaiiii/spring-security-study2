package com.auth.securitystudy.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class TestConfig {

  @Bean
  public String test() {
    log.info("<UNK>");
    return "";
  }

}
