package com.gradle.yelp.api.servicee;

import com.gradle.yelp.api.controller.RetrofitAPIController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class YelpServiceTest {

    @Mock
    private RetrofitAPIController retrofitAPI;

    private YelpService yelpService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        yelpService = new YelpService(
                "Bearer G1N1EkbH7TwOk26i3sTVwE4PbOrdCgLwLk49i4qwrIIFu8Dr6OJ-PyRNTzLtXzhF7WedHq2D6ON0WRmjUppiKKC0OUQFl9fB7ikxsQMxX34yMXMnMHLVzkGwoaECZXYx",
                "https://api.yelp.com");
        yelpService.retrofitAPI = retrofitAPI;
    }

    @Test
    public void testGetBusinessesByPhoneSuccess() throws IOException {
        String phone = "123-456-7890";
        String mockResponse = "mock yelp response";
        Call<String> call = mock(Call.class);
        when(call.execute()).thenReturn(Response.success(mockResponse));
        when(retrofitAPI.getAllBusinessByPhone(any(), eq(phone))).thenReturn(call);

        ResponseEntity<String> responseEntity = yelpService.getBusinessesByPhone(phone);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponse, responseEntity.getBody());
    }

    @Test
    public void testGetBusinessesByPhoneRequestFailed() throws IOException {
        String phone = null;
        Call<String> call = mock(Call.class);
        when(call.execute()).thenReturn(Response.error(400, okhttp3.ResponseBody.create(null, "Not Found")));
        when(retrofitAPI.getAllBusinessByPhone(any(), eq(phone))).thenReturn(call);

        ResponseEntity<String> responseEntity = yelpService.getBusinessesByPhone(phone);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Request failed with code: 400", responseEntity.getBody());
    }

    @Test
    public void testGetBusinessesByPhoneIOException() throws IOException {
        String phone = "123-456-7890";
        Call<String> call = mock(Call.class);

        when(retrofitAPI.getAllBusinessByPhone(any(), eq(phone))).thenReturn(call);
        when(call.execute()).thenThrow(new IOException("Request execution failed."));
        assertThrows(RuntimeException.class, () -> yelpService.getBusinessesByPhone(phone));

        verify(retrofitAPI, times(1)).getAllBusinessByPhone(any(), eq(phone));
    }


}