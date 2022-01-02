package com.example.shopuchetonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapters.MainMenuAdapter;
import com.example.functional.Img;
import com.example.object.MainMenu;
import com.example.object.Product;
import com.example.object.Shop;
import com.example.scan.IntentIntegrator;
import com.example.scan.IntentResult;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    public ListView list;
    private String name;
    private String code;
    private double price1;
    private double price2;
    private String image;
    private Img img = new Img(this);
    private ImageView picView ;

    private static final int CAMERA_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        picView = findViewById(R.id.Photo);
        makeList();
        selectMenu();
    }

    public void addProduct(View view) {

        EditText editTextName = findViewById(R.id.name);
        name = editTextName.getText().toString();

        EditText editTextPrice1 = findViewById(R.id.price1);
        price1 = Double.parseDouble(editTextPrice1.getText().toString());

        EditText editTextPrice2 = findViewById(R.id.price2);
        price2 = Double.parseDouble(editTextPrice2.getText().toString());

        EditText editTextCode = findViewById(R.id.code);
        code = editTextCode.getText().toString();
        Product.add(name, code, price1, price2, Shop.data.id, img.file);

        Toast toast = Toast.makeText(this, Product.message, Toast.LENGTH_LONG);
        toast.show();


    }

    private void makeList() {

        ArrayList<MainMenu> mainMenu = ini();
        // получаем элемент ListView
        list = findViewById(R.id.list);

        // создаем адаптер
        MainMenuAdapter mainMenuAdapter = new MainMenuAdapter(this, R.layout.list_menu, mainMenu);

        // устанавливаем для списка адаптер
        list.setAdapter(mainMenuAdapter);


    }

    private ArrayList<MainMenu> ini() {
        ArrayList<MainMenu> mainMenu = new ArrayList<>();
        mainMenu.add(new MainMenu("Штрихкод", R.drawable.code));
        mainMenu.add(new MainMenu("Фото", R.drawable.code));
        return mainMenu;
    }

    private void selectMenu() {

        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                switch (position) {
                    case 0:
                        IntentIntegrator scanIntegrator = new IntentIntegrator(ProductActivity.this);
                        scanIntegrator.initiateScan();
                        break;
                    case 1:
                        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(captureIntent, CAMERA_REQUEST);
                        break;

                }
            }
        };
        list.setOnItemClickListener(itemListener);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//retrieve scan result
        super.onActivityResult(requestCode, resultCode, intent);

        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            EditText editText = findViewById(R.id.code);
            editText.setText(scanContent);
        }

        if (requestCode == CAMERA_REQUEST) {

            image = img.result(intent, picView);


        }

    }
}