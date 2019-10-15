package com.example.projectdemo04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectdemo04.model.User;
import com.example.projectdemo04.repositories.FAccountRepository;
import com.example.projectdemo04.repositories.FAccountRepositoryImp;
import com.example.projectdemo04.utils.CallBackData;

public class EditProfileActivity extends AppCompatActivity {
    FAccountRepository fAccountRepository ;
    EditText txtFullName,txtEmail, txtPhone,txtAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        fAccountRepository = new FAccountRepositoryImp(this);

        User userInfo =(User) getIntent().getSerializableExtra("userInfo");
        txtFullName = findViewById(R.id.txtFullname);
        txtAddress = findViewById(R.id.txtAddress);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);

        txtFullName.setText(userInfo.getFullname());
        txtAddress.setText(userInfo.getAddress());
        txtPhone.setText(userInfo.getPhone());
        txtEmail.setText(userInfo.getEmail());
    }

    public void onClickCancel(View view){
        finish();
    }
    public void onClickSave(View view){
        String fullname = txtFullName.getText().toString();
        String email = txtEmail.getText().toString();
        String phone = txtPhone.getText().toString();
        String address = txtAddress.getText().toString();
        User user = new User(fullname,email,phone,address);
        fAccountRepository.updateProfile(user, new CallBackData<User>() {
            @Override
            public void onSuccess(User user) {
                Toast.makeText(EditProfileActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditProfileActivity.this,HomeActivity.class));
            }

            @Override
            public void onFail(String message) {

            }
        });
    }
}
