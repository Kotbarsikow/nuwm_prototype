package com.m_myr.nuwm.nuwmschedule.data.models.feed;

import android.content.Context;
import android.text.Spanned;
import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.data.models.Worker;
import com.m_myr.nuwm.nuwmschedule.ui.view.HtmlImproved;

/* loaded from: classes2.dex */
public class TextPost extends PostMessage {
    public static final int POST_TEXT = 2;

    @SerializedName("by")
    Worker Worker;

    @SerializedName("message")
    protected String message;
    private Spanned messageSpannable;

    public TextPost() {
        super(2);
    }

    public Spanned getMessage(Context context) {
        String str = this.message;
        if (str == null) {
            return null;
        }
        if (this.messageSpannable == null) {
            this.messageSpannable = HtmlImproved.fromHtml(str, context);
        }
        return this.messageSpannable;
    }
}
