package com.example.object;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Shop {
    public static Shop data;
    public static String mess;


    public  String name;
    public  String password;
    public  String bin;
    public  String cashbox;
    public String message;
    public String id;




    public  Shop(String bin, String password)
    {
        this.bin = bin;
        this.password = password;

        Request request = ini_request();
        request(request);

        Map<String, String> shopData = jsonParse(message);

        this.name=shopData.get("name");

        this.cashbox = shopData.get("cashbox");
        this.id = shopData.get("id");



    }
    public static void reg(String name, String bin, String password1, String password2)
    {
        mess=null;
        RequestBody requestBody = new FormBody.Builder()
                .add("name", name)
                .add("bin", bin)
                .add("password1", password1)
                .add("password2", password2)
                .build();


        Request request= new Request.Builder()
                .url("https://shop.tiwy.ru/index.php?action=reg_action")
                .post(requestBody)
                .build();

        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                mess =e.getMessage();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                mess = response.body().string();

            }

        });
        while (mess ==null) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    public static void findProduct(String product)
    {
        mess=null;
        RequestBody requestBody = new FormBody.Builder()
                .add("find_product", product)
                .add("shop_id", Shop.data.id)
                .build();


        Request request= new Request.Builder()
                .url("https://shop.tiwy.ru/index.php?action=find_product")
                .post(requestBody)
                .build();

        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                mess =e.getMessage();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                mess = response.body().string();

            }

        });
        while (mess ==null) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
    private Request ini_request()
    {
        RequestBody requestBody = new FormBody.Builder()
                .add("bin", bin)
                .add("password", password)
                .build();


        Request request= new Request.Builder()
                .url("https://shop.tiwy.ru/index.php?action=enter_mob")
                .post(requestBody)
                .build();
        return request;
    }
    private void request(Request request)
    {

        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                message =e.getMessage();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                message = response.body().string();

            }

        });
        while (message==null) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    private Map jsonParse(String message)
    {
        Map<String, String> shopData = new HashMap<>();

        try {
            JSONObject obj = new JSONObject(message);
            shopData.put("name",obj.getString("name"));
            shopData.put("cashbox",obj.getString("cashbox"));
            shopData.put("id",obj.getString("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return shopData;
    }





}
