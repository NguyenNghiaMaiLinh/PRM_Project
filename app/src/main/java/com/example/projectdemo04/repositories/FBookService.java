package com.example.projectdemo04.repositories;

import com.example.projectdemo04.utils.ConfigApi;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
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

    @POST(ConfigApi.Api.POST_CLICKED_BOOKS)
    Call<ResponseBody> addToClickedBooks(@Header("Authorization") String token, @Body RequestBody body);


    @GET(ConfigApi.Api.GET_BOOK_ID)
    Call<ResponseBody> getBookById(@Header("Authorization") String token, @Path("id") long id);

    @GET(ConfigApi.Api.GET_ALL_BOOK_NAMES)
    Call<ResponseBody> getAllBookNames(@Header("Authorization") String token);
    @GET(ConfigApi.Api.GET_CATEGORY)
    Call<ResponseBody> getBookByCategory(@Header("Authorization") String token, @Path("category") String category, @Query("page") int page);




}

