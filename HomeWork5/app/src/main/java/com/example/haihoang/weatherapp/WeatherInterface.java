package com.example.haihoang.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by haihoang on 11/17/17.
 */

public interface WeatherInterface {
    @GET("weather")
    Call<WeatherTypeResponseJSON> getWeather(
            @Query("q") String city,
            @Query("APPID") String Key
    );
}
