package com.example.projectdemo04.views;

import com.example.projectdemo04.model.Cart;
import com.example.projectdemo04.model.CartBook;

import java.util.List;

public interface CartBookView {
    void getSuccess(List<CartBook> cartBook);

    void getFailed(String s);
}
