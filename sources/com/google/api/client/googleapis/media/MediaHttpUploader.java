package com.google.api.client.googleapis.media;

import com.github.mikephil.charting.utils.Utils;
import com.google.api.client.googleapis.MethodOverride;
import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.EmptyContent;
import com.google.api.client.http.GZipEncoding;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.MultipartContent;
import com.google.api.client.util.ByteStreams;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sleeper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.mortbay.jetty.security.Constraint;
import org.mortbay.util.URIUtil;

/* loaded from: classes2.dex */
public final class MediaHttpUploader {
    public static final String CONTENT_LENGTH_HEADER = "X-Upload-Content-Length";
    public static final String CONTENT_TYPE_HEADER = "X-Upload-Content-Type";
    public static final int DEFAULT_CHUNK_SIZE = 10485760;
    private static final int KB = 1024;
    static final int MB = 1048576;
    public static final int MINIMUM_CHUNK_SIZE = 262144;
    private Byte cachedByte;
    private InputStream contentInputStream;
    private int currentChunkLength;
    private HttpRequest currentRequest;
    private byte[] currentRequestContentBuffer;
    private boolean directUploadEnabled;
    private boolean disableGZipContent;
    private boolean isMediaContentLengthCalculated;
    private final AbstractInputStreamContent mediaContent;
    private long mediaContentLength;
    private HttpContent metadata;
    private MediaHttpUploaderProgressListener progressListener;
    private final HttpRequestFactory requestFactory;
    private long totalBytesClientSent;
    private long totalBytesServerReceived;
    private final HttpTransport transport;
    private UploadState uploadState = UploadState.NOT_STARTED;
    private String initiationRequestMethod = "POST";
    private HttpHeaders initiationHeaders = new HttpHeaders();
    String mediaContentLengthStr = Constraint.ANY_ROLE;
    private int chunkSize = DEFAULT_CHUNK_SIZE;
    Sleeper sleeper = Sleeper.DEFAULT;

    public enum UploadState {
        NOT_STARTED,
        INITIATION_STARTED,
        INITIATION_COMPLETE,
        MEDIA_IN_PROGRESS,
        MEDIA_COMPLETE
    }

    public MediaHttpUploader(AbstractInputStreamContent abstractInputStreamContent, HttpTransport httpTransport, HttpRequestInitializer httpRequestInitializer) {
        this.mediaContent = (AbstractInputStreamContent) Preconditions.checkNotNull(abstractInputStreamContent);
        this.transport = (HttpTransport) Preconditions.checkNotNull(httpTransport);
        this.requestFactory = httpRequestInitializer == null ? httpTransport.createRequestFactory() : httpTransport.createRequestFactory(httpRequestInitializer);
    }

    public HttpResponse upload(GenericUrl genericUrl) throws IOException {
        Preconditions.checkArgument(this.uploadState == UploadState.NOT_STARTED);
        if (this.directUploadEnabled) {
            return directUpload(genericUrl);
        }
        return resumableUpload(genericUrl);
    }

    private HttpResponse directUpload(GenericUrl genericUrl) throws IOException {
        updateStateAndNotifyListener(UploadState.MEDIA_IN_PROGRESS);
        HttpContent httpContent = this.mediaContent;
        if (this.metadata != null) {
            httpContent = new MultipartContent().setContentParts(Arrays.asList(this.metadata, this.mediaContent));
            genericUrl.put("uploadType", "multipart");
        } else {
            genericUrl.put("uploadType", "media");
        }
        HttpRequest buildRequest = this.requestFactory.buildRequest(this.initiationRequestMethod, genericUrl, httpContent);
        buildRequest.getHeaders().putAll(this.initiationHeaders);
        HttpResponse executeCurrentRequest = executeCurrentRequest(buildRequest);
        try {
            if (isMediaLengthKnown()) {
                this.totalBytesServerReceived = getMediaContentLength();
            }
            updateStateAndNotifyListener(UploadState.MEDIA_COMPLETE);
            return executeCurrentRequest;
        } catch (Throwable th) {
            executeCurrentRequest.disconnect();
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x0064, code lost:
    
        r13.totalBytesServerReceived = getMediaContentLength();
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0070, code lost:
    
        if (r13.mediaContent.getCloseInputStream() == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0072, code lost:
    
        r13.contentInputStream.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0077, code lost:
    
        updateStateAndNotifyListener(com.google.api.client.googleapis.media.MediaHttpUploader.UploadState.MEDIA_COMPLETE);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x007c, code lost:
    
        return r14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.google.api.client.http.HttpResponse resumableUpload(com.google.api.client.http.GenericUrl r14) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 249
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.googleapis.media.MediaHttpUploader.resumableUpload(com.google.api.client.http.GenericUrl):com.google.api.client.http.HttpResponse");
    }

    private boolean isMediaLengthKnown() throws IOException {
        return getMediaContentLength() >= 0;
    }

    private long getMediaContentLength() throws IOException {
        if (!this.isMediaContentLengthCalculated) {
            this.mediaContentLength = this.mediaContent.getLength();
            this.isMediaContentLengthCalculated = true;
        }
        return this.mediaContentLength;
    }

    private HttpResponse executeUploadInitiation(GenericUrl genericUrl) throws IOException {
        updateStateAndNotifyListener(UploadState.INITIATION_STARTED);
        genericUrl.put("uploadType", "resumable");
        HttpContent httpContent = this.metadata;
        if (httpContent == null) {
            httpContent = new EmptyContent();
        }
        HttpRequest buildRequest = this.requestFactory.buildRequest(this.initiationRequestMethod, genericUrl, httpContent);
        this.initiationHeaders.set(CONTENT_TYPE_HEADER, (Object) this.mediaContent.getType());
        if (isMediaLengthKnown()) {
            this.initiationHeaders.set(CONTENT_LENGTH_HEADER, (Object) Long.valueOf(getMediaContentLength()));
        }
        buildRequest.getHeaders().putAll(this.initiationHeaders);
        HttpResponse executeCurrentRequest = executeCurrentRequest(buildRequest);
        try {
            updateStateAndNotifyListener(UploadState.INITIATION_COMPLETE);
            return executeCurrentRequest;
        } catch (Throwable th) {
            executeCurrentRequest.disconnect();
            throw th;
        }
    }

    private HttpResponse executeCurrentRequestWithoutGZip(HttpRequest httpRequest) throws IOException {
        new MethodOverride().intercept(httpRequest);
        httpRequest.setThrowExceptionOnExecuteError(false);
        return httpRequest.execute();
    }

    private HttpResponse executeCurrentRequest(HttpRequest httpRequest) throws IOException {
        if (!this.disableGZipContent && !(httpRequest.getContent() instanceof EmptyContent)) {
            httpRequest.setEncoding(new GZipEncoding());
        }
        return executeCurrentRequestWithoutGZip(httpRequest);
    }

    private void setContentAndHeadersOnCurrentRequest() throws IOException {
        int i;
        int i2;
        int i3;
        HttpContent byteArrayContent;
        if (isMediaLengthKnown()) {
            i = (int) Math.min(this.chunkSize, getMediaContentLength() - this.totalBytesServerReceived);
        } else {
            i = this.chunkSize;
        }
        if (isMediaLengthKnown()) {
            this.contentInputStream.mark(i);
            long j = i;
            byteArrayContent = new InputStreamContent(this.mediaContent.getType(), ByteStreams.limit(this.contentInputStream, j)).setRetrySupported(true).setLength(j).setCloseInputStream(false);
            this.mediaContentLengthStr = String.valueOf(getMediaContentLength());
        } else {
            byte[] bArr = this.currentRequestContentBuffer;
            if (bArr == null) {
                Byte b = this.cachedByte;
                i3 = b == null ? i + 1 : i;
                byte[] bArr2 = new byte[i + 1];
                this.currentRequestContentBuffer = bArr2;
                if (b != null) {
                    bArr2[0] = b.byteValue();
                }
                i2 = 0;
            } else {
                i2 = (int) (this.totalBytesClientSent - this.totalBytesServerReceived);
                System.arraycopy(bArr, this.currentChunkLength - i2, bArr, 0, i2);
                Byte b2 = this.cachedByte;
                if (b2 != null) {
                    this.currentRequestContentBuffer[i2] = b2.byteValue();
                }
                i3 = i - i2;
            }
            int read = ByteStreams.read(this.contentInputStream, this.currentRequestContentBuffer, (i + 1) - i3, i3);
            if (read < i3) {
                int max = i2 + Math.max(0, read);
                if (this.cachedByte != null) {
                    max++;
                    this.cachedByte = null;
                }
                if (this.mediaContentLengthStr.equals(Constraint.ANY_ROLE)) {
                    this.mediaContentLengthStr = String.valueOf(this.totalBytesServerReceived + max);
                }
                i = max;
            } else {
                this.cachedByte = Byte.valueOf(this.currentRequestContentBuffer[i]);
            }
            byteArrayContent = new ByteArrayContent(this.mediaContent.getType(), this.currentRequestContentBuffer, 0, i);
            this.totalBytesClientSent = this.totalBytesServerReceived + i;
        }
        this.currentChunkLength = i;
        this.currentRequest.setContent(byteArrayContent);
        if (i == 0) {
            HttpHeaders headers = this.currentRequest.getHeaders();
            String valueOf = String.valueOf(this.mediaContentLengthStr);
            headers.setContentRange(valueOf.length() != 0 ? "bytes */".concat(valueOf) : new String("bytes */"));
            return;
        }
        HttpHeaders headers2 = this.currentRequest.getHeaders();
        long j2 = this.totalBytesServerReceived;
        long j3 = (i + j2) - 1;
        String valueOf2 = String.valueOf(String.valueOf(this.mediaContentLengthStr));
        StringBuilder sb = new StringBuilder(valueOf2.length() + 48);
        sb.append("bytes ");
        sb.append(j2);
        sb.append("-");
        sb.append(j3);
        sb.append(URIUtil.SLASH);
        sb.append(valueOf2);
        headers2.setContentRange(sb.toString());
    }

    void serverErrorCallback() throws IOException {
        Preconditions.checkNotNull(this.currentRequest, "The current request should not be null");
        this.currentRequest.setContent(new EmptyContent());
        HttpHeaders headers = this.currentRequest.getHeaders();
        String valueOf = String.valueOf(this.mediaContentLengthStr);
        headers.setContentRange(valueOf.length() != 0 ? "bytes */".concat(valueOf) : new String("bytes */"));
    }

    private long getNextByteIndex(String str) {
        if (str == null) {
            return 0L;
        }
        return Long.parseLong(str.substring(str.indexOf(45) + 1)) + 1;
    }

    public HttpContent getMetadata() {
        return this.metadata;
    }

    public MediaHttpUploader setMetadata(HttpContent httpContent) {
        this.metadata = httpContent;
        return this;
    }

    public HttpContent getMediaContent() {
        return this.mediaContent;
    }

    public HttpTransport getTransport() {
        return this.transport;
    }

    public MediaHttpUploader setDirectUploadEnabled(boolean z) {
        this.directUploadEnabled = z;
        return this;
    }

    public boolean isDirectUploadEnabled() {
        return this.directUploadEnabled;
    }

    public MediaHttpUploader setProgressListener(MediaHttpUploaderProgressListener mediaHttpUploaderProgressListener) {
        this.progressListener = mediaHttpUploaderProgressListener;
        return this;
    }

    public MediaHttpUploaderProgressListener getProgressListener() {
        return this.progressListener;
    }

    public MediaHttpUploader setChunkSize(int i) {
        Preconditions.checkArgument(i > 0 && i % 262144 == 0, "chunkSize must be a positive multiple of 262144.");
        this.chunkSize = i;
        return this;
    }

    public int getChunkSize() {
        return this.chunkSize;
    }

    public boolean getDisableGZipContent() {
        return this.disableGZipContent;
    }

    public MediaHttpUploader setDisableGZipContent(boolean z) {
        this.disableGZipContent = z;
        return this;
    }

    public Sleeper getSleeper() {
        return this.sleeper;
    }

    public MediaHttpUploader setSleeper(Sleeper sleeper) {
        this.sleeper = sleeper;
        return this;
    }

    public String getInitiationRequestMethod() {
        return this.initiationRequestMethod;
    }

    public MediaHttpUploader setInitiationRequestMethod(String str) {
        Preconditions.checkArgument(str.equals("POST") || str.equals("PUT") || str.equals(HttpMethods.PATCH));
        this.initiationRequestMethod = str;
        return this;
    }

    public MediaHttpUploader setInitiationHeaders(HttpHeaders httpHeaders) {
        this.initiationHeaders = httpHeaders;
        return this;
    }

    public HttpHeaders getInitiationHeaders() {
        return this.initiationHeaders;
    }

    public long getNumBytesUploaded() {
        return this.totalBytesServerReceived;
    }

    private void updateStateAndNotifyListener(UploadState uploadState) throws IOException {
        this.uploadState = uploadState;
        MediaHttpUploaderProgressListener mediaHttpUploaderProgressListener = this.progressListener;
        if (mediaHttpUploaderProgressListener != null) {
            mediaHttpUploaderProgressListener.progressChanged(this);
        }
    }

    public UploadState getUploadState() {
        return this.uploadState;
    }

    public double getProgress() throws IOException {
        Preconditions.checkArgument(isMediaLengthKnown(), "Cannot call getProgress() if the specified AbstractInputStreamContent has no content length. Use  getNumBytesUploaded() to denote progress instead.");
        return getMediaContentLength() == 0 ? Utils.DOUBLE_EPSILON : this.totalBytesServerReceived / getMediaContentLength();
    }
}
