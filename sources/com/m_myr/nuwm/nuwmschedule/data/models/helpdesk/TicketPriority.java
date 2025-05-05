package com.m_myr.nuwm.nuwmschedule.data.models.helpdesk;

import androidx.exifinterface.media.ExifInterface;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: TicketPriority.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\fB\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\r"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/TicketPriority;", "", "Ljava/io/Serializable;", "type", "", "(Ljava/lang/String;II)V", "getType", "()I", "CRITICALLY", "AVERAGE", "HIGH", "LOW", "Companion", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TicketPriority extends Enum<TicketPriority> implements Serializable {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ TicketPriority[] $VALUES;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private final int type;

    @SerializedName("0")
    public static final TicketPriority CRITICALLY = new TicketPriority("CRITICALLY", 0, 0);

    @SerializedName("1")
    public static final TicketPriority AVERAGE = new TicketPriority("AVERAGE", 1, 1);

    @SerializedName(ExifInterface.GPS_MEASUREMENT_2D)
    public static final TicketPriority HIGH = new TicketPriority("HIGH", 2, 2);

    @SerializedName(ExifInterface.GPS_MEASUREMENT_3D)
    public static final TicketPriority LOW = new TicketPriority("LOW", 3, 3);

    private static final /* synthetic */ TicketPriority[] $values() {
        return new TicketPriority[]{CRITICALLY, AVERAGE, HIGH, LOW};
    }

    public static EnumEntries<TicketPriority> getEntries() {
        return $ENTRIES;
    }

    public static TicketPriority valueOf(String str) {
        return (TicketPriority) Enum.valueOf(TicketPriority.class, str);
    }

    public static TicketPriority[] values() {
        return (TicketPriority[]) $VALUES.clone();
    }

    private TicketPriority(String str, int i, int i2) {
        super(str, i);
        this.type = i2;
    }

    public final int getType() {
        return this.type;
    }

    static {
        TicketPriority[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        INSTANCE = new Companion(null);
    }

    /* compiled from: TicketPriority.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/TicketPriority$Companion;", "", "()V", "fromInt", "Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/TicketPriority;", "value", "", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TicketPriority fromInt(int value) {
            for (TicketPriority ticketPriority : TicketPriority.values()) {
                if (ticketPriority.getType() == value) {
                    return ticketPriority;
                }
            }
            throw new NoSuchElementException("Array contains no element matching the predicate.");
        }
    }
}
