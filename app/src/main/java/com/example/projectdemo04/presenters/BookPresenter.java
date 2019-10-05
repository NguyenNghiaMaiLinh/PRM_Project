package com.example.projectdemo04.presenters;

import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.repositories.FBookRepositoryImp;
import com.example.projectdemo04.views.BookView;
import com.example.projectdemo04.model.Token;
import com.example.projectdemo04.repositories.FAccountRepository;
import com.example.projectdemo04.repositories.FAccountRepositoryImp;
import com.example.projectdemo04.repositories.FBookRepository;
import com.example.projectdemo04.utils.CallBackData;
import com.example.projectdemo04.views.LoginView;

import java.util.List;

public class BookPresenter {
    private BookView bookView;
    private FBookRepository repo;

    public BookPresenter(BookView bookView) {
        this.bookView = bookView;
        repo = new FBookRepositoryImp() {
        };
    }

    public void getTruyen(String token) {
        repo.getTruyen(token, new CallBackData<List<Book>>() {
            @Override
            public void onSuccess(List<Book> book) {
                bookView.getSuccess(book);
            }

            @Override
            public void onFail(String message) {
                bookView.getFailed(message);
            }
        });
    }
}
