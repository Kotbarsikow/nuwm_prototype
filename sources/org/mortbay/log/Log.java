package org.mortbay.log;

import java.security.AccessController;
import java.security.PrivilegedAction;
import org.mortbay.util.Loader;

/* loaded from: classes3.dex */
public class Log {
    public static final String EXCEPTION = "EXCEPTION ";
    public static final String IGNORED = "IGNORED";
    public static final String IGNORED_FMT = "IGNORED: {}";
    public static final String NOT_IMPLEMENTED = "NOT IMPLEMENTED ";
    public static boolean __ignored;
    private static Logger __log;
    public static String __logClass;
    private static final String[] __nestedEx = {"getTargetException", "getTargetError", "getException", "getRootCause"};
    private static final Class[] __noArgs = new Class[0];
    public static boolean __verbose;
    static /* synthetic */ Class class$org$mortbay$log$Log;
    static /* synthetic */ Class class$org$mortbay$log$StdErrLog;

    static {
        Class cls;
        AccessController.doPrivileged(new PrivilegedAction() { // from class: org.mortbay.log.Log.1
            AnonymousClass1() {
            }

            @Override // java.security.PrivilegedAction
            public Object run() {
                Log.__logClass = System.getProperty("org.mortbay.log.class", "org.mortbay.log.Slf4jLog");
                Log.__verbose = System.getProperty("VERBOSE", null) != null;
                Log.__ignored = System.getProperty(Log.IGNORED, null) != null;
                return new Boolean(true);
            }
        });
        try {
            Class cls2 = class$org$mortbay$log$Log;
            if (cls2 == null) {
                cls2 = class$("org.mortbay.log.Log");
                class$org$mortbay$log$Log = cls2;
            }
            cls = Loader.loadClass(cls2, __logClass);
            __log = (Logger) cls.newInstance();
        } catch (Throwable th) {
            Class cls3 = class$org$mortbay$log$StdErrLog;
            if (cls3 == null) {
                cls3 = class$("org.mortbay.log.StdErrLog");
                class$org$mortbay$log$StdErrLog = cls3;
            }
            __log = new StdErrLog();
            __logClass = cls3.getName();
            if (__verbose) {
                th.printStackTrace();
            }
            cls = cls3;
        }
        Logger logger = __log;
        logger.info("Logging to {} via {}", logger, cls.getName());
    }

    /* renamed from: org.mortbay.log.Log$1 */
    final class AnonymousClass1 implements PrivilegedAction {
        AnonymousClass1() {
        }

        @Override // java.security.PrivilegedAction
        public Object run() {
            Log.__logClass = System.getProperty("org.mortbay.log.class", "org.mortbay.log.Slf4jLog");
            Log.__verbose = System.getProperty("VERBOSE", null) != null;
            Log.__ignored = System.getProperty(Log.IGNORED, null) != null;
            return new Boolean(true);
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public static void setLog(Logger logger) {
        __log = logger;
    }

    public static Logger getLog() {
        return __log;
    }

    public static void debug(Throwable th) {
        if (__log == null || !isDebugEnabled()) {
            return;
        }
        __log.debug(EXCEPTION, th);
        unwind(th);
    }

    public static void debug(String str) {
        Logger logger = __log;
        if (logger == null) {
            return;
        }
        logger.debug(str, null, null);
    }

    public static void debug(String str, Object obj) {
        Logger logger = __log;
        if (logger == null) {
            return;
        }
        logger.debug(str, obj, null);
    }

    public static void debug(String str, Object obj, Object obj2) {
        Logger logger = __log;
        if (logger == null) {
            return;
        }
        logger.debug(str, obj, obj2);
    }

    public static void ignore(Throwable th) {
        Logger logger = __log;
        if (logger == null) {
            return;
        }
        if (__ignored) {
            logger.warn(IGNORED, th);
            unwind(th);
        } else if (__verbose) {
            logger.debug(IGNORED, th);
            unwind(th);
        }
    }

    public static void info(String str) {
        Logger logger = __log;
        if (logger == null) {
            return;
        }
        logger.info(str, null, null);
    }

    public static void info(String str, Object obj) {
        Logger logger = __log;
        if (logger == null) {
            return;
        }
        logger.info(str, obj, null);
    }

    public static void info(String str, Object obj, Object obj2) {
        Logger logger = __log;
        if (logger == null) {
            return;
        }
        logger.info(str, obj, obj2);
    }

    public static boolean isDebugEnabled() {
        Logger logger = __log;
        if (logger == null) {
            return false;
        }
        return logger.isDebugEnabled();
    }

    public static void warn(String str) {
        Logger logger = __log;
        if (logger == null) {
            return;
        }
        logger.warn(str, null, null);
    }

    public static void warn(String str, Object obj) {
        Logger logger = __log;
        if (logger == null) {
            return;
        }
        logger.warn(str, obj, null);
    }

    public static void warn(String str, Object obj, Object obj2) {
        Logger logger = __log;
        if (logger == null) {
            return;
        }
        logger.warn(str, obj, obj2);
    }

    public static void warn(String str, Throwable th) {
        Logger logger = __log;
        if (logger == null) {
            return;
        }
        logger.warn(str, th);
        unwind(th);
    }

    public static void warn(Throwable th) {
        Logger logger = __log;
        if (logger == null) {
            return;
        }
        logger.warn(EXCEPTION, th);
        unwind(th);
    }

    public static Logger getLogger(String str) {
        Logger logger = __log;
        return (logger == null || str == null) ? logger : logger.getLogger(str);
    }

    private static void unwind(Throwable th) {
        if (th == null) {
            return;
        }
        int i = 0;
        while (true) {
            String[] strArr = __nestedEx;
            if (i >= strArr.length) {
                return;
            }
            try {
                Throwable th2 = (Throwable) th.getClass().getMethod(strArr[i], __noArgs).invoke(th, null);
                if (th2 != null && th2 != th) {
                    warn(new StringBuffer().append("Nested in ").append(th).append(":").toString(), th2);
                }
            } catch (Exception unused) {
            }
            i++;
        }
    }
}
