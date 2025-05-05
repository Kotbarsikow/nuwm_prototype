package com.google.api.client.googleapis.notifications;

import java.io.InputStream;

/* loaded from: classes2.dex */
public class UnparsedNotification extends AbstractNotification {
    private InputStream contentStream;
    private String contentType;

    public UnparsedNotification(long j, String str, String str2, String str3, String str4) {
        super(j, str, str2, str3, str4);
    }

    public final String getContentType() {
        return this.contentType;
    }

    public UnparsedNotification setContentType(String str) {
        this.contentType = str;
        return this;
    }

    public final InputStream getContentStream() {
        return this.contentStream;
    }

    public UnparsedNotification setContentStream(InputStream inputStream) {
        this.contentStream = inputStream;
        return this;
    }

    @Override // com.google.api.client.googleapis.notifications.AbstractNotification
    public UnparsedNotification setMessageNumber(long j) {
        return (UnparsedNotification) super.setMessageNumber(j);
    }

    @Override // com.google.api.client.googleapis.notifications.AbstractNotification
    public UnparsedNotification setResourceState(String str) {
        return (UnparsedNotification) super.setResourceState(str);
    }

    @Override // com.google.api.client.googleapis.notifications.AbstractNotification
    public UnparsedNotification setResourceId(String str) {
        return (UnparsedNotification) super.setResourceId(str);
    }

    @Override // com.google.api.client.googleapis.notifications.AbstractNotification
    public UnparsedNotification setResourceUri(String str) {
        return (UnparsedNotification) super.setResourceUri(str);
    }

    @Override // com.google.api.client.googleapis.notifications.AbstractNotification
    public UnparsedNotification setChannelId(String str) {
        return (UnparsedNotification) super.setChannelId(str);
    }

    @Override // com.google.api.client.googleapis.notifications.AbstractNotification
    public UnparsedNotification setChannelExpiration(String str) {
        return (UnparsedNotification) super.setChannelExpiration(str);
    }

    @Override // com.google.api.client.googleapis.notifications.AbstractNotification
    public UnparsedNotification setChannelToken(String str) {
        return (UnparsedNotification) super.setChannelToken(str);
    }

    @Override // com.google.api.client.googleapis.notifications.AbstractNotification
    public UnparsedNotification setChanged(String str) {
        return (UnparsedNotification) super.setChanged(str);
    }

    @Override // com.google.api.client.googleapis.notifications.AbstractNotification
    public String toString() {
        return super.toStringHelper().add("contentType", this.contentType).toString();
    }
}
