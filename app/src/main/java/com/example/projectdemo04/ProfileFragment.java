package com.example.projectdemo04;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectdemo04.model.User;
import com.example.projectdemo04.repositories.FAccountRepository;
import com.example.projectdemo04.repositories.FAccountRepositoryImp;
import com.example.projectdemo04.repositories.FBookRepositoryImp;
import com.example.projectdemo04.utils.CallBackData;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    FAccountRepository repon ;
    private TextView fullname, email, phone, txtAddress;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        repon = new FAccountRepositoryImp(getActivity());
        fullname = view.findViewById(R.id.txtFullname);
        email = view.findViewById(R.id.txtEmail);
        phone = view.findViewById(R.id.txtPhone);
        txtAddress = view.findViewById(R.id.txtAddressManage);
        initView();
        return view;
    }

    private void initView() {
        repon.getProfile(new CallBackData<User>() {

            @Override
            public void onSuccess(User user) {
                email.setText(user.getEmail());
                if(user.getFullname() != null && !user.getFullname().isEmpty())
                fullname.setText(user.getFullname());

                if(user.getPhone() != null && !user.getPhone().isEmpty())
                    phone.setText(user.getPhone());
                if(user.getAddress() != null && !user.getAddress().isEmpty())
                    txtAddress.setText(user.getAddress());
            }

            @Override
            public void onFail(String message) {

            }
        });
    }


}
