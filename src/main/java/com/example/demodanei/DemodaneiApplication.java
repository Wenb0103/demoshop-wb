package com.example.demodanei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@MapperScan("com.example.demodanei.mapper")
public class DemodaneiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemodaneiApplication.class, args);
    }

}
