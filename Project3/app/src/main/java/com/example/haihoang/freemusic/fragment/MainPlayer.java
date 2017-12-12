package com.example.haihoang.freemusic.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haihoang.freemusic.R;
import com.example.haihoang.freemusic.database.TopSongModel;
import com.example.haihoang.freemusic.event.OnClickTopSongEvent;
import com.example.haihoang.freemusic.util.DownloadHandler;
import com.example.haihoang.freemusic.util.MusicHandler;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainPlayer extends Fragment {
    @BindView(R.id.iv_close)
    ImageView ivBack;
    @BindView(R.id.tv_song_name)
    TextView tvSong;
    @BindView(R.id.tv_singer_name)
    TextView tvSinger;
    @BindView(R.id.iv_download)
    ImageView ivDowload;
    @BindView(R.id.iv_song)
    ImageView ivSong;
    @BindView(R.id.tv_duration_time_song)
    TextView tvStartTime;
    @BindView(R.id.sb_main)
    SeekBar sbMini;
    @BindView(R.id.tv_current_time_song)
    TextView tvEndTime;
    @BindView(R.id.iv_pre)
    ImageView ivPre;
    @BindView(R.id.fb_play)
    FloatingActionButton ivPlay;
    @BindView(R.id.iv_next)
    ImageView ivNext;
    TopSongModel topSongModel;
    public MainPlayer() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_player, container, false);

        setupUI(view);
        EventBus.getDefault().register(this);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        ivDowload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MusicHandler.mediaPlayer != null && MusicHandler.mediaPlayer.isPlaying()){
                    Toast.makeText(getActivity(), "This song has downloaded!", Toast.LENGTH_LONG).show();
                }else {
                    DownloadHandler.downloadSearchSong(topSongModel, getContext());
                }
            }
        });
        return view;
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);
    }

    @Subscribe(sticky = true)
    public void onMiniPlayerClicked(OnClickTopSongEvent onClickTopSongEvent){
        topSongModel = onClickTopSongEvent.topSongModel;

        tvSong.setText(topSongModel.song);
        tvSinger.setText(topSongModel.singer);
        if(topSongModel.status == 1){
            Picasso.with(getContext()).load(R.drawable.offline_song).transform(new CropCircleTransformation()).into(ivSong);
        }else{
            Picasso.with(getContext()).load(topSongModel.lagreImage).transform(new CropCircleTransformation()).into(ivSong);
        }

        MusicHandler.updateUIRealtime(sbMini, ivPlay , ivSong, tvStartTime, tvEndTime);
        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicHandler.playPauseMusic();
            }
        });

    }
//    @Subscribe(sticky = true)
//    public void onMiniPlayerOfflineClicked(OnClickOfflineSongEvent onClickOfflineSongEvent){
//        offlineSongModel = onClickOfflineSongEvent.offlineSongModel;
//
//        tvSong.setText(offlineSongModel.song);
//        tvSinger.setText(offlineSongModel.singer);
//        Picasso.with(getContext()).load(R.drawable.offline_song).transform(new CropCircleTransformation()).into(ivSong);
//
//        MusicHandler.updateUIRealtimeOffline(sbMini, ivPlay , ivSong, tvStartTime, tvEndTime);
//        ivPlay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                MusicHandler.playPauseMusic();
//            }
//        });
//
//    }


}
