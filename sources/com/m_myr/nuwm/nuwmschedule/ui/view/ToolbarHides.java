package com.m_myr.nuwm.nuwmschedule.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.ViewCompat;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public class ToolbarHides extends Toolbar {
    int titleColor;

    public ToolbarHides(Context context) {
        super(context, null);
    }

    public ToolbarHides(Context context, AttributeSet attrs) {
        super(context, attrs);
        fixColor(context, attrs, 0);
    }

    public ToolbarHides(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        fixColor(context, attrs, defStyleAttr);
    }

    private void fixColor(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.ToolbarHides, defStyleAttr, 0);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        this.titleColor = context.obtainStyledAttributes(resourceId, new int[]{android.R.attr.textColor}).getColor(0, ViewCompat.MEASURED_STATE_MASK);
        setTitleTextAppearance(context, resourceId);
        obtainStyledAttributes.recycle();
    }

    public final void setTextAlpha(float a) {
        setTitleTextColor(ColorUtils.setAlphaComponent(this.titleColor, (int) (a * 255.0f)));
    }
}
