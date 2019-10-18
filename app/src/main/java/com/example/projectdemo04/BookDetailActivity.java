package com.example.projectdemo04;

import com.example.projectdemo04.home.BookRecyclerAdapter;
import com.example.projectdemo04.model.Book;

import com.example.projectdemo04.model.CartBook;
import com.example.projectdemo04.presenters.BooksPresenter;
import com.example.projectdemo04.presenters.CartPresenter;
import com.example.projectdemo04.views.BookView;
import com.example.projectdemo04.views.CartView;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BookDetailActivity extends AppCompatActivity implements BookView,CartView {
    ViewPager viewPager;
    private BooksPresenter mBooksPresenter;
    private CartPresenter mCartPresenter;
    Adapter adapter;
    List<Book> bookList;

    TextView txtBookDescription;
    TextView txtCartQuantity;
    TextView productName1;
    TextView price1;
    TextView author1;
    TextView providedBy1;
    TextView publishedBy1;
    TextView providedBy12;
    ImageView imageView1;
    TextView category1;
    int totalOfCartItem;
    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        txtCartQuantity = findViewById(R.id.cartquantity);
        txtCartQuantity.setVisibility(View.INVISIBLE);
        viewPager = findViewById(R.id.viewPager011);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar01);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        txtBookDescription = findViewById(R.id.txtBookDescription);
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
        book = (Book) intent.getSerializableExtra("book");
        productName1.setText(book.getProductName());
        price1.setText(BookRecyclerAdapter.convertPriceToFormatString(book.getPrice()));
        author1.setText(book.getAuthor());
        providedBy1.setText(book.getProvidedBy());
        publishedBy1.setText(book.getPublishedBy());
        providedBy12.setText(book.getProvidedBy());
        Picasso.get().load(book.getImgUrl()).into(imageView1);
        category1.setText(book.getCategory());
        txtBookDescription.setText(book.getDescription());

        mBooksPresenter = new BooksPresenter(this);



        mCartPresenter = new CartPresenter(this, this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        getTotalOfCartItemFromUserInfo();
        mBooksPresenter.getTopDiscount();
    }
    private void getTotalOfCartItemFromUserInfo(){
        UserInfo userInfo = (UserInfo) getApplication();
        List<CartBook> list = userInfo.getListCart();
        totalOfCartItem = 0;
        for(CartBook cartBook: list){
            totalOfCartItem+= cartBook.getQuantity();
        }
        txtCartQuantity.setText(totalOfCartItem+"");
        if(totalOfCartItem > 0)
            txtCartQuantity.setVisibility(View.VISIBLE);
    }

    @Override
    public void getSuccess(List<Book> books) {
        bookList = new ArrayList<>();
        bookList.addAll(books);
        adapter = new Adapter(bookList, this);

        viewPager.setAdapter(adapter);
        viewPager.setPadding(10, 0, 10, 0);
    }

    @Override
    public void getFailed(String s) {
        Toast.makeText(BookDetailActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    public void onAddToCart(View view) {
        mCartPresenter.postAddToCart(book.getId(), 1);
        totalOfCartItem = totalOfCartItem + 1;
        txtCartQuantity.setText(totalOfCartItem + "");
        Toast.makeText(BookDetailActivity.this, "Sản phẩm đã được thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
    }
    public void onClickToCart(View view) {
       startActivity(new Intent(getApplicationContext(), CartActivity.class));

    }








    @Override
    public void getCartSuccess(List<CartBook> cart) {
        UserInfo userInfo = (UserInfo) getApplication();
        userInfo.setListCart(cart);
    }

    @Override
    public void getCartFailed(String s) {

    }
}
