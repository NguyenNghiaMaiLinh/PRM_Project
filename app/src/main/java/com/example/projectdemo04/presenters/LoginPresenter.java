package com.example.projectdemo04.presenters;


import com.example.projectdemo04.model.Token;
import com.example.projectdemo04.repositories.FAccountRepository;
import com.example.projectdemo04.repositories.FAccountRepositoryImp;
import com.example.projectdemo04.utils.CallBackData;
import com.example.projectdemo04.views.LoginView;

public class LoginPresenter {
    private LoginView loginView;
    private FAccountRepository repo;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        repo = new FAccountRepositoryImp();
    }

    public void login(String username, String password) {
        repo.login(username, password, new CallBackData<Token>() {
            @Override
            public void onSuccess(Token token) {
                loginView.loginSuccess(token);
            }

            @Override
            public void onFail(String message) {
                loginView.loginFailed(message);
            }
        });
    }
}

