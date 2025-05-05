package com.m_myr.nuwm.nuwmschedule.network.models;

import com.m_myr.nuwm.nuwmschedule.data.models.Event;

/* loaded from: classes2.dex */
public class EventCreateResponse {
    boolean created;
    Event event;

    public boolean isCreated() {
        return this.created;
    }

    public Event getEvent() {
        return this.event;
    }
}
