package com.example.projectdemo04.presenters;

import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.repositories.FBookRepositoryImp;
import com.example.projectdemo04.views.BookView;
import com.example.projectdemo04.repositories.FBookRepository;
import com.example.projectdemo04.utils.CallBackData;

import java.util.List;

public class BooksPresenter {
    private BookView bookView;
    private FBookRepository repo;

    public BooksPresenter(BookView bookView) {
        this.bookView = bookView;
        repo = new FBookRepositoryImp() {
        };
    }

    public void getTopDiscount() {
        repo.getBooksByCategory("discount",0, new CallBackData<List<Book>>() {
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
