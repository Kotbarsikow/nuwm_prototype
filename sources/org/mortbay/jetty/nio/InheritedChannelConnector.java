package org.mortbay.jetty.nio;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import org.mortbay.log.Log;

/* loaded from: classes3.dex */
public class InheritedChannelConnector extends SelectChannelConnector {
    static /* synthetic */ Class class$java$lang$System;

    @Override // org.mortbay.jetty.nio.SelectChannelConnector, org.mortbay.jetty.Connector
    public void open() throws IOException {
        synchronized (this) {
            try {
                Class cls = class$java$lang$System;
                if (cls == null) {
                    cls = class$("java.lang.System");
                    class$java$lang$System = cls;
                }
                Method method = cls.getMethod("inheritedChannel", null);
                if (method != null) {
                    Channel channel = (Channel) method.invoke(null, null);
                    if (channel instanceof ServerSocketChannel) {
                        this._acceptChannel = (ServerSocketChannel) channel;
                    }
                }
                if (this._acceptChannel != null) {
                    this._acceptChannel.configureBlocking(false);
                }
            } catch (Exception e) {
                Log.warn(e);
            }
            if (this._acceptChannel == null) {
                super.open();
            } else {
                throw new IOException("No System.inheritedChannel()");
            }
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }
}
