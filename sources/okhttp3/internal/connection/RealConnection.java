package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.Connection;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.Version;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http1.Http1Codec;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Http2Codec;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.ws.RealWebSocket;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;
import org.mortbay.jetty.HttpHeaderValues;

/* loaded from: classes3.dex */
public final class RealConnection extends Http2Connection.Listener implements Connection {
    private static final int MAX_TUNNEL_ATTEMPTS = 21;
    private static final String NPE_THROW_WITH_NULL = "throw with null exception";
    private final ConnectionPool connectionPool;
    private Handshake handshake;
    private Http2Connection http2Connection;
    public boolean noNewStreams;
    private Protocol protocol;
    private Socket rawSocket;
    private final Route route;
    private BufferedSink sink;
    private Socket socket;
    private BufferedSource source;
    public int successCount;
    public int allocationLimit = 1;
    public final List<Reference<StreamAllocation>> allocations = new ArrayList();
    public long idleAtNanos = Long.MAX_VALUE;

    public RealConnection(ConnectionPool connectionPool, Route route) {
        this.connectionPool = connectionPool;
        this.route = route;
    }

    public static RealConnection testConnection(ConnectionPool connectionPool, Route route, Socket socket, long j) {
        RealConnection realConnection = new RealConnection(connectionPool, route);
        realConnection.socket = socket;
        realConnection.idleAtNanos = j;
        return realConnection;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00f1 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x013f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0132  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void connect(int r17, int r18, int r19, int r20, boolean r21, okhttp3.Call r22, okhttp3.EventListener r23) {
        /*
            Method dump skipped, instructions count: 341
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnection.connect(int, int, int, int, boolean, okhttp3.Call, okhttp3.EventListener):void");
    }

    private void connectTunnel(int i, int i2, int i3, Call call, EventListener eventListener) throws IOException {
        Request createTunnelRequest = createTunnelRequest();
        HttpUrl url = createTunnelRequest.url();
        for (int i4 = 0; i4 < 21; i4++) {
            connectSocket(i, i2, call, eventListener);
            createTunnelRequest = createTunnel(i2, i3, createTunnelRequest, url);
            if (createTunnelRequest == null) {
                return;
            }
            Util.closeQuietly(this.rawSocket);
            this.rawSocket = null;
            this.sink = null;
            this.source = null;
            eventListener.connectEnd(call, this.route.socketAddress(), this.route.proxy(), null);
        }
    }

    private void connectSocket(int i, int i2, Call call, EventListener eventListener) throws IOException {
        Socket createSocket;
        Proxy proxy = this.route.proxy();
        Address address = this.route.address();
        if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.HTTP) {
            createSocket = address.socketFactory().createSocket();
        } else {
            createSocket = new Socket(proxy);
        }
        this.rawSocket = createSocket;
        eventListener.connectStart(call, this.route.socketAddress(), proxy);
        this.rawSocket.setSoTimeout(i2);
        try {
            Platform.get().connectSocket(this.rawSocket, this.route.socketAddress(), i);
            try {
                this.source = Okio.buffer(Okio.source(this.rawSocket));
                this.sink = Okio.buffer(Okio.sink(this.rawSocket));
            } catch (NullPointerException e) {
                if (NPE_THROW_WITH_NULL.equals(e.getMessage())) {
                    throw new IOException(e);
                }
            }
        } catch (ConnectException e2) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.route.socketAddress());
            connectException.initCause(e2);
            throw connectException;
        }
    }

    private void establishProtocol(ConnectionSpecSelector connectionSpecSelector, int i, Call call, EventListener eventListener) throws IOException {
        if (this.route.address().sslSocketFactory() == null) {
            if (this.route.address().protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE)) {
                this.socket = this.rawSocket;
                this.protocol = Protocol.H2_PRIOR_KNOWLEDGE;
                startHttp2(i);
                return;
            } else {
                this.socket = this.rawSocket;
                this.protocol = Protocol.HTTP_1_1;
                return;
            }
        }
        eventListener.secureConnectStart(call);
        connectTls(connectionSpecSelector);
        eventListener.secureConnectEnd(call, this.handshake);
        if (this.protocol == Protocol.HTTP_2) {
            startHttp2(i);
        }
    }

    private void startHttp2(int i) throws IOException {
        this.socket.setSoTimeout(0);
        Http2Connection build = new Http2Connection.Builder(true).socket(this.socket, this.route.address().url().host(), this.source, this.sink).listener(this).pingIntervalMillis(i).build();
        this.http2Connection = build;
        build.start();
    }

    private void connectTls(ConnectionSpecSelector connectionSpecSelector) throws IOException {
        Protocol protocol;
        Address address = this.route.address();
        SSLSocket sSLSocket = null;
        try {
            try {
                SSLSocket sSLSocket2 = (SSLSocket) address.sslSocketFactory().createSocket(this.rawSocket, address.url().host(), address.url().port(), true);
                try {
                    ConnectionSpec configureSecureSocket = connectionSpecSelector.configureSecureSocket(sSLSocket2);
                    if (configureSecureSocket.supportsTlsExtensions()) {
                        Platform.get().configureTlsExtensions(sSLSocket2, address.url().host(), address.protocols());
                    }
                    sSLSocket2.startHandshake();
                    SSLSession session = sSLSocket2.getSession();
                    Handshake handshake = Handshake.get(session);
                    if (!address.hostnameVerifier().verify(address.url().host(), session)) {
                        List<Certificate> peerCertificates = handshake.peerCertificates();
                        if (!peerCertificates.isEmpty()) {
                            X509Certificate x509Certificate = (X509Certificate) peerCertificates.get(0);
                            throw new SSLPeerUnverifiedException("Hostname " + address.url().host() + " not verified:\n    certificate: " + CertificatePinner.pin(x509Certificate) + "\n    DN: " + x509Certificate.getSubjectDN().getName() + "\n    subjectAltNames: " + OkHostnameVerifier.allSubjectAltNames(x509Certificate));
                        }
                        throw new SSLPeerUnverifiedException("Hostname " + address.url().host() + " not verified (no certificates)");
                    }
                    address.certificatePinner().check(address.url().host(), handshake.peerCertificates());
                    String selectedProtocol = configureSecureSocket.supportsTlsExtensions() ? Platform.get().getSelectedProtocol(sSLSocket2) : null;
                    this.socket = sSLSocket2;
                    this.source = Okio.buffer(Okio.source(sSLSocket2));
                    this.sink = Okio.buffer(Okio.sink(this.socket));
                    this.handshake = handshake;
                    if (selectedProtocol != null) {
                        protocol = Protocol.get(selectedProtocol);
                    } else {
                        protocol = Protocol.HTTP_1_1;
                    }
                    this.protocol = protocol;
                    if (sSLSocket2 != null) {
                        Platform.get().afterHandshake(sSLSocket2);
                    }
                } catch (AssertionError e) {
                    e = e;
                    if (!Util.isAndroidGetsocknameError(e)) {
                        throw e;
                    }
                    throw new IOException(e);
                } catch (Throwable th) {
                    th = th;
                    sSLSocket = sSLSocket2;
                    if (sSLSocket != null) {
                        Platform.get().afterHandshake(sSLSocket);
                    }
                    Util.closeQuietly((Socket) sSLSocket);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (AssertionError e2) {
            e = e2;
        }
    }

    private Request createTunnel(int i, int i2, Request request, HttpUrl httpUrl) throws IOException {
        String str = "CONNECT " + Util.hostHeader(httpUrl, true) + " HTTP/1.1";
        while (true) {
            Http1Codec http1Codec = new Http1Codec(null, null, this.source, this.sink);
            this.source.timeout().timeout(i, TimeUnit.MILLISECONDS);
            this.sink.timeout().timeout(i2, TimeUnit.MILLISECONDS);
            http1Codec.writeRequest(request.headers(), str);
            http1Codec.finishRequest();
            Response build = http1Codec.readResponseHeaders(false).request(request).build();
            long contentLength = HttpHeaders.contentLength(build);
            if (contentLength == -1) {
                contentLength = 0;
            }
            Source newFixedLengthSource = http1Codec.newFixedLengthSource(contentLength);
            Util.skipAll(newFixedLengthSource, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            newFixedLengthSource.close();
            int code = build.code();
            if (code == 200) {
                if (this.source.buffer().exhausted() && this.sink.buffer().exhausted()) {
                    return null;
                }
                throw new IOException("TLS tunnel buffered too many bytes!");
            }
            if (code == 407) {
                Request authenticate = this.route.address().proxyAuthenticator().authenticate(this.route, build);
                if (authenticate == null) {
                    throw new IOException("Failed to authenticate with proxy");
                }
                if (HttpHeaderValues.CLOSE.equalsIgnoreCase(build.header("Connection"))) {
                    return authenticate;
                }
                request = authenticate;
            } else {
                throw new IOException("Unexpected response code for CONNECT: " + build.code());
            }
        }
    }

    private Request createTunnelRequest() throws IOException {
        Request build = new Request.Builder().url(this.route.address().url()).method("CONNECT", null).header("Host", Util.hostHeader(this.route.address().url(), true)).header(org.mortbay.jetty.HttpHeaders.PROXY_CONNECTION, "Keep-Alive").header("User-Agent", Version.userAgent()).build();
        Request authenticate = this.route.address().proxyAuthenticator().authenticate(this.route, new Response.Builder().request(build).protocol(Protocol.HTTP_1_1).code(407).message("Preemptive Authenticate").body(Util.EMPTY_RESPONSE).sentRequestAtMillis(-1L).receivedResponseAtMillis(-1L).header("Proxy-Authenticate", "OkHttp-Preemptive").build());
        return authenticate != null ? authenticate : build;
    }

    public boolean isEligible(Address address, @Nullable Route route) {
        if (this.allocations.size() >= this.allocationLimit || this.noNewStreams || !Internal.instance.equalsNonHost(this.route.address(), address)) {
            return false;
        }
        if (address.url().host().equals(route().address().url().host())) {
            return true;
        }
        if (this.http2Connection == null || route == null || route.proxy().type() != Proxy.Type.DIRECT || this.route.proxy().type() != Proxy.Type.DIRECT || !this.route.socketAddress().equals(route.socketAddress()) || route.address().hostnameVerifier() != OkHostnameVerifier.INSTANCE || !supportsUrl(address.url())) {
            return false;
        }
        try {
            address.certificatePinner().check(address.url().host(), handshake().peerCertificates());
            return true;
        } catch (SSLPeerUnverifiedException unused) {
            return false;
        }
    }

    public boolean supportsUrl(HttpUrl httpUrl) {
        if (httpUrl.port() != this.route.address().url().port()) {
            return false;
        }
        if (httpUrl.host().equals(this.route.address().url().host())) {
            return true;
        }
        return this.handshake != null && OkHostnameVerifier.INSTANCE.verify(httpUrl.host(), (X509Certificate) this.handshake.peerCertificates().get(0));
    }

    public HttpCodec newCodec(OkHttpClient okHttpClient, Interceptor.Chain chain, StreamAllocation streamAllocation) throws SocketException {
        if (this.http2Connection != null) {
            return new Http2Codec(okHttpClient, chain, streamAllocation, this.http2Connection);
        }
        this.socket.setSoTimeout(chain.readTimeoutMillis());
        this.source.timeout().timeout(chain.readTimeoutMillis(), TimeUnit.MILLISECONDS);
        this.sink.timeout().timeout(chain.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
        return new Http1Codec(okHttpClient, streamAllocation, this.source, this.sink);
    }

    /* renamed from: okhttp3.internal.connection.RealConnection$1 */
    class AnonymousClass1 extends RealWebSocket.Streams {
        final /* synthetic */ StreamAllocation val$streamAllocation;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(boolean z, BufferedSource bufferedSource, BufferedSink bufferedSink, StreamAllocation streamAllocation) {
            super(z, bufferedSource, bufferedSink);
            r5 = streamAllocation;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            StreamAllocation streamAllocation = r5;
            streamAllocation.streamFinished(true, streamAllocation.codec(), -1L, null);
        }
    }

    public RealWebSocket.Streams newWebSocketStreams(StreamAllocation streamAllocation) {
        return new RealWebSocket.Streams(true, this.source, this.sink) { // from class: okhttp3.internal.connection.RealConnection.1
            final /* synthetic */ StreamAllocation val$streamAllocation;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(boolean z, BufferedSource bufferedSource, BufferedSink bufferedSink, StreamAllocation streamAllocation2) {
                super(z, bufferedSource, bufferedSink);
                r5 = streamAllocation2;
            }

            @Override // java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                StreamAllocation streamAllocation2 = r5;
                streamAllocation2.streamFinished(true, streamAllocation2.codec(), -1L, null);
            }
        };
    }

    @Override // okhttp3.Connection
    public Route route() {
        return this.route;
    }

    public void cancel() {
        Util.closeQuietly(this.rawSocket);
    }

    @Override // okhttp3.Connection
    public Socket socket() {
        return this.socket;
    }

    public boolean isHealthy(boolean z) {
        if (this.socket.isClosed() || this.socket.isInputShutdown() || this.socket.isOutputShutdown()) {
            return false;
        }
        Http2Connection http2Connection = this.http2Connection;
        if (http2Connection != null) {
            return http2Connection.isHealthy(System.nanoTime());
        }
        if (z) {
            try {
                int soTimeout = this.socket.getSoTimeout();
                try {
                    this.socket.setSoTimeout(1);
                    return !this.source.exhausted();
                } finally {
                    this.socket.setSoTimeout(soTimeout);
                }
            } catch (SocketTimeoutException unused) {
            } catch (IOException unused2) {
                return false;
            }
        }
        return true;
    }

    @Override // okhttp3.internal.http2.Http2Connection.Listener
    public void onStream(Http2Stream http2Stream) throws IOException {
        http2Stream.close(ErrorCode.REFUSED_STREAM);
    }

    @Override // okhttp3.internal.http2.Http2Connection.Listener
    public void onSettings(Http2Connection http2Connection) {
        synchronized (this.connectionPool) {
            this.allocationLimit = http2Connection.maxConcurrentStreams();
        }
    }

    @Override // okhttp3.Connection
    public Handshake handshake() {
        return this.handshake;
    }

    public boolean isMultiplexed() {
        return this.http2Connection != null;
    }

    @Override // okhttp3.Connection
    public Protocol protocol() {
        return this.protocol;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Connection{");
        sb.append(this.route.address().url().host());
        sb.append(":");
        sb.append(this.route.address().url().port());
        sb.append(", proxy=");
        sb.append(this.route.proxy());
        sb.append(" hostAddress=");
        sb.append(this.route.socketAddress());
        sb.append(" cipherSuite=");
        Handshake handshake = this.handshake;
        sb.append(handshake != null ? handshake.cipherSuite() : "none");
        sb.append(" protocol=");
        sb.append(this.protocol);
        sb.append('}');
        return sb.toString();
    }
}
