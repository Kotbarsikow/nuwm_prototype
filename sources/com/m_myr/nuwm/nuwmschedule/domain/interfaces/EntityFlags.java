package com.m_myr.nuwm.nuwmschedule.domain.interfaces;

/* loaded from: classes2.dex */
public interface EntityFlags {
    public static final byte COLLAPSED = 16;
    public static final byte HIDDEN = 32;
    public static final byte MERGE = 64;

    boolean checkAttribute(byte attribute);

    byte getFlag();

    boolean isCollapsed();

    boolean isHidden();

    boolean isMerge();

    void removeAttribute(byte attribute);

    void setAttribute(byte attribute);

    void setFlag(byte newFlag);

    /* renamed from: com.m_myr.nuwm.nuwmschedule.domain.interfaces.EntityFlags$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static boolean $default$checkAttribute(EntityFlags _this, byte attribute) {
            return (_this.getFlag() & attribute) == attribute;
        }
    }
}
