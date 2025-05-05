package com.m_myr.nuwm.nuwmschedule.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import java.util.HashMap;
import java.util.Random;

/* loaded from: classes2.dex */
public final class ResourcesHelper {
    public static int getAttrColor(Fragment fragment, int themeAttributeId) {
        return getAttrColor(fragment.getContext(), themeAttributeId);
    }

    public static int getValueOfAttr(Context context, int themeAttributeId) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(themeAttributeId, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
        }
        throw new RuntimeException("Cannot get attr value of" + themeAttributeId);
    }

    public static int getAttrColor(Context context, int themeAttributeId) {
        if (context == null) {
            context = App.getInstance();
        }
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(themeAttributeId, typedValue, true)) {
            return ContextCompat.getColor(context, typedValue.resourceId);
        }
        return context.getResources().getColor(R.color.colorPrimary);
    }

    public static int getAttrColor(Context context, String attrName) {
        if (context == null) {
            context = App.getInstance();
        }
        int identifier = context.getResources().getIdentifier(attrName, "attr", context.getPackageName());
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(identifier, typedValue, true)) {
            return ContextCompat.getColor(context, typedValue.resourceId);
        }
        return context.getResources().getColor(R.color.colorPrimary);
    }

    public static TypedValue resolveAttribute(Context context, int attr) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attr, typedValue, true);
        return typedValue;
    }

    public static int dpToPx(Context context, int dp) {
        return Math.round(TypedValue.applyDimension(1, dp, context.getResources().getDisplayMetrics()));
    }

    public static Drawable getAttrDrawable(Context context, int themeAttributeId) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(themeAttributeId, typedValue, true)) {
            return ContextCompat.getDrawable(context, typedValue.resourceId);
        }
        return null;
    }

    public static boolean getIsNightMode(Context context) {
        if ((context.getResources().getConfiguration().uiMode & 48) != 32) {
            return false;
        }
        return App.appThemeResource == Constant.getThemeResource(1) || App.appThemeResource == Constant.getThemeResource(2);
    }

    public static Integer getColorByName(Context context, String substring) {
        if (substring.equals("random")) {
            return Integer.valueOf(context.getResources().getColor(new int[]{R.color.ext_palette1, R.color.ext_palette2, R.color.ext_palette3, R.color.ext_palette4, R.color.ext_palette5, R.color.ext_palette6, R.color.ext_palette7, R.color.md_theme_dark_error, R.color.ext_palette8, R.color.ext_palette9, R.color.ext_palette10, R.color.purpleDark, R.color.red_normal, R.color.md_theme_light_primaryInverse, R.color.tealDark, R.color.greenDark, R.color.orangeDark, R.color.base_ext_palette3, R.color.studentColor}[new Random().nextInt(19)]));
        }
        HashMap hashMap = new HashMap();
        hashMap.put("colorButtonNormal", Integer.valueOf(R.attr.colorButtonNormal));
        hashMap.put("colorPrimaryDark", Integer.valueOf(R.attr.colorPrimaryDark));
        hashMap.put("colorAccentLight", Integer.valueOf(R.attr.colorAccentLight));
        hashMap.put("colorAccent", Integer.valueOf(R.attr.colorAccent));
        hashMap.put("colorControlNormal", Integer.valueOf(R.attr.colorControlNormal));
        hashMap.put("colorTint", Integer.valueOf(R.attr.colorTint));
        hashMap.put("colorInnerIconDark", Integer.valueOf(R.attr.colorInnerIconDark));
        hashMap.put("colorBackgroundSub", Integer.valueOf(R.attr.colorBackgroundSub));
        hashMap.put("colorBackgroundTop", Integer.valueOf(R.attr.colorBackgroundTop));
        hashMap.put("colorBackgroundView", Integer.valueOf(R.attr.colorBackgroundView));
        hashMap.put("colorTextTitle", Integer.valueOf(R.attr.colorTextTitle));
        hashMap.put("colorTextMain", Integer.valueOf(R.attr.colorTextMain));
        hashMap.put("colorTextSub", Integer.valueOf(R.attr.colorTextSub));
        hashMap.put("colorPrimary", Integer.valueOf(R.attr.colorPrimary));
        hashMap.put("colorPrimaryContainer", Integer.valueOf(R.attr.colorPrimaryContainer));
        hashMap.put("colorOnPrimaryContainer", Integer.valueOf(R.attr.colorOnPrimaryContainer));
        hashMap.put("colorSecondary", Integer.valueOf(R.attr.colorSecondary));
        hashMap.put("colorOnSecondary", Integer.valueOf(R.attr.colorOnSecondary));
        hashMap.put("colorSecondaryContainer", Integer.valueOf(R.attr.colorSecondaryContainer));
        hashMap.put("colorOnSecondaryContainer", Integer.valueOf(R.attr.colorOnSecondaryContainer));
        hashMap.put("colorOnSurface", Integer.valueOf(R.attr.colorOnSurface));
        hashMap.put("colorSurfaceVariant", Integer.valueOf(R.attr.colorSurfaceVariant));
        hashMap.put("colorOnSurfaceVariant", Integer.valueOf(R.attr.colorOnSurfaceVariant));
        hashMap.put("colorOutline", Integer.valueOf(R.attr.colorOutline));
        hashMap.put("colorOnSurfaceInverse", Integer.valueOf(R.attr.colorOnSurfaceInverse));
        hashMap.put("colorSurfaceInverse", Integer.valueOf(R.attr.colorSurfaceInverse));
        hashMap.put("colorAvailable", Integer.valueOf(R.attr.colorAvailable));
        hashMap.put("colorMonochromaticBlue", Integer.valueOf(R.attr.colorMonochromaticBlue));
        Integer num = (Integer) hashMap.get(substring);
        if (num != null) {
            return Integer.valueOf(getAttrColor(context, num.intValue()));
        }
        return 0;
    }
}
