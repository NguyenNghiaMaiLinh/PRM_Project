package com.example.projectdemo04.repositories;

import com.example.projectdemo04.model.Account;
import com.example.projectdemo04.utils.CallBackData;
import com.example.projectdemo04.utils.ClientApi;
import com.example.projectdemo04.utils.ResponseData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;


import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FAccountRepositoryImp implements FAccountRepository {

    @Override
    public void login(String username, String password, final CallBackData<Account> data) {
        ClientApi clientApi = new ClientApi();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        Call<ResponseBody> call = clientApi.fAccountService().login(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<Account>() {
                        }.getType();
                        Account responseData = new Gson().fromJson(result, type);

                        //Account account = responseData.getData();

                        if (responseData != null) {
                            data.onSuccess(responseData);
                        } else {
                            data.onFail("Login Fail");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
