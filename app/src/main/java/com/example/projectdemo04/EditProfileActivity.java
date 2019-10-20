package com.example.projectdemo04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectdemo04.model.User;
import com.example.projectdemo04.repositories.FAccountRepository;
import com.example.projectdemo04.repositories.FAccountRepositoryImp;
import com.example.projectdemo04.utils.CallBackData;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;

public class EditProfileActivity extends AppCompatActivity {
    FAccountRepository fAccountRepository;
    EditText txtFullName, txtEmail, txtPhone, txtNewAddress;
    TextView btnAddAddress;
    List<String> listAddress;
    ListViewAdapterEditProfile listAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        fAccountRepository = new FAccountRepositoryImp(this);

        UserInfo userInfo = (UserInfo) getApplication();
        txtFullName = findViewById(R.id.txtFullname);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
        txtNewAddress = findViewById(R.id.txtNewAddress);
        listView = findViewById(R.id.listViewAddress);
        btnAddAddress = findViewById(R.id.buttonAddAddress);

        txtFullName.setText(userInfo.getFullname());
        txtPhone.setText(userInfo.getPhone());
        txtEmail.setText(userInfo.getEmail());
        listAddress = new ArrayList<>();
        if (userInfo.getListAddress() != null)
            listAddress.addAll(userInfo.getListAddress());
        listAdapter = new ListViewAdapterEditProfile(listAddress);
        listView.setAdapter(listAdapter);
    }

    public void onClickCancel(View view) {
        finish();
    }

    public void onClickSave(final View view) {
        String fullname = txtFullName.getText().toString();
        String email = txtEmail.getText().toString();
        String phone = txtPhone.getText().toString();
        User user = new User(email, fullname, phone, listAddress);
        UserInfo userInfo = (UserInfo) getApplication();
        userInfo.setListAddress(listAddress);
        userInfo.setEmail(email);
        userInfo.setFullname(fullname);
        userInfo.setPhone(phone);
        fAccountRepository.updateProfile(user, new CallBackData<User>() {
            @Override
            public void onSuccess(User user) {
                final KProgressHUD kProgressHUD = KProgressHUDManager.showProgessBar(view.getContext(), "Xin đợi");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        kProgressHUD.dismiss();
                        startActivity(new Intent(EditProfileActivity.this, HomeActivity.class));
                    }
                }, 1500);// = 1 seconds

            }

            @Override
            public void onFail(String message) {

            }
        });
    }

    public void onClickAddMoreAddress(View view) {
        if (txtNewAddress.getVisibility() == View.GONE) {
            txtNewAddress.setVisibility(View.VISIBLE);
            btnAddAddress.setText("Thêm");
        } else {
            listAddress.add(txtNewAddress.getText().toString());
            listAdapter.notifyDataSetChanged();
            txtNewAddress.setVisibility(View.GONE);

            btnAddAddress.setText("Thêm địa chỉ");
        }
    }

    public void onClickDeleteAddress(View view) {
        String address = (String) view.getTag();
        listAddress.remove(address);
        listAdapter.notifyDataSetChanged();

    }
}
