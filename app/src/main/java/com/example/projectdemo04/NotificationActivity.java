package com.example.projectdemo04;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

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


}
