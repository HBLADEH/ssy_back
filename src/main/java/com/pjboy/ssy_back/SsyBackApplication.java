package com.pjboy.ssy_back;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.pjboy.ssy_back.generator","com.pjboy.ssy_back.mapper"})
public class SsyBackApplication {

  public static void main(String[] args) {
    SpringApplication.run(SsyBackApplication.class, args);
  }

}
