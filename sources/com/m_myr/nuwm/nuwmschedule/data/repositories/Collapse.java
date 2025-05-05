package com.m_myr.nuwm.nuwmschedule.data.repositories;

import androidx.core.app.NotificationCompat;
import com.m_myr.nuwm.nuwmschedule.data.models.EventLinks;
import com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: SchedulerProvider2.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/data/repositories/Collapse;", "", "itemTimetableContract", "Lcom/m_myr/nuwm/nuwmschedule/data/models/ItemTimetableContract;", NotificationCompat.CATEGORY_EVENT, "Lcom/m_myr/nuwm/nuwmschedule/data/models/EventLinks;", "checkMatch", "", "(Lcom/m_myr/nuwm/nuwmschedule/data/models/ItemTimetableContract;Lcom/m_myr/nuwm/nuwmschedule/data/models/EventLinks;F)V", "getCheckMatch", "()F", "getEvent", "()Lcom/m_myr/nuwm/nuwmschedule/data/models/EventLinks;", "getItemTimetableContract", "()Lcom/m_myr/nuwm/nuwmschedule/data/models/ItemTimetableContract;", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Collapse {
    private final float checkMatch;
    private final EventLinks event;
    private final ItemTimetableContract itemTimetableContract;

    public Collapse(ItemTimetableContract itemTimetableContract, EventLinks event, float f) {
        Intrinsics.checkNotNullParameter(itemTimetableContract, "itemTimetableContract");
        Intrinsics.checkNotNullParameter(event, "event");
        this.itemTimetableContract = itemTimetableContract;
        this.event = event;
        this.checkMatch = f;
        event.onlineType = "Онлайн " + itemTimetableContract.getType();
        int i = 0;
        for (Object obj : SchedulerProvider2Kt.getKeyWordsType()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String title = this.event.getTitle();
            Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
            if (StringsKt.contains((CharSequence) title, (CharSequence) obj, true)) {
                this.event.onlineType = SchedulerProvider2Kt.getTypeName().get(i);
            }
            i = i2;
        }
    }

    public final float getCheckMatch() {
        return this.checkMatch;
    }

    public final EventLinks getEvent() {
        return this.event;
    }

    public final ItemTimetableContract getItemTimetableContract() {
        return this.itemTimetableContract;
    }
}
