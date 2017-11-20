package com.example.haihm.weatherapprecycleview.mode;

/**
 * Created by haihm on 11/20/2017.
 */

public class Weather {
    public float perssure;
    public String description, main;
    public String day;

    public Weather(float perssure, String description, String main, String day) {
        this.perssure = perssure;
        this.description = description;
        this.main = main;
        this.day = day;
    }

    @Override
    public String toString() {
        return perssure + " " +  description;
    }
}
