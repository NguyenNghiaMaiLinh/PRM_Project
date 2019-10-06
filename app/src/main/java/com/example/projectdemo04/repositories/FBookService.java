package com.example.projectdemo04.repositories;

import com.example.projectdemo04.utils.ConfigApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface FBookService {
    @GET(ConfigApi.Api.GET_TOP_DISCOUNT)
    Call<ResponseBody> getTopDiscount(@Header("Authorization") String token);

    @GET(ConfigApi.Api.GET_CLICKED_BOOKS)
    Call<ResponseBody> getClickedBooks(@Header("Authorization") String token);

    @GET(ConfigApi.Api.GET_TOP_SALES)
    Call<ResponseBody> getTopSales(@Header("Authorization") String token);

    Call<ResponseBody> search(@Header("Authorization") String token, @Query("name=") String value);

}
