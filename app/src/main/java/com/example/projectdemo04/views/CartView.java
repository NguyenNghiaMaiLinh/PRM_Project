package com.example.projectdemo04.views;

import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.model.Cart;

import java.util.List;

public interface CartView {
    void getSuccess(Cart cart);
    void getFailed(String s);
}
