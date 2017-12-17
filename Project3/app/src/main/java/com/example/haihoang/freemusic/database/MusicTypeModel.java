package com.example.haihoang.freemusic.database;

import io.realm.RealmObject;

/**
 * Created by haihm on 11/20/2017.
 */

public class MusicTypeModel extends RealmObject {
    public String key;
    public String id;
    public int imageID;

    public boolean isFavorite;

}
