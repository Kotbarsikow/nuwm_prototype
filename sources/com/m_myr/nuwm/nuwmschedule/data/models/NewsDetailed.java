package com.m_myr.nuwm.nuwmschedule.data.models;

import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class NewsDetailed implements Serializable {

    @SerializedName(FirebaseAnalytics.Param.CONTENT)
    private String Content;
    boolean checked;

    @SerializedName("docs")
    private Document[] doc;

    @SerializedName("g_images")
    private String[] g_images;

    public boolean haveDoc() {
        Document[] documentArr = this.doc;
        return documentArr != null && documentArr.length > 0;
    }

    public String getContent() {
        if (!this.checked && this.Content.contains("iframe")) {
            StringBuffer stringBuffer = new StringBuffer();
            int i = 0;
            while (i >= 0) {
                int indexOf = this.Content.indexOf("<iframe");
                int indexOf2 = this.Content.indexOf("</iframe>") + 9;
                stringBuffer.append((CharSequence) this.Content, i, indexOf);
                String substring = this.Content.substring(indexOf, indexOf2);
                this.Content = this.Content.substring(indexOf2);
                stringBuffer.append(substring.replace("<iframe", "<a").replace("src", "href").replace("</iframe>", "<img src=\"http://x1.nuwm.edu.ua/file/video_stub.PNG\" ></a>"));
                i = this.Content.indexOf("<iframe");
            }
            stringBuffer.append(this.Content);
            this.Content = stringBuffer.toString();
        }
        this.checked = true;
        Log.e("Content", this.Content);
        return this.Content;
    }

    public Document[] getDoc() {
        return this.doc;
    }

    public String[] getG_images() {
        return this.g_images;
    }
}
