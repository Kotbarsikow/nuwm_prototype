package com.google.api.services.calendar;

import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.googleapis.services.json.CommonGoogleJsonClientRequestInitializer;
import java.io.IOException;

/* loaded from: classes2.dex */
public class CalendarRequestInitializer extends CommonGoogleJsonClientRequestInitializer {
    protected void initializeCalendarRequest(CalendarRequest<?> calendarRequest) throws IOException {
    }

    public CalendarRequestInitializer() {
    }

    public CalendarRequestInitializer(String str) {
        super(str);
    }

    public CalendarRequestInitializer(String str, String str2) {
        super(str, str2);
    }

    @Override // com.google.api.client.googleapis.services.json.CommonGoogleJsonClientRequestInitializer
    public final void initializeJsonRequest(AbstractGoogleJsonClientRequest<?> abstractGoogleJsonClientRequest) throws IOException {
        super.initializeJsonRequest(abstractGoogleJsonClientRequest);
        initializeCalendarRequest((CalendarRequest) abstractGoogleJsonClientRequest);
    }
}
