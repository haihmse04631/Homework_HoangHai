package com.example.haihoang.freemusic.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.haihoang.freemusic.database.TopSongModel;
import com.example.haihoang.freemusic.network.MusicInterface;
import com.example.haihoang.freemusic.network.MusicResponseJSON;
import com.example.haihoang.freemusic.network.RetrofitInstance;
import com.example.haihoang.freemusic.network.TopSongResponseJSON;

import javax.security.auth.callback.Callback;

import hybridmediaplayer.HybridMediaPlayer;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by haihm on 11/29/2017.
 */

public class MusicHandler {
    private static HybridMediaPlayer hybridMediaPlayer;

    public static void getSearchSong(final TopSongModel topSongModel, final Context context){
        MusicInterface musicInterface = RetrofitInstance.getInstance().create(MusicInterface.class);
        musicInterface.getSearchSong(topSongModel.song + " " + topSongModel.singer).enqueue(new retrofit2.Callback<MusicResponseJSON>() {
            @Override
            public void onResponse(Call<MusicResponseJSON> call, Response<MusicResponseJSON> response) {
                Log.e("check", response.isSuccessful() + " " + response.code());
                if(response.code() == 200){
                    topSongModel.url = response.body().data.url;
                    topSongModel.lagreImage = response.body().data.thumbnail;

                    playMusic(context, topSongModel);
                }else{
                    Toast.makeText(context, "Not Found", Toast.LENGTH_LONG).show();
                    return;
                }

            }

            @Override
            public void onFailure(Call<MusicResponseJSON> call, Throwable t) {
                Log.e("check2", "I'm Here!");
                Toast.makeText(context, "No Internet!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void playPauseMusic() {
        if(hybridMediaPlayer.isPlaying()){
            hybridMediaPlayer.pause();
        }else{
            hybridMediaPlayer.play();
        }
    }

    private static void playMusic(Context context, TopSongModel topSongModel) {

        if(hybridMediaPlayer != null){
            hybridMediaPlayer.pause();
            hybridMediaPlayer.release();
        }

        hybridMediaPlayer = HybridMediaPlayer.getInstance(context);
        hybridMediaPlayer.setDataSource(topSongModel.url);
        hybridMediaPlayer.prepare();
        hybridMediaPlayer.setOnPreparedListener(new HybridMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(HybridMediaPlayer hybridMediaPlayer) {
                hybridMediaPlayer.play();
            }
        });
    }


}
