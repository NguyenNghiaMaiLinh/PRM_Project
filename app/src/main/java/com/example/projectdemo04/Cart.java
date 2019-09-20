package com.example.projectdemo04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Cart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        CartFragment cartFragment = new CartFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, cartFragment).commit();
    }
}
