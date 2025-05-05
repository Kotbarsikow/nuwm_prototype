package org.mortbay.jetty.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

/* loaded from: classes3.dex */
public class PKCS12Import {
    public static void main(String[] strArr) throws Exception {
        File file;
        if (strArr.length < 1) {
            System.err.println("usage: java PKCS12Import {pkcs12file} [newjksfile]");
            System.exit(1);
        }
        int i = 0;
        File file2 = new File(strArr[0]);
        if (strArr.length > 1) {
            file = new File(strArr[1]);
        } else {
            file = new File("newstore.jks");
        }
        if (!file2.canRead()) {
            System.err.println(new StringBuffer("Unable to access input keystore: ").append(file2.getPath()).toString());
            System.exit(2);
        }
        if (file.exists() && !file.canWrite()) {
            System.err.println(new StringBuffer("Output file is not writable: ").append(file.getPath()).toString());
            System.exit(2);
        }
        KeyStore keyStore = KeyStore.getInstance("pkcs12");
        KeyStore keyStore2 = KeyStore.getInstance("jks");
        System.out.print("Enter input keystore passphrase: ");
        char[] readPassphrase = readPassphrase();
        System.out.print("Enter output keystore passphrase: ");
        char[] readPassphrase2 = readPassphrase();
        keyStore.load(new FileInputStream(file2), readPassphrase);
        keyStore2.load(file.exists() ? new FileInputStream(file) : null, readPassphrase2);
        Enumeration<String> aliases = keyStore.aliases();
        while (aliases.hasMoreElements()) {
            String nextElement = aliases.nextElement();
            int i2 = i + 1;
            System.err.println(new StringBuffer("Alias ").append(i).append(": ").append(nextElement).toString());
            if (keyStore.isKeyEntry(nextElement)) {
                System.err.println(new StringBuffer("Adding key for alias ").append(nextElement).toString());
                keyStore2.setKeyEntry(nextElement, keyStore.getKey(nextElement, readPassphrase), readPassphrase2, keyStore.getCertificateChain(nextElement));
            }
            i = i2;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        keyStore2.store(fileOutputStream, readPassphrase2);
        fileOutputStream.close();
    }

    static void dumpChain(Certificate[] certificateArr) {
        for (Certificate certificate : certificateArr) {
            if (certificate instanceof X509Certificate) {
                X509Certificate x509Certificate = (X509Certificate) certificate;
                System.err.println(new StringBuffer("subject: ").append(x509Certificate.getSubjectDN()).toString());
                System.err.println(new StringBuffer("issuer: ").append(x509Certificate.getIssuerDN()).toString());
            }
        }
    }

    static char[] readPassphrase() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        char[] cArr = new char[256];
        int i = 0;
        while (i < 256) {
            char read = (char) inputStreamReader.read();
            if (read == '\n' || read == '\r') {
                break;
            }
            cArr[i] = read;
            i++;
        }
        char[] cArr2 = new char[i];
        System.arraycopy(cArr, 0, cArr2, 0, i);
        return cArr2;
    }
}
