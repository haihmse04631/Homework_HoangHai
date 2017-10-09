package com.example.haihoang.icenote;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNote extends AppCompatActivity {

    EditText edtTitle;
    EditText edtContent;
    FloatingActionButton btnAddNote;
    String title, content, time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar);

        edtTitle = (EditText) findViewById(R.id.edtTitle);
        edtContent = (EditText) findViewById(R.id.edtContent);
        btnAddNote = (FloatingActionButton) findViewById(R.id.btnAddNote);

        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                time = sdf.format(new Date());

                title = edtTitle.getText().toString();
                content = edtContent.getText().toString();
                DatabaseHandle.getInstance(AddNote.this).addListNote(title, content, time, 0);
                Intent intent = new Intent(AddNote.this, IceNote.class);
                startActivity(intent);
            }
        });


    }
}
