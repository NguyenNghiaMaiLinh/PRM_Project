package com.example.projectdemo04;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectdemo04.home.CategoryFragment;
import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.repositories.FBookRepositoryImp;
import com.example.projectdemo04.utils.CallBackData;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {
    FBookRepositoryImp repo ;


    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        repo = new FBookRepositoryImp(getContext());
        initView();
        return view;
    }

    private void initView() {
        repo.getTruyenTranh( new CallBackData<List<Book>>() {
            @Override
            public void onSuccess(List<Book> books) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.add(R.id.homeContainer, new CategoryFragment("Truyện Tranh",books));
                transaction.commit();
            }

            @Override
            public void onFail(String message) {

            }
        });

        repo.getTieuThuyet( new CallBackData<List<Book>>() {
            @Override
            public void onSuccess(List<Book> books) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.add(R.id.homeContainer, new CategoryFragment("Tiểu Thuyết",books));
                transaction.commit();
            }

            @Override
            public void onFail(String message) {

            }
        });

        repo.getVanHoc( new CallBackData<List<Book>>() {
            @Override
            public void onSuccess(List<Book> books) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.add(R.id.homeContainer, new CategoryFragment("Văn Học",books));
                transaction.commit();
            }

            @Override
            public void onFail(String message) {

            }
        });

        repo.getXaHoi( new CallBackData<List<Book>>() {
            @Override
            public void onSuccess(List<Book> books) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.add(R.id.homeContainer, new CategoryFragment("Xã Hội",books));
                transaction.commit();
            }

            @Override
            public void onFail(String message) {

            }
        });
    }

}
