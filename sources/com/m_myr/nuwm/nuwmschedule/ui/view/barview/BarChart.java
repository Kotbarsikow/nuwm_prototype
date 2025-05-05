package com.m_myr.nuwm.nuwmschedule.ui.view.barview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.core.content.res.ResourcesCompat;
import com.github.mikephil.charting.animation.Easing;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.utils.ResourcesHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* loaded from: classes2.dex */
public class BarChart extends View {
    private ArrayList<BarDataEntry> barEntries;
    private float barHeight;
    private float barPaddingHorizontal;
    private float barPaddingVertical;
    private int duration;
    final int elips;
    float endX;
    private Easing.EasingFunction function;
    private final int hintColor;
    private float labelTextSize;
    private int mainColor;
    private float maxLabelLength;
    private float maxValue;
    private float minValue;
    private final Paint paintBar;
    private final Paint paintText;
    private final Paint paintVal;
    final float scaleDp;
    final float scaleSp;
    private boolean showLabel;
    float startX;
    private float stepSize;
    private boolean stickyRigth;
    private ValueFormatter valueFormatter;
    private float valueTextSize;

    public interface ValueFormatter {
        String onFormat(float val);
    }

    public BarChart(Context context) {
        this(context, null);
    }

    public BarChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BarChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.elips = 25;
        this.barEntries = new ArrayList<>();
        this.minValue = 0.0f;
        this.maxValue = 0.0f;
        this.duration = 0;
        this.showLabel = true;
        this.valueFormatter = new ValueFormatter() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.barview.BarChart.1
            @Override // com.m_myr.nuwm.nuwmschedule.ui.view.barview.BarChart.ValueFormatter
            public String onFormat(float val) {
                return String.valueOf(val);
            }
        };
        this.maxLabelLength = 0.0f;
        this.startX = 0.0f;
        this.endX = 0.0f;
        float f = getResources().getDisplayMetrics().density;
        this.scaleDp = f;
        float f2 = getResources().getDisplayMetrics().scaledDensity;
        this.scaleSp = f2;
        this.hintColor = adjustAlpha(ResourcesHelper.getAttrColor(context, R.attr.colorPrimaryDark), 0.6f);
        this.mainColor = adjustAlpha(ResourcesHelper.getAttrColor(context, R.attr.colorPrimaryDark), 0.4f);
        this.barHeight = 20.0f * f;
        this.barPaddingHorizontal = 6.0f * f;
        this.barPaddingVertical = f * 4.0f;
        this.valueTextSize = 10.0f * f2;
        this.labelTextSize = f2 * 13.0f;
        Paint paint = new Paint(1);
        this.paintVal = paint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(ResourcesHelper.getAttrColor(context, R.attr.colorTextSub));
        paint.setTextSize(this.valueTextSize);
        paint.setTypeface(ResourcesCompat.getFont(context, R.font.basefont_semibold));
        Paint paint2 = new Paint(1);
        this.paintText = paint2;
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);
        paint2.setColor(ResourcesHelper.getAttrColor(context, R.attr.colorTextMain));
        paint2.setTextAlign(Paint.Align.RIGHT);
        paint2.setTextSize(this.labelTextSize);
        Paint paint3 = new Paint(1);
        this.paintBar = paint3;
        paint3.setStyle(Paint.Style.FILL);
        paint3.setColor(this.mainColor);
    }

    public static int adjustAlpha(int color, float factor) {
        return Color.argb(Math.round(Color.alpha(color) * factor), Color.red(color), Color.green(color), Color.blue(color));
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i;
        if (getLayoutParams().width == -2) {
            getLayoutParams().width = -1;
            requestLayout();
            i = 100;
        } else if (getLayoutParams().width == -1) {
            i = View.MeasureSpec.getSize(widthMeasureSpec);
        } else {
            i = getLayoutParams().width;
        }
        int round = Math.round((this.barEntries.size() * this.barHeight) + (this.barEntries.size() * (this.barPaddingVertical + this.scaleDp)));
        getLayoutParams().height = getPaddingTop() + round + getPaddingBottom();
        setMeasuredDimension(1073741824 | i, round);
        Log.e("onMeasure", " width " + i + "  height" + round + " barEntries" + this.barEntries);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        calculateBarBounds();
        int round = Math.round((this.barEntries.size() / 100.0f) * 45.0f) - 1;
        int i = 0;
        while (i < this.barEntries.size()) {
            drawBar(canvas, i, this.barEntries.get(i), i < round);
            i++;
        }
    }

    private void drawBar(Canvas canvas, int i, BarDataEntry dataEntry, boolean best) {
        float topBar = getTopBar(i);
        float f = (this.barHeight / 1.6f) + topBar;
        if (dataEntry.isHint()) {
            this.paintBar.setColor(this.hintColor);
            this.paintText.setFakeBoldText(true);
        } else {
            this.paintBar.setColor(this.mainColor);
            this.paintText.setFakeBoldText(false);
        }
        if (!best) {
            this.paintBar.setAlpha(58);
        }
        float rightBar = getRightBar(dataEntry.getValue());
        canvas.drawText(dataEntry.getNameElipsized(25), this.startX - this.barPaddingHorizontal, f, this.paintText);
        canvas.drawRect(this.startX, topBar, rightBar, topBar + this.barHeight, this.paintBar);
        canvas.drawText(this.valueFormatter.onFormat(dataEntry.getValue()), rightBar + this.barPaddingHorizontal, f, this.paintVal);
    }

    private float getRightBar(float value) {
        return this.startX + (this.stepSize * value);
    }

    private float getTopBar(int i) {
        float f = this.barPaddingVertical;
        float f2 = i;
        return (this.barHeight * f2) + f + (f * f2);
    }

    private void calculateBarBounds() {
        this.startX = getPaddingLeft() + this.barPaddingHorizontal;
        float right = getRight() - getPaddingRight();
        float f = this.barPaddingHorizontal;
        this.endX = right - f;
        if (this.showLabel) {
            this.startX += this.maxLabelLength + f;
        }
        if (!this.stickyRigth) {
            Rect rect = new Rect();
            String onFormat = this.valueFormatter.onFormat(this.maxValue);
            this.paintVal.getTextBounds(onFormat, 0, onFormat.length(), rect);
            this.endX -= rect.width();
        }
        this.stepSize = (this.endX - this.startX) / this.maxValue;
    }

    @Override // android.view.View
    public void invalidate() {
        Collections.sort(this.barEntries, new Comparator() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.barview.BarChart$$ExternalSyntheticLambda1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Float.compare(((BarDataEntry) obj2).getValue(), ((BarDataEntry) obj).getValue());
                return compare;
            }
        });
        calculateXRange(false);
        requestLayout();
        super.invalidate();
    }

    public void addData(ArrayList<BarDataEntry> barEntries) {
        this.barEntries = barEntries;
    }

    public void calculateXRange(boolean sticky) {
        this.stickyRigth = sticky;
        if (this.barEntries.isEmpty()) {
            this.maxValue = 0.0f;
            return;
        }
        this.maxValue = ((BarDataEntry) Collections.max(this.barEntries, new Comparator() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.barview.BarChart$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Float.compare(((BarDataEntry) obj).getValue(), ((BarDataEntry) obj2).getValue());
                return compare;
            }
        })).getValue();
        BarDataEntry barDataEntry = (BarDataEntry) Collections.max(this.barEntries, new Comparator<BarDataEntry>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.barview.BarChart.2
            @Override // java.util.Comparator
            public int compare(BarDataEntry o1, BarDataEntry o2) {
                Rect rect = new Rect();
                BarChart.this.paintText.getTextBounds(o1.getNameElipsized(25), 0, o1.getNameElipsized(25).length(), rect);
                int width = rect.width();
                Rect rect2 = new Rect();
                BarChart.this.paintText.getTextBounds(o2.getNameElipsized(25), 0, o2.getNameElipsized(25).length(), rect2);
                return Integer.compare(width, rect2.width());
            }
        });
        this.paintText.getTextBounds(barDataEntry.getNameElipsized(25), 0, barDataEntry.getNameElipsized(25).length(), new Rect());
        this.maxLabelLength = r0.width();
    }

    public void animateX(int duration, Easing.EasingFunction function) {
        this.duration = duration;
        this.function = function;
    }

    public void setValueFormatter(ValueFormatter valueFormatter) {
        this.valueFormatter = valueFormatter;
    }
}
