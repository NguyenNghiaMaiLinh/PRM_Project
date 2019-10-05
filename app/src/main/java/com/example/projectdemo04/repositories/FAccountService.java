package com.example.projectdemo04.repositories;

import com.example.projectdemo04.utils.ConfigApi;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FAccountService {
    @POST(ConfigApi.Api.LOGIN)
    Call<ResponseBody> login(@Body RequestBody body );

    @POST(ConfigApi.Api.REGISTER)
    Call<ResponseBody> register(@Body RequestBody body );


}
