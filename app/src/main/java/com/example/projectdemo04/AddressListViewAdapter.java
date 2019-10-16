package com.example.projectdemo04;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class AddressListViewAdapter extends BaseAdapter {
    String[] addresses;

    public AddressListViewAdapter(String[] addresses) {
        this.addresses = addresses;
    }

    @Override

    public int getCount() {
        return addresses.length;
    }

    @Override
    public Object getItem(int position) {
        return addresses[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
