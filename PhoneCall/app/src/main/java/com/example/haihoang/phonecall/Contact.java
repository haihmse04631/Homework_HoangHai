package com.example.haihoang.phonecall;

/**
 * Created by haihoang on 10/5/17.
 */

public class Contact {
    private String name, phoneNumber;
    private int avatar;

    public Contact(String name, String phoneNumber, int avatar) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}
