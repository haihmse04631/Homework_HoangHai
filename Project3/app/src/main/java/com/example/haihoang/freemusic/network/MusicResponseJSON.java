package com.example.haihoang.freemusic.network;

import retrofit2.http.Url;

/**
 * Created by haihm on 11/29/2017.
 */

public class MusicResponseJSON {
    public DataJSON data;

    public class DataJSON{
        public String url;

        public String thumbnail;
    }
}
