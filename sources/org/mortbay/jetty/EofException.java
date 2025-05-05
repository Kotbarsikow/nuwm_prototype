package org.mortbay.jetty;

import java.io.EOFException;

/* loaded from: classes3.dex */
public class EofException extends EOFException {
    public EofException() {
    }

    public EofException(String str) {
        super(str);
    }

    public EofException(Throwable th) {
        initCause(th);
    }
}
