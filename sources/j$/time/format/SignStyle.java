package j$.time.format;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes4.dex */
public final class SignStyle {
    private static final /* synthetic */ SignStyle[] $VALUES;
    public static final SignStyle ALWAYS;
    public static final SignStyle EXCEEDS_PAD;
    public static final SignStyle NEVER;
    public static final SignStyle NORMAL;
    public static final SignStyle NOT_NEGATIVE;

    public static SignStyle valueOf(String str) {
        return (SignStyle) Enum.valueOf(SignStyle.class, str);
    }

    public static SignStyle[] values() {
        return (SignStyle[]) $VALUES.clone();
    }

    static {
        SignStyle signStyle = new SignStyle("NORMAL", 0);
        NORMAL = signStyle;
        SignStyle signStyle2 = new SignStyle("ALWAYS", 1);
        ALWAYS = signStyle2;
        SignStyle signStyle3 = new SignStyle("NEVER", 2);
        NEVER = signStyle3;
        SignStyle signStyle4 = new SignStyle("NOT_NEGATIVE", 3);
        NOT_NEGATIVE = signStyle4;
        SignStyle signStyle5 = new SignStyle("EXCEEDS_PAD", 4);
        EXCEEDS_PAD = signStyle5;
        $VALUES = new SignStyle[]{signStyle, signStyle2, signStyle3, signStyle4, signStyle5};
    }
}
