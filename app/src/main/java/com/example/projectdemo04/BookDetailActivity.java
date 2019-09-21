package com.example.projectdemo04;

import com.example.projectdemo04.model.Book;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BookDetailActivity extends AppCompatActivity {
    ViewPager viewPager;

    Adapter adapter;

    List<Book> model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        model = new ArrayList<>();
        model.add(new Book("Nhà Giả Kim", R.drawable.nhagiakim, 123));
        model.add(new Book("Mắt Biếc", R.drawable.matbiec, 123));
        model.add(new Book("Nhà Trắng", R.drawable.nanhtrang, 123));
        model.add(new Book("Thức Tỉnh", R.drawable.thuctinh, 123));
        model.add(new Book("Nhà Giả Kim", R.drawable.nhagiakim, 123));

        adapter = new Adapter(model, this);
        viewPager = findViewById(R.id.viewPager01);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(10, 0, 10, 0);

          Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar01);
          setSupportActionBar(toolbar);
          getSupportActionBar().setTitle("");
          if (getSupportActionBar() != null) {
              getSupportActionBar().setDisplayHomeAsUpEnabled(true);
              getSupportActionBar().setDisplayShowHomeEnabled(true);
          }
          toolbar.setNavigationOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  startActivity(new Intent(getApplicationContext(), HomeActivity.class));
              }
          });

    }


    public void onClickBookDecription(View view) {
        Intent intent = new Intent(this, BookActivity.class);
        startActivity(intent);
    }
}
