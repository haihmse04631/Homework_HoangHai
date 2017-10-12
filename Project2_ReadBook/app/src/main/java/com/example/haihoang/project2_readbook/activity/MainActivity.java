package com.example.haihoang.project2_readbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.haihoang.project2_readbook.R;
import com.example.haihoang.project2_readbook.adapter.StoryAdapter;
import com.example.haihoang.project2_readbook.databases.DatabaseHandle;
import com.example.haihoang.project2_readbook.databases.StoryModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String STORY_KEY = "story_key";
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

        lvStory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DescriptionActivity.class);
                intent.putExtra(STORY_KEY, storyModelList.get(i));
                startActivity(intent);
            }
        });
    }
}
