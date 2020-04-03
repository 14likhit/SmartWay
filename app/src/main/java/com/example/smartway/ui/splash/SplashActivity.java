package com.example.smartway.ui.splash;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.smartway.R;
import com.example.smartway.base.BaseActivity;
import com.example.smartway.databinding.ActivitySplashBinding;

public class SplashActivity extends BaseActivity {

    public static final String TAG = "SplashActivity";

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
    }
}
