package com.example.functional;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import com.example.adapters.DealListAdapter;
import com.example.adapters.ProductAdapter;
import com.example.object.Deal;
import com.example.object.DealPunkt;
import com.example.object.Product;
import com.example.object.Shop;
import com.example.shopuchetonline.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class DealList {
    public EditText editText;
    public ListView dealList;
    public Activity activity;
    public ArrayList<DealPunkt> products =new ArrayList<>();

    public DealList(ListView dealList, Activity activity)
    {

        this.dealList = dealList;
        this.activity=activity;
    }

    public void makeList(ArrayList<DealPunkt> list)
    {


        // создаем адаптер
        DealListAdapter dealListAdapter = new DealListAdapter(activity, R.layout.list_deal, list);

        // устанавливаем для списка адаптер
        dealList.setAdapter(dealListAdapter);


    }
    public  ArrayList<DealPunkt> ini(String server_message)
    {
        ArrayList<DealPunkt> deals =new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(server_message);
            for(int i=0;i<jsonArray.length();i++)
            {
                Product product = new Product(jsonArray.getJSONObject(i).getJSONObject("product"));
                deals.add(new DealPunkt(jsonArray.getJSONObject(i).getJSONObject("punkt"), product));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return deals;
    }



}