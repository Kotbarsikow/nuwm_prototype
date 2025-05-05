package j$.time;

import com.google.common.base.Ascii;
import j$.time.temporal.ChronoUnit;
import j$.util.function.BiConsumer$CC;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public final class Period implements Serializable {
    public static final Period ZERO = new Period(0, 0, 0);
    private static final long serialVersionUID = -3587258372562876L;
    private final int days;
    private final int months;
    private final int years;

    static {
        Pattern.compile("([-+]?)P(?:([-+]?[0-9]+)Y)?(?:([-+]?[0-9]+)M)?(?:([-+]?[0-9]+)W)?(?:([-+]?[0-9]+)D)?", 2);
        BiConsumer$CC.m(new Object[]{ChronoUnit.YEARS, ChronoUnit.MONTHS, ChronoUnit.DAYS});
    }

    private Period(int i, int i2, int i3) {
        this.years = i;
        this.months = i2;
        this.days = i3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Period)) {
            return false;
        }
        Period period = (Period) obj;
        return this.years == period.years && this.months == period.months && this.days == period.days;
    }

    public final int hashCode() {
        return Integer.rotateLeft(this.days, 16) + Integer.rotateLeft(this.months, 8) + this.years;
    }

    public final String toString() {
        if (this == ZERO) {
            return "P0D";
        }
        StringBuilder sb = new StringBuilder("P");
        int i = this.years;
        if (i != 0) {
            sb.append(i);
            sb.append('Y');
        }
        int i2 = this.months;
        if (i2 != 0) {
            sb.append(i2);
            sb.append('M');
        }
        int i3 = this.days;
        if (i3 != 0) {
            sb.append(i3);
            sb.append('D');
        }
        return sb.toString();
    }

    private Object writeReplace() {
        return new Ser(Ascii.SO, this);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void writeExternal(ObjectOutput objectOutput) {
        objectOutput.writeInt(this.years);
        objectOutput.writeInt(this.months);
        objectOutput.writeInt(this.days);
    }

    static Period readExternal(ObjectInput objectInput) {
        int readInt = objectInput.readInt();
        int readInt2 = objectInput.readInt();
        int readInt3 = objectInput.readInt();
        if ((readInt | readInt2 | readInt3) == 0) {
            return ZERO;
        }
        return new Period(readInt, readInt2, readInt3);
    }
}
