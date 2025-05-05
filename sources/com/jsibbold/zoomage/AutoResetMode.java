package com.jsibbold.zoomage;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes2.dex */
public @interface AutoResetMode {
    public static final int ALWAYS = 2;
    public static final int NEVER = 3;
    public static final int OVER = 1;
    public static final int UNDER = 0;

    public static class Parser {
        public static int fromInt(int i) {
            int i2 = 1;
            if (i != 1) {
                i2 = 2;
                if (i != 2) {
                    i2 = 3;
                    if (i != 3) {
                        return 0;
                    }
                }
            }
            return i2;
        }
    }
}
