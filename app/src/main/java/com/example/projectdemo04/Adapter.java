package com.example.projectdemo04;

import android.content.Context;
import android.view.Display;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


import com.example.projectdemo04.model.BookViews;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends PagerAdapter {

    private List<BookViews> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public Adapter(List<BookViews> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = layoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, container, false);
        ImageView imageView;
        TextView title, desc;
        imageView = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        desc = view.findViewById(R.id.desc);
        Picasso.get().load(models.get(position).getImageID()).into(imageView);
        title.setText(models.get(position).getTitle());
        desc.setText(String.valueOf(models.get(position).getPrice() + " đ"));

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
