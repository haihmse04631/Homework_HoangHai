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

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import jp.wasabeef.picasso.transformations.CropTransformation;

/**
 * Created by haihm on 12/11/2017.
 */

public class DownloadListAdapter extends RecyclerView.Adapter<DownloadListAdapter.DownloadListViewHodel> {
    public Context context;
    public List<TopSongModel> downloadSongModeList;

    public DownloadListAdapter(Context context, List<TopSongModel> downloadSongModeList) {
        this.context = context;
        this.downloadSongModeList = downloadSongModeList;
    }

    @Override
    public DownloadListViewHodel onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.item_list_top_songs, parent, false);

        return new DownloadListViewHodel(itemView);
    }

    @Override
    public void onBindViewHolder(DownloadListViewHodel holder, int position) {
        holder.setData(downloadSongModeList.get(position));
    }

    @Override
    public int getItemCount() {
        return downloadSongModeList.size();
    }

    public class DownloadListViewHodel extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_song)
        ImageView ivSong;
        @BindView(R.id.tv_song)
        TextView tvSong;
        @BindView(R.id.tv_singer)
        TextView tvSinger;
        View view;
        public DownloadListViewHodel(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }

        public void setData(final  TopSongModel topSongModel){
            tvSong.setText(topSongModel.song);
            tvSinger.setText(topSongModel.singer);
            ivSong.setImageResource(R.drawable.ic_favorite_black_24dp);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
