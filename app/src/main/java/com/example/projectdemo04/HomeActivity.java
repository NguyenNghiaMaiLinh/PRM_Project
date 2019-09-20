package com.example.projectdemo04;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.projectdemo04.model.Book;

public class HomeActivity extends AppCompatActivity {
    ViewPager viewPager;
    SlideFragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewPager = findViewById(R.id.viewPagerHome);
        adapter = new SlideFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Book book1 = new Book("Thuc tinh", R.drawable.thuctinh, 10000);
        Book book2 = new Book("Nha gia kim", R.drawable.nhagiakimm, 20000);
        Book book3 = new Book("Nanh trang", R.drawable.nanhtrang, 60000);
        Book book4 = new Book("Mat biec", R.drawable.matbiec, 52000);
        Book[] books = {book1,book2,book3,book4};

        transaction.add(R.id.containerTopSale, new CategoryFragment("Top sale",books));
        transaction.add(R.id.containerTopSale, new CategoryFragment("New book",books));
        transaction.commit();
    }

    public void onClickHome(MenuItem view){
        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);
    }
    public void onClickProfile(MenuItem view){
        Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
        startActivity(intent);
    }
    public void onClickProducts(MenuItem view){
        Intent intent = new Intent(getApplicationContext(),ProductsActivity.class);
        startActivity(intent);

    }
    public void onClickNotification(MenuItem view){
        Intent intent = new Intent(getApplicationContext(),NotificationActivity.class);
        startActivity(intent);
    }
    public void onClickBook(View view){
        Toast.makeText(this, "book is clicked", Toast.LENGTH_SHORT).show();
    }

    public void onClickCart(View view){
        Intent intent = new Intent(this, Cart.class);
        startActivity(intent);
    }



}
