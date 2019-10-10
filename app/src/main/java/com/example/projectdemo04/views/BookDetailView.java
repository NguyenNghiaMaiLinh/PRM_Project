package com.example.projectdemo04.views;

import com.example.projectdemo04.model.Book;

import java.util.List;

public interface BookDetailView {
    void getSuccess(Book book);
    void getFailed(String s);
}
