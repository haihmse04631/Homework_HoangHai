package com.example.haihoang.freemusic.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.haihoang.freemusic.R;
import com.example.haihoang.freemusic.adapter.ViewPagerAdapter;
import com.example.haihoang.freemusic.database.OfflineListManager;
import com.example.haihoang.freemusic.database.TopSongModel;
import com.example.haihoang.freemusic.event.OnClickTopSongEvent;
import com.example.haihoang.freemusic.fragment.MainPlayer;
import com.example.haihoang.freemusic.util.MusicHandler;
import com.example.haihoang.freemusic.util.Utils;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.rl_mini)
    LinearLayout rlMini;
    @BindView(R.id.sb_mini)
    SeekBar sbMini;
    @BindView(R.id.tv_singer)
    TextView tvSinger;
    @BindView(R.id.btn_play_pause)
    FloatingActionButton btnPlayPause;
    @BindView(R.id.tv_song)
    TextView tvSong;
    @BindView(R.id.iv_song)
    ImageView ivSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        OfflineListManager.loadFile(this);
        setupUI();
    }

    @Subscribe(sticky = true)
    public void onReceivedTopSong(OnClickTopSongEvent onClickTopSongEvent){
        TopSongModel topSongModel = onClickTopSongEvent.topSongModel;

        rlMini.setVisibility(View.VISIBLE);

        tvSinger.setText(topSongModel.singer);
        tvSong.setText(topSongModel.song);
        Log.e("image", topSongModel.smallImage);
        if(topSongModel.status == 1){
            Picasso.with(this).load(R.drawable.offline_song).transform(new CropCircleTransformation()).into(ivSong);
        }else{
            Picasso.with(this).load(topSongModel.smallImage).transform(new CropCircleTransformation()).into(ivSong);
        }

        MusicHandler.getSearchSong(topSongModel, this);
        MusicHandler.updateUIRealtime(sbMini, btnPlayPause, ivSong, null, null);
    }

//    @Subscribe(sticky = true)
//    public void onReceivedOfflineSong(OnClickOfflineSongEvent onClickOfflineSongEvent) throws IOException {
//        OfflineSongModel offlineSongModel = onClickOfflineSongEvent.offlineSongModel;
//
//        rlMini.setVisibility(View.VISIBLE);
//
//        tvSinger.setText(offlineSongModel.singer);
//        tvSong.setText(offlineSongModel.song);
//        Picasso.with(this).load(R.drawable.offline_song).transform(new CropCircleTransformation()).into(ivSong);
//
//        MusicHandler.playOfflineMusic(offlineSongModel.path, offlineSongModel, this);
//        MusicHandler.updateUIRealtimeOffline(sbMini, btnPlayPause, ivSong, null, null);
//    }

    private void setupUI() {
        ButterKnife.bind(this);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_view_list_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_favorite_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_file_download_black_24dp));

        tabLayout.getTabAt(0).getIcon().setAlpha(255);
        tabLayout.getTabAt(1).getIcon().setAlpha(100);
        tabLayout.getTabAt(2).getIcon().setAlpha(100);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(255);
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(100);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        rlMini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.openFragment(getSupportFragmentManager(), R.id.rl_main_player, new MainPlayer());
            }
        });

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        sbMini.setPadding(0,0,0, 0);
        btnPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicHandler.playPauseMusic();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() != 0){
            super.onBackPressed();
        }else{
            moveTaskToBack(true);
        }
    }
}
