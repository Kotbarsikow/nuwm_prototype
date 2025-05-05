package com.m_myr.nuwm.nuwmschedule.data.models;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class TimetableIdentifier implements Serializable {
    private static final int GROUP_NAME = 2;
    private static final int ID = 0;
    private static final int ONLY_EVENT = 5;
    private static final int PERSON_NAME = 1;
    private static final int ROOM_NAME = 6;
    private static final int SCHEDULE_ID = 3;
    private static final int STREAM_ID = 4;
    String field;
    boolean self;
    int type;
    String value;

    private TimetableIdentifier(int type, String field, String value, boolean self) {
        this.type = type;
        this.field = field;
        this.value = value;
        this.self = self;
    }

    public static TimetableIdentifier byId(int id) {
        return new TimetableIdentifier(0, "id", String.valueOf(id), false);
    }

    public static TimetableIdentifier byPersonName(String name) {
        return new TimetableIdentifier(1, "teacher", name, false);
    }

    public static TimetableIdentifier byPersonName(String lastName, String initials) {
        return byPersonName(lastName + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + initials);
    }

    public static TimetableIdentifier byGroupName(String name) {
        return new TimetableIdentifier(2, "group", name, false);
    }

    public static TimetableIdentifier byRoom(String name) {
        return new TimetableIdentifier(6, "room", name, false);
    }

    public static TimetableIdentifier byStreams(String streams) {
        return new TimetableIdentifier(4, "streams", streams, false);
    }

    public static TimetableIdentifier byScheduleId(int id) {
        return new TimetableIdentifier(3, "schedule_id", String.valueOf(id), false);
    }

    public static TimetableIdentifier getSelf(AppDataManager appDataManager) {
        if (appDataManager.isStudent()) {
            return new TimetableIdentifier(2, "group", appDataManager.getStudent().getGroupName(), true);
        }
        if (appDataManager.isOffice()) {
            return new TimetableIdentifier(5, "onlyEvent", "true", true);
        }
        LoggedUser user = appDataManager.getUser();
        if (appDataManager.isEmployee() && appDataManager.getEmployee().getSheduleId() != 0) {
            return new TimetableIdentifier(3, "schedule_id", String.valueOf(appDataManager.getEmployee().getSheduleId()), true);
        }
        return new TimetableIdentifier(1, "teacher", user.getLastName() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + user.getInitials(), true);
    }

    public static TimetableIdentifier byPerson(EmployerInfo employerInfo) {
        if (employerInfo.getSheduleId() != 0) {
            return byScheduleId(employerInfo.getSheduleId());
        }
        return byPersonName(employerInfo.getLastName(), employerInfo.getInitials());
    }

    public String getField() {
        return this.field;
    }

    public String getValue() {
        return this.value;
    }

    public boolean isSelf() {
        return this.self;
    }
}
