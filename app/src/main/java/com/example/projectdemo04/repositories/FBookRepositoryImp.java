package com.example.projectdemo04.repositories;

import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.utils.CallBackData;
import com.example.projectdemo04.utils.ClientApi;
import com.example.projectdemo04.utils.ResponseData;
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
    public void getTopDiscount(String token, final CallBackData<List<Book>> data) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fBookService().getTopDiscount(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<List<Book>>>() {
                        }.getType();
                        ResponseData<List<Book>> responseData = new Gson().fromJson(result, type);
                        List<Book> list = responseData.getData();
                        if (responseData != null) {
                            data.onSuccess(list);
                        } else {
                            data.onFail("Lỗi server");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
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
    public void getClickedBooks(String token,final CallBackData<List<Book>> data) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fBookService().getClickedBooks(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<List<Book>>>() {
                        }.getType();
                        ResponseData<List<Book>> responseData = new Gson().fromJson(result, type);
                        List<Book> list = responseData.getData();
                        if (responseData != null) {
                            data.onSuccess(list);
                        } else {
                            data.onFail("Lỗi server");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
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
    public void getTopSales(String token,final CallBackData<List<Book>> data) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fBookService().getTopSales(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<List<Book>>>() {
                        }.getType();
                        ResponseData<List<Book>> responseData = new Gson().fromJson(result, type);
                        List<Book> list = responseData.getData();
                        if (responseData != null) {
                            data.onSuccess(list);
                        } else {
                            data.onFail("Lỗi server");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
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
    public void search(String token, String search, final CallBackData<List<Book>> data) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fBookService().search(token,search);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<List<Book>>>() {
                        }.getType();
                        ResponseData<List<Book>> responseData = new Gson().fromJson(result, type);
                        List<Book> list = responseData.getData();
                        if (responseData != null) {
                            data.onSuccess(list);
                        } else {
                            data.onFail("Lỗi server");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
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
    public void getTruyenTranh(String token, final CallBackData<List<Book>> data) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fBookService().getTruyenTranh(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<List<Book>>>() {
                        }.getType();
                        ResponseData<List<Book>> responseData = new Gson().fromJson(result, type);
                        List<Book> list = responseData.getData();
                        if (responseData != null) {
                            data.onSuccess(list);
                        } else {
                            data.onFail("Lỗi server");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    data.onFail(response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getTieuThuyet(String token, final CallBackData<List<Book>> data) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fBookService().getTieuThuyet(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<List<Book>>>() {
                        }.getType();
                        ResponseData<List<Book>> responseData = new Gson().fromJson(result, type);
                        List<Book> list = responseData.getData();
                        if (responseData != null) {
                            data.onSuccess(list);
                        } else {
                            data.onFail("Lỗi server");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    data.onFail(response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getVanHoc(String token, final CallBackData<List<Book>> data) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fBookService().getXaHoi(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<List<Book>>>() {
                        }.getType();
                        ResponseData<List<Book>> responseData = new Gson().fromJson(result, type);
                        List<Book> list = responseData.getData();
                        if (responseData != null) {
                            data.onSuccess(list);
                        } else {
                            data.onFail("Lỗi server");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    data.onFail(response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getXaHoi(String token, final CallBackData<List<Book>> data) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fBookService().getXaHoi(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<List<Book>>>() {
                        }.getType();
                        ResponseData<List<Book>> responseData = new Gson().fromJson(result, type);
                        List<Book> list = responseData.getData();
                        if (responseData != null) {
                            data.onSuccess(list);
                        } else {
                            data.onFail("Lỗi server");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    data.onFail(response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
