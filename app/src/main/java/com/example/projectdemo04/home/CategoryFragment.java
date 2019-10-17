package com.example.projectdemo04.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectdemo04.R;
import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.repositories.FBookRepository;
import com.example.projectdemo04.repositories.FBookRepositoryImp;
import com.example.projectdemo04.utils.CallBackData;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    String category;
    List<Book> books;
    RecyclerView recyclerView;
    BookRecyclerAdapter bookRecyclerAdapter;
    boolean isLoading;
    int curentPage;

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
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        bookRecyclerAdapter = new BookRecyclerAdapter(books);
        recyclerView.setAdapter(bookRecyclerAdapter);
        addScrollListener();
        return view;
    }

    void addScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == books.size()) {
                        //bottom of list!
                        loadMore();
                        isLoading = true;
                    }
                }
            }
        });
    }
    private void loadMore(){
        FBookRepository fBookRepository = new FBookRepositoryImp(getActivity());
        fBookRepository.getBooksByCategory(category, curentPage +1, new CallBackData<List<Book>>() {
            @Override
            public void onSuccess(List<Book> books) {
                CategoryFragment.this.books.addAll(books);
                bookRecyclerAdapter.notifyDataSetChanged();
                isLoading = false;
                curentPage++;
            }

            @Override
            public void onFail(String message) {

            }
        });

    }
}


