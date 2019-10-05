package com.example.projectdemo04;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.presenters.BookPresenter;
import com.example.projectdemo04.views.BookView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements BookView {


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

        BookPresenter getAllBooksPresenter = new BookPresenter(this);
        getAllBooksPresenter.getTruyen("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU3MDIwNjg4N30.kpGegav6pUTZR46v1NjNuEL14UUhEMzJdTgxnQvVHC3cmtGjZMHR61bCHjQX0TJgntk_1IH6i4JaczYDks8Bgw");


        return view;

    }

    @Override
    public void getSuccess(List<Book> book) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.containerTopSale, new CategoryFragment("Top sale",book));
        transaction.commit();
    }

    @Override
    public void getFailed(String s) {
        System.out.println("Fail " + s);
    }
}
