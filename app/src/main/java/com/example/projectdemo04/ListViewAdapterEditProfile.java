package com.example.projectdemo04;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapterEditProfile extends BaseAdapter {
    List<String> addresses;

    public ListViewAdapterEditProfile(List<String> addresses) {
        this.addresses = addresses;
    }

    @Override

    public int getCount() {
        return addresses.size();
    }

    @Override
    public Object getItem(int position) {
        return addresses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.fragment_address_manage, parent, false);
        }

        TextView txtAddress = convertView.findViewById(R.id.addressView);
        TextView btnDelete = convertView.findViewById(R.id.txtDeleteAddress);
        txtAddress.setText(addresses.get(position));
        btnDelete.setTag(addresses.get(position));
        return convertView;
    }
}
