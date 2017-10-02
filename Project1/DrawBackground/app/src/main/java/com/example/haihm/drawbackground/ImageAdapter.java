package com.example.haihm.drawbackground;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


public class ImageAdapter extends BaseAdapter {
    Context context;
    List<String> imagePaths = new ArrayList<>();

    public ImageAdapter(List<String> imagePaths, Context context) {
        this.imagePaths = imagePaths;
        this.context = context;
    }

    @Override
    public int getCount() {
        return imagePaths.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(context);

        //set data
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 200
        ));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setPadding(5,5,5,5);
        imageView.setCropToPadding(true);

        //set data
        Bitmap bitmap = BitmapFactory.decodeFile(imagePaths.get(i));
        imageView.setImageBitmap(bitmap);
        return imageView;
    }
}
