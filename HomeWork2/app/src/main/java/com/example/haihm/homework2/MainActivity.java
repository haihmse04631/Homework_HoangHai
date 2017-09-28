package com.example.haihm.homework2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ScrollView cv;
    FloatingActionButton btnAddImage;
    private CustomImageView customImageView;
    private CustomSpace customSpace;
    int[] image = {R.drawable.food_1, R.drawable.food_2, R.drawable.food_3, R.drawable.food_4, R.drawable.food_5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddImage = (FloatingActionButton) findViewById(R.id.btnAdd);
        addRandomImage();
    }

    private void addRandomImage() {
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.imageContainer);
        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rn = new Random();
                int i = rn.nextInt(4) + 1;
                Log.e("random", i + "");
                customImageView = new CustomImageView(MainActivity.this);
                customImageView.setBackgroundResource(image[i]);
                customSpace = new CustomSpace(MainActivity.this);
                linearLayout.addView(customImageView);
                linearLayout.addView(customSpace);
                cv = (ScrollView) findViewById(R.id.scrView);
                cv.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

}
