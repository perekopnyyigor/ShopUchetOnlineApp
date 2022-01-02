package com.example.functional;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapters.DealListAdapter;
import com.example.adapters.DealsAdapter;
import com.example.adapters.ProductAdapter;
import com.example.object.Deal;
import com.example.object.Product;
import com.example.object.Shop;
import com.example.shopuchetonline.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Deals {
    public ListView dealList;
    public Activity activity;



    public Deals(Activity activity, ListView dealList )
    {
        this.activity=activity;
        this.dealList=dealList;

    }
    public  ArrayList<Deal> ini(String server_message)
    {
        ArrayList<Deal> deals =new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(server_message);
            for(int i=0;i<jsonArray.length();i++)
                deals.add(new Deal(jsonArray.getJSONObject(i)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return deals;
    }
    public void makeList(ArrayList<Deal> list)
    {

        // создаем адаптер
        DealsAdapter dealsAdapter = new DealsAdapter(activity, R.layout.list_deals, list);

        // устанавливаем для списка адаптер
        dealList.setAdapter(dealsAdapter);
    }

}
