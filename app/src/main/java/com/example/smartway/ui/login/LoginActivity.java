package com.example.smartway.ui.login;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.example.smartway.R;
import com.example.smartway.base.BaseActivity;
import com.example.smartway.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity {

    public static final String TAG = "LoginActivity";

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
    }
}
