package com.example.projectdemo04.views;

import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.model.Token;

import java.util.List;

public interface BookView {
    void getSuccess(List<Book> book);
    void getFailed(String s);
}
