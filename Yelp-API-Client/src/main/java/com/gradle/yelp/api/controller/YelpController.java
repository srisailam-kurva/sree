package com.gradle.yelp.api.controller;

import com.gradle.yelp.api.service.YelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/yelp")
public class YelpController {

    public YelpService yelpService;

    @Autowired
    public YelpController(YelpService yelpService) {
        this.yelpService = yelpService;
    }

    @GetMapping(value = "/businessesByPhone", produces = "application/json")
    public ResponseEntity<String> getBusinessesByPhone(@RequestParam("phone") String phone) throws IOException {
        return yelpService.getBusinessesByPhone(phone);
    }
}

