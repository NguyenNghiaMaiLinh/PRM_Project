package com.example.projectdemo04.repositories;

import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.model.Token;
import com.example.projectdemo04.utils.CallBackData;

import java.util.List;

public interface FBookRepository {

    void getTopDiscount(String token, CallBackData<List<Book>> data);
    void getClickedBooks(String token, CallBackData<List<Book>> data);
    void getTopSales(String token, CallBackData<List<Book>> data);
    void search(String token, String name, CallBackData<List<Book>> data);
    void getTruyenTranh(String token, CallBackData<List<Book>> data);
    void getBookById(String token,long id, CallBackData<Book> data);

}
