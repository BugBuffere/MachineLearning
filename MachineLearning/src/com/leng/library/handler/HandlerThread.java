package com.leng.library.handler;

public class HandlerThread extends Thread {
	
	int mPriority;
    long mTid = -1;
    Looper mLooper;

    public HandlerThread(String name) {
        super(name);
        mPriority = 5;
    }
    
    public HandlerThread(String name, int priority) {
        super(name);
        mPriority = priority;
    }
    
    @Override
    public void run() {
    	mTid = getId();
        Looper.prepare();
        synchronized (this) {
            mLooper = Looper.myLooper();
            notifyAll();
        }
        this.setPriority(mPriority);
        onLooperPrepared();
        Looper.loop();
        mTid = -1;
    }
    
    public Looper getLooper() {
        if (!isAlive()) {
            return null;
        }
        
        // If the thread has been started, wait until the looper has been created.
        synchronized (this) {
            while (isAlive() && mLooper == null) {
                try {
                    wait();
                } catch (InterruptedException e) {
                	e.printStackTrace();
                }
            }
        }
        return mLooper;
    }
    
    public boolean quit() {
        Looper looper = getLooper();
        if (looper != null) {
            looper.quit();
            return true;
        }
        return false;
    }
    
    public boolean quitSafely() {
        Looper looper = getLooper();
        if (looper != null) {
            looper.quitSafely();
            return true;
        }
        return false;
    }
    
    public long getThreadId() {
        return mTid;
    }

	protected void onLooperPrepared() {
		
	}

}
