package fptt.example.bookshop;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fptt.example.bookshop.R;

import fptt.example.bookshop.model.CartBook;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment{
    List<CartBook> listProduct;
    RecyclerView myCartRecyclerView;
    RecyclerView.LayoutManager layoutManager;


    public CartFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cart, container, false);



        return view;
    }



}
