package com.m_myr.nuwm.nuwmschedule.network.models;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public class AppVersions {

    @SerializedName("force")
    boolean force;

    @SerializedName("last_version")
    AppVersionInfo lastVersion;

    public static class AppVersionInfo {

        @SerializedName("force")
        boolean force;

        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
        String name;

        @SerializedName("v")
        int v;
    }

    public AppVersionInfo getLastVersion() {
        return this.lastVersion;
    }

    public boolean isForce() {
        return this.force;
    }
}
