package com.example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.object.MainMenu;
import com.example.shopuchetonline.R;

import java.util.List;

public class MainMenuAdapter extends ArrayAdapter<MainMenu> {

    private LayoutInflater inflater;
    private int layout;
    private List<com.example.object.MainMenu> states;

    public MainMenuAdapter(Context context, int resource, List<com.example.object.MainMenu> states) {
        super(context, resource, states);
        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        ImageView flagView = view.findViewById(R.id.flag);
        TextView nameView = view.findViewById(R.id.name);


        com.example.object.MainMenu menu = states.get(position);

        flagView.setImageResource(menu.picture);
        nameView.setText(menu.name);


        return view;
    }
}
