package com.example.shopuchetonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.object.Shop;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {
    public Shop shop;

    EditText editTextBin;
    EditText editTextPassword;

    String bin;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextBin = findViewById(R.id.bin);
        editTextPassword = findViewById(R.id.password);
    }
    public void reg(View view)
    {
        Intent intent= new Intent(this,RegActivity.class);
        startActivity(intent);
    }
    public void enter(View view)
    {

        bin = editTextBin.getText().toString();


        password = editTextPassword.getText().toString();

        Shop.data = new Shop(bin,password);

        if(Shop.data.message==null)
        {
            Toast toast = Toast.makeText(this,"Нет соединения с интернетом",Toast.LENGTH_LONG);
            toast.show();
        }
        else if(Shop.data.name==null)
        {
            Toast toast = Toast.makeText(this,Shop.data.id,Toast.LENGTH_LONG);
            toast.show();
        }
        else
        {


            Intent intent = new Intent(this, CabinetActivity.class);
            startActivity(intent);
        }


    }
}