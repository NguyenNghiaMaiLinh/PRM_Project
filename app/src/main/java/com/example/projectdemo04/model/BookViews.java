package com.example.projectdemo04.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BookViews{

    String title;
    int imageID;
    long price;

    public BookViews(String title, int imageID, long price) {
        this.title = title;
        this.imageID = imageID;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
