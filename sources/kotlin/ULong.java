package kotlin;

import com.google.firebase.messaging.Constants;
import kotlin.jvm.JvmInline;
import kotlin.ranges.ULongRange;
import kotlin.ranges.URangesKt;

/* compiled from: ULong.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 ~2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001~B\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\u0097\nø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u0000H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b \u0010\u000bJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b!\u0010\"J\u001a\u0010#\u001a\u00020$2\b\u0010\t\u001a\u0004\u0018\u00010%HÖ\u0003¢\u0006\u0004\b&\u0010'J\u001b\u0010(\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\bø\u0001\u0000¢\u0006\u0004\b)\u0010\u001dJ\u001b\u0010(\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b*\u0010\u001fJ\u001b\u0010(\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b+\u0010\u000bJ\u001b\u0010(\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\bø\u0001\u0000¢\u0006\u0004\b,\u0010\"J\u0010\u0010-\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b.\u0010/J\u0016\u00100\u001a\u00020\u0000H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b1\u0010\u0005J\u0016\u00102\u001a\u00020\u0000H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b3\u0010\u0005J\u001b\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b5\u0010\u001dJ\u001b\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b6\u0010\u001fJ\u001b\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b7\u0010\u000bJ\u001b\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b8\u0010\"J\u001b\u00109\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u000eH\u0087\bø\u0001\u0000¢\u0006\u0004\b:\u0010;J\u001b\u00109\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b<\u0010\u0013J\u001b\u00109\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b=\u0010\u000bJ\u001b\u00109\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\u0016H\u0087\bø\u0001\u0000¢\u0006\u0004\b>\u0010?J\u001b\u0010@\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\bA\u0010\u000bJ\u001b\u0010B\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\bC\u0010\u001dJ\u001b\u0010B\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bD\u0010\u001fJ\u001b\u0010B\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bE\u0010\u000bJ\u001b\u0010B\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bF\u0010\"J\u001b\u0010G\u001a\u00020H2\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bI\u0010JJ\u001b\u0010K\u001a\u00020H2\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bL\u0010JJ\u001b\u0010M\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\bN\u0010\u001dJ\u001b\u0010M\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bO\u0010\u001fJ\u001b\u0010M\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bP\u0010\u000bJ\u001b\u0010M\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bQ\u0010\"J\u001e\u0010R\u001a\u00020\u00002\u0006\u0010S\u001a\u00020\rH\u0087\fø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bT\u0010\u001fJ\u001e\u0010U\u001a\u00020\u00002\u0006\u0010S\u001a\u00020\rH\u0087\fø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bV\u0010\u001fJ\u001b\u0010W\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\bX\u0010\u001dJ\u001b\u0010W\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bY\u0010\u001fJ\u001b\u0010W\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bZ\u0010\u000bJ\u001b\u0010W\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b[\u0010\"J\u0010\u0010\\\u001a\u00020]H\u0087\b¢\u0006\u0004\b^\u0010_J\u0010\u0010`\u001a\u00020aH\u0087\b¢\u0006\u0004\bb\u0010cJ\u0010\u0010d\u001a\u00020eH\u0087\b¢\u0006\u0004\bf\u0010gJ\u0010\u0010h\u001a\u00020\rH\u0087\b¢\u0006\u0004\bi\u0010/J\u0010\u0010j\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bk\u0010\u0005J\u0010\u0010l\u001a\u00020mH\u0087\b¢\u0006\u0004\bn\u0010oJ\u000f\u0010p\u001a\u00020qH\u0016¢\u0006\u0004\br\u0010sJ\u0016\u0010t\u001a\u00020\u000eH\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bu\u0010_J\u0016\u0010v\u001a\u00020\u0011H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bw\u0010/J\u0016\u0010x\u001a\u00020\u0000H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\by\u0010\u0005J\u0016\u0010z\u001a\u00020\u0016H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b{\u0010oJ\u001b\u0010|\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b}\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003ø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u007f"}, d2 = {"Lkotlin/ULong;", "", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "", "constructor-impl", "(J)J", "getData$annotations", "()V", "and", "other", "and-VKZWuLQ", "(JJ)J", "compareTo", "", "Lkotlin/UByte;", "compareTo-7apg3OU", "(JB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(JI)I", "compareTo-VKZWuLQ", "(JJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(JS)I", "dec", "dec-s-VKNKU", "div", "div-7apg3OU", "(JB)J", "div-WZ4Q5Ns", "(JI)J", "div-VKZWuLQ", "div-xj2QHRw", "(JS)J", "equals", "", "", "equals-impl", "(JLjava/lang/Object;)Z", "floorDiv", "floorDiv-7apg3OU", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode", "hashCode-impl", "(J)I", "inc", "inc-s-VKNKU", "inv", "inv-s-VKNKU", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod", "mod-7apg3OU", "(JB)B", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "(JS)S", "or", "or-VKZWuLQ", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/ULongRange;", "rangeTo-VKZWuLQ", "(JJ)Lkotlin/ranges/ULongRange;", "rangeUntil", "rangeUntil-VKZWuLQ", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "shl", "bitCount", "shl-s-VKNKU", "shr", "shr-s-VKNKU", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(J)B", "toDouble", "", "toDouble-impl", "(J)D", "toFloat", "", "toFloat-impl", "(J)F", "toInt", "toInt-impl", "toLong", "toLong-impl", "toShort", "", "toShort-impl", "(J)S", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-VKZWuLQ", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
@JvmInline
/* loaded from: classes2.dex */
public final class ULong implements Comparable<ULong> {
    public static final long MAX_VALUE = -1;
    public static final long MIN_VALUE = 0;
    public static final int SIZE_BITS = 64;
    public static final int SIZE_BYTES = 8;
    private final long data;

    /* renamed from: box-impl */
    public static final /* synthetic */ ULong m423boximpl(long j) {
        return new ULong(j);
    }

    /* renamed from: constructor-impl */
    public static long m429constructorimpl(long j) {
        return j;
    }

    /* renamed from: equals-impl */
    public static boolean m435equalsimpl(long j, Object obj) {
        return (obj instanceof ULong) && j == ((ULong) obj).getData();
    }

    /* renamed from: equals-impl0 */
    public static final boolean m436equalsimpl0(long j, long j2) {
        return j == j2;
    }

    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl */
    public static int m441hashCodeimpl(long j) {
        return UByte$$ExternalSyntheticBackport0.m(j);
    }

    /* renamed from: toByte-impl */
    private static final byte m469toByteimpl(long j) {
        return (byte) j;
    }

    /* renamed from: toInt-impl */
    private static final int m472toIntimpl(long j) {
        return (int) j;
    }

    /* renamed from: toLong-impl */
    private static final long m473toLongimpl(long j) {
        return j;
    }

    /* renamed from: toShort-impl */
    private static final short m474toShortimpl(long j) {
        return (short) j;
    }

    /* renamed from: toULong-s-VKNKU */
    private static final long m478toULongsVKNKU(long j) {
        return j;
    }

    public boolean equals(Object obj) {
        return m435equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m441hashCodeimpl(this.data);
    }

    /* renamed from: unbox-impl, reason: from getter */
    public final /* synthetic */ long getData() {
        return this.data;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(ULong uLong) {
        return UnsignedKt.ulongCompare(getData(), uLong.getData());
    }

    private /* synthetic */ ULong(long j) {
        this.data = j;
    }

    /* renamed from: compareTo-7apg3OU */
    private static final int m424compareTo7apg3OU(long j, byte b) {
        int compare;
        compare = Long.compare(j ^ Long.MIN_VALUE, m429constructorimpl(b & 255) ^ Long.MIN_VALUE);
        return compare;
    }

    /* renamed from: compareTo-xj2QHRw */
    private static final int m428compareToxj2QHRw(long j, short s) {
        int compare;
        compare = Long.compare(j ^ Long.MIN_VALUE, m429constructorimpl(s & 65535) ^ Long.MIN_VALUE);
        return compare;
    }

    /* renamed from: compareTo-WZ4Q5Ns */
    private static final int m427compareToWZ4Q5Ns(long j, int i) {
        int compare;
        compare = Long.compare(j ^ Long.MIN_VALUE, m429constructorimpl(i & 4294967295L) ^ Long.MIN_VALUE);
        return compare;
    }

    /* renamed from: compareTo-VKZWuLQ */
    private int m425compareToVKZWuLQ(long j) {
        return UnsignedKt.ulongCompare(getData(), j);
    }

    /* renamed from: compareTo-VKZWuLQ */
    private static int m426compareToVKZWuLQ(long j, long j2) {
        return UnsignedKt.ulongCompare(j, j2);
    }

    /* renamed from: plus-7apg3OU */
    private static final long m453plus7apg3OU(long j, byte b) {
        return m429constructorimpl(j + m429constructorimpl(b & 255));
    }

    /* renamed from: plus-xj2QHRw */
    private static final long m456plusxj2QHRw(long j, short s) {
        return m429constructorimpl(j + m429constructorimpl(s & 65535));
    }

    /* renamed from: plus-WZ4Q5Ns */
    private static final long m455plusWZ4Q5Ns(long j, int i) {
        return m429constructorimpl(j + m429constructorimpl(i & 4294967295L));
    }

    /* renamed from: plus-VKZWuLQ */
    private static final long m454plusVKZWuLQ(long j, long j2) {
        return m429constructorimpl(j + j2);
    }

    /* renamed from: minus-7apg3OU */
    private static final long m444minus7apg3OU(long j, byte b) {
        return m429constructorimpl(j - m429constructorimpl(b & 255));
    }

    /* renamed from: minus-xj2QHRw */
    private static final long m447minusxj2QHRw(long j, short s) {
        return m429constructorimpl(j - m429constructorimpl(s & 65535));
    }

    /* renamed from: minus-WZ4Q5Ns */
    private static final long m446minusWZ4Q5Ns(long j, int i) {
        return m429constructorimpl(j - m429constructorimpl(i & 4294967295L));
    }

    /* renamed from: minus-VKZWuLQ */
    private static final long m445minusVKZWuLQ(long j, long j2) {
        return m429constructorimpl(j - j2);
    }

    /* renamed from: times-7apg3OU */
    private static final long m465times7apg3OU(long j, byte b) {
        return m429constructorimpl(j * m429constructorimpl(b & 255));
    }

    /* renamed from: times-xj2QHRw */
    private static final long m468timesxj2QHRw(long j, short s) {
        return m429constructorimpl(j * m429constructorimpl(s & 65535));
    }

    /* renamed from: times-WZ4Q5Ns */
    private static final long m467timesWZ4Q5Ns(long j, int i) {
        return m429constructorimpl(j * m429constructorimpl(i & 4294967295L));
    }

    /* renamed from: times-VKZWuLQ */
    private static final long m466timesVKZWuLQ(long j, long j2) {
        return m429constructorimpl(j * j2);
    }

    /* renamed from: div-7apg3OU */
    private static final long m431div7apg3OU(long j, byte b) {
        return UByte$$ExternalSyntheticBackport0.m$1(j, m429constructorimpl(b & 255));
    }

    /* renamed from: div-xj2QHRw */
    private static final long m434divxj2QHRw(long j, short s) {
        return UByte$$ExternalSyntheticBackport0.m$1(j, m429constructorimpl(s & 65535));
    }

    /* renamed from: div-WZ4Q5Ns */
    private static final long m433divWZ4Q5Ns(long j, int i) {
        return UByte$$ExternalSyntheticBackport0.m$1(j, m429constructorimpl(i & 4294967295L));
    }

    /* renamed from: div-VKZWuLQ */
    private static final long m432divVKZWuLQ(long j, long j2) {
        return UnsignedKt.m608ulongDivideeb3DHEI(j, j2);
    }

    /* renamed from: rem-7apg3OU */
    private static final long m459rem7apg3OU(long j, byte b) {
        return UByte$$ExternalSyntheticBackport0.m323m(j, m429constructorimpl(b & 255));
    }

    /* renamed from: rem-xj2QHRw */
    private static final long m462remxj2QHRw(long j, short s) {
        return UByte$$ExternalSyntheticBackport0.m323m(j, m429constructorimpl(s & 65535));
    }

    /* renamed from: rem-WZ4Q5Ns */
    private static final long m461remWZ4Q5Ns(long j, int i) {
        return UByte$$ExternalSyntheticBackport0.m323m(j, m429constructorimpl(i & 4294967295L));
    }

    /* renamed from: rem-VKZWuLQ */
    private static final long m460remVKZWuLQ(long j, long j2) {
        return UnsignedKt.m609ulongRemaindereb3DHEI(j, j2);
    }

    /* renamed from: floorDiv-7apg3OU */
    private static final long m437floorDiv7apg3OU(long j, byte b) {
        return UByte$$ExternalSyntheticBackport0.m$1(j, m429constructorimpl(b & 255));
    }

    /* renamed from: floorDiv-xj2QHRw */
    private static final long m440floorDivxj2QHRw(long j, short s) {
        return UByte$$ExternalSyntheticBackport0.m$1(j, m429constructorimpl(s & 65535));
    }

    /* renamed from: floorDiv-WZ4Q5Ns */
    private static final long m439floorDivWZ4Q5Ns(long j, int i) {
        return UByte$$ExternalSyntheticBackport0.m$1(j, m429constructorimpl(i & 4294967295L));
    }

    /* renamed from: floorDiv-VKZWuLQ */
    private static final long m438floorDivVKZWuLQ(long j, long j2) {
        return UByte$$ExternalSyntheticBackport0.m$1(j, j2);
    }

    /* renamed from: mod-7apg3OU */
    private static final byte m448mod7apg3OU(long j, byte b) {
        return UByte.m272constructorimpl((byte) UByte$$ExternalSyntheticBackport0.m323m(j, m429constructorimpl(b & 255)));
    }

    /* renamed from: mod-xj2QHRw */
    private static final short m451modxj2QHRw(long j, short s) {
        return UShort.m536constructorimpl((short) UByte$$ExternalSyntheticBackport0.m323m(j, m429constructorimpl(s & 65535)));
    }

    /* renamed from: mod-WZ4Q5Ns */
    private static final int m450modWZ4Q5Ns(long j, int i) {
        return UInt.m350constructorimpl((int) UByte$$ExternalSyntheticBackport0.m323m(j, m429constructorimpl(i & 4294967295L)));
    }

    /* renamed from: mod-VKZWuLQ */
    private static final long m449modVKZWuLQ(long j, long j2) {
        return UByte$$ExternalSyntheticBackport0.m323m(j, j2);
    }

    /* renamed from: inc-s-VKNKU */
    private static final long m442incsVKNKU(long j) {
        return m429constructorimpl(j + 1);
    }

    /* renamed from: dec-s-VKNKU */
    private static final long m430decsVKNKU(long j) {
        return m429constructorimpl(j - 1);
    }

    /* renamed from: rangeTo-VKZWuLQ */
    private static final ULongRange m457rangeToVKZWuLQ(long j, long j2) {
        return new ULongRange(j, j2, null);
    }

    /* renamed from: rangeUntil-VKZWuLQ */
    private static final ULongRange m458rangeUntilVKZWuLQ(long j, long j2) {
        return URangesKt.m1524untileb3DHEI(j, j2);
    }

    /* renamed from: shl-s-VKNKU */
    private static final long m463shlsVKNKU(long j, int i) {
        return m429constructorimpl(j << i);
    }

    /* renamed from: shr-s-VKNKU */
    private static final long m464shrsVKNKU(long j, int i) {
        return m429constructorimpl(j >>> i);
    }

    /* renamed from: and-VKZWuLQ */
    private static final long m422andVKZWuLQ(long j, long j2) {
        return m429constructorimpl(j & j2);
    }

    /* renamed from: or-VKZWuLQ */
    private static final long m452orVKZWuLQ(long j, long j2) {
        return m429constructorimpl(j | j2);
    }

    /* renamed from: xor-VKZWuLQ */
    private static final long m480xorVKZWuLQ(long j, long j2) {
        return m429constructorimpl(j ^ j2);
    }

    /* renamed from: inv-s-VKNKU */
    private static final long m443invsVKNKU(long j) {
        return m429constructorimpl(~j);
    }

    /* renamed from: toUByte-w2LRezQ */
    private static final byte m476toUBytew2LRezQ(long j) {
        return UByte.m272constructorimpl((byte) j);
    }

    /* renamed from: toUShort-Mh2AYeg */
    private static final short m479toUShortMh2AYeg(long j) {
        return UShort.m536constructorimpl((short) j);
    }

    /* renamed from: toUInt-pVg5ArA */
    private static final int m477toUIntpVg5ArA(long j) {
        return UInt.m350constructorimpl((int) j);
    }

    /* renamed from: toFloat-impl */
    private static final float m471toFloatimpl(long j) {
        return (float) UnsignedKt.ulongToDouble(j);
    }

    /* renamed from: toDouble-impl */
    private static final double m470toDoubleimpl(long j) {
        return UnsignedKt.ulongToDouble(j);
    }

    /* renamed from: toString-impl */
    public static String m475toStringimpl(long j) {
        return UnsignedKt.ulongToString(j);
    }

    public String toString() {
        return m475toStringimpl(this.data);
    }
}
