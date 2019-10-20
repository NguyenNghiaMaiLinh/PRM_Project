package com.example.projectdemo04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.projectdemo04.model.Token;
import com.example.projectdemo04.presenters.LoginPresenter;
import com.example.projectdemo04.presenters.RegisterPresenter;
import com.example.projectdemo04.views.LoginView;
import com.example.projectdemo04.views.RegisterView;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


public class LoginActivity extends AppCompatActivity implements LoginView, RegisterView {
    private AutoCompleteTextView userName;
    private AutoCompleteTextView userPass;
    private String username;
    private String pass;
    private String baseUrl;
    private LoginPresenter mLoginPresenter;
    private RegisterPresenter mRegisterPresenter;
    private static int REGISTER_ACTIVITY = 1;
    private String accessToken;
    private String tokenType;
    private Preferences preferences;
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private String id = null;
    private String name = null;
    private String email = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferences = new Preferences();
        String check = preferences.getAccessToken(this);
        if (check != null) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }
        userName = (AutoCompleteTextView) findViewById(R.id.userName123);
        userPass = (AutoCompleteTextView) findViewById(R.id.userPassword123);
        mRegisterPresenter = new RegisterPresenter(this);
        mLoginPresenter = new LoginPresenter(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);


        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {

                                try {
                                    id = object.getString("id");
//                                    name = object.getString("name");
                                    if (object.has("email")) {
                                        email = object.getString("email");
                                    }
                                    mRegisterPresenter.register(id, email, "123456");

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                LoginManager.getInstance().logOut();
            }

            @Override
            public void onError(FacebookException exception) {
            }
        });

    }


    public void onClickHomePage(View view) {
        username = userName.getText().toString().trim();
        pass = userPass.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Tài khoản không được trống", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Mật khẩu không được trống", Toast.LENGTH_LONG).show();
        } else if (pass.length() < 6) {
            Toast.makeText(this, "Mật khẩu phải lớn hơn 6 ký tự", Toast.LENGTH_LONG).show();
        } else {
            mLoginPresenter.login(username, pass);

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
    public void loginSuccess(Token token) {
        accessToken = token.getAccessToken();
        tokenType = token.getTokenType();
        Bundle bundle = new Bundle();
        bundle.putString("accessToken", accessToken);
        bundle.putString("tokenType", tokenType);
//        preferences.removeAccessToken(this);
        preferences.setAccessToken(this, tokenType + " " + accessToken);
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
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void registerSuccess(Token token) {
        accessToken = token.getAccessToken();
        tokenType = token.getTokenType();
        Bundle bundle = new Bundle();
        bundle.putString("accessToken", accessToken);
        bundle.putString("tokenType", tokenType);
        preferences.setAccessToken(this, tokenType + " " + accessToken);
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
    public void registerFailed(String s) {
        mLoginPresenter.login(id, "123456");
    }
}


