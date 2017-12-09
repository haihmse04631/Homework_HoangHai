package com.example.haihoang.freemusic.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by haihoang on 11/15/17.
 */

public interface MusicInterface {
    @GET("api")
    Call<MusicTypeResponseJSON> getMusicType();

    @GET("https://itunes.apple.com/us/rss/topsongs/limit=50/genre={id}/explicit=true/json")
    Call<TopSongResponseJSON> getTopSongs(@Path("id") String id);

    @GET("https://tk-gx.herokuapp.com/api/audio")
    Call<MusicResponseJSON> getSearchSong(@Query("search_terms") String search);

}