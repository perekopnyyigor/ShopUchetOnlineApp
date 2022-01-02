package com.example.shopuchetonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.object.DealPunkt;
import com.example.object.Product;
import com.squareup.picasso.Picasso;

public class DealPunktActivity extends AppCompatActivity {
    public Product product;
    public TextView textViewName;
    public TextView textViewCode;
    public EditText editTextPrice;
    public EditText editTextDiscount;
    public EditText editTextQuantity;
    public ImageView imageView;
    public String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_punkt);
        Bundle arguments = getIntent().getExtras();

        product = (Product)arguments.getSerializable("product");
        type = arguments.getString("type");

        textViewName = findViewById(R.id.name);
        textViewCode = findViewById(R.id.code);
        editTextPrice = findViewById(R.id.price);
        editTextDiscount = findViewById(R.id.discont);
        editTextQuantity = findViewById(R.id.quantity);
        imageView = findViewById(R.id.Photo);
        fill_form();

    }
    public void fill_form()
    {
        textViewName.setText(product.name);
        textViewCode.setText(product.code);
        if(type.equals("bye"))
            editTextPrice.setText(Double.toString(product.price1));
        if(type.equals("sell"))
            editTextPrice.setText(Double.toString(product.price2));

        Picasso.get().load(product.img).into(imageView);
    }
    public void add(View view)
    {
        Double discount =Double.parseDouble(editTextDiscount.getText().toString());
        Double quantity =Double.parseDouble(editTextQuantity.getText().toString());
        DealPunkt dealPunkt = new DealPunkt(quantity,discount,product);
        Intent intent = new Intent(this,DealActivity.class);
        intent.putExtra("dealPunkt",dealPunkt);
        setResult(RESULT_OK, intent);
        finish();

    }
}