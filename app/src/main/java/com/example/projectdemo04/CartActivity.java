package com.example.projectdemo04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectdemo04.model.CartBook;
import com.example.projectdemo04.presenters.BillPresenter;
import com.example.projectdemo04.presenters.CartBookPresenter;
import com.example.projectdemo04.repositories.FCartRepositoryImp;
import com.example.projectdemo04.utils.CallBackData;
import com.example.projectdemo04.views.BillView;
import com.example.projectdemo04.views.CartBookView;

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
    TextView txtTotalPrice;
    int total = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        button = findViewById(R.id.btnPayment);
        txtTotalPrice = findViewById(R.id.total);
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
                for(CartBook book : cartBooks){
                    total += book.getBook().getPrice()*book.getQuantity();
                }
                String totalConvert = String.valueOf(total);
                txtTotalPrice.setText(totalConvert);
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

    public void onAddingItem(View view){
        ViewGroup row = (ViewGroup) view.getParent();
        TextView txtQuantity = row.findViewById(R.id.cart_item_quantity);

        int quan = Integer.parseInt(txtQuantity.getText().toString());
        quan++;
        txtQuantity.setText(quan+"");
        total= 0;
        for(CartBook cartBook: listProduct){
            if(cartBook.getId() == (long) view.getTag()){
                cartBook.setQuantity(quan);
                break;
            }
        }
        for(CartBook cartBook: listProduct){
            total += cartBook.getBook().getPrice()*cartBook.getQuantity();
        }
        String totalConvert = String.valueOf(total);
        txtTotalPrice.setText(totalConvert);
        adapter.notifyDataSetChanged();

        repoCart.editCart((long)view.getTag(), quan, new CallBackData<List<CartBook>>() {
            @Override
            public void onSuccess(List<CartBook> cartBooks) {

            }

            @Override
            public void onFail(String message) {

            }
        });
    }

    public void onRemoveItem(View view){
        ViewGroup row = (ViewGroup) view.getParent();
        TextView txtQuantity = row.findViewById(R.id.cart_item_quantity);
        int quan = Integer.parseInt(txtQuantity.getText().toString());
        quan--;
        txtQuantity.setText(quan+"");
        total= 0;
        for(CartBook cartBook: listProduct){
            if(cartBook.getId() == (long) view.getTag()){
                cartBook.setQuantity(quan);
                break;
            }
        }
        for(CartBook cartBook: listProduct){

            total += cartBook.getBook().getPrice()*cartBook.getQuantity();
        }
        String totalConvert = String.valueOf(total);
        txtTotalPrice.setText(totalConvert);
        adapter.notifyDataSetChanged();

        repoCart.editCart((long)view.getTag(), quan, new CallBackData<List<CartBook>>() {
            @Override
            public void onSuccess(List<CartBook> cartBooks) {

            }

            @Override
            public void onFail(String message) {

            }
        });
    }

    public void onDeleteBook(View view){
        total= 0;
        for(CartBook cartBook: listProduct){
            if(cartBook.getId() == (long) view.getTag()){
                listProduct.remove(cartBook);
                break;
            }
        }

        for(CartBook cartBook:listProduct){
            total += cartBook.getBook().getPrice()*cartBook.getQuantity();
        }
        String totalConvert = String.valueOf(total);
        txtTotalPrice.setText(totalConvert);
        adapter.notifyDataSetChanged();
        repoCart.delete((long) view.getTag(), new CallBackData<List<CartBook>>() {

            @Override
            public void onSuccess(List<CartBook> cartBooks) {

            }

            @Override
            public void onFail(String message) {

            }
        });
    }
}
