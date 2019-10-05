package com.example.projectdemo04.utils;

import com.example.projectdemo04.repositories.FAccountService;
import com.example.projectdemo04.repositories.FBookService;

public class ClientApi extends BaseApi {
    public FAccountService fAccountService(){
        return this.getService(FAccountService.class, ConfigApi.BASE_URL);
    }
    public FBookService fBookService(){
        return this.getService(FBookService.class, ConfigApi.BASE_URL);
    }
}
