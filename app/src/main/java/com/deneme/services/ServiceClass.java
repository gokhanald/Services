package com.deneme.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;


public class ServiceClass extends Service {

    public static final String TAG="ServiceClass";
    @Nullable
    @Override
    public IBinder onBind(Intent ıntent) {
        return null;
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

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String currentThread=Thread.currentThread().getName();
        Log.d(TAG, "onStartCommand: "+currentThread);

        AsyncTaskingClass asyncTask=new AsyncTaskingClass();
        try {
            asyncTask.execute("http://atilsamancioglu.com/").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    class AsyncTaskingClass extends AsyncTask<String,Void,String>{

        private static final String TAG = "AsyncTaskingClass";
        

        @Override
        protected String doInBackground(String... strings) {

            String result="";
            URL url;
            HttpURLConnection httpURLConnection=null;

            try {

                url=new URL(strings[0]);
                httpURLConnection=(HttpURLConnection)url.openConnection();

                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);

                int data=inputStreamReader.read();

                while (data!=-1){
                    char current=(char)data;
                    result+=current;

                    data=inputStreamReader.read();
                }

                return result;

            }
            catch (Exception e){
                return "Failed";
            }


        }

        @Override
        protected void onPreExecute() {
           /* String currentThread=Thread.currentThread().getName();
            Log.d(TAG, "onPreExecute: "+currentThread);*/

            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            System.out.println("Result: "+ s);
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
           /* String currentThread=Thread.currentThread().getName();
            Log.d(TAG, "onProgressUpdate: "+currentThread);*/
            super.onProgressUpdate(values);
        }
    }
}
