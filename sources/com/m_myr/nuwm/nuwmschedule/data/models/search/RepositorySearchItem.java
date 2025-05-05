package com.m_myr.nuwm.nuwmschedule.data.models.search;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.RepositoryDocInfoProvider;

/* loaded from: classes2.dex */
public class RepositorySearchItem extends BaseSearchResult implements RepositoryDocInfoProvider {
    public static final int REPOSITORY_SEARCH = 1;

    @SerializedName("bib")
    String bib;

    @SerializedName("date_year")
    int date_year;

    @SerializedName("doc_type_name")
    String docTypeName;

    @SerializedName("eprintid")
    int eprintid;

    @SerializedName("image")
    String imageUrl;

    @SerializedName("doc_type")
    String typeDocument;

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.RepositoryDocInfoProvider
    public int getDateYear() {
        return this.date_year;
    }

    public String getBib() {
        return this.bib;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.RepositoryDocInfoProvider
    public int getEprintid() {
        return this.eprintid;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.RepositoryDocInfoProvider
    public String getDocumentTypeName() {
        return this.typeDocument;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.RepositoryDocInfoProvider
    public String getImageUrl() {
        return this.imageUrl;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.search.BaseSearchResult
    public String getDescription() {
        return this.docTypeName + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.date_year + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.bib;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.RepositoryDocInfoProvider
    public String getTitle() {
        return getText();
    }

    public void generatePreviewImage() {
        if (this.imageUrl == null) {
            this.imageUrl = "https://ep3.nuwm.edu.ua/" + this.eprintid + "/3/preview.jpg";
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.search.BaseSearchResult
    public boolean equals(Object obj) {
        if (obj instanceof RepositoryDocInfoProvider) {
            return ((RepositoryDocInfoProvider) obj).getEprintid() == getEprintid();
        }
        return super.equals(obj);
    }
}
