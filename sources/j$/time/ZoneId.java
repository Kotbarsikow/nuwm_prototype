package j$.time;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.net.HttpHeaders;
import j$.time.zone.ZoneRules;
import j$.util.Objects;
import j$.util.function.BiConsumer$CC;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class ZoneId implements Serializable {
    private static final long serialVersionUID = 8352817235686L;

    public abstract String getId();

    public abstract ZoneRules getRules();

    abstract void write(ObjectOutput objectOutput);

    static {
        Map.Entry[] entryArr = {BiConsumer$CC.m("ACT", "Australia/Darwin"), BiConsumer$CC.m("AET", "Australia/Sydney"), BiConsumer$CC.m("AGT", "America/Argentina/Buenos_Aires"), BiConsumer$CC.m("ART", "Africa/Cairo"), BiConsumer$CC.m("AST", "America/Anchorage"), BiConsumer$CC.m("BET", "America/Sao_Paulo"), BiConsumer$CC.m("BST", "Asia/Dhaka"), BiConsumer$CC.m("CAT", "Africa/Harare"), BiConsumer$CC.m("CNT", "America/St_Johns"), BiConsumer$CC.m("CST", "America/Chicago"), BiConsumer$CC.m("CTT", "Asia/Shanghai"), BiConsumer$CC.m("EAT", "Africa/Addis_Ababa"), BiConsumer$CC.m(HttpHeaders.ECT, "Europe/Paris"), BiConsumer$CC.m("IET", "America/Indiana/Indianapolis"), BiConsumer$CC.m("IST", "Asia/Kolkata"), BiConsumer$CC.m("JST", "Asia/Tokyo"), BiConsumer$CC.m("MIT", "Pacific/Apia"), BiConsumer$CC.m("NET", "Asia/Yerevan"), BiConsumer$CC.m("NST", "Pacific/Auckland"), BiConsumer$CC.m("PLT", "Asia/Karachi"), BiConsumer$CC.m("PNT", "America/Phoenix"), BiConsumer$CC.m("PRT", "America/Puerto_Rico"), BiConsumer$CC.m("PST", "America/Los_Angeles"), BiConsumer$CC.m("SST", "Pacific/Guadalcanal"), BiConsumer$CC.m("VST", "Asia/Ho_Chi_Minh"), BiConsumer$CC.m("EST", "-05:00"), BiConsumer$CC.m("MST", "-07:00"), BiConsumer$CC.m("HST", "-10:00")};
        HashMap hashMap = new HashMap(28);
        for (int i = 0; i < 28; i++) {
            Map.Entry entry = entryArr[i];
            Object requireNonNull = Objects.requireNonNull(entry.getKey());
            if (hashMap.put(requireNonNull, Objects.requireNonNull(entry.getValue())) != null) {
                throw new IllegalArgumentException("duplicate key: " + requireNonNull);
            }
        }
        Collections.unmodifiableMap(hashMap);
    }

    public static ZoneId ofOffset(String str, ZoneOffset zoneOffset) {
        Objects.requireNonNull(str, "prefix");
        Objects.requireNonNull(zoneOffset, TypedValues.Cycle.S_WAVE_OFFSET);
        if (str.isEmpty()) {
            return zoneOffset;
        }
        if (!str.equals("GMT") && !str.equals("UTC") && !str.equals("UT")) {
            throw new IllegalArgumentException("prefix should be GMT, UTC or UT, is: ".concat(str));
        }
        if (zoneOffset.getTotalSeconds() != 0) {
            str = str.concat(zoneOffset.getId());
        }
        return new ZoneRegion(str, ZoneRules.of(zoneOffset));
    }

    static ZoneId of(String str) {
        Objects.requireNonNull(str, "zoneId");
        if (str.length() <= 1 || str.startsWith("+") || str.startsWith("-")) {
            return ZoneOffset.of(str);
        }
        if (str.startsWith("UTC") || str.startsWith("GMT")) {
            return ofWithPrefix(str, 3);
        }
        if (str.startsWith("UT")) {
            return ofWithPrefix(str, 2);
        }
        return ZoneRegion.ofId(str);
    }

    private static ZoneId ofWithPrefix(String str, int i) {
        String substring = str.substring(0, i);
        if (str.length() == i) {
            return ofOffset(substring, ZoneOffset.UTC);
        }
        if (str.charAt(i) != '+' && str.charAt(i) != '-') {
            return ZoneRegion.ofId(str);
        }
        try {
            ZoneOffset of = ZoneOffset.of(str.substring(i));
            if (of == ZoneOffset.UTC) {
                return ofOffset(substring, of);
            }
            return ofOffset(substring, of);
        } catch (DateTimeException e) {
            throw new DateTimeException("Invalid ID for offset-based ZoneId: ".concat(str), e);
        }
    }

    ZoneId() {
        if (getClass() != ZoneOffset.class && getClass() != ZoneRegion.class) {
            throw new AssertionError("Invalid subclass");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ZoneId) {
            return getId().equals(((ZoneId) obj).getId());
        }
        return false;
    }

    public int hashCode() {
        return getId().hashCode();
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    public String toString() {
        return getId();
    }

    private Object writeReplace() {
        return new Ser((byte) 7, this);
    }
}
