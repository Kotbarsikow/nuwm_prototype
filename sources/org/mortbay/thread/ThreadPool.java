package org.mortbay.thread;

/* loaded from: classes3.dex */
public interface ThreadPool {
    boolean dispatch(Runnable runnable);

    int getIdleThreads();

    int getThreads();

    boolean isLowOnThreads();

    void join() throws InterruptedException;
}
