package com.m_myr.nuwm.nuwmschedule.network.models;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.data.models.KeyPairValue;
import com.m_myr.nuwm.nuwmschedule.data.models.RepositoryItem;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class RepositoryPage {

    @SerializedName("doc_types")
    ArrayList<KeyPairValue<String>> documentTypes;

    @SerializedName(FirebaseAnalytics.Param.ITEMS)
    ArrayList<RepositoryItem> items;

    public ArrayList<RepositoryItem> getItems() {
        return this.items;
    }

    public ArrayList<KeyPairValue<String>> getDocumentTypes() {
        return this.documentTypes;
    }
}
