package com.example.haihm.weatherapprecycleview.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by haihm on 11/20/2017.
 */

public interface WeatherInterface {
    @GET("daily")
    Call<WeatherResponseJSON> getWeather(
            @Query("q") String cityName,
            @Query("appid") String key
    );
}
