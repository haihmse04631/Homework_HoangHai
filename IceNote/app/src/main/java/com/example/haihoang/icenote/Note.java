package com.example.haihoang.icenote;

/**
 * Created by haihoang on 10/9/17.
 */

public class Note {
    private String title, content, time, id;
    private boolean bookmark;

    public Note(String title, String content, String time, boolean bookmark) {
        this.title = title;
        this.content = content;
        this.time = time;
        this.bookmark = bookmark;
    }

    public Note(String id, String title, String content, String time, boolean bookmark) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.time = time;
        this.bookmark = bookmark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean getBookmark() {
        return bookmark;
    }

    public void setBookmark(boolean bookmark) {
        this.bookmark = bookmark;
    }
}
