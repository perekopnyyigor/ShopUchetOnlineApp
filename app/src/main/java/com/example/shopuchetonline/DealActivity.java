package com.example.shopuchetonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapters.DealListAdapter;
import com.example.adapters.ProductAdapter;
import com.example.functional.ProductList;
import com.example.object.Deal;
import com.example.object.DealPunkt;
import com.example.object.Product;
import com.example.object.Shop;

public class DealActivity extends AppCompatActivity {
    ListView productsList;
    EditText editText ;
    ProductList productList;
    public ListView dealList;
    public String type;
    public Deal deal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal);

        editText=findViewById(R.id.name);
        productsList = findViewById(R.id.productsList);
        dealList= findViewById(R.id.dealList);

        Bundle arguments = getIntent().getExtras();
        type = arguments.getString("type");
        deal = new Deal(type);

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


                Intent intent = new Intent(DealActivity.this, DealPunktActivity.class);
                intent.putExtra("product",productList.products.get(position));
                intent.putExtra("type",type);
                startActivityForResult(intent,100);


            }
        };
        productsList.setOnItemClickListener(itemListener);
    }
    public void add(View view)
    {
        String list = deal.JsonEncode();
        deal.request(list);
        Toast toast = Toast.makeText(this,deal.mess,Toast.LENGTH_LONG);
        toast.show();
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//retrieve scan result
        super.onActivityResult(requestCode, resultCode, intent);


        if (requestCode==100)
        {
            DealPunkt dealPunkt = (DealPunkt)intent.getSerializableExtra("dealPunkt");
            deal.dealPunkts.add(dealPunkt);
            // создаем адаптер
            DealListAdapter dealListAdapter = new DealListAdapter(this, R.layout.list_deal,  deal.dealPunkts);

            // устанавливаем для списка адаптер
            dealList.setAdapter(dealListAdapter);

            Toast toast = Toast.makeText(this,dealPunkt.product.name,Toast.LENGTH_SHORT);
            toast.show();
        }


    }

}