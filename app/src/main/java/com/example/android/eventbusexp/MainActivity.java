package com.example.android.eventbusexp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.os.SystemClock;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.eventbusexp.events.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;
    MessageEvent messageEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);


        button.setOnClickListener(view -> {

            String message = "EVENT started...";
            messageEvent = new MessageEvent(message);
            EventBus.getDefault().post(messageEvent);
        });
    }

    @Override
    protected void onStart() {
        EventBus.getDefault().register(MainActivity.this);
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(MainActivity.this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent){
        String s = messageEvent.getMessage();
        textView.setText(s+"\n");
    }

}