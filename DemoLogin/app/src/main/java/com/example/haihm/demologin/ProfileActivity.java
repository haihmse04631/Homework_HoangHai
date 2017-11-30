package com.example.haihm.demologin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {
    TextView name;
    ImageView cover, avatar;
    Button btnLogOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        avatar = (ImageView) findViewById(R.id.iv_avatar);
        name = (TextView) findViewById(R.id.tv_name);
        cover = (ImageView) findViewById(R.id.iv_cover);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("myPakage");
        Picasso.with(ProfileActivity.this).load(bundle.getString("cover")).fit().centerCrop().into(cover);
        Picasso.with(ProfileActivity.this).load(bundle.getString("avatar")).into(avatar);
        name.setText(bundle.getString("name"));

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

    }

    private void logout() {
        LoginManager.getInstance().logOut();
        finish();
    }
}
