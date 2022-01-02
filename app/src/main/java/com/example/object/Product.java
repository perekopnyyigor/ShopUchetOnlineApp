package com.example.object;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Product implements Serializable {
    public String id;
    public String name;
    public String code;
    public Double price1;
    public Double price2;

    public String img;
    public Double quantity;

    public String messageProduct;
    public String messageChange;
    public static String message;
    public static void add(String name, String code, Double price1, Double price2, String shop, File file)
    {
        Request requestBody = ini_request_add(name, code, price1, price2, shop, file);
        request_add(requestBody);

    }
    private static Request ini_request_add(String name, String code, Double price1, Double price2, String shop, File file)
    {
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("name", name)
                .addFormDataPart("code", code)
                .addFormDataPart("price1", Double.toString(price1))
                .addFormDataPart("price2", Double.toString(price2))
                .addFormDataPart("shop", shop)
                .addFormDataPart("file[]",file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file))

                .build();

        Request request= new Request.Builder()
                .url("https://shop.tiwy.ru/index.php?action=add_product_mob")
                .post(requestBody)
                .build();

        return request;
    }
    private static void request_add(Request request)
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
    public Product(String id)
    {
        this.id = id;
        Request request = ini_request();
        request(request);
        Map<String, String> shopData = jsonParse(messageProduct);

        this.name=shopData.get("name");
        code =shopData.get("code");
        img ="https://shop.tiwy.ru/"+shopData.get("img");
        price1 = Double.parseDouble(shopData.get("price1"));
        price2 = Double.parseDouble(shopData.get("price2"));
        quantity = Double.parseDouble(shopData.get("quantity"));


    }
    public Product(JSONObject obj)
    {
        try {
            id=obj.getString("id");
            name=obj.getString("name");
            code=obj.getString("code");
            img="https://shop.tiwy.ru/"+obj.getString("img");
            price1 = Double.parseDouble(obj.getString("price1"));
            price2 = Double.parseDouble(obj.getString("price2"));
            quantity = Double.parseDouble(obj.getString("quantity"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private Request ini_request()
    {
        RequestBody requestBody = new FormBody.Builder()
                .add("id", id)
                .build();


        Request request= new Request.Builder()
                .url("https://shop.tiwy.ru/index.php?action=product_mob")
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
                messageProduct =e.getMessage();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                messageProduct = response.body().string();

            }

        });
        while (messageProduct==null) {
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
            shopData.put("price1",obj.getString("price1"));
            shopData.put("price2",obj.getString("price2"));
            shopData.put("code",obj.getString("code"));
            shopData.put("quantity",obj.getString("quantity"));
            shopData.put("img",obj.getString("img"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return shopData;
    }

    public   void change(File file)
    {
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("id", id)
                .addFormDataPart("name", name)
                .addFormDataPart("code", code)
                .addFormDataPart("price1", Double.toString(price1))
                .addFormDataPart("price2", Double.toString(price2))
                .addFormDataPart("file[]",file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file))

                .build();

        Request request= new Request.Builder()
                .url("https://shop.tiwy.ru/index.php?action=update_product")
                .post(requestBody)
                .build();

        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                messageChange =e.getMessage();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                messageChange = response.body().string();

            }

        });
        while (messageChange==null) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }

}
