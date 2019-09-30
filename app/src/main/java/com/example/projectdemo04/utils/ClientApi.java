package com.example.projectdemo04.utils;

import com.example.projectdemo04.repositories.FAccountService;

public class ClientApi extends BaseApi {
    public FAccountService fAccountService(){
        return this.getService(FAccountService.class, ConfigApi.BASE_URL);
    }
}
