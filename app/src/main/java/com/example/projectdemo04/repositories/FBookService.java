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
    @GET(ConfigApi.Api.SEARCH)
    Call<ResponseBody> search(@Header("Authorization") String token, @Query("name") String value);

    @GET(ConfigApi.Api.GET_TRUYEN_TRANH)
    Call<ResponseBody> getTruyenTranh(@Header("Authorization") String token);

    @GET(ConfigApi.Api.GET_TIEU_THUYET)
    Call<ResponseBody> getTieuThuyet(@Header("Authorization") String token);

    @GET(ConfigApi.Api.GET_VAN_HOC)
    Call<ResponseBody> getVanHoc(@Header("Authorization") String token);

    @GET(ConfigApi.Api.GET_XA_HOI)
    Call<ResponseBody> getXaHoi(@Header("Authorization") String token);


}

