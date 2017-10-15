package com.example.haihoang.project2_readbook.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.haihoang.project2_readbook.R;
import com.example.haihoang.project2_readbook.adapter.ViewPagerAdaper;

public class ReadingActivity extends AppCompatActivity {
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        viewPager = (ViewPager) findViewById(R.id.vp_reading);

        ViewPagerAdaper adaper = new ViewPagerAdaper(this);
        viewPager.setAdapter(adaper);


    }
}
