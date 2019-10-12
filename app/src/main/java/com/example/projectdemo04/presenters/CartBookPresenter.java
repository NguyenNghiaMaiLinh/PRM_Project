package com.example.projectdemo04.presenters;

import com.example.projectdemo04.model.Cart;
import com.example.projectdemo04.model.CartBook;
import com.example.projectdemo04.repositories.FCartRepository;
import com.example.projectdemo04.repositories.FCartRepositoryImp;
import com.example.projectdemo04.utils.CallBackData;
import com.example.projectdemo04.views.CartBookView;
import com.example.projectdemo04.views.CartView;

import java.util.List;

public class CartBookPresenter {
    private CartBookView cartBookView;
    private FCartRepository repo;

    public CartBookPresenter(CartBookView cartBookView) {
        this.cartBookView = cartBookView;
        repo = new FCartRepositoryImp() {
        };
    }

    public void getAllInCart(String token) {
        repo.getAllInCart(token, new CallBackData<List<CartBook>>() {

            @Override
            public void onSuccess(List<CartBook> cartBooks) {
                cartBookView.getSuccess(cartBooks);
            }

            @Override
            public void onFail(String message) {
                cartBookView.getFailed(message);
            }
        });
    }
}