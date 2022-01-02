package com.example.functional;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import com.example.adapters.ProductAdapter;
import com.example.object.Product;
import com.example.object.Shop;
import com.example.shopuchetonline.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ProductList {
    public EditText editText;
    public ListView productsList;
    public Activity activity;
    public ArrayList<Product> products =new ArrayList<>();


    public ProductList(EditText editText, ListView productsList, Activity activity)
    {
        this.editText = editText;
        this.productsList = productsList;
        this.activity=activity;
    }
    public void changeEditText()
    {
        editText.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                String strCatName = editText.getText().toString();
                makeList(strCatName);




            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }
    public void makeList(String strCatName)
    {
        Shop.findProduct(strCatName);

        products = ini(Shop.mess);

        // создаем адаптер
        ProductAdapter productAdapter = new ProductAdapter(activity, R.layout.list_product, products);

        // устанавливаем для списка адаптер
        productsList.setAdapter(productAdapter);
    }

    public static ArrayList<Product> ini(String server_message)
    {
        ArrayList<Product> products =new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(server_message);
            for(int i=0;i<jsonArray.length();i++)
                products.add(new Product(jsonArray.getJSONObject(i)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return products;
    }
}
