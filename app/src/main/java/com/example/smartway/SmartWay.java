package com.example.smartway;

import android.app.Application;

import com.example.smartway.data.ApiClient;

public class SmartWay extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ApiClient.initialiseRetrofitInstance(this);
    }
}
