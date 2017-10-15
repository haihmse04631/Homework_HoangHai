package com.example.haihoang.icenote;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowOwnNote extends AppCompatActivity {

    Bundle bundle;
    Intent intent;
    TextView tvTitle, tvContent, tvCustomTitle;
    ImageView ivDone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_own_note);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar_show_note);

        tvTitle = (TextView) findViewById(R.id.txtShowTitle);
        tvContent = (TextView) findViewById(R.id.edtShowContent);
        tvCustomTitle = (TextView) findViewById(R.id.txtTitle);
        ivDone = (ImageView) findViewById(R.id.ivDone);

        intent = getIntent();
        bundle = intent.getBundleExtra("myNote");
        tvCustomTitle.setText(bundle.getString("title"));
        if(bundle.getBoolean("status")){
            ivDone.setVisibility(View.VISIBLE);
            tvTitle.setText(bundle.getString("title"));
            tvContent.setText(bundle.getString("content"));
            tvContent.setEnabled(true);
            ivDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String content = tvContent.getText().toString();
                    DatabaseHandle.getInstance(ShowOwnNote.this).updateContent(bundle.getString("id"), content, bundle.getInt("position"));
                    finish();
                }
            });
        }else{
            tvContent.setEnabled(false);
            tvTitle.setText(bundle.getString("title"));
            tvContent.setText(bundle.getString("content"));
            ivDone.setVisibility(View.GONE);
        }

    }
}
