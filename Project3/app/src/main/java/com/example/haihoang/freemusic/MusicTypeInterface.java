package com.example.haihoang.freemusic;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by haihoang on 11/15/17.
 */

public interface MusicTypeInterface {
    @GET("api")
    Call<MusicTypeResponseJSON> getMusicType();

}
