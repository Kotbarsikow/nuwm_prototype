package org.mortbay.io.nio;

import java.nio.ByteBuffer;
import org.mortbay.io.Buffer;

/* loaded from: classes3.dex */
public interface NIOBuffer extends Buffer {
    ByteBuffer getByteBuffer();

    boolean isDirect();
}
