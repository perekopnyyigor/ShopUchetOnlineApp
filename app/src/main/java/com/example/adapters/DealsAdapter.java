package com.example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.object.Deal;
import com.example.object.DealPunkt;
import com.example.shopuchetonline.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DealsAdapter extends ArrayAdapter<Deal> {

    private LayoutInflater inflater;
    private int layout;
    private List<Deal> deals;

    public DealsAdapter(Context context, int resource, List<Deal> deals) {
        super(context, resource, deals);
        this.deals = deals;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);


        TextView dateView = view.findViewById(R.id.date);
        TextView typeView = view.findViewById(R.id.type);
        TextView sellView = view.findViewById(R.id.sum);


        Deal deal = deals.get(position);



        dateView.setText(deal.date);
        typeView.setText(deal.type );
        sellView.setText(Double.toString(deal.sum) );


        return view;
    }
}

