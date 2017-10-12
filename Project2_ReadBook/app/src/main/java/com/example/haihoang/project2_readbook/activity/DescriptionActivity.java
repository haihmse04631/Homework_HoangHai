package com.example.haihoang.project2_readbook.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haihoang.project2_readbook.R;
import com.example.haihoang.project2_readbook.databases.StoryModel;

public class DescriptionActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTitle,tvAuthor,tvDescription;
    private Button btnStartReading;
    private ImageView ivBack,ivBookmark,ivStory;
    private StoryModel storyModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        setupUI();
        addListioners();
        loadData();

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
                super.onBackPressed();
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
        finish();
    }
}
