package com.example.haihoang.freemusic.database;

import android.util.Log;

import java.util.List;

import io.realm.Realm;

/**
 * Created by haihm on 12/16/2017.
 */

public class DatabaseHandler {
    private static Realm realm = Realm.getDefaultInstance();

    public static void addMusicType(MusicTypeModel musicTypeModel){
        realm.beginTransaction();
        realm.copyToRealm(musicTypeModel);
        realm.commitTransaction();
    }

    public static List<MusicTypeModel> getMusicTypes(){
        return realm.where(MusicTypeModel.class).findAll();
    }

    public static void updateFavorite(MusicTypeModel musicTypeModel){
        realm.beginTransaction();
        musicTypeModel.isFavorite = !musicTypeModel.isFavorite;
        realm.commitTransaction();
    }

    public static List<MusicTypeModel> getListFavirite(){
        return realm.where(MusicTypeModel.class).equalTo("isFavorite", true).findAll();
    }
}
