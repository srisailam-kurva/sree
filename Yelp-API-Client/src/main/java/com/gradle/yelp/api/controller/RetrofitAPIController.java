package com.gradle.yelp.api.controller;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RetrofitAPIController {
    @GET(value = "/v3/businesses/search/phone")
    Call<String> getAllBusinessByPhone(
            @Header("Authorization") String authorization,
            @Query("phone") String phone
    );
}

