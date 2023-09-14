package com.spring.yelp.api.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class YelpService {
    @Value("${yelp.api.key_value}")
    private String API_KEY;
    @Value("${yelp.base_url}")
    private String BASE_URL;
    @Value("${bearer.vaue}")
    private String bearerValue;

    private final OkHttpClient client = new OkHttpClient();

    public String searchBusinesses(String phone) throws IOException {
        Request request = new Request.Builder()
                .url(getBASE_URL(phone))
                .method("GET", null)
                .addHeader("Authorization", bearerValue + API_KEY)
                .addHeader("accept", "application/json")
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Request failed with code: " + response.code());
            } else {
                return response.body().string();
            }
        }
    }

    public String getBASE_URL(String phone) {
        String url = BASE_URL + "phone=" + phone;
        return url;
    }
}