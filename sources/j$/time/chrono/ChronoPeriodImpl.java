package j$.time.chrono;

import j$.time.temporal.ChronoUnit;
import j$.util.function.BiConsumer$CC;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;

/* loaded from: classes4.dex */
final class ChronoPeriodImpl implements Serializable {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final long serialVersionUID = 57387258289L;
    private final Chronology chrono;
    final int days;
    final int months;
    final int years;

    static {
        BiConsumer$CC.m(new Object[]{ChronoUnit.YEARS, ChronoUnit.MONTHS, ChronoUnit.DAYS});
    }

    ChronoPeriodImpl(Chronology chronology, int i, int i2, int i3) {
        this.chrono = chronology;
        this.years = i;
        this.months = i2;
        this.days = i3;
    }

    public final String toString() {
        Chronology chronology = this.chrono;
        int i = this.days;
        int i2 = this.months;
        int i3 = this.years;
        if (i3 == 0 && i2 == 0 && i == 0) {
            return chronology.toString() + " P0D";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(chronology.toString());
        sb.append(" P");
        if (i3 != 0) {
            sb.append(i3);
            sb.append('Y');
        }
        if (i2 != 0) {
            sb.append(i2);
            sb.append('M');
        }
        if (i != 0) {
            sb.append(i);
            sb.append('D');
        }
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ChronoPeriodImpl)) {
            return false;
        }
        ChronoPeriodImpl chronoPeriodImpl = (ChronoPeriodImpl) obj;
        return this.years == chronoPeriodImpl.years && this.months == chronoPeriodImpl.months && this.days == chronoPeriodImpl.days && this.chrono.equals(chronoPeriodImpl.chrono);
    }

    public final int hashCode() {
        return this.chrono.hashCode() ^ (Integer.rotateLeft(this.days, 16) + (Integer.rotateLeft(this.months, 8) + this.years));
    }

    protected Object writeReplace() {
        return new Ser((byte) 9, this);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void writeExternal(ObjectOutput objectOutput) {
        objectOutput.writeUTF(this.chrono.getId());
        objectOutput.writeInt(this.years);
        objectOutput.writeInt(this.months);
        objectOutput.writeInt(this.days);
    }
}
