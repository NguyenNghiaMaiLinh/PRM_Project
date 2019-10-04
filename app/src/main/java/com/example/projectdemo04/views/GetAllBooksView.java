package com.example.projectdemo04.views;

import com.example.projectdemo04.model.Book;

import java.util.List;

public interface GetAllBooksView {
    void getSuccess(List<Book> list);
    void getFail(String message);
}
