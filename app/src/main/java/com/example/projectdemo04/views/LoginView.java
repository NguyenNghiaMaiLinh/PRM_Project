package com.example.projectdemo04.views;

import com.example.projectdemo04.model.Token;

public interface LoginView {
    void loginSuccess(Token token);
    void loginFailed(String s);

}
