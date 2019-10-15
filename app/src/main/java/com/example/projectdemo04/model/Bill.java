package com.example.projectdemo04.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Date;

public class Bill implements Serializable {
    @SerializedName("id")
    private long id;

    @SerializedName("user")
    private User user;

    @SerializedName("dateCreated")
    private String dateCreated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Bill(long id, User user, String dateCreated) {
        this.id = id;
        this.user = user;
        this.dateCreated = dateCreated;
    }

    public Bill() {
    }
}
