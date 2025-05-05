package com.m_myr.nuwm.nuwmschedule.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import com.m_myr.nuwm.nuwmschedule.data.models.DayGroupMarks;
import com.m_myr.nuwm.nuwmschedule.data.models.SimpleUser;
import com.m_myr.nuwm.nuwmschedule.network.models.GroupMarksResponse;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes2.dex */
public class TableScoreView extends View {
    private GroupMarksResponse data;
    final float scaleDp;
    final float scaleSp;

    public TableScoreView(Context context) {
        this(context, null);
    }

    public TableScoreView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TableScoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.scaleDp = getResources().getDisplayMetrics().density;
        this.scaleSp = getResources().getDisplayMetrics().scaledDensity;
    }

    public TableScoreView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Iterator<Map.Entry<Integer, SimpleUser>> it = this.data.getStudents().entrySet().iterator();
        while (it.hasNext()) {
            SimpleUser value = it.next().getValue();
            Iterator<DayGroupMarks> it2 = this.data.getMarks().iterator();
            while (it2.hasNext()) {
                it2.next().getMarks().get(Integer.valueOf(value.getId()));
            }
        }
    }

    public void setData(GroupMarksResponse data) {
        this.data = data;
        invalidate();
    }
}
