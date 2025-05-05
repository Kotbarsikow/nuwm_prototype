package com.m_myr.nuwm.nuwmschedule.data.models;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class Attendees implements Serializable {
    String email;
    String name;
    String relationship;
    String type;

    public Attendees(String name, String relationship, String email, String type) {
        this.name = name;
        this.relationship = relationship;
        this.email = email;
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(String type) {
        this.type = type;
    }
}
