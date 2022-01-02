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

import com.example.object.DealPunkt;
import com.example.object.MainMenu;

import com.example.object.Product;
import com.example.shopuchetonline.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DealListAdapter extends ArrayAdapter<DealPunkt> {

    private LayoutInflater inflater;
    private int layout;
    private List<DealPunkt> dealPunkts;

    public DealListAdapter(Context context, int resource, List<DealPunkt> dealPunkts) {
        super(context, resource, dealPunkts);
        this.dealPunkts = dealPunkts;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        ImageView imageView = view.findViewById(R.id.flag);
        TextView nameView = view.findViewById(R.id.name);
        TextView quantityView = view.findViewById(R.id.quantity);
        TextView priceView = view.findViewById(R.id.price);
        TextView costView = view.findViewById(R.id.cost);

        DealPunkt dealPunkt = dealPunkts.get(position);



        nameView.setText(dealPunkt.product.name);
        quantityView.setText(Double.toString(dealPunkt.quantity) );
        priceView.setText(Double.toString(dealPunkt.price) );
        costView.setText(Double.toString(dealPunkt.cost) );
        Picasso.get().load(dealPunkt.product.img).into(imageView);

        return view;
    }
}
