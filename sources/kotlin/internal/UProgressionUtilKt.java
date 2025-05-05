package kotlin.internal;

import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.UInt;
import kotlin.ULong;

/* compiled from: UProgressionUtil.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a*\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0007H\u0002ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a*\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0001ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0006\u001a*\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0010H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"differenceModulo", "Lkotlin/UInt;", "a", "b", "c", "differenceModulo-WZ9TVnA", "(III)I", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "getProgressionLastElement", "start", "end", "step", "", "getProgressionLastElement-Nkh28Cs", "", "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class UProgressionUtilKt {
    /* renamed from: differenceModulo-WZ9TVnA */
    private static final int m1460differenceModuloWZ9TVnA(int i, int i2, int i3) {
        int compare;
        int m$1 = UByte$$ExternalSyntheticBackport0.m$1(i, i3);
        int m$12 = UByte$$ExternalSyntheticBackport0.m$1(i2, i3);
        compare = Integer.compare(m$1 ^ Integer.MIN_VALUE, m$12 ^ Integer.MIN_VALUE);
        int m350constructorimpl = UInt.m350constructorimpl(m$1 - m$12);
        return compare >= 0 ? m350constructorimpl : UInt.m350constructorimpl(m350constructorimpl + i3);
    }

    /* renamed from: differenceModulo-sambcqE */
    private static final long m1461differenceModulosambcqE(long j, long j2, long j3) {
        int compare;
        long m323m = UByte$$ExternalSyntheticBackport0.m323m(j, j3);
        long m323m2 = UByte$$ExternalSyntheticBackport0.m323m(j2, j3);
        compare = Long.compare(m323m ^ Long.MIN_VALUE, m323m2 ^ Long.MIN_VALUE);
        long m429constructorimpl = ULong.m429constructorimpl(m323m - m323m2);
        return compare >= 0 ? m429constructorimpl : ULong.m429constructorimpl(m429constructorimpl + j3);
    }

    /* renamed from: getProgressionLastElement-Nkh28Cs */
    public static final int m1463getProgressionLastElementNkh28Cs(int i, int i2, int i3) {
        int compare;
        int compare2;
        if (i3 > 0) {
            compare2 = Integer.compare(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE);
            return compare2 >= 0 ? i2 : UInt.m350constructorimpl(i2 - m1460differenceModuloWZ9TVnA(i2, i, UInt.m350constructorimpl(i3)));
        }
        if (i3 < 0) {
            compare = Integer.compare(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE);
            return compare <= 0 ? i2 : UInt.m350constructorimpl(i2 + m1460differenceModuloWZ9TVnA(i, i2, UInt.m350constructorimpl(-i3)));
        }
        throw new IllegalArgumentException("Step is zero.");
    }

    /* renamed from: getProgressionLastElement-7ftBX0g */
    public static final long m1462getProgressionLastElement7ftBX0g(long j, long j2, long j3) {
        int compare;
        int compare2;
        if (j3 > 0) {
            compare2 = Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE);
            return compare2 >= 0 ? j2 : ULong.m429constructorimpl(j2 - m1461differenceModulosambcqE(j2, j, ULong.m429constructorimpl(j3)));
        }
        if (j3 < 0) {
            compare = Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE);
            return compare <= 0 ? j2 : ULong.m429constructorimpl(j2 + m1461differenceModulosambcqE(j, j2, ULong.m429constructorimpl(-j3)));
        }
        throw new IllegalArgumentException("Step is zero.");
    }
}
