package com.example.projectdemo04;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.model.BookViews;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    String category;
    List<Book> books;
    public CategoryFragment() {
        // Required empty public constructor
    }

    public CategoryFragment(String category, List<Book> books) {
        this.category = category;
        this.books = books;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        TextView txtCategory = view.findViewById(R.id.txtCategory);
        txtCategory.setText(category);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.bookRow1, new ItemFragment(books.get(0)));
        transaction.add(R.id.bookRow1, new ItemFragment(books.get(1)));
        transaction.add(R.id.bookRow2, new ItemFragment(books.get(2)));
        transaction.add(R.id.bookRow2, new ItemFragment(books.get(3)));
        transaction.commit();
        return view;
    }

}
