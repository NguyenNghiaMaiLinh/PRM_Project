package com.example.projectdemo04.presenters;

import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.model.BookOrders;
import com.example.projectdemo04.model.Order;
import com.example.projectdemo04.repositories.FBookRepository;
import com.example.projectdemo04.repositories.FBookRepositoryImp;
import com.example.projectdemo04.repositories.FCartRepository;
import com.example.projectdemo04.repositories.FCartRepositoryImp;
import com.example.projectdemo04.utils.CallBackData;
import com.example.projectdemo04.views.BillView;
import com.example.projectdemo04.views.BookDetailView;

import java.util.List;

public class BillPresenter {
    private BillView billView;
    private FCartRepository repo;


    public BillPresenter(BillView billView) {
        this.billView = billView;
        repo = new FCartRepositoryImp() {
        };
    }

    public void payment(String token, List<Order> list) {
        repo.payment(token, list, new CallBackData<String>() {

            @Override
            public void onSuccess(String book) {
                billView.getSuccess(book);
            }

            @Override
            public void onFail(String message) {
                billView.getFailed(message);
            }
        });
    }
}
