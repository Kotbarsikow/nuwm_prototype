package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Data;
import com.google.api.client.util.Key;
import java.util.List;

/* loaded from: classes2.dex */
public final class CalendarListEntry extends GenericJson {

    @Key
    private String accessRole;

    @Key
    private String backgroundColor;

    @Key
    private String colorId;

    @Key
    private ConferenceProperties conferenceProperties;

    @Key
    private List<EventReminder> defaultReminders;

    @Key
    private Boolean deleted;

    @Key
    private String description;

    @Key
    private String etag;

    @Key
    private String foregroundColor;

    @Key
    private Boolean hidden;

    @Key
    private String id;

    @Key
    private String kind;

    @Key
    private String location;

    @Key
    private NotificationSettings notificationSettings;

    @Key
    private Boolean primary;

    @Key
    private Boolean selected;

    @Key
    private String summary;

    @Key
    private String summaryOverride;

    @Key
    private String timeZone;

    public String getAccessRole() {
        return this.accessRole;
    }

    public CalendarListEntry setAccessRole(String str) {
        this.accessRole = str;
        return this;
    }

    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    public CalendarListEntry setBackgroundColor(String str) {
        this.backgroundColor = str;
        return this;
    }

    public String getColorId() {
        return this.colorId;
    }

    public CalendarListEntry setColorId(String str) {
        this.colorId = str;
        return this;
    }

    public ConferenceProperties getConferenceProperties() {
        return this.conferenceProperties;
    }

    public CalendarListEntry setConferenceProperties(ConferenceProperties conferenceProperties) {
        this.conferenceProperties = conferenceProperties;
        return this;
    }

    public List<EventReminder> getDefaultReminders() {
        return this.defaultReminders;
    }

    public CalendarListEntry setDefaultReminders(List<EventReminder> list) {
        this.defaultReminders = list;
        return this;
    }

    public Boolean getDeleted() {
        return this.deleted;
    }

    public CalendarListEntry setDeleted(Boolean bool) {
        this.deleted = bool;
        return this;
    }

    public boolean isDeleted() {
        Boolean bool = this.deleted;
        if (bool == null || bool == Data.NULL_BOOLEAN) {
            return false;
        }
        return this.deleted.booleanValue();
    }

    public String getDescription() {
        return this.description;
    }

    public CalendarListEntry setDescription(String str) {
        this.description = str;
        return this;
    }

    public String getEtag() {
        return this.etag;
    }

    public CalendarListEntry setEtag(String str) {
        this.etag = str;
        return this;
    }

    public String getForegroundColor() {
        return this.foregroundColor;
    }

    public CalendarListEntry setForegroundColor(String str) {
        this.foregroundColor = str;
        return this;
    }

    public Boolean getHidden() {
        return this.hidden;
    }

    public CalendarListEntry setHidden(Boolean bool) {
        this.hidden = bool;
        return this;
    }

    public boolean isHidden() {
        Boolean bool = this.hidden;
        if (bool == null || bool == Data.NULL_BOOLEAN) {
            return false;
        }
        return this.hidden.booleanValue();
    }

    public String getId() {
        return this.id;
    }

    public CalendarListEntry setId(String str) {
        this.id = str;
        return this;
    }

    public String getKind() {
        return this.kind;
    }

    public CalendarListEntry setKind(String str) {
        this.kind = str;
        return this;
    }

    public String getLocation() {
        return this.location;
    }

    public CalendarListEntry setLocation(String str) {
        this.location = str;
        return this;
    }

    public NotificationSettings getNotificationSettings() {
        return this.notificationSettings;
    }

    public CalendarListEntry setNotificationSettings(NotificationSettings notificationSettings) {
        this.notificationSettings = notificationSettings;
        return this;
    }

    public Boolean getPrimary() {
        return this.primary;
    }

    public CalendarListEntry setPrimary(Boolean bool) {
        this.primary = bool;
        return this;
    }

    public boolean isPrimary() {
        Boolean bool = this.primary;
        if (bool == null || bool == Data.NULL_BOOLEAN) {
            return false;
        }
        return this.primary.booleanValue();
    }

    public Boolean getSelected() {
        return this.selected;
    }

    public CalendarListEntry setSelected(Boolean bool) {
        this.selected = bool;
        return this;
    }

    public boolean isSelected() {
        Boolean bool = this.selected;
        if (bool == null || bool == Data.NULL_BOOLEAN) {
            return false;
        }
        return this.selected.booleanValue();
    }

    public String getSummary() {
        return this.summary;
    }

    public CalendarListEntry setSummary(String str) {
        this.summary = str;
        return this;
    }

    public String getSummaryOverride() {
        return this.summaryOverride;
    }

    public CalendarListEntry setSummaryOverride(String str) {
        this.summaryOverride = str;
        return this;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public CalendarListEntry setTimeZone(String str) {
        this.timeZone = str;
        return this;
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData
    public CalendarListEntry set(String str, Object obj) {
        return (CalendarListEntry) super.set(str, obj);
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData, java.util.AbstractMap
    public CalendarListEntry clone() {
        return (CalendarListEntry) super.clone();
    }

    public static final class NotificationSettings extends GenericJson {

        @Key
        private List<CalendarNotification> notifications;

        public List<CalendarNotification> getNotifications() {
            return this.notifications;
        }

        public NotificationSettings setNotifications(List<CalendarNotification> list) {
            this.notifications = list;
            return this;
        }

        @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData
        public NotificationSettings set(String str, Object obj) {
            return (NotificationSettings) super.set(str, obj);
        }

        @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData, java.util.AbstractMap
        public NotificationSettings clone() {
            return (NotificationSettings) super.clone();
        }
    }
}
