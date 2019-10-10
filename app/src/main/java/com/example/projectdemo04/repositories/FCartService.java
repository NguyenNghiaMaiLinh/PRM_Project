package com.example.projectdemo04.repositories;

import com.example.projectdemo04.utils.ConfigApi;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface FCartService {
    @POST(ConfigApi.Api.POST_ADD_TO_CART)
    Call<ResponseBody> postAddToCart(@Header("Authorization") String token, @Body RequestBody body);

    @GET(ConfigApi.Api.GET_ALL_IN_CART)
    Call<ResponseBody> getAllInCart(@Header("Authorization") String token);

    @POST(ConfigApi.Api.PAYMENT)
    Call<ResponseBody> payment(@Header("Authorization") String token, @Body RequestBody body);
}
