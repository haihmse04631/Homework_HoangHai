package com.example.haihm.drawbackground;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.io.FileNotFoundException;

public class DrawWhiteBackgroundActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView btnDone, btnPickColor;
    public static int currentColor = 0xfff7a72e;
    private RadioGroup radioGroupSize;
    public static int currentSize = 10;
    private DrawingView drawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_white_background);
        setupUI();
        addDrawingView();
        addListioner();
    }

    private void addDrawingView() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rl_drawing);

        drawingView = new DrawingView(this);
        drawingView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        relativeLayout.addView(drawingView);
    }

    public void addListioner(){
        btnPickColor.setOnClickListener(this);
        btnDone.setOnClickListener(this);

        radioGroupSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.rb_thin: {
                        currentSize = 5;
                        break;
                    }
                    case R.id.rb_medium: {
                        currentSize = 10;
                        break;
                    }
                    case R.id.rb_strong: {
                        currentSize = 15;
                        break;
                    }
                }
                Log.d("size", currentSize + "");
            }
        });

    }

    public void setupUI(){
        btnPickColor = (ImageView) findViewById(R.id.btnPickColor);
        btnDone = (ImageView) findViewById(R.id.btnDone);
        btnPickColor.setColorFilter(currentColor);

        radioGroupSize = (RadioGroup) findViewById(R.id.rgSize);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPickColor: {
            pickColor();
            break;
        }
        case R.id.btnDone: {
            saveImage();
            break;
        }
    }
    }

    private void saveImage() {
        drawingView.setDrawingCacheEnabled(true);
        drawingView.buildDrawingCache();
        Bitmap bitmap = drawingView.getDrawingCache();
        Log.e("bitmap", bitmap.toString());

        try {
            ImageUtils.saveImage(bitmap, this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void pickColor() {
        ColorPickerDialogBuilder
                .with(this)
                .setTitle("Choose your color")
                .initialColor(currentColor)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setPositiveButton("Ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, Integer[] integers) {
                        btnPickColor.setColorFilter(i);
                        currentColor = i;
                        Log.e("color",  i + "");
                    }
                })
                .lightnessSliderOnly()
                .build()
                .show();
    }
}
