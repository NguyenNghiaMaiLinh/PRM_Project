package com.example.projectdemo04.utils;

public class ConfigApi {
    public static final String BASE_URL="https://prmapi.herokuapp.com/api/";
    public interface Api{
        String LOGIN ="authen/login";
        String REGISTER ="authen/signup";
        String GET_TOP_DISCOUNT ="book/discount";
        String GET_CLICKED_BOOKS = "book/clicked-books";
        String POST_CLICKED_BOOKS = "book/clicked-books";
        String GET_TOP_SALES = "book/topsales";
        String SEARCH = "book";
        String GET_TRUYEN_TRANH = "book/category/Truyen";
        String GET_TIEU_THUYET = "book/category/Tieu thuyet";
        String GET_VAN_HOC = "book/category/Van hoc";
        String GET_XA_HOI = "book/category/Xa hoi";
        String GET_CATEGORY = "book/category/{category}";

        String GET_BOOK_ID = "book/{id}";
        String GET_ALL_BOOK_NAMES = "book/names";
        String PAYMENT = "cart/pay";
        String POST_ADD_TO_CART = "cart";
        String GET_ALL_IN_CART = "cart";
        String GET_PROFILE="user";
        String EDIT_CART = "cart/{id}";
        String DELETE_ITEM_IN_CART = "cart/{id}";
    }
}
