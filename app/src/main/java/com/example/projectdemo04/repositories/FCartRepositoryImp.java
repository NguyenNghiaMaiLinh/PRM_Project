package com.example.projectdemo04.repositories;

import android.content.Context;

import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.model.Cart;
import com.example.projectdemo04.model.CartBook;
import com.example.projectdemo04.model.Order;
import com.example.projectdemo04.model.Token;
import com.example.projectdemo04.model.BookOrders;
import com.example.projectdemo04.utils.CallBackData;
import com.example.projectdemo04.utils.ClientApi;
import com.example.projectdemo04.utils.ResponseData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FCartRepositoryImp implements FCartRepository {
    static String token;
    public FCartRepositoryImp() {

    }
    public FCartRepositoryImp(Context context) {
        token = context.getSharedPreferences("autheninfo", 0).getString("token","");

    }

    @Override
    public void addToCart(long id, int quantity, final CallBackData<Cart> data) {
        ClientApi clientApi = new ClientApi();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
            jsonObject.put("quantity", quantity);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        Call<ResponseBody> call = clientApi.fCartService().postAddToCart(token, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<Cart>() {
                        }.getType();
                        Cart responseData = new Gson().fromJson(result, type);

                        if (responseData != null) {
                            data.onSuccess(responseData);
                        } else {
                            data.onFail("Thêm vào giỏ hàng không thành công");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    data.onFail("Bạn cần đăng nhập lại");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void getAllInCart(String token, final CallBackData<List<CartBook>> data) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fCartService().getAllInCart(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<List<CartBook>>>() {
                        }.getType();
                        ResponseData<List<CartBook>> responseData = new Gson().fromJson(result, type);
                        List<CartBook> list = responseData.getData();
                        if (responseData != null) {
                            data.onSuccess(list);
                        } else {
                            data.onFail("Lỗi server");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    data.onFail(response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void payment(String token, List<Order> list, final CallBackData<String> data) {

        ClientApi clientApi = new ClientApi();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        try {
            for (Order item : list) {
                jsonObject1.put("id", item.getId());
                jsonObject1.put("quantity", item.getQuantity());
                jsonArray.put(jsonObject1);
            }
            jsonObject.put("bookOrders", jsonArray);

        } catch (
                JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        Call<ResponseBody> call = clientApi.fCartService().payment(token, body);
        System.out.println("body" + body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        if (result == null) {
                            data.onSuccess("Thanh toán Thành công");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    data.onFail("Thanh toán thất bại");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}
