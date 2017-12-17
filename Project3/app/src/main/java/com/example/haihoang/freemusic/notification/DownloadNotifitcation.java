package com.example.haihoang.freemusic.notification;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.haihoang.freemusic.R;
import com.example.haihoang.freemusic.database.TopSongModel;

/**
 * Created by haihm on 12/12/2017.
 */

public class DownloadNotifitcation {
    private static final int NOTIFICATION_ID = 2;
    public static NotificationCompat.Builder builder;
    public static NotificationManager notificationManager;

    public static void setDownloadNotification(Context context, TopSongModel topSongModel){

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(context);
        builder.setContentTitle(topSongModel.song);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setProgress(100, 0, false);
        builder.setAutoCancel(false);

    }

    public static void updateNotification(int progress, TopSongModel topSongModel) {
        try {
            builder.setContentTitle(topSongModel.song)
                    .setOngoing(true)
                    .setProgress(100, progress,false);

            notificationManager.notify(NOTIFICATION_ID, builder.build());
            if (progress == 100)
                deleteNotification();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteNotification() {
        notificationManager.cancel(NOTIFICATION_ID);
        builder = null;
    }

}