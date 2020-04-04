package com.example.smartway.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.smartway.data.ApiClient;
import com.example.smartway.data.ApiService;
import com.example.smartway.data.model.PostingData;
import com.example.smartway.data.remote.RemoteDataSource;
import com.example.smartway.data.remote.RemoteDataSourceClass;

public class HomeViewModel extends AndroidViewModel {

    public MutableLiveData<String> isLocationPosted = null;
    public MutableLiveData<String> isError = null;

    private RemoteDataSourceClass remoteDataSource;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        remoteDataSource = RemoteDataSourceClass.getInstance(ApiClient.getRetrofitInstance().create(ApiService.class));
    }

    public void postUserLocation(PostingData postingData) {
        remoteDataSource.postUserLocation(postingData, new RemoteDataSource.Callback<String>() {
            @Override
            public void onSuccess(String data) {
                isLocationPosted.setValue(data);
            }

            @Override
            public void onFailed(String error) {
                isError.setValue(error);
            }
        });
    }
}
