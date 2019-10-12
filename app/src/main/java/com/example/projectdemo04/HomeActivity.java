package com.example.projectdemo04;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.projectdemo04.home.HomeFragment;
import com.example.projectdemo04.home.ProductFragment;
import com.example.projectdemo04.home.SlideFragmentAdapter;
import com.example.projectdemo04.model.CartBook;
import com.example.projectdemo04.views.CartBookView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    ViewPager viewPager;
    SlideFragmentAdapter adapter;
    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_bar);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = new HomeFragment();
        transaction.replace(R.id.main_container, fragment);
        transaction.commit();
        preferences = new Preferences();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment = new HomeFragment();

                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.nav_product:
                        fragment = new ProductFragment();
                        break;
                    case R.id.nav_notification:
                        fragment = new NotificationFragment();
                        break;
                    case R.id.nav_profile:
                        fragment = new ProfileFragment();
                        break;

                }
                transaction.replace(R.id.main_container, fragment);
                transaction.commit();
                return true;
            }
        });


    }

    public void onLogout(View view) {
        preferences.removeAccessToken(this);
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void onClickBookDetails(View view) {
        TextView textView = view.findViewById(R.id.bookId);
        long bookId = Long.parseLong(textView.getText().toString());
        Intent intent = new Intent(this, BookDetailActivity.class);
        intent.putExtra("bookId", bookId);
        startActivity(intent);
    }

    public void onClickCart(View view) {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }
}
