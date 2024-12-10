package com.example.securitytest;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SecuritytestApplication {

    public static void main(String[] args) {

        SpringApplication.run(SecuritytestApplication.class, args);
    }

}
