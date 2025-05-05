package com.google.api.client.extensions.jetty.auth.oauth2;

import com.google.api.client.extensions.java6.auth.oauth2.VerificationCodeReceiver;
import com.google.api.client.util.Throwables;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Semaphore;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.MimeTypes;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;

/* loaded from: classes2.dex */
public final class LocalServerReceiver implements VerificationCodeReceiver {
    private static final String CALLBACK_PATH = "/Callback";
    private static final String LOCALHOST = "localhost";
    private final String callbackPath;
    String code;
    String error;
    private String failureLandingPageUrl;
    private final String host;
    private int port;
    private Server server;
    private String successLandingPageUrl;
    final Semaphore waitUnlessSignaled;

    public LocalServerReceiver() {
        this(LOCALHOST, -1, CALLBACK_PATH, null, null);
    }

    LocalServerReceiver(String str, int i, String str2, String str3) {
        this(str, i, CALLBACK_PATH, str2, str3);
    }

    LocalServerReceiver(String str, int i, String str2, String str3, String str4) {
        this.waitUnlessSignaled = new Semaphore(0);
        this.host = str;
        this.port = i;
        this.callbackPath = str2;
        this.successLandingPageUrl = str3;
        this.failureLandingPageUrl = str4;
    }

    @Override // com.google.api.client.extensions.java6.auth.oauth2.VerificationCodeReceiver
    public String getRedirectUri() throws IOException {
        int i = this.port;
        if (i == -1) {
            i = 0;
        }
        Server server = new Server(i);
        this.server = server;
        Connector connector = server.getConnectors()[0];
        connector.setHost(this.host);
        this.server.addHandler(new CallbackHandler());
        try {
            this.server.start();
            this.port = connector.getLocalPort();
            String valueOf = String.valueOf(String.valueOf(this.host));
            int i2 = this.port;
            String valueOf2 = String.valueOf(String.valueOf(this.callbackPath));
            StringBuilder sb = new StringBuilder(valueOf.length() + 19 + valueOf2.length());
            sb.append("http://");
            sb.append(valueOf);
            sb.append(":");
            sb.append(i2);
            sb.append(valueOf2);
            return sb.toString();
        } catch (Exception e) {
            Throwables.propagateIfPossible(e);
            throw new IOException(e);
        }
    }

    @Override // com.google.api.client.extensions.java6.auth.oauth2.VerificationCodeReceiver
    public String waitForCode() throws IOException {
        this.waitUnlessSignaled.acquireUninterruptibly();
        if (this.error != null) {
            String valueOf = String.valueOf(String.valueOf(this.error));
            StringBuilder sb = new StringBuilder(valueOf.length() + 28);
            sb.append("User authorization failed (");
            sb.append(valueOf);
            sb.append(")");
            throw new IOException(sb.toString());
        }
        return this.code;
    }

    @Override // com.google.api.client.extensions.java6.auth.oauth2.VerificationCodeReceiver
    public void stop() throws IOException {
        this.waitUnlessSignaled.release();
        Server server = this.server;
        if (server != null) {
            try {
                server.stop();
                this.server = null;
            } catch (Exception e) {
                Throwables.propagateIfPossible(e);
                throw new IOException(e);
            }
        }
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public String getCallbackPath() {
        return this.callbackPath;
    }

    public static final class Builder {
        private String failureLandingPageUrl;
        private String successLandingPageUrl;
        private String host = LocalServerReceiver.LOCALHOST;
        private int port = -1;
        private String callbackPath = LocalServerReceiver.CALLBACK_PATH;

        public LocalServerReceiver build() {
            return new LocalServerReceiver(this.host, this.port, this.callbackPath, this.successLandingPageUrl, this.failureLandingPageUrl);
        }

        public String getHost() {
            return this.host;
        }

        public Builder setHost(String str) {
            this.host = str;
            return this;
        }

        public int getPort() {
            return this.port;
        }

        public Builder setPort(int i) {
            this.port = i;
            return this;
        }

        public String getCallbackPath() {
            return this.callbackPath;
        }

        public Builder setCallbackPath(String str) {
            this.callbackPath = str;
            return this;
        }

        public Builder setLandingPages(String str, String str2) {
            this.successLandingPageUrl = str;
            this.failureLandingPageUrl = str2;
            return this;
        }
    }

    class CallbackHandler extends AbstractHandler {
        CallbackHandler() {
        }

        @Override // org.mortbay.jetty.Handler
        public void handle(String str, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int i) throws IOException {
            if (LocalServerReceiver.CALLBACK_PATH.equals(str)) {
                try {
                    ((Request) httpServletRequest).setHandled(true);
                    LocalServerReceiver.this.error = httpServletRequest.getParameter(Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                    LocalServerReceiver.this.code = httpServletRequest.getParameter("code");
                    if (LocalServerReceiver.this.error == null && LocalServerReceiver.this.successLandingPageUrl != null) {
                        httpServletResponse.sendRedirect(LocalServerReceiver.this.successLandingPageUrl);
                    } else if (LocalServerReceiver.this.error != null && LocalServerReceiver.this.failureLandingPageUrl != null) {
                        httpServletResponse.sendRedirect(LocalServerReceiver.this.failureLandingPageUrl);
                    } else {
                        writeLandingHtml(httpServletResponse);
                    }
                    httpServletResponse.flushBuffer();
                } finally {
                    LocalServerReceiver.this.waitUnlessSignaled.release();
                }
            }
        }

        private void writeLandingHtml(HttpServletResponse httpServletResponse) throws IOException {
            httpServletResponse.setStatus(200);
            httpServletResponse.setContentType(MimeTypes.TEXT_HTML);
            PrintWriter writer = httpServletResponse.getWriter();
            writer.println("<html>");
            writer.println("<head><title>OAuth 2.0 Authentication Token Received</title></head>");
            writer.println("<body>");
            writer.println("Received verification code. You may now close this window.");
            writer.println("</body>");
            writer.println("</html>");
            writer.flush();
        }
    }
}
