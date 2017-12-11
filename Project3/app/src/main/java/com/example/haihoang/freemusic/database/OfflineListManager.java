package com.example.haihoang.freemusic.database;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by haihm on 12/11/2017.
 */

public class OfflineListManager {
    public static ArrayList<String> listSongName;
    public static void loadFile(Context context){
        //File path = new File("/storage/emulated/0/Android/data/com.example.haihoang.freemusic/files/");
        File path = new File(context.getExternalFilesDir("").getPath());
        Log.e("path", path.toString());
        listSongName = new ArrayList<>();
        File list[] = path.listFiles();
        for (int i=0; i<list.length; i++){
            listSongName.add(list[i].getName());
        }

        for(int i=0; i<listSongName.size(); i++){
            Log.e("fileName", listSongName.get(i));
        }
    }

}
