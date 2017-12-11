package com.example.haihoang.freemusic.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.haihoang.freemusic.R;
import com.example.haihoang.freemusic.activity.MainActivity;
import com.example.haihoang.freemusic.database.OfflineSongModel;
import com.example.haihoang.freemusic.database.TopSongModel;
import com.example.haihoang.freemusic.util.MusicHandler;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by haihm on 12/9/2017.
 */

public class MusicNotificaiton {
    private static final int NOTIFICATION_ID = 1;
    private static RemoteViews remoteViews;
    public static NotificationCompat.Builder builder;
    public static NotificationManager notificationManager;

    public static void setupNotification(Context context, TopSongModel topSongModel){
        remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_layout);
        remoteViews.setTextViewText(R.id.tv_song, topSongModel.song);
        remoteViews.setTextViewText(R.id.tv_singer, topSongModel.singer);
        remoteViews.setImageViewResource(R.id.btn_play_pause, R.drawable.ic_pause_black_24dp);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContent(remoteViews)
                .setContentIntent(pendingIntent)
                .setOngoing(true);

        notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        Picasso.with(context).load(topSongModel.smallImage).transform(new CropCircleTransformation())
                .into(remoteViews, R.id.iv_song, NOTIFICATION_ID, builder.build());
        setOnClickPlayPause(context);

        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }

    public static void setupNotificationOffline(Context context, OfflineSongModel offlineSongModel){
        remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_layout);
        remoteViews.setTextViewText(R.id.tv_song, offlineSongModel.song);
        remoteViews.setTextViewText(R.id.tv_singer, offlineSongModel.singer);
        remoteViews.setImageViewResource(R.id.btn_play_pause, R.drawable.ic_pause_black_24dp);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContent(remoteViews)
                .setContentIntent(pendingIntent)
                .setOngoing(true);

        notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        Picasso.with(context).load(R.drawable.offline_song).transform(new CropCircleTransformation())
                .into(remoteViews, R.id.iv_song, NOTIFICATION_ID, builder.build());
        setOnClickPlayPause(context);

        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }

    public static void updateNotification() {
        if(MusicHandler.hybridMediaPlayer != null){
            if(MusicHandler.hybridMediaPlayer.isPlaying()){
                remoteViews.setImageViewResource(R.id.btn_play_pause, R.drawable.ic_pause_black_24dp);
                builder.setOngoing(true);
            }else{
                remoteViews.setImageViewResource(R.id.btn_play_pause, R.drawable.ic_play_arrow_black_24dp);
                builder.setOngoing(false);
            }
        }
            if(MusicHandler.mediaPlayer.isPlaying()){
                remoteViews.setImageViewResource(R.id.btn_play_pause, R.drawable.ic_pause_black_24dp);
                builder.setOngoing(true);
            }else{
                remoteViews.setImageViewResource(R.id.btn_play_pause, R.drawable.ic_play_arrow_black_24dp);
                builder.setOngoing(false);
            }


        notificationManager.notify(NOTIFICATION_ID,builder.build());
    }

    private static void setOnClickPlayPause(Context context) {
        Intent intent = new Intent(context, MusicService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);
        remoteViews.setOnClickPendingIntent(R.id.btn_play_pause, pendingIntent);
    }
}
