package com.example.haihoang.weatherapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by haihoang on 11/17/17.
 */

public class RetrofitInstance {
    private static Retrofit retrofit;
    public static Retrofit getInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    //.baseUrl("https://api.openweathermap.org/data/2.5/")
                    //.baseUrl("https://samples.openweathermap.org/data/2.5/forecast/daily?q=London&appid=36659f145f4bb9b56ca96bc519225d0e&cnt=16")
                    .baseUrl("https://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
