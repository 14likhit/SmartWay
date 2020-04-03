package com.example.smartway.ui.home;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.smartway.R;
import com.example.smartway.base.BaseActivity;
import com.example.smartway.databinding.ActivityHomeBinding;

public class HomeActivity extends BaseActivity {

    public static final String TAG = "HomeActivity";

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
    }
}
