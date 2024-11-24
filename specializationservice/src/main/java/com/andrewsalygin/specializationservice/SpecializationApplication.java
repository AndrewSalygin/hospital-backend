package com.andrewsalygin.specializationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.andrewsalygin.specializationservice.client")
public class SpecializationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpecializationApplication.class, args);
    }
}
