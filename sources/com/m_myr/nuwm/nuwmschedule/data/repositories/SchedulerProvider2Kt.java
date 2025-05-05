package com.m_myr.nuwm.nuwmschedule.data.repositories;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* compiled from: SchedulerProvider2.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\u001a\"\u0010\u0007\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0002\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0004¨\u0006\f"}, d2 = {"keyWordsType", "", "", "getKeyWordsType", "()Ljava/util/List;", "typeName", "getTypeName", "replaceForce", "occurrenceIndexFirst", "", "oldValue", "newValue", "app_publicReleaseRelease"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SchedulerProvider2Kt {
    private static final List<String> keyWordsType = CollectionsKt.listOfNotNull((Object[]) new String[]{"лекція", "лаб.роб", "(лаб)", "(л)", "(прс)", "практичне заняття"});
    private static final List<String> typeName = CollectionsKt.listOfNotNull((Object[]) new String[]{"Online Lecture", "Online Laboratory", "Online Laboratory", "Online Lecture", "Online Seminar", "Online Practical Class"});

    public static final List<String> getKeyWordsType() {
        return keyWordsType;
    }

    public static final List<String> getTypeName() {
        return typeName;
    }

    public static final String replaceForce(String str, int i, String oldValue, String newValue) {
        String str2;
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(oldValue, "oldValue");
        Intrinsics.checkNotNullParameter(newValue, "newValue");
        if (i < 0) {
            return str;
        }
        int length = oldValue.length();
        int coerceAtLeast = RangesKt.coerceAtLeast(length, 1);
        int length2 = (str.length() - length) + newValue.length();
        if (length2 < 0) {
            throw new OutOfMemoryError();
        }
        StringBuilder sb = new StringBuilder(length2);
        int i2 = 0;
        do {
            str2 = str;
            sb.append((CharSequence) str2, i2, i);
            sb.append(newValue);
            i2 = i + length;
            if (i >= str.length()) {
                break;
            }
            i = StringsKt.indexOf((CharSequence) str2, oldValue, i + coerceAtLeast, false);
        } while (i > 0);
        sb.append((CharSequence) str2, i2, str.length());
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }
}
