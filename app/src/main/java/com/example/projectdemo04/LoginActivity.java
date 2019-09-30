package com.example.projectdemo04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectdemo04.model.Account;
import com.example.projectdemo04.presenters.LoginPresenter;
import com.example.projectdemo04.views.LoginView;
import com.kaopiz.kprogresshud.KProgressHUD;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private EditText userEmail;
    private EditText userPass;
    private String baseUrl;
    private LoginPresenter mLoginPresenter;
    private static int REGISTER_ACTIVITY = 1;
    private String accessToken ;
    private String tokenType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userEmail = findViewById(R.id.userEmail);
        userPass = findViewById(R.id.userPassword);
        baseUrl = "http://192.168.43.24:9090/api/login";
        mLoginPresenter = new LoginPresenter(this);
    }

    public void onClickHomePage(View view) {
        final String email = userEmail.getText().toString().trim();
        final String pass = userPass.getText().toString().trim();
        if (TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(pass).matches()) {
            Toast.makeText(this, "Email không được trống", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Mật khẩu không được trống", Toast.LENGTH_LONG).show();
        } else if (pass.length() < 6) {
            Toast.makeText(this, "Mật khẩu phải lớn hơn 6 ký tự", Toast.LENGTH_LONG).show();
        } else {
            mLoginPresenter.login(email, pass);
//            ApiAuthenticationClient apiAuthenticationClient = new ApiAuthenticationClient(baseUrl, email, pass);
//            //ApiAuthenticationClient apiAuthenticationClient = new ApiAuthenticationClient(baseUrl);
//            AsyncTask<Void, Void, String> execute = new ExecuteNetworkOperation(apiAuthenticationClient);
//            execute.execute();

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

    @Override
    public void loginSuccess(Account account) {
        accessToken = account.getAccessToken();
        tokenType = account.getTokenType();
        Bundle bundle = new Bundle();
        bundle.putString("accessToken",accessToken);
        bundle.putString("tokenType",tokenType);
        Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
        final KProgressHUD kProgressHUD = KProgressHUDManager.showProgessBar(this, "Thành công");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                kProgressHUD.dismiss();
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }
        }, 1000);// = 1 seconds

    }

    @Override
    public void loginFailed(String s) {
        Toast.makeText(getApplicationContext(), "Đăng nhập thất bại", Toast.LENGTH_LONG).show();
    }

}


