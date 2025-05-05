package org.mortbay.jetty.nio;

import org.mortbay.io.Buffer;
import org.mortbay.io.nio.DirectNIOBuffer;
import org.mortbay.io.nio.IndirectNIOBuffer;
import org.mortbay.jetty.AbstractConnector;

/* loaded from: classes3.dex */
public abstract class AbstractNIOConnector extends AbstractConnector implements NIOConnector {
    private boolean _useDirectBuffers = true;

    @Override // org.mortbay.jetty.nio.NIOConnector
    public boolean getUseDirectBuffers() {
        return this._useDirectBuffers;
    }

    public void setUseDirectBuffers(boolean z) {
        this._useDirectBuffers = z;
    }

    @Override // org.mortbay.jetty.AbstractBuffers
    protected Buffer newBuffer(int i) {
        if (i == getHeaderBufferSize()) {
            return new IndirectNIOBuffer(i);
        }
        return this._useDirectBuffers ? new DirectNIOBuffer(i) : new IndirectNIOBuffer(i);
    }
}
