package com.example.projectdemo04;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectdemo04.model.Book;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


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
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        Book book1 = new Book("Thuc tinh", R.drawable.thuctinh, 10000);
        Book book2 = new Book("Nha gia kim", R.drawable.nhagiakimm, 20000);
        Book book3 = new Book("Nanh trang", R.drawable.nanhtrang, 60000);
        Book book4 = new Book("Mat biec", R.drawable.matbiec, 52000);
        Book[] books = {book1,book2,book3,book4};

        transaction.add(R.id.containerTopSale, new CategoryFragment("Top sale",books));
        transaction.add(R.id.containerTopSale, new CategoryFragment("New book",books));
        transaction.commit();
        return view;
    }

}
