package com.example.shopuchetonline;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.object.Shop;

import java.util.ArrayList;

public class RegActivity extends AppCompatActivity {
    public ArrayList<EditText> editText = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        editText.add( findViewById(R.id.name));
        editText.add( findViewById(R.id.bin));
        editText.add(findViewById(R.id.password1));
        editText.add(findViewById(R.id.password2));
    }
    public void reg(View view)
    {
        Shop.reg(
                editText.get(0).getText().toString(),
                editText.get(1).getText().toString(),
                editText.get(2).getText().toString(),
                editText.get(3).getText().toString()
        );
        Toast.makeText(this,Shop.mess,Toast.LENGTH_LONG).show();
    }
}