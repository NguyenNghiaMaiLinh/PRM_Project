package com.example.projectdemo04.repositories;

import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.model.Token;
import com.example.projectdemo04.utils.CallBackData;

import java.util.List;

public interface FBookRepository {

    void getClickedBooks( CallBackData<List<Book>> data);
    void search( String name, CallBackData<List<Book>> data);
    void getBookById(long id, CallBackData<Book> data);
    void postClickedBook(long bookId);
    void getAllBookNames(CallBackData<List<String>> data);
    void getBooksByCategory(String category,int page, CallBackData<List<Book>> data);

}
