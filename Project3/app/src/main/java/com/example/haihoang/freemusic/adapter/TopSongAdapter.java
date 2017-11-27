package com.example.haihoang.freemusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haihoang.freemusic.R;
import com.example.haihoang.freemusic.database.TopSongModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by haihm on 11/25/2017.
 */

public class TopSongAdapter extends RecyclerView.Adapter<TopSongAdapter.TopSongViewHodel> {
    public Context context;
    public List<TopSongModel> topSongModelList;

    public TopSongAdapter(Context context, List<TopSongModel> topSongModelList) {
        this.context = context;
        this.topSongModelList = topSongModelList;
    }

    @Override
    public TopSongViewHodel onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.item_list_top_songs, parent ,false);

        return new TopSongViewHodel(itemView);
    }

    @Override
    public void onBindViewHolder(TopSongViewHodel holder, int position) {
        holder.setData(topSongModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return topSongModelList.size();
    }


    public class TopSongViewHodel extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_song)
        ImageView ivSong;
        @BindView(R.id.tv_song)
        TextView tvSong;
        @BindView(R.id.tv_singer)
        TextView tvSinger;
        public TopSongViewHodel(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(TopSongModel topSongModel){
            Picasso.with(context).load(topSongModel.smallImage).transform(new CropCircleTransformation()).into(ivSong);
            tvSong.setText(topSongModel.song);
            tvSinger.setText(topSongModel.singer);
        }
    }
}
