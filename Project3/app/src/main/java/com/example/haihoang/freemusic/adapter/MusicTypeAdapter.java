package com.example.haihoang.freemusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haihoang.freemusic.R;
import com.example.haihoang.freemusic.activity.MainActivity;
import com.example.haihoang.freemusic.database.MusicTypeModel;
import com.example.haihoang.freemusic.event.OnClickMusicTypeEvent;
import com.example.haihoang.freemusic.fragment.TopSongFragment;
import com.example.haihoang.freemusic.util.Utils;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by haihm on 11/20/2017.
 */

public class MusicTypeAdapter extends RecyclerView.Adapter<MusicTypeAdapter.MusicTypeViewHodel> {
    List<MusicTypeModel> musicTypeModelList;
    Context context;

    public MusicTypeAdapter(List<MusicTypeModel> musicTypeModelList, Context context) {
        this.musicTypeModelList = musicTypeModelList;
        this.context = context;
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
        View view;

        public MusicTypeViewHodel(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, itemView);
        }

        public void setData(final MusicTypeModel musicTypeModel){
            Picasso.with(context).load(musicTypeModel.imageID).into(imageView);
            tvName.setText(musicTypeModel.key);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utils.openFragment( ((MainActivity) context).getSupportFragmentManager(), R.id.rl_main, new TopSongFragment());
                    EventBus.getDefault().postSticky(new OnClickMusicTypeEvent(musicTypeModel));
                }
            });
        }
    }
}
