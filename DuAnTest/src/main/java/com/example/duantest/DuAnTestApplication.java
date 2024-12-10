package com.example.duantest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DuAnTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DuAnTestApplication.class, args);
    }

}
