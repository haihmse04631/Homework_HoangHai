package com.example.haihoang.project2_readbook.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haihoang.project2_readbook.R;
import com.example.haihoang.project2_readbook.databases.DatabaseHandle;
import com.example.haihoang.project2_readbook.databases.StoryModel;

public class DescriptionActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTitle,tvAuthor,tvDescription;
    private Button btnStartReading;
    private ImageView ivBack,ivBookmark,ivStory;
    private StoryModel storyModel;
    private int check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        setupUI();
        addListioners();
        loadData();
        if(storyModel.getBookmark()) check = 1;
        else check = 2;
        ivBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("click", "I'm click");
                if(check%2 != 0){
                    DatabaseHandle.getInstance(DescriptionActivity.this).resetBookmark(storyModel.getId());
                    check++;
                    ivBookmark.setImageResource(R.drawable.ic_bookmark_black_24dp);
                }
                else if(check%2 == 0){
                    DatabaseHandle.getInstance(DescriptionActivity.this).updateBookmark(storyModel.getId());
                    check++;
                    ivBookmark.setImageResource(R.drawable.ic_bookmark_24dp);
                }
            }
        });

    }

    private void loadData() {
        storyModel = (StoryModel) getIntent().getExtras().getSerializable(MainActivity.STORY_KEY);
        tvTitle.setText(storyModel.getTitle());
        tvDescription.setText(storyModel.getDescription());
        tvAuthor.setText(storyModel.getAuthor());
        String[] base64 = storyModel.getImage().split(",");
        Bitmap bitmap = BitmapFactory.decodeByteArray(
                Base64.decode(base64[1], Base64.DEFAULT), 0,
                (Base64.decode(base64[1], Base64.DEFAULT)).length
        );
        ivStory.setImageBitmap(bitmap);
        if(storyModel.getBookmark()){
            ivBookmark.setImageResource(R.drawable.ic_bookmark_24dp);

        }else{
            ivBookmark.setImageResource(R.drawable.ic_bookmark_black_24dp);
        }
    }

    private void addListioners() {
        ivBack.setOnClickListener(this);
        ivBookmark.setOnClickListener(this);
        btnStartReading.setOnClickListener(this);
    }

    private void setupUI(){
        tvAuthor = (TextView) findViewById(R.id.tvAuthor);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        btnStartReading = (Button) findViewById(R.id.btnStartReading);
        ivBack = (ImageView) findViewById(R.id.ivBack);
        ivBookmark = (ImageView) findViewById(R.id.ivBookmark);
        ivStory = (ImageView) findViewById(R.id.ivStory);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.ivBack: {
                back();
                break;
            }
            case R.id.ivBookmark: {
                bookmark();
                break;
            }
            case R.id.btnStartReading: {
                break;
            }
        }

    }

    private void bookmark() {

    }

    private void back() {
        Intent intent = new Intent(DescriptionActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
