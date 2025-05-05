package com.m_myr.nuwm.nuwmschedule.data.repositories;

import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.m_myr.nuwm.nuwmschedule.network.APIRequestResponse;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener;
import com.m_myr.nuwm.nuwmschedule.network.api.ApiRequestBuilder;
import com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback;
import okhttp3.HttpUrl;

/* loaded from: classes2.dex */
public class FastRepository<T> implements LifecycleObserver, Runnable {
    private final ApiRequestBuilder<T> builder;
    private APIObjectListener<T> weakReference;

    /* synthetic */ FastRepository(ApiRequestBuilder apiRequestBuilder, LifecycleOwner lifecycleOwner, AnonymousClass1 anonymousClass1) {
        this(apiRequestBuilder, lifecycleOwner);
    }

    private FastRepository(ApiRequestBuilder<T> builder, LifecycleOwner lifecycleOwner) {
        this.builder = builder;
        if (lifecycleOwner != null) {
            lifecycleOwner.getLifecycle().addObserver(this);
        }
    }

    public static <T> FastRepository<T> call(ApiRequestBuilder<T> builder) {
        return new FastRepository<>(builder, null);
    }

    public static FastRepositoryOwner from(LifecycleOwner owner) {
        return new FastRepositoryOwner(owner);
    }

    public Thread into(APIObjectListener<T> apiObjectListener) {
        this.weakReference = apiObjectListener;
        apiObjectListener.setType(this.builder.getType(), this.builder.getFieldName());
        return new Thread(this);
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository$1 */
    class AnonymousClass1 extends APIObjectListener<T> {
        final /* synthetic */ RequestObjectCallback val$requestObjectCallback;

        AnonymousClass1(final RequestObjectCallback val$requestObjectCallback) {
            val$requestObjectCallback = val$requestObjectCallback;
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
        public void onError(ErrorResponse response) {
            val$requestObjectCallback.onError(response);
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
        public void onSuccessData(T data) {
            val$requestObjectCallback.onSuccessData(data);
        }
    }

    public Thread into(RequestObjectCallback<T> requestObjectCallback) {
        return into((APIObjectListener) new APIObjectListener<T>() { // from class: com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository.1
            final /* synthetic */ RequestObjectCallback val$requestObjectCallback;

            AnonymousClass1(RequestObjectCallback requestObjectCallback2) {
                val$requestObjectCallback = requestObjectCallback2;
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onError(ErrorResponse response) {
                val$requestObjectCallback.onError(response);
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onSuccessData(T data) {
                val$requestObjectCallback.onSuccessData(data);
            }
        });
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ProcessVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Method arg registers not loaded: com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository$$ExternalSyntheticLambda0.<init>(com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository, okhttp3.HttpUrl, com.m_myr.nuwm.nuwmschedule.network.APIRequestResponse):void, class status: GENERATED_AND_UNLOADED
        	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:290)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.isArgUnused(ProcessVariables.java:146)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.lambda$isVarUnused$0(ProcessVariables.java:131)
        	at jadx.core.utils.ListUtils.allMatch(ListUtils.java:193)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.isVarUnused(ProcessVariables.java:131)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.processBlock(ProcessVariables.java:82)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:64)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.removeUnusedResults(ProcessVariables.java:73)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.visit(ProcessVariables.java:48)
        */
    @Override // java.lang.Runnable
    public void run() {
        /*
            r4 = this;
            com.m_myr.nuwm.nuwmschedule.network.api.ApiRequestBuilder<T> r0 = r4.builder     // Catch: java.lang.Exception -> L49
            okhttp3.HttpUrl r0 = r0.build()     // Catch: java.lang.Exception -> L49
            com.google.gson.JsonParser r1 = new com.google.gson.JsonParser     // Catch: java.lang.Exception -> L49
            r1.<init>()     // Catch: java.lang.Exception -> L49
            com.m_myr.nuwm.nuwmschedule.network.api.ApiRequestBuilder<T> r2 = r4.builder     // Catch: java.lang.Exception -> L49
            boolean r2 = r2.isPost()     // Catch: java.lang.Exception -> L49
            if (r2 == 0) goto L24
            com.m_myr.nuwm.nuwmschedule.network.api.ApiRequestBuilder<T> r2 = r4.builder     // Catch: java.lang.Exception -> L49
            okhttp3.HttpUrl r2 = r2.buildForm()     // Catch: java.lang.Exception -> L49
            com.m_myr.nuwm.nuwmschedule.network.api.ApiRequestBuilder<T> r3 = r4.builder     // Catch: java.lang.Exception -> L49
            int r3 = r3.getTimeout()     // Catch: java.lang.Exception -> L49
            java.lang.String r2 = com.m_myr.nuwm.nuwmschedule.network.NetworkClient.post(r0, r2, r3)     // Catch: java.lang.Exception -> L49
            goto L2e
        L24:
            com.m_myr.nuwm.nuwmschedule.network.api.ApiRequestBuilder<T> r2 = r4.builder     // Catch: java.lang.Exception -> L49
            int r2 = r2.getTimeout()     // Catch: java.lang.Exception -> L49
            java.lang.String r2 = com.m_myr.nuwm.nuwmschedule.network.NetworkClient.get(r0, r2)     // Catch: java.lang.Exception -> L49
        L2e:
            com.google.gson.JsonElement r1 = r1.parse(r2)     // Catch: java.lang.Exception -> L49
            com.m_myr.nuwm.nuwmschedule.network.APIRequestResponse r2 = new com.m_myr.nuwm.nuwmschedule.network.APIRequestResponse     // Catch: java.lang.Exception -> L49
            r2.<init>(r1)     // Catch: java.lang.Exception -> L49
            android.os.Handler r1 = new android.os.Handler     // Catch: java.lang.Exception -> L49
            android.os.Looper r3 = android.os.Looper.getMainLooper()     // Catch: java.lang.Exception -> L49
            r1.<init>(r3)     // Catch: java.lang.Exception -> L49
            com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository$$ExternalSyntheticLambda0 r3 = new com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository$$ExternalSyntheticLambda0     // Catch: java.lang.Exception -> L49
            r3.<init>()     // Catch: java.lang.Exception -> L49
            r1.post(r3)     // Catch: java.lang.Exception -> L49
            goto L60
        L49:
            r0 = move-exception
            com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener<T> r1 = r4.weakReference
            if (r1 != 0) goto L4f
            return
        L4f:
            android.os.Handler r1 = new android.os.Handler
            android.os.Looper r2 = android.os.Looper.getMainLooper()
            r1.<init>(r2)
            com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository$$ExternalSyntheticLambda1 r2 = new com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository$$ExternalSyntheticLambda1
            r2.<init>()
            r1.post(r2)
        L60:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository.run():void");
    }

    /* renamed from: lambda$run$0$com-m_myr-nuwm-nuwmschedule-data-repositories-FastRepository */
    /* synthetic */ void m149x1d1c5a80(HttpUrl httpUrl, APIRequestResponse aPIRequestResponse) {
        if (this.weakReference == null) {
            Log.d("FastRepository", "Reference lost. Skip result " + httpUrl.encodedPath());
        } else if (aPIRequestResponse.isSuccessful()) {
            this.weakReference.onSuccess(aPIRequestResponse);
        } else {
            this.weakReference.onError(aPIRequestResponse.getError());
        }
    }

    /* renamed from: lambda$run$1$com-m_myr-nuwm-nuwmschedule-data-repositories-FastRepository */
    /* synthetic */ void m150xb15aca1f(Exception exc) {
        this.weakReference.onError(exc);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onDestroy() {
        this.weakReference = null;
    }

    public Thread detach() {
        return new Thread(this);
    }

    public static class FastRepositoryOwner {
        private LifecycleOwner owner;

        /* synthetic */ FastRepositoryOwner(LifecycleOwner lifecycleOwner, AnonymousClass1 anonymousClass1) {
            this(lifecycleOwner);
        }

        private FastRepositoryOwner(LifecycleOwner owner) {
            this.owner = owner;
        }

        public <T> FastRepository<T> call(ApiRequestBuilder<T> builder) {
            return new FastRepository<>(builder, this.owner);
        }
    }
}
