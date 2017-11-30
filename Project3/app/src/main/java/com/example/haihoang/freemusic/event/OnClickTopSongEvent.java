package com.example.haihoang.freemusic.event;

import com.example.haihoang.freemusic.database.TopSongModel;

/**
 * Created by haihm on 11/29/2017.
 */

public class OnClickTopSongEvent {
    public TopSongModel topSongModel;

    public OnClickTopSongEvent(TopSongModel topSongModel) {
        this.topSongModel = topSongModel;
    }
}
