package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.R;

/* loaded from: classes2.dex */
public class ViewUtils {

    public interface OnApplyWindowInsetsListener {
        WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat, RelativePadding relativePadding);
    }

    private ViewUtils() {
    }

    public static PorterDuff.Mode parseTintMode(int i, PorterDuff.Mode mode) {
        if (i == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }

    public static boolean isLayoutRtl(View view) {
        return ViewCompat.getLayoutDirection(view) == 1;
    }

    public static float dpToPx(Context context, int i) {
        return TypedValue.applyDimension(1, i, context.getResources().getDisplayMetrics());
    }

    public static void requestFocusAndShowKeyboard(View view) {
        view.requestFocus();
        view.post(new Runnable() { // from class: com.google.android.material.internal.ViewUtils.1
            final /* synthetic */ View val$view;

            AnonymousClass1(View view2) {
                r1 = view2;
            }

            @Override // java.lang.Runnable
            public void run() {
                ((InputMethodManager) r1.getContext().getSystemService("input_method")).showSoftInput(r1, 1);
            }
        });
    }

    /* renamed from: com.google.android.material.internal.ViewUtils$1 */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ View val$view;

        AnonymousClass1(View view2) {
            r1 = view2;
        }

        @Override // java.lang.Runnable
        public void run() {
            ((InputMethodManager) r1.getContext().getSystemService("input_method")).showSoftInput(r1, 1);
        }
    }

    public static class RelativePadding {
        public int bottom;
        public int end;
        public int start;
        public int top;

        public RelativePadding(int i, int i2, int i3, int i4) {
            this.start = i;
            this.top = i2;
            this.end = i3;
            this.bottom = i4;
        }

        public RelativePadding(RelativePadding relativePadding) {
            this.start = relativePadding.start;
            this.top = relativePadding.top;
            this.end = relativePadding.end;
            this.bottom = relativePadding.bottom;
        }

        public void applyToView(View view) {
            ViewCompat.setPaddingRelative(view, this.start, this.top, this.end, this.bottom);
        }
    }

    public static void doOnApplyWindowInsets(View view, AttributeSet attributeSet, int i, int i2) {
        doOnApplyWindowInsets(view, attributeSet, i, i2, null);
    }

    public static void doOnApplyWindowInsets(View view, AttributeSet attributeSet, int i, int i2, OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        TypedArray obtainStyledAttributes = view.getContext().obtainStyledAttributes(attributeSet, R.styleable.Insets, i, i2);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.Insets_paddingBottomSystemWindowInsets, false);
        boolean z2 = obtainStyledAttributes.getBoolean(R.styleable.Insets_paddingLeftSystemWindowInsets, false);
        boolean z3 = obtainStyledAttributes.getBoolean(R.styleable.Insets_paddingRightSystemWindowInsets, false);
        obtainStyledAttributes.recycle();
        doOnApplyWindowInsets(view, new OnApplyWindowInsetsListener() { // from class: com.google.android.material.internal.ViewUtils.2
            final /* synthetic */ OnApplyWindowInsetsListener val$listener;
            final /* synthetic */ boolean val$paddingBottomSystemWindowInsets;
            final /* synthetic */ boolean val$paddingLeftSystemWindowInsets;
            final /* synthetic */ boolean val$paddingRightSystemWindowInsets;

            AnonymousClass2(boolean z4, boolean z22, boolean z32, OnApplyWindowInsetsListener onApplyWindowInsetsListener2) {
                r1 = z4;
                r2 = z22;
                r3 = z32;
                r4 = onApplyWindowInsetsListener2;
            }

            @Override // com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener
            public WindowInsetsCompat onApplyWindowInsets(View view2, WindowInsetsCompat windowInsetsCompat, RelativePadding relativePadding) {
                if (r1) {
                    relativePadding.bottom += windowInsetsCompat.getSystemWindowInsetBottom();
                }
                boolean isLayoutRtl = ViewUtils.isLayoutRtl(view2);
                if (r2) {
                    if (isLayoutRtl) {
                        relativePadding.end += windowInsetsCompat.getSystemWindowInsetLeft();
                    } else {
                        relativePadding.start += windowInsetsCompat.getSystemWindowInsetLeft();
                    }
                }
                if (r3) {
                    if (isLayoutRtl) {
                        relativePadding.start += windowInsetsCompat.getSystemWindowInsetRight();
                    } else {
                        relativePadding.end += windowInsetsCompat.getSystemWindowInsetRight();
                    }
                }
                relativePadding.applyToView(view2);
                OnApplyWindowInsetsListener onApplyWindowInsetsListener2 = r4;
                return onApplyWindowInsetsListener2 != null ? onApplyWindowInsetsListener2.onApplyWindowInsets(view2, windowInsetsCompat, relativePadding) : windowInsetsCompat;
            }
        });
    }

    /* renamed from: com.google.android.material.internal.ViewUtils$2 */
    class AnonymousClass2 implements OnApplyWindowInsetsListener {
        final /* synthetic */ OnApplyWindowInsetsListener val$listener;
        final /* synthetic */ boolean val$paddingBottomSystemWindowInsets;
        final /* synthetic */ boolean val$paddingLeftSystemWindowInsets;
        final /* synthetic */ boolean val$paddingRightSystemWindowInsets;

        AnonymousClass2(boolean z4, boolean z22, boolean z32, OnApplyWindowInsetsListener onApplyWindowInsetsListener2) {
            r1 = z4;
            r2 = z22;
            r3 = z32;
            r4 = onApplyWindowInsetsListener2;
        }

        @Override // com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View view2, WindowInsetsCompat windowInsetsCompat, RelativePadding relativePadding) {
            if (r1) {
                relativePadding.bottom += windowInsetsCompat.getSystemWindowInsetBottom();
            }
            boolean isLayoutRtl = ViewUtils.isLayoutRtl(view2);
            if (r2) {
                if (isLayoutRtl) {
                    relativePadding.end += windowInsetsCompat.getSystemWindowInsetLeft();
                } else {
                    relativePadding.start += windowInsetsCompat.getSystemWindowInsetLeft();
                }
            }
            if (r3) {
                if (isLayoutRtl) {
                    relativePadding.start += windowInsetsCompat.getSystemWindowInsetRight();
                } else {
                    relativePadding.end += windowInsetsCompat.getSystemWindowInsetRight();
                }
            }
            relativePadding.applyToView(view2);
            OnApplyWindowInsetsListener onApplyWindowInsetsListener2 = r4;
            return onApplyWindowInsetsListener2 != null ? onApplyWindowInsetsListener2.onApplyWindowInsets(view2, windowInsetsCompat, relativePadding) : windowInsetsCompat;
        }
    }

    public static void doOnApplyWindowInsets(View view, OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        ViewCompat.setOnApplyWindowInsetsListener(view, new androidx.core.view.OnApplyWindowInsetsListener() { // from class: com.google.android.material.internal.ViewUtils.3
            final /* synthetic */ RelativePadding val$initialPadding;

            AnonymousClass3(RelativePadding relativePadding) {
                r2 = relativePadding;
            }

            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public WindowInsetsCompat onApplyWindowInsets(View view2, WindowInsetsCompat windowInsetsCompat) {
                return OnApplyWindowInsetsListener.this.onApplyWindowInsets(view2, windowInsetsCompat, new RelativePadding(r2));
            }
        });
        requestApplyInsetsWhenAttached(view);
    }

    /* renamed from: com.google.android.material.internal.ViewUtils$3 */
    class AnonymousClass3 implements androidx.core.view.OnApplyWindowInsetsListener {
        final /* synthetic */ RelativePadding val$initialPadding;

        AnonymousClass3(RelativePadding relativePadding) {
            r2 = relativePadding;
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View view2, WindowInsetsCompat windowInsetsCompat) {
            return OnApplyWindowInsetsListener.this.onApplyWindowInsets(view2, windowInsetsCompat, new RelativePadding(r2));
        }
    }

    public static void requestApplyInsetsWhenAttached(View view) {
        if (ViewCompat.isAttachedToWindow(view)) {
            ViewCompat.requestApplyInsets(view);
        } else {
            view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.google.android.material.internal.ViewUtils.4
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View view2) {
                }

                AnonymousClass4() {
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(View view2) {
                    view2.removeOnAttachStateChangeListener(this);
                    ViewCompat.requestApplyInsets(view2);
                }
            });
        }
    }

    /* renamed from: com.google.android.material.internal.ViewUtils$4 */
    class AnonymousClass4 implements View.OnAttachStateChangeListener {
        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view2) {
        }

        AnonymousClass4() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view2) {
            view2.removeOnAttachStateChangeListener(this);
            ViewCompat.requestApplyInsets(view2);
        }
    }

    public static float getParentAbsoluteElevation(View view) {
        float f = 0.0f;
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            f += ViewCompat.getElevation((View) parent);
        }
        return f;
    }

    public static ViewOverlayImpl getOverlay(View view) {
        if (view == null) {
            return null;
        }
        return new ViewOverlayApi18(view);
    }

    public static ViewGroup getContentView(View view) {
        if (view == null) {
            return null;
        }
        View rootView = view.getRootView();
        ViewGroup viewGroup = (ViewGroup) rootView.findViewById(android.R.id.content);
        if (viewGroup != null) {
            return viewGroup;
        }
        if (rootView == view || !(rootView instanceof ViewGroup)) {
            return null;
        }
        return (ViewGroup) rootView;
    }

    public static ViewOverlayImpl getContentViewOverlay(View view) {
        return getOverlay(getContentView(view));
    }

    public static void addOnGlobalLayoutListener(View view, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (view != null) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    public static void removeOnGlobalLayoutListener(View view, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (view != null) {
            removeOnGlobalLayoutListener(view.getViewTreeObserver(), onGlobalLayoutListener);
        }
    }

    public static void removeOnGlobalLayoutListener(ViewTreeObserver viewTreeObserver, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener);
    }

    public static Integer getBackgroundColor(View view) {
        if (view.getBackground() instanceof ColorDrawable) {
            return Integer.valueOf(((ColorDrawable) view.getBackground()).getColor());
        }
        return null;
    }
}
