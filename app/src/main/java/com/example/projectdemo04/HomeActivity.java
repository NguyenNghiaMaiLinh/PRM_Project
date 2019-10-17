package com.example.projectdemo04;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.projectdemo04.home.HomeFragment;
import com.example.projectdemo04.home.ProductFragment;
import com.example.projectdemo04.home.SlideFragmentAdapter;
import com.example.projectdemo04.model.Book;
import com.example.projectdemo04.model.User;
import com.example.projectdemo04.repositories.FAccountRepository;
import com.example.projectdemo04.repositories.FAccountRepositoryImp;
import com.example.projectdemo04.repositories.FBookRepository;
import com.example.projectdemo04.repositories.FBookRepositoryImp;
import com.example.projectdemo04.utils.CallBackData;
import com.facebook.AccessToken;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    Preferences preferences;
    FAccountRepository fAccountRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fAccountRepository = new FAccountRepositoryImp(this);
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
        initData();


    }
    private void initData(){
        fAccountRepository.getProfile(new CallBackData<User>() {
            @Override
            public void onSuccess(User user) {
                UserInfo userInfo = (UserInfo) getApplication();
                userInfo.setEmail(user.getEmail());
                userInfo.setListAddress(user.getListAddress());
                userInfo.setPhone(user.getPhone());
                userInfo.setFullname(user.getFullname());
            }

            @Override
            public void onFail(String message) {

            }
        });
    }

    public void onLogout(View view) {
        new AlertDialog.Builder(this)
                .setMessage("Bạn chắc chắn muốn đăng xuất?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        preferences.removeAccessToken(HomeActivity.this);
                        AccessToken accessToken = AccessToken.getCurrentAccessToken();
                        accessToken.setCurrentAccessToken(null);
                        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton("Không", null)
                .show();

    }

    public void onClickBookDetails(View view) {
        TextView textView = view.findViewById(R.id.txtBookTitle);
        Book book =(Book) textView.getTag();
        FBookRepository fBookRepository = new FBookRepositoryImp(this);
        fBookRepository.postClickedBook(book.getId());
        Intent intent = new Intent(this, BookDetailActivity.class);
        intent.putExtra("book", book);
        startActivity(intent);
    }

    public void onClickCart(View view) {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }
}
