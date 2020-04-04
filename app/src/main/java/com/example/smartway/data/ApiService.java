package com.example.smartway.data;

import com.example.smartway.data.model.BaseResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Service to implement all Api endpoints
 */
public interface ApiService {

    @POST("/")
    Call<BaseResponse<String>> postUserLocation(@Body JsonObject postingData);
}

