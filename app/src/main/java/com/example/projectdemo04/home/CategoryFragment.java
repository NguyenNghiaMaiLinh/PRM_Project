package com.example.projectdemo04.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectdemo04.R;
import com.example.projectdemo04.model.Book;

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
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        BookRecyclerAdapter bookRecyclerAdapter = new BookRecyclerAdapter(books);
        recyclerView.setAdapter(bookRecyclerAdapter);
        return view;
    }

}
