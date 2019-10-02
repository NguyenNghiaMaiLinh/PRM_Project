package com.example.projectdemo04.views;

import com.example.projectdemo04.model.Token;

public interface RegisterView {
    void registerSuccess(Token token);

    void registerFailed(String s);
}
