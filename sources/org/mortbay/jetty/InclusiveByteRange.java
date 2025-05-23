package org.mortbay.jetty;

import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;
import org.mortbay.log.Log;
import org.mortbay.util.LazyList;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
public class InclusiveByteRange {
    long first;
    long last;

    public InclusiveByteRange(long j, long j2) {
        this.first = j;
        this.last = j2;
    }

    public long getFirst() {
        return this.first;
    }

    public long getLast() {
        return this.last;
    }

    public static List satisfiableRanges(Enumeration enumeration, long j) {
        long parseLong;
        long j2;
        Object obj = null;
        while (enumeration.hasMoreElements()) {
            StringTokenizer stringTokenizer = new StringTokenizer((String) enumeration.nextElement(), "=,", false);
            String str = null;
            while (true) {
                try {
                    if (stringTokenizer.hasMoreTokens()) {
                        str = stringTokenizer.nextToken().trim();
                        int indexOf = str.indexOf(45);
                        if (indexOf >= 0) {
                            int i = indexOf + 1;
                            if (str.indexOf("-", i) < 0) {
                                if (indexOf == 0) {
                                    if (i < str.length()) {
                                        j2 = Long.parseLong(str.substring(i).trim());
                                        parseLong = -1;
                                    } else {
                                        Log.warn("Bad range format: {}", str);
                                        break;
                                    }
                                } else if (i < str.length()) {
                                    parseLong = Long.parseLong(str.substring(0, indexOf).trim());
                                    j2 = Long.parseLong(str.substring(i).trim());
                                } else {
                                    parseLong = Long.parseLong(str.substring(0, indexOf).trim());
                                    j2 = -1;
                                }
                                if ((parseLong != -1 || j2 != -1) && (parseLong == -1 || j2 == -1 || parseLong <= j2)) {
                                    if (parseLong < j) {
                                        obj = LazyList.add(obj, new InclusiveByteRange(parseLong, j2));
                                    }
                                }
                            }
                        }
                        if (!HttpHeaderValues.BYTES.equals(str)) {
                            Log.warn("Bad range format: {}", str);
                            break;
                        }
                    }
                } catch (Exception e) {
                    Log.warn(new StringBuffer("Bad range format: ").append(str).toString());
                    Log.ignore(e);
                }
            }
        }
        return LazyList.getList(obj, true);
    }

    public long getFirst(long j) {
        long j2 = this.first;
        if (j2 >= 0) {
            return j2;
        }
        long j3 = j - this.last;
        if (j3 < 0) {
            return 0L;
        }
        return j3;
    }

    public long getLast(long j) {
        if (this.first < 0) {
            return j - 1;
        }
        long j2 = this.last;
        return (j2 < 0 || j2 >= j) ? j - 1 : j2;
    }

    public long getSize(long j) {
        return (getLast(j) - getFirst(j)) + 1;
    }

    public String toHeaderRangeString(long j) {
        StringBuffer stringBuffer = new StringBuffer(40);
        stringBuffer.append("bytes ");
        stringBuffer.append(getFirst(j));
        stringBuffer.append('-');
        stringBuffer.append(getLast(j));
        stringBuffer.append(URIUtil.SLASH);
        stringBuffer.append(j);
        return stringBuffer.toString();
    }

    public static String to416HeaderRangeString(long j) {
        StringBuffer stringBuffer = new StringBuffer(40);
        stringBuffer.append("bytes */");
        stringBuffer.append(j);
        return stringBuffer.toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(60);
        stringBuffer.append(Long.toString(this.first));
        stringBuffer.append(":");
        stringBuffer.append(Long.toString(this.last));
        return stringBuffer.toString();
    }
}
