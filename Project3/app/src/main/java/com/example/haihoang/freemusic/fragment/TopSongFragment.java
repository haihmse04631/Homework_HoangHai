package com.example.haihoang.freemusic.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haihoang.freemusic.R;
import com.example.haihoang.freemusic.adapter.TopSongAdapter;
import com.example.haihoang.freemusic.database.DatabaseHandler;
import com.example.haihoang.freemusic.database.MusicTypeModel;
import com.example.haihoang.freemusic.database.TopSongModel;
import com.example.haihoang.freemusic.event.OnClickMusicTypeEvent;
import com.example.haihoang.freemusic.event.OnUpdateRvFav;
import com.example.haihoang.freemusic.network.MusicInterface;
import com.example.haihoang.freemusic.network.RetrofitInstance;
import com.example.haihoang.freemusic.network.TopSongResponseJSON;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopSongFragment extends Fragment {
    @BindView(R.id.tv_title)
    TextView tvMusicType;
    @BindView(R.id.iv_favorite2)
    ImageView ivFavorite;
    @BindView(R.id.iv_music_type)
    ImageView ivMusticType;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.rv_top_songs)
    RecyclerView rvTopSongs;
    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;
    @BindView(R.id.process)
    AVLoadingIndicatorView avLoad;

    public MusicTypeModel musicTypeModel;

    private TopSongAdapter topSongAdapter;
    public static List<TopSongModel> topSongModelList = new ArrayList<>();

    public TopSongFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_song, container, false);
        EventBus.getDefault().register(this);
        setupUI(view);
        loadData();
        return view;
    }

    private void loadData() {
        MusicInterface musicInterface = RetrofitInstance.getInstance().create(MusicInterface.class);
        musicInterface.getTopSongs(musicTypeModel.id).enqueue(new Callback<TopSongResponseJSON>() {
            @Override
            public void onResponse(Call<TopSongResponseJSON> call, Response<TopSongResponseJSON> response) {
                avLoad.hide();
                List<TopSongResponseJSON.FeedJSON.EntryJSON> entryJSONList = response.body().feed.entry;
                for(int i=0; i<entryJSONList.size(); i++){
                    TopSongModel topSongModel = new TopSongModel();
                    topSongModel.singer = entryJSONList.get(i).artist.label;
                    topSongModel.song = entryJSONList.get(i).name.label;
                    topSongModel.smallImage = entryJSONList.get(i).image.get(2).label;
                    topSongModel.index = i;
                    topSongModelList.add(topSongModel);
                    topSongAdapter.notifyItemChanged(i);
                }
            }

            @Override
            public void onFailure(Call<TopSongResponseJSON> call, Throwable t) {
                Toast.makeText(getContext(), "No connection!", Toast.LENGTH_SHORT).show();
                Log.d("Error!", "onFailure: " + t.toString());
            }
        });
    }

    @Subscribe(sticky = true)
    public void onReceivedOnMusicTypeClicked(OnClickMusicTypeEvent onClickMusicTypeEvent){
        musicTypeModel = onClickMusicTypeEvent.musicTypeModel;

    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        tvMusicType.setText(musicTypeModel.key);
        Picasso.with(getContext()).load(musicTypeModel.imageID).into(ivMusticType);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.e("vertical offset", " " + verticalOffset);

                if(verticalOffset == 0){
                    toolbar.setBackground(getResources().getDrawable(R.drawable.custom_gradient2));
                }else{
                    toolbar.setBackground(null);
                }
            }
        });

        topSongAdapter = new TopSongAdapter(getContext(), topSongModelList) ;
        rvTopSongs.setAdapter(topSongAdapter);
        rvTopSongs.setLayoutManager(new LinearLayoutManager(getContext()));

        rvTopSongs.setItemAnimator(new SlideInLeftAnimator());
        rvTopSongs.getItemAnimator().setAddDuration(300);
        avLoad.show();

        if(musicTypeModel.isFavorite){
            ivFavorite.setColorFilter(Color.RED);
        }else {
            ivFavorite.setColorFilter(Color.WHITE);
        }

        ivFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler.updateFavorite(musicTypeModel);
                if(musicTypeModel.isFavorite){
                    ivFavorite.setColorFilter(Color.RED);
                }else {
                    ivFavorite.setColorFilter(Color.WHITE);
                }
                EventBus.getDefault().postSticky(new OnUpdateRvFav());
            }
        });


    }

}
