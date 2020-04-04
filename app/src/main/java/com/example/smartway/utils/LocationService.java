package com.example.smartway.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.lifecycle.ViewModelProviders;

import com.example.smartway.data.ApiClient;
import com.example.smartway.data.ApiService;
import com.example.smartway.data.model.PostingData;
import com.example.smartway.data.remote.RemoteDataSource;
import com.example.smartway.data.remote.RemoteDataSourceClass;
import com.example.smartway.ui.home.HomeViewModel;

public class LocationService extends Service {

    private final LocationServiceBinder binder = new LocationServiceBinder();
    private final String TAG = "LocationService";
    private LocationListener mLocationListener;
    private LocationManager mLocationManager;
    private NotificationManager notificationManager;

    private HomeViewModel homeViewModel;
    private RemoteDataSourceClass remoteDataSource;

    private final int LOCATION_INTERVAL = 500;
    private final int LOCATION_DISTANCE = 10;

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private class LocationListener implements android.location.LocationListener {
        private Location lastLocation = null;
        private final String TAG = "LocationListener";
        private Location mLastLocation;

        public LocationListener(String provider) {
            mLastLocation = new Location(provider);
        }

        @Override
        public void onLocationChanged(Location location) {
            mLastLocation = location;
            Log.i(TAG, "LocationChanged: " + location);
            PostingData postingData = new PostingData();
            postingData.setId(14);
            postingData.setName("Goat");
            postingData.setLatitude(mLastLocation.getLatitude());
            postingData.setLongitude(mLastLocation.getLongitude());
            postingData.setAccuracy(mLastLocation.getAccuracy());
            postingData.setDeviceid(null);
            postingData.setCountrycode(null);
            postingData.setCountryname(null);
            postingData.setPostalcode(null);
            postingData.setLocality(null);
            remoteDataSource.postUserLocation(postingData, new RemoteDataSource.Callback<String>() {
                @Override
                public void onSuccess(String data) {
                    Log.i(TAG, "Posted " + data);
                }

                @Override
                public void onFailed(String error) {
                    Log.i(TAG, "Error " + error);
                }
            });
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.e(TAG, "onProviderDisabled: " + provider);
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.e(TAG, "onProviderEnabled: " + provider);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.e(TAG, "onStatusChanged: " + status);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate");
        remoteDataSource = RemoteDataSourceClass.getInstance(ApiClient.getRetrofitInstance().create(ApiService.class));
        startForeground(12345678, getNotification());
        startTracking();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLocationManager != null) {
            try {
                mLocationManager.removeUpdates(mLocationListener);
            } catch (Exception ex) {
                Log.i(TAG, "fail to remove location listners, ignore", ex);
            }
        }
    }

    private void initializeLocationManager() {
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }

    public void startTracking() {
        initializeLocationManager();
        mLocationListener = new LocationListener(LocationManager.GPS_PROVIDER);

        try {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE, mLocationListener);

        } catch (java.lang.SecurityException ex) {
            // Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            // Log.d(TAG, "gps provider does not exist " + ex.getMessage());
        }

    }

    public void stopTracking() {
        this.onDestroy();
    }

    private Notification getNotification() {

        NotificationChannel channel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel("channel_01", "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
        }

        NotificationManager notificationManager = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            notificationManager = getSystemService(NotificationManager.class);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(channel);
        }

        Notification.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Notification.Builder(getApplicationContext(), "channel_01").setAutoCancel(true);
        }
        return builder.build();
    }


    public class LocationServiceBinder extends Binder {
        public LocationService getService() {
            return LocationService.this;
        }
    }


}
