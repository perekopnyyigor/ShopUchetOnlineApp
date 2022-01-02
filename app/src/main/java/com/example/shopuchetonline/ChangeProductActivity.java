package com.example.shopuchetonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChangeProductActivity extends AppCompatActivity {
    public String id;
    public EditText editTextName;
    public EditText editTextPrice1;
    public EditText editTextPrice2;
    public EditText editTextCode;
    public Product product;
    public ImageView picView ;
    public ListView list;
    private String image;
    private Img img = new Img(this);
    private static final int CAMERA_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_product);
        Bundle arg = getIntent().getExtras();
        id = arg.getString("id");

        editTextName = findViewById(R.id.name);
        editTextPrice1 = findViewById(R.id.price1);
        editTextPrice2 = findViewById(R.id.price2);
        editTextCode = findViewById(R.id.code);
        picView = findViewById(R.id.Photo);

        product =  new Product(id);

        fillForm();
        makeList();
        selectMenu();

    }

    public void fillForm() {
        editTextName.setText(product.name);
        editTextPrice1.setText(product.price1.toString());
        editTextPrice2.setText(product.price2.toString());
        editTextCode.setText(product.code);
        Picasso.get().load(product.img).into(picView);
    }
    public void changeProduct(View view) {


        product.name = editTextName.getText().toString();


        product.price1 = Double.parseDouble(editTextPrice1.getText().toString());
        product.price2 = Double.parseDouble(editTextPrice2.getText().toString());

        product.code = editTextCode.getText().toString();
        product.change(img.file);
        Toast toast = Toast.makeText(this, product.messageChange, Toast.LENGTH_LONG);
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
                        IntentIntegrator scanIntegrator = new IntentIntegrator(ChangeProductActivity.this);
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