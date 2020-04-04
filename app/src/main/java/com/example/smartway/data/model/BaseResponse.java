package com.example.smartway.data.model;

import com.google.gson.annotations.SerializedName;


public class BaseResponse<T> {

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private Data<T> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data<T> getData() {
        return data;
    }

    public void setData(Data<T> data) {
        this.data = data;
    }

}
