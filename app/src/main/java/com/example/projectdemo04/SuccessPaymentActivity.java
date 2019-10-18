package com.example.projectdemo04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.projectdemo04.model.Bill;
import com.example.projectdemo04.model.CartBook;
import com.example.projectdemo04.model.Order;
import com.example.projectdemo04.model.User;
import com.example.projectdemo04.repositories.FCartRepositoryImp;
import com.example.projectdemo04.utils.CallBackData;

import java.util.List;

public class SuccessPaymentActivity extends AppCompatActivity {

    TextView txtAmount, txtDate, txtCustomer,txtBillID,txtDeliveryAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        txtAmount = findViewById(R.id.txtAmount);
        txtDate = findViewById(R.id.txtDate);
        txtCustomer = findViewById(R.id.txtCustomer);
        txtBillID = findViewById(R.id.txtBillId);
        txtDeliveryAddress = findViewById(R.id.txtDeliveryAddress);
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
        Bill bill =(Bill) getIntent().getSerializableExtra("bill");
        User user = bill.getUser();
        txtDate.setText(bill.getDateCreated());
        String username = user.getFullname() != null && user.getFullname().isEmpty() ? user.getFullname() : user.getUsername();
        txtCustomer.setText(username);
        txtDeliveryAddress.setText(bill.getDeliveryAddress());
        txtAmount.setText(getIntent().getStringExtra("total"));
        txtBillID.setText(bill.getId()+"");

    }

}
