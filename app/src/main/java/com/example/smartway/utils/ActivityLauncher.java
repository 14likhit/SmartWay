package com.example.smartway.utils;

import android.app.Activity;
import android.content.Intent;

import com.example.smartway.ui.home.HomeActivity;

public class ActivityLauncher {

    public static void launchHomeActivity(Activity activity) {
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
    }

}
