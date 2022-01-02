package com.example.shopuchetonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.functional.Deals;
import com.example.object.Deal;

import java.util.ArrayList;

public class DealListActivity extends AppCompatActivity {
    public ListView listView;
    public ArrayList<Deal> deals_array = new ArrayList<>();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_list);
        Deal.findDeal();
        listView = findViewById(R.id.dealList);


        Deals deals = new Deals(this,listView);
        deals_array=deals.ini(Deal.message);
        deals.makeList(deals_array);
        selectMenu();
    }
    private void selectMenu()
    {

        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            Intent intent = new Intent(DealListActivity.this,DealPunktListActivity.class);
            intent.putExtra("id",deals_array.get(position).id);
            startActivity(intent);




            }
        };
        listView.setOnItemClickListener(itemListener);
    }
}