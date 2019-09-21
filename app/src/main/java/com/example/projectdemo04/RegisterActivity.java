package com.example.projectdemo04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

public class RegisterActivity extends AppCompatActivity {
    private EditText userEmail;
    private EditText userPass;
    private EditText userPassConfirm;
    private EditText userName;
    private RadioButton check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userEmail = (EditText) findViewById(R.id.userEmail);
        userPass = (EditText) findViewById(R.id.userPassword);
        userPassConfirm = (EditText) findViewById(R.id.userConfirm);
        userName = (EditText) findViewById(R.id.userName);
        check = (RadioButton) findViewById(R.id.radioButton);
        ;
    }
    public  void onClickHomePage(View view){
        String email = userEmail.getText().toString().trim();
        String pass = userPass.getText().toString().trim();
        if (TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(pass).matches()) {
            Toast.makeText(this, "Email không được trống", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Mật khẩu không được để trống", Toast.LENGTH_LONG).show();
        } else if (pass.length() < 6) {
            Toast.makeText(this, "Mật khẩu phải lớn hơn 6 ký tự", Toast.LENGTH_LONG).show();
        } else {
            final KProgressHUD kProgressHUD = KProgressHUDManager.showProgessBar(this, "Thành công");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    kProgressHUD.dismiss();
                    RegisterActivity.this.startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                }
            }, 1000);// = 1 seconds
        }
    }
    public  void onExit(View view){
        finish();
        moveTaskToBack(true);
    }
}
