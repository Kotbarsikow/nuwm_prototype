package com.m_myr.nuwm.nuwmschedule.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import okhttp3.HttpUrl;

/* loaded from: classes2.dex */
public class NetworkClient {
    public static String post(HttpUrl url, HttpUrl formData, int timeout) throws Exception {
        byte[] bytes = formData.encodedQuery().getBytes(StandardCharsets.UTF_8);
        int length = bytes.length;
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url.toString()).openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setConnectTimeout(timeout);
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("charset", "utf-8");
        httpURLConnection.setRequestProperty("Content-Length", Integer.toString(length));
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoOutput(true);
        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
        dataOutputStream.write(bytes);
        dataOutputStream.flush();
        dataOutputStream.close();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                stringBuffer.append(readLine);
            } else {
                bufferedReader.close();
                httpURLConnection.disconnect();
                return stringBuffer.toString();
            }
        }
    }

    public static String get(HttpUrl urlBuilder, int timeout) throws Exception {
        return get(urlBuilder.toString(), timeout);
    }

    public static String get(String urlBuilder, int timeout) throws Exception {
        StringBuilder sb = new StringBuilder();
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(urlBuilder).openConnection();
        httpURLConnection.setConnectTimeout(timeout);
        httpURLConnection.setReadTimeout(timeout * 2);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setRequestMethod("GET");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
        httpURLConnection.getResponseCode();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                if (!readLine.isEmpty()) {
                    sb.append(readLine);
                }
            } else {
                bufferedReader.close();
                return sb.toString();
            }
        }
    }
}
