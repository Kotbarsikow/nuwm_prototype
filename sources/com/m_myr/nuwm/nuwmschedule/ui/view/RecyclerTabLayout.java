package com.m_myr.nuwm.nuwmschedule.ui.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public class RecyclerTabLayout extends RecyclerView {
    protected static final float DEFAULT_POSITION_THRESHOLD = 0.6f;
    protected static final long DEFAULT_SCROLL_DURATION = 200;
    protected static final float POSITION_THRESHOLD_ALLOWABLE = 0.001f;
    protected Adapter<?> mAdapter;
    protected int mIndicatorGap;
    protected int mIndicatorHeight;
    protected Paint mIndicatorPaint;
    protected int mIndicatorPosition;
    protected int mIndicatorScroll;
    protected LinearLayoutManager mLinearLayoutManager;
    private int mOldPosition;
    protected float mOldPositionOffset;
    private int mOldScrollOffset;
    protected float mPositionThreshold;
    protected RecyclerOnScrollListener mRecyclerOnScrollListener;
    protected boolean mRequestScrollToTab;
    protected boolean mScrollEanbled;
    protected int mTabBackgroundResId;
    protected int mTabMaxWidth;
    protected int mTabMinWidth;
    protected int mTabOnScreenLimit;
    protected int mTabPaddingBottom;
    protected int mTabPaddingEnd;
    protected int mTabPaddingStart;
    protected int mTabPaddingTop;
    protected int mTabSelectedTextColor;
    protected boolean mTabSelectedTextColorSet;
    protected int mTabTextAppearance;
    protected ViewPager2 mViewPager;

    public RecyclerTabLayout(Context context) {
        this(context, null);
    }

    public RecyclerTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecyclerTabLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setWillNotDraw(false);
        Paint paint = new Paint(1);
        this.mIndicatorPaint = paint;
        paint.setStrokeCap(Paint.Cap.ROUND);
        this.mIndicatorPaint.setStrokeWidth(context.getResources().getDimensionPixelSize(R.dimen.indicator_width));
        getAttributes(context, attrs, defStyle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()) { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.RecyclerTabLayout.1
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollHorizontally() {
                return RecyclerTabLayout.this.mScrollEanbled;
            }
        };
        this.mLinearLayoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(0);
        setLayoutManager(this.mLinearLayoutManager);
        setItemAnimator(null);
        this.mPositionThreshold = DEFAULT_POSITION_THRESHOLD;
    }

    private void getAttributes(Context context, AttributeSet attrs, int defStyle) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.rtl_RecyclerTabLayout, defStyle, R.style.rtl_RecyclerTabLayout);
        setIndicatorColor(obtainStyledAttributes.getColor(2, 0));
        setIndicatorHeight(obtainStyledAttributes.getDimensionPixelSize(3, 0));
        this.mTabTextAppearance = obtainStyledAttributes.getResourceId(13, R.style.rtl_RecyclerTabLayout_Tab);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(7, 0);
        this.mTabPaddingBottom = dimensionPixelSize;
        this.mTabPaddingEnd = dimensionPixelSize;
        this.mTabPaddingTop = dimensionPixelSize;
        this.mTabPaddingStart = dimensionPixelSize;
        this.mTabPaddingStart = obtainStyledAttributes.getDimensionPixelSize(10, dimensionPixelSize);
        this.mTabPaddingTop = obtainStyledAttributes.getDimensionPixelSize(11, this.mTabPaddingTop);
        this.mTabPaddingEnd = obtainStyledAttributes.getDimensionPixelSize(9, this.mTabPaddingEnd);
        this.mTabPaddingBottom = obtainStyledAttributes.getDimensionPixelSize(8, this.mTabPaddingBottom);
        if (obtainStyledAttributes.hasValue(12)) {
            this.mTabSelectedTextColor = obtainStyledAttributes.getColor(12, 0);
            this.mTabSelectedTextColorSet = true;
        }
        int integer = obtainStyledAttributes.getInteger(6, 0);
        this.mTabOnScreenLimit = integer;
        if (integer == 0) {
            this.mTabMinWidth = obtainStyledAttributes.getDimensionPixelSize(5, 0);
            this.mTabMaxWidth = obtainStyledAttributes.getDimensionPixelSize(4, 0);
        }
        this.mTabBackgroundResId = obtainStyledAttributes.getResourceId(1, 0);
        this.mScrollEanbled = obtainStyledAttributes.getBoolean(0, true);
        obtainStyledAttributes.recycle();
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        RecyclerOnScrollListener recyclerOnScrollListener = this.mRecyclerOnScrollListener;
        if (recyclerOnScrollListener != null) {
            removeOnScrollListener(recyclerOnScrollListener);
            this.mRecyclerOnScrollListener = null;
        }
        super.onDetachedFromWindow();
    }

    public void setIndicatorColor(int color) {
        this.mIndicatorPaint.setColor(color);
    }

    public void setIndicatorHeight(int indicatorHeight) {
        this.mIndicatorHeight = indicatorHeight;
    }

    public void setAutoSelectionMode(boolean autoSelect) {
        RecyclerView.OnScrollListener onScrollListener = this.mRecyclerOnScrollListener;
        if (onScrollListener != null) {
            removeOnScrollListener(onScrollListener);
            this.mRecyclerOnScrollListener = null;
        }
        if (autoSelect) {
            RecyclerOnScrollListener recyclerOnScrollListener = new RecyclerOnScrollListener(this, this.mLinearLayoutManager);
            this.mRecyclerOnScrollListener = recyclerOnScrollListener;
            addOnScrollListener(recyclerOnScrollListener);
        }
    }

    public void setPositionThreshold(float positionThreshold) {
        this.mPositionThreshold = positionThreshold;
    }

    public void setUpWithViewPager(ViewPager2 viewPager, int fistDayOfYear) {
        DefaultAdapter defaultAdapter = new DefaultAdapter(viewPager, getContext(), fistDayOfYear);
        defaultAdapter.setTabPadding(this.mTabPaddingStart, this.mTabPaddingTop, this.mTabPaddingEnd, this.mTabPaddingBottom);
        defaultAdapter.setTabTextAppearance(this.mTabTextAppearance);
        defaultAdapter.setTabSelectedTextColor(this.mTabSelectedTextColorSet, this.mTabSelectedTextColor);
        defaultAdapter.setTabMaxWidth(this.mTabMaxWidth);
        defaultAdapter.setTabMinWidth(this.mTabMinWidth);
        defaultAdapter.setTabBackgroundResId(this.mTabBackgroundResId);
        defaultAdapter.setTabOnScreenLimit(this.mTabOnScreenLimit);
        setUpWithAdapter(defaultAdapter);
    }

    public void setUpWithAdapter(Adapter<?> adapter) {
        this.mAdapter = adapter;
        ViewPager2 viewPager = adapter.getViewPager();
        this.mViewPager = viewPager;
        if (viewPager.getAdapter() == null) {
            throw new IllegalArgumentException("ViewPager does not have a PagerAdapter set");
        }
        setAdapter(adapter);
        this.mViewPager.registerOnPageChangeCallback(new ViewPagerOnPageChangeListener(this));
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.RecyclerTabLayout.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                RecyclerTabLayout recyclerTabLayout = RecyclerTabLayout.this;
                recyclerTabLayout.scrollToTab(recyclerTabLayout.mViewPager.getCurrentItem());
                RecyclerTabLayout.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    public void setCurrentItem(int position, boolean smoothScroll) {
        ViewPager2 viewPager2 = this.mViewPager;
        if (viewPager2 != null) {
            viewPager2.setCurrentItem(position, smoothScroll);
            scrollToTab(this.mViewPager.getCurrentItem());
        } else if (smoothScroll && position != this.mIndicatorPosition) {
            startAnimation(position);
        } else {
            scrollToTab(position);
        }
    }

    protected void startAnimation(final int position) {
        ValueAnimator ofFloat;
        View findViewByPosition = this.mLinearLayoutManager.findViewByPosition(position);
        float abs = findViewByPosition != null ? Math.abs((getMeasuredWidth() / 2.0f) - (findViewByPosition.getX() + (findViewByPosition.getMeasuredWidth() / 2.0f))) / findViewByPosition.getMeasuredWidth() : 1.0f;
        if (position < this.mIndicatorPosition) {
            ofFloat = ValueAnimator.ofFloat(abs, 0.0f);
        } else {
            ofFloat = ValueAnimator.ofFloat(-abs, 0.0f);
        }
        ofFloat.setDuration(DEFAULT_SCROLL_DURATION);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.RecyclerTabLayout.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                RecyclerTabLayout.this.scrollToTab(position, ((Float) animation.getAnimatedValue()).floatValue(), true);
            }
        });
        ofFloat.start();
    }

    protected void scrollToTab(int position) {
        scrollToTab(position, 0.0f, false);
        this.mAdapter.setCurrentIndicatorPosition(position);
        this.mAdapter.notifyDataSetChanged();
    }

    protected void scrollToTab(int position, float positionOffset, boolean fitIndicator) {
        int i;
        int i2;
        int i3;
        View findViewByPosition = this.mLinearLayoutManager.findViewByPosition(position);
        View findViewByPosition2 = this.mLinearLayoutManager.findViewByPosition(position + 1);
        int i4 = 0;
        if (findViewByPosition != null) {
            int measuredWidth = getMeasuredWidth();
            float measuredWidth2 = position == 0 ? 0.0f : (measuredWidth / 2.0f) - (findViewByPosition.getMeasuredWidth() / 2.0f);
            float measuredWidth3 = findViewByPosition.getMeasuredWidth() + measuredWidth2;
            if (findViewByPosition2 != null) {
                float measuredWidth4 = (measuredWidth3 - ((measuredWidth / 2.0f) - (findViewByPosition2.getMeasuredWidth() / 2.0f))) * positionOffset;
                i = (int) (measuredWidth2 - measuredWidth4);
                if (position == 0) {
                    float measuredWidth5 = (findViewByPosition2.getMeasuredWidth() - findViewByPosition.getMeasuredWidth()) / 2;
                    this.mIndicatorGap = (int) (measuredWidth5 * positionOffset);
                    this.mIndicatorScroll = (int) ((findViewByPosition.getMeasuredWidth() + measuredWidth5) * positionOffset);
                } else {
                    this.mIndicatorGap = (int) (((findViewByPosition2.getMeasuredWidth() - findViewByPosition.getMeasuredWidth()) / 2) * positionOffset);
                    this.mIndicatorScroll = (int) measuredWidth4;
                }
            } else {
                i = (int) measuredWidth2;
                this.mIndicatorScroll = 0;
                this.mIndicatorGap = 0;
            }
            if (fitIndicator) {
                this.mIndicatorScroll = 0;
                this.mIndicatorGap = 0;
            }
        } else {
            if (getMeasuredWidth() > 0 && (i2 = this.mTabMaxWidth) > 0 && (i3 = this.mTabMinWidth) == i2) {
                i4 = ((int) ((-i3) * positionOffset)) + ((int) ((getMeasuredWidth() - i3) / 2.0f));
            }
            this.mRequestScrollToTab = true;
            i = i4;
        }
        updateCurrentIndicatorPosition(position, positionOffset - this.mOldPositionOffset, positionOffset);
        this.mIndicatorPosition = position;
        stopScroll();
        if (position != this.mOldPosition || i != this.mOldScrollOffset) {
            this.mLinearLayoutManager.scrollToPositionWithOffset(position, i);
        }
        if (this.mIndicatorHeight > 0) {
            invalidate();
        }
        this.mOldPosition = position;
        this.mOldScrollOffset = i;
        this.mOldPositionOffset = positionOffset;
    }

    protected void updateCurrentIndicatorPosition(int position, float dx, float positionOffset) {
        Adapter<?> adapter = this.mAdapter;
        if (adapter == null) {
            return;
        }
        if (dx > 0.0f && positionOffset >= this.mPositionThreshold - POSITION_THRESHOLD_ALLOWABLE) {
            position++;
        } else if (dx >= 0.0f || positionOffset > (1.0f - this.mPositionThreshold) + POSITION_THRESHOLD_ALLOWABLE) {
            position = -1;
        }
        if (position < 0 || position == adapter.getCurrentIndicatorPosition()) {
            return;
        }
        this.mAdapter.setCurrentIndicatorPosition(position);
        this.mAdapter.notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onDraw(Canvas canvas) {
        int left;
        int right;
        int i;
        View findViewByPosition = this.mLinearLayoutManager.findViewByPosition(this.mIndicatorPosition);
        if (findViewByPosition == null) {
            if (this.mRequestScrollToTab) {
                this.mRequestScrollToTab = false;
                scrollToTab(this.mViewPager.getCurrentItem());
                return;
            }
            return;
        }
        this.mRequestScrollToTab = false;
        if (isLayoutRtl()) {
            left = (findViewByPosition.getLeft() - this.mIndicatorScroll) - this.mIndicatorGap;
            right = findViewByPosition.getRight() - this.mIndicatorScroll;
            i = this.mIndicatorGap;
        } else {
            left = (findViewByPosition.getLeft() + this.mIndicatorScroll) - this.mIndicatorGap;
            right = findViewByPosition.getRight() + this.mIndicatorScroll;
            i = this.mIndicatorGap;
        }
        int i2 = right + i;
        int height = getHeight() - this.mIndicatorHeight;
        float height2 = height + (getHeight() - height);
        canvas.drawLine(left, height2, i2, height2, this.mIndicatorPaint);
    }

    protected boolean isLayoutRtl() {
        return ViewCompat.getLayoutDirection(this) == 1;
    }

    protected static class RecyclerOnScrollListener extends RecyclerView.OnScrollListener {
        public int mDx;
        protected LinearLayoutManager mLinearLayoutManager;
        protected RecyclerTabLayout mRecyclerTabLayout;

        public RecyclerOnScrollListener(RecyclerTabLayout recyclerTabLayout, LinearLayoutManager linearLayoutManager) {
            this.mRecyclerTabLayout = recyclerTabLayout;
            this.mLinearLayoutManager = linearLayoutManager;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            this.mDx += dx;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (newState != 0) {
                return;
            }
            if (this.mDx > 0) {
                selectCenterTabForRightScroll();
            } else {
                selectCenterTabForLeftScroll();
            }
            this.mDx = 0;
        }

        protected void selectCenterTabForRightScroll() {
            int findLastVisibleItemPosition = this.mLinearLayoutManager.findLastVisibleItemPosition();
            int width = this.mRecyclerTabLayout.getWidth() / 2;
            for (int findFirstVisibleItemPosition = this.mLinearLayoutManager.findFirstVisibleItemPosition(); findFirstVisibleItemPosition <= findLastVisibleItemPosition; findFirstVisibleItemPosition++) {
                View findViewByPosition = this.mLinearLayoutManager.findViewByPosition(findFirstVisibleItemPosition);
                if (findViewByPosition.getLeft() + findViewByPosition.getWidth() >= width) {
                    this.mRecyclerTabLayout.setCurrentItem(findFirstVisibleItemPosition, false);
                    return;
                }
            }
        }

        protected void selectCenterTabForLeftScroll() {
            int findFirstVisibleItemPosition = this.mLinearLayoutManager.findFirstVisibleItemPosition();
            int width = this.mRecyclerTabLayout.getWidth() / 2;
            for (int findLastVisibleItemPosition = this.mLinearLayoutManager.findLastVisibleItemPosition(); findLastVisibleItemPosition >= findFirstVisibleItemPosition; findLastVisibleItemPosition--) {
                if (this.mLinearLayoutManager.findViewByPosition(findLastVisibleItemPosition).getLeft() <= width) {
                    this.mRecyclerTabLayout.setCurrentItem(findLastVisibleItemPosition, false);
                    return;
                }
            }
        }
    }

    protected static class ViewPagerOnPageChangeListener extends ViewPager2.OnPageChangeCallback {
        private final RecyclerTabLayout mRecyclerTabLayout;
        private int mScrollState;

        public ViewPagerOnPageChangeListener(RecyclerTabLayout recyclerTabLayout) {
            this.mRecyclerTabLayout = recyclerTabLayout;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            this.mRecyclerTabLayout.scrollToTab(position, positionOffset, false);
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void onPageScrollStateChanged(int state) {
            this.mScrollState = state;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void onPageSelected(int position) {
            if (this.mScrollState != 0 || this.mRecyclerTabLayout.mIndicatorPosition == position) {
                return;
            }
            this.mRecyclerTabLayout.scrollToTab(position);
        }
    }

    public static abstract class Adapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {
        protected int mIndicatorPosition;
        protected ViewPager2 mViewPager;

        public Adapter(ViewPager2 viewPager) {
            this.mViewPager = viewPager;
        }

        public ViewPager2 getViewPager() {
            return this.mViewPager;
        }

        public void setCurrentIndicatorPosition(int indicatorPosition) {
            this.mIndicatorPosition = indicatorPosition;
        }

        public int getCurrentIndicatorPosition() {
            return this.mIndicatorPosition;
        }
    }

    public static class DefaultAdapter extends Adapter<ViewHolder> {
        protected static final int MAX_TAB_TEXT_LINES = 2;
        private String[] days;
        private int fistDayOfYear;
        private Typeface font;
        private int mTabBackgroundResId;
        private int mTabMaxWidth;
        private int mTabMinWidth;
        private int mTabOnScreenLimit;
        protected int mTabPaddingBottom;
        protected int mTabPaddingEnd;
        protected int mTabPaddingStart;
        protected int mTabPaddingTop;
        protected int mTabSelectedTextColor;
        protected boolean mTabSelectedTextColorSet;
        protected int mTabTextAppearance;

        public DefaultAdapter(ViewPager2 viewPager, Context context, int fistDayOfYear) {
            super(viewPager);
            this.days = context.getResources().getStringArray(R.array.days);
            this.font = ResourcesCompat.getFont(context, R.font.basefont_semibold);
            this.fistDayOfYear = fistDayOfYear;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TabTextView tabTextView = new TabTextView(parent.getContext());
            if (this.mTabSelectedTextColorSet) {
                tabTextView.setTextColor(tabTextView.createColorStateList(tabTextView.getCurrentTextColor(), this.mTabSelectedTextColor));
            }
            ViewCompat.setPaddingRelative(tabTextView, this.mTabPaddingStart, this.mTabPaddingTop, this.mTabPaddingEnd, this.mTabPaddingBottom);
            tabTextView.setTextAppearance(parent.getContext(), this.mTabTextAppearance);
            tabTextView.setGravity(17);
            tabTextView.setMaxLines(2);
            tabTextView.setEllipsize(TextUtils.TruncateAt.END);
            if (this.mTabOnScreenLimit > 0) {
                int measuredWidth = parent.getMeasuredWidth() / this.mTabOnScreenLimit;
                tabTextView.setMaxWidth(measuredWidth);
                tabTextView.setMinWidth(measuredWidth);
            } else {
                int i = this.mTabMaxWidth;
                if (i > 0) {
                    tabTextView.setMaxWidth(i);
                }
                tabTextView.setMinWidth(this.mTabMinWidth);
            }
            tabTextView.setTextAppearance(tabTextView.getContext(), this.mTabTextAppearance);
            if (this.mTabSelectedTextColorSet) {
                tabTextView.setTextColor(tabTextView.createColorStateList(tabTextView.getCurrentTextColor(), this.mTabSelectedTextColor));
            }
            if (this.mTabBackgroundResId != 0) {
                tabTextView.setBackgroundDrawable(AppCompatResources.getDrawable(tabTextView.getContext(), this.mTabBackgroundResId));
            }
            tabTextView.setLayoutParams(createLayoutParamsForTabs());
            tabTextView.setTypeface(this.font);
            return new ViewHolder(tabTextView);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder holder, int position) {
            String[] strArr = this.days;
            holder.title.setText(strArr[(this.fistDayOfYear + position) % strArr.length]);
            holder.title.setSelected(getCurrentIndicatorPosition() == position);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return getViewPager().getAdapter().getItemCount();
        }

        public void setTabPadding(int tabPaddingStart, int tabPaddingTop, int tabPaddingEnd, int tabPaddingBottom) {
            this.mTabPaddingStart = tabPaddingStart;
            this.mTabPaddingTop = tabPaddingTop;
            this.mTabPaddingEnd = tabPaddingEnd;
            this.mTabPaddingBottom = tabPaddingBottom;
        }

        public void setTabTextAppearance(int tabTextAppearance) {
            this.mTabTextAppearance = tabTextAppearance;
        }

        public void setTabSelectedTextColor(boolean tabSelectedTextColorSet, int tabSelectedTextColor) {
            this.mTabSelectedTextColorSet = tabSelectedTextColorSet;
            this.mTabSelectedTextColor = tabSelectedTextColor;
        }

        public void setTabMaxWidth(int tabMaxWidth) {
            this.mTabMaxWidth = tabMaxWidth;
        }

        public void setTabMinWidth(int tabMinWidth) {
            this.mTabMinWidth = tabMinWidth;
        }

        public void setTabBackgroundResId(int tabBackgroundResId) {
            this.mTabBackgroundResId = tabBackgroundResId;
        }

        public void setTabOnScreenLimit(int tabOnScreenLimit) {
            this.mTabOnScreenLimit = tabOnScreenLimit;
        }

        protected RecyclerView.LayoutParams createLayoutParamsForTabs() {
            return new RecyclerView.LayoutParams(-2, -1);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView title;

            public ViewHolder(View itemView) {
                super(itemView);
                this.title = (TextView) itemView;
                itemView.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.RecyclerTabLayout.DefaultAdapter.ViewHolder.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        int adapterPosition = ViewHolder.this.getAdapterPosition();
                        if (adapterPosition != -1) {
                            DefaultAdapter.this.getViewPager().setCurrentItem(adapterPosition, true);
                        }
                    }
                });
            }
        }
    }

    public static class TabTextView extends AppCompatTextView {
        public TabTextView(Context context) {
            super(context);
        }

        public ColorStateList createColorStateList(int defaultColor, int selectedColor) {
            return new ColorStateList(new int[][]{SELECTED_STATE_SET, EMPTY_STATE_SET}, new int[]{selectedColor, defaultColor});
        }
    }
}
