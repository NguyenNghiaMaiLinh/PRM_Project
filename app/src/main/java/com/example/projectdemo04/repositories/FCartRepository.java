package com.example.projectdemo04.repositories;

import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.model.Cart;
import com.example.projectdemo04.model.CartBook;
import com.example.projectdemo04.model.BookOrders;
import com.example.projectdemo04.model.Order;
import com.example.projectdemo04.utils.CallBackData;

import java.util.List;

public interface FCartRepository {

    void addToCart( long id, int quantity, CallBackData<Cart> data);
    void getAllInCart(String token, CallBackData<List<CartBook>> data);
    void payment(String token, List<Order> bookOrders, CallBackData<String> data);

}
