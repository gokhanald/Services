package com.deneme.services;

import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StickyServiceActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_service);
    }

    public void start(View view){
        Intent intent=new Intent(this,StickyServiceClass.class);
        startService(intent);


    }

    public void stop(View view){
        Intent intent=new Intent(this,StickyServiceClass.class);
        stopService(intent);
    }
}
