package org.mortbay.log;

import com.hootsuite.nachos.tokenizer.SpanChipTokenizer;
import kotlin.text.Typography;
import org.mortbay.util.DateCache;

/* loaded from: classes3.dex */
public class StdErrLog implements Logger {
    private static boolean __debug;
    private static DateCache _dateCache;
    StringBuffer _buffer;
    private String _name;

    static {
        __debug = System.getProperty("DEBUG", null) != null;
        try {
            _dateCache = new DateCache("yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StdErrLog() {
        this(null);
    }

    public StdErrLog(String str) {
        this._buffer = new StringBuffer();
        this._name = str == null ? "" : str;
    }

    @Override // org.mortbay.log.Logger
    public boolean isDebugEnabled() {
        return __debug;
    }

    @Override // org.mortbay.log.Logger
    public void setDebugEnabled(boolean z) {
        __debug = z;
    }

    @Override // org.mortbay.log.Logger
    public void info(String str, Object obj, Object obj2) {
        String now = _dateCache.now();
        int lastMs = _dateCache.lastMs();
        synchronized (this._buffer) {
            tag(now, lastMs, ":INFO:");
            format(str, obj, obj2);
            System.err.println(this._buffer.toString());
        }
    }

    @Override // org.mortbay.log.Logger
    public void debug(String str, Throwable th) {
        if (__debug) {
            String now = _dateCache.now();
            int lastMs = _dateCache.lastMs();
            synchronized (this._buffer) {
                tag(now, lastMs, ":DBUG:");
                format(str);
                format(th);
                System.err.println(this._buffer.toString());
            }
        }
    }

    @Override // org.mortbay.log.Logger
    public void debug(String str, Object obj, Object obj2) {
        if (__debug) {
            String now = _dateCache.now();
            int lastMs = _dateCache.lastMs();
            synchronized (this._buffer) {
                tag(now, lastMs, ":DBUG:");
                format(str, obj, obj2);
                System.err.println(this._buffer.toString());
            }
        }
    }

    @Override // org.mortbay.log.Logger
    public void warn(String str, Object obj, Object obj2) {
        String now = _dateCache.now();
        int lastMs = _dateCache.lastMs();
        synchronized (this._buffer) {
            tag(now, lastMs, ":WARN:");
            format(str, obj, obj2);
            System.err.println(this._buffer.toString());
        }
    }

    @Override // org.mortbay.log.Logger
    public void warn(String str, Throwable th) {
        String now = _dateCache.now();
        int lastMs = _dateCache.lastMs();
        synchronized (this._buffer) {
            tag(now, lastMs, ":WARN:");
            format(str);
            format(th);
            System.err.println(this._buffer.toString());
        }
    }

    private void tag(String str, int i, String str2) {
        this._buffer.setLength(0);
        this._buffer.append(str);
        if (i > 99) {
            this._buffer.append('.');
        } else if (i > 9) {
            this._buffer.append(".0");
        } else {
            this._buffer.append(".00");
        }
        this._buffer.append(i).append(str2).append(this._name).append(':');
    }

    private void format(String str, Object obj, Object obj2) {
        int indexOf = str == null ? -1 : str.indexOf("{}");
        int indexOf2 = indexOf >= 0 ? str.indexOf("{}", indexOf + 2) : -1;
        if (indexOf >= 0) {
            format(str.substring(0, indexOf));
            if (obj == null) {
                obj = "null";
            }
            format(String.valueOf(obj));
            if (indexOf2 >= 0) {
                format(str.substring(indexOf + 2, indexOf2));
                if (obj2 == null) {
                    obj2 = "null";
                }
                format(String.valueOf(obj2));
                format(str.substring(indexOf2 + 2));
                return;
            }
            format(str.substring(indexOf + 2));
            if (obj2 != null) {
                this._buffer.append(SpanChipTokenizer.AUTOCORRECT_SEPARATOR);
                format(String.valueOf(obj2));
                return;
            }
            return;
        }
        format(str);
        if (obj != null) {
            this._buffer.append(SpanChipTokenizer.AUTOCORRECT_SEPARATOR);
            format(String.valueOf(obj));
        }
        if (obj2 != null) {
            this._buffer.append(SpanChipTokenizer.AUTOCORRECT_SEPARATOR);
            format(String.valueOf(obj2));
        }
    }

    private void format(String str) {
        if (str == null) {
            this._buffer.append("null");
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (!Character.isISOControl(charAt)) {
                this._buffer.append(charAt);
            } else if (charAt == '\n') {
                this._buffer.append('|');
            } else if (charAt == '\r') {
                this._buffer.append(Typography.less);
            } else {
                this._buffer.append('?');
            }
        }
    }

    private void format(Throwable th) {
        if (th == null) {
            this._buffer.append("null");
            return;
        }
        this._buffer.append('\n');
        format(th.toString());
        StackTraceElement[] stackTrace = th.getStackTrace();
        for (int i = 0; stackTrace != null && i < stackTrace.length; i++) {
            this._buffer.append("\n\tat ");
            format(stackTrace[i].toString());
        }
    }

    @Override // org.mortbay.log.Logger
    public Logger getLogger(String str) {
        return (!(str == null && this._name == null) && (str == null || !str.equals(this._name))) ? new StdErrLog(str) : this;
    }

    public String toString() {
        return new StringBuffer("STDERR").append(this._name).toString();
    }
}
