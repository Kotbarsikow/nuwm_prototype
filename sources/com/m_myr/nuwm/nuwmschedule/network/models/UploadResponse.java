package com.m_myr.nuwm.nuwmschedule.network.models;

import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.data.models.DocumentInfo;

/* loaded from: classes2.dex */
public class UploadResponse implements DocumentInfo {

    @SerializedName("extension")
    String extension;

    @SerializedName("id")
    int id;

    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    String name;

    @SerializedName("size")
    public long size;

    @SerializedName("type")
    String type;

    @SerializedName(ImagesContract.URL)
    String url;

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.DocumentInfo
    public int getId() {
        return this.id;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.DocumentInfo
    public long getSize() {
        return this.size;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.DocumentInfo
    public String getName() {
        return this.name;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.DocumentInfo
    public String getType() {
        return this.type;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.DocumentInfo
    public String getUrl() {
        return this.url;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.DocumentInfo
    public String getExtension() {
        return this.extension;
    }
}
