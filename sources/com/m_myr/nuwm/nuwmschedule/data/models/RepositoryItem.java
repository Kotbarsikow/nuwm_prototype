package com.m_myr.nuwm.nuwmschedule.data.models;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.RepositoryDocInfoProvider;
import java.util.List;

/* loaded from: classes2.dex */
public class RepositoryItem implements RepositoryDocInfoProvider {

    @SerializedName("date_year")
    protected int date_year;

    @SerializedName("downloads")
    protected int downloads;

    @SerializedName("eprintid")
    protected int eprintid;

    @SerializedName("shufr")
    protected String shufr;

    @SerializedName("thumbnail")
    protected String thumbnail;

    @SerializedName("title")
    protected String title;

    @SerializedName("doc_type_raw")
    protected String typeDocument;

    @SerializedName("doc_type")
    protected String typeDocumentName;

    @SerializedName("vud")
    private List<String> vud;

    public int getDownloads() {
        return this.downloads;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.RepositoryDocInfoProvider
    public String getTitle() {
        return this.title;
    }

    public List<String> getVud() {
        return this.vud;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.RepositoryDocInfoProvider
    public int getDateYear() {
        return this.date_year;
    }

    public String getShufr() {
        return this.shufr;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.RepositoryDocInfoProvider
    public int getEprintid() {
        return this.eprintid;
    }

    public String getTypeDocument() {
        return this.typeDocument;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.RepositoryDocInfoProvider
    public String getDocumentTypeName() {
        return this.typeDocumentName;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.RepositoryDocInfoProvider
    public String getImageUrl() {
        return this.thumbnail;
    }

    public String getDescription() {
        return this.typeDocumentName + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.date_year;
    }
}
