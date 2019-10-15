package com.example.projectdemo04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.projectdemo04.model.Bill;
import com.example.projectdemo04.model.CartBook;
import com.example.projectdemo04.model.Order;
import com.example.projectdemo04.repositories.FCartRepositoryImp;
import com.example.projectdemo04.utils.CallBackData;

import java.util.List;

public class PaymentActivity extends AppCompatActivity {

    FCartRepositoryImp repoCart;
    Bill payBill;
    TextView txtAmount, txtDate, txtCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        repoCart = new FCartRepositoryImp(this);
        txtAmount = findViewById(R.id.txtAmount);
        txtDate = findViewById(R.id.txtDate);
        txtCustomer = findViewById(R.id.txtCustomer);

    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
    }

    public void backToHome(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private void initView(){
        txtAmount.setText(getIntent().getIntExtra("total",0)+"");
        repoCart.payment(new CallBackData<Bill>() {

            @Override
            public void onSuccess(Bill bill) {

                txtCustomer.setText(bill.getUser().getFullname());
                txtDate.setText(bill.getDateCreated());
            }

            @Override
            public void onFail(String message) {

            }
        });


    }
}
