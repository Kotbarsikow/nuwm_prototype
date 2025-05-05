package j$.util.stream;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes4.dex */
public final class StreamShape {
    private static final /* synthetic */ StreamShape[] $VALUES;
    public static final StreamShape DOUBLE_VALUE;
    public static final StreamShape INT_VALUE;
    public static final StreamShape LONG_VALUE;
    public static final StreamShape REFERENCE;

    public static StreamShape valueOf(String str) {
        return (StreamShape) Enum.valueOf(StreamShape.class, str);
    }

    public static StreamShape[] values() {
        return (StreamShape[]) $VALUES.clone();
    }

    static {
        StreamShape streamShape = new StreamShape("REFERENCE", 0);
        REFERENCE = streamShape;
        StreamShape streamShape2 = new StreamShape("INT_VALUE", 1);
        INT_VALUE = streamShape2;
        StreamShape streamShape3 = new StreamShape("LONG_VALUE", 2);
        LONG_VALUE = streamShape3;
        StreamShape streamShape4 = new StreamShape("DOUBLE_VALUE", 3);
        DOUBLE_VALUE = streamShape4;
        $VALUES = new StreamShape[]{streamShape, streamShape2, streamShape3, streamShape4};
    }
}
