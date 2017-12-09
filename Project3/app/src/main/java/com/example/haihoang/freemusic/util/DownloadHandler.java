package com.example.haihoang.freemusic.util;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.haihoang.freemusic.database.TopSongModel;
import com.thin.downloadmanager.DefaultRetryPolicy;
import com.thin.downloadmanager.DownloadManager;
import com.thin.downloadmanager.DownloadRequest;
import com.thin.downloadmanager.DownloadStatusListener;
import com.thin.downloadmanager.ThinDownloadManager;

/**
 * Created by haihm on 12/10/2017.
 */

public class DownloadHandler {
    public static ThinDownloadManager downloadManager;
    public static void downloadSearchSong(TopSongModel topSongModel, Context context){
        Uri downloadUri = Uri.parse(topSongModel.url);
        Uri destinationUri = Uri.parse(context.getExternalCacheDir().toString() + "/music.mp3");
        DownloadRequest downloadRequest = new DownloadRequest(downloadUri)
                .setRetryPolicy(new DefaultRetryPolicy())
                .setDestinationURI(destinationUri).setPriority(DownloadRequest.Priority.HIGH)
                .setDownloadListener(new DownloadStatusListener() {
                    @Override
                    public void onDownloadComplete(int id) {
                        Log.e("downloadComplete", "complete");
                    }

                    @Override
                    public void onDownloadFailed(int id, int errorCode, String errorMessage) {
                        Log.e("failed", "error!");
                    }

                    @Override
                    public void onProgress(int id, long totalBytes, long downloadedBytes, int progress) {
                        Log.e("progress", progress + "");
                    }
                });

        downloadManager = new ThinDownloadManager();
        downloadManager.add(downloadRequest);
    }


}
