package com.example.projectdemo04;

import android.app.Application;

import com.example.projectdemo04.model.CartBook;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserInfo extends Application {
    private List<String> listAddress;
    private String email;
    private String fullname;
    private String phone;
    private String username;
    private List<CartBook>  listCart;

    public List<CartBook> getListCart() {
        return listCart;
    }

    public void setListCart(List<CartBook> listCart) {
        this.listCart = listCart;
    }

    public UserInfo(List<String> listAddress, String email, String fullname, String phone, String username) {
        this.listAddress = listAddress;
        this.email = email;
        this.fullname = fullname;
        this.phone = phone;
        this.username = username;
    }

    public UserInfo() {
    }

    public List<String> getListAddress() {
        return listAddress;
    }

    public void setListAddress(List<String> listAddress) {
        this.listAddress = listAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
