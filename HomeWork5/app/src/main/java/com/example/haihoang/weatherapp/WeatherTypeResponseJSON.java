package com.example.haihoang.weatherapp;

import java.util.List;

/**
 * Created by haihoang on 11/17/17.
 */

public class WeatherTypeResponseJSON {
    List<SubWeatherObj> weather;

    public class SubWeatherObj{
        String id, main, description;
    }
}
