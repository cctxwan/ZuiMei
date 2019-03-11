package com.cc.zuimei.test.handler;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HandlerThreadActivity extends AppCompatActivity {

    //定义handler
    Handler handler;

    //定义handlerthread
    HandlerThread handlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("我是HandlerThreadActivity");
        setContentView(textView);

        //实现一个HandlerThead，并起名为caicai，与定义的handler进行绑定
        handlerThread = new HandlerThread("caicai");
        handlerThread.start();

        //使用handlerthread的looper
        handler = new Handler(handlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                System.out.println("Log----->" + Thread.currentThread());
            }
        };

        //发送消息给handler
        handler.sendEmptyMessage(1);
    }
}
