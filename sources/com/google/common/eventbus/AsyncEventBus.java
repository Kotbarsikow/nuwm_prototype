package com.google.common.eventbus;

import com.google.common.eventbus.EventBus;
import java.util.concurrent.Executor;
import org.mortbay.jetty.servlet.ServletHandler;

@ElementTypesAreNonnullByDefault
/* loaded from: classes2.dex */
public class AsyncEventBus extends EventBus {
    public AsyncEventBus(String str, Executor executor) {
        super(str, executor, Dispatcher.legacyAsync(), EventBus.LoggingHandler.INSTANCE);
    }

    public AsyncEventBus(Executor executor, SubscriberExceptionHandler subscriberExceptionHandler) {
        super(ServletHandler.__DEFAULT_SERVLET, executor, Dispatcher.legacyAsync(), subscriberExceptionHandler);
    }

    public AsyncEventBus(Executor executor) {
        super(ServletHandler.__DEFAULT_SERVLET, executor, Dispatcher.legacyAsync(), EventBus.LoggingHandler.INSTANCE);
    }
}
