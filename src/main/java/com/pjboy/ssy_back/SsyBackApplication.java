package com.pjboy.ssy_back;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.pjboy.ssy_back.config","com.pjboy.ssy_back.mapper"})
public class SsyBackApplication {

  public static void main(String[] args) {
    SpringApplication.run(SsyBackApplication.class, args);
  }

}
