package com.pjboy.ssy_back;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
class SsyBackApplicationTests {

  @Resource
  PasswordEncoder passwordEncoder;


  @Test
  void contextLoads() {
    System.out.println(passwordEncoder.encode("123456"));
  }

}
