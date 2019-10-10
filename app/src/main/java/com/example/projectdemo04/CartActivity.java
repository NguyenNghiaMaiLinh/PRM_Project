package com.example.projectdemo04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.model.CartBook;
import com.example.projectdemo04.model.BookOrders;
import com.example.projectdemo04.model.Order;
import com.example.projectdemo04.presenters.BillPresenter;
import com.example.projectdemo04.presenters.BookPresenter;
import com.example.projectdemo04.presenters.CartBookPresenter;
import com.example.projectdemo04.presenters.CartPresenter;
import com.example.projectdemo04.views.BillView;
import com.example.projectdemo04.views.BookView;
import com.example.projectdemo04.views.CartBookView;
import com.example.projectdemo04.views.CartView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements CartBookView , BillView {
    private CartBookPresenter cartBookPresenter;
    private BillPresenter billPresenter;
    String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU3MDIwNjg4N30.kpGegav6pUTZR46v1NjNuEL14UUhEMzJdTgxnQvVHC3cmtGjZMHR61bCHjQX0TJgntk_1IH6i4JaczYDks8Bgw";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        CartFragment cartFragment = new CartFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, cartFragment).commit();

        cartBookPresenter = new CartBookPresenter(this);
        cartBookPresenter.getAllInCart(token);
        List<Order> list = new ArrayList<>();
        Order item = new Order(1,1);
        Order item1 = new Order(2,1);
        list.add(item);
        list.add(item1);
        BookOrders bookOrders = new BookOrders(list);
        billPresenter = new BillPresenter(this);
        billPresenter.payment(token,list);
    }

    @Override
    public void getSuccess(List<CartBook> cartBook) {
        Toast.makeText(getApplicationContext(), cartBook.get(0).getBook().getProductName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getSuccess(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getFailed(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}
