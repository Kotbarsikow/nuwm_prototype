package com.m_myr.nuwm.nuwmschedule.data.models.push;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.utils.LinksResolver;

/* loaded from: classes2.dex */
public class LinkPush extends SimplePush {
    public static final transient int PUSH_LINK_INT = 7;

    @SerializedName(ImagesContract.URL)
    protected String url;

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.push.SimplePush, com.m_myr.nuwm.nuwmschedule.data.models.push.BasePush
    public int getType() {
        return 7;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.push.SimplePush, com.m_myr.nuwm.nuwmschedule.data.models.push.BasePush
    public Intent getIntent(Context context) {
        return LinksResolver.getWebIntent(this.url);
    }
}
