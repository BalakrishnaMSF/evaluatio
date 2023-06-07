package com.example.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter= new IntentFilter("android.intent.action.BATTERY_LOW");
        MyReceiver objReceiver = new MyReceiver();
        registerReceiver(objReceiver,intentFilter);

    }

//
//   public void broadcastIntent(View view){
//        Intent intent = new Intent();
//        intent.setAction("android.intent.action.BATTERY_LOW");
//        sendBroadcast(intent);
//    }
}