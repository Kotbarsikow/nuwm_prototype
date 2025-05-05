package com.m_myr.nuwm.nuwmschedule.domain.interfaces;

import android.content.Intent;
import org.mortbay.jetty.MimeTypes;

/* loaded from: classes2.dex */
public interface ShareIntent {
    Intent createShareIntent();

    String createShareText();

    String getType();

    /* renamed from: com.m_myr.nuwm.nuwmschedule.domain.interfaces.ShareIntent$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static Intent $default$createShareIntent(ShareIntent _this) {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType(MimeTypes.TEXT_PLAIN);
            intent.putExtra("android.intent.extra.SUBJECT", _this.getType());
            intent.putExtra("android.intent.extra.TEXT", _this.createShareText());
            return Intent.createChooser(intent, "Поділитися ");
        }
    }
}
