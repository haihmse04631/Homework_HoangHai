package com.example.haihoang.project2_readbook.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.example.haihoang.project2_readbook.R;
import com.example.haihoang.project2_readbook.adapter.ViewPagerAdaper;
import com.example.haihoang.project2_readbook.databases.StoryModel;

public class ReadingActivity extends AppCompatActivity {
    private ViewPager viewPager;
    ProgressBar prbStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        viewPager = (ViewPager) findViewById(R.id.vp_reading);
        StoryModel storyModel = (StoryModel) getIntent().getSerializableExtra(MainActivity.STORY_KEY);

        ViewPagerAdaper adaper = new ViewPagerAdaper(this, storyModel);
        viewPager.setAdapter(adaper);

        prbStatus = (ProgressBar) findViewById(R.id.prbStatus);
        prbStatus.setMax(adaper.getCount());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                prbStatus.setProgress(position + 1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
}
