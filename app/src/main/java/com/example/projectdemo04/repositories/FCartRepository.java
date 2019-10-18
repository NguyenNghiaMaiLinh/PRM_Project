package com.example.projectdemo04.repositories;

import com.example.projectdemo04.model.Bill;
import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.model.Cart;
import com.example.projectdemo04.model.CartBook;
import com.example.projectdemo04.model.BookOrders;
import com.example.projectdemo04.model.MakePaymentRequest;
import com.example.projectdemo04.model.Order;
import com.example.projectdemo04.utils.CallBackData;

import java.util.List;

public interface FCartRepository {

    void addToCart( long id, int quantity, CallBackData<List<CartBook>> data);
    void getAllInCart( CallBackData<List<CartBook>> data);
    void payment(MakePaymentRequest request, CallBackData<Bill> data);
    void editCart(long id, int quantity, CallBackData<List<CartBook>> data);
    void delete(long id, CallBackData<List<CartBook>> data);

}
