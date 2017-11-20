package com.example.haihoang.freemusic.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haihoang.freemusic.R;
import com.example.haihoang.freemusic.database.MusicTypeModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by haihm on 11/20/2017.
 */

public class MusicTypeAdapter extends RecyclerView.Adapter<MusicTypeAdapter.MusicTypeViewHodel> {
    List<MusicTypeModel> musicTypeModelList;

    public MusicTypeAdapter(List<MusicTypeModel> musicTypeModelList) {
        this.musicTypeModelList = musicTypeModelList;
    }

    @Override
    public MusicTypeViewHodel onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_must_type,parent,false);
        return new MusicTypeViewHodel(view);
    }

    @Override
    public void onBindViewHolder(MusicTypeViewHodel holder, int position) {
        holder.setData(musicTypeModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return musicTypeModelList.size();
    }

    public class MusicTypeViewHodel extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_music_type)
        ImageView imageView;
        @BindView(R.id.tv_name)
        TextView tvName;

        public MusicTypeViewHodel(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(MusicTypeModel musicTypeModel){
            imageView.setImageResource(musicTypeModel.imageID);
            tvName.setText(musicTypeModel.key);
        }
    }
}
