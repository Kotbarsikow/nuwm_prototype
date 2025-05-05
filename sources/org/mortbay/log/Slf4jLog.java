package org.mortbay.log;

import org.slf4j.LoggerFactory;

/* loaded from: classes3.dex */
public class Slf4jLog implements Logger {
    private org.slf4j.Logger logger;

    public Slf4jLog() throws Exception {
        this("org.mortbay.log");
    }

    public Slf4jLog(String str) {
        this.logger = LoggerFactory.getLogger(str);
    }

    @Override // org.mortbay.log.Logger
    public void debug(String str, Object obj, Object obj2) {
        this.logger.debug(str, obj, obj2);
    }

    @Override // org.mortbay.log.Logger
    public void debug(String str, Throwable th) {
        this.logger.debug(str, th);
    }

    @Override // org.mortbay.log.Logger
    public boolean isDebugEnabled() {
        return this.logger.isDebugEnabled();
    }

    @Override // org.mortbay.log.Logger
    public void info(String str, Object obj, Object obj2) {
        this.logger.info(str, obj, obj2);
    }

    @Override // org.mortbay.log.Logger
    public void warn(String str, Object obj, Object obj2) {
        this.logger.warn(str, obj, obj2);
    }

    @Override // org.mortbay.log.Logger
    public void warn(String str, Throwable th) {
        if ((th instanceof RuntimeException) || (th instanceof Error)) {
            this.logger.error(str, th);
        } else {
            this.logger.warn(str, th);
        }
    }

    @Override // org.mortbay.log.Logger
    public Logger getLogger(String str) {
        return new Slf4jLog(str);
    }

    public String toString() {
        return this.logger.toString();
    }

    @Override // org.mortbay.log.Logger
    public void setDebugEnabled(boolean z) {
        warn("setDebugEnabled not implemented", null, null);
    }
}
