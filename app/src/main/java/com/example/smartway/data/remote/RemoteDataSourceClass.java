package com.example.smartway.data.remote;

import com.example.smartway.data.ApiService;

/**
 * Repository Class to fetch response from network.
 */
public class RemoteDataSourceClass implements RemoteDataSource {

    private static final String TAG = "RemoteDataSourceClass";

    private static RemoteDataSource mInstance;
    private ApiService service;

    private RemoteDataSourceClass(ApiService apiService) {
        this.service = apiService;
    }

    public static RemoteDataSource getInstance(ApiService apiService) {
        if (mInstance == null) {
            mInstance = new RemoteDataSourceClass(apiService);
        }

        return mInstance;
    }
}
