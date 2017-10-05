package com.example.haihm.drawbackground;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

        if(!getIntent().getBooleanExtra(MainActivity.MODE_CAMERA, false)){
            addDrawingView(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, null);
        }else{
            openCamera();
        }

        addListioner();
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        Uri uri = ImageUtils.getUriFromImage(this);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, 1);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("onActivityResul", "I'm Here!");
        if(requestCode == 1){
            Log.e("onActivityResul", "I'm get Request code == 1");
            if(resultCode == RESULT_OK)
            {
                Bitmap bitmap = ImageUtils.getBitmap(this);
                addDrawingView(bitmap.getWidth(), bitmap.getHeight(), bitmap);
            }

        }

    }

    private void addDrawingView(int with, int height, Bitmap bitmap) {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rl_drawing);

        drawingView = new DrawingView(this, bitmap);
        drawingView.setLayoutParams(new ViewGroup.LayoutParams(with, height));
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
            btnDone.setClickable(false);
            this.finish();
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
