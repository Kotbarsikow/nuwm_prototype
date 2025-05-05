package com.google.api.client.googleapis.javanet;

import com.google.api.client.googleapis.GoogleUtils;
import com.google.api.client.http.javanet.NetHttpTransport;
import java.io.IOException;
import java.security.GeneralSecurityException;

/* loaded from: classes2.dex */
public class GoogleNetHttpTransport {
    public static NetHttpTransport newTrustedTransport() throws GeneralSecurityException, IOException {
        return new NetHttpTransport.Builder().trustCertificates(GoogleUtils.getCertificateTrustStore()).build();
    }

    private GoogleNetHttpTransport() {
    }
}
