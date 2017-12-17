package com.example.haihoang.freemusic;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by haihm on 12/16/2017.
 */

public class MyAplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
    }
}
