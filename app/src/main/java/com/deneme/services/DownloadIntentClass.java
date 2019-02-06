package com.deneme.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadIntentClass extends IntentService {

    private static final String TAG = "DownloadIntentClass";
    
    public DownloadIntentClass() {
        super("Download Class");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String currentThread=Thread.currentThread().getName();

        String urlInput=intent.getStringExtra("download");
        ResultReceiver resultReceiver=intent.getParcelableExtra("receiver");

        String result="";
        URL url;
        HttpURLConnection httpURLConnection=null;

        try {

            url=new URL(urlInput);
            httpURLConnection=(HttpURLConnection)url.openConnection();

            InputStream inputStream=httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);

            int data=inputStreamReader.read();

            while (data!=-1){
                char current=(char)data;
                result+=current;

                data=inputStreamReader.read();
            }

            Bundle bundle=new Bundle();
            bundle.putString("websiteResult",result);
            resultReceiver.send(1,bundle);


        }
        catch (Exception e){
            Bundle bundle=new Bundle();
            bundle.putString("websiteResult","Failed");
            resultReceiver.send(1,bundle);
        }


        Log.d(TAG, "onHandleIntent: "+currentThread);

    }

    @Override
    public void onCreate() {
        String currentThread=Thread.currentThread().getName();
        Log.d(TAG, "onCreate: "+currentThread);
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        String currentThread=Thread.currentThread().getName();
        Log.d(TAG, "onDestroy: "+currentThread);
        super.onDestroy();
    }
}
