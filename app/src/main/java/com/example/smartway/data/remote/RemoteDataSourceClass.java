package com.example.smartway.data.remote;

import androidx.annotation.NonNull;

import com.example.smartway.data.ApiService;
import com.example.smartway.data.model.BaseResponse;
import com.example.smartway.data.model.PostingData;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository Class to fetch response from network.
 */
public class RemoteDataSourceClass implements RemoteDataSource {

    private static final String TAG = "RemoteDataSourceClass";

    private static RemoteDataSourceClass mInstance;
    private ApiService service;

    private RemoteDataSourceClass(ApiService apiService) {
        this.service = apiService;
    }

    public static RemoteDataSourceClass getInstance(ApiService apiService) {
        if (mInstance == null) {
            mInstance = new RemoteDataSourceClass(apiService);
        }

        return mInstance;
    }

    @Override
    public void postUserLocation(PostingData postingData, Callback<String> callback) {
        JsonObject body = new JsonObject();
        body.addProperty("id", postingData.getId());
        body.addProperty("name", postingData.getName());
        body.addProperty("latitude", postingData.getLatitude());
        body.addProperty("longitude", postingData.getLongitude());
        body.addProperty("deviceid", postingData.getDeviceid());
        body.addProperty("accuracy", postingData.getAccuracy());
        body.addProperty("countrycode", postingData.getCountrycode());
        body.addProperty("countryname", postingData.getCountryname());
        body.addProperty("postalcode", postingData.getPostalcode());
        body.addProperty("locality", postingData.getLocality());

        String url = "https://location-service-mxl7a62gpq-uc.a.run.app/";

        Call<BaseResponse<String>> request = service.postUserLocation(body);

        request.enqueue(new retrofit2.Callback<BaseResponse<String>>() {
            @Override
            public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
                if (response.body() != null && response.body().getData() != null) {
                    callback.onSuccess(response.body().getData().getMessage());
                } else {
                    callback.onFailed(response.message());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<String>> call, Throwable t) {
                handleOnFailed(t, callback);
            }
        });
    }

    private void handleOnFailed(@NonNull Throwable throwable, Callback callback) {
        Throwable cause = throwable.getCause();
        if (cause != null && cause.getMessage().equalsIgnoreCase("Network is Unreachable")) {
            callback.onFailed("No Internet available");
        } else {
            callback.onFailed("Something went wrong, please try again");
        }
    }

}
