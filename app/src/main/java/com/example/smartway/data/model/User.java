package com.example.smartway.data.model;

public class User {

    private Integer ukid;
    private Integer phoneNumber;
    private String userName;
    private Integer userDeviceId;

    public Integer getUkid() {
        return ukid;
    }

    public void setUkid(Integer ukid) {
        this.ukid = ukid;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserDeviceId() {
        return userDeviceId;
    }

    public void setUserDeviceId(Integer userDeviceId) {
        this.userDeviceId = userDeviceId;
    }
}
