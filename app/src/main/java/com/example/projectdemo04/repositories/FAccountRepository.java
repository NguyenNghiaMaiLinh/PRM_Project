package com.example.projectdemo04.repositories;

import com.example.projectdemo04.model.Account;
import com.example.projectdemo04.utils.CallBackData;

public interface FAccountRepository {
    void login(String username, String password, CallBackData<Account> data);

}
