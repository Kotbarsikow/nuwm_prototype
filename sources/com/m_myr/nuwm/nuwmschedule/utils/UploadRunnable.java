package com.m_myr.nuwm.nuwmschedule.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.JsonParser;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.network.APIMethod;
import com.m_myr.nuwm.nuwmschedule.network.APIRequestResponse;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback;
import com.m_myr.nuwm.nuwmschedule.network.models.UploadResponse;
import com.m_myr.nuwm.nuwmschedule.utils.RequestBodyUtil;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* loaded from: classes2.dex */
public class UploadRunnable extends Handler implements Runnable, RequestBodyUtil.TransferListener {
    private MultipartBody.Builder builder;
    long fileSize;
    private final ProgressListener listener;
    RequestBody requestBody;
    private String serverPatch;
    private int serverPatchFuture;

    public interface ProgressListener {
        void failUpload(String error);

        void finishUpload(UploadResponse response);

        void progressUpload(long num, float p);
    }

    public static String getFileName(Context context, Uri uri) {
        String str = null;
        if (uri.getScheme().equals(FirebaseAnalytics.Param.CONTENT)) {
            Cursor query = context.getContentResolver().query(uri, null, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        str = query.getString(query.getColumnIndex("_display_name"));
                    }
                } finally {
                    query.close();
                }
            }
        }
        if (str != null) {
            return str;
        }
        String path = uri.getPath();
        int lastIndexOf = path.lastIndexOf(47);
        return lastIndexOf != -1 ? path.substring(lastIndexOf + 1) : path;
    }

    public static String getMimeType(Context context, Uri uri) {
        if (uri.getScheme().equals(FirebaseAnalytics.Param.CONTENT)) {
            return MimeTypeMap.getSingleton().getExtensionFromMimeType(context.getContentResolver().getType(uri));
        }
        return MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(new File(uri.getPath())).toString());
    }

    public void addForm(String s, String data) {
        this.builder.addFormDataPart(s, data);
    }

    public UploadRunnable(Context context, Uri uri, ProgressListener listener, String serverPatch) {
        super(Looper.getMainLooper());
        this.serverPatchFuture = 0;
        this.listener = listener;
        this.serverPatch = serverPatch;
        Log.e("UploadRunnable", uri.toString());
        try {
            RequestBody create = RequestBodyUtil.create("file", App.getInstance().getContentResolver().openInputStream(uri), this);
            this.fileSize = create.contentLength();
            String mimeType = getMimeType(context, uri);
            this.builder = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("file", uri.getLastPathSegment(), create).addFormDataPart("extension", mimeType).addFormDataPart("type", Utils.resolveContentType(mimeType)).addFormDataPart(AppMeasurementSdk.ConditionalUserProperty.NAME, getFileName(context, uri)).addFormDataPart("token", AppDataManager.getInstance().getToken());
        } catch (Exception e) {
            e.printStackTrace();
            listener.failUpload(e.getMessage());
        }
    }

    public UploadRunnable(Context context, Uri uri, ProgressListener listener, int serverPatchFuture) {
        super(Looper.getMainLooper());
        this.serverPatchFuture = serverPatchFuture;
        this.listener = listener;
        this.serverPatch = this.serverPatch;
        Log.e("UploadRunnable", uri.toString());
        try {
            RequestBody create = RequestBodyUtil.create("file", App.getInstance().getContentResolver().openInputStream(uri), this);
            this.fileSize = create.contentLength();
            String mimeType = getMimeType(context, uri);
            this.builder = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("file", uri.getLastPathSegment(), create).addFormDataPart("extension", mimeType).addFormDataPart("type", Utils.resolveContentType(mimeType)).addFormDataPart(AppMeasurementSdk.ConditionalUserProperty.NAME, getFileName(context, uri)).addFormDataPart("token", AppDataManager.getInstance().getToken());
        } catch (Exception e) {
            e.printStackTrace();
            listener.failUpload(e.getMessage());
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.serverPatchFuture == APIMethod.Patch.PATCH_HESK_FILE_UPLOAD) {
                FastRepository.call(APIMethods.getHeskImageUploadLink()).into(new RequestObjectCallback<String>() { // from class: com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable.1
                    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
                    public void onError(ErrorResponse response) {
                    }

                    AnonymousClass1() {
                    }

                    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
                    public void onSuccessData(String data) {
                        UploadRunnable.this.serverPatch = data;
                    }
                }).run();
            }
            this.requestBody = this.builder.build();
            Response execute = new OkHttpClient.Builder().connectTimeout(10L, TimeUnit.SECONDS).writeTimeout(40L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).build().newCall(new Request.Builder().url(this.serverPatch).header("token", AppDataManager.getInstance().getToken()).header("Accept", "application/json").post(this.requestBody).build()).execute();
            String string = execute.body().string();
            Log.e("Fedwsfwef", "" + string);
            if (execute.isSuccessful()) {
                post(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable.2
                    final /* synthetic */ String val$res;

                    AnonymousClass2(String string2) {
                        val$res = string2;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        APIRequestResponse aPIRequestResponse = new APIRequestResponse(new JsonParser().parse(val$res));
                        if (aPIRequestResponse.isSuccessful()) {
                            UploadRunnable.this.listener.finishUpload((UploadResponse) Utils.getGson().fromJson(aPIRequestResponse.getResponse(), UploadResponse.class));
                        } else {
                            UploadRunnable.this.listener.failUpload(aPIRequestResponse.getError().getMessage());
                        }
                    }
                });
            } else {
                post(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable.3
                    final /* synthetic */ String val$error;

                    AnonymousClass3(final String val$error) {
                        val$error = val$error;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        UploadRunnable.this.listener.failUpload(val$error);
                    }
                });
            }
        } catch (IOException e) {
            post(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable.4
                final /* synthetic */ IOException val$e;

                AnonymousClass4(IOException e2) {
                    val$e = e2;
                }

                @Override // java.lang.Runnable
                public void run() {
                    UploadRunnable.this.listener.failUpload(val$e.getMessage());
                }
            });
            e2.printStackTrace();
        }
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable$1 */
    class AnonymousClass1 implements RequestObjectCallback<String> {
        @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
        public void onError(ErrorResponse response) {
        }

        AnonymousClass1() {
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
        public void onSuccessData(String data) {
            UploadRunnable.this.serverPatch = data;
        }
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable$2 */
    class AnonymousClass2 implements Runnable {
        final /* synthetic */ String val$res;

        AnonymousClass2(String string2) {
            val$res = string2;
        }

        @Override // java.lang.Runnable
        public void run() {
            APIRequestResponse aPIRequestResponse = new APIRequestResponse(new JsonParser().parse(val$res));
            if (aPIRequestResponse.isSuccessful()) {
                UploadRunnable.this.listener.finishUpload((UploadResponse) Utils.getGson().fromJson(aPIRequestResponse.getResponse(), UploadResponse.class));
            } else {
                UploadRunnable.this.listener.failUpload(aPIRequestResponse.getError().getMessage());
            }
        }
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable$3 */
    class AnonymousClass3 implements Runnable {
        final /* synthetic */ String val$error;

        AnonymousClass3(final String val$error) {
            val$error = val$error;
        }

        @Override // java.lang.Runnable
        public void run() {
            UploadRunnable.this.listener.failUpload(val$error);
        }
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable$4 */
    class AnonymousClass4 implements Runnable {
        final /* synthetic */ IOException val$e;

        AnonymousClass4(IOException e2) {
            val$e = e2;
        }

        @Override // java.lang.Runnable
        public void run() {
            UploadRunnable.this.listener.failUpload(val$e.getMessage());
        }
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable$5 */
    class AnonymousClass5 implements Runnable {
        final /* synthetic */ long val$num;

        AnonymousClass5(final long val$num) {
            val$num = val$num;
        }

        @Override // java.lang.Runnable
        public void run() {
            ProgressListener progressListener = UploadRunnable.this.listener;
            long j = val$num;
            progressListener.progressUpload(j, (j / UploadRunnable.this.fileSize) * 100.0f);
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.utils.RequestBodyUtil.TransferListener
    public void transferred(long num) {
        post(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable.5
            final /* synthetic */ long val$num;

            AnonymousClass5(long num2) {
                val$num = num2;
            }

            @Override // java.lang.Runnable
            public void run() {
                ProgressListener progressListener = UploadRunnable.this.listener;
                long j = val$num;
                progressListener.progressUpload(j, (j / UploadRunnable.this.fileSize) * 100.0f);
            }
        });
    }
}
