package com.google.android.gms.fido.fido2.api.common;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;

/* compiled from: com.google.android.gms:play-services-fido@@19.0.0 */
/* loaded from: classes.dex */
public abstract class RequestOptions extends AbstractSafeParcelable {
    public abstract AuthenticationExtensions getAuthenticationExtensions();

    public abstract byte[] getChallenge();

    public abstract Integer getRequestId();

    public abstract Double getTimeoutSeconds();

    public abstract TokenBinding getTokenBinding();

    public byte[] serializeToBytes() {
        return SafeParcelableSerializer.serializeToBytes(this);
    }
}
