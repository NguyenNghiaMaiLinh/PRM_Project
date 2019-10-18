package com.example.projectdemo04.presenters;

import android.content.Context;

import com.example.projectdemo04.model.Bill;
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
    Context context;
    String token;


    public BillPresenter(BillView billView, Context context) {
        this.billView = billView;
        this.context = context;
        repo = new FCartRepositoryImp(context) {
        };
    }


}
