package com.m_myr.nuwm.nuwmschedule.network.api;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: APIRepositoryCallableKtx.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J+\u0010\u001d\u001a\u00020\u000e2!\u0010\b\u001a\u001d\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tH\u0004J+\u0010\u001e\u001a\u00020\u000e2!\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u000e0\tH\u0004J\u0006\u0010 \u001a\u00020\u000eJ/\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002!\u0010\"\u001a\u001d\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tJ\u001a\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0014J/\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002!\u0010%\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u000e0\tR7\u0010\b\u001a\u001f\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\tX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0014X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R7\u0010\u0019\u001a\u001f\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u000e\u0018\u00010\tX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0010\"\u0004\b\u001c\u0010\u0012¨\u0006&"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/network/api/APIRepositoryCallableKtx;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/m_myr/nuwm/nuwmschedule/network/api/APIRepositoryCallable;", "apiRepository", "Lcom/m_myr/nuwm/nuwmschedule/network/api/APIRepository;", "requestBuilder", "Lcom/m_myr/nuwm/nuwmschedule/network/api/ApiRequestBuilder;", "(Lcom/m_myr/nuwm/nuwmschedule/network/api/APIRepository;Lcom/m_myr/nuwm/nuwmschedule/network/api/ApiRequestBuilder;)V", NotificationCompat.CATEGORY_ERROR, "Lkotlin/Function1;", "Lcom/m_myr/nuwm/nuwmschedule/network/ErrorResponse;", "Lkotlin/ParameterName;", AppMeasurementSdk.ConditionalUserProperty.NAME, "response", "", "getErr", "()Lkotlin/jvm/functions/Function1;", "setErr", "(Lkotlin/jvm/functions/Function1;)V", "pre", "Lkotlin/Function0;", "getPre", "()Lkotlin/jvm/functions/Function0;", "setPre", "(Lkotlin/jvm/functions/Function0;)V", "scs", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "getScs", "setScs", "addOnFailListener", "addOnSuccessListener", FirebaseAnalytics.Param.SUCCESS, "async", "onFailCallback", "e", "onPreExecute", "onSuccessCallback", "s", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class APIRepositoryCallableKtx<T> extends APIRepositoryCallable<T> {
    private Function1<? super ErrorResponse, Unit> err;
    private Function0<Unit> pre;
    private Function1<? super T, Unit> scs;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public APIRepositoryCallableKtx(APIRepository apiRepository, ApiRequestBuilder<T> requestBuilder) {
        super(apiRepository, requestBuilder);
        Intrinsics.checkNotNullParameter(apiRepository, "apiRepository");
        Intrinsics.checkNotNullParameter(requestBuilder, "requestBuilder");
        this.pre = new Function0<Unit>() { // from class: com.m_myr.nuwm.nuwmschedule.network.api.APIRepositoryCallableKtx$pre$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        };
        this.scs = new Function1<T, Unit>() { // from class: com.m_myr.nuwm.nuwmschedule.network.api.APIRepositoryCallableKtx$scs$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(T t) {
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke2((APIRepositoryCallableKtx$scs$1<T>) obj);
                return Unit.INSTANCE;
            }
        };
        this.err = new Function1<ErrorResponse, Unit>() { // from class: com.m_myr.nuwm.nuwmschedule.network.api.APIRepositoryCallableKtx$err$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ErrorResponse it) {
                Intrinsics.checkNotNullParameter(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ErrorResponse errorResponse) {
                invoke2(errorResponse);
                return Unit.INSTANCE;
            }
        };
    }

    protected final Function0<Unit> getPre() {
        return this.pre;
    }

    protected final void setPre(Function0<Unit> function0) {
        this.pre = function0;
    }

    protected final Function1<T, Unit> getScs() {
        return this.scs;
    }

    protected final void setScs(Function1<? super T, Unit> function1) {
        this.scs = function1;
    }

    protected final Function1<ErrorResponse, Unit> getErr() {
        return this.err;
    }

    protected final void setErr(Function1<? super ErrorResponse, Unit> function1) {
        this.err = function1;
    }

    protected final void addOnSuccessListener(Function1<? super T, Unit> success) {
        Intrinsics.checkNotNullParameter(success, "success");
        this.scs = success;
    }

    protected final void addOnFailListener(Function1<? super ErrorResponse, Unit> err) {
        Intrinsics.checkNotNullParameter(err, "err");
        this.err = err;
    }

    public final APIRepositoryCallableKtx<T> onPreExecute(Function0<Unit> e) {
        Intrinsics.checkNotNullParameter(e, "e");
        this.pre = e;
        return this;
    }

    public final APIRepositoryCallableKtx<T> onSuccessCallback(Function1<? super T, Unit> s) {
        Intrinsics.checkNotNullParameter(s, "s");
        addOnSuccessListener(s);
        return this;
    }

    public final APIRepositoryCallableKtx<T> onFailCallback(Function1<? super ErrorResponse, Unit> e) {
        Intrinsics.checkNotNullParameter(e, "e");
        addOnFailListener(e);
        return this;
    }

    public final void async() {
        Function0<Unit> function0 = this.pre;
        if (function0 != null) {
            function0.invoke();
        }
        super.async((APIObjectListener) new APIObjectListener<T>(this) { // from class: com.m_myr.nuwm.nuwmschedule.network.api.APIRepositoryCallableKtx$async$1
            final /* synthetic */ APIRepositoryCallableKtx<T> this$0;

            {
                this.this$0 = this;
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onError(ErrorResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                Function1<ErrorResponse, Unit> err = this.this$0.getErr();
                if (err != null) {
                    err.invoke(response);
                }
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onSuccessData(T data) {
                Intrinsics.checkNotNullParameter(data, "data");
                Function1<T, Unit> scs = this.this$0.getScs();
                if (scs != null) {
                    scs.invoke(data);
                }
            }
        });
    }
}
