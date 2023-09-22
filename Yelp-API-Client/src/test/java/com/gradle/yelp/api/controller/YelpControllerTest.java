package com.gradle.yelp.api.controller;


import com.gradle.yelp.api.servicee.YelpService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class YelpControllerTest {

    @InjectMocks
    private YelpController yelpController;

    @Mock
    private YelpService yelpService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGetBusinessesByPhoneValid() throws IOException {
        String validPhone = "1234567890";
        String mockResponse = "Mocked Yelp Response";

        when(yelpService.getBusinessesByPhone(validPhone)).thenReturn(new ResponseEntity<>(mockResponse, HttpStatus.OK));

        ResponseEntity<String> responseEntity = yelpController.getBusinessesByPhone(validPhone);
        verify(yelpService).getBusinessesByPhone(validPhone);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponse, responseEntity.getBody());
    }

    @Test
    public void testGetBusinessesByPhoneRequestFailed() throws IOException {
        String phone = null;
        String errorMessage = "Request failed with code: 400";
        when(yelpService.getBusinessesByPhone(phone)).thenReturn(new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST));

        ResponseEntity<String> responseEntity = yelpController.getBusinessesByPhone(phone);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(errorMessage, responseEntity.getBody());
    }

}
