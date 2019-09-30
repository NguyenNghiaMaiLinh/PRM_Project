package com.example.projectdemo04.views;

import com.example.projectdemo04.model.Account;

public interface LoginView {
    void loginSuccess(Account account);
    void loginFailed(String s);

}
