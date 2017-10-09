package com.example.haihoang.project2_readbook;

/**
 * Created by haihoang on 10/7/17.
 */

public class StoryModel {
    private String image;
    private String title;
    private String description, content, author;
    private boolean bookmark;

    public StoryModel(String image, String title, String description, String content, String author, boolean bookmark) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.content = content;
        this.author = author;
        this.bookmark = bookmark;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String context) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean getBookmark() {
        return bookmark;
    }

    public void setBookmark(boolean bookmark) {
        this.bookmark = bookmark;
    }

    @Override
    public String toString() {
        return "StoryModel{" +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", bookmark=" + bookmark +
                '}';
    }
}
