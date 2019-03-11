//package com.cc.zuimei.test.handler
//
//import android.os.Handler
//import android.os.HandlerThread
//import android.os.Message
//import android.support.v7.app.AppCompatActivity
//import android.os.Bundle
//import android.view.View
//import android.widget.Button
//
//import cc.com.zuimei.R
//
//class HandlerThread2Activity : AppCompatActivity(), View.OnClickListener {
//
//    //定义两个按钮用来发送和停止消息
//    internal var handlerthread_bt1: Button
//    internal var handlerthread_bt2: Button
//
//    /**
//     * 定义一个主线程的handler
//     */
//    internal var handler: Handler = object : Handler() {
//        override fun handleMessage(msg: Message) {
//            super.handleMessage(msg)
//            val message = this.obtainMessage()
//
//            //向子线程发送一个消息
//            threadHandler.sendMessageDelayed(message, 1000)
//
//            println("主线程----->" + Thread.currentThread())
//        }
//    }
//
//
//    //作为子线程去处理handler
//    internal var handlerThread: HandlerThread
//    internal var threadHandler: Handler
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_handler_thread2)
//
//        //实例化按钮
//        handlerthread_bt1 = findViewById(R.id.handlerthread_bt1)
//        handlerthread_bt2 = findViewById(R.id.handlerthread_bt2)
//        handlerthread_bt1.setOnClickListener(this)
//        handlerthread_bt2.setOnClickListener(this)
//
//
//        //创建一个子线程
//        handlerThread = HandlerThread("你最好看")
//        handlerThread.start()
//
//        //创建一个子线程的handler
//        threadHandler = object : Handler(handlerThread.looper) {
//            override fun handleMessage(msg: Message) {
//                super.handleMessage(msg)
//                val message = threadHandler.obtainMessage()
//
//                //向主线程发送一个消息
//                handler.sendMessageDelayed(message, 1000)
//
//                println("子线程----->" + Thread.currentThread())
//            }
//        }
//
//    }
//
//    override fun onClick(v: View) {
//        val temdId = v.id
//        if (temdId == R.id.handlerthread_bt1) {
//            //开始发送
//            handler.sendEmptyMessage(1)
//        } else if (temdId == R.id.handlerthread_bt2) {
//            //任务停止
//            handler.removeMessages(0)
//        }
//    }
//}
