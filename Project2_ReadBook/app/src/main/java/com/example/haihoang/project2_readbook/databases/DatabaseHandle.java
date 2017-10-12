package com.example.haihoang.project2_readbook.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haihoang on 10/7/17.
 */

public class DatabaseHandle {

    private  AssetsHelper assetsHelper;
    private SQLiteDatabase sqLiteDatabase;
    private  SQLiteDatabase sqlWrite;

    public DatabaseHandle(Context context) {
        assetsHelper = new AssetsHelper(context);

    }

    private static DatabaseHandle databaseHandle;
    public static DatabaseHandle getInstance(Context context){
        if(databaseHandle == null){
            databaseHandle = new DatabaseHandle(context);
        }
        return databaseHandle;

    }

    public List<StoryModel> getListStory(){
        List<StoryModel> storyModelList = new ArrayList<>();
        sqLiteDatabase = assetsHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from tbl_short_story", null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            String image = cursor.getString(1);
            String title = cursor.getString(2);
            String description = cursor.getString(3);
            String content = cursor.getString(4);
            String author = cursor.getString(5);
            boolean bookmark = cursor.getInt(6) != 0;
            StoryModel storyModel = new StoryModel(image,title, description, content, author,bookmark );
            storyModelList.add(storyModel);
            cursor.moveToNext();
        }
        for(int i=0; i<storyModelList.size(); i++){
            Log.e("check data", storyModelList.get(i).toString());

        }
        return storyModelList;
    }

    public void updateBookmark(String id){
        sqlWrite = assetsHelper.getWritableDatabase();
        String querry = "update tbl_short_story set bookmark = 1 where id =" + id;
        sqlWrite.execSQL(querry);
    }
}
