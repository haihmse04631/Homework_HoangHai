package com.example.haihm.drawbackground;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

public class MainActivity extends AppCompatActivity{

    FloatingActionButton btnFloatingAction;
    private SubActionButton btCameraNote;
    private SubActionButton btUpload;
    private SubActionButton btWhiteNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnFloatingAction = (FloatingActionButton) findViewById(R.id.floatingAction);

        SetupUI();
        addListioner();




    }

    private void addListioner() {
        btCameraNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                startActivity(intent);
            }
        });
    }

    private void SetupUI() {
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

         btCameraNote = itemBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_camera)).build();
         btUpload = itemBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_arrow_upward)).build();
         btWhiteNote = itemBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_brush)).build();

        FloatingActionMenu floatingActionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(btWhiteNote)
                .addSubActionView(btCameraNote)
                .addSubActionView(btUpload)
                .attachTo(btnFloatingAction)
                .build();


    }

}
