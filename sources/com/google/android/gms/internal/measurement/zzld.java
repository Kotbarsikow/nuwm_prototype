package com.google.android.gms.internal.measurement;

import com.hootsuite.nachos.tokenizer.SpanChipTokenizer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.text.Typography;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes.dex */
final class zzld {
    private static final char[] zza;

    static String zza(zzlc zzlcVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zza(zzlcVar, sb, 0);
        return sb.toString();
    }

    static {
        char[] cArr = new char[80];
        zza = cArr;
        Arrays.fill(cArr, SpanChipTokenizer.AUTOCORRECT_SEPARATOR);
    }

    private static void zza(int i, StringBuilder sb) {
        while (i > 0) {
            char[] cArr = zza;
            int length = i > cArr.length ? cArr.length : i;
            sb.append(cArr, 0, length);
            i -= length;
        }
    }

    static void zza(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                zza(sb, i, str, it.next());
            }
            return;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                zza(sb, i, str, (Map.Entry) it2.next());
            }
            return;
        }
        sb.append('\n');
        zza(i, sb);
        if (!str.isEmpty()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(Character.toLowerCase(str.charAt(0)));
            for (int i2 = 1; i2 < str.length(); i2++) {
                char charAt = str.charAt(i2);
                if (Character.isUpperCase(charAt)) {
                    sb2.append("_");
                }
                sb2.append(Character.toLowerCase(charAt));
            }
            str = sb2.toString();
        }
        sb.append(str);
        if (obj instanceof String) {
            sb.append(": \"");
            sb.append(zzmg.zza(zzik.zza((String) obj)));
            sb.append(Typography.quote);
            return;
        }
        if (obj instanceof zzik) {
            sb.append(": \"");
            sb.append(zzmg.zza((zzik) obj));
            sb.append(Typography.quote);
            return;
        }
        if (obj instanceof zzjt) {
            sb.append(" {");
            zza((zzjt) obj, sb, i + 2);
            sb.append("\n");
            zza(i, sb);
            sb.append("}");
            return;
        }
        if (obj instanceof Map.Entry) {
            sb.append(" {");
            Map.Entry entry = (Map.Entry) obj;
            int i3 = i + 2;
            zza(sb, i3, "key", entry.getKey());
            zza(sb, i3, "value", entry.getValue());
            sb.append("\n");
            zza(i, sb);
            sb.append("}");
            return;
        }
        sb.append(": ");
        sb.append(obj);
    }

    /* JADX WARN: Code restructure failed: missing block: B:75:0x0193, code lost:
    
        if (((java.lang.Boolean) r7).booleanValue() == false) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0195, code lost:
    
        r13 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x01a6, code lost:
    
        if (((java.lang.Integer) r7).intValue() == 0) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x01b8, code lost:
    
        if (java.lang.Float.floatToRawIntBits(((java.lang.Float) r7).floatValue()) == 0) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01ce, code lost:
    
        if (java.lang.Double.doubleToRawLongBits(((java.lang.Double) r7).doubleValue()) == 0) goto L64;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void zza(com.google.android.gms.internal.measurement.zzlc r20, java.lang.StringBuilder r21, int r22) {
        /*
            Method dump skipped, instructions count: 590
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzld.zza(com.google.android.gms.internal.measurement.zzlc, java.lang.StringBuilder, int):void");
    }
}
