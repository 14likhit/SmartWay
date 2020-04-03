package com.example.smartway.data;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Service to implement all Api endpoints
 */
public interface ApiService {

    @GET("/repositories")
    Call<String> getTrendingGitRepositories();
}

