package com.example.smartway.ui.userprofile;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.example.smartway.R;
import com.example.smartway.base.BaseActivity;
import com.example.smartway.databinding.ActivityUserProfileBinding;

public class UserProfileActivity extends BaseActivity {

    public static final String TAG = "UserProfileActivity";

    private ActivityUserProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_profile);
    }
}
