package org.mortbay.thread;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.mortbay.component.AbstractLifeCycle;
import org.mortbay.log.Log;

/* loaded from: classes3.dex */
public class QueuedThreadPool extends AbstractLifeCycle implements Serializable, ThreadPool {
    private boolean _daemon;
    private int _id;
    private List _idle;
    private Runnable[] _jobs;
    private final Object _joinLock;
    private long _lastShrink;
    private final Object _lock;
    private int _lowThreads;
    private int _maxIdleTimeMs;
    private int _maxQueued;
    private int _maxStopTimeMs;
    private int _maxThreads;
    private int _minThreads;
    private String _name;
    private int _nextJob;
    private int _nextJobSlot;
    private int _priority;
    private int _queued;
    private int _spawnOrShrinkAt;
    private Set _threads;
    private final Object _threadsLock;
    private boolean _warned;

    static /* synthetic */ int access$410(QueuedThreadPool queuedThreadPool) {
        int i = queuedThreadPool._queued;
        queuedThreadPool._queued = i - 1;
        return i;
    }

    static /* synthetic */ int access$608(QueuedThreadPool queuedThreadPool) {
        int i = queuedThreadPool._nextJob;
        queuedThreadPool._nextJob = i + 1;
        return i;
    }

    public QueuedThreadPool() {
        this._lock = new Lock();
        this._threadsLock = new Lock();
        this._joinLock = new Lock();
        this._maxIdleTimeMs = 60000;
        this._maxThreads = ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
        this._minThreads = 2;
        this._warned = false;
        this._lowThreads = 0;
        this._priority = 5;
        this._spawnOrShrinkAt = 0;
        this._name = new StringBuffer("qtp-").append(hashCode()).toString();
    }

    public QueuedThreadPool(int i) {
        this();
        setMaxThreads(i);
    }

    @Override // org.mortbay.thread.ThreadPool
    public boolean dispatch(Runnable runnable) {
        PoolThread poolThread;
        if (!isRunning() || runnable == null) {
            return false;
        }
        synchronized (this._lock) {
            int size = this._idle.size();
            if (size > 0) {
                poolThread = (PoolThread) this._idle.remove(size - 1);
            } else {
                int i = this._queued + 1;
                this._queued = i;
                if (i > this._maxQueued) {
                    this._maxQueued = i;
                }
                Runnable[] runnableArr = this._jobs;
                int i2 = this._nextJobSlot;
                int i3 = i2 + 1;
                this._nextJobSlot = i3;
                runnableArr[i2] = runnable;
                if (i3 == runnableArr.length) {
                    this._nextJobSlot = 0;
                }
                int i4 = this._nextJobSlot;
                int i5 = this._nextJob;
                if (i4 == i5) {
                    Runnable[] runnableArr2 = new Runnable[runnableArr.length + this._maxThreads];
                    int length = runnableArr.length - i5;
                    if (length > 0) {
                        System.arraycopy(runnableArr, i5, runnableArr2, 0, length);
                    }
                    if (this._nextJob != 0) {
                        System.arraycopy(this._jobs, 0, runnableArr2, length, this._nextJobSlot);
                    }
                    this._jobs = runnableArr2;
                    this._nextJob = 0;
                    this._nextJobSlot = this._queued;
                }
                r1 = this._queued > this._spawnOrShrinkAt;
                poolThread = null;
            }
        }
        if (poolThread != null) {
            poolThread.dispatch(runnable);
        } else if (r1) {
            newThread();
        }
        return true;
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

    public int getMaxQueued() {
        return this._maxQueued;
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
        return this._queued;
    }

    public int getSpawnOrShrinkAt() {
        return this._spawnOrShrinkAt;
    }

    public void setSpawnOrShrinkAt(int i) {
        this._spawnOrShrinkAt = i;
    }

    public int getMaxStopTimeMs() {
        return this._maxStopTimeMs;
    }

    public void setMaxStopTimeMs(int i) {
        this._maxStopTimeMs = i;
    }

    public boolean isDaemon() {
        return this._daemon;
    }

    @Override // org.mortbay.thread.ThreadPool
    public boolean isLowOnThreads() {
        return this._queued > this._lowThreads;
    }

    @Override // org.mortbay.thread.ThreadPool
    public void join() throws InterruptedException {
        synchronized (this._joinLock) {
            while (isRunning()) {
                this._joinLock.wait();
            }
        }
        while (isStopping()) {
            Thread.sleep(100L);
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
        synchronized (this._threadsLock) {
            while (isStarted() && this._threads.size() < this._minThreads) {
                newThread();
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
        this._jobs = new Runnable[this._maxThreads];
        for (int i3 = 0; i3 < this._minThreads; i3++) {
            newThread();
        }
    }

    @Override // org.mortbay.component.AbstractLifeCycle
    protected void doStop() throws Exception {
        int i;
        super.doStop();
        long currentTimeMillis = System.currentTimeMillis();
        for (int i2 = 0; i2 < 100; i2++) {
            synchronized (this._threadsLock) {
                Iterator it = this._threads.iterator();
                while (it.hasNext()) {
                    ((Thread) it.next()).interrupt();
                }
            }
            Thread.yield();
            if (this._threads.size() == 0 || ((i = this._maxStopTimeMs) > 0 && i < System.currentTimeMillis() - currentTimeMillis)) {
                break;
            }
            try {
                Thread.sleep(i2 * 100);
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

    protected void newThread() {
        synchronized (this._threadsLock) {
            if (this._threads.size() < this._maxThreads) {
                PoolThread poolThread = new PoolThread();
                this._threads.add(poolThread);
                StringBuffer append = new StringBuffer().append(poolThread.hashCode()).append("@").append(this._name).append("-");
                int i = this._id;
                this._id = i + 1;
                poolThread.setName(append.append(i).toString());
                poolThread.start();
            } else if (!this._warned) {
                this._warned = true;
                Log.debug("Max threads for {}", this);
            }
        }
    }

    protected void stopJob(Thread thread, Object obj) {
        thread.interrupt();
    }

    public String dump() {
        StringBuffer stringBuffer = new StringBuffer();
        synchronized (this._threadsLock) {
            for (Thread thread : this._threads) {
                stringBuffer.append(thread.getName()).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(thread.toString()).append('\n');
            }
        }
        return stringBuffer.toString();
    }

    public boolean stopThread(String str) {
        synchronized (this._threadsLock) {
            for (Thread thread : this._threads) {
                if (str.equals(thread.getName())) {
                    thread.stop();
                    return true;
                }
            }
            return false;
        }
    }

    public boolean interruptThread(String str) {
        synchronized (this._threadsLock) {
            for (Thread thread : this._threads) {
                if (str.equals(thread.getName())) {
                    thread.interrupt();
                    return true;
                }
            }
            return false;
        }
    }

    public class PoolThread extends Thread {
        Runnable _job = null;

        PoolThread() {
            setDaemon(QueuedThreadPool.this._daemon);
            setPriority(QueuedThreadPool.this._priority);
        }

        /* JADX WARN: Code restructure failed: missing block: B:24:0x00ab, code lost:
        
            r0 = r11.this$0._lock;
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x00b1, code lost:
        
            monitor-enter(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x00b2, code lost:
        
            r11.this$0._idle.remove(r11);
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x00bb, code lost:
        
            monitor-exit(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x00bc, code lost:
        
            r1 = r11.this$0._threadsLock;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x00c2, code lost:
        
            monitor-enter(r1);
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x00c3, code lost:
        
            r11.this$0._threads.remove(r11);
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x00cc, code lost:
        
            monitor-exit(r1);
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x00cd, code lost:
        
            monitor-enter(r11);
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x00ce, code lost:
        
            r0 = r11._job;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x00d0, code lost:
        
            monitor-exit(r11);
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x00d1, code lost:
        
            if (r0 == null) goto L173;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x00d3, code lost:
        
            r11.this$0.dispatch(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x00d8, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:?, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x00ef, code lost:
        
            monitor-enter(r11);
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x00f2, code lost:
        
            if (r11._job != null) goto L56;
         */
        /* JADX WARN: Code restructure failed: missing block: B:63:0x00f4, code lost:
        
            wait(r11.this$0.getMaxIdleTimeMs());
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x00fe, code lost:
        
            r2 = r11._job;
            r11._job = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x0102, code lost:
        
            monitor-exit(r11);
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 434
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.mortbay.thread.QueuedThreadPool.PoolThread.run():void");
        }

        void dispatch(Runnable runnable) {
            synchronized (this) {
                this._job = runnable;
                notify();
            }
        }
    }

    private class Lock {
        private Lock() {
        }
    }
}
