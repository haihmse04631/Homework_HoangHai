package com.example.haihoang.freemusic.util;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haihoang.freemusic.R;
import com.example.haihoang.freemusic.database.TopSongModel;
import com.example.haihoang.freemusic.network.MusicInterface;
import com.example.haihoang.freemusic.network.MusicResponseJSON;
import com.example.haihoang.freemusic.network.RetrofitInstance;
import com.example.haihoang.freemusic.network.TopSongResponseJSON;
import com.example.haihoang.freemusic.notification.MusicNotificaiton;

import org.w3c.dom.Text;

import java.util.logging.Handler;

import javax.security.auth.callback.Callback;

import hybridmediaplayer.HybridMediaPlayer;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by haihm on 11/29/2017.
 */

public class MusicHandler {
    public static HybridMediaPlayer hybridMediaPlayer;
    private static boolean isUpdate = true;

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
                    MusicNotificaiton.setupNotification(context, topSongModel);
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
        MusicNotificaiton.updateNotification();
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

    public static void updateUIRealtime(final SeekBar seekBar,
                                        final FloatingActionButton floatingActionButton,
                                        final ImageView imageView,
                                        final TextView tvCurrent,
                                        final TextView tvDuration) {
        final android.os.Handler handler = new android.os.Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //update UI
                if( isUpdate && hybridMediaPlayer != null){
                    seekBar.setMax(hybridMediaPlayer.getDuration());
                    seekBar.setProgress(hybridMediaPlayer.getCurrentPosition());

                    if(hybridMediaPlayer.isPlaying()){
                        floatingActionButton.setImageResource(R.drawable.ic_pause_black_24dp);

                    }else{
                        floatingActionButton.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    }

                    Utils.rotateAnimation(imageView, hybridMediaPlayer.isPlaying());

                    if(tvCurrent != null){
                        tvCurrent.setText(Utils.convertTime(hybridMediaPlayer.getCurrentPosition()));
                        tvDuration.setText(Utils.convertTime(hybridMediaPlayer.getDuration()));
                    }
                }

                handler.postDelayed(this, 100);
            }
        };
        runnable.run();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isUpdate = false;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                hybridMediaPlayer.seekTo(seekBar.getProgress());
                isUpdate = true;
            }
        });


    }


}
