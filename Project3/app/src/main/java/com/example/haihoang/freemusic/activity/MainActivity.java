package com.example.haihoang.freemusic.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.haihoang.freemusic.R;
import com.example.haihoang.freemusic.adapter.ViewPagerAdapter;
import com.example.haihoang.freemusic.database.TopSongModel;
import com.example.haihoang.freemusic.event.OnClickTopSongEvent;
import com.example.haihoang.freemusic.util.MusicHandler;
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
        setupUI();
    }

    @Subscribe(sticky = true)
    public void onReceivedTopSong(OnClickTopSongEvent onClickTopSongEvent){
        TopSongModel topSongModel = onClickTopSongEvent.topSongModel;
        Log.e("OnreceiviedSong", topSongModel.song + "");

        rlMini.setVisibility(View.VISIBLE);

        tvSinger.setText(topSongModel.singer);
        tvSong.setText(topSongModel.song);
        Picasso.with(this).load(topSongModel.smallImage).transform(new CropCircleTransformation()).into(ivSong);

        MusicHandler.getSearchSong(topSongModel, this);
    }

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
}
