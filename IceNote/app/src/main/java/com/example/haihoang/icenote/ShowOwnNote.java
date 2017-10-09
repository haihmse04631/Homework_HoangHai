package com.example.haihoang.icenote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowOwnNote extends AppCompatActivity {

    Bundle bundle;
    Intent intent;
    TextView tvTitle, tvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_own_note);

        tvTitle = (TextView) findViewById(R.id.txtShowTitle);
        tvContent = (TextView) findViewById(R.id.txtShowContent);

        intent = getIntent();
        bundle = intent.getBundleExtra("myNote");
        tvTitle.setText(bundle.getString("title"));
        tvContent.setText(bundle.getString("content"));

    }
}
