package com.example.haihoang.freemusic;

import java.util.List;

/**
 * Created by haihoang on 11/15/17.
 */

public class MusicTypeResponseJSON {
    List<SubObjectJSON> subgenres;

    public class SubObjectJSON {
        String id, translation_key;

    }
}
