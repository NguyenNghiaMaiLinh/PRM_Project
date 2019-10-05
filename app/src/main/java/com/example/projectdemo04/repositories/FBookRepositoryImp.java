package com.example.projectdemo04.repositories;

import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.utils.CallBackData;
import com.example.projectdemo04.utils.ClientApi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FBookRepositoryImp implements FBookRepository {
    @Override
    public void getAllBooks(final CallBackData<List<Book>> data) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fAccountService().getBooksByCategory();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<List<Book>>() {
                        }.getType();
                        List<Book> responseData = new Gson().fromJson(result, type);

                        if (responseData != null) {
                            data.onSuccess(responseData);
                        } else {
                            data.onFail("Lỗi server");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
