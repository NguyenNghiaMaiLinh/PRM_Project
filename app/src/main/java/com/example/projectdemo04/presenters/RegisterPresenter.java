package com.example.projectdemo04.presenters;


import com.example.projectdemo04.model.Token;
import com.example.projectdemo04.repositories.FAccountRepository;
import com.example.projectdemo04.repositories.FAccountRepositoryImp;
import com.example.projectdemo04.utils.CallBackData;
import com.example.projectdemo04.views.LoginView;
import com.example.projectdemo04.views.RegisterView;

public class RegisterPresenter {
    private RegisterView registerView;
    private FAccountRepository repo;

    public RegisterPresenter(RegisterView registerView) {
        this.registerView = registerView;
        repo = new FAccountRepositoryImp();
    }

    public void register(String username,String email, String password) {
        repo.register(username,email, password, new CallBackData<Token>() {
            @Override
            public void onSuccess(Token token) {
                registerView.registerSuccess(token);
            }

            @Override
            public void onFail(String message) {
                registerView.registerFailed(message);
            }
        });
    }
}
