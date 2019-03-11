package com.cc.zuimei.test.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cc.com.zuimei.R;

/**
 * Android多线程----异步消息处理机制之Handler详解
 * @author
 */
public class HandlerActivity extends AppCompatActivity {

//    TextView handler_txt1;
//
//    Button handler_bt1;

    /**
     * 主线程handler
     */
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            System.out.println("UI----->" + Thread.currentThread());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("我是界面");
        setContentView(textView);

        myThread = new MyThread();
        myThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread.handler.sendEmptyMessage(1);
        handler.sendEmptyMessage(1);
//        handler_bt1 = findViewById(R.id.handler_bt1);
//        handler_txt1 = findViewById(R.id.handler_txt1);
//
//
//        handler_bt1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        //这里获取message时，建议通过handler.obtainMessage()或者handler.obtain()来获取
//                        //这样的话，可以由系统自己负责message的创建和销毁
//                        Message message = handler.obtainMessage();
//                        handler.sendMessage(message);
//                    }
//                }){}.start();
//            }
//        });
    }

//    /**
//     * 通过handler接收指令并修改
//     */
//    Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            handler_bt1.setText("修改完毕");
//            handler_txt1.setText("我变成了2");
//        }
//    };

    //首先定义线程
    MyThread myThread;

    /**
     * 创建一个MyThread线程
     */
    class MyThread extends Thread{
        //通过handler去打印一个ID
        Handler handler;
        @Override
        public void run() {
            //创建一个Looper，与handler绑定
            Looper.prepare();
            handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    System.out.println("Thread----->" + Thread.currentThread());
                }
            };
            //循环处理消息
            Looper.loop();
        }
    }

}