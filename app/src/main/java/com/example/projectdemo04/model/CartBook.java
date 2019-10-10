package com.example.projectdemo04.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CartBook implements Serializable {
    @SerializedName("id")
    private long id;
    @SerializedName("book")
    private Book book;
    @SerializedName("account")
    private Account account;

    public CartBook(long id, Book book, Account account) {
        this.id = id;
        this.book = book;
        this.account = account;
    }

    public CartBook() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
