package com.example.haihoang.freemusic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.Switch;

import com.example.haihoang.freemusic.fragment.DownloadFragment;
import com.example.haihoang.freemusic.fragment.FavoriteFragment;
import com.example.haihoang.freemusic.fragment.MusicTypeFragment;

/**
 * Created by haihm on 11/20/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0: return new MusicTypeFragment();
            case 1: return new FavoriteFragment();
            case 2: return new DownloadFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
