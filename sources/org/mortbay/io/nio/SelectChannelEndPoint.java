package org.mortbay.io.nio;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import org.mortbay.io.Buffer;
import org.mortbay.io.Connection;
import org.mortbay.io.nio.SelectorManager;
import org.mortbay.jetty.EofException;
import org.mortbay.jetty.HttpException;
import org.mortbay.log.Log;
import org.mortbay.thread.Timeout;

/* loaded from: classes3.dex */
public class SelectChannelEndPoint extends ChannelEndPoint implements Runnable {
    protected Connection _connection;
    protected boolean _dispatched;
    protected int _interestOps;
    protected SelectionKey _key;
    protected SelectorManager _manager;
    protected boolean _readBlocked;
    protected SelectorManager.SelectSet _selectSet;
    private Timeout.Task _timeoutTask;
    protected boolean _writable;
    protected boolean _writeBlocked;

    public Connection getConnection() {
        return this._connection;
    }

    public SelectChannelEndPoint(SocketChannel socketChannel, SelectorManager.SelectSet selectSet, SelectionKey selectionKey) {
        super(socketChannel);
        this._dispatched = false;
        this._writable = true;
        this._timeoutTask = new IdleTask();
        SelectorManager manager = selectSet.getManager();
        this._manager = manager;
        this._selectSet = selectSet;
        this._connection = manager.newConnection(socketChannel, this);
        this._manager.endPointOpened(this);
        this._key = selectionKey;
    }

    void dispatch() throws IOException {
        try {
            boolean dispatch = dispatch(this._manager.isDelaySelectKeyUpdate()) ? this._manager.dispatch(this) : true;
        } finally {
            if (1 == 0) {
                Log.warn("dispatch failed!");
                undispatch();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0059, code lost:
    
        if (r3._key.isReadable() == false) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x005b, code lost:
    
        r3._readBlocked = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatch(boolean r4) throws java.io.IOException {
        /*
            r3 = this;
            monitor-enter(r3)
            java.nio.channels.SelectionKey r0 = r3._key     // Catch: java.lang.Throwable -> L7e
            r1 = 0
            if (r0 == 0) goto L75
            boolean r0 = r0.isValid()     // Catch: java.lang.Throwable -> L7e
            if (r0 != 0) goto Ld
            goto L75
        Ld:
            boolean r0 = r3._readBlocked     // Catch: java.lang.Throwable -> L7e
            if (r0 != 0) goto L51
            boolean r2 = r3._writeBlocked     // Catch: java.lang.Throwable -> L7e
            if (r2 == 0) goto L16
            goto L51
        L16:
            if (r4 != 0) goto L1d
            java.nio.channels.SelectionKey r4 = r3._key     // Catch: java.lang.Throwable -> L7e
            r4.interestOps(r1)     // Catch: java.lang.Throwable -> L7e
        L1d:
            boolean r4 = r3._dispatched     // Catch: java.lang.Throwable -> L7e
            if (r4 == 0) goto L28
            java.nio.channels.SelectionKey r4 = r3._key     // Catch: java.lang.Throwable -> L7e
            r4.interestOps(r1)     // Catch: java.lang.Throwable -> L7e
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L7e
            return r1
        L28:
            java.nio.channels.SelectionKey r4 = r3._key     // Catch: java.lang.Throwable -> L7e
            int r4 = r4.readyOps()     // Catch: java.lang.Throwable -> L7e
            r0 = 4
            r4 = r4 & r0
            r1 = 1
            if (r4 != r0) goto L4d
            java.nio.channels.SelectionKey r4 = r3._key     // Catch: java.lang.Throwable -> L7e
            int r4 = r4.interestOps()     // Catch: java.lang.Throwable -> L7e
            r4 = r4 & r0
            if (r4 != r0) goto L4d
            java.nio.channels.SelectionKey r4 = r3._key     // Catch: java.lang.Throwable -> L7e
            int r4 = r4.interestOps()     // Catch: java.lang.Throwable -> L7e
            r4 = r4 & (-5)
            r3._interestOps = r4     // Catch: java.lang.Throwable -> L7e
            java.nio.channels.SelectionKey r0 = r3._key     // Catch: java.lang.Throwable -> L7e
            r0.interestOps(r4)     // Catch: java.lang.Throwable -> L7e
            r3._writable = r1     // Catch: java.lang.Throwable -> L7e
        L4d:
            r3._dispatched = r1     // Catch: java.lang.Throwable -> L7e
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L7e
            return r1
        L51:
            if (r0 == 0) goto L5d
            java.nio.channels.SelectionKey r4 = r3._key     // Catch: java.lang.Throwable -> L7e
            boolean r4 = r4.isReadable()     // Catch: java.lang.Throwable -> L7e
            if (r4 == 0) goto L5d
            r3._readBlocked = r1     // Catch: java.lang.Throwable -> L7e
        L5d:
            boolean r4 = r3._writeBlocked     // Catch: java.lang.Throwable -> L7e
            if (r4 == 0) goto L6b
            java.nio.channels.SelectionKey r4 = r3._key     // Catch: java.lang.Throwable -> L7e
            boolean r4 = r4.isWritable()     // Catch: java.lang.Throwable -> L7e
            if (r4 == 0) goto L6b
            r3._writeBlocked = r1     // Catch: java.lang.Throwable -> L7e
        L6b:
            r3.notifyAll()     // Catch: java.lang.Throwable -> L7e
            java.nio.channels.SelectionKey r4 = r3._key     // Catch: java.lang.Throwable -> L7e
            r4.interestOps(r1)     // Catch: java.lang.Throwable -> L7e
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L7e
            return r1
        L75:
            r3._readBlocked = r1     // Catch: java.lang.Throwable -> L7e
            r3._writeBlocked = r1     // Catch: java.lang.Throwable -> L7e
            r3.notifyAll()     // Catch: java.lang.Throwable -> L7e
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L7e
            return r1
        L7e:
            r4 = move-exception
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L7e
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.io.nio.SelectChannelEndPoint.dispatch(boolean):boolean");
    }

    public void scheduleIdle() {
        this._selectSet.scheduleIdle(this._timeoutTask);
    }

    public void cancelIdle() {
        this._selectSet.cancelIdle(this._timeoutTask);
    }

    protected void idleExpired() {
        try {
            close();
        } catch (IOException e) {
            Log.ignore(e);
        }
    }

    public void undispatch() {
        synchronized (this) {
            try {
                this._dispatched = false;
                updateKey();
            } catch (Exception e) {
                Log.ignore(e);
                this._interestOps = -1;
                this._selectSet.addChange(this);
            }
        }
    }

    @Override // org.mortbay.io.nio.ChannelEndPoint, org.mortbay.io.EndPoint
    public int flush(Buffer buffer, Buffer buffer2, Buffer buffer3) throws IOException {
        int flush = super.flush(buffer, buffer2, buffer3);
        this._writable = flush > 0;
        return flush;
    }

    @Override // org.mortbay.io.nio.ChannelEndPoint, org.mortbay.io.EndPoint
    public int flush(Buffer buffer) throws IOException {
        int flush = super.flush(buffer);
        this._writable = flush > 0;
        return flush;
    }

    @Override // org.mortbay.io.nio.ChannelEndPoint, org.mortbay.io.EndPoint
    public boolean blockReadable(long j) throws IOException {
        synchronized (this) {
            long now = this._selectSet.getNow();
            try {
                this._readBlocked = true;
                while (isOpen() && this._readBlocked) {
                    try {
                        updateKey();
                        wait(j);
                        if (this._readBlocked && j < this._selectSet.getNow() - now) {
                            return false;
                        }
                    } catch (InterruptedException e) {
                        Log.warn(e);
                    }
                }
                return true;
            } finally {
                this._readBlocked = false;
            }
        }
    }

    @Override // org.mortbay.io.nio.ChannelEndPoint, org.mortbay.io.EndPoint
    public boolean blockWritable(long j) throws IOException {
        synchronized (this) {
            long now = this._selectSet.getNow();
            try {
                this._writeBlocked = true;
                while (isOpen() && this._writeBlocked) {
                    try {
                        updateKey();
                        wait(j);
                        if (this._writeBlocked && j < this._selectSet.getNow() - now) {
                            return false;
                        }
                    } catch (InterruptedException e) {
                        Log.warn(e);
                    }
                }
                return true;
            } finally {
                this._writeBlocked = false;
                scheduleIdle();
            }
        }
    }

    public void setWritable(boolean z) {
        this._writable = z;
    }

    public void scheduleWrite() {
        this._writable = false;
        updateKey();
    }

    private void updateKey() {
        int i;
        synchronized (this) {
            int i2 = -1;
            if (getChannel().isOpen()) {
                SelectionKey selectionKey = this._key;
                if (selectionKey != null && selectionKey.isValid()) {
                    i2 = this._key.interestOps();
                }
                if (this._dispatched && !this._readBlocked) {
                    i = 0;
                    this._interestOps = i | ((this._writable || this._writeBlocked) ? 4 : 0);
                }
                i = 1;
                this._interestOps = i | ((this._writable || this._writeBlocked) ? 4 : 0);
            }
            if (this._interestOps == i2 && getChannel().isOpen()) {
                return;
            }
            this._selectSet.addChange(this);
            this._selectSet.wakeup();
        }
    }

    void doUpdateKey() {
        synchronized (this) {
            if (getChannel().isOpen()) {
                if (this._interestOps > 0) {
                    SelectionKey selectionKey = this._key;
                    if (selectionKey != null && selectionKey.isValid()) {
                        this._key.interestOps(this._interestOps);
                    }
                    if (((SelectableChannel) getChannel()).isRegistered()) {
                        updateKey();
                    } else {
                        try {
                            this._key = ((SelectableChannel) getChannel()).register(this._selectSet.getSelector(), this._interestOps, this);
                        } catch (Exception e) {
                            Log.ignore(e);
                            SelectionKey selectionKey2 = this._key;
                            if (selectionKey2 != null && selectionKey2.isValid()) {
                                this._key.cancel();
                            }
                            cancelIdle();
                            this._manager.endPointClosed(this);
                            this._key = null;
                        }
                    }
                } else {
                    SelectionKey selectionKey3 = this._key;
                    if (selectionKey3 != null && selectionKey3.isValid()) {
                        this._key.interestOps(0);
                    } else {
                        this._key = null;
                    }
                }
            } else {
                SelectionKey selectionKey4 = this._key;
                if (selectionKey4 != null && selectionKey4.isValid()) {
                    this._key.interestOps(0);
                    this._key.cancel();
                }
                cancelIdle();
                this._manager.endPointClosed(this);
                this._key = null;
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            try {
                try {
                    this._connection.handle();
                } catch (ClosedChannelException e) {
                    Log.ignore(e);
                } catch (Throwable th) {
                    Log.warn("handle failed", th);
                    try {
                        close();
                    } catch (IOException e2) {
                        Log.ignore(e2);
                    }
                }
            } catch (EofException e3) {
                Log.debug("EOF", e3);
                try {
                    close();
                } catch (IOException e4) {
                    Log.ignore(e4);
                }
            } catch (HttpException e5) {
                Log.debug("BAD", e5);
                try {
                    close();
                } catch (IOException e6) {
                    Log.ignore(e6);
                }
            }
            undispatch();
        } catch (Throwable th2) {
            undispatch();
            throw th2;
        }
    }

    @Override // org.mortbay.io.nio.ChannelEndPoint, org.mortbay.io.EndPoint
    public void close() throws IOException {
        try {
            try {
                super.close();
            } catch (IOException e) {
                Log.ignore(e);
            }
        } finally {
            updateKey();
        }
    }

    public String toString() {
        return new StringBuffer("SCEP@").append(hashCode()).append("[d=").append(this._dispatched).append(",io=").append(this._interestOps).append(",w=").append(this._writable).append(",b=").append(this._readBlocked).append("|").append(this._writeBlocked).append("]").toString();
    }

    public Timeout.Task getTimeoutTask() {
        return this._timeoutTask;
    }

    public SelectorManager.SelectSet getSelectSet() {
        return this._selectSet;
    }

    public class IdleTask extends Timeout.Task {
        public IdleTask() {
        }

        @Override // org.mortbay.thread.Timeout.Task
        public void expired() {
            SelectChannelEndPoint.this.idleExpired();
        }

        public String toString() {
            return new StringBuffer("TimeoutTask:").append(SelectChannelEndPoint.this.toString()).toString();
        }
    }
}
