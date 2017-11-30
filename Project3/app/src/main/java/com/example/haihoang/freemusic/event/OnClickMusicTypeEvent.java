package com.example.haihoang.freemusic.event;

import com.example.haihoang.freemusic.database.MusicTypeModel;
import com.example.haihoang.freemusic.database.TopSongModel;

/**
 * Created by haihm on 11/22/2017.
 */

public class OnClickMusicTypeEvent {
    public MusicTypeModel musicTypeModel;

    public OnClickMusicTypeEvent(MusicTypeModel musicTypeModel) {
        this.musicTypeModel = musicTypeModel;
    }
}
