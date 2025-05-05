package org.mortbay.util.ajax;

import org.mortbay.log.Log;

/* loaded from: classes3.dex */
public class WaitingContinuation implements Continuation {
    Object _mutex;
    boolean _new;
    Object _object;
    boolean _pending;
    boolean _resumed;

    public WaitingContinuation() {
        this._new = true;
        this._resumed = false;
        this._pending = false;
        this._mutex = this;
    }

    public WaitingContinuation(Object obj) {
        this._new = true;
        this._resumed = false;
        this._pending = false;
        this._mutex = obj == null ? this : obj;
    }

    @Override // org.mortbay.util.ajax.Continuation
    public void resume() {
        synchronized (this._mutex) {
            this._resumed = true;
            this._mutex.notify();
        }
    }

    @Override // org.mortbay.util.ajax.Continuation
    public void reset() {
        synchronized (this._mutex) {
            this._resumed = false;
            this._pending = false;
            this._mutex.notify();
        }
    }

    @Override // org.mortbay.util.ajax.Continuation
    public boolean isNew() {
        return this._new;
    }

    @Override // org.mortbay.util.ajax.Continuation
    public boolean suspend(long j) {
        boolean z;
        synchronized (this._mutex) {
            this._new = false;
            this._pending = true;
            try {
                try {
                    if (!this._resumed && j >= 0) {
                        if (j == 0) {
                            this._mutex.wait();
                        } else if (j > 0) {
                            this._mutex.wait(j);
                        }
                    }
                    z = this._resumed;
                    this._resumed = false;
                } catch (InterruptedException e) {
                    Log.ignore(e);
                    z = this._resumed;
                    this._resumed = false;
                }
                this._pending = false;
            } catch (Throwable th) {
                this._resumed = false;
                this._pending = false;
                throw th;
            }
        }
        return z;
    }

    @Override // org.mortbay.util.ajax.Continuation
    public boolean isPending() {
        boolean z;
        synchronized (this._mutex) {
            z = this._pending;
        }
        return z;
    }

    @Override // org.mortbay.util.ajax.Continuation
    public boolean isResumed() {
        boolean z;
        synchronized (this._mutex) {
            z = this._resumed;
        }
        return z;
    }

    @Override // org.mortbay.util.ajax.Continuation
    public Object getObject() {
        return this._object;
    }

    @Override // org.mortbay.util.ajax.Continuation
    public void setObject(Object obj) {
        this._object = obj;
    }

    public Object getMutex() {
        return this._mutex;
    }

    public void setMutex(Object obj) {
        if (this._pending && obj != this._mutex) {
            throw new IllegalStateException();
        }
        if (obj == null) {
            obj = this;
        }
        this._mutex = obj;
    }

    public String toString() {
        String stringBuffer;
        synchronized (this) {
            stringBuffer = new StringBuffer("WaitingContinuation@").append(hashCode()).append(this._new ? ",new" : "").append(this._pending ? ",pending" : "").append(this._resumed ? ",resumed" : "").toString();
        }
        return stringBuffer;
    }
}
