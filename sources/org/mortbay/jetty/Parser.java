package org.mortbay.jetty;

import java.io.IOException;

/* loaded from: classes3.dex */
public interface Parser {
    boolean isComplete();

    boolean isIdle();

    boolean isMoreInBuffer() throws IOException;

    long parseAvailable() throws IOException;

    void reset(boolean z);
}
