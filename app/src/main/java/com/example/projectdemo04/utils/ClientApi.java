package com.example.projectdemo04.utils;

import com.example.projectdemo04.repositories.FAccountService;
import com.example.projectdemo04.repositories.FBookService;
import com.example.projectdemo04.repositories.FCartService;

public class ClientApi extends BaseApi {
    public FAccountService fAccountService(){
        return this.getService(FAccountService.class, ConfigApi.BASE_URL);
    }
    public FBookService fBookService(){
        return this.getService(FBookService.class, ConfigApi.BASE_URL);
    }
    public FCartService fCartService(){
        return this.getService(FCartService.class, ConfigApi.BASE_URL);
    }
}
