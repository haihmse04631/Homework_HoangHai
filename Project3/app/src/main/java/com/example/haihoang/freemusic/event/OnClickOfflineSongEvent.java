package com.example.haihoang.freemusic.event;

import com.example.haihoang.freemusic.database.OfflineSongModel;

/**
 * Created by haihm on 12/11/2017.
 */

public class OnClickOfflineSongEvent {
    public OfflineSongModel offlineSongModel;

    public OnClickOfflineSongEvent(OfflineSongModel offlineSongModel) {
        this.offlineSongModel = offlineSongModel;
    }
}
