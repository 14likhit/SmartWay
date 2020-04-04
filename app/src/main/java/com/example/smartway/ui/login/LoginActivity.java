package com.example.smartway.ui.login;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.example.smartway.R;
import com.example.smartway.base.BaseActivity;
import com.example.smartway.databinding.ActivityLoginBinding;
import com.example.smartway.utils.ActivityLauncher;

public class LoginActivity extends BaseActivity {

    public static final String TAG = "LoginActivity";

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityLauncher.launchHomeActivity(LoginActivity.this);
                finish();
            }
        });
    }
}
