package com.gradle.yelp.api.controller;

import com.gradle.yelp.api.servicee.YelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/yelp")
public class YelpController {

    @Autowired
    public YelpService yelpService;

    @GetMapping(value = "/businessesByPhone", produces = "application/json")
    public ResponseEntity<String> getBusinessesByPhone(@RequestParam("phone") String phone) throws IOException {
        return yelpService.getBusinessesByPhone(phone);
    }


}

