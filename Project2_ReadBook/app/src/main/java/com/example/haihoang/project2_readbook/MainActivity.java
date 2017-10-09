package com.example.haihoang.project2_readbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<StoryModel> storyModelList = new ArrayList<>();
    private ListView lvStory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storyModelList = DatabaseHandle.getInstance(this).getListStory();

        lvStory = (ListView) findViewById(R.id.lv_story);
        StoryAdapter storyAdapter = new StoryAdapter(this, R.layout.item_lv_story,storyModelList);
        lvStory.setAdapter(storyAdapter);
    }
}
