package com.example.projectdemo04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class CartActivity extends AppCompatActivity implements CartBookView, BillView {
    private CartBookPresenter cartBookPresenter;
    private BillPresenter billPresenter;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        button = findViewById(R.id.btnPayment);
        CartFragment cartFragment = new CartFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, cartFragment).commit();
        billPresenter = new BillPresenter(this, this);
        cartBookPresenter = new CartBookPresenter(this);
        cartBookPresenter.getAllInCart();
        List<Order> list = new ArrayList<>();
        Order item = new Order(1, 3);
        Order item1 = new Order(1, 4);
        list.add(item);
        list.add(item1);
        BookOrders bookOrders = new BookOrders(list);

        billPresenter.payment(list);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartBookPresenter.getAllInCart();
                List<Order> list = new ArrayList<>();
                Order item = new Order(1, 1);
                Order item1 = new Order(2, 1);
                list.add(item);
                list.add(item1);
                BookOrders bookOrders = new BookOrders(list);

                billPresenter.payment(list);
            }
        });
    }

    private void onPayment1(View view) {

    }

    @Override
    public void getSuccess(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getFailed(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCartBookSuccess(List<CartBook> cartBook) {
        Toast.makeText(getApplicationContext(), cartBook.get(0).getBook().getProductName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCartBookFailed(String s) {

    }
}
