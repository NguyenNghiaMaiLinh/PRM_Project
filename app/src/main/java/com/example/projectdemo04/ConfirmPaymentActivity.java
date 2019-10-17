package com.example.projectdemo04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ConfirmPaymentActivity extends AppCompatActivity {
    ListView listView;
    String selectedAddress;
    List<String> listAddress = new ArrayList<>();
    AddressListViewAdapter addressListViewAdapter;
    TextView currentAddressCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_payment);
        listView = findViewById(R.id.listAddress);
        initView();



    }
    private void initView(){
        int total = getIntent().getIntExtra("total",0);
        TextView textView = findViewById(R.id.txtTotal);
        textView.setText(total+"đ");
        UserInfo userInfo = (UserInfo) getApplication();
        listAddress = new ArrayList<>();
        listAddress.addAll(userInfo.getListAddress());
        addressListViewAdapter = new AddressListViewAdapter(listAddress);
        listView.setAdapter(addressListViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if(currentAddressCheckBox != null)
                        currentAddressCheckBox.setBackgroundResource(R.drawable.unselected_bg);
                selectedAddress =(String) adapterView.getItemAtPosition(position);
                TextView checkBox = view.findViewById(R.id.addressCheckbox);
                checkBox.setBackgroundResource(R.drawable.selected_bg);
                currentAddressCheckBox = checkBox;

            }
        });


    }
}
