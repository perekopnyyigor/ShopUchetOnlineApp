package com.example.adapters;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.object.MainMenu;

import com.example.object.Product;
import com.example.shopuchetonline.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    private LayoutInflater inflater;
    private int layout;
    private List<Product> products;

    public ProductAdapter(Context context, int resource, List<Product> products) {
        super(context, resource, products);
        this.products = products;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        ImageView imageView = view.findViewById(R.id.flag);
        TextView nameView = view.findViewById(R.id.name);
        TextView quantityView = view.findViewById(R.id.quantity);


        Product product = products.get(position);

        //Uri uri =  Uri.parse( product.img );
        //flagView.setImageURI(uri);

        nameView.setText(product.name);
        quantityView.setText(Double.toString(product.quantity) );
        Picasso.get().load(product.img).into(imageView);

        return view;
    }
}
