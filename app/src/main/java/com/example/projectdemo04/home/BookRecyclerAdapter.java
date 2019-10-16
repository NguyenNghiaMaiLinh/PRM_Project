package com.example.projectdemo04.home;

import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectdemo04.R;
import com.example.projectdemo04.model.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.MyViewHolder> {
    List<Book> books;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView bookImg;
        private TextView bookTitle,bookPrice,bookDiscount,bookId,bookOriginalPrice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImg = itemView.findViewById(R.id.imageBook);
            bookTitle = itemView.findViewById(R.id.txtBookTitle);
            bookPrice = itemView.findViewById(R.id.txtBookPrice);
            bookId = itemView.findViewById(R.id.bookId);
            bookDiscount = itemView.findViewById(R.id.txtBookDiscount);
            bookOriginalPrice  = itemView.findViewById(R.id.txtBookOriginalPrice);
        }


    }

    public BookRecyclerAdapter(List<Book> books) {
        this.books = books;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item,parent,false);
        MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Book book = books.get(position);
        holder.bookPrice.setText(convertPriceToFormatString(book.getPrice()));
        holder.bookTitle.setText(books.get(position).getProductName()+"");
        holder.bookId.setText(books.get(position).getId()+"");
        if(book.getDiscount() >0){
            holder.bookDiscount.setText("-" + book.getDiscount()*100 + "%");
            holder.bookDiscount.setVisibility(View.VISIBLE);
            holder.bookOriginalPrice.setText(convertPriceToFormatString(book.getPrice()));
            holder.bookOriginalPrice.setVisibility(View.VISIBLE);
            holder.bookOriginalPrice.setPaintFlags(holder.bookOriginalPrice.getPaintFlags()|Paint.STRIKE_THRU_TEXT_FLAG);
            long sellPrice =Math.round( book.getPrice()*(1-book.getDiscount()));
            holder.bookPrice.setText(convertPriceToFormatString(sellPrice));
        }

        Picasso.get().load(book.getImgUrl()).into(holder.bookImg);

    }
    public static String convertPriceToFormatString(long price){
        String raw = price +"";
        String result = raw.substring(0,raw.length()-3)+ "." + raw.substring(raw.length()-3, raw.length()) + " đ";
        return result;
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
