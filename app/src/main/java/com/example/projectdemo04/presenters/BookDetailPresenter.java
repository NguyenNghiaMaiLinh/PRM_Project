package com.example.projectdemo04.presenters;

import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.repositories.FBookRepository;
import com.example.projectdemo04.repositories.FBookRepositoryImp;
import com.example.projectdemo04.utils.CallBackData;
import com.example.projectdemo04.views.BookDetailView;
import com.example.projectdemo04.views.BookView;

import java.util.List;

public class BookDetailPresenter {
    private BookDetailView bookView;
    private FBookRepository repo;


    public BookDetailPresenter(BookDetailView bookView) {
        this.bookView = bookView;
        repo = new FBookRepositoryImp() {
        };
    }

    public void getBookById(String token, long id) {
        repo.getBookById(token, id, new CallBackData<Book>() {

            @Override
            public void onSuccess(Book book) {
                bookView.getSuccess(book);
            }

            @Override
            public void onFail(String message) {
                bookView.getFailed(message);
            }
        });
    }
}
