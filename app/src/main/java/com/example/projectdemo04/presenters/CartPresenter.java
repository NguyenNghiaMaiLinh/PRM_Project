package com.example.projectdemo04.presenters;

import android.content.Context;

import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.model.Cart;
import com.example.projectdemo04.model.CartBook;
import com.example.projectdemo04.repositories.FBookRepository;
import com.example.projectdemo04.repositories.FBookRepositoryImp;
import com.example.projectdemo04.repositories.FCartRepository;
import com.example.projectdemo04.repositories.FCartRepositoryImp;
import com.example.projectdemo04.utils.CallBackData;
import com.example.projectdemo04.views.BookView;
import com.example.projectdemo04.views.CartView;

import java.util.List;

public class CartPresenter {
    private CartView cartView;
    private FCartRepository repo;

    public CartPresenter(CartView cartView, Context context) {
        this.cartView = cartView;
        repo = new FCartRepositoryImp(context) {
        };
    }

    public void postAddToCart(long id, int quantity) {
        repo.addToCart(id, quantity, new CallBackData<List<CartBook>>() {

            @Override
            public void onSuccess(List<CartBook> cart) {
                cartView.getCartSuccess(cart);
            }

            @Override
            public void onFail(String message) {
                cartView.getCartFailed(message);
            }
        });
    }


}
