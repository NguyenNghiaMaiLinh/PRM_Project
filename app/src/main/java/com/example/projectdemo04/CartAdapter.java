package com.example.projectdemo04;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectdemo04.model.CartBook;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class CartViewHolder extends RecyclerView.ViewHolder{

    public TextView cart_name, book_price, cart_quantity;
    public ImageView cart_img;



    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        cart_name = itemView.findViewById(R.id.cart_item_name);
        book_price = itemView.findViewById(R.id.cart_item_price);
        cart_quantity = itemView.findViewById(R.id.cart_item_quantity);
        cart_img = itemView.findViewById(R.id.cart_img);
    }
}
public class CartAdapter extends RecyclerView.Adapter<CartViewHolder>{

    private List<CartBook> listData = new ArrayList<>();
    private Context context;


    public CartAdapter(List<CartBook> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cart_item,parent,false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.cart_name.setText(listData.get(position).getBook().getProductName());
        String price = String.valueOf(listData.get(position).getBook().getPrice());
        String quantity = String.valueOf(listData.get(position).getQuantity());
        holder.cart_quantity.setText(quantity);
        holder.book_price.setText(price);

        Picasso.get().load(listData.get(position).getBook().getImgUrl()).into(holder.cart_img);

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
