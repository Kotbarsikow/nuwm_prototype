package j$.time.zone;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import j$.time.ZoneOffset;
import java.io.Externalizable;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.TimeZone;

/* loaded from: classes4.dex */
final class Ser implements Externalizable {
    private static final long serialVersionUID = -8885321777449118786L;
    private Serializable object;
    private byte type;

    public Ser() {
    }

    Ser(byte b, Serializable serializable) {
        this.type = b;
        this.object = serializable;
    }

    @Override // java.io.Externalizable
    public final void writeExternal(ObjectOutput objectOutput) {
        byte b = this.type;
        Serializable serializable = this.object;
        objectOutput.writeByte(b);
        if (b == 1) {
            ((ZoneRules) serializable).writeExternal(objectOutput);
            return;
        }
        if (b == 2) {
            ((ZoneOffsetTransition) serializable).writeExternal(objectOutput);
        } else if (b == 3) {
            ((ZoneOffsetTransitionRule) serializable).writeExternal(objectOutput);
        } else {
            if (b == 100) {
                ((ZoneRules) serializable).writeExternalTimeZone(objectOutput);
                return;
            }
            throw new InvalidClassException("Unknown serialized type");
        }
    }

    @Override // java.io.Externalizable
    public final void readExternal(ObjectInput objectInput) {
        Serializable readExternal;
        byte readByte = objectInput.readByte();
        this.type = readByte;
        if (readByte == 1) {
            readExternal = ZoneRules.readExternal(objectInput);
        } else if (readByte == 2) {
            long readEpochSec = readEpochSec(objectInput);
            ZoneOffset readOffset = readOffset(objectInput);
            ZoneOffset readOffset2 = readOffset(objectInput);
            if (readOffset.equals(readOffset2)) {
                throw new IllegalArgumentException("Offsets must not be equal");
            }
            readExternal = new ZoneOffsetTransition(readEpochSec, readOffset, readOffset2);
        } else if (readByte == 3) {
            readExternal = ZoneOffsetTransitionRule.readExternal(objectInput);
        } else {
            if (readByte != 100) {
                throw new StreamCorruptedException("Unknown serialized type");
            }
            readExternal = new ZoneRules(TimeZone.getTimeZone(objectInput.readUTF()));
        }
        this.object = readExternal;
    }

    private Object readResolve() {
        return this.object;
    }

    static void writeOffset(ZoneOffset zoneOffset, ObjectOutput objectOutput) {
        int totalSeconds = zoneOffset.getTotalSeconds();
        int i = totalSeconds % TypedValues.Custom.TYPE_INT == 0 ? totalSeconds / TypedValues.Custom.TYPE_INT : 127;
        objectOutput.writeByte(i);
        if (i == 127) {
            objectOutput.writeInt(totalSeconds);
        }
    }

    static ZoneOffset readOffset(ObjectInput objectInput) {
        byte readByte = objectInput.readByte();
        return readByte == Byte.MAX_VALUE ? ZoneOffset.ofTotalSeconds(objectInput.readInt()) : ZoneOffset.ofTotalSeconds(readByte * 900);
    }

    static void writeEpochSec(long j, ObjectOutput objectOutput) {
        if (j >= -4575744000L && j < 10413792000L && j % 900 == 0) {
            int i = (int) ((j + 4575744000L) / 900);
            objectOutput.writeByte((i >>> 16) & 255);
            objectOutput.writeByte((i >>> 8) & 255);
            objectOutput.writeByte(i & 255);
            return;
        }
        objectOutput.writeByte(255);
        objectOutput.writeLong(j);
    }

    static long readEpochSec(ObjectInput objectInput) {
        if ((objectInput.readByte() & 255) == 255) {
            return objectInput.readLong();
        }
        return ((((r0 << 16) + ((objectInput.readByte() & 255) << 8)) + (objectInput.readByte() & 255)) * 900) - 4575744000L;
    }
}
