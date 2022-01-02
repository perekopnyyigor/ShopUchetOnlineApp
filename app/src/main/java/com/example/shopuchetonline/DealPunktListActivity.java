package com.example.shopuchetonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapters.DealListAdapter;
import com.example.functional.DealList;
import com.example.object.Deal;
import com.example.object.DealPunkt;

import java.util.ArrayList;

public class DealPunktListActivity extends AppCompatActivity {
    public int id;
    public ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_punkt_list);
        Bundle arg = getIntent().getExtras();
        id = arg.getInt("id");

        DealPunkt.findDealPunkt(id);



        listView = findViewById(R.id.DealPunktList);
        DealList dealList = new DealList(listView,this);

        ArrayList<DealPunkt> deals=dealList.ini(DealPunkt.message);

        // создаем адаптер
        DealListAdapter dealListAdapter = new DealListAdapter(this, R.layout.list_deal, deals);

        // устанавливаем для списка адаптер
        listView.setAdapter(dealListAdapter);





    }
}