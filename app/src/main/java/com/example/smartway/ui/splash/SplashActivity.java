package com.example.smartway.ui.splash;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.smartway.R;
import com.example.smartway.base.BaseActivity;
import com.example.smartway.databinding.ActivitySplashBinding;
import com.example.smartway.utils.ActivityLauncher;

public class SplashActivity extends BaseActivity {

    public static final String TAG = "SplashActivity";

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        startTimer();
    }

    private void startTimer() {
        //30 Sec Timer.
        binding.getRoot().postDelayed(new Runnable() {
            @Override
            public void run() {
//                if (PreferenceHelper.getInstance().getUser() != null && PreferenceHelper.getInstance().getUser().getActiveUser() != null) {
//                    if (PreferenceHelper.getInstance().getUser().getActiveUser().equals(User.USER_TYPE_TENANT)) {
//                        ActivityLauncher.launchTenantActivity(SplashActivity.this);
//                    } else if (PreferenceHelper.getInstance().getUser().getActiveUser().equals(User.USER_TYPE_PG_OWNER)) {
//                        ActivityLauncher.launchPGActivity(SplashActivity.this);
//                    } else {
//                        ActivityLauncher.launchLoginActivity(SplashActivity.this);
//                    }
//                } else {
//                    ActivityLauncher.launchLoginActivity(SplashActivity.this);
//                }
                ActivityLauncher.launchOnBoardingActivity(SplashActivity.this);
                finish();
            }
        }, 500);
    }

}
