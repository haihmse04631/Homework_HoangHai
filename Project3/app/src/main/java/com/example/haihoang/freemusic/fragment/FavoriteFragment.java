package com.example.haihoang.freemusic.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haihoang.freemusic.R;
import com.example.haihoang.freemusic.adapter.MusicTypeAdapter;
import com.example.haihoang.freemusic.database.DatabaseHandler;
import com.example.haihoang.freemusic.event.OnUpdateRvFav;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    @BindView(R.id.rv_list_favorite)
    RecyclerView rvFavorite;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        ButterKnife.bind(this, view);

        rvFavorite.setAdapter(new MusicTypeAdapter(DatabaseHandler.getListFavirite(), getContext()));
        rvFavorite.setLayoutManager(new GridLayoutManager(getContext(), 2));

        EventBus.getDefault().register(this);
        return view;
    }

    @Subscribe(sticky = true)
    public void update(OnUpdateRvFav updateRvFav){
        rvFavorite.setAdapter(new MusicTypeAdapter(DatabaseHandler.getListFavirite(), getContext()));
    }



}
