package com.example.trainticketing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.trainticketing.model")
@EnableJpaRepositories(basePackages = "com.example.trainticketing.repository")
public class TrainTicketingApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrainTicketingApplication.class, args);
    }
}

