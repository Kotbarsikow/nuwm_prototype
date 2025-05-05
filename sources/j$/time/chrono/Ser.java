package j$.time.chrono;

import j$.time.LocalDate;
import j$.time.LocalTime;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.temporal.ChronoField;
import j$.time.temporal.Temporal;
import java.io.Externalizable;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.StreamCorruptedException;

/* loaded from: classes4.dex */
final class Ser implements Externalizable {
    private static final long serialVersionUID = -6103370247208168577L;
    private Object object;
    private byte type;

    public Ser() {
    }

    Ser(byte b, Object obj) {
        this.type = b;
        this.object = obj;
    }

    @Override // java.io.Externalizable
    public final void writeExternal(ObjectOutput objectOutput) {
        byte b = this.type;
        Object obj = this.object;
        objectOutput.writeByte(b);
        switch (b) {
            case 1:
                objectOutput.writeUTF(((AbstractChronology) obj).getId());
                return;
            case 2:
                ((ChronoLocalDateTimeImpl) obj).writeExternal(objectOutput);
                return;
            case 3:
                ((ChronoZonedDateTimeImpl) obj).writeExternal(objectOutput);
                return;
            case 4:
                JapaneseDate japaneseDate = (JapaneseDate) obj;
                japaneseDate.getClass();
                objectOutput.writeInt(Temporal.CC.$default$get(japaneseDate, ChronoField.YEAR));
                objectOutput.writeByte(Temporal.CC.$default$get(japaneseDate, ChronoField.MONTH_OF_YEAR));
                objectOutput.writeByte(Temporal.CC.$default$get(japaneseDate, ChronoField.DAY_OF_MONTH));
                return;
            case 5:
                ((JapaneseEra) obj).writeExternal(objectOutput);
                return;
            case 6:
                ((HijrahDate) obj).writeExternal(objectOutput);
                return;
            case 7:
                MinguoDate minguoDate = (MinguoDate) obj;
                minguoDate.getClass();
                objectOutput.writeInt(Temporal.CC.$default$get(minguoDate, ChronoField.YEAR));
                objectOutput.writeByte(Temporal.CC.$default$get(minguoDate, ChronoField.MONTH_OF_YEAR));
                objectOutput.writeByte(Temporal.CC.$default$get(minguoDate, ChronoField.DAY_OF_MONTH));
                return;
            case 8:
                ThaiBuddhistDate thaiBuddhistDate = (ThaiBuddhistDate) obj;
                thaiBuddhistDate.getClass();
                objectOutput.writeInt(Temporal.CC.$default$get(thaiBuddhistDate, ChronoField.YEAR));
                objectOutput.writeByte(Temporal.CC.$default$get(thaiBuddhistDate, ChronoField.MONTH_OF_YEAR));
                objectOutput.writeByte(Temporal.CC.$default$get(thaiBuddhistDate, ChronoField.DAY_OF_MONTH));
                return;
            case 9:
                ((ChronoPeriodImpl) obj).writeExternal(objectOutput);
                return;
            default:
                throw new InvalidClassException("Unknown serialized type");
        }
    }

    @Override // java.io.Externalizable
    public final void readExternal(ObjectInput objectInput) {
        Object of;
        byte readByte = objectInput.readByte();
        this.type = readByte;
        switch (readByte) {
            case 1:
                int i = AbstractChronology.$r8$clinit;
                of = AbstractChronology.of(objectInput.readUTF());
                break;
            case 2:
                of = ((ChronoLocalDate) objectInput.readObject()).atTime((LocalTime) objectInput.readObject());
                break;
            case 3:
                of = ((ChronoLocalDateTime) objectInput.readObject()).atZone((ZoneOffset) objectInput.readObject()).withZoneSameLocal((ZoneId) objectInput.readObject());
                break;
            case 4:
                LocalDate localDate = JapaneseDate.MEIJI_6_ISODATE;
                int readInt = objectInput.readInt();
                byte readByte2 = objectInput.readByte();
                byte readByte3 = objectInput.readByte();
                JapaneseChronology.INSTANCE.getClass();
                of = new JapaneseDate(LocalDate.of(readInt, readByte2, readByte3));
                break;
            case 5:
                JapaneseEra japaneseEra = JapaneseEra.MEIJI;
                of = JapaneseEra.of(objectInput.readByte());
                break;
            case 6:
                HijrahChronology hijrahChronology = (HijrahChronology) objectInput.readObject();
                int readInt2 = objectInput.readInt();
                byte readByte4 = objectInput.readByte();
                byte readByte5 = objectInput.readByte();
                hijrahChronology.getClass();
                of = HijrahDate.of(hijrahChronology, readInt2, readByte4, readByte5);
                break;
            case 7:
                int readInt3 = objectInput.readInt();
                byte readByte6 = objectInput.readByte();
                byte readByte7 = objectInput.readByte();
                MinguoChronology.INSTANCE.getClass();
                of = new MinguoDate(LocalDate.of(readInt3 + 1911, readByte6, readByte7));
                break;
            case 8:
                int readInt4 = objectInput.readInt();
                byte readByte8 = objectInput.readByte();
                byte readByte9 = objectInput.readByte();
                ThaiBuddhistChronology.INSTANCE.getClass();
                of = new ThaiBuddhistDate(LocalDate.of(readInt4 - 543, readByte8, readByte9));
                break;
            case 9:
                int i2 = ChronoPeriodImpl.$r8$clinit;
                of = new ChronoPeriodImpl(AbstractChronology.of(objectInput.readUTF()), objectInput.readInt(), objectInput.readInt(), objectInput.readInt());
                break;
            default:
                throw new StreamCorruptedException("Unknown serialized type");
        }
        this.object = of;
    }

    private Object readResolve() {
        return this.object;
    }
}
