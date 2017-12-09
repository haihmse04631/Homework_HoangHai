package com.example.haihoang.freemusic.notification;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.haihoang.freemusic.util.MusicHandler;

/**
 * Created by haihm on 12/9/2017.
 */

public class MusicService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MusicHandler.playPauseMusic();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.e("task", "taskRemoved");
        MusicNotificaiton.builder.setOngoing(false);
        MusicNotificaiton.notificationManager.cancelAll();
    }
}
