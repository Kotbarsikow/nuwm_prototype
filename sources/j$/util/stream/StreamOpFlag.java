package j$.util.stream;

import j$.util.Map;
import j$.util.Spliterator;
import java.util.EnumMap;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'DISTINCT' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes4.dex */
final class StreamOpFlag {
    private static final /* synthetic */ StreamOpFlag[] $VALUES;
    public static final StreamOpFlag DISTINCT;
    private static final int FLAG_MASK;
    private static final int FLAG_MASK_IS;
    private static final int FLAG_MASK_NOT;
    static final int INITIAL_OPS_VALUE;
    static final int IS_DISTINCT;
    static final int IS_ORDERED;
    static final int IS_SHORT_CIRCUIT;
    static final int IS_SIZED;
    static final int IS_SORTED;
    static final int NOT_DISTINCT;
    static final int NOT_ORDERED;
    static final int NOT_SIZED;
    static final int NOT_SORTED;
    static final int OP_MASK;
    public static final StreamOpFlag ORDERED;
    public static final StreamOpFlag SHORT_CIRCUIT;
    public static final StreamOpFlag SIZED;
    public static final StreamOpFlag SORTED;
    static final int SPLITERATOR_CHARACTERISTICS_MASK;
    static final int STREAM_MASK;
    private final int bitPosition;
    private final int clear;
    private final EnumMap maskTable;
    private final int preserve;
    private final int set;

    public static StreamOpFlag valueOf(String str) {
        return (StreamOpFlag) Enum.valueOf(StreamOpFlag.class, str);
    }

    public static StreamOpFlag[] values() {
        return (StreamOpFlag[]) $VALUES.clone();
    }

    static {
        Type type = Type.SPLITERATOR;
        MaskBuilder maskBuilder = set(type);
        Type type2 = Type.STREAM;
        maskBuilder.set(type2);
        Type type3 = Type.OP;
        maskBuilder.map.put((EnumMap) type3, (Type) 3);
        StreamOpFlag streamOpFlag = new StreamOpFlag("DISTINCT", 0, 0, maskBuilder);
        DISTINCT = streamOpFlag;
        MaskBuilder maskBuilder2 = set(type);
        maskBuilder2.set(type2);
        maskBuilder2.map.put((EnumMap) type3, (Type) 3);
        StreamOpFlag streamOpFlag2 = new StreamOpFlag("SORTED", 1, 1, maskBuilder2);
        SORTED = streamOpFlag2;
        MaskBuilder maskBuilder3 = set(type);
        maskBuilder3.set(type2);
        EnumMap enumMap = maskBuilder3.map;
        enumMap.put((EnumMap) type3, (Type) 3);
        Type type4 = Type.TERMINAL_OP;
        enumMap.put((EnumMap) type4, (Type) 2);
        Type type5 = Type.UPSTREAM_TERMINAL_OP;
        enumMap.put((EnumMap) type5, (Type) 2);
        StreamOpFlag streamOpFlag3 = new StreamOpFlag("ORDERED", 2, 2, maskBuilder3);
        ORDERED = streamOpFlag3;
        MaskBuilder maskBuilder4 = set(type);
        maskBuilder4.set(type2);
        maskBuilder4.map.put((EnumMap) type3, (Type) 2);
        StreamOpFlag streamOpFlag4 = new StreamOpFlag("SIZED", 3, 3, maskBuilder4);
        SIZED = streamOpFlag4;
        MaskBuilder maskBuilder5 = set(type3);
        maskBuilder5.set(type4);
        StreamOpFlag streamOpFlag5 = new StreamOpFlag("SHORT_CIRCUIT", 4, 12, maskBuilder5);
        SHORT_CIRCUIT = streamOpFlag5;
        $VALUES = new StreamOpFlag[]{streamOpFlag, streamOpFlag2, streamOpFlag3, streamOpFlag4, streamOpFlag5};
        SPLITERATOR_CHARACTERISTICS_MASK = createMask(type);
        STREAM_MASK = createMask(type2);
        OP_MASK = createMask(type3);
        createMask(type4);
        createMask(type5);
        int i = 0;
        for (StreamOpFlag streamOpFlag6 : values()) {
            i |= streamOpFlag6.preserve;
        }
        FLAG_MASK = i;
        int i2 = STREAM_MASK;
        FLAG_MASK_IS = i2;
        int i3 = i2 << 1;
        FLAG_MASK_NOT = i3;
        INITIAL_OPS_VALUE = i2 | i3;
        StreamOpFlag streamOpFlag7 = DISTINCT;
        IS_DISTINCT = streamOpFlag7.set;
        NOT_DISTINCT = streamOpFlag7.clear;
        StreamOpFlag streamOpFlag8 = SORTED;
        IS_SORTED = streamOpFlag8.set;
        NOT_SORTED = streamOpFlag8.clear;
        StreamOpFlag streamOpFlag9 = ORDERED;
        IS_ORDERED = streamOpFlag9.set;
        NOT_ORDERED = streamOpFlag9.clear;
        StreamOpFlag streamOpFlag10 = SIZED;
        IS_SIZED = streamOpFlag10.set;
        NOT_SIZED = streamOpFlag10.clear;
        IS_SHORT_CIRCUIT = SHORT_CIRCUIT.set;
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    final class Type {
        private static final /* synthetic */ Type[] $VALUES;
        public static final Type OP;
        public static final Type SPLITERATOR;
        public static final Type STREAM;
        public static final Type TERMINAL_OP;
        public static final Type UPSTREAM_TERMINAL_OP;

        public static Type valueOf(String str) {
            return (Type) Enum.valueOf(Type.class, str);
        }

        public static Type[] values() {
            return (Type[]) $VALUES.clone();
        }

        static {
            Type type = new Type("SPLITERATOR", 0);
            SPLITERATOR = type;
            Type type2 = new Type("STREAM", 1);
            STREAM = type2;
            Type type3 = new Type("OP", 2);
            OP = type3;
            Type type4 = new Type("TERMINAL_OP", 3);
            TERMINAL_OP = type4;
            Type type5 = new Type("UPSTREAM_TERMINAL_OP", 4);
            UPSTREAM_TERMINAL_OP = type5;
            $VALUES = new Type[]{type, type2, type3, type4, type5};
        }
    }

    private static MaskBuilder set(Type type) {
        MaskBuilder maskBuilder = new MaskBuilder(new EnumMap(Type.class));
        maskBuilder.set(type);
        return maskBuilder;
    }

    final class MaskBuilder {
        final EnumMap map;

        MaskBuilder(EnumMap enumMap) {
            this.map = enumMap;
        }

        final void set(Type type) {
            this.map.put((EnumMap) type, (Type) 1);
        }
    }

    private StreamOpFlag(String str, int i, int i2, MaskBuilder maskBuilder) {
        Type[] values = Type.values();
        int length = values.length;
        int i3 = 0;
        while (true) {
            EnumMap enumMap = maskBuilder.map;
            if (i3 < length) {
                Map.EL.putIfAbsent(enumMap, values[i3], 0);
                i3++;
            } else {
                this.maskTable = enumMap;
                int i4 = i2 * 2;
                this.bitPosition = i4;
                this.set = 1 << i4;
                this.clear = 2 << i4;
                this.preserve = 3 << i4;
                return;
            }
        }
    }

    final boolean isKnown(int i) {
        return (i & this.preserve) == this.set;
    }

    final boolean isPreserved(int i) {
        int i2 = this.preserve;
        return (i & i2) == i2;
    }

    private static int createMask(Type type) {
        int i = 0;
        for (StreamOpFlag streamOpFlag : values()) {
            i |= ((Integer) streamOpFlag.maskTable.get(type)).intValue() << streamOpFlag.bitPosition;
        }
        return i;
    }

    static int combineOpFlags(int i, int i2) {
        int i3;
        if (i == 0) {
            i3 = FLAG_MASK;
        } else {
            i3 = ~(((FLAG_MASK_IS & i) << 1) | i | ((FLAG_MASK_NOT & i) >> 1));
        }
        return i | (i2 & i3);
    }

    static int toStreamFlags(int i) {
        return i & ((~i) >> 1) & FLAG_MASK_IS;
    }

    static int fromCharacteristics(Spliterator spliterator) {
        int characteristics = spliterator.characteristics();
        int i = characteristics & 4;
        int i2 = SPLITERATOR_CHARACTERISTICS_MASK;
        return (i == 0 || spliterator.getComparator() == null) ? characteristics & i2 : characteristics & i2 & (-5);
    }
}
