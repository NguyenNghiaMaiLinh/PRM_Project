package com.example.projectdemo04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectdemo04.model.Bill;
import com.example.projectdemo04.model.MakePaymentRequest;
import com.example.projectdemo04.model.User;
import com.example.projectdemo04.repositories.FAccountRepository;
import com.example.projectdemo04.repositories.FAccountRepositoryImp;
import com.example.projectdemo04.repositories.FCartRepository;
import com.example.projectdemo04.repositories.FCartRepositoryImp;
import com.example.projectdemo04.utils.CallBackData;

import java.util.ArrayList;
import java.util.List;

public class ConfirmPaymentActivity extends AppCompatActivity {
    ListView listView;
    String selectedAddress;
    List<String> listAddress = new ArrayList<>();
    AddressListViewAdapter addressListViewAdapter;
    TextView currentAddressCheckBox;
    TextView btnAddAddress;
    EditText txtNewAddress;
    FAccountRepository fAccountRepository;
    FCartRepository fCartRepository;
    String total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_payment);
        listView = findViewById(R.id.listViewAddress);
        btnAddAddress = findViewById(R.id.buttonAddAddress);
        txtNewAddress = findViewById(R.id.txtNewAddress);
        fAccountRepository = new FAccountRepositoryImp(this);
        fCartRepository = new FCartRepositoryImp(this);
        initView();



    }
    private void initView(){
        total = getIntent().getStringExtra("total");
        TextView textView = findViewById(R.id.txtTotal);
        textView.setText(total);
        UserInfo userInfo = (UserInfo) getApplication();
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
    public void onClickAddMoreAddress(View view){
        if(txtNewAddress.getVisibility() == View.GONE){
            txtNewAddress.setVisibility(View.VISIBLE);
            btnAddAddress.setText("Lưu");
        }
        else {
            listAddress.add(txtNewAddress.getText().toString());
            addressListViewAdapter.notifyDataSetChanged();
            UserInfo userInfo = (UserInfo) getApplication();
            userInfo.setListAddress(listAddress);
            txtNewAddress.setVisibility(View.GONE);
            User user = new User();
            user.setListAddress(listAddress);
            fAccountRepository.updateProfile(user, new CallBackData<User>() {
                @Override
                public void onSuccess(User user) {
                }

                @Override
                public void onFail(String message) {

                }
            });
            btnAddAddress.setText("Thêm địa chỉ");
        }
    }
    public void onClickMakePayment(View view){
        if(selectedAddress == null){
            Toast.makeText(this, "Làm ơn chọn 1 địa chỉ", Toast.LENGTH_SHORT).show();
        }
        else {
            fCartRepository.payment(new MakePaymentRequest(selectedAddress), new CallBackData<Bill>() {
                @Override
                public void onSuccess(Bill bill) {
                    Intent intent = new Intent(ConfirmPaymentActivity.this, SuccessPaymentActivity.class);
                    intent.putExtra("total",total);
                    intent.putExtra("bill", bill);
                    startActivity(intent);
                }

                @Override
                public void onFail(String message) {

                }
            });

        }
    }
}
