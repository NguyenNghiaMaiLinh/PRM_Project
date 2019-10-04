package com.example.projectdemo04;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.presenters.GetAllBooksPresenter;
import com.example.projectdemo04.views.GetAllBooksView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements GetAllBooksView {
    GetAllBooksPresenter getAllBooksPresenter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ViewPager viewPager = view.findViewById(R.id.viewPagerHome);
        SlideFragmentAdapter adapter = new SlideFragmentAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        Book book1 = new Book("Thuc tinh", R.drawable.thuctinh, 10000);
//        Book book2 = new Book("Nha gia kim", R.drawable.nhagiakimm, 20000);
//        Book book3 = new Book("Nanh trang", R.drawable.nanhtrang, 60000);
//        Book book4 = new Book("Mat biec", R.drawable.matbiec, 52000);
//        Book[] books = {book1,book2,book3,book4};
        getAllBooksPresenter = new GetAllBooksPresenter(this);
        getAllBooksPresenter.getAllBooks();

//        transaction.add(R.id.containerTopSale, new CategoryFragment("Top sale",books));
//        transaction.add(R.id.containerTopSale, new CategoryFragment("New book",books));
//        transaction.commit();
        return view;

    }

    @Override
    public void getSuccess(List<Book> list) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.containerTopSale, new CategoryFragment("Top sale",list));
        transaction.commit();
    }

    @Override
    public void getFail(String message) {
        System.out.println("Fail " + message);

    }
}
