package com.spring.yelp.api.controller;

import com.spring.yelp.api.service.YelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/yelp")
public class YelpController {
    private final YelpService yelpService;

    @Autowired
    public YelpController(YelpService yelpService) {
        this.yelpService = yelpService;
    }

    @GetMapping(value = "/businesses")
    public String searchBusinesses(@RequestParam("phone") String phone) throws IOException {
        return yelpService.searchBusinesses(phone);
    }
}


