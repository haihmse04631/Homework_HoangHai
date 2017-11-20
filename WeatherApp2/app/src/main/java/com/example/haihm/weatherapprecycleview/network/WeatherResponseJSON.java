package com.example.haihm.weatherapprecycleview.network;

import java.util.List;

/**
 * Created by haihm on 11/20/2017.
 */

public class WeatherResponseJSON {
    public List<SubObjectJSON> list;

    public class SubObjectJSON{
        public float pressure;
        public List<WeatherInfor> weather;

        public class WeatherInfor{
            public String main;
            public String description;
        }

    }
}
