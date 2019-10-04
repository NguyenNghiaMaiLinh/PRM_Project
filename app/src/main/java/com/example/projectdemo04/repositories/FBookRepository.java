package com.example.projectdemo04.repositories;

import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.utils.CallBackData;

import java.util.List;

public interface FBookRepository {
    void getAllBooks(CallBackData<List<Book>> data);
}
