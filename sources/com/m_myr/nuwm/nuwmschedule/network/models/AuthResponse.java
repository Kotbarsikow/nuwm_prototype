package com.m_myr.nuwm.nuwmschedule.network.models;

import com.m_myr.nuwm.nuwmschedule.utils.Utils;

/* loaded from: classes2.dex */
public class AuthResponse {
    public Long expired;
    public String token;

    @Deprecated
    public static AuthResponse createForGuest() {
        AuthResponse authResponse = new AuthResponse();
        authResponse.token = "guest_token";
        authResponse.expired = Long.MAX_VALUE;
        return authResponse;
    }

    public String getToken() {
        return this.token;
    }

    public Long getExpired() {
        return this.expired;
    }

    public String toString() {
        return "{\"token\":\"" + this.token + "\"}";
    }

    public boolean isValid() {
        return !Utils.StringUtils.isBlank(this.token) && this.expired.longValue() >= System.currentTimeMillis();
    }
}
