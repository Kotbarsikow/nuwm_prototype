package com.m_myr.nuwm.nuwmschedule.data.models;

import android.text.Html;
import android.text.Spanned;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public class HtmlData {

    @SerializedName("html")
    String html;

    public Spanned getHtml() {
        return Html.fromHtml(this.html);
    }
}
