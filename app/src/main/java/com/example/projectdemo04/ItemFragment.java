package com.example.projectdemo04;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projectdemo04.model.Book;


public class ItemFragment extends Fragment {
    Book book;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item,container,false);
        view.findViewById(R.id.imageBook).setBackgroundResource(book.getImageID());
        TextView textView = view.findViewById(R.id.txtBookTitle);
        TextView txtBookPrice = view.findViewById(R.id.txtBookPrice);
        txtBookPrice.setText(book.getPrice() + " đ");
        textView.setText(book.getTitle());
        return view;
    }

    public ItemFragment(Book book) {
        this.book = book;
    }
}
