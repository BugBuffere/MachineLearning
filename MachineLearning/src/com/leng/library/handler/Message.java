package com.leng.library.handler;

import com.leng.library.util.TimeUtils;


public class Message {
	
	static final int FLAG_IN_USE = 1 << 0;

    static final int FLAG_ASYNCHRONOUS = 1 << 1;

    static final int FLAGS_TO_CLEAR_ON_COPY_FROM = FLAG_IN_USE;
    
    private static final int MAX_POOL_SIZE = 50;
	
	int flags;
	
	Handler target;
	
	Message next;
	
	long when;
	
	Runnable callback;
	
	Bundle data;
	
	private static final Object sPoolSync = new Object();
    private static Message sPool;
	private static int sPoolSize = 0;
    
    public int what;
    public int arg1; 
    public int arg2;
    public Object obj;
    public int sendingUid = -1;

	private boolean gCheckRecycle = true;
	
	public static Message obtain() {
        synchronized (sPoolSync) {
            if (sPool != null) {
                Message m = sPool;
                sPool = m.next;
                m.next = null;
                m.flags = 0; // clear in-use flag
                sPoolSize--;
                return m;
            }
        }
        return new Message();
    }
	
	 public static Message obtain(Message orig) {
	        Message m = obtain();
	        m.what = orig.what;
	        m.arg1 = orig.arg1;
	        m.arg2 = orig.arg2;
	        m.obj = orig.obj;
	        m.sendingUid = orig.sendingUid;
	        if (orig.data != null) {
	            m.data = new Bundle(orig.data);
	        }
	        m.target = orig.target;
	        m.callback = orig.callback;

	        return m;
	}
	 
	public static Message obtain(Handler h) {
        Message m = obtain();
        m.target = h;

        return m;
	}
	
	public static Message obtain(Handler h, Runnable callback) {
        Message m = obtain();
        m.target = h;
        m.callback = callback;

        return m;
	}
	
	public static Message obtain(Handler h, int what) {
        Message m = obtain();
        m.target = h;
        m.what = what;

        return m;
    }
	
	public static Message obtain(Handler h, int what, Object obj) {
        Message m = obtain();
        m.target = h;
        m.what = what;
        m.obj = obj;

        return m;
    }
	
	public static Message obtain(Handler h, int what, int arg1, int arg2) {
        Message m = obtain();
        m.target = h;
        m.what = what;
        m.arg1 = arg1;
        m.arg2 = arg2;

        return m;
    }
	
	public static Message obtain(Handler h, int what, 
            int arg1, int arg2, Object obj) {
        Message m = obtain();
        m.target = h;
        m.what = what;
        m.arg1 = arg1;
        m.arg2 = arg2;
        m.obj = obj;

        return m;
    }

	public void recycleUnchecked() {
		flags = FLAG_IN_USE;
        what = 0;
        arg1 = 0;
        arg2 = 0;
        obj = null;
        sendingUid = -1;
        when = 0;
        target = null;
        callback = null;
        data = null;
        synchronized (sPoolSync) {
            if (sPoolSize < MAX_POOL_SIZE) {
                next = sPool;
                sPool = this;
                sPoolSize++;
            }
        }
	}

	public boolean isAsynchronous() {
		
		return (flags & FLAG_ASYNCHRONOUS) != 0;
	}
	
	public void setAsynchronous(boolean async) {
        if (async) {
            flags |= FLAG_ASYNCHRONOUS;
        } else {
            flags &= ~FLAG_ASYNCHRONOUS;
        }
    }
	
	boolean isInUse() {
        return ((flags & FLAG_IN_USE) == FLAG_IN_USE);
    }

	void markInUse() {
		flags |= FLAG_IN_USE;
	}
	
	public long getWhen() {
        return when;
    }
    
    public void setTarget(Handler target) {
        this.target = target;
    }
	
	public Handler getTarget() {
		return target;
	}
	
	public Runnable getCallback() {
        return callback;
    }
	
	public Bundle getData() {
        if (data == null) {
            data = new Bundle();
        }
        
        return data;
    }
	
	public void setData(Bundle data) {
        this.data = data;
    }
	
	public void sendToTarget() {
        target.sendMessage(this);
    }
	
	public Bundle peekData() {
        return data;
    }
	
	@Override
    public String toString() {
        return toString(System.currentTimeMillis());
    }

    String toString(long now) {
        StringBuilder b = new StringBuilder();
        b.append("{ when=");
        TimeUtils.formatDuration(when - now, b);

        if (target != null) {
            if (callback != null) {
                b.append(" callback=");
                b.append(callback.getClass().getName());
            } else {
                b.append(" what=");
                b.append(what);
            }

            if (arg1 != 0) {
                b.append(" arg1=");
                b.append(arg1);
            }

            if (arg2 != 0) {
                b.append(" arg2=");
                b.append(arg2);
            }

            if (obj != null) {
                b.append(" obj=");
                b.append(obj);
            }

            b.append(" target=");
            b.append(target.getClass().getName());
        } else {
            b.append(" barrier=");
            b.append(arg1);
        }

        b.append(" }");
        return b.toString();
    }

	public void recycle() {
		if (isInUse()) {
            if (gCheckRecycle) {
                throw new IllegalStateException("This message cannot be recycled because it "
                        + "is still in use.");
            }
            return;
        }
        recycleUnchecked();
	}

}
