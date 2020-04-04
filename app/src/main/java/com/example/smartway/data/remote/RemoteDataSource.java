package com.example.smartway.data.remote;

import com.example.smartway.data.model.PostingData;

public interface RemoteDataSource {

    interface Callback<T> {
        void onSuccess(T data);

        void onFailed(String error);
    }

    void postUserLocation(PostingData postingData, Callback<String> callback);

}
