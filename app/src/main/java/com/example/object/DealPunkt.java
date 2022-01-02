package com.example.object;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DealPunkt implements Serializable {
    public static String message;
    public Product product;
    public Double quantity;
    public Double discount;
    public Double price;
    public Double cost;

    public static void findDealPunkt(int id)
    {
        message=null;
        RequestBody requestBody = new FormBody.Builder()
                .add("deal_id", Integer.toString(id))
                .build();


        Request request= new Request.Builder()
                .url("https://shop.tiwy.ru/index.php?action=find_deal_punkt")
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
    public  DealPunkt(Double quantity, Double discount, Product product)
    {
        this.discount=discount;
        this.product=product;
        this.quantity=quantity;
        this.price=product.price2-product.price2/100*this.discount;
        this.cost = this.price*this.quantity;
    }
    public String JsonEncode()
    {
        String message="{";
        message+="\"product\":\""+product.id+"\",";
        message+="\"quantity\":\""+ quantity +"\",";
        message+="\"discount\":\""+ discount +"\",";
        message+="\"price\":\""+ price +"\",";
        message+="\"cost\":\""+ cost +"\"}";
        return message;

    }
    public DealPunkt(JSONObject obj, Product product)
    {
        this.product = product;
        try {
            quantity=obj.getDouble("quantity");
            discount=obj.getDouble("discount");
            price=obj.getDouble("price");
            cost=this.price*this.quantity;


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
