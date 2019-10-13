package com.example.projectdemo04.views;

import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.model.Cart;
import com.example.projectdemo04.model.CartBook;

import java.util.List;

public interface CartView {
    void getCartSuccess(List<CartBook> cart);
    void getCartFailed(String s);
}
