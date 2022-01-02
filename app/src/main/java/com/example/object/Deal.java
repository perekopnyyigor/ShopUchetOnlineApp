package com.example.object;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Deal {
    public static String message;
    public String mess;
    public ArrayList<DealPunkt> dealPunkts = new ArrayList<>();
    public Double sum;
    public String type;
    public String date;
    public int id;

//найти все сделки
    public static void findDeal()
    {
        message=null;
        RequestBody requestBody = new FormBody.Builder()
                .add("shop_id", Shop.data.id)
                .build();


        Request request= new Request.Builder()
                .url("https://shop.tiwy.ru/index.php?action=find_deal")
                .post(requestBody)
                .build();

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
        while (message ==null) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
    public Deal(String type)
    {
        this.type=type;
    }

    //конструктор
    public Deal(JSONObject obj)
    {
        try {
            id=obj.getInt("id");
            sum=obj.getDouble("sum");
            type=obj.getString("type");
            date=obj.getString("date");


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    //создать json из списка
    public String JsonEncode()
    {
        String message="{";
        message+="\"type\":\""+type+"\",";
        message+="\"sum\":\""+sum()+"\",";
        message+="\"shop\":\""+Shop.data.id+"\",";
        message+="\"dealPunkts\":[";
        for (int i =0;i<dealPunkts.size();i++)
        {
            if (i!=0)
                message+=",";

            message+=dealPunkts.get(i).JsonEncode();

        }
        message+="]}";
        return message;

    }
    //сумма сделки
    public Double sum()
    {
        Double summ=0.0;
        for (int i =0;i<dealPunkts.size();i++)
        {
            summ+=dealPunkts.get(i).cost;
        }
        return summ;
    }
    //отправить список на сервер
    public  void request(String list)
    {
        mess=null;
        RequestBody requestBody = new FormBody.Builder()
                .add("list", list)

                .build();


        Request request= new Request.Builder()
                .url("https://shop.tiwy.ru/index.php?action=add_list")
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


}
