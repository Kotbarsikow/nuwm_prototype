package j$.util.stream;

/* loaded from: classes4.dex */
abstract /* synthetic */ class Nodes$1 {
    static final /* synthetic */ int[] $SwitchMap$java$util$stream$StreamShape;

    static {
        int[] iArr = new int[StreamShape.values().length];
        $SwitchMap$java$util$stream$StreamShape = iArr;
        try {
            iArr[StreamShape.REFERENCE.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            $SwitchMap$java$util$stream$StreamShape[StreamShape.INT_VALUE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            $SwitchMap$java$util$stream$StreamShape[StreamShape.LONG_VALUE.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            $SwitchMap$java$util$stream$StreamShape[StreamShape.DOUBLE_VALUE.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
    }
}
