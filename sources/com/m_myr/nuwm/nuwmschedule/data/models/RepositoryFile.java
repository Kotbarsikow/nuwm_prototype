package com.m_myr.nuwm.nuwmschedule.data.models;

/* loaded from: classes2.dex */
public class RepositoryFile {
    private String filename;
    private long filesize;
    private String format;
    private String language;
    private String mime_type;
    private String preview;
    private String uri;

    public String getPreview() {
        return this.preview;
    }

    public String getFilename() {
        return this.filename;
    }

    public String getMimeType() {
        return this.mime_type;
    }

    public String getFormat() {
        return this.format;
    }

    public String getLanguage() {
        return this.language;
    }

    public long getFilesize() {
        return this.filesize;
    }

    public String getUri() {
        return this.uri;
    }
}
