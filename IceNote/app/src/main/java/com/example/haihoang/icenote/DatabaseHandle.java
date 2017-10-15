package com.example.haihoang.icenote;

import android.content.ContentValues;
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
    private SQLiteDatabase sqlWrite;
    List<Note> noteList;

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

    public List<Note> getListNote(){
        noteList = new ArrayList<>();
        sqLiteDatabase = assetsHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from tbl_ice_note", null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            String id = cursor.getString(0);
            String title = cursor.getString(1);
            String content = cursor.getString(2);
            String time = cursor.getString(3);
            boolean bookmark = cursor.getInt(4) != 0;
            Note note = new Note(id,title,content,time,bookmark);
            noteList.add(note);
            cursor.moveToNext();
        }

        for(int i=0; i<noteList.size(); i++){
            Log.e("check data", noteList.get(i).toString());
        }

        return noteList;
    }

    public void addListNote(String title, String content, String time, int bookmark){
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("content",content);
        contentValues.put("time", time);
        contentValues.put("bookmark", bookmark);
        sqLiteDatabase.insertOrThrow("tbl_ice_note", null, contentValues);

    }

    public void deleteNote(String id){
        sqlWrite = assetsHelper.getWritableDatabase();
        String querry = "delete from tbl_ice_note where id =" + id;
        sqlWrite.execSQL(querry);
    }

    public void updateBookmark(String id){
        sqlWrite = assetsHelper.getWritableDatabase();
        String querry = "update tbl_ice_note set bookmark = 1 where id =" + id;
        sqlWrite.execSQL(querry);
    }

    public void updateContent(String id, String content, int position){
        sqlWrite = assetsHelper.getWritableDatabase();
        String querry = "update tbl_ice_note set content = '" + content + "'where id =" + id;
        noteList.get(position).setContent(content);
        sqlWrite.execSQL(querry);
    }
}
