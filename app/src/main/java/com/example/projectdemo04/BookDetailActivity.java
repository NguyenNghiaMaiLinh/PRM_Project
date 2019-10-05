package com.example.projectdemo04;

import com.example.projectdemo04.model.Book;

import com.example.projectdemo04.model.BookViews;
import com.example.projectdemo04.presenters.BookPresenter;
import com.example.projectdemo04.presenters.LoginPresenter;
import com.example.projectdemo04.views.BookView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BookDetailActivity extends AppCompatActivity implements BookView {
    ViewPager viewPager;
    private BookPresenter mBookPresenter;
    Adapter adapter;

    List<BookViews> model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        model = new ArrayList<>();
        model.add(new BookViews("Nhà Giả Kim", R.drawable.nhagiakim, 123));
        model.add(new BookViews("Mắt Biếc", R.drawable.matbiec, 123));
        model.add(new BookViews("Nhà Trắng", R.drawable.nanhtrang, 123));
        model.add(new BookViews("Thức Tỉnh", R.drawable.thuctinh, 123));
        model.add(new BookViews("Nhà Giả Kim", R.drawable.nhagiakim, 123));

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
        mBookPresenter = new BookPresenter(this);
        mBookPresenter.getTruyen(
                "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU3MDIwNjg4N30.kpGegav6pUTZR46v1NjNuEL14UUhEMzJdTgxnQvVHC3cmtGjZMHR61bCHjQX0TJgntk_1IH6i4JaczYDks8Bgw");
    }


    public void onClickBookDecription(View view) {
        Intent intent = new Intent(this, BookActivity.class);
        startActivity(intent);
    }



    @Override
    public void getSuccess(List<Book> book) {
        Toast.makeText(getApplicationContext(), book.get(1).getProductName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getFailed(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}
