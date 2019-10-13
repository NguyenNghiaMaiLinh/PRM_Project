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

    public void getAllInCart() {
        repo.getAllInCart( new CallBackData<List<CartBook>>() {

            @Override
            public void onSuccess(List<CartBook> cartBooks) {
                cartBookView.getCartBookSuccess(cartBooks);
            }

            @Override
            public void onFail(String message) {
                cartBookView.getCartBookFailed(message);
            }
        });
    }
}
