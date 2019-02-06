package com.deneme.services;

import android.content.Intent;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class IntentServices extends AppCompatActivity {

    EditText EdittextUrl;
    TextView textContent;
    Handler handler=new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_services);

        EdittextUrl=findViewById(R.id.editTextUrl);
        textContent=findViewById(R.id.textViewContent);
    }

    public void download(View view){

        ResultReceiver myResultReceiver=new MyResultReceiver(null);

        Intent intent=new Intent(this,DownloadIntentClass.class);
        String userInput=EdittextUrl.getText().toString();
        intent.putExtra("download",userInput);
        intent.putExtra("receiver",myResultReceiver);
        textContent.setText("Downloading");
        startService(intent);
    }


    public class MyResultReceiver extends ResultReceiver{

        public MyResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, final Bundle resultData) {

            if (resultCode==1&&resultData!=null){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String result=resultData.getString("websiteResult");
                        textContent.setText(result);
                    }
                });

            }

            super.onReceiveResult(resultCode, resultData);
        }
    }
}
