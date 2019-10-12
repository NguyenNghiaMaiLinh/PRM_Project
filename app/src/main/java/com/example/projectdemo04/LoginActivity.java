package com.example.projectdemo04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectdemo04.model.Token;
import com.example.projectdemo04.presenters.LoginPresenter;
import com.example.projectdemo04.views.LoginView;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private AutoCompleteTextView userName;
    private AutoCompleteTextView userPass;
    private String username;
    private String pass;
    private String baseUrl;
    private LoginPresenter mLoginPresenter;
    private static int REGISTER_ACTIVITY = 1;
    private String accessToken;
    private String tokenType;
    private Preferences preferences;
    private CallbackManager callbackManager;
    private LoginButton loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferences = new Preferences();
        userName = (AutoCompleteTextView) findViewById(R.id.userName123);
        userPass = (AutoCompleteTextView) findViewById(R.id.userPassword123);
        mLoginPresenter = new LoginPresenter(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);


        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(
                "user_friends", "email", "public_profile", "user_gender", "user_location"));

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
                                Log.v("LoginActivity Response ", response.toString());

                                try {
                                    String id = object.getString("id");
                                    String name = object.getString("name");
                                    String gender = object.getString("gender");
                                    String birthday = object.getString("birthday");
                                    String location = object.getJSONObject("location").getString("name");
                                    Log.v("id = ", " " + id);
                                    Log.v("name = ", " " + name);
                                    Log.v("gender = ", " " + gender);
                                    Log.v("birthday = ", " " + birthday);
                                    Log.v("location = ", " " + location);

                                    String email;
                                    if (object.has("email")) {
                                        email = object.getString("email");
                                        Log.v("email = ", " " + email);
                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,gender,email, birthday,location");
                request.setParameters(parameters);
                request.executeAsync();
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                LoginManager.getInstance().logOut();
            }

            @Override
            public void onError(FacebookException exception) {
            }
        });
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }
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
        preferences.setAccessToken(this, tokenType+ " " + accessToken );
        Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_LONG).show();
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

}


