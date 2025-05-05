package com.google.api.client.googleapis.json;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Preconditions;
import java.io.IOException;

/* loaded from: classes2.dex */
public class GoogleJsonResponseException extends HttpResponseException {
    private static final long serialVersionUID = 409811126989994864L;
    private final transient GoogleJsonError details;

    public GoogleJsonResponseException(HttpResponseException.Builder builder, GoogleJsonError googleJsonError) {
        super(builder);
        this.details = googleJsonError;
    }

    public final GoogleJsonError getDetails() {
        return this.details;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:10|11|12|13|(1:15)|(2:17|(11:19|21|22|23|24|(1:26)(5:(1:36)|28|(1:30)|31|32)|27|28|(0)|31|32))|59|24|(0)(0)|27|28|(0)|31|32) */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x006f, code lost:
    
        r5 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0070, code lost:
    
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00ad, code lost:
    
        r5.printStackTrace();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006b A[Catch: IOException -> 0x006f, TRY_ENTER, TryCatch #5 {IOException -> 0x006f, blocks: (B:26:0x006b, B:36:0x0073), top: B:24:0x0069 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00a2 A[Catch: IOException -> 0x008d, TryCatch #6 {IOException -> 0x008d, blocks: (B:54:0x009e, B:55:0x00a5, B:56:0x00a2, B:46:0x0089, B:49:0x0093), top: B:8:0x002b }] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.google.api.client.googleapis.json.GoogleJsonError] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.api.client.googleapis.json.GoogleJsonResponseException from(com.google.api.client.json.JsonFactory r5, com.google.api.client.http.HttpResponse r6) {
        /*
            Method dump skipped, instructions count: 210
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.googleapis.json.GoogleJsonResponseException.from(com.google.api.client.json.JsonFactory, com.google.api.client.http.HttpResponse):com.google.api.client.googleapis.json.GoogleJsonResponseException");
    }

    public static HttpResponse execute(JsonFactory jsonFactory, HttpRequest httpRequest) throws GoogleJsonResponseException, IOException {
        Preconditions.checkNotNull(jsonFactory);
        boolean throwExceptionOnExecuteError = httpRequest.getThrowExceptionOnExecuteError();
        if (throwExceptionOnExecuteError) {
            httpRequest.setThrowExceptionOnExecuteError(false);
        }
        HttpResponse execute = httpRequest.execute();
        httpRequest.setThrowExceptionOnExecuteError(throwExceptionOnExecuteError);
        if (!throwExceptionOnExecuteError || execute.isSuccessStatusCode()) {
            return execute;
        }
        throw from(jsonFactory, execute);
    }
}
