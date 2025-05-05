package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.StringTitleCollection;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;

/* loaded from: classes2.dex */
public class Recipient implements StringTitleCollection {

    @SerializedName("topic_name")
    String name;

    @SerializedName("topic")
    String topic;

    private Recipient(String name, String topic) {
        this.name = name;
        this.topic = topic;
    }

    public static Recipient group(String s) {
        return new Recipient(s, Utils.md5(s.trim()));
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.StringTitleCollection
    public String getTitle() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == null || this.name == null || (str = this.topic) == null || !(obj instanceof Recipient)) {
            return false;
        }
        return str.toLowerCase().equals(((Recipient) obj).topic.toLowerCase());
    }

    public int hashCode() {
        String str = this.topic;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public boolean equalsTopicName(String t) {
        String str;
        if (t == null || (str = this.name) == null) {
            return false;
        }
        return t.equals(str.toLowerCase());
    }
}
