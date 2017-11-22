package com.leng.library.handler;




public class Looper {
	
	static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<Looper>();
	
	MessageQueue mQueue;
	
	Thread mThread;
	
	private Looper(boolean quitAllowed){
		mQueue = new MessageQueue(quitAllowed);
		mThread = Thread.currentThread();
	}
	
	public static void prepare(){
		prepare(true);
	}
	
	private static void prepare(boolean quitAllowed) {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new Looper(quitAllowed));
    }
	
	public static void loop(){
		final Looper me = myLooper();
        if (me == null) {
            throw new RuntimeException("No Looper; Looper.prepare() wasn't called on this thread.");
        }
        MessageQueue queue = me.mQueue;
        for (;;) {
			Message msg = queue.next();
			if (msg == null) {
				return;
			}
			msg.target.dispatchMessage(msg);
			msg.recycleUnchecked();
		}
	}
	
	public static Looper myLooper(){
		return sThreadLocal.get();
	}
	
	public static MessageQueue myQueue(){
		return myLooper().mQueue;
	}
	
	public void quit(){
		mQueue.quit(false);
	}
	
	public void quitSafely() {
        mQueue.quit(true);
    }
	
	public Thread getThread(){
		return mThread;
	}
	
	public MessageQueue getQueue(){
		return mQueue;
	}
	
	public boolean isCurrentThread() {
        return Thread.currentThread() == mThread;
    }
	
	@Override
    public String toString() {
        return "Looper (" + mThread.getName() + ", tid " + mThread.getId()
                + ") {" + Integer.toHexString(System.identityHashCode(this)) + "}";
    }

}
