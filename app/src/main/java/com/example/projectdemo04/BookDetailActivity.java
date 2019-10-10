package com.example.projectdemo04;

import com.example.projectdemo04.model.Book;

import com.example.projectdemo04.model.BookViews;
import com.example.projectdemo04.model.Cart;
import com.example.projectdemo04.presenters.BookDetailPresenter;
import com.example.projectdemo04.presenters.BookPresenter;
import com.example.projectdemo04.presenters.CartPresenter;
import com.example.projectdemo04.views.BookDetailView;
import com.example.projectdemo04.views.BookView;
import com.example.projectdemo04.views.CartView;
import com.squareup.picasso.Picasso;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BookDetailActivity extends AppCompatActivity implements BookView, CartView, BookDetailView {
    ViewPager viewPager;
    private BookPresenter mBookPresenter;
    private BookDetailPresenter bookDetailPresenter;
    private CartPresenter mCartPresenter;
    Adapter adapter;
    long bookId;
    List<BookViews> model;
    String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU3MDIwNjg4N30.kpGegav6pUTZR46v1NjNuEL14UUhEMzJdTgxnQvVHC3cmtGjZMHR61bCHjQX0TJgntk_1IH6i4JaczYDks8Bgw";

    TextView productName1;
    TextView price1;
    TextView author1;
    TextView providedBy1;
    TextView publishedBy1;
    TextView providedBy12;
    ImageView imageView1;
    TextView category1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

//        model = new ArrayList<>();
//        model.add(new BookViews("Nhà Giả Kim", R.drawable.nhagiakim, 123));
//        model.add(new BookViews("Mắt Biếc", R.drawable.matbiec, 123));
//        model.add(new BookViews("Nhà Trắng", R.drawable.nanhtrang, 123));
//        model.add(new BookViews("Thức Tỉnh", R.drawable.thuctinh, 123));
//        model.add(new BookViews("Nhà Giả Kim", R.drawable.nhagiakim, 123));
//
//        adapter = new Adapter(model, this);
//        viewPager = findViewById(R.id.viewPager01);
//        viewPager.setAdapter(adapter);
//        viewPager.setPadding(10, 0, 128, 0);
        viewPager = findViewById(R.id.viewPager011);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar01);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        productName1 = findViewById(R.id.productName1);
        price1 = findViewById(R.id.price1);
        author1 = findViewById(R.id.author1);
        providedBy1 = findViewById(R.id.providedBy1);
        publishedBy1 = findViewById(R.id.publishedBy1);
        providedBy12 = findViewById(R.id.providedBy12);
        imageView1 = findViewById(R.id.image11);
        category1 = findViewById(R.id.category12);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });

        Intent intent = this.getIntent();
        bookId = (long) intent.getLongExtra("bookId", 1);
        mBookPresenter = new BookPresenter(this);
        bookDetailPresenter = new BookDetailPresenter(this);
        if (savedInstanceState != null) {
            String s = savedInstanceState.getString("token");
            mBookPresenter.getTopDiscount(s);
        } else {
            mBookPresenter.getTopDiscount(token);
            bookDetailPresenter.getBookById(token, bookId);
        }


        mCartPresenter = new CartPresenter(this);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("token", token);
    }

    public void onClickBookDecription(View view) {
        Intent intent = new Intent(this, BookActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_OK) {
            long bookId = Long.parseLong(data.getStringExtra("bookId"));
            Toast.makeText(getApplicationContext(), bookId + "", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getSuccess(List<Book> book) {
        model = new ArrayList<>();
        for (int i = 0; i < book.size(); i++) {
            model.add(new BookViews(book.get(i).getProductName(), book.get(i).getImgUrl(), book.get(i).getPrice()));
        }
        adapter = new Adapter(model, this);

        viewPager.setAdapter(adapter);
        viewPager.setPadding(10, 0, 10, 0);
    }

    public void onAddToCart(View view) {
        mCartPresenter.portAddToCart("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU3MDIwNjg4N30.kpGegav6pUTZR46v1NjNuEL14UUhEMzJdTgxnQvVHC3cmtGjZMHR61bCHjQX0TJgntk_1IH6i4JaczYDks8Bgw", bookId, 1);
        Intent intent = new Intent(getApplicationContext(), CartActivity.class);
        startActivity(intent);
    }

    @Override
    public void getSuccess(Cart cart) {
        Toast.makeText(getApplicationContext(), cart.getBook_id(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getSuccess(Book book) {
        productName1.setText(book.getProductName());
        price1.setText(String.valueOf(book.getPrice()));
        author1.setText(book.getAuthor());
        providedBy1.setText(book.getProvidedBy());
        publishedBy1.setText(book.getPublishedBy());
        providedBy12.setText(book.getProvidedBy());
        Picasso.get().load(book.getImgUrl()).into(imageView1);
        category1.setText(book.getCategory());
    }

    @Override
    public void getFailed(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}
