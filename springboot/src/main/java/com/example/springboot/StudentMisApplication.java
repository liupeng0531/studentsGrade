package com.example.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.example.springboot.mapper")
public class StudentMisApplication {

  public static void main(String[] args) {
    SpringApplication.run(StudentMisApplication.class, args);
  }

}
