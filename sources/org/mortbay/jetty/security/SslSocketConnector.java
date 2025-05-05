package org.mortbay.jetty.security;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import org.mortbay.io.EndPoint;
import org.mortbay.io.bio.SocketEndPoint;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.log.Log;
import org.mortbay.resource.Resource;

/* loaded from: classes3.dex */
public class SslSocketConnector extends SocketConnector {
    static final String CACHED_INFO_ATTR;
    public static final String DEFAULT_KEYSTORE;
    public static final String KEYPASSWORD_PROPERTY = "jetty.ssl.keypassword";
    public static final String PASSWORD_PROPERTY = "jetty.ssl.password";
    static /* synthetic */ Class class$org$mortbay$jetty$security$SslSocketConnector$CachedInfo;
    private boolean _allowRenegotiate;
    private int _handshakeTimeout;
    private transient Password _keyPassword;
    private transient Password _password;
    private String _provider;
    private String _secureRandomAlgorithm;
    private String _sslKeyManagerFactoryAlgorithm;
    private String _sslTrustManagerFactoryAlgorithm;
    private transient Password _trustPassword;
    private String _truststore;
    private String _truststoreType;
    private boolean _wantClientAuth;
    private String[] _excludeCipherSuites = null;
    private String _keystore = DEFAULT_KEYSTORE;
    private String _keystoreType = "JKS";
    private boolean _needClientAuth = false;
    private String _protocol = "TLS";

    static {
        Class cls = class$org$mortbay$jetty$security$SslSocketConnector$CachedInfo;
        if (cls == null) {
            cls = class$("org.mortbay.jetty.security.SslSocketConnector$CachedInfo");
            class$org$mortbay$jetty$security$SslSocketConnector$CachedInfo = cls;
        }
        CACHED_INFO_ATTR = cls.getName();
        DEFAULT_KEYSTORE = new StringBuffer().append(System.getProperty("user.home")).append(File.separator).append(".keystore").toString();
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    private static X509Certificate[] getCertChain(SSLSession sSLSession) {
        try {
            javax.security.cert.X509Certificate[] peerCertificateChain = sSLSession.getPeerCertificateChain();
            if (peerCertificateChain != null && peerCertificateChain.length != 0) {
                int length = peerCertificateChain.length;
                X509Certificate[] x509CertificateArr = new X509Certificate[length];
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                for (int i = 0; i < length; i++) {
                    x509CertificateArr[i] = (X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(peerCertificateChain[i].getEncoded()));
                }
                return x509CertificateArr;
            }
            return null;
        } catch (SSLPeerUnverifiedException unused) {
            return null;
        } catch (Exception e) {
            Log.warn(Log.EXCEPTION, (Throwable) e);
            return null;
        }
    }

    public SslSocketConnector() {
        this._sslKeyManagerFactoryAlgorithm = Security.getProperty("ssl.KeyManagerFactory.algorithm") == null ? "SunX509" : Security.getProperty("ssl.KeyManagerFactory.algorithm");
        this._sslTrustManagerFactoryAlgorithm = Security.getProperty("ssl.TrustManagerFactory.algorithm") != null ? Security.getProperty("ssl.TrustManagerFactory.algorithm") : "SunX509";
        this._truststoreType = "JKS";
        this._wantClientAuth = false;
        this._handshakeTimeout = 0;
        this._allowRenegotiate = false;
    }

    public boolean isAllowRenegotiate() {
        return this._allowRenegotiate;
    }

    public void setAllowRenegotiate(boolean z) {
        this._allowRenegotiate = z;
    }

    @Override // org.mortbay.jetty.bio.SocketConnector, org.mortbay.jetty.AbstractConnector
    public void accept(int i) throws IOException, InterruptedException {
        try {
            Socket accept = this._serverSocket.accept();
            configure(accept);
            new SslConnection(accept).dispatch();
        } catch (SSLException e) {
            Log.warn(e);
            try {
                stop();
            } catch (Exception e2) {
                Log.warn(e2);
                throw new IllegalStateException(e2.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mortbay.jetty.AbstractConnector
    public void configure(Socket socket) throws IOException {
        super.configure(socket);
    }

    protected SSLServerSocketFactory createFactory() throws Exception {
        InputStream inputStream;
        InputStream inputStream2;
        if (this._truststore == null) {
            this._truststore = this._keystore;
            this._truststoreType = this._keystoreType;
        }
        InputStream inputStream3 = null;
        InputStream inputStream4 = null;
        try {
            String str = this._keystore;
            inputStream = str != null ? Resource.newResource(str).getInputStream() : null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            KeyStore keyStore = KeyStore.getInstance(this._keystoreType);
            Password password = this._password;
            keyStore.load(inputStream, password == null ? null : password.toString().toCharArray());
            if (inputStream != null) {
                inputStream.close();
            }
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(this._sslKeyManagerFactoryAlgorithm);
            Password password2 = this._keyPassword;
            keyManagerFactory.init(keyStore, password2 == null ? null : password2.toString().toCharArray());
            KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();
            try {
                String str2 = this._truststore;
                inputStream2 = str2 != null ? Resource.newResource(str2).getInputStream() : null;
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                KeyStore keyStore2 = KeyStore.getInstance(this._truststoreType);
                Password password3 = this._trustPassword;
                keyStore2.load(inputStream2, password3 == null ? null : password3.toString().toCharArray());
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(this._sslTrustManagerFactoryAlgorithm);
                trustManagerFactory.init(keyStore2);
                TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
                String str3 = this._secureRandomAlgorithm;
                SecureRandom secureRandom = str3 != null ? SecureRandom.getInstance(str3) : null;
                String str4 = this._provider;
                SSLContext sSLContext = str4 == null ? SSLContext.getInstance(this._protocol) : SSLContext.getInstance(this._protocol, str4);
                sSLContext.init(keyManagers, trustManagers, secureRandom);
                return sSLContext.getServerSocketFactory();
            } catch (Throwable th3) {
                th = th3;
                inputStream4 = inputStream2;
                if (inputStream4 != null) {
                    inputStream4.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            InputStream inputStream5 = inputStream;
            th = th4;
            inputStream3 = inputStream5;
            if (inputStream3 != null) {
                inputStream3.close();
            }
            throw th;
        }
    }

    @Override // org.mortbay.jetty.bio.SocketConnector, org.mortbay.jetty.AbstractConnector, org.mortbay.jetty.Connector
    public void customize(EndPoint endPoint, Request request) throws IOException {
        Object obj;
        Object obj2;
        super.customize(endPoint, request);
        request.setScheme("https");
        try {
            SSLSession session = ((SSLSocket) ((SocketEndPoint) endPoint).getTransport()).getSession();
            String cipherSuite = session.getCipherSuite();
            String str = CACHED_INFO_ATTR;
            CachedInfo cachedInfo = (CachedInfo) session.getValue(str);
            if (cachedInfo != null) {
                obj = cachedInfo.getKeySize();
                obj2 = cachedInfo.getCerts();
            } else {
                Integer num = new Integer(ServletSSL.deduceKeyLength(cipherSuite));
                X509Certificate[] certChain = getCertChain(session);
                session.putValue(str, new CachedInfo(num, certChain));
                obj = num;
                obj2 = certChain;
            }
            if (obj2 != null) {
                request.setAttribute("javax.servlet.request.X509Certificate", obj2);
            } else if (this._needClientAuth) {
                throw new IllegalStateException("no client auth");
            }
            request.setAttribute("javax.servlet.request.cipher_suite", cipherSuite);
            request.setAttribute("javax.servlet.request.key_size", obj);
        } catch (Exception e) {
            Log.warn(Log.EXCEPTION, (Throwable) e);
        }
    }

    public String[] getExcludeCipherSuites() {
        return this._excludeCipherSuites;
    }

    public String getKeystore() {
        return this._keystore;
    }

    public String getKeystoreType() {
        return this._keystoreType;
    }

    public boolean getNeedClientAuth() {
        return this._needClientAuth;
    }

    public String getProtocol() {
        return this._protocol;
    }

    public String getProvider() {
        return this._provider;
    }

    public String getSecureRandomAlgorithm() {
        return this._secureRandomAlgorithm;
    }

    public String getSslKeyManagerFactoryAlgorithm() {
        return this._sslKeyManagerFactoryAlgorithm;
    }

    public String getSslTrustManagerFactoryAlgorithm() {
        return this._sslTrustManagerFactoryAlgorithm;
    }

    public String getTruststore() {
        return this._truststore;
    }

    public String getTruststoreType() {
        return this._truststoreType;
    }

    public boolean getWantClientAuth() {
        return this._wantClientAuth;
    }

    @Override // org.mortbay.jetty.AbstractConnector, org.mortbay.jetty.Connector
    public boolean isConfidential(Request request) {
        int confidentialPort = getConfidentialPort();
        return confidentialPort == 0 || confidentialPort == request.getServerPort();
    }

    @Override // org.mortbay.jetty.AbstractConnector, org.mortbay.jetty.Connector
    public boolean isIntegral(Request request) {
        int integralPort = getIntegralPort();
        return integralPort == 0 || integralPort == request.getServerPort();
    }

    @Override // org.mortbay.jetty.bio.SocketConnector
    protected ServerSocket newServerSocket(String str, int i, int i2) throws IOException {
        try {
            SSLServerSocketFactory createFactory = createFactory();
            SSLServerSocket sSLServerSocket = (SSLServerSocket) (str == null ? createFactory.createServerSocket(i, i2) : createFactory.createServerSocket(i, i2, InetAddress.getByName(str)));
            boolean z = this._wantClientAuth;
            if (z) {
                sSLServerSocket.setWantClientAuth(z);
            }
            boolean z2 = this._needClientAuth;
            if (z2) {
                sSLServerSocket.setNeedClientAuth(z2);
            }
            String[] strArr = this._excludeCipherSuites;
            if (strArr != null && strArr.length > 0) {
                List<String> asList = Arrays.asList(strArr);
                ArrayList arrayList = new ArrayList(Arrays.asList(sSLServerSocket.getEnabledCipherSuites()));
                for (String str2 : asList) {
                    if (arrayList.contains(str2)) {
                        arrayList.remove(str2);
                    }
                }
                sSLServerSocket.setEnabledCipherSuites((String[]) arrayList.toArray(new String[arrayList.size()]));
            }
            return sSLServerSocket;
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            Log.warn(e2.toString());
            Log.debug(e2);
            throw new IOException(new StringBuffer("!JsseListener: ").append(e2).toString());
        }
    }

    public void setExcludeCipherSuites(String[] strArr) {
        this._excludeCipherSuites = strArr;
    }

    public void setKeyPassword(String str) {
        this._keyPassword = Password.getPassword(KEYPASSWORD_PROPERTY, str, null);
    }

    public void setKeystore(String str) {
        this._keystore = str;
    }

    public void setKeystoreType(String str) {
        this._keystoreType = str;
    }

    public void setNeedClientAuth(boolean z) {
        this._needClientAuth = z;
    }

    public void setPassword(String str) {
        this._password = Password.getPassword(PASSWORD_PROPERTY, str, null);
    }

    public void setTrustPassword(String str) {
        this._trustPassword = Password.getPassword(PASSWORD_PROPERTY, str, null);
    }

    public void setProtocol(String str) {
        this._protocol = str;
    }

    public void setProvider(String str) {
        this._provider = str;
    }

    public void setSecureRandomAlgorithm(String str) {
        this._secureRandomAlgorithm = str;
    }

    public void setSslKeyManagerFactoryAlgorithm(String str) {
        this._sslKeyManagerFactoryAlgorithm = str;
    }

    public void setSslTrustManagerFactoryAlgorithm(String str) {
        this._sslTrustManagerFactoryAlgorithm = str;
    }

    public void setTruststore(String str) {
        this._truststore = str;
    }

    public void setTruststoreType(String str) {
        this._truststoreType = str;
    }

    public void setWantClientAuth(boolean z) {
        this._wantClientAuth = z;
    }

    public void setHandshakeTimeout(int i) {
        this._handshakeTimeout = i;
    }

    public int getHandshakeTimeout() {
        return this._handshakeTimeout;
    }

    private class CachedInfo {
        private X509Certificate[] _certs;
        private Integer _keySize;

        CachedInfo(Integer num, X509Certificate[] x509CertificateArr) {
            this._keySize = num;
            this._certs = x509CertificateArr;
        }

        X509Certificate[] getCerts() {
            return this._certs;
        }

        Integer getKeySize() {
            return this._keySize;
        }
    }

    public class SslConnection extends SocketConnector.Connection {
        public SslConnection(Socket socket) throws IOException {
            super(socket);
        }

        @Override // org.mortbay.io.bio.SocketEndPoint, org.mortbay.io.bio.StreamEndPoint, org.mortbay.io.EndPoint
        public void shutdownOutput() throws IOException {
            close();
        }

        @Override // org.mortbay.jetty.bio.SocketConnector.Connection, java.lang.Runnable
        public void run() {
            try {
                int handshakeTimeout = SslSocketConnector.this.getHandshakeTimeout();
                int soTimeout = this._socket.getSoTimeout();
                if (handshakeTimeout > 0) {
                    this._socket.setSoTimeout(handshakeTimeout);
                }
                final SSLSocket sSLSocket = (SSLSocket) this._socket;
                sSLSocket.addHandshakeCompletedListener(new HandshakeCompletedListener() { // from class: org.mortbay.jetty.security.SslSocketConnector.SslConnection.1
                    boolean handshook = false;

                    @Override // javax.net.ssl.HandshakeCompletedListener
                    public void handshakeCompleted(HandshakeCompletedEvent handshakeCompletedEvent) {
                        if (this.handshook) {
                            if (SslSocketConnector.this._allowRenegotiate) {
                                return;
                            }
                            Log.warn(new StringBuffer("SSL renegotiate denied: ").append(sSLSocket).toString());
                            try {
                                sSLSocket.close();
                                return;
                            } catch (IOException e) {
                                Log.warn(e);
                                return;
                            }
                        }
                        this.handshook = true;
                    }
                });
                sSLSocket.startHandshake();
                if (handshakeTimeout > 0) {
                    this._socket.setSoTimeout(soTimeout);
                }
                super.run();
            } catch (SSLException e) {
                Log.warn(e);
                try {
                    close();
                } catch (IOException e2) {
                    Log.ignore(e2);
                }
            } catch (IOException e3) {
                Log.debug(e3);
                try {
                    close();
                } catch (IOException e4) {
                    Log.ignore(e4);
                }
            }
        }
    }
}
