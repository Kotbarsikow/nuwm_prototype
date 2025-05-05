package org.mortbay.thread;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.mortbay.component.AbstractLifeCycle;
import org.mortbay.log.Log;

/* loaded from: classes3.dex */
public class BoundedThreadPool extends AbstractLifeCycle implements Serializable, ThreadPool {
    private static int __id;
    private boolean _daemon;
    private int _id;
    private List _idle;
    private long _lastShrink;
    private String _name;
    private List _queue;
    private Set _threads;
    private final Object _lock = new Object();
    private final Object _joinLock = new Object();
    private int _maxIdleTimeMs = 60000;
    private int _maxThreads = 255;
    private int _minThreads = 1;
    private boolean _warned = false;
    int _lowThreads = 0;
    int _priority = 5;

    public BoundedThreadPool() {
        StringBuffer stringBuffer = new StringBuffer("btpool");
        int i = __id;
        __id = i + 1;
        this._name = stringBuffer.append(i).toString();
    }

    @Override // org.mortbay.thread.ThreadPool
    public boolean dispatch(Runnable runnable) {
        synchronized (this._lock) {
            if (isRunning() && runnable != null) {
                int size = this._idle.size();
                if (size > 0) {
                    ((PoolThread) this._idle.remove(size - 1)).dispatch(runnable);
                } else if (this._threads.size() < this._maxThreads) {
                    newThread(runnable);
                } else {
                    if (!this._warned) {
                        this._warned = true;
                        Log.debug("Out of threads for {}", this);
                    }
                    this._queue.add(runnable);
                }
                return true;
            }
            return false;
        }
    }

    @Override // org.mortbay.thread.ThreadPool
    public int getIdleThreads() {
        List list = this._idle;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public int getLowThreads() {
        return this._lowThreads;
    }

    public int getMaxIdleTimeMs() {
        return this._maxIdleTimeMs;
    }

    public int getMaxThreads() {
        return this._maxThreads;
    }

    public int getMinThreads() {
        return this._minThreads;
    }

    public String getName() {
        return this._name;
    }

    @Override // org.mortbay.thread.ThreadPool
    public int getThreads() {
        return this._threads.size();
    }

    public int getThreadsPriority() {
        return this._priority;
    }

    public int getQueueSize() {
        int size;
        synchronized (this._lock) {
            size = this._queue.size();
        }
        return size;
    }

    public boolean isDaemon() {
        return this._daemon;
    }

    @Override // org.mortbay.thread.ThreadPool
    public boolean isLowOnThreads() {
        boolean z;
        synchronized (this._lock) {
            z = this._queue.size() > this._lowThreads;
        }
        return z;
    }

    @Override // org.mortbay.thread.ThreadPool
    public void join() throws InterruptedException {
        synchronized (this._joinLock) {
            while (isRunning()) {
                this._joinLock.wait();
            }
        }
        while (isStopping()) {
            Thread.sleep(10L);
        }
    }

    public void setDaemon(boolean z) {
        this._daemon = z;
    }

    public void setLowThreads(int i) {
        this._lowThreads = i;
    }

    public void setMaxIdleTimeMs(int i) {
        this._maxIdleTimeMs = i;
    }

    public void setMaxThreads(int i) {
        if (isStarted() && i < this._minThreads) {
            throw new IllegalArgumentException("!minThreads<maxThreads");
        }
        this._maxThreads = i;
    }

    public void setMinThreads(int i) {
        if (isStarted() && (i <= 0 || i > this._maxThreads)) {
            throw new IllegalArgumentException("!0<=minThreads<maxThreads");
        }
        this._minThreads = i;
        synchronized (this._lock) {
            while (isStarted() && this._threads.size() < this._minThreads) {
                newThread(null);
            }
        }
    }

    public void setName(String str) {
        this._name = str;
    }

    public void setThreadsPriority(int i) {
        this._priority = i;
    }

    @Override // org.mortbay.component.AbstractLifeCycle
    protected void doStart() throws Exception {
        int i = this._maxThreads;
        int i2 = this._minThreads;
        if (i < i2 || i2 <= 0) {
            throw new IllegalArgumentException("!0<minThreads<maxThreads");
        }
        this._threads = new HashSet();
        this._idle = new ArrayList();
        this._queue = new LinkedList();
        for (int i3 = 0; i3 < this._minThreads; i3++) {
            newThread(null);
        }
    }

    @Override // org.mortbay.component.AbstractLifeCycle
    protected void doStop() throws Exception {
        super.doStop();
        for (int i = 0; i < 100; i++) {
            synchronized (this._lock) {
                Iterator it = this._threads.iterator();
                while (it.hasNext()) {
                    ((Thread) it.next()).interrupt();
                }
            }
            Thread.yield();
            if (this._threads.size() == 0) {
                break;
            }
            try {
                Thread.sleep(i * 100);
            } catch (InterruptedException unused) {
            }
        }
        if (this._threads.size() > 0) {
            Log.warn(new StringBuffer().append(this._threads.size()).append(" threads could not be stopped").toString());
        }
        synchronized (this._joinLock) {
            this._joinLock.notifyAll();
        }
    }

    protected PoolThread newThread(Runnable runnable) {
        PoolThread poolThread;
        synchronized (this._lock) {
            poolThread = new PoolThread(runnable);
            this._threads.add(poolThread);
            StringBuffer append = new StringBuffer().append(this._name).append("-");
            int i = this._id;
            this._id = i + 1;
            poolThread.setName(append.append(i).toString());
            poolThread.start();
        }
        return poolThread;
    }

    protected void stopJob(Thread thread, Object obj) {
        thread.interrupt();
    }

    public class PoolThread extends Thread {
        Runnable _job;

        PoolThread() {
            this._job = null;
            setDaemon(BoundedThreadPool.this._daemon);
            setPriority(BoundedThreadPool.this._priority);
        }

        PoolThread(Runnable runnable) {
            this._job = null;
            setDaemon(BoundedThreadPool.this._daemon);
            setPriority(BoundedThreadPool.this._priority);
            this._job = runnable;
        }

        /* JADX WARN: Code restructure failed: missing block: B:25:0x00bf, code lost:
        
            monitor-enter(r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x00c2, code lost:
        
            if (r10._job != null) goto L50;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x00c4, code lost:
        
            wait(r10.this$0.getMaxIdleTimeMs());
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x00ce, code lost:
        
            r0 = r10._job;
            r10._job = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x00d2, code lost:
        
            monitor-exit(r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x00d3, code lost:
        
            r2 = r10.this$0._lock;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x00d9, code lost:
        
            monitor-enter(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x00da, code lost:
        
            r10.this$0._idle.remove(r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x00e3, code lost:
        
            monitor-exit(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x00ee, code lost:
        
            r2 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00ef, code lost:
        
            org.mortbay.log.Log.ignore(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x00f8, code lost:
        
            monitor-enter(r10.this$0._lock);
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x00f9, code lost:
        
            r10.this$0._idle.remove(r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:66:0x00ec, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:68:0x010e, code lost:
        
            monitor-enter(r10.this$0._lock);
         */
        /* JADX WARN: Code restructure failed: missing block: B:70:0x010f, code lost:
        
            r10.this$0._idle.remove(r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:72:0x0119, code lost:
        
            throw r0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:82:0x008a, code lost:
        
            r0 = r10.this$0._lock;
         */
        /* JADX WARN: Code restructure failed: missing block: B:83:0x0090, code lost:
        
            monitor-enter(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:85:0x0091, code lost:
        
            r10.this$0._threads.remove(r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:86:0x009a, code lost:
        
            monitor-exit(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:87:0x009b, code lost:
        
            monitor-enter(r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:89:0x009c, code lost:
        
            r0 = r10._job;
         */
        /* JADX WARN: Code restructure failed: missing block: B:90:0x009e, code lost:
        
            monitor-exit(r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:91:0x009f, code lost:
        
            if (r0 == null) goto L167;
         */
        /* JADX WARN: Code restructure failed: missing block: B:93:0x00a7, code lost:
        
            if (r10.this$0.isRunning() == false) goto L168;
         */
        /* JADX WARN: Code restructure failed: missing block: B:94:0x00a9, code lost:
        
            r10.this$0.dispatch(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:95:0x00ae, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:96:?, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:97:?, code lost:
        
            return;
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 378
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.mortbay.thread.BoundedThreadPool.PoolThread.run():void");
        }

        void dispatch(Runnable runnable) {
            synchronized (this) {
                if (this._job != null || runnable == null) {
                    throw new IllegalStateException();
                }
                this._job = runnable;
                notify();
            }
        }
    }
}
