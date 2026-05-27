package com.example.tfgtravelbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TfgTravelBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TfgTravelBackendApplication.class, args);
    }

}
