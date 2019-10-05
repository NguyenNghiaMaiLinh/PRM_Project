package com.example.projectdemo04.utils;

public class ConfigApi {
    public static final String BASE_URL="https://prmapi.herokuapp.com/api/";
    public interface Api{
        String LOGIN ="authen/login";
        String REGISTER ="authen/signup";
        String GET_TOP_DISCOUNT ="book/discount";
        String GET_CLICKED_BOOKS = "book/clicked-books";
        String GET_TOP_SALES = "book/top-sales";
    }
}
