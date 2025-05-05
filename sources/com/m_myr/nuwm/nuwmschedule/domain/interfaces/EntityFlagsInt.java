package com.m_myr.nuwm.nuwmschedule.domain.interfaces;

/* loaded from: classes2.dex */
public interface EntityFlagsInt {
    boolean checkAttribute(int attribute);

    int getFlag();

    void removeAttribute(int attribute);

    void setAttribute(int attribute);

    void setFlag(int newFlag);

    /* renamed from: com.m_myr.nuwm.nuwmschedule.domain.interfaces.EntityFlagsInt$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static boolean $default$checkAttribute(EntityFlagsInt _this, int attribute) {
            return (_this.getFlag() & attribute) == attribute;
        }
    }
}
