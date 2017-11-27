package com.example.haihoang.freemusic.network;

import android.media.Image;

import com.google.gson.annotations.SerializedName;

import java.security.PublicKey;
import java.util.List;

/**
 * Created by haihm on 11/27/2017.
 */

public class TopSongResponseJSON {
    public FeedJSON feed;

    public class FeedJSON{
        public List<EntryJSON> entry;

        public class EntryJSON{
            @SerializedName("im:name")
            public NameJSON name;
            public class NameJSON{
                public String label;
            }

            @SerializedName("im:image")
            public List<ImageJSON> image;
            public class ImageJSON{
                public String label;
            }

            @SerializedName("im:artist")
            public ArtistJSON artist;
            public class ArtistJSON{
                public String label;
            }
        }

    }

}
