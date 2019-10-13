package com.example.projectdemo04.views;

import com.example.projectdemo04.model.Cart;
import com.example.projectdemo04.model.CartBook;

import java.util.List;

public interface CartBookView {
    void getCartBookSuccess(List<CartBook> cartBook);

    void getCartBookFailed(String s);
}
