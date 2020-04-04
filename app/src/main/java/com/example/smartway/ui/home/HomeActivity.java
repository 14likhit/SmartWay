package com.example.smartway.ui.home;

import androidx.databinding.DataBindingUtil;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.webkit.WebView;

import com.example.smartway.R;
import com.example.smartway.base.BaseActivity;
import com.example.smartway.customwidget.SmartWayWebView;
import com.example.smartway.databinding.ActivityHomeBinding;
import com.example.smartway.utils.LocationService;

import java.util.HashMap;

public class HomeActivity extends BaseActivity implements SmartWayWebView.OnWebViewInteractionListener {

    public static final String TAG = "HomeActivity";

    private ActivityHomeBinding binding;
    public LocationService gpsService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        final Intent intent = new Intent(this.getApplication(), LocationService.class);
        this.getApplication().startService(intent);
        this.getApplication().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        HashMap<String, String> userInfo = new HashMap<>();
        HashMap<String, String> cookies = new HashMap<>();
        binding.smartWayWebView.setInteractionListener(this);
//        binding.smartWayWebView.setWebViewClient(new WebViewClient());
//        binding.smartWayWebView.setWebChromeClient(new WebChromeClient());
        binding.smartWayWebView.loadWebPage("https://lpmkz.csb.app/", cookies, userInfo);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            String name = className.getClassName();
            if (name.endsWith("LocationService")) {
                gpsService = ((LocationService.LocationServiceBinder) service).getService();
            }
        }

        public void onServiceDisconnected(ComponentName className) {
            if (className.getClassName().equals("BackgroundService")) {
                gpsService = null;
            }
        }
    };

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        binding.loadingProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
    }

    @Override
    public void shouldOverrideUrlLoading(WebView view, String url) {
    }

    @Override
    public void onLocalStorageDataFetched(String data) {
    }

    @Override
    public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
    }
}
