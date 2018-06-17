package com.minoru.minoru.spajam2018;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpSingleton {
    private static final OkHttpSingleton okHttpSingleton = new OkHttpSingleton();
    private String url = "192.168.1.1";

    private OkHttpSingleton(){

    }
    public static OkHttpSingleton getInstance(){
        return okHttpSingleton;
    }

    public void setUrl(String argUrl){
        url=argUrl;
    }

    public void post(final String music){
        final String endpoint = "http://"+url+":3000";
        new AsyncTask<Void,Void,Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                OkHttpClient client = new OkHttpClient();
                MediaType jsonMedia = MediaType.parse("application/json; charset=utf-8");
                Request request = new Request.Builder()
                        .url(endpoint)
                        .post(RequestBody.create(jsonMedia, "{\"id\": \"value\",\"name\": \""+music+"\"}"))
                        .build();
                Log.d("HTTP:",endpoint);
                try {
                    Response response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
}
