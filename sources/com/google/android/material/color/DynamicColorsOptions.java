package com.google.android.material.color;

import android.app.Activity;
import com.google.android.material.color.DynamicColors;

/* loaded from: classes2.dex */
public class DynamicColorsOptions {
    private static final DynamicColors.Precondition ALWAYS_ALLOW = new DynamicColors.Precondition() { // from class: com.google.android.material.color.DynamicColorsOptions.1
        @Override // com.google.android.material.color.DynamicColors.Precondition
        public boolean shouldApplyDynamicColors(Activity activity, int i) {
            return true;
        }

        AnonymousClass1() {
        }
    };
    private static final DynamicColors.OnAppliedCallback NO_OP_CALLBACK = new DynamicColors.OnAppliedCallback() { // from class: com.google.android.material.color.DynamicColorsOptions.2
        @Override // com.google.android.material.color.DynamicColors.OnAppliedCallback
        public void onApplied(Activity activity) {
        }

        AnonymousClass2() {
        }
    };
    private final DynamicColors.OnAppliedCallback onAppliedCallback;
    private final DynamicColors.Precondition precondition;
    private final int themeOverlay;

    /* synthetic */ DynamicColorsOptions(Builder builder, AnonymousClass1 anonymousClass1) {
        this(builder);
    }

    /* renamed from: com.google.android.material.color.DynamicColorsOptions$1 */
    class AnonymousClass1 implements DynamicColors.Precondition {
        @Override // com.google.android.material.color.DynamicColors.Precondition
        public boolean shouldApplyDynamicColors(Activity activity, int i) {
            return true;
        }

        AnonymousClass1() {
        }
    }

    /* renamed from: com.google.android.material.color.DynamicColorsOptions$2 */
    class AnonymousClass2 implements DynamicColors.OnAppliedCallback {
        @Override // com.google.android.material.color.DynamicColors.OnAppliedCallback
        public void onApplied(Activity activity) {
        }

        AnonymousClass2() {
        }
    }

    private DynamicColorsOptions(Builder builder) {
        this.themeOverlay = builder.themeOverlay;
        this.precondition = builder.precondition;
        this.onAppliedCallback = builder.onAppliedCallback;
    }

    public int getThemeOverlay() {
        return this.themeOverlay;
    }

    public DynamicColors.Precondition getPrecondition() {
        return this.precondition;
    }

    public DynamicColors.OnAppliedCallback getOnAppliedCallback() {
        return this.onAppliedCallback;
    }

    public static class Builder {
        private int themeOverlay;
        private DynamicColors.Precondition precondition = DynamicColorsOptions.ALWAYS_ALLOW;
        private DynamicColors.OnAppliedCallback onAppliedCallback = DynamicColorsOptions.NO_OP_CALLBACK;

        public Builder setThemeOverlay(int i) {
            this.themeOverlay = i;
            return this;
        }

        public Builder setPrecondition(DynamicColors.Precondition precondition) {
            this.precondition = precondition;
            return this;
        }

        public Builder setOnAppliedCallback(DynamicColors.OnAppliedCallback onAppliedCallback) {
            this.onAppliedCallback = onAppliedCallback;
            return this;
        }

        public DynamicColorsOptions build() {
            return new DynamicColorsOptions(this);
        }
    }
}
