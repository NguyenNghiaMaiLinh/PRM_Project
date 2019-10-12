package com.example.projectdemo04.home;


import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.projectdemo04.R;
import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.repositories.FBookRepositoryImp;
import com.example.projectdemo04.utils.CallBackData;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    AutoCompleteTextView txtSearch;
    List<String> listOfBookName = new ArrayList<>();
    ArrayAdapter<String> searchAdapter;

    FBookRepositoryImp repo;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        repo = new FBookRepositoryImp(getActivity());

        //mapping slideshow and set it's adapter
        ViewPager viewPager = view.findViewById(R.id.viewPagerHome);
        SlideFragmentAdapter adapter = new SlideFragmentAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);

        //mapping search field and set it's adapter
        txtSearch = view.findViewById(R.id.txtSearch);
        searchAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_expandable_list_item_1, listOfBookName);
        txtSearch.setAdapter(searchAdapter);
        setDataSetForSearchField();
        initView();
        txtSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER){
                    searchEnter(txtSearch.getText().toString());
                }
                return false;
            }
        });
        return view;

    }
    private void searchEnter(String search){
        repo.search( search, new CallBackData<List<Book>>() {
            @Override
            public void onSuccess(List<Book> books) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.search_result, new CategoryFragment("Kết quả",books));
                transaction.commit();
            }

            @Override
            public void onFail(String message) {

            }
        });
    }

    private void setDataSetForSearchField() {
        repo.search( "", new CallBackData<List<Book>>() {
            @Override
            public void onSuccess(List<Book> books) {
                for(Book book: books){
                    listOfBookName.add(book.getProductName());
                }
                searchAdapter.notifyDataSetChanged();
                System.out.println("Data set size :" + listOfBookName.size());
            }

            @Override
            public void onFail(String message) {

            }
        });

    }

    private void initView() {
        repo.getTopSales( new CallBackData<List<Book>>() {
            @Override
            public void onSuccess(List<Book> books) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.add(R.id.homeContainer, new CategoryFragment("Top sales",books));
                transaction.commit();
            }

            @Override
            public void onFail(String message) {

            }
        });
        repo.getTopDiscount( new CallBackData<List<Book>>() {
            @Override
            public void onSuccess(List<Book> books) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.add(R.id.homeContainer, new CategoryFragment("Top discount",books));
                transaction.commit();
            }

            @Override
            public void onFail(String message) {

            }
        });
        repo.getClickedBooks( new CallBackData<List<Book>>() {
            @Override
            public void onSuccess(List<Book> books) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.add(R.id.homeContainer, new CategoryFragment("Sản phẩm đã xem",books));
                transaction.commit();
            }

            @Override
            public void onFail(String message) {

            }
        });


    }


}
