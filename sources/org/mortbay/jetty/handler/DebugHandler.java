package org.mortbay.jetty.handler;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Locale;
import org.mortbay.util.DateCache;
import org.mortbay.util.RolloverFileOutputStream;

/* loaded from: classes3.dex */
public class DebugHandler extends HandlerWrapper {
    private DateCache _date = new DateCache("HH:mm:ss", Locale.US);
    private OutputStream _out;
    private PrintStream _print;

    /* JADX WARN: Removed duplicated region for block: B:48:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0235  */
    @Override // org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.Handler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void handle(java.lang.String r20, javax.servlet.http.HttpServletRequest r21, javax.servlet.http.HttpServletResponse r22, int r23) throws java.io.IOException, javax.servlet.ServletException {
        /*
            Method dump skipped, instructions count: 672
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.handler.DebugHandler.handle(java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, int):void");
    }

    @Override // org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.handler.AbstractHandler, org.mortbay.component.AbstractLifeCycle
    protected void doStart() throws Exception {
        if (this._out == null) {
            this._out = new RolloverFileOutputStream("./logs/yyyy_mm_dd.debug.log", true);
        }
        this._print = new PrintStream(this._out);
        super.doStart();
    }

    @Override // org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.handler.AbstractHandler, org.mortbay.component.AbstractLifeCycle
    protected void doStop() throws Exception {
        super.doStop();
        this._print.close();
    }

    public OutputStream getOutputStream() {
        return this._out;
    }

    public void setOutputStream(OutputStream outputStream) {
        this._out = outputStream;
    }
}
