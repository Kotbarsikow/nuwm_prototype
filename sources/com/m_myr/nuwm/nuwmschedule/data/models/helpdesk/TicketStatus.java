package com.m_myr.nuwm.nuwmschedule.data.models.helpdesk;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: TicketStatus.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u000eB\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000f"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/TicketStatus;", "", "Ljava/io/Serializable;", NotificationCompat.CATEGORY_STATUS, "", "(Ljava/lang/String;II)V", "getStatus", "()I", "NEW", "WAIT", "ANSWER_SENT", "REVIEWED", "ON_REVIEW", "HOLD", "Companion", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TicketStatus extends Enum<TicketStatus> implements Serializable {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ TicketStatus[] $VALUES;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private final int status;

    @SerializedName("0")
    public static final TicketStatus NEW = new TicketStatus("NEW", 0, 0);

    @SerializedName("1")
    public static final TicketStatus WAIT = new TicketStatus("WAIT", 1, 1);

    @SerializedName(ExifInterface.GPS_MEASUREMENT_2D)
    public static final TicketStatus ANSWER_SENT = new TicketStatus("ANSWER_SENT", 2, 2);

    @SerializedName(ExifInterface.GPS_MEASUREMENT_3D)
    public static final TicketStatus REVIEWED = new TicketStatus("REVIEWED", 3, 3);

    @SerializedName("4")
    public static final TicketStatus ON_REVIEW = new TicketStatus("ON_REVIEW", 4, 4);

    @SerializedName("5")
    public static final TicketStatus HOLD = new TicketStatus("HOLD", 5, 5);

    private static final /* synthetic */ TicketStatus[] $values() {
        return new TicketStatus[]{NEW, WAIT, ANSWER_SENT, REVIEWED, ON_REVIEW, HOLD};
    }

    public static EnumEntries<TicketStatus> getEntries() {
        return $ENTRIES;
    }

    public static TicketStatus valueOf(String str) {
        return (TicketStatus) Enum.valueOf(TicketStatus.class, str);
    }

    public static TicketStatus[] values() {
        return (TicketStatus[]) $VALUES.clone();
    }

    private TicketStatus(String str, int i, int i2) {
        super(str, i);
        this.status = i2;
    }

    public final int getStatus() {
        return this.status;
    }

    static {
        TicketStatus[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        INSTANCE = new Companion(null);
    }

    /* compiled from: TicketStatus.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/TicketStatus$Companion;", "", "()V", "fromInt", "Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/TicketStatus;", "value", "", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TicketStatus fromInt(int value) {
            for (TicketStatus ticketStatus : TicketStatus.values()) {
                if (ticketStatus.getStatus() == value) {
                    return ticketStatus;
                }
            }
            throw new NoSuchElementException("Array contains no element matching the predicate.");
        }
    }
}
