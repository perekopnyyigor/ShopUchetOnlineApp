package com.example.shopuchetonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapters.ProductAdapter;
import com.example.functional.ProductList;
import com.example.object.Product;
import com.example.object.Shop;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class WarehousActivity extends AppCompatActivity {

    public ListView productsList;
    public EditText editText ;
    public ProductList productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehous);




        editText=findViewById(R.id.name);
        productsList = findViewById(R.id.productsList);

        productList = new ProductList(editText,productsList,this);
        productList.makeList("");
        productList.changeEditText();
        selectMenu();


    }
    private void selectMenu()
    {

        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
             Intent intent = new Intent(WarehousActivity.this,ChangeProductActivity.class);
             intent.putExtra("id",productList.products.get(position).id);
             startActivity(intent);

            }
        };
        productsList.setOnItemClickListener(itemListener);
    }

}