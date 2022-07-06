package com.mediscreen.diabetesriskservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DiabetesRiskServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiabetesRiskServiceApplication.class, args);
    }

}
