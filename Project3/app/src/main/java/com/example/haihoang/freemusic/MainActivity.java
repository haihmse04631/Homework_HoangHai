package com.example.haihoang.freemusic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MusicTypeInterface musicTypeInterface =
                RetrofitInstance.getInstance().create(MusicTypeInterface.class);

        musicTypeInterface.getMusicType().enqueue(new Callback<MusicTypeResponseJSON>() {
            @Override
            public void onResponse(Call<MusicTypeResponseJSON> call, Response<MusicTypeResponseJSON> response) {
                List<MusicTypeResponseJSON.SubObjectJSON> list = response.body().subgenres;

                for(int i=0; i<list.size(); i++){
                    Log.e("Response", list.get(i).translation_key);
                }
            }

            @Override
            public void onFailure(Call<MusicTypeResponseJSON> call, Throwable t) {

            }
        });
    }
}
