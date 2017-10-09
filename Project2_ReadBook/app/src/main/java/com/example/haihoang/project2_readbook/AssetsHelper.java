package com.example.haihoang.project2_readbook;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by haihoang on 10/7/17.
 */

public class AssetsHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "short_story.db";
    private static final int DATABASE_VERSION = 1;

    public AssetsHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
