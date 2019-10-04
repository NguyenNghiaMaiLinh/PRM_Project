package com.example.projectdemo04.presenters;

import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.repositories.FBookRepository;
import com.example.projectdemo04.repositories.FBookRepositoryImp;
import com.example.projectdemo04.utils.CallBackData;
import com.example.projectdemo04.views.GetAllBooksView;

import java.util.List;

public class GetAllBooksPresenter {
    GetAllBooksView booksView;
    FBookRepository repo;

    public GetAllBooksPresenter(GetAllBooksView booksView) {
        this.booksView = booksView;
        repo = new FBookRepositoryImp();
    }
    public void getAllBooks(){
        repo.getAllBooks(new CallBackData<List<Book>>() {
            @Override
            public void onSuccess(List<Book> list) {
                booksView.getSuccess(list);
            }

            @Override
            public void onFail(String message) {
                booksView.getFail(message);
            }
        });
    }
}
