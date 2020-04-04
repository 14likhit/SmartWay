package com.example.smartway.ui.userprofile;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.example.smartway.R;
import com.example.smartway.base.BaseActivity;
import com.example.smartway.databinding.ActivityUserProfileBinding;
import com.example.smartway.utils.ActivityLauncher;

public class UserProfileActivity extends BaseActivity {

    public static final String TAG = "UserProfileActivity";

    private ActivityUserProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_profile);
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityLauncher.launchHomeActivity(UserProfileActivity.this);
                finish();
            }
        });
    }
}
