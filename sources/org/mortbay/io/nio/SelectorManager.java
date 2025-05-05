package org.mortbay.io.nio;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.IOException;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.mortbay.component.AbstractLifeCycle;
import org.mortbay.io.Connection;
import org.mortbay.io.EndPoint;
import org.mortbay.log.Log;
import org.mortbay.thread.Timeout;

/* loaded from: classes3.dex */
public abstract class SelectorManager extends AbstractLifeCycle {
    private long _lowResourcesConnections;
    private long _lowResourcesMaxIdleTime;
    private long _maxIdleTime;
    private transient SelectSet[] _selectSet;
    private volatile int _set;
    private static final int __JVMBUG_THRESHHOLD = Integer.getInteger("org.mortbay.io.nio.JVMBUG_THRESHHOLD", 512).intValue();
    private static final int __MONITOR_PERIOD = Integer.getInteger("org.mortbay.io.nio.MONITOR_PERIOD", 1000).intValue();
    private static final int __MAX_SELECTS = Integer.getInteger("org.mortbay.io.nio.MAX_SELECTS", 15000).intValue();
    private static final int __BUSY_PAUSE = Integer.getInteger("org.mortbay.io.nio.BUSY_PAUSE", 50).intValue();
    private static final int __BUSY_KEY = Integer.getInteger("org.mortbay.io.nio.BUSY_KEY", -1).intValue();
    private boolean _delaySelectKeyUpdate = true;
    private int _selectSets = 1;

    private interface ChangeTask {
        void run();
    }

    protected abstract SocketChannel acceptChannel(SelectionKey selectionKey) throws IOException;

    public abstract boolean dispatch(Runnable runnable) throws IOException;

    protected abstract void endPointClosed(SelectChannelEndPoint selectChannelEndPoint);

    protected abstract void endPointOpened(SelectChannelEndPoint selectChannelEndPoint);

    protected abstract Connection newConnection(SocketChannel socketChannel, SelectChannelEndPoint selectChannelEndPoint);

    protected abstract SelectChannelEndPoint newEndPoint(SocketChannel socketChannel, SelectSet selectSet, SelectionKey selectionKey) throws IOException;

    public void setMaxIdleTime(long j) {
        this._maxIdleTime = j;
    }

    public void setSelectSets(int i) {
        long j = this._lowResourcesConnections * this._selectSets;
        this._selectSets = i;
        this._lowResourcesConnections = j / i;
    }

    public long getMaxIdleTime() {
        return this._maxIdleTime;
    }

    public int getSelectSets() {
        return this._selectSets;
    }

    public boolean isDelaySelectKeyUpdate() {
        return this._delaySelectKeyUpdate;
    }

    public void register(SocketChannel socketChannel, Object obj) throws IOException {
        int i = this._set;
        this._set = i + 1;
        int i2 = i % this._selectSets;
        SelectSet[] selectSetArr = this._selectSet;
        if (selectSetArr != null) {
            SelectSet selectSet = selectSetArr[i2];
            selectSet.addChange(socketChannel, obj);
            selectSet.wakeup();
        }
    }

    public void register(ServerSocketChannel serverSocketChannel) throws IOException {
        int i = this._set;
        this._set = i + 1;
        SelectSet selectSet = this._selectSet[i % this._selectSets];
        selectSet.addChange(serverSocketChannel);
        selectSet.wakeup();
    }

    public long getLowResourcesConnections() {
        return this._lowResourcesConnections * this._selectSets;
    }

    public void setLowResourcesConnections(long j) {
        int i = this._selectSets;
        this._lowResourcesConnections = ((j + i) - 1) / i;
    }

    public long getLowResourcesMaxIdleTime() {
        return this._lowResourcesMaxIdleTime;
    }

    public void setLowResourcesMaxIdleTime(long j) {
        this._lowResourcesMaxIdleTime = j;
    }

    public void doSelect(int i) throws IOException {
        SelectSet selectSet;
        SelectSet[] selectSetArr = this._selectSet;
        if (selectSetArr == null || selectSetArr.length <= i || (selectSet = selectSetArr[i]) == null) {
            return;
        }
        selectSet.doSelect();
    }

    public void setDelaySelectKeyUpdate(boolean z) {
        this._delaySelectKeyUpdate = z;
    }

    @Override // org.mortbay.component.AbstractLifeCycle
    protected void doStart() throws Exception {
        this._selectSet = new SelectSet[this._selectSets];
        int i = 0;
        while (true) {
            SelectSet[] selectSetArr = this._selectSet;
            if (i < selectSetArr.length) {
                selectSetArr[i] = new SelectSet(i);
                i++;
            } else {
                super.doStart();
                return;
            }
        }
    }

    @Override // org.mortbay.component.AbstractLifeCycle
    protected void doStop() throws Exception {
        SelectSet[] selectSetArr = this._selectSet;
        this._selectSet = null;
        if (selectSetArr != null) {
            for (SelectSet selectSet : selectSetArr) {
                if (selectSet != null) {
                    selectSet.stop();
                }
            }
        }
        super.doStop();
    }

    protected void connectionFailed(SocketChannel socketChannel, Throwable th, Object obj) {
        Log.warn(th);
    }

    public class SelectSet {
        private SelectionKey _busyKey;
        private int _busyKeyCount;
        private transient int _change;
        private transient List[] _changes;
        private transient Timeout _idleTimeout;
        private transient int _jvmBug;
        private int _jvmFix0;
        private int _jvmFix1;
        private int _jvmFix2;
        private long _log;
        private long _monitorNext;
        private long _monitorStart;
        private transient int _nextSet;
        private int _paused;
        private boolean _pausing;
        private transient Timeout _retryTimeout;
        private volatile boolean _selecting;
        private transient Selector _selector;
        private int _selects;
        private transient int _setID;

        SelectSet(int i) throws Exception {
            this._setID = i;
            Timeout timeout = new Timeout(this);
            this._idleTimeout = timeout;
            timeout.setDuration(SelectorManager.this.getMaxIdleTime());
            Timeout timeout2 = new Timeout(this);
            this._retryTimeout = timeout2;
            timeout2.setDuration(0L);
            this._selector = Selector.open();
            this._changes = new ArrayList[]{new ArrayList(), new ArrayList()};
            this._change = 0;
            long currentTimeMillis = System.currentTimeMillis();
            this._monitorStart = currentTimeMillis;
            this._monitorNext = currentTimeMillis + SelectorManager.__MONITOR_PERIOD;
            this._log = this._monitorStart + 60000;
        }

        public void addChange(Object obj) {
            synchronized (this._changes) {
                this._changes[this._change].add(obj);
            }
        }

        public void addChange(SelectableChannel selectableChannel, Object obj) {
            if (obj == null) {
                addChange(selectableChannel);
            } else if (obj instanceof EndPoint) {
                addChange(obj);
            } else {
                addChange(new ChangeSelectableChannel(selectableChannel, obj));
            }
        }

        public void cancelIdle(Timeout.Task task) {
            synchronized (this) {
                task.cancel();
            }
        }

        public void doSelect() throws IOException {
            boolean z;
            ClosedSelectorException closedSelectorException;
            Throwable th;
            List list;
            Selector selector;
            long timeToNext;
            long timeToNext2;
            long j;
            long j2;
            try {
                try {
                    try {
                    } catch (ClosedSelectorException e) {
                        z = false;
                        closedSelectorException = e;
                    }
                    try {
                        synchronized (this._changes) {
                            List[] listArr = this._changes;
                            int i = this._change;
                            list = listArr[i];
                            this._change = i == 0 ? 1 : 0;
                            this._selecting = true;
                            selector = this._selector;
                        }
                        for (int i2 = 0; i2 < list.size(); i2++) {
                            try {
                                try {
                                    Object obj = list.get(i2);
                                    if (obj instanceof EndPoint) {
                                        ((SelectChannelEndPoint) obj).doUpdateKey();
                                    } else if (obj instanceof Runnable) {
                                        SelectorManager.this.dispatch((Runnable) obj);
                                    } else if (obj instanceof ChangeSelectableChannel) {
                                        ChangeSelectableChannel changeSelectableChannel = (ChangeSelectableChannel) obj;
                                        SelectableChannel selectableChannel = changeSelectableChannel._channel;
                                        Object obj2 = changeSelectableChannel._attachment;
                                        if ((selectableChannel instanceof SocketChannel) && ((SocketChannel) selectableChannel).isConnected()) {
                                            SelectionKey register = selectableChannel.register(selector, 1, obj2);
                                            SelectChannelEndPoint newEndPoint = SelectorManager.this.newEndPoint((SocketChannel) selectableChannel, this, register);
                                            register.attach(newEndPoint);
                                            newEndPoint.dispatch();
                                        } else if (selectableChannel.isOpen()) {
                                            selectableChannel.register(selector, 8, obj2);
                                        }
                                    } else if (obj instanceof SocketChannel) {
                                        SocketChannel socketChannel = (SocketChannel) obj;
                                        if (socketChannel.isConnected()) {
                                            SelectionKey register2 = socketChannel.register(selector, 1, null);
                                            SelectChannelEndPoint newEndPoint2 = SelectorManager.this.newEndPoint(socketChannel, this, register2);
                                            register2.attach(newEndPoint2);
                                            newEndPoint2.dispatch();
                                        } else if (socketChannel.isOpen()) {
                                            socketChannel.register(selector, 8, null);
                                        }
                                    } else if (obj instanceof ServerSocketChannel) {
                                        ((ServerSocketChannel) obj).register(getSelector(), 16);
                                    } else {
                                        if (!(obj instanceof ChangeTask)) {
                                            throw new IllegalArgumentException(obj.toString());
                                        }
                                        ((ChangeTask) obj).run();
                                    }
                                } catch (Error e2) {
                                    if (SelectorManager.this.isRunning()) {
                                        Log.warn(e2);
                                    } else {
                                        Log.debug(e2);
                                    }
                                } catch (Exception e3) {
                                    if (SelectorManager.this.isRunning()) {
                                        Log.warn(e3);
                                    } else {
                                        Log.debug(e3);
                                    }
                                }
                            } catch (Throwable th2) {
                                list.clear();
                                throw th2;
                            }
                        }
                        list.clear();
                        long currentTimeMillis = System.currentTimeMillis();
                        synchronized (this) {
                            this._idleTimeout.setNow(currentTimeMillis);
                            this._retryTimeout.setNow(currentTimeMillis);
                            if (SelectorManager.this._lowResourcesConnections <= 0 || selector.keys().size() <= SelectorManager.this._lowResourcesConnections) {
                                this._idleTimeout.setDuration(SelectorManager.this._maxIdleTime);
                            } else {
                                this._idleTimeout.setDuration(SelectorManager.this._lowResourcesMaxIdleTime);
                            }
                            timeToNext = this._idleTimeout.getTimeToNext();
                            timeToNext2 = this._retryTimeout.getTimeToNext();
                        }
                        if (timeToNext < 0 || 1000 <= timeToNext) {
                            timeToNext = 1000;
                        }
                        if (timeToNext <= 0 || timeToNext2 < 0 || timeToNext <= timeToNext2) {
                            timeToNext2 = timeToNext;
                        }
                        if (timeToNext2 > 2) {
                            if (this._pausing) {
                                try {
                                    Thread.sleep(SelectorManager.__BUSY_PAUSE);
                                } catch (InterruptedException e4) {
                                    Log.ignore(e4);
                                }
                            }
                            int select = selector.select(timeToNext2);
                            long currentTimeMillis2 = System.currentTimeMillis();
                            this._idleTimeout.setNow(currentTimeMillis2);
                            this._retryTimeout.setNow(currentTimeMillis2);
                            this._selects = this._selects + 1;
                            if (currentTimeMillis2 > this._monitorNext) {
                                j2 = currentTimeMillis;
                                int i3 = (int) ((r10 * SelectorManager.__MONITOR_PERIOD) / (currentTimeMillis2 - this._monitorStart));
                                this._selects = i3;
                                boolean z2 = i3 > SelectorManager.__MAX_SELECTS;
                                this._pausing = z2;
                                if (z2) {
                                    this._paused++;
                                }
                                this._selects = 0;
                                this._jvmBug = 0;
                                this._monitorStart = currentTimeMillis2;
                                this._monitorNext = SelectorManager.__MONITOR_PERIOD + currentTimeMillis2;
                            } else {
                                j2 = currentTimeMillis;
                            }
                            if (currentTimeMillis2 > this._log) {
                                if (this._paused > 0) {
                                    Log.info(new StringBuffer().append(this).append(" Busy selector - injecting delay ").append(this._paused).append(" times").toString());
                                }
                                if (this._jvmFix2 > 0) {
                                    Log.info(new StringBuffer().append(this).append(" JVM BUG(s) - injecting delay").append(this._jvmFix2).append(" times").toString());
                                }
                                if (this._jvmFix1 > 0) {
                                    Log.info(new StringBuffer().append(this).append(" JVM BUG(s) - recreating selector ").append(this._jvmFix1).append(" times, canceled keys ").append(this._jvmFix0).append(" times").toString());
                                } else if (Log.isDebugEnabled() && this._jvmFix0 > 0) {
                                    Log.info(new StringBuffer().append(this).append(" JVM BUG(s) - canceled keys ").append(this._jvmFix0).append(" times").toString());
                                }
                                this._paused = 0;
                                this._jvmFix2 = 0;
                                this._jvmFix1 = 0;
                                this._jvmFix0 = 0;
                                this._log = 60000 + currentTimeMillis2;
                            }
                            if (select == 0 && timeToNext2 > 10 && currentTimeMillis2 - j2 < timeToNext2 / 2) {
                                int i4 = this._jvmBug + 1;
                                this._jvmBug = i4;
                                if (i4 > SelectorManager.__JVMBUG_THRESHHOLD) {
                                    try {
                                        if (this._jvmBug == SelectorManager.__JVMBUG_THRESHHOLD + 1) {
                                            this._jvmFix2++;
                                        }
                                        Thread.sleep(SelectorManager.__BUSY_PAUSE);
                                    } catch (InterruptedException e5) {
                                        Log.ignore(e5);
                                    }
                                } else {
                                    if (this._jvmBug == SelectorManager.__JVMBUG_THRESHHOLD) {
                                        synchronized (this) {
                                            this._jvmFix1++;
                                            Selector open = Selector.open();
                                            for (SelectionKey selectionKey : this._selector.keys()) {
                                                if (selectionKey.isValid() && selectionKey.interestOps() != 0) {
                                                    SelectableChannel channel = selectionKey.channel();
                                                    Object attachment = selectionKey.attachment();
                                                    if (attachment == null) {
                                                        addChange(channel);
                                                    } else {
                                                        addChange(channel, attachment);
                                                    }
                                                }
                                            }
                                            Selector selector2 = this._selector;
                                            this._selector = open;
                                            try {
                                                selector2.close();
                                            } catch (Exception e6) {
                                                Log.warn(e6);
                                            }
                                        }
                                        this._selecting = false;
                                        return;
                                    }
                                    if (this._jvmBug % 32 == 31) {
                                        int i5 = 0;
                                        for (SelectionKey selectionKey2 : selector.keys()) {
                                            if (selectionKey2.isValid() && selectionKey2.interestOps() == 0) {
                                                selectionKey2.cancel();
                                                i5++;
                                            }
                                        }
                                        if (i5 > 0) {
                                            this._jvmFix0++;
                                        }
                                        this._selecting = false;
                                        return;
                                    }
                                }
                            } else if (SelectorManager.__BUSY_KEY > 0 && select == 1 && this._selects > SelectorManager.__MAX_SELECTS) {
                                SelectionKey next = selector.selectedKeys().iterator().next();
                                if (next == this._busyKey) {
                                    int i6 = this._busyKeyCount + 1;
                                    this._busyKeyCount = i6;
                                    if (i6 > SelectorManager.__BUSY_KEY && !(next.channel() instanceof ServerSocketChannel)) {
                                        final SelectChannelEndPoint selectChannelEndPoint = (SelectChannelEndPoint) next.attachment();
                                        Log.warn(new StringBuffer().append("Busy Key ").append(next.channel()).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(selectChannelEndPoint).toString());
                                        next.cancel();
                                        if (selectChannelEndPoint != null) {
                                            SelectorManager.this.dispatch(new Runnable() { // from class: org.mortbay.io.nio.SelectorManager.SelectSet.1
                                                @Override // java.lang.Runnable
                                                public void run() {
                                                    try {
                                                        selectChannelEndPoint.close();
                                                    } catch (IOException e7) {
                                                        Log.ignore(e7);
                                                    }
                                                }
                                            });
                                        }
                                    }
                                } else {
                                    this._busyKeyCount = 0;
                                }
                                this._busyKey = next;
                            }
                            j = currentTimeMillis2;
                        } else {
                            selector.selectNow();
                            this._selects++;
                            j = currentTimeMillis;
                        }
                    } catch (ClosedSelectorException e7) {
                        closedSelectorException = e7;
                        z = false;
                        try {
                            Log.warn(closedSelectorException);
                            this._selecting = z;
                            return;
                        } catch (Throwable th3) {
                            th = th3;
                            th = th;
                            this._selecting = z;
                            throw th;
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    z = false;
                    this._selecting = z;
                    throw th;
                }
            } catch (CancelledKeyException e8) {
                try {
                    Log.ignore(e8);
                    z = false;
                } catch (Throwable th5) {
                    th = th5;
                    z = false;
                    th = th;
                    this._selecting = z;
                    throw th;
                }
            }
            if (this._selector != null && selector.isOpen()) {
                for (SelectionKey selectionKey3 : selector.selectedKeys()) {
                    try {
                        if (selectionKey3.isValid()) {
                            Object attachment2 = selectionKey3.attachment();
                            if (attachment2 instanceof SelectChannelEndPoint) {
                                ((SelectChannelEndPoint) attachment2).dispatch();
                            } else if (selectionKey3.isAcceptable()) {
                                SocketChannel acceptChannel = SelectorManager.this.acceptChannel(selectionKey3);
                                if (acceptChannel != null) {
                                    acceptChannel.configureBlocking(false);
                                    int i7 = this._nextSet + 1;
                                    this._nextSet = i7;
                                    int length = i7 % SelectorManager.this._selectSet.length;
                                    this._nextSet = length;
                                    if (length == this._setID) {
                                        SelectionKey register3 = acceptChannel.register(SelectorManager.this._selectSet[this._nextSet].getSelector(), 1);
                                        SelectorManager selectorManager = SelectorManager.this;
                                        SelectChannelEndPoint newEndPoint3 = selectorManager.newEndPoint(acceptChannel, selectorManager._selectSet[this._nextSet], register3);
                                        register3.attach(newEndPoint3);
                                        if (newEndPoint3 != null) {
                                            newEndPoint3.dispatch();
                                        }
                                    } else {
                                        SelectorManager.this._selectSet[this._nextSet].addChange(acceptChannel);
                                        SelectorManager.this._selectSet[this._nextSet].wakeup();
                                    }
                                }
                            } else if (selectionKey3.isConnectable()) {
                                SocketChannel socketChannel2 = (SocketChannel) selectionKey3.channel();
                                try {
                                    try {
                                    } catch (Throwable th6) {
                                        selectionKey3.cancel();
                                        throw th6;
                                    }
                                } catch (Exception e9) {
                                    SelectorManager.this.connectionFailed(socketChannel2, e9, attachment2);
                                }
                                if (socketChannel2.finishConnect()) {
                                    selectionKey3.interestOps(1);
                                    SelectChannelEndPoint newEndPoint4 = SelectorManager.this.newEndPoint(socketChannel2, this, selectionKey3);
                                    selectionKey3.attach(newEndPoint4);
                                    newEndPoint4.dispatch();
                                } else {
                                    selectionKey3.cancel();
                                }
                            } else {
                                SelectChannelEndPoint newEndPoint5 = SelectorManager.this.newEndPoint((SocketChannel) selectionKey3.channel(), this, selectionKey3);
                                selectionKey3.attach(newEndPoint5);
                                if (selectionKey3.isReadable()) {
                                    newEndPoint5.dispatch();
                                }
                            }
                        } else {
                            selectionKey3.cancel();
                            SelectChannelEndPoint selectChannelEndPoint2 = (SelectChannelEndPoint) selectionKey3.attachment();
                            if (selectChannelEndPoint2 != null) {
                                selectChannelEndPoint2.doUpdateKey();
                            }
                        }
                    } catch (CancelledKeyException e10) {
                        Log.ignore(e10);
                    } catch (Exception e11) {
                        if (SelectorManager.this.isRunning()) {
                            Log.warn(e11);
                        } else {
                            Log.ignore(e11);
                        }
                        if (selectionKey3 != null && !(selectionKey3.channel() instanceof ServerSocketChannel) && selectionKey3.isValid()) {
                            selectionKey3.interestOps(0);
                            selectionKey3.cancel();
                        }
                    }
                }
                selector.selectedKeys().clear();
                this._idleTimeout.tick(j);
                this._retryTimeout.tick(j);
                z = false;
                this._selecting = z;
                return;
            }
            this._selecting = false;
        }

        public SelectorManager getManager() {
            return SelectorManager.this;
        }

        public long getNow() {
            return this._idleTimeout.getNow();
        }

        public void scheduleIdle(Timeout.Task task) {
            synchronized (this) {
                if (this._idleTimeout.getDuration() <= 0) {
                    return;
                }
                task.schedule(this._idleTimeout);
            }
        }

        public void scheduleTimeout(Timeout.Task task, long j) {
            synchronized (this) {
                this._retryTimeout.schedule(task, j);
            }
        }

        public void wakeup() {
            Selector selector = this._selector;
            if (selector != null) {
                selector.wakeup();
            }
        }

        Selector getSelector() {
            return this._selector;
        }

        void stop() throws Exception {
            boolean z = true;
            while (z) {
                wakeup();
                z = this._selecting;
            }
            Iterator it = new ArrayList(this._selector.keys()).iterator();
            while (it.hasNext()) {
                SelectionKey selectionKey = (SelectionKey) it.next();
                if (selectionKey != null) {
                    Object attachment = selectionKey.attachment();
                    if (attachment instanceof EndPoint) {
                        try {
                            ((EndPoint) attachment).close();
                        } catch (IOException e) {
                            Log.ignore(e);
                        }
                    }
                }
            }
            synchronized (this) {
                boolean z2 = this._selecting;
                while (z2) {
                    wakeup();
                    z2 = this._selecting;
                }
                this._idleTimeout.cancelAll();
                this._retryTimeout.cancelAll();
                try {
                    Selector selector = this._selector;
                    if (selector != null) {
                        selector.close();
                    }
                } catch (IOException e2) {
                    Log.ignore(e2);
                }
                this._selector = null;
            }
        }
    }

    private static class ChangeSelectableChannel {
        final Object _attachment;
        final SelectableChannel _channel;

        public ChangeSelectableChannel(SelectableChannel selectableChannel, Object obj) {
            this._channel = selectableChannel;
            this._attachment = obj;
        }
    }
}
