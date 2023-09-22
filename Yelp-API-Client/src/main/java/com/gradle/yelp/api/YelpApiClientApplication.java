package com.gradle.yelp.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YelpApiClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(YelpApiClientApplication.class, args);
        System.out.println("hello");
    }

}
