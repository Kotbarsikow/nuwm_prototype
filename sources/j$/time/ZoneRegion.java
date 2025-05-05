package j$.time;

import j$.time.zone.ZoneRules;
import j$.time.zone.ZoneRulesException;
import j$.time.zone.ZoneRulesProvider;
import j$.util.Objects;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;

/* loaded from: classes4.dex */
final class ZoneRegion extends ZoneId {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final long serialVersionUID = 8386373296231747096L;
    private final String id;
    private final transient ZoneRules rules;

    static ZoneRegion ofId(String str) {
        ZoneRules zoneRules;
        Objects.requireNonNull(str, "zoneId");
        int length = str.length();
        if (length >= 2) {
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if ((charAt < 'a' || charAt > 'z') && ((charAt < 'A' || charAt > 'Z') && ((charAt != '/' || i == 0) && ((charAt < '0' || charAt > '9' || i == 0) && ((charAt != '~' || i == 0) && ((charAt != '.' || i == 0) && ((charAt != '_' || i == 0) && ((charAt != '+' || i == 0) && (charAt != '-' || i == 0))))))))) {
                    throw new DateTimeException("Invalid ID for region-based ZoneId, invalid format: ".concat(str));
                }
            }
            try {
                zoneRules = ZoneRulesProvider.getRules(str, true);
            } catch (ZoneRulesException unused) {
                zoneRules = null;
            }
            return new ZoneRegion(str, zoneRules);
        }
        throw new DateTimeException("Invalid ID for region-based ZoneId, invalid format: ".concat(str));
    }

    ZoneRegion(String str, ZoneRules zoneRules) {
        this.id = str;
        this.rules = zoneRules;
    }

    @Override // j$.time.ZoneId
    public final String getId() {
        return this.id;
    }

    @Override // j$.time.ZoneId
    public final ZoneRules getRules() {
        ZoneRules zoneRules = this.rules;
        return zoneRules != null ? zoneRules : ZoneRulesProvider.getRules(this.id, false);
    }

    private Object writeReplace() {
        return new Ser((byte) 7, this);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    @Override // j$.time.ZoneId
    final void write(ObjectOutput objectOutput) {
        objectOutput.writeByte(7);
        objectOutput.writeUTF(this.id);
    }

    final void writeExternal(DataOutput dataOutput) {
        dataOutput.writeUTF(this.id);
    }
}
