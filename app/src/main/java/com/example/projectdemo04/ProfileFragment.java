package com.example.projectdemo04;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectdemo04.repositories.FAccountRepositoryImp;
import com.example.projectdemo04.repositories.FBookRepositoryImp;
import com.example.projectdemo04.utils.CallBackData;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    FBookRepositoryImp repon ;
    private TextView fullname, email, phone;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        repon = new FBookRepositoryImp(getActivity());
        fullname = view.findViewById(R.id.txtFullname);
        email = view.findViewById(R.id.txtEmail);
        phone = view.findViewById(R.id.txtPhone);
        initView();
        return view;
    }

    private void initView() {
//        repon.getProfile(new CallBackData<List<String>>() {
//
//            @Override
//            public void onSuccess(List<String> list) {
//                fullname.setText(list.get(0));
//                email.setText(list.get(1));
//                phone.setText(list.get(2));
//            }
//
//            @Override
//            public void onFail(String message) {
//
//            }
//        });
    }
    public  void clickToExit(View view){

    }

}
