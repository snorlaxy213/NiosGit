package com.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.springboot.mapper")
@EnableScheduling
public class NiosApplication {

    public static void main(String[] args) {
        SpringApplication.run(NiosApplication.class, args);
    }
}
