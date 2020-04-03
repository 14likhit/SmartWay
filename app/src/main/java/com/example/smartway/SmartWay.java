package com.example.smartway;

import android.app.Application;

import com.example.smartway.data.ApiClient;
import com.example.smartway.preferenceHelper.PreferenceHelper;

public class SmartWay extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        PreferenceHelper.initialize(this);
        ApiClient.initialiseRetrofitInstance(this);
    }
}
