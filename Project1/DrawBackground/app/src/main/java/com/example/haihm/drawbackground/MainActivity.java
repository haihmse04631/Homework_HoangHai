package com.example.haihm.drawbackground;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    FloatingActionButton btnFloatingAction;
    private SubActionButton btCameraNote;
    private SubActionButton btUpload;
    private SubActionButton btWhiteNote;
    private FloatingActionMenu floatingActionMenu;
    private GridView gvImage;
    File imageFolder;

    public static String MODE_CAMERA = "mode_camera";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnFloatingAction = (FloatingActionButton) findViewById(R.id.floatingAction);
        initPermission();
        SetupUI();


        addListioner();
    }


    private void initPermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1){

        }
    }


    private void addListioner() {
        btCameraNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DrawWhiteBackgroundActivity.class);
                intent.putExtra(MODE_CAMERA, true);
                startActivity(intent);
                floatingActionMenu.close(false);
                Toast.makeText(MainActivity.this, "Log camera", Toast.LENGTH_SHORT).show();
            }
        });

        btUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Log Upload", Toast.LENGTH_SHORT).show();
            }
        });

        btWhiteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DrawWhiteBackgroundActivity.class);
                intent.putExtra(MODE_CAMERA, false);
                startActivity(intent);
                floatingActionMenu.close(false);
            }
        });
    }

    private void SetupUI() {
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

         btCameraNote = itemBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_camera)).build();
         btUpload = itemBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_arrow_upward)).build();
         btWhiteNote = itemBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_brush)).build();
         gvImage = (GridView) findViewById(R.id.gvImage);


         floatingActionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(btWhiteNote)
                .addSubActionView(btCameraNote)
                .addSubActionView(btUpload)
                .attachTo(btnFloatingAction)
                .build();


    }

    private List<String> getListImagePaths(){
        List<String> imagePaths = new ArrayList<>();

        imageFolder = new File(Environment.getExternalStorageDirectory().toString() + "/DrawingNotes");
        File[] listFile = imageFolder.listFiles();
        if (listFile != null){
            for(int i=0; i<listFile.length; i++){
                String filePaths = listFile[i].getAbsolutePath();
                imagePaths.add(filePaths);

            }
        }

        return imagePaths;
    }

    @Override
    protected void onStart() {
        super.onStart();
        ImageAdapter imageAdapter = new ImageAdapter(getListImagePaths(), this);
        gvImage.setAdapter(imageAdapter);
    }
}
