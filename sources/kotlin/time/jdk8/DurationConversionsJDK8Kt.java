package kotlin.time.jdk8;

import j$.time.Duration;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;

/* compiled from: DurationConversions.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0017\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u001a\u0010\u0006\u001a\u00020\u0000*\u00020\u0001H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u0007"}, d2 = {"j$/time/Duration", "Lkotlin/time/Duration;", "toKotlinDuration", "(Lj$/time/Duration;)J", "toJavaDuration-LRDsOJo", "(J)Lj$/time/Duration;", "toJavaDuration", "kotlin-stdlib-jdk8"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DurationConversionsJDK8Kt {
    private static final long toKotlinDuration(Duration duration) {
        Intrinsics.checkNotNullParameter(duration, "<this>");
        return kotlin.time.Duration.m1616plusLRDsOJo(DurationKt.toDuration(duration.getSeconds(), DurationUnit.SECONDS), DurationKt.toDuration(duration.getNano(), DurationUnit.NANOSECONDS));
    }

    /* renamed from: toJavaDuration-LRDsOJo, reason: not valid java name */
    private static final Duration m1742toJavaDurationLRDsOJo(long j) {
        Duration ofSeconds = Duration.ofSeconds(kotlin.time.Duration.m1601getInWholeSecondsimpl(j), kotlin.time.Duration.m1603getNanosecondsComponentimpl(j));
        Intrinsics.checkNotNullExpressionValue(ofSeconds, "toJavaDuration-LRDsOJo");
        return ofSeconds;
    }
}
