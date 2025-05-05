package com.m_myr.nuwm.nuwmschedule.ui.view.calendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.res.ResourcesCompat;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableDay;
import com.m_myr.nuwm.nuwmschedule.data.repositories.SchedulerProvider;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.domain.AppPreferences;
import com.m_myr.nuwm.nuwmschedule.utils.ResourcesHelper;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes2.dex */
public class MonthView extends View {
    private Calendar calendar;
    private int cellSize;
    private final int circleEventPadding;
    private final int circleEventRadius;
    private final int circleRadius;
    private Paint circleToday;
    private final int colorSelected;
    private final int colorToday;
    private int currentDay;
    private OnDayClickListener dayClickListener;
    private final Paint dayNamePaint;
    private final int dayNameTextSize;
    private final Paint dayPaint;
    private final int dayTextSize;
    private final Paint eventPaint;
    private int firstDayOfYear;
    private int firstDayOffset;
    private final Paint generalTextPaint;
    final GestureDetector gestureDetector;
    private int iconPaddingTop;
    private int lastDay;
    private int mainColor;
    private int month;
    private int padding;
    private final Paint rectCachePaint;
    private final Paint rectPaint;
    private final int row;
    final float scaleDp;
    final float scaleSp;
    SchedulerProvider schedulerProvider;
    private float scrollAlert;
    private int selectDay;
    private ClickableRegion selectInfoDay;
    private boolean showCache;
    private boolean showEvent;
    private final int textColor;

    static /* synthetic */ float access$116(MonthView monthView, float f) {
        float f2 = monthView.scrollAlert + f;
        monthView.scrollAlert = f2;
        return f2;
    }

    public void setSchedulerProvider(SchedulerProvider schedulerProvider) {
        this.schedulerProvider = schedulerProvider;
    }

    public MonthView(Context context) {
        this(context, null);
    }

    public MonthView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MonthView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.calendar = Calendar.getInstance();
        this.selectDay = Integer.MIN_VALUE;
        this.showEvent = true;
        this.showCache = true;
        this.row = 5;
        this.scrollAlert = 0.0f;
        this.gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.calendar.MonthView.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if (Math.abs(distanceY) > Math.abs(distanceX)) {
                    MonthView.access$116(MonthView.this, -distanceY);
                    MonthView.this.invalidate();
                    return false;
                }
                return super.onScroll(e1, e2, distanceX, distanceY);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent e) {
                if (!AppPreferences.getInstance().isEnableTestFunction()) {
                    super.onLongPress(e);
                    return;
                }
                Vibrator vibrator = (Vibrator) MonthView.this.getContext().getSystemService("vibrator");
                if (Build.VERSION.SDK_INT >= 26) {
                    vibrator.vibrate(VibrationEffect.createOneShot(20L, 5));
                } else {
                    vibrator.vibrate(20L);
                }
                MonthView monthView = MonthView.this;
                monthView.selectInfoDay = monthView.findRegion(e.getX(), e.getY());
                MonthView monthView2 = MonthView.this;
                monthView2.selectDay = monthView2.selectInfoDay.getDay();
                MonthView.this.scrollAlert = 0.0f;
                if (MonthView.this.selectInfoDay.isValidDate() && Build.VERSION.SDK_INT >= 23 && (MonthView.this.getForeground() instanceof RippleDrawable)) {
                    MonthView.this.getForeground().setBounds(MonthView.this.selectInfoDay.getArea().left, MonthView.this.selectInfoDay.getArea().top + MonthView.this.iconPaddingTop, MonthView.this.selectInfoDay.getArea().right, MonthView.this.selectInfoDay.getArea().bottom + MonthView.this.iconPaddingTop);
                    ((RippleDrawable) MonthView.this.getForeground()).setRadius(MonthView.this.selectInfoDay.getRadius());
                    MonthView.this.getForeground().setHotspot(e.getX(), e.getY());
                }
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent event) {
                ClickableRegion findRegion = MonthView.this.findRegion(event.getX(), event.getY());
                MonthView.this.selectDay = findRegion.getDay();
                if (MonthView.this.selectInfoDay != null && MonthView.this.selectDay != MonthView.this.selectInfoDay.getDay()) {
                    MonthView.this.selectInfoDay = null;
                }
                if (!findRegion.isValidDate()) {
                    return false;
                }
                if (MonthView.this.dayClickListener != null) {
                    MonthView.this.dayClickListener.onDaySelected(MonthView.this.selectDay, MonthView.this.month, MonthView.this.firstDayOfYear);
                }
                if (Build.VERSION.SDK_INT < 23 || !(MonthView.this.getForeground() instanceof RippleDrawable)) {
                    return true;
                }
                MonthView.this.getForeground().setBounds(findRegion.getArea().left, findRegion.getArea().top + MonthView.this.iconPaddingTop, findRegion.getArea().right, findRegion.getArea().bottom + MonthView.this.iconPaddingTop);
                ((RippleDrawable) MonthView.this.getForeground()).setRadius(findRegion.getRadius());
                MonthView.this.getForeground().setHotspot(event.getX(), event.getY());
                return true;
            }
        });
        this.scaleDp = getResources().getDisplayMetrics().density;
        float f = getResources().getDisplayMetrics().scaledDensity;
        this.scaleSp = f;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MonthView, defStyleAttr, 0);
        this.mainColor = ResourcesHelper.getAttrColor(App.getInstance(), R.attr.colorAccent);
        Paint paint = new Paint(1);
        this.rectCachePaint = paint;
        paint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint(1);
        this.dayPaint = paint2;
        Paint paint3 = new Paint(1);
        this.generalTextPaint = paint3;
        Paint paint4 = new Paint(1);
        this.dayNamePaint = paint4;
        Paint paint5 = new Paint(1);
        this.rectPaint = paint5;
        this.circleToday = new Paint(1);
        Paint paint6 = new Paint(1);
        this.eventPaint = paint6;
        try {
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(5, (int) (14.0f * f));
            this.dayTextSize = dimensionPixelSize;
            int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(4, (int) (16.0f * f));
            this.dayNameTextSize = dimensionPixelSize2;
            this.colorToday = obtainStyledAttributes.getColor(1, ResourcesHelper.getAttrColor(getContext(), R.attr.colorAccentLight));
            this.colorSelected = obtainStyledAttributes.getColor(2, ResourcesHelper.getAttrColor(getContext(), R.attr.colorPrimaryDark));
            paint.setColor(obtainStyledAttributes.getColor(0, ResourcesHelper.getAttrColor(getContext(), R.attr.colorBackgroundAccent)));
            int color = obtainStyledAttributes.getColor(3, ResourcesHelper.getAttrColor(getContext(), R.attr.colorTextMain));
            this.textColor = color;
            this.circleRadius = (int) (dimensionPixelSize2 * 0.9f);
            int i = (int) (dimensionPixelSize2 * 0.14f);
            this.circleEventRadius = i;
            this.circleEventPadding = (int) (i * 1.56f);
            obtainStyledAttributes.recycle();
            this.padding = dimensionPixelSize;
            this.iconPaddingTop = (int) ((-dimensionPixelSize) / 2.9f);
            paint2.setTextSize(dimensionPixelSize);
            paint2.setStyle(Paint.Style.FILL);
            paint2.setColor(color);
            paint2.setTextAlign(Paint.Align.CENTER);
            paint2.setTypeface(ResourcesCompat.getFont(context, R.font.basefont_regular));
            paint3.setTextSize(dimensionPixelSize * 0.9f);
            paint3.setStyle(Paint.Style.FILL);
            paint3.setColor(color);
            paint3.setTypeface(ResourcesCompat.getFont(context, R.font.basefont_regular));
            paint4.setTextSize(dimensionPixelSize2);
            paint4.setStyle(Paint.Style.FILL);
            paint4.setColor(color);
            paint4.setTextAlign(Paint.Align.CENTER);
            paint4.setTypeface(ResourcesCompat.getFont(context, R.font.basefont_semibold));
            paint5.setStyle(Paint.Style.FILL_AND_STROKE);
            paint5.setTextSize(dimensionPixelSize * 0.8f);
            paint6.setStyle(Paint.Style.FILL);
            paint6.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
            paint6.setTextSize(f * i * 1.6f);
            setClickable(true);
            setFocusable(true);
            if (Build.VERSION.SDK_INT >= 23) {
                setForeground(ResourcesHelper.getAttrDrawable(context, R.attr.selectableItemBackgroundBorderless));
            }
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public void setDayClickListener(OnDayClickListener dayClickListener) {
        this.dayClickListener = dayClickListener;
    }

    @Override // android.view.View
    protected void onMeasure(int parentWidth, int parentHeight) {
        super.onMeasure(parentWidth, parentHeight);
        int size = View.MeasureSpec.getSize(parentWidth);
        int size2 = View.MeasureSpec.getSize(parentHeight);
        if (size > 0 && size2 > 0) {
            int i = this.dayNameTextSize;
            int i2 = this.padding;
            int i3 = this.dayTextSize;
            size2 = (int) (i + i2 + (((i2 * 2) + i3) * 5) + i3 + (i2 * 2) + 0.5f);
        }
        setMeasuredDimension(size, size2);
        this.cellSize = (int) ((((size - getPaddingLeft()) - getPaddingRight()) / 7.0f) + 0.5f);
        ViewGroup.LayoutParams layoutParams = ((View) getParent()).getLayoutParams();
        layoutParams.height = size2;
        ((View) getParent()).setLayoutParams(layoutParams);
    }

    public void setMonth(int month, int selectedDay) {
        this.month = month;
        this.selectDay = selectedDay;
        if (month == this.calendar.get(2)) {
            this.currentDay = this.calendar.get(5);
        } else {
            this.currentDay = Integer.MIN_VALUE;
        }
        this.calendar.set(2, month);
        this.calendar.set(5, 1);
        this.firstDayOfYear = this.calendar.get(6);
        this.firstDayOffset = new int[]{0, 5, -1, 0, 1, 2, 3, 4}[this.calendar.get(7)];
        Calendar calendar = this.calendar;
        calendar.set(5, calendar.getActualMaximum(5));
        this.lastDay = this.calendar.get(5);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        TimetableDay timetableDay;
        canvas.drawText("пн", this.cellSize >> 1, this.dayTextSize, this.dayNamePaint);
        int i = this.cellSize;
        canvas.drawText("вт", i + (i >> 1), this.dayTextSize, this.dayNamePaint);
        int i2 = this.cellSize;
        canvas.drawText("ср", (i2 * 2) + (i2 >> 1), this.dayTextSize, this.dayNamePaint);
        int i3 = this.cellSize;
        canvas.drawText("чт", (i3 * 3) + (i3 >> 1), this.dayTextSize, this.dayNamePaint);
        int i4 = this.cellSize;
        canvas.drawText("пт", (i4 * 4) + (i4 >> 1), this.dayTextSize, this.dayNamePaint);
        int i5 = this.cellSize;
        canvas.drawText("сб", (i5 * 5) + (i5 >> 1), this.dayTextSize, this.dayNamePaint);
        int i6 = this.cellSize;
        canvas.drawText("нд", (i6 * 6) + (i6 >> 1), this.dayTextSize, this.dayNamePaint);
        this.circleToday.setColor(this.colorToday);
        this.circleToday.setStyle(Paint.Style.FILL_AND_STROKE);
        float f = this.circleRadius / 1.1f;
        float f2 = this.padding / 2.0f;
        for (int i7 = 0; i7 < 42; i7++) {
            int i8 = i7 - this.firstDayOffset;
            DayRegion findDayRegion = findDayRegion(i7);
            if (this.showCache && this.schedulerProvider.getStorage().getData().containsKey(Integer.valueOf((this.firstDayOfYear + i8) - 1))) {
                boolean containsKey = this.schedulerProvider.getStorage().getData().containsKey(Integer.valueOf(this.firstDayOfYear + i8));
                boolean containsKey2 = this.schedulerProvider.getStorage().getData().containsKey(Integer.valueOf((this.firstDayOfYear + i8) - 2));
                if (containsKey && containsKey2) {
                    canvas.drawRect(findDayRegion.area.left, findDayRegion.area.top, findDayRegion.area.right, findDayRegion.area.bottom - f2, this.rectCachePaint);
                } else if (containsKey) {
                    canvas.drawRoundRect(new RectF(findDayRegion.area.left, findDayRegion.area.top, findDayRegion.area.right + f, findDayRegion.area.bottom - f2), f, f, this.rectCachePaint);
                } else if (containsKey2) {
                    canvas.drawRoundRect(new RectF(findDayRegion.area.left - f, findDayRegion.area.top, findDayRegion.area.right, findDayRegion.area.bottom - f2), f, f, this.rectCachePaint);
                } else {
                    canvas.drawRoundRect(new RectF(findDayRegion.area.left, findDayRegion.area.top, findDayRegion.area.right, findDayRegion.area.bottom - f2), f, f, this.rectCachePaint);
                }
            }
            if (i8 == this.currentDay) {
                canvas.drawCircle(findDayRegion.getArea().centerX(), findDayRegion.getArea().centerY() + this.iconPaddingTop, this.circleRadius, this.circleToday);
            }
            if (i8 < 1) {
                this.dayPaint.setAlpha(18);
                this.calendar.set(2, this.month - 1);
                canvas.drawText(String.valueOf(this.calendar.getActualMaximum(5) + i8), findDayRegion.getArea().centerX(), findDayRegion.getArea().centerY(), this.dayPaint);
            } else if (i8 > this.lastDay) {
                this.dayPaint.setAlpha(18);
                canvas.drawText(String.valueOf(i8 - this.lastDay), findDayRegion.getArea().centerX(), findDayRegion.getArea().centerY(), this.dayPaint);
            } else {
                this.dayPaint.setAlpha(255);
                canvas.drawText(String.valueOf(i8), findDayRegion.getArea().centerX(), findDayRegion.getArea().centerY(), this.dayPaint);
                if (i8 != this.currentDay && i8 != this.selectDay && (timetableDay = this.schedulerProvider.getStorage().get((this.firstDayOfYear + i8) - 1)) != null) {
                    drawEvent(timetableDay, canvas, findDayRegion);
                }
            }
        }
        int i9 = this.selectDay;
        if (i9 > 0) {
            drawSelector(i9, canvas);
        }
        if (this.selectInfoDay != null) {
            drawAlert(canvas);
        }
        super.onDraw(canvas);
    }

    private void drawSelector(int selectDay, Canvas canvas) {
        this.circleToday.setColor(this.colorSelected);
        this.circleToday.setStyle(Paint.Style.STROKE);
        this.circleToday.setStrokeWidth(this.scaleDp);
        DayRegion findDayRegion = findDayRegion(selectDay + this.firstDayOffset);
        canvas.drawCircle(findDayRegion.getArea().centerX(), this.iconPaddingTop + findDayRegion.getArea().centerY(), this.circleRadius, this.circleToday);
    }

    private void drawAlert(Canvas canvas) {
        int i;
        TimetableDay timetableDay = this.schedulerProvider.getStorage().get((this.firstDayOfYear + this.selectInfoDay.getDay()) - 1);
        if (timetableDay != null) {
            if (timetableDay.getItems().size() == 0) {
                Rect rect = new Rect();
                this.generalTextPaint.getTextBounds("Немає завдань", 0, 13, rect);
                this.rectPaint.setColor(-1);
                this.rectPaint.setShadowLayer(this.scaleDp * 6.0f, 0.0f, 2.0f, -3355444);
                int centerX = (int) ((this.selectInfoDay.getArea().centerX() - this.padding) - (rect.width() / 2.0f));
                int width = rect.width() + centerX + (this.padding * 2);
                if (width > getRight()) {
                    int right = (width - getRight()) + (this.padding >> 1);
                    width -= right;
                    centerX -= right;
                }
                if (centerX < getLeft()) {
                    int left = (getLeft() - centerX) + (this.padding >> 1);
                    width += left;
                    centerX += left;
                }
                RectF rectF = new RectF(centerX, this.selectInfoDay.getArea().top - (this.padding >> 1), width, this.selectInfoDay.getArea().bottom + rect.height() + this.padding);
                int i2 = this.circleRadius;
                canvas.drawRoundRect(rectF, i2, i2, this.rectPaint);
                this.generalTextPaint.setColor(-12303292);
                canvas.drawText("Немає завдань", centerX + this.padding, this.selectInfoDay.getArea().bottom + (this.padding >> 1), this.generalTextPaint);
            } else {
                List<? extends ItemTimetableContract> items = timetableDay.getItems();
                Iterator<? extends ItemTimetableContract> it = items.iterator();
                int i3 = 0;
                int i4 = 0;
                while (it.hasNext()) {
                    String substrElips = Utils.StringUtils.substrElips(it.next().getTitle(), 20);
                    Rect rect2 = new Rect();
                    this.generalTextPaint.getTextBounds(substrElips, 0, substrElips.length(), rect2);
                    if (rect2.width() > i3) {
                        i3 = rect2.width();
                    }
                    i4 += rect2.height() + (this.padding >> 1);
                }
                this.rectPaint.setColor(-1);
                this.rectPaint.setShadowLayer(this.scaleDp * 6.0f, 0.0f, 2.0f, -3355444);
                int centerX2 = this.selectInfoDay.getArea().centerX();
                int i5 = (int) ((centerX2 - r4) - (i3 / 2.0f));
                int i6 = i3 + i5 + (this.padding * 2);
                if (i6 > getRight()) {
                    int right2 = (i6 - getRight()) + (this.padding >> 1);
                    i6 -= right2;
                    i5 -= right2;
                }
                if (i5 < getLeft()) {
                    int left2 = (getLeft() - i5) + (this.padding >> 1);
                    i6 += left2;
                    i5 += left2;
                }
                float f = this.selectInfoDay.getArea().bottom + i4 + this.padding;
                if (f > getBottom() - (this.padding >> 1)) {
                    f = getBottom() - (this.padding >> 1);
                }
                RectF rectF2 = new RectF(i5, this.selectInfoDay.getArea().top - (this.padding >> 1), i6, f);
                int i7 = this.circleRadius;
                canvas.drawRoundRect(rectF2, i7, i7, this.rectPaint);
                while (i < items.size()) {
                    String substrElips2 = Utils.StringUtils.substrElips(items.get(i).getTitle(), 20);
                    int i8 = this.selectInfoDay.getArea().bottom;
                    int i9 = this.padding;
                    float textSize = i8 + i9 + ((i9 >> 1) * i) + (i * this.generalTextPaint.getTextSize()) + this.scrollAlert;
                    this.generalTextPaint.setColor(items.get(i).getColor(this.mainColor));
                    this.generalTextPaint.setAlpha(255);
                    float f2 = (this.padding >> 2) + textSize;
                    int i10 = this.circleRadius;
                    if (f2 > f - i10) {
                        this.generalTextPaint.setAlpha((int) ((255.0f / i10) * (f - f2)));
                        if (f2 > f) {
                            break;
                        }
                    }
                    int i11 = this.selectInfoDay.getArea().bottom;
                    int i12 = this.padding;
                    if (textSize < i11 + i12) {
                        this.generalTextPaint.setAlpha((int) ((255.0f / i12) * (textSize - this.selectInfoDay.getArea().bottom)));
                        i = textSize < ((float) this.selectInfoDay.getArea().bottom) ? i + 1 : 0;
                    }
                    RectF rectF3 = new RectF((this.padding >> 1) + i5, textSize - this.generalTextPaint.getTextSize(), i6 - (this.padding >> 1), f2);
                    int i13 = this.circleEventRadius;
                    canvas.drawRoundRect(rectF3, i13, i13, this.generalTextPaint);
                    this.generalTextPaint.setColor(-1);
                    canvas.drawText(substrElips2, this.padding + i5, textSize, this.generalTextPaint);
                }
            }
            this.dayPaint.setAlpha(255);
            canvas.drawText(String.valueOf(this.selectInfoDay.getDay()), this.selectInfoDay.getArea().centerX(), this.selectInfoDay.getArea().centerY(), this.dayPaint);
            drawSelector(this.selectInfoDay.getDay(), canvas);
        }
    }

    private void drawEvent(TimetableDay timetableDay, Canvas canvas, DayRegion dayRegion) {
        Set<Integer> uniqueColors = timetableDay.getUniqueColors(this.mainColor);
        if (uniqueColors.size() == 1) {
            this.eventPaint.setColor(uniqueColors.iterator().next().intValue());
            canvas.drawCircle(dayRegion.getArea().centerX(), dayRegion.getArea().bottom - this.padding, this.circleEventRadius, this.eventPaint);
            return;
        }
        if (uniqueColors.size() == 2) {
            Iterator<Integer> it = uniqueColors.iterator();
            this.eventPaint.setColor(it.next().intValue());
            canvas.drawCircle(dayRegion.getArea().centerX() - this.circleEventPadding, dayRegion.getArea().bottom - this.padding, this.circleEventRadius, this.eventPaint);
            this.eventPaint.setColor(it.next().intValue());
            canvas.drawCircle(dayRegion.getArea().centerX() + this.circleEventPadding, dayRegion.getArea().bottom - this.padding, this.circleEventRadius, this.eventPaint);
            return;
        }
        if (uniqueColors.size() > 2) {
            Iterator<Integer> it2 = uniqueColors.iterator();
            this.eventPaint.setColor(it2.next().intValue());
            canvas.drawCircle(dayRegion.getArea().centerX() - (this.circleEventPadding * 2), dayRegion.getArea().bottom - this.padding, this.circleEventRadius, this.eventPaint);
            this.eventPaint.setColor(it2.next().intValue());
            canvas.drawCircle(dayRegion.getArea().centerX(), dayRegion.getArea().bottom - this.padding, this.circleEventRadius, this.eventPaint);
            this.eventPaint.setColor(-1442840576);
            canvas.drawText("+", dayRegion.getArea().centerX() + this.circleEventPadding + this.scaleSp, (dayRegion.getArea().bottom - this.padding) + this.circleEventRadius, this.eventPaint);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ClickableRegion findRegion(float x, float y) {
        int i = this.dayTextSize;
        int i2 = this.cellSize;
        int i3 = ((int) x) / i2;
        int i4 = this.dayNameTextSize;
        int i5 = this.padding;
        int i6 = (int) (((y - i4) - i5) / ((i5 * 2) + i));
        int i7 = i4 + i5 + (((i5 * 2) + i) * i6);
        int i8 = i2 * i3;
        int i9 = (i2 * i3) + i2;
        int i10 = i + i7 + (i5 * 2);
        int i11 = (i3 + (i6 * 7)) - this.firstDayOffset;
        if (i11 > this.lastDay || i11 < 1) {
            i11 = Integer.MIN_VALUE;
        }
        return new ClickableRegion(new Rect(i8, i7, i9, i10), i11, this.circleRadius * 1.4f);
    }

    private DayRegion findDayRegion(int dayIndex) {
        int i = dayIndex / 7;
        int i2 = dayIndex % 7;
        int i3 = this.dayNameTextSize;
        int i4 = this.padding;
        int i5 = this.dayTextSize;
        int i6 = i3 + i4 + (i * ((i4 * 2) + i5));
        int i7 = this.cellSize;
        return new DayRegion(new Rect(i7 * i2, i6, (i7 * i2) + i7, i5 + i6 + (i4 * 2)), i2);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private int getDayOfYear(int selectDay) {
        return (this.firstDayOfYear + selectDay) - 1;
    }

    public static class DayRegion {
        private Rect area;
        private int day;

        public DayRegion(Rect area, int day) {
            this.area = area;
            this.day = day;
        }

        public Rect getArea() {
            return this.area;
        }

        public int getDay() {
            return this.day;
        }

        public boolean isValidDate() {
            return this.day > 0;
        }
    }

    public static class ClickableRegion extends DayRegion {
        private int radius;

        public ClickableRegion(Rect area, int day, float radius) {
            super(area, day);
            this.radius = (int) radius;
        }

        public int getRadius() {
            return this.radius;
        }
    }

    public interface OnDayClickListener {
        void onDaySelected(int dayOfYear);

        void onDaySelected(int day, int month, int offset);

        /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.view.calendar.MonthView$OnDayClickListener$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
        }
    }
}
