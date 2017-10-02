package com.example.haihoang.homeword_gridview;

/**
 * Created by haihoang on 10/2/17.
 */

public class CountryFlag {
    private String countryName;
    private int imgCountry;

    public CountryFlag(String countryName, int imgCountry) {
        this.countryName = countryName;
        this.imgCountry = imgCountry;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getImgCountry() {
        return imgCountry;
    }

    public void setImgCountry(int imgCountry) {
        this.imgCountry = imgCountry;
    }
}
