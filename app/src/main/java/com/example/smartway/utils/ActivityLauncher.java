package com.example.smartway.utils;

import android.app.Activity;
import android.content.Intent;

import com.example.smartway.ui.home.HomeActivity;
import com.example.smartway.ui.login.LoginActivity;
import com.example.smartway.ui.onboarding.OnBoardingActivity;

public class ActivityLauncher {

    public static void launchHomeActivity(Activity activity) {
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
    }

    public static void launchOnBoardingActivity(Activity activity) {
        Intent intent = new Intent(activity, OnBoardingActivity.class);
        activity.startActivity(intent);
    }

    public static void launchLoginActivity(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }

}
