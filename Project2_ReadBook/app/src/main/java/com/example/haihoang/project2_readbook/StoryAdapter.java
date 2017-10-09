package com.example.haihoang.project2_readbook;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by haihoang on 10/7/17.
 */

public class StoryAdapter extends ArrayAdapter<StoryModel> {

    private Context context;
    private int resource;
    private List<StoryModel> storyModelList;

    public StoryAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<StoryModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.storyModelList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //setup UI
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(resource, parent, false);
        TextView tvTitle = convertView.findViewById(R.id.tv_title);
        TextView tvAuthor = convertView.findViewById(R.id.tv_author);
        ImageView imgStory = convertView.findViewById(R.id.iv_story);

        //set Data
        tvTitle.setText(storyModelList.get(position).getTitle());
        tvAuthor.setText(storyModelList.get(position).getAuthor());
        String[] base64 = storyModelList.get(position).getImage().split(",");
        Bitmap bitmap = BitmapFactory.decodeByteArray(
                Base64.decode(base64[1], Base64.DEFAULT), 0,
                (Base64.decode(base64[1], Base64.DEFAULT)).length
        );

        imgStory.setImageBitmap(bitmap);

        return convertView;
    }
}
