package com.leng.main.test;

import com.leng.library.handler.Handler;
import com.leng.library.handler.HandlerThread;
import com.leng.library.handler.Message;

public class TestHandler {
	
	public static void main(String[] args) {
		testHandler();
	}
	
	public static void testHandler(){
		Handler handler1 = TestHandler.test1();
		Handler handler2 = TestHandler.test2();
		handler1.obtainMessage(123).sendToTarget();
		handler2.obtainMessage(456).sendToTarget();
		handler1.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "回调");
			}
		}, 1000);
		
		handler2.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "回调");
			}
		}, 2000);
	}
	
	public static Handler test1(){
		HandlerThread mThread = new HandlerThread("test1");
		mThread.start();
		Handler handler = new Handler(mThread.getLooper()){
			@Override
			public void handleMessage(Message msg) {
				System.out.println(Thread.currentThread().getName() + "收到消息::" + msg.what);
			}
		};
		return handler;
	}
	
	public static Handler test2(){
		HandlerThread mThread = new HandlerThread("test2");
		mThread.start();
		Handler handler = new Handler(mThread.getLooper()){
			@Override
			public void handleMessage(Message msg) {
				System.out.println(Thread.currentThread().getName() + "收到消息::" + msg.what);
			}
		};
		return handler;
	}

}
