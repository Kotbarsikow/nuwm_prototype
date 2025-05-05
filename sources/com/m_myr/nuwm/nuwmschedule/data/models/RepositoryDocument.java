package com.m_myr.nuwm.nuwmschedule.data.models;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes2.dex */
public class RepositoryDocument extends RepositoryItem {

    @SerializedName("abstract")
    private String _abstract;

    @SerializedName("bib")
    String bib;

    @SerializedName("cover")
    String cover;

    @SerializedName("creators")
    private List<Author> creators;

    @SerializedName("file")
    private RepositoryFile file;

    @SerializedName("fileinfo")
    private String fileinfo;

    @SerializedName("keywords")
    private String keywords;

    @SerializedName("pages_preview")
    private List<String> pagesPreview;

    @SerializedName("views")
    private String views;

    public String getCover() {
        return this.cover;
    }

    public List<String> getPagesPreview() {
        return this.pagesPreview;
    }

    public String getBib() {
        return this.bib;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.RepositoryItem, com.m_myr.nuwm.nuwmschedule.domain.interfaces.RepositoryDocInfoProvider
    public String getImageUrl() {
        return this.cover;
    }

    public static class Author {
        public String family;
        public String full_name;
        public String given;

        public String toString() {
            return this.full_name;
        }
    }

    public String getKeywords() {
        return this.keywords;
    }

    public List<Author> getCreators() {
        return this.creators;
    }

    public String getAbstract() {
        return this._abstract;
    }

    public RepositoryFile getFile() {
        return this.file;
    }

    public String getFileinfo() {
        return this.fileinfo;
    }

    public String getViews() {
        return this.views;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.RepositoryItem
    public String getDescription() {
        return this.typeDocumentName + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.date_year + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.bib;
    }
}
