package com.google.android.gms.fido.fido2.api.common;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: com.google.android.gms:play-services-fido@@19.0.0 */
/* loaded from: classes.dex */
public abstract class AuthenticatorResponse extends AbstractSafeParcelable {
    public abstract byte[] getClientDataJSON();

    public abstract byte[] serializeToBytes();
}
