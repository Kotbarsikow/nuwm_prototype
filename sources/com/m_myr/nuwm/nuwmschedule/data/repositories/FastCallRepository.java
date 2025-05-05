package com.m_myr.nuwm.nuwmschedule.data.repositories;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.m_myr.nuwm.nuwmschedule.network.API5RequestBuilder;
import com.m_myr.nuwm.nuwmschedule.network.APIObjectCallback;
import com.m_myr.nuwm.nuwmschedule.network.APIRequestResponse;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.NetworkClient;
import com.m_myr.nuwm.nuwmschedule.network.models.EmptyResult;
import java.lang.ref.WeakReference;
import java.net.UnknownHostException;
import okhttp3.HttpUrl;

@Deprecated
/* loaded from: classes2.dex */
public class FastCallRepository<T> implements LifecycleObserver {
    private API5RequestBuilder requestBuilder;
    private Class<T> tClass;
    private WeakReference<? extends APIOldObjectListener<T>> weakReference;

    @Deprecated
    public FastCallRepository() {
    }

    public static FastCallRepository<EmptyResult> create() {
        return new FastCallRepository<>();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onDestroy() {
        WeakReference<? extends APIOldObjectListener<T>> weakReference = this.weakReference;
        if (weakReference == null) {
            return;
        }
        weakReference.clear();
    }

    @Deprecated
    private FastCallRepository(Class<T> tClass, LifecycleOwner lifecycleOwner) {
        this.tClass = tClass;
        if (lifecycleOwner != null) {
            lifecycleOwner.getLifecycle().addObserver(this);
        }
    }

    @Deprecated
    public static <T> FastCallRepository<T> create(Class<T> tClass) {
        return new FastCallRepository<>(tClass, null);
    }

    @Deprecated
    public static <T> FastCallRepository<T> create(LifecycleOwner owner, Class<T> tClass) {
        return new FastCallRepository<>(tClass, owner);
    }

    @Deprecated
    public FastCallRepository<T> call(API5RequestBuilder requestBuilder) {
        this.requestBuilder = requestBuilder;
        return this;
    }

    @Deprecated
    public void startInto(APIOldObjectListener<T> apiObjectOkListener) {
        into(apiObjectOkListener).start();
    }

    @Deprecated
    public void startInto(APIObjectCallback.ObjectCallback<T> objectCallback, String field) {
        into(new APIObjectCallback(objectCallback, field)).start();
    }

    @Deprecated
    public Thread into(APIObjectCallback.ObjectCallback<T> objectCallback) {
        return into(new APIObjectCallback(objectCallback));
    }

    @Deprecated
    public Thread into(APIObjectCallback.ObjectCallback<T> objectCallback, String field) {
        return into(new APIObjectCallback(objectCallback, field));
    }

    @Deprecated
    public Thread into(APIOldObjectListener<T> apiObjectOkListener) {
        this.weakReference = new WeakReference<>(apiObjectOkListener);
        apiObjectOkListener.setClass(this.tClass);
        return new Thread(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.data.repositories.FastCallRepository$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FastCallRepository.this.m148x2483f12f();
            }
        });
    }

    /* renamed from: lambda$into$0$com-m_myr-nuwm-nuwmschedule-data-repositories-FastCallRepository, reason: not valid java name */
    /* synthetic */ void m148x2483f12f() {
        final APIOldObjectListener<T> aPIOldObjectListener;
        try {
            HttpUrl build = this.requestBuilder.build();
            Log.e("FastCallRepository", "launch " + build.toString());
            JsonElement parse = new JsonParser().parse(this.requestBuilder.isPost() ? NetworkClient.post(build, this.requestBuilder.buildForm(), 16000) : NetworkClient.get(build, 16000));
            Log.e("DDDD", new GsonBuilder().setPrettyPrinting().create().toJson(parse));
            final APIRequestResponse aPIRequestResponse = new APIRequestResponse(parse);
            WeakReference<? extends APIOldObjectListener<T>> weakReference = this.weakReference;
            if (weakReference == null || (aPIOldObjectListener = weakReference.get()) == null) {
                return;
            }
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.data.repositories.FastCallRepository.1
                @Override // java.lang.Runnable
                public void run() {
                    if (aPIRequestResponse.isSuccessful()) {
                        aPIOldObjectListener.onSuccess(aPIRequestResponse);
                    } else {
                        aPIOldObjectListener.onError(aPIRequestResponse.getError());
                    }
                }
            });
        } catch (Exception e) {
            if (this.weakReference == null) {
                return;
            }
            e.printStackTrace();
            final APIOldObjectListener<T> aPIOldObjectListener2 = this.weakReference.get();
            if (aPIOldObjectListener2 == null) {
                return;
            }
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.data.repositories.FastCallRepository.2
                @Override // java.lang.Runnable
                public void run() {
                    Exception exc = e;
                    if (exc instanceof UnknownHostException) {
                        aPIOldObjectListener2.onError(new ErrorResponse(-8, "Відсутня мережа"));
                    } else {
                        aPIOldObjectListener2.m146xbd98ac6e(exc);
                    }
                }
            });
        }
    }
}
