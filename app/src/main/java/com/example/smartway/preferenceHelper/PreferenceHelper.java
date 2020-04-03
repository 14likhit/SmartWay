package com.example.smartway.preferenceHelper;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private static final String PREF_APP_NAME = "app_pref_name_0404";

    private static PreferenceHelper mInstance;
    private SharedPreferences sharedPreferences;

    private static final String KEY_USER = "USER";

    public static void initialize(Context context) {
        if (mInstance == null) {
            mInstance = new PreferenceHelper(context);
        }
    }

    private PreferenceHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_APP_NAME, Context.MODE_PRIVATE);
    }

    public static PreferenceHelper getInstance() {
        if (mInstance == null) {
            throw new IllegalStateException("Call initialize() once before using the Shared Preferences");
        }
        return mInstance;
    }

//    public void saveUser(User user) {
//        sharedPreferences.edit().putString(KEY_USER, new Gson().toJson(user)).apply();
//    }
//
//    public User getUser() {
//        User user = null;
//        try{
//            user = new Gson().fromJson(sharedPreferences.getString(KEY_USER,""),User.class);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return user;
//    }

}
