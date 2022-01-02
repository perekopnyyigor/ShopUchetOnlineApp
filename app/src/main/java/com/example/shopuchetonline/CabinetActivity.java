package com.example.shopuchetonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adapters.MainMenuAdapter;
import com.example.object.MainMenu;
import com.example.object.Shop;

import java.util.ArrayList;

public class CabinetActivity extends AppCompatActivity {

    public TextView editTextName;
    ListView countriesList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabinet);

        editTextName = findViewById(R.id.name);
        editTextName.setText(Shop.data.name);

        makeList();
        selectMenu();
    }


    private void makeList()
    {

        ArrayList<MainMenu> mainMenu = ini();
        // получаем элемент ListView
        countriesList = findViewById(R.id.countriesList);

        // создаем адаптер
        MainMenuAdapter mainMenuAdapter = new MainMenuAdapter(this, R.layout.list_menu, mainMenu);

        // устанавливаем для списка адаптер
        countriesList.setAdapter(mainMenuAdapter);


    }
    private ArrayList<MainMenu> ini()
    {
        ArrayList<MainMenu> mainMenu = new ArrayList<>();
        mainMenu.add(new MainMenu("Продажа",R.drawable.sell));
        mainMenu.add(new MainMenu("Закуп",R.drawable.bue));
        mainMenu.add(new MainMenu("Товар",R.drawable.produkt));
        mainMenu.add(new MainMenu("Категории",R.drawable.category));
        mainMenu.add(new MainMenu("Склад",R.drawable.warehouse));
        mainMenu.add(new MainMenu("Отчет",R.drawable.warehouse));
        return mainMenu;
    }
    private void selectMenu()
    {

        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent;
                switch (position)
                {
                    case 0:
                        intent = new Intent(CabinetActivity.this, DealActivity.class);
                        intent.putExtra("type","sell");
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(CabinetActivity.this, DealActivity.class);
                        intent.putExtra("type","bye");
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(CabinetActivity.this, ProductActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(CabinetActivity.this, WarehousActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(CabinetActivity.this, DealListActivity.class);
                        startActivity(intent);
                        break;
                        
                }
            }
        };
        countriesList.setOnItemClickListener(itemListener);
    }
}