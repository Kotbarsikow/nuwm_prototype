package com.m_myr.nuwm.nuwmschedule.ui.view.barview;

/* loaded from: classes2.dex */
public class BarDataEntry<T> {
    private T data;
    private boolean hint;
    private String name;
    private float v;

    public BarDataEntry(String name, float v, T data) {
        this.name = name;
        this.v = v;
        this.data = data;
    }

    public String getName() {
        return this.name;
    }

    public float getValue() {
        return this.v;
    }

    public T getData() {
        return this.data;
    }

    public void hint(boolean hint) {
        this.hint = hint;
    }

    public boolean isHint() {
        return this.hint;
    }

    public String getNameElipsized(int elips) {
        String str = this.name;
        if (str == null || str.length() <= elips) {
            return this.name;
        }
        return this.name.substring(0, elips) + "...";
    }
}
