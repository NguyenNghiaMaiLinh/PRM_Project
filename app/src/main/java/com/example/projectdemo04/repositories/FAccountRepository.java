package com.example.projectdemo04.repositories;


import com.example.projectdemo04.model.Token;
import com.example.projectdemo04.utils.CallBackData;

public interface FAccountRepository {
    void login(String username, String password, CallBackData<Token> data);
    void register(String username, String email,String password, CallBackData<Token> data);

}