package com.example.smartway.customwidget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SmartWayWebView extends WebView {

    private Context context;
    private OnWebViewInteractionListener mListener;
    private HashMap<String, String> localData;

    public SmartWayWebView(Context context) {
        super(context);
        this.context = context;
    }

    public SmartWayWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SmartWayWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setInteractionListener(OnWebViewInteractionListener listener) {
        this.mListener = listener;
        init();
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void init() {
        getSettings().setJavaScriptEnabled(true);
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        getSettings().setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        this.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mListener.shouldOverrideUrlLoading(view, url);
                loadUrl(url);
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mListener.onPageStarted(view, url, favicon);
            }

            public void onPageFinished(WebView view, String url) {
                if (localData != null && localData.size() > 0) {
                    updateLocalStorage(localData);
                }
                mListener.onPageFinished(view, url);
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                mListener.onReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
                super.doUpdateVisitedHistory(view, url, isReload);
                mListener.doUpdateVisitedHistory(view, url, isReload);
            }
        });

        setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                return super.onConsoleMessage(consoleMessage);
            }
        });
    }

    public void loadWebPage(String url, HashMap<String, String> cookies, HashMap<String, String> localData) {
        updateCookie(url, cookies);
        updateLocalData(localData);
        this.loadUrl(url);
    }

    private void updateLocalData(HashMap<String, String> localData) {
        if (this.localData == null) {
            this.localData = new HashMap<>();
        }
        //Updating the flag 'appView' for the web front-end to remove headers
        if (localData != null) {
            if (!localData.containsKey("appView")) {
                this.localData.put("appView", String.valueOf(true));
            }
            this.localData.putAll(localData);
        } else {
            this.localData.put("appView", String.valueOf(true));
        }
    }

    public void loadPostUrl(String url, HashMap<String, String> content) {
        StringBuilder postData = new StringBuilder();
        if (content != null && content.size() > 0) {
            for (Object o : content.entrySet()) {
                Map.Entry pair = (Map.Entry) o;
                try {
                    postData.append(String.format(Locale.getDefault(), (postData.length() == 0) ? "%s=%s" :
                            "&%s=%s", pair.getKey(), URLEncoder.encode((String) pair.getValue(), "UTF-8")));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        postUrl(url, postData.toString().getBytes());
    }

    private void updateLocalStorage(HashMap<String, String> userData) {
        for (Map.Entry<String, String> entry : userData.entrySet()) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                evaluateJavascript("window.localStorage.setItem('" + entry.getKey() +
                        "','" + entry.getValue() + "');", null);
                evaluateJavascript("window.localStorage.setItem('" + entry.getKey() +
                        "','" + entry.getValue() + "');", null);
            } else {
                loadUrl("javascript:localStorage.setItem('" + entry.getKey() +
                        "','" + entry.getValue() + "');");
                loadUrl("javascript:localStorage.setItem('" + entry.getKey() +
                        "','" + entry.getValue() + "');");
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void getStringFromLocalStorage(String key) {
        evaluateJavascript("window.localStorage.getItem('" + key + "');", s -> {
            if (s != null && !s.isEmpty()) {
                mListener.onLocalStorageDataFetched(s);
            }
        });
    }

    private void updateCookie(String url, HashMap<String, String> cookies) {
        CookieSyncManager.createInstance(getContext());
        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.setAcceptThirdPartyCookies(this, true);
        } else {
            cookieManager.setAcceptCookie(true);
        }
        for (Map.Entry<String, String> entry : cookies.entrySet()) {
            cookieManager.setCookie(url, String.format(Locale.getDefault(), "%s=%s",
                    entry.getKey(), entry.getValue()));
        }
        CookieSyncManager.getInstance().startSync();
    }

    public interface OnWebViewInteractionListener {
        void onPageStarted(WebView view, String url, Bitmap favicon);

        void onPageFinished(WebView view, String url);

        void onReceivedError(WebView view, int errorCode, String description, String failingUrl);

        void shouldOverrideUrlLoading(WebView view, String url);

        void onLocalStorageDataFetched(String data);

        void doUpdateVisitedHistory(WebView view, String url, boolean isReload);
    }

}
