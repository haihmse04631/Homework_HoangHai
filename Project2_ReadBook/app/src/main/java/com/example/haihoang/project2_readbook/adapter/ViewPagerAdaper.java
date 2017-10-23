package com.example.haihoang.project2_readbook.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v4.view.PagerAdapter;
import android.text.StaticLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.haihoang.project2_readbook.R;
import com.example.haihoang.project2_readbook.databases.StoryModel;
import com.example.haihoang.project2_readbook.ulti.PageSpliter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haihoang on 10/14/17.
 */

public class ViewPagerAdaper extends PagerAdapter {

    private Context context;

    private List<String> listPage = new ArrayList<>();

    public ViewPagerAdaper(Context context, StoryModel storyModel) {
        this.context = context;

        new PageSpliter(PageSpliter.getHeight(context), PageSpliter.getWidth(context), storyModel.getContent());
        StaticLayout staticLayout = PageSpliter.createrStaticLayout(storyModel.getContent());
        listPage = PageSpliter.splitPage(staticLayout);
    }

    @Override
    public int getCount() {
        return listPage.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_view_pager, container, false);

        ImageView imageView = view.findViewById(R.id.iv_reading);
        Bitmap bitmap = getBitmap(position);
        imageView.setImageBitmap(bitmap);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public Bitmap getBitmap(int position) {
        Bitmap bitmap = Bitmap.createBitmap(PageSpliter.getWidth(context), PageSpliter.getHeight(context), Bitmap.Config.ARGB_8888);
        Log.e("size", PageSpliter.getHeight(context) + " " + PageSpliter.getWidth(context));

        Canvas canvas = new Canvas(bitmap);
        StaticLayout staticLayout = PageSpliter.createrStaticLayout(listPage.get(position));
        staticLayout.draw(canvas);

        return bitmap;
    }
}
