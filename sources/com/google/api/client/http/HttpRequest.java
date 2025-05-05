package com.google.api.client.http;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sleeper;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/* loaded from: classes2.dex */
public final class HttpRequest {
    public static final int DEFAULT_NUMBER_OF_RETRIES = 10;
    public static final String USER_AGENT_SUFFIX = "Google-HTTP-Java-Client/1.23.0 (gzip)";
    public static final String VERSION = "1.23.0";

    @Deprecated
    private BackOffPolicy backOffPolicy;
    private HttpContent content;
    private HttpEncoding encoding;
    private HttpExecuteInterceptor executeInterceptor;
    private HttpIOExceptionHandler ioExceptionHandler;
    private ObjectParser objectParser;
    private String requestMethod;
    private HttpResponseInterceptor responseInterceptor;
    private boolean suppressUserAgentSuffix;
    private final HttpTransport transport;
    private HttpUnsuccessfulResponseHandler unsuccessfulResponseHandler;
    private GenericUrl url;
    private HttpHeaders headers = new HttpHeaders();
    private HttpHeaders responseHeaders = new HttpHeaders();
    private int numRetries = 10;
    private int contentLoggingLimit = 16384;
    private boolean loggingEnabled = true;
    private boolean curlLoggingEnabled = true;
    private int connectTimeout = AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH;
    private int readTimeout = AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH;
    private boolean followRedirects = true;
    private boolean throwExceptionOnExecuteError = true;

    @Deprecated
    private boolean retryOnExecuteIOException = false;
    private Sleeper sleeper = Sleeper.DEFAULT;

    HttpRequest(HttpTransport httpTransport, String str) {
        this.transport = httpTransport;
        setRequestMethod(str);
    }

    public HttpTransport getTransport() {
        return this.transport;
    }

    public String getRequestMethod() {
        return this.requestMethod;
    }

    public HttpRequest setRequestMethod(String str) {
        Preconditions.checkArgument(str == null || HttpMediaType.matchesToken(str));
        this.requestMethod = str;
        return this;
    }

    public GenericUrl getUrl() {
        return this.url;
    }

    public HttpRequest setUrl(GenericUrl genericUrl) {
        this.url = (GenericUrl) Preconditions.checkNotNull(genericUrl);
        return this;
    }

    public HttpContent getContent() {
        return this.content;
    }

    public HttpRequest setContent(HttpContent httpContent) {
        this.content = httpContent;
        return this;
    }

    public HttpEncoding getEncoding() {
        return this.encoding;
    }

    public HttpRequest setEncoding(HttpEncoding httpEncoding) {
        this.encoding = httpEncoding;
        return this;
    }

    @Deprecated
    public BackOffPolicy getBackOffPolicy() {
        return this.backOffPolicy;
    }

    @Deprecated
    public HttpRequest setBackOffPolicy(BackOffPolicy backOffPolicy) {
        this.backOffPolicy = backOffPolicy;
        return this;
    }

    public int getContentLoggingLimit() {
        return this.contentLoggingLimit;
    }

    public HttpRequest setContentLoggingLimit(int i) {
        Preconditions.checkArgument(i >= 0, "The content logging limit must be non-negative.");
        this.contentLoggingLimit = i;
        return this;
    }

    public boolean isLoggingEnabled() {
        return this.loggingEnabled;
    }

    public HttpRequest setLoggingEnabled(boolean z) {
        this.loggingEnabled = z;
        return this;
    }

    public boolean isCurlLoggingEnabled() {
        return this.curlLoggingEnabled;
    }

    public HttpRequest setCurlLoggingEnabled(boolean z) {
        this.curlLoggingEnabled = z;
        return this;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public HttpRequest setConnectTimeout(int i) {
        Preconditions.checkArgument(i >= 0);
        this.connectTimeout = i;
        return this;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public HttpRequest setReadTimeout(int i) {
        Preconditions.checkArgument(i >= 0);
        this.readTimeout = i;
        return this;
    }

    public HttpHeaders getHeaders() {
        return this.headers;
    }

    public HttpRequest setHeaders(HttpHeaders httpHeaders) {
        this.headers = (HttpHeaders) Preconditions.checkNotNull(httpHeaders);
        return this;
    }

    public HttpHeaders getResponseHeaders() {
        return this.responseHeaders;
    }

    public HttpRequest setResponseHeaders(HttpHeaders httpHeaders) {
        this.responseHeaders = (HttpHeaders) Preconditions.checkNotNull(httpHeaders);
        return this;
    }

    public HttpExecuteInterceptor getInterceptor() {
        return this.executeInterceptor;
    }

    public HttpRequest setInterceptor(HttpExecuteInterceptor httpExecuteInterceptor) {
        this.executeInterceptor = httpExecuteInterceptor;
        return this;
    }

    public HttpUnsuccessfulResponseHandler getUnsuccessfulResponseHandler() {
        return this.unsuccessfulResponseHandler;
    }

    public HttpRequest setUnsuccessfulResponseHandler(HttpUnsuccessfulResponseHandler httpUnsuccessfulResponseHandler) {
        this.unsuccessfulResponseHandler = httpUnsuccessfulResponseHandler;
        return this;
    }

    public HttpIOExceptionHandler getIOExceptionHandler() {
        return this.ioExceptionHandler;
    }

    public HttpRequest setIOExceptionHandler(HttpIOExceptionHandler httpIOExceptionHandler) {
        this.ioExceptionHandler = httpIOExceptionHandler;
        return this;
    }

    public HttpResponseInterceptor getResponseInterceptor() {
        return this.responseInterceptor;
    }

    public HttpRequest setResponseInterceptor(HttpResponseInterceptor httpResponseInterceptor) {
        this.responseInterceptor = httpResponseInterceptor;
        return this;
    }

    public int getNumberOfRetries() {
        return this.numRetries;
    }

    public HttpRequest setNumberOfRetries(int i) {
        Preconditions.checkArgument(i >= 0);
        this.numRetries = i;
        return this;
    }

    public HttpRequest setParser(ObjectParser objectParser) {
        this.objectParser = objectParser;
        return this;
    }

    public final ObjectParser getParser() {
        return this.objectParser;
    }

    public boolean getFollowRedirects() {
        return this.followRedirects;
    }

    public HttpRequest setFollowRedirects(boolean z) {
        this.followRedirects = z;
        return this;
    }

    public boolean getThrowExceptionOnExecuteError() {
        return this.throwExceptionOnExecuteError;
    }

    public HttpRequest setThrowExceptionOnExecuteError(boolean z) {
        this.throwExceptionOnExecuteError = z;
        return this;
    }

    @Deprecated
    public boolean getRetryOnExecuteIOException() {
        return this.retryOnExecuteIOException;
    }

    @Deprecated
    public HttpRequest setRetryOnExecuteIOException(boolean z) {
        this.retryOnExecuteIOException = z;
        return this;
    }

    public boolean getSuppressUserAgentSuffix() {
        return this.suppressUserAgentSuffix;
    }

    public HttpRequest setSuppressUserAgentSuffix(boolean z) {
        this.suppressUserAgentSuffix = z;
        return this;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(30:(1:9)|10|(1:12)|13|(1:175)(1:17)|(2:19|(23:21|(1:23)|24|(1:(1:27)(1:28))|29|(1:31)|32|(1:172)(1:36)|37|(7:39|(1:41)|42|(1:44)(3:166|(1:168)(1:170)|169)|(6:46|(4:48|(1:50)(1:163)|51|(4:53|(4:55|(1:57)(1:61)|58|(1:60))|62|(1:64)))(1:164)|162|(0)|62|(0))(1:165)|(1:66)|67)(1:171)|(2:69|(3:71|(1:73)|74))|(1:161)(1:77)|78|79|80|81|82|83|(3:111|112|(7:114|(1:116)(1:136)|(3:118|(1:(3:126|127|(2:129|130)))|120)|133|(1:135)|88|(2:91|(4:93|(1:95)|96|(1:108)(3:100|101|102))(1:109))(1:90)))|(1:86)(1:110)|87|88|(0)(0)))(1:174)|173|24|(0)|29|(0)|32|(1:34)|172|37|(0)(0)|(0)|(0)|161|78|79|80|81|82|83|(0)|(0)(0)|87|88|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x0245, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x0248, code lost:
    
        if (r1.retryOnExecuteIOException != false) goto L296;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x0255, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x0256, code lost:
    
        r7.log(java.util.logging.Level.WARNING, "exception thrown while executing request", (java.lang.Throwable) r0);
        r3 = null;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:110:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0260 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:171:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x02df A[LOOP:0: B:8:0x0021->B:90:0x02df, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x02be A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v16, types: [com.google.api.client.util.LoggingStreamingContent] */
    /* JADX WARN: Type inference failed for: r4v15, types: [com.google.api.client.http.HttpEncodingStreamingContent, com.google.api.client.util.StreamingContent] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.google.api.client.http.HttpResponse execute() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 738
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.http.HttpRequest.execute():com.google.api.client.http.HttpResponse");
    }

    /* renamed from: com.google.api.client.http.HttpRequest$1 */
    class AnonymousClass1 implements Callable<HttpResponse> {
        AnonymousClass1() {
        }

        @Override // java.util.concurrent.Callable
        public HttpResponse call() throws Exception {
            return HttpRequest.this.execute();
        }
    }

    public Future<HttpResponse> executeAsync(Executor executor) {
        FutureTask futureTask = new FutureTask(new Callable<HttpResponse>() { // from class: com.google.api.client.http.HttpRequest.1
            AnonymousClass1() {
            }

            @Override // java.util.concurrent.Callable
            public HttpResponse call() throws Exception {
                return HttpRequest.this.execute();
            }
        });
        executor.execute(futureTask);
        return futureTask;
    }

    public Future<HttpResponse> executeAsync() {
        return executeAsync(Executors.newSingleThreadExecutor());
    }

    public boolean handleRedirect(int i, HttpHeaders httpHeaders) {
        String location = httpHeaders.getLocation();
        if (!getFollowRedirects() || !HttpStatusCodes.isRedirect(i) || location == null) {
            return false;
        }
        setUrl(new GenericUrl(this.url.toURL(location)));
        if (i == 303) {
            setRequestMethod("GET");
            setContent(null);
        }
        this.headers.setAuthorization((String) null);
        this.headers.setIfMatch(null);
        this.headers.setIfNoneMatch(null);
        this.headers.setIfModifiedSince(null);
        this.headers.setIfUnmodifiedSince(null);
        this.headers.setIfRange(null);
        return true;
    }

    public Sleeper getSleeper() {
        return this.sleeper;
    }

    public HttpRequest setSleeper(Sleeper sleeper) {
        this.sleeper = (Sleeper) Preconditions.checkNotNull(sleeper);
        return this;
    }
}
