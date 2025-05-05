package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.network.models.UploadResponse;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class Document implements Serializable, DocumentInfo {

    @SerializedName("type")
    public String doc_mime;

    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    public String doc_name;

    @SerializedName(ImagesContract.URL)
    public String doc_url;

    @SerializedName("id")
    public int id;

    @SerializedName("size")
    public long size;

    public Document(UploadResponse result) {
        this.doc_url = result.getUrl();
        this.doc_mime = result.getExtension();
        this.doc_name = result.getName();
        this.size = result.getSize();
        this.id = result.getId();
    }

    public static Document create(String json) {
        return (Document) new Gson().fromJson(json, Document.class);
    }

    public boolean isPicture() {
        return Utils.contentTypeIsPicture(this.doc_mime);
    }

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
        return this.doc_name;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.DocumentInfo
    public String getType() {
        return this.doc_mime;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.DocumentInfo
    public String getUrl() {
        return this.doc_url;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.DocumentInfo
    public String getExtension() {
        return this.doc_mime;
    }
}
