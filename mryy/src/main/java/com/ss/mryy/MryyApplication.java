package com.ss.mryy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan("com.ss.mryy.dao")
public class MryyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MryyApplication.class, args);
    }

}
