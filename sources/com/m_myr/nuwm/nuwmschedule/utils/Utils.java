package com.m_myr.nuwm.nuwmschedule.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Insets;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.WindowInsets;
import android.view.WindowMetrics;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.common.collect.Lists;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.DataUniqueId;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.FieldGetter;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import j$.util.Objects;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.mortbay.jetty.MimeTypes;

/* loaded from: classes2.dex */
public final class Utils {
    public static boolean isEmpty(ArrayList o) {
        return o == null || o.isEmpty();
    }

    public static void createAlertInfo(Context context, String HINT_eventAdd, int res) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Довідка");
        builder.setMessage(res);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.utils.Utils.1
            final /* synthetic */ String val$HINT_eventAdd;

            AnonymousClass1(String HINT_eventAdd2) {
                val$HINT_eventAdd = HINT_eventAdd2;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                AppDataManager.getInstance().setHideHint(val$HINT_eventAdd);
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.utils.Utils$1 */
    class AnonymousClass1 implements DialogInterface.OnClickListener {
        final /* synthetic */ String val$HINT_eventAdd;

        AnonymousClass1(String HINT_eventAdd2) {
            val$HINT_eventAdd = HINT_eventAdd2;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialog, int which) {
            AppDataManager.getInstance().setHideHint(val$HINT_eventAdd);
        }
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static float dpToPxRaw(float dp) {
        return dp * Resources.getSystem().getDisplayMetrics().density;
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static String md5(String st) {
        byte[] bArr = new byte[0];
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            bArr = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String bigInteger = new BigInteger(1, bArr).toString(16);
        while (bigInteger.length() < 32) {
            bigInteger = "0" + bigInteger;
        }
        return bigInteger;
    }

    public static void showStub(Context context) {
        showStub(context, "Ця функція стане доступною у наступній версії");
    }

    public static Toast getStub(Context context) {
        return getStub(context, "Ця функція стане доступною у наступній версії");
    }

    public static Toast getStub(Context context, String text) {
        return Toast.makeText(context, text, 0);
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.utils.Utils$2 */
    class AnonymousClass2 extends CountDownTimer {
        final /* synthetic */ Toast val$mToastToShow;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(long millisInFuture, long countDownInterval, final Toast val$mToastToShow) {
            super(millisInFuture, countDownInterval);
            val$mToastToShow = val$mToastToShow;
        }

        @Override // android.os.CountDownTimer
        public void onTick(long millisUntilFinished) {
            val$mToastToShow.show();
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            val$mToastToShow.cancel();
        }
    }

    public static void showStub(Context context, String text) {
        Toast stub = getStub(context, text);
        AnonymousClass2 anonymousClass2 = new CountDownTimer(500, 500L) { // from class: com.m_myr.nuwm.nuwmschedule.utils.Utils.2
            final /* synthetic */ Toast val$mToastToShow;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(long millisInFuture, long countDownInterval, Toast stub2) {
                super(millisInFuture, countDownInterval);
                val$mToastToShow = stub2;
            }

            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                val$mToastToShow.show();
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                val$mToastToShow.cancel();
            }
        };
        stub2.show();
        anonymousClass2.start();
    }

    public static boolean timeInterval(long time, TimeUnit hours, int i) {
        return Math.abs((System.currentTimeMillis() / 1000) - time) > hours.toSeconds((long) i);
    }

    public static int getColorOfId(long s) {
        return new int[]{-1739917, -1023342, -4560696, -6982195, -8812853, -10177034, -11549705, -11677471, -11684180, -8271996, -5319295, -2300043, -141259, -18611, -4412764, -4412764}[(int) (s % 15)];
    }

    public static int getRandomColor() {
        return new int[]{-1739917, -1023342, -4560696, -6982195, -8812853, -10177034, -11549705, -11677471, -11684180, -8271996, -5319295, -2300043, -141259, -18611, -4412764}[new Random().nextInt(15)];
    }

    public static int getPrettyCount(int downloads) {
        if (downloads == 0) {
            return 0;
        }
        if (downloads < 10) {
            return 1;
        }
        if (downloads < 50) {
            return 10;
        }
        if (downloads < 100) {
            return 50;
        }
        if (downloads < 200) {
            return 100;
        }
        if (downloads < 500) {
            return 200;
        }
        if (downloads < 800) {
            return 500;
        }
        if (downloads < 1000) {
            return 800;
        }
        if (downloads < 2000) {
            return 1000;
        }
        if (downloads < 3000) {
            return 2000;
        }
        if (downloads < 5000) {
            return 3000;
        }
        if (downloads < 8000) {
            return 5000;
        }
        if (downloads < 10000) {
            return 8000;
        }
        if (downloads < 15000) {
            return 10000;
        }
        if (downloads < 20000) {
            return 15000;
        }
        return (downloads / 10000) * 10000;
    }

    public static void sendAnalytic(String event) {
        FirebaseAnalytics.getInstance(App.getInstance()).logEvent(event, null);
    }

    public static void sendAnalytic(String event, Pair<String, Object>... args) {
        sendAnalytic(App.getInstance(), event, args);
    }

    public static void sendAnalytic(Context context, String event, Pair<String, Object>... args) {
        Bundle bundle = new Bundle();
        for (Pair<String, Object> pair : args) {
            if (pair.second instanceof String) {
                bundle.putString((String) pair.first, (String) pair.second);
            }
            if (pair.second instanceof String) {
                bundle.putString((String) pair.first, String.valueOf(pair.second));
            }
        }
        FirebaseAnalytics.getInstance(App.getInstance()).logEvent(event, bundle);
    }

    public static final class StringUtils {
        public static String elvis(String a, String b) {
            return a != null ? a : b;
        }

        public static boolean isWhitespace(int c) {
            return c == 32 || c == 9 || c == 10 || c == 12 || c == 13 || c == 160;
        }

        static /* synthetic */ String lambda$joinString$0(String str) {
            return str;
        }

        static /* synthetic */ String lambda$joinString$1(String str) {
            return str;
        }

        public static String units(int num, String a, String b) {
            return units(num, a, b, b);
        }

        public static String units(int num, String a, String b, String c) {
            int i;
            int i2 = num % 10;
            return (i2 != 1 || num % 100 == 11) ? (i2 < 2 || i2 > 4 || ((i = num % 100) >= 10 && i < 20)) ? c : b : a;
        }

        public static String unitsFormat(String str, int num, String a, String b, String c) {
            return str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + unitsFormat(num, a, b, c);
        }

        public static String unitsFormat(int num, String a, String b, String c) {
            return num + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + units(num, a, b, c);
        }

        public static boolean isBlank(String string) {
            if (string != null && string.length() != 0) {
                int length = string.length();
                for (int i = 0; i < length; i++) {
                    if (!isWhitespace(string.codePointAt(i))) {
                        return false;
                    }
                }
            }
            return true;
        }

        public static String joinString(CharSequence delimiter, ArrayList<String> v) {
            return join(delimiter, v, new FieldGetter() { // from class: com.m_myr.nuwm.nuwmschedule.utils.Utils$StringUtils$$ExternalSyntheticLambda1
                @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.FieldGetter
                public final String getFieldToString(Object obj) {
                    return Utils.StringUtils.lambda$joinString$0((String) obj);
                }
            });
        }

        public static String joinString(CharSequence delimiter, List<String> v) {
            return join(delimiter, v, new FieldGetter() { // from class: com.m_myr.nuwm.nuwmschedule.utils.Utils$StringUtils$$ExternalSyntheticLambda0
                @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.FieldGetter
                public final String getFieldToString(Object obj) {
                    return Utils.StringUtils.lambda$joinString$1((String) obj);
                }
            });
        }

        public static String joinIterableString(CharSequence delimiter, Iterable<String> v) {
            return joinString(delimiter, (ArrayList<String>) Lists.newArrayList(v));
        }

        public static String join(CharSequence delimiter, List<? extends DataUniqueId> v) {
            Objects.requireNonNull(delimiter);
            Objects.requireNonNull(v);
            if (v.size() == 0) {
                return "";
            }
            if (v.size() == 1) {
                return String.valueOf(v.get(0).getId());
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < v.size() - 1; i++) {
                sb.append(v.get(i).getId());
                sb.append(delimiter);
            }
            sb.append(v.get(v.size() - 1).getId());
            return sb.toString();
        }

        public static <T> String join(CharSequence delimiter, List<T> v, FieldGetter<T> fieldGetter) {
            Objects.requireNonNull(delimiter);
            Objects.requireNonNull(v);
            if (v.size() == 0) {
                return "";
            }
            if (v.size() == 1) {
                return fieldGetter.getFieldToString(v.get(0));
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < v.size() - 1; i++) {
                sb.append(fieldGetter.getFieldToString(v.get(i)));
                sb.append(delimiter);
            }
            sb.append(fieldGetter.getFieldToString(v.get(v.size() - 1)));
            return sb.toString();
        }

        public static String substrElips(String description, int i) {
            if (description == null) {
                return "...";
            }
            if (description.length() < i) {
                return description + "...";
            }
            return description.substring(0, i) + "...";
        }

        public static boolean isEmail(String email) {
            return Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}").matcher(email).matches();
        }

        public static float safeToFloat(String firstMark) {
            try {
                return Float.parseFloat(firstMark);
            } catch (Exception unused) {
                return 0.0f;
            }
        }

        public static boolean safeEqual(String a, String b) {
            if (a == b) {
                return true;
            }
            if (a == null || b == null) {
                return false;
            }
            return a.equals(b);
        }

        public static String safeTrim(String str) {
            if (str == null) {
                return "";
            }
            return str.trim();
        }

        public static int safeToInt(String tr, int fallback) {
            try {
                return Integer.parseInt(tr);
            } catch (Exception unused) {
                return fallback;
            }
        }

        public static String getHumanShortTime(Date date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            if (DateUtils.isToday(date.getTime())) {
                return DateFormat.format("HH:mm", calendar).toString();
            }
            if (Utils.isYesterday(date)) {
                return "Вчора";
            }
            if (Utils.isBeforeYesterday(date)) {
                return "Позавчора";
            }
            if (TimeUnit.DAYS.convert(new Date().getTime() - date.getTime(), TimeUnit.MILLISECONDS) < 6) {
                return DateFormat.format("EEEE", calendar).toString();
            }
            if (Calendar.getInstance().get(1) == calendar.get(1)) {
                return DateFormat.format("d MMMM", calendar).toString();
            }
            return DateFormat.format("MM.yyyy", calendar).toString();
        }
    }

    public static int getScreenWidth(Activity activity) {
        if (Build.VERSION.SDK_INT >= 30) {
            WindowMetrics currentWindowMetrics = activity.getWindowManager().getCurrentWindowMetrics();
            Insets insetsIgnoringVisibility = currentWindowMetrics.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());
            return (currentWindowMetrics.getBounds().width() - insetsIgnoringVisibility.left) - insetsIgnoringVisibility.right;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static boolean isYesterday(Date d) {
        return DateUtils.isToday(d.getTime() + 86400000);
    }

    public static boolean isBeforeYesterday(Date d) {
        return DateUtils.isToday(d.getTime() + 172800000);
    }

    public static int calculateNoOfColumns(Context context) {
        int screenOrientation = getScreenOrientation(context);
        return isTablet(context) ? screenOrientation == 1 ? 2 : 3 : screenOrientation == 1 ? 1 : 2;
    }

    public static int getScreenOrientation(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels < context.getResources().getDisplayMetrics().heightPixels ? 1 : 2;
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    public static boolean contentTypeIsPicture(String mime) {
        return mime.equals("gif") || mime.equals("png") || mime.equals("jpg") || mime.equals("image/jpeg") || mime.equals("image/png") || mime.equals("image/gif");
    }

    public static String resolveContentType(String mime) {
        if (mime == null) {
            return null;
        }
        if (mime.equals("gif") || mime.equals("png")) {
            return "image/" + mime;
        }
        if (mime.equals("jpg")) {
            return "image/jpeg";
        }
        if (mime.equals("zip") || mime.equals("pdf")) {
            return "application/" + mime;
        }
        if (mime.equals("xlsx")) {
            return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        }
        if (mime.equals("xls")) {
            return "application/vnd.ms-excel";
        }
        if (mime.equals("doc")) {
            return "application/msword";
        }
        if (mime.equals("docx")) {
            return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        }
        if (mime.equals("csv")) {
            return "text/csv";
        }
        if (mime.equals("txt")) {
            return MimeTypes.TEXT_PLAIN;
        }
        if (mime.equals("rar")) {
            return "application/x-rar-compressed";
        }
        return null;
    }

    public static String resolveContentName(String mime) {
        if (mime == null) {
            return "Документ";
        }
        if (contentTypeIsPicture(mime)) {
            return "Зображення " + mime;
        }
        if (mime.equals("pdf")) {
            return "Документ PDF";
        }
        if (mime.equals("zip") || mime.equals("rar")) {
            return "Архів ZIP";
        }
        if (mime.equals("xlsx") || mime.equals("xls")) {
            return "Таблиці Microsoft Excel";
        }
        if (mime.equals("doc") || mime.equals("docx")) {
            return "Документ Microsoft Word";
        }
        if (mime.equals("csv")) {
            return "Файл CSV";
        }
        if (!mime.equals("txt")) {
            return "Документ";
        }
        return "Текстовий документ";
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.utils.Utils$3 */
    class AnonymousClass3 extends TypeToken<ArrayList<? extends ItemTimetableContract>> {
        AnonymousClass3() {
        }
    }

    public static Gson getGsonTimetable() {
        new TypeToken<ArrayList<? extends ItemTimetableContract>>() { // from class: com.m_myr.nuwm.nuwmschedule.utils.Utils.3
            AnonymousClass3() {
            }
        }.getType();
        return new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer() { // from class: com.m_myr.nuwm.nuwmschedule.utils.Utils$$ExternalSyntheticLambda2
            @Override // com.google.gson.JsonDeserializer
            public final Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return Utils.lambda$getGsonTimetable$0(jsonElement, type, jsonDeserializationContext);
            }
        }).registerTypeAdapter(Date.class, new JsonSerializer() { // from class: com.m_myr.nuwm.nuwmschedule.utils.Utils$$ExternalSyntheticLambda3
            @Override // com.google.gson.JsonSerializer
            public final JsonElement serialize(Object obj, Type type, JsonSerializationContext jsonSerializationContext) {
                return Utils.lambda$getGsonTimetable$1((Date) obj, type, jsonSerializationContext);
            }
        }).registerTypeHierarchyAdapter(new ArrayList().getClass(), new InterfaceAdapter()).create();
    }

    static /* synthetic */ Date lambda$getGsonTimetable$0(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return new Date(jsonElement.getAsJsonPrimitive().getAsLong() * 1000);
    }

    static /* synthetic */ JsonElement lambda$getGsonTimetable$1(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(Long.valueOf(date.getTime() / 1000));
    }

    public static Gson getGson() {
        return new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer() { // from class: com.m_myr.nuwm.nuwmschedule.utils.Utils$$ExternalSyntheticLambda0
            @Override // com.google.gson.JsonDeserializer
            public final Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return Utils.lambda$getGson$2(jsonElement, type, jsonDeserializationContext);
            }
        }).registerTypeAdapter(Date.class, new JsonSerializer() { // from class: com.m_myr.nuwm.nuwmschedule.utils.Utils$$ExternalSyntheticLambda1
            @Override // com.google.gson.JsonSerializer
            public final JsonElement serialize(Object obj, Type type, JsonSerializationContext jsonSerializationContext) {
                return Utils.lambda$getGson$3((Date) obj, type, jsonSerializationContext);
            }
        }).create();
    }

    static /* synthetic */ Date lambda$getGson$2(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return new Date(jsonElement.getAsJsonPrimitive().getAsLong() * 1000);
    }

    static /* synthetic */ JsonElement lambda$getGson$3(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(Long.valueOf(date.getTime() / 1000));
    }

    public static class DataCompat {
        public static String getSex(String sex) {
            if (sex == null) {
                return "unknown_sex";
            }
            String lowerCase = sex.trim().replace(".", "").toLowerCase();
            return "чол".equals(lowerCase) ? "men" : "жін".equals(lowerCase) ? "women" : "unknown_sex";
        }
    }

    public static String humanReadableByteCountSI(long bytes) {
        if (-1000 < bytes && bytes < 1000) {
            return bytes + " B";
        }
        StringCharacterIterator stringCharacterIterator = new StringCharacterIterator("kMGTPE");
        while (true) {
            if (bytes > -999950 && bytes < 999950) {
                return String.format("%.1f %cB", Double.valueOf(bytes / 1000.0d), Character.valueOf(stringCharacterIterator.current()));
            }
            bytes /= 1000;
            stringCharacterIterator.next();
        }
    }
}
