package com.example.haihoang.freemusic.network;

import java.util.List;

/**
 * Created by haihoang on 11/15/17.
 */

public class MusicTypeResponseJSON {
    public List<SubObjectJSON> subgenres;

    public class SubObjectJSON {
        public String id, translation_key;

    }
}
