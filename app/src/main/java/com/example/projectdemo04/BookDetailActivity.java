package com.example.projectdemo04;

import com.example.projectdemo04.home.BookRecyclerAdapter;
import com.example.projectdemo04.model.Book;

import com.example.projectdemo04.model.CartBook;
import com.example.projectdemo04.presenters.BookPresenter;
import com.example.projectdemo04.presenters.CartBookPresenter;
import com.example.projectdemo04.presenters.CartPresenter;
import com.example.projectdemo04.repositories.FBookRepository;
import com.example.projectdemo04.repositories.FBookRepositoryImp;
import com.example.projectdemo04.views.BookView;
import com.example.projectdemo04.views.CartBookView;
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

public class BookDetailActivity extends AppCompatActivity implements BookView,CartView, CartBookView {
    ViewPager viewPager;
    private BookPresenter mBookPresenter;
    private CartPresenter mCartPresenter;
    private CartBookPresenter cartBookPresenter;
    Adapter adapter;
    List<Book> bookList;

    TextView txtBookDescription;
    TextView cartQuantity;
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

        cartQuantity = findViewById(R.id.cartquantity);
        cartQuantity.setVisibility(View.INVISIBLE);
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

        mBookPresenter = new BookPresenter(this);



        mCartPresenter = new CartPresenter(this, this);
        cartBookPresenter = new CartBookPresenter(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        cartBookPresenter.getAllInCart();
        mBookPresenter.getTopDiscount();
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
        cartQuantity.setText(totalOfCartItem + "");
        Toast.makeText(BookDetailActivity.this, "Sản phẩm đã được thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
    }
    public void onClickToCart(View view) {
       startActivity(new Intent(getApplicationContext(), CartActivity.class));

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





    @Override
    public void getCartBookSuccess(List<CartBook> cartBooks) {
        for (CartBook cartBook : cartBooks) {
            totalOfCartItem += cartBook.getQuantity();
        }
        cartQuantity.setText(totalOfCartItem + "");
        cartQuantity.setVisibility(View.VISIBLE);
    }

    @Override
    public void getCartBookFailed(String s) {
        Toast.makeText(BookDetailActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCartSuccess(List<CartBook> cart) {

    }

    @Override
    public void getCartFailed(String s) {

    }
}
