package com.example.projectdemo04.presenters;

import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.model.Cart;
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

    public CartPresenter(CartView cartView) {
        this.cartView = cartView;
        repo = new FCartRepositoryImp() {
        };
    }

    public void portAddToCart(String token, long id, int quantity) {
        repo.addToCart(token,id, quantity, new CallBackData<Cart>() {

            @Override
            public void onSuccess(Cart cart) {
                cartView.getSuccess(cart);
            }

            @Override
            public void onFail(String message) {
                cartView.getFailed(message);
            }
        });
    }


}
