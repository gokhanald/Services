package com.deneme.services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startService(View view){
        Intent intent=new Intent(getApplicationContext(),ServiceClass.class);
        startService(intent);

    }

    public void stopService(View view){
        Intent intent=new Intent(getApplicationContext(),ServiceClass.class);
        stopService(intent);
    }

    public void startIntent(View view){
        Intent intent=new Intent(getApplicationContext(),IntentServices.class);
        startActivity(intent);
    }

    public void stickyService(View view){
        Intent intent=new Intent(getApplicationContext(),StickyServiceActivity.class);
        startActivity(intent);
    }
}
