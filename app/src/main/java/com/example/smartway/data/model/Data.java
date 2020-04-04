package com.example.smartway.data.model;


import com.google.gson.annotations.SerializedName;


public class Data<T> {

    @SerializedName("message")
    private String mMessage;
    @SerializedName("values")
    private T mValues;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public T getValues() {
        return mValues;
    }

    public void setValues(T values) {
        mValues = values;
    }

}
