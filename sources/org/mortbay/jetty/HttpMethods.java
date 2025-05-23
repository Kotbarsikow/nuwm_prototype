package org.mortbay.jetty;

import org.mortbay.io.Buffer;
import org.mortbay.io.BufferCache;

/* loaded from: classes3.dex */
public class HttpMethods {
    public static final BufferCache CACHE;
    public static final String CONNECT = "CONNECT";
    public static final Buffer CONNECT_BUFFER;
    public static final int CONNECT_ORDINAL = 8;
    public static final String DELETE = "DELETE";
    public static final Buffer DELETE_BUFFER;
    public static final int DELETE_ORDINAL = 6;
    public static final String GET = "GET";
    public static final Buffer GET_BUFFER;
    public static final int GET_ORDINAL = 1;
    public static final String HEAD = "HEAD";
    public static final Buffer HEAD_BUFFER;
    public static final int HEAD_ORDINAL = 3;
    public static final String MOVE = "MOVE";
    public static final Buffer MOVE_BUFFER;
    public static final int MOVE_ORDINAL = 9;
    public static final String OPTIONS = "OPTIONS";
    public static final Buffer OPTIONS_BUFFER;
    public static final int OPTIONS_ORDINAL = 5;
    public static final String POST = "POST";
    public static final Buffer POST_BUFFER;
    public static final int POST_ORDINAL = 2;
    public static final String PUT = "PUT";
    public static final Buffer PUT_BUFFER;
    public static final int PUT_ORDINAL = 4;
    public static final String TRACE = "TRACE";
    public static final Buffer TRACE_BUFFER;
    public static final int TRACE_ORDINAL = 7;

    static {
        BufferCache bufferCache = new BufferCache();
        CACHE = bufferCache;
        GET_BUFFER = bufferCache.add("GET", 1);
        POST_BUFFER = bufferCache.add("POST", 2);
        HEAD_BUFFER = bufferCache.add("HEAD", 3);
        PUT_BUFFER = bufferCache.add("PUT", 4);
        OPTIONS_BUFFER = bufferCache.add("OPTIONS", 5);
        DELETE_BUFFER = bufferCache.add("DELETE", 6);
        TRACE_BUFFER = bufferCache.add("TRACE", 7);
        CONNECT_BUFFER = bufferCache.add("CONNECT", 8);
        MOVE_BUFFER = bufferCache.add(MOVE, 9);
    }
}
