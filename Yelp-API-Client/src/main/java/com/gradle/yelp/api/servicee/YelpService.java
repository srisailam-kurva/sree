package com.gradle.yelp.api.servicee;

import com.gradle.yelp.api.controller.RetrofitAPIController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;

@Service
public class YelpService {
    private final String API_KEY;
    RetrofitAPIController retrofitAPI;

    public YelpService(@Value("${yelp.api.key_value}") String API_KEY,
                       @Value("${yelp.base_url}") String BASE_URL) {
        this.API_KEY = API_KEY;
        Retrofit client = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        this.retrofitAPI = client.create(RetrofitAPIController.class);
    }

    public ResponseEntity<String> getBusinessesByPhone(String phone) {
        try {
            Call<String> call = retrofitAPI.getAllBusinessByPhone(API_KEY, phone);
            Response<String> response = call.execute();

            if (!response.isSuccessful()) {
                return new ResponseEntity<>("Request failed with code: " + response.code(), HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(response.body(), HttpStatus.OK);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
