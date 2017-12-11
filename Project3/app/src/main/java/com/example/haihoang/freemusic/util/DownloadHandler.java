package com.example.haihoang.freemusic.util;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.example.haihoang.freemusic.database.OfflineListManager;
import com.example.haihoang.freemusic.database.TopSongModel;
import com.thin.downloadmanager.DefaultRetryPolicy;
import com.thin.downloadmanager.DownloadManager;
import com.thin.downloadmanager.DownloadRequest;
import com.thin.downloadmanager.DownloadStatusListener;
import com.thin.downloadmanager.DownloadStatusListenerV1;
import com.thin.downloadmanager.ThinDownloadManager;

/**
 * Created by haihm on 12/10/2017.
 */

public class DownloadHandler {
    public static ThinDownloadManager downloadManager;
    public static void downloadSearchSong(final TopSongModel topSongModel, final Context context){
        Uri downloadUri = Uri.parse(topSongModel.url);
        final Uri destinationUri = Uri.parse(context.getExternalFilesDir(topSongModel.song + "-" + topSongModel.singer) + "");
        DownloadRequest downloadRequest = new DownloadRequest(downloadUri)
                .setRetryPolicy(new DefaultRetryPolicy())
                .setDestinationURI(destinationUri).setPriority(DownloadRequest.Priority.HIGH)
                .setStatusListener(new DownloadStatusListenerV1() {
                    @Override
                    public void onDownloadComplete(DownloadRequest downloadRequest) {
                        Toast.makeText(context, "Download complete!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onDownloadFailed(DownloadRequest downloadRequest, int errorCode, String errorMessage) {
                        Toast.makeText(context, "No internet!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onProgress(DownloadRequest downloadRequest, long totalBytes, long downloadedBytes, int progress) {
                    }
                });
        downloadManager = new ThinDownloadManager();
        String songName = topSongModel.song + "-" + topSongModel.singer;
        if(checkListOffline(topSongModel,songName)){
            Toast.makeText(context, "This song has download!",Toast.LENGTH_LONG).show();
        }else{
            downloadManager.add(downloadRequest);
            OfflineListManager.loadFile(context);
        }

    }

    private static boolean checkListOffline(TopSongModel topSongModel, String songName){
        for(int i=0 ; i<OfflineListManager.listSongName.size(); i++){
            if(OfflineListManager.listSongName.get(i).equals(songName)) return true;
        }

        return false;
    }



}
