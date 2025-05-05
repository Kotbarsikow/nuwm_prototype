package com.m_myr.nuwm.nuwmschedule.utils;

/* loaded from: classes2.dex */
public class FlagsUtils {
    public static final boolean checkAttribute(int flag, int attribute) {
        return (flag & attribute) == attribute;
    }

    public final int removeAttribute(int flag, int attribute) {
        return flag & (~attribute);
    }

    public final int setAttribute(int flag, int attribute) {
        return flag | attribute;
    }
}
