package com.m_myr.nuwm.nuwmschedule.data.models.roomLocation;

import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RoomLocationInfo.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000b\u001a\u00020\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/data/models/roomLocation/RoomLocationInfo;", "", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Lcom/m_myr/nuwm/nuwmschedule/data/models/roomLocation/JsonFileBuilding;", "room", "Lcom/m_myr/nuwm/nuwmschedule/data/models/roomLocation/FuncArea;", "(Lcom/m_myr/nuwm/nuwmschedule/data/models/roomLocation/JsonFileBuilding;Lcom/m_myr/nuwm/nuwmschedule/data/models/roomLocation/FuncArea;)V", "getData", "()Lcom/m_myr/nuwm/nuwmschedule/data/models/roomLocation/JsonFileBuilding;", "getRoom", "()Lcom/m_myr/nuwm/nuwmschedule/data/models/roomLocation/FuncArea;", "isRoom", "", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RoomLocationInfo {
    private final JsonFileBuilding data;
    private final FuncArea room;

    public RoomLocationInfo(JsonFileBuilding data, FuncArea funcArea) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
        this.room = funcArea;
    }

    public final JsonFileBuilding getData() {
        return this.data;
    }

    public final FuncArea getRoom() {
        return this.room;
    }

    public final boolean isRoom() {
        return this.room != null;
    }
}
