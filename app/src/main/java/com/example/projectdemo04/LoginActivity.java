package com.example.projectdemo04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

public class LoginActivity extends AppCompatActivity {
    private EditText userEmail;
    private EditText userPass;

    private static int REGISTER_ACTIVITY = 1;
    private static String ADMIN = "admin";
    private static String PASS = "admin123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userEmail = findViewById(R.id.userEmail);
        userPass = findViewById(R.id.userPassword);
    }
    public void onClickHomePage(View view) {
        String email = userEmail.getText().toString().trim();
        String pass = userPass.getText().toString().trim();
        if (TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(pass).matches()) {
            Toast.makeText(this, "Email không được trống", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Mật khẩu không được trống", Toast.LENGTH_LONG).show();
        } else if (pass.length() < 6) {
            Toast.makeText(this, "Mật khẩu phải lớn hơn 6 ký tự", Toast.LENGTH_LONG).show();
        } else if (email.equalsIgnoreCase(ADMIN) && pass.equalsIgnoreCase(PASS)) {
            final KProgressHUD kProgressHUD = KProgressHUDManager.showProgessBar(this, "Thành công");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    kProgressHUD.dismiss();
                    LoginActivity.this.startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                }
            }, 1000);// = 1 seconds

        } else {
            Toast.makeText(this, "Đăng nhập thất bại!", Toast.LENGTH_LONG).show();
        }
    }

    public void onExit(View view) {
        finish();
        moveTaskToBack(true);
    }
    public void onMoveRegisterForm(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivityForResult(intent, REGISTER_ACTIVITY);
    }
}
