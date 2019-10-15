package com.example.projectdemo04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.model.CartBook;
import com.example.projectdemo04.model.BookOrders;
import com.example.projectdemo04.model.Order;
import com.example.projectdemo04.model.User;
import com.example.projectdemo04.presenters.BillPresenter;
import com.example.projectdemo04.presenters.BookPresenter;
import com.example.projectdemo04.presenters.CartBookPresenter;
import com.example.projectdemo04.presenters.CartPresenter;
import com.example.projectdemo04.repositories.FCartRepositoryImp;
import com.example.projectdemo04.utils.CallBackData;
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
    CartAdapter adapter;
    FCartRepositoryImp repoCart;
    List<CartBook> listProduct;
    RecyclerView myCartRecyclerView;
    TextView totalPrice;
    int total = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        button = findViewById(R.id.btnPayment);
        totalPrice = findViewById(R.id.total);
        repoCart = new FCartRepositoryImp(this);

        listProduct = new ArrayList<>();

        myCartRecyclerView = findViewById(R.id.listCart);
        adapter =new CartAdapter(listProduct,this);
        myCartRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        myCartRecyclerView.setAdapter(adapter);
        initView();




    }

    public void onPayment(View view) {
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra("total",total);
        startActivity(intent);

    }



    @Override
    public void getSuccess(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getFailed(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    private void initView(){

        repoCart.getAllInCart(new CallBackData<List<CartBook>>() {
            @Override
            public void onSuccess(List<CartBook> cartBooks) {
                listProduct.addAll(cartBooks);
                adapter.notifyDataSetChanged();
                total = 0;
                for(CartBook book : listProduct){
                    total += book.getBook().getPrice()*book.getQuantity();
                }
                String totalConvert = String.valueOf(total);
                totalPrice.setText(totalConvert);
            }

            @Override
            public void onFail(String message) {
                System.out.println("null");
            }
        });

    }

    @Override
    public void getCartBookSuccess(List<CartBook> cartBook) {

    }

    @Override
    public void getCartBookFailed(String s) {

    }
}
