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

import com.example.projectdemo04.model.Token;
import com.example.projectdemo04.presenters.LoginPresenter;
import com.example.projectdemo04.presenters.RegisterPresenter;
import com.example.projectdemo04.views.LoginView;
import com.example.projectdemo04.views.RegisterView;
import com.kaopiz.kprogresshud.KProgressHUD;

public class RegisterActivity extends AppCompatActivity implements RegisterView {
    private EditText userEmail;
    private EditText userPass;
    private EditText userPassConfirm;
    private RegisterPresenter mRegisterPresenter;
    private EditText userName;
    private RadioButton check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userEmail = (EditText) findViewById(R.id.userEmail2);
        userPass = (EditText) findViewById(R.id.userPassword2);
        userPassConfirm = (EditText) findViewById(R.id.userConfirm2);
        userName = (EditText) findViewById(R.id.userName2);
        check = (RadioButton) findViewById(R.id.radioButton);
        mRegisterPresenter = new RegisterPresenter(this);
    }

    public void onRegisterDone(View view) {
        String email = userEmail.getText().toString().trim();
        String pass = userPass.getText().toString().trim();
        String passConfirm = userPassConfirm.getText().toString().trim();
        String username = userName.getText().toString().trim();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(pass).matches()) {
            Toast.makeText(this, "Email không được trống", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Mật khẩu không được để trống", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Tài khoản không được để trống", Toast.LENGTH_LONG).show();
        } else if (!email.matches(emailPattern)) {
            Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_LONG).show();
        } else if (!passConfirm.equals(pass)) {
            Toast.makeText(this, "Mật khẩu không giống nhau", Toast.LENGTH_LONG).show();
        } else if (pass.length() < 6) {
            Toast.makeText(this, "Mật khẩu phải lớn hơn 6 ký tự", Toast.LENGTH_LONG).show();
        } else if (!check.isChecked()) {
            Toast.makeText(this, "Bạn phải chấp nhận điều khoản của chúng tôi", Toast.LENGTH_LONG).show();
        } else {
            mRegisterPresenter.register(username, email, pass);

        }
    }

    public void onExit(View view) {
        finish();
        moveTaskToBack(true);
    }

    @Override
    public void registerSuccess(Token token) {
        final KProgressHUD kProgressHUD = KProgressHUDManager.showProgessBar(this, "Thành công");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                kProgressHUD.dismiss();
                RegisterActivity.this.startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
            }
        }, 1000);// = 1 seconds
    }

    @Override
    public void registerFailed(String s) {
        Toast.makeText(getApplicationContext(), "Tài khoản đã tồn tại", Toast.LENGTH_LONG).show();
    }
}
