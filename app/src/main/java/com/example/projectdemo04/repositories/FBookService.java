package com.example.projectdemo04.repositories;

import com.example.projectdemo04.utils.ConfigApi;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FBookService {
    @GET(ConfigApi.Api.GETTRUYEN)
    Call<ResponseBody> getTruyen(@Header("Authorization") String token);
    Call<ResponseBody> getTruyen(@Header("Authorization") String token, @Query("name=") String value);

}

