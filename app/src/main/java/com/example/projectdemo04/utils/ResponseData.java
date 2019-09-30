package com.example.projectdemo04.utils;

public class ResponseData<T> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseData(T data) {
        this.data = data;
    }
}
