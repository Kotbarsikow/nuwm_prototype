package com.google.api.client.googleapis.services;

import com.google.api.client.googleapis.MethodOverride;
import com.google.api.client.googleapis.batch.BatchCallback;
import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.EmptyContent;
import com.google.api.client.http.GZipEncoding;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpResponseInterceptor;
import com.google.api.client.http.UriTemplate;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public abstract class AbstractGoogleClientRequest<T> extends GenericData {
    public static final String USER_AGENT_SUFFIX = "Google-API-Java-Client";
    private final AbstractGoogleClient abstractGoogleClient;
    private boolean disableGZipContent;
    private MediaHttpDownloader downloader;
    private final HttpContent httpContent;
    private HttpHeaders lastResponseHeaders;
    private String lastStatusMessage;
    private final String requestMethod;
    private Class<T> responseClass;
    private MediaHttpUploader uploader;
    private final String uriTemplate;
    private HttpHeaders requestHeaders = new HttpHeaders();
    private int lastStatusCode = -1;

    protected AbstractGoogleClientRequest(AbstractGoogleClient abstractGoogleClient, String str, String str2, HttpContent httpContent, Class<T> cls) {
        this.responseClass = (Class) Preconditions.checkNotNull(cls);
        this.abstractGoogleClient = (AbstractGoogleClient) Preconditions.checkNotNull(abstractGoogleClient);
        this.requestMethod = (String) Preconditions.checkNotNull(str);
        this.uriTemplate = (String) Preconditions.checkNotNull(str2);
        this.httpContent = httpContent;
        String applicationName = abstractGoogleClient.getApplicationName();
        if (applicationName != null) {
            HttpHeaders httpHeaders = this.requestHeaders;
            String valueOf = String.valueOf(String.valueOf(applicationName));
            StringBuilder sb = new StringBuilder(valueOf.length() + 23);
            sb.append(valueOf);
            sb.append(" Google-API-Java-Client");
            httpHeaders.setUserAgent(sb.toString());
            return;
        }
        this.requestHeaders.setUserAgent(USER_AGENT_SUFFIX);
    }

    public final boolean getDisableGZipContent() {
        return this.disableGZipContent;
    }

    public AbstractGoogleClientRequest<T> setDisableGZipContent(boolean z) {
        this.disableGZipContent = z;
        return this;
    }

    public final String getRequestMethod() {
        return this.requestMethod;
    }

    public final String getUriTemplate() {
        return this.uriTemplate;
    }

    public final HttpContent getHttpContent() {
        return this.httpContent;
    }

    public AbstractGoogleClient getAbstractGoogleClient() {
        return this.abstractGoogleClient;
    }

    public final HttpHeaders getRequestHeaders() {
        return this.requestHeaders;
    }

    public AbstractGoogleClientRequest<T> setRequestHeaders(HttpHeaders httpHeaders) {
        this.requestHeaders = httpHeaders;
        return this;
    }

    public final HttpHeaders getLastResponseHeaders() {
        return this.lastResponseHeaders;
    }

    public final int getLastStatusCode() {
        return this.lastStatusCode;
    }

    public final String getLastStatusMessage() {
        return this.lastStatusMessage;
    }

    public final Class<T> getResponseClass() {
        return this.responseClass;
    }

    public final MediaHttpUploader getMediaHttpUploader() {
        return this.uploader;
    }

    protected final void initializeMediaUpload(AbstractInputStreamContent abstractInputStreamContent) {
        HttpRequestFactory requestFactory = this.abstractGoogleClient.getRequestFactory();
        MediaHttpUploader mediaHttpUploader = new MediaHttpUploader(abstractInputStreamContent, requestFactory.getTransport(), requestFactory.getInitializer());
        this.uploader = mediaHttpUploader;
        mediaHttpUploader.setInitiationRequestMethod(this.requestMethod);
        HttpContent httpContent = this.httpContent;
        if (httpContent != null) {
            this.uploader.setMetadata(httpContent);
        }
    }

    public final MediaHttpDownloader getMediaHttpDownloader() {
        return this.downloader;
    }

    protected final void initializeMediaDownload() {
        HttpRequestFactory requestFactory = this.abstractGoogleClient.getRequestFactory();
        this.downloader = new MediaHttpDownloader(requestFactory.getTransport(), requestFactory.getInitializer());
    }

    public GenericUrl buildHttpRequestUrl() {
        return new GenericUrl(UriTemplate.expand(this.abstractGoogleClient.getBaseUrl(), this.uriTemplate, this, true));
    }

    public HttpRequest buildHttpRequest() throws IOException {
        return buildHttpRequest(false);
    }

    protected HttpRequest buildHttpRequestUsingHead() throws IOException {
        return buildHttpRequest(true);
    }

    private HttpRequest buildHttpRequest(boolean z) throws IOException {
        boolean z2 = true;
        Preconditions.checkArgument(this.uploader == null);
        if (z && !this.requestMethod.equals("GET")) {
            z2 = false;
        }
        Preconditions.checkArgument(z2);
        HttpRequest buildRequest = getAbstractGoogleClient().getRequestFactory().buildRequest(z ? "HEAD" : this.requestMethod, buildHttpRequestUrl(), this.httpContent);
        new MethodOverride().intercept(buildRequest);
        buildRequest.setParser(getAbstractGoogleClient().getObjectParser());
        if (this.httpContent == null && (this.requestMethod.equals("POST") || this.requestMethod.equals("PUT") || this.requestMethod.equals(HttpMethods.PATCH))) {
            buildRequest.setContent(new EmptyContent());
        }
        buildRequest.getHeaders().putAll(this.requestHeaders);
        if (!this.disableGZipContent) {
            buildRequest.setEncoding(new GZipEncoding());
        }
        buildRequest.setResponseInterceptor(new HttpResponseInterceptor() { // from class: com.google.api.client.googleapis.services.AbstractGoogleClientRequest.1
            final /* synthetic */ HttpRequest val$httpRequest;
            final /* synthetic */ HttpResponseInterceptor val$responseInterceptor;

            AnonymousClass1(HttpResponseInterceptor httpResponseInterceptor, HttpRequest buildRequest2) {
                r2 = httpResponseInterceptor;
                r3 = buildRequest2;
            }

            @Override // com.google.api.client.http.HttpResponseInterceptor
            public void interceptResponse(HttpResponse httpResponse) throws IOException {
                HttpResponseInterceptor httpResponseInterceptor = r2;
                if (httpResponseInterceptor != null) {
                    httpResponseInterceptor.interceptResponse(httpResponse);
                }
                if (!httpResponse.isSuccessStatusCode() && r3.getThrowExceptionOnExecuteError()) {
                    throw AbstractGoogleClientRequest.this.newExceptionOnError(httpResponse);
                }
            }
        });
        return buildRequest2;
    }

    /* renamed from: com.google.api.client.googleapis.services.AbstractGoogleClientRequest$1 */
    class AnonymousClass1 implements HttpResponseInterceptor {
        final /* synthetic */ HttpRequest val$httpRequest;
        final /* synthetic */ HttpResponseInterceptor val$responseInterceptor;

        AnonymousClass1(HttpResponseInterceptor httpResponseInterceptor, HttpRequest buildRequest2) {
            r2 = httpResponseInterceptor;
            r3 = buildRequest2;
        }

        @Override // com.google.api.client.http.HttpResponseInterceptor
        public void interceptResponse(HttpResponse httpResponse) throws IOException {
            HttpResponseInterceptor httpResponseInterceptor = r2;
            if (httpResponseInterceptor != null) {
                httpResponseInterceptor.interceptResponse(httpResponse);
            }
            if (!httpResponse.isSuccessStatusCode() && r3.getThrowExceptionOnExecuteError()) {
                throw AbstractGoogleClientRequest.this.newExceptionOnError(httpResponse);
            }
        }
    }

    public HttpResponse executeUnparsed() throws IOException {
        return executeUnparsed(false);
    }

    protected HttpResponse executeMedia() throws IOException {
        set("alt", (Object) "media");
        return executeUnparsed();
    }

    protected HttpResponse executeUsingHead() throws IOException {
        Preconditions.checkArgument(this.uploader == null);
        HttpResponse executeUnparsed = executeUnparsed(true);
        executeUnparsed.ignore();
        return executeUnparsed;
    }

    private HttpResponse executeUnparsed(boolean z) throws IOException {
        HttpResponse upload;
        if (this.uploader == null) {
            upload = buildHttpRequest(z).execute();
        } else {
            GenericUrl buildHttpRequestUrl = buildHttpRequestUrl();
            boolean throwExceptionOnExecuteError = getAbstractGoogleClient().getRequestFactory().buildRequest(this.requestMethod, buildHttpRequestUrl, this.httpContent).getThrowExceptionOnExecuteError();
            upload = this.uploader.setInitiationHeaders(this.requestHeaders).setDisableGZipContent(this.disableGZipContent).upload(buildHttpRequestUrl);
            upload.getRequest().setParser(getAbstractGoogleClient().getObjectParser());
            if (throwExceptionOnExecuteError && !upload.isSuccessStatusCode()) {
                throw newExceptionOnError(upload);
            }
        }
        this.lastResponseHeaders = upload.getHeaders();
        this.lastStatusCode = upload.getStatusCode();
        this.lastStatusMessage = upload.getStatusMessage();
        return upload;
    }

    protected IOException newExceptionOnError(HttpResponse httpResponse) {
        return new HttpResponseException(httpResponse);
    }

    public T execute() throws IOException {
        return (T) executeUnparsed().parseAs((Class) this.responseClass);
    }

    public InputStream executeAsInputStream() throws IOException {
        return executeUnparsed().getContent();
    }

    protected InputStream executeMediaAsInputStream() throws IOException {
        return executeMedia().getContent();
    }

    public void executeAndDownloadTo(OutputStream outputStream) throws IOException {
        executeUnparsed().download(outputStream);
    }

    protected void executeMediaAndDownloadTo(OutputStream outputStream) throws IOException {
        MediaHttpDownloader mediaHttpDownloader = this.downloader;
        if (mediaHttpDownloader == null) {
            executeMedia().download(outputStream);
        } else {
            mediaHttpDownloader.download(buildHttpRequestUrl(), this.requestHeaders, outputStream);
        }
    }

    public final <E> void queue(BatchRequest batchRequest, Class<E> cls, BatchCallback<T, E> batchCallback) throws IOException {
        Preconditions.checkArgument(this.uploader == null, "Batching media requests is not supported");
        batchRequest.queue(buildHttpRequest(), getResponseClass(), cls, batchCallback);
    }

    @Override // com.google.api.client.util.GenericData
    public AbstractGoogleClientRequest<T> set(String str, Object obj) {
        return (AbstractGoogleClientRequest) super.set(str, obj);
    }

    protected final void checkRequiredParameter(Object obj, String str) {
        Preconditions.checkArgument(this.abstractGoogleClient.getSuppressRequiredParameterChecks() || obj != null, "Required parameter %s must be specified", str);
    }
}
