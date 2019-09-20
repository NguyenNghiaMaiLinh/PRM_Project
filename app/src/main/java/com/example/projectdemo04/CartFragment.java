package com.example.projectdemo04;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment{


    public CartFragment() {
        // Required empty public constructor
    }

    ArrayList<Product> listProduct;
    ProductListViewAdapter productListViewAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        listProduct = new ArrayList<>();
        listProduct.add(new Product(1, "Apple", 500));
        listProduct.add(new Product(2, "Samsung", 450));
        listProduct.add(new Product(3, "Sony", 300));
        listProduct.add(new Product(4, "HTC", 200));

//        String [] list = {"asdasd","asdasd","asdasd"};

        productListViewAdapter = new ProductListViewAdapter(listProduct);
//
//        listViewProduct = view.findViewById(R.id.listProduct);
//        listViewProduct.setAdapter(productListViewAdapter);

        ListView listViewProduct = (ListView)view.findViewById(R.id.listProduct);
//
//        ArrayAdapter<Product> listViewAdapter = new ArrayAdapter<Product>(
//                getActivity(),
//                android.R.layout.simple_list_item_1,
//                listProduct
//        );

        listViewProduct.setAdapter(productListViewAdapter);

        return view;
    }

    class Product{
        String name;
        int price;
        int productID;

        public Product(int productID, String name, int price ) {
            this.name = name;
            this.price = price;
            this.productID = productID;
        }
    }

    class ProductListViewAdapter extends BaseAdapter {
        final ArrayList<Product> listProduct;

        ProductListViewAdapter(ArrayList<Product> listProduct) {
            this.listProduct = listProduct;
        }

        @Override
        public int getCount() {
            return listProduct.size();
        }

        @Override
        public Object getItem(int i) {
            return listProduct.get(i);
        }

        @Override
        public long getItemId(int i) {
            return listProduct.get(i).productID;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View viewProduct;
            if(view == null){
                viewProduct = View.inflate(viewGroup.getContext(), R.layout.fragment_cart, null);

            }else viewProduct = view;
            Product product = (Product) getItem(i);

            ((TextView) viewProduct.findViewById(R.id.nameproduct)).setText(product.name);
            ((TextView) viewProduct.findViewById(R.id.priceproduct)).setText(String.format("Price: %d", product.price));
            return viewProduct;
        }
    }

}
