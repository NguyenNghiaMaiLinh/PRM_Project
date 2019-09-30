package com.example.projectdemo04.utils;

public interface CallBackData<T> {
    void onSuccess(T t);
    void onFail(String message);
}
