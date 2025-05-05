package com.m_myr.nuwm.nuwmschedule.ui.activities.groupMarks;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.DayGroupMarks;
import com.m_myr.nuwm.nuwmschedule.data.models.Mark;
import com.m_myr.nuwm.nuwmschedule.data.models.SimpleUser;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.network.models.ElevationUpdate;
import com.m_myr.nuwm.nuwmschedule.network.models.GroupMarksResponse;
import com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity;
import com.m_myr.nuwm.nuwmschedule.utils.ResourcesHelper;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/* loaded from: classes2.dex */
public class GroupMarksActivity extends BaseStateActivity implements IGroupMarksView {
    private int half;
    private TextView mLastUpdate;
    private TableLayout mMainTable;
    private Typeface typeface;
    GroupMarksPresenterCompat presenter = new GroupMarksPresenterCompat(this);
    private View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.groupMarks.GroupMarksActivity.1
        AnonymousClass1() {
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View v) {
            if (v.getTag(R.bool.view_tag_1) != null && ((Boolean) v.getTag(R.bool.view_tag_1)).booleanValue()) {
                v.setTag(R.bool.view_tag_1, Boolean.FALSE);
                for (int i = 0; i < GroupMarksActivity.this.mMainTable.getChildCount(); i++) {
                    TableRow tableRow = (TableRow) GroupMarksActivity.this.mMainTable.getChildAt(i);
                    for (int i2 = 1; i2 < tableRow.getChildCount() - 1; i2++) {
                        tableRow.getChildAt(i2).setVisibility(0);
                    }
                }
                return true;
            }
            v.setTag(R.bool.view_tag_1, Boolean.TRUE);
            int i3 = ((TableRow.LayoutParams) v.getLayoutParams()).column;
            for (int i4 = 0; i4 < GroupMarksActivity.this.mMainTable.getChildCount(); i4++) {
                TableRow tableRow2 = (TableRow) GroupMarksActivity.this.mMainTable.getChildAt(i4);
                for (int i5 = 1; i5 < tableRow2.getChildCount() - 1; i5++) {
                    View childAt = tableRow2.getChildAt(i5);
                    if (((TableRow.LayoutParams) childAt.getLayoutParams()).column == i3) {
                        childAt.setVisibility(0);
                    } else {
                        childAt.setVisibility(8);
                    }
                }
            }
            return true;
        }
    };
    private View.OnClickListener onClickListenerType = new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.groupMarks.GroupMarksActivity.2
        AnonymousClass2() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            int i = ((TableRow.LayoutParams) v.getLayoutParams()).column;
            int i2 = 0;
            if (v.getTag(R.bool.view_tag_2) != null && ((Boolean) v.getTag(R.bool.view_tag_2)).booleanValue()) {
                v.setTag(R.bool.view_tag_2, Boolean.FALSE);
                while (i2 < GroupMarksActivity.this.mMainTable.getChildCount()) {
                    TableRow tableRow = (TableRow) GroupMarksActivity.this.mMainTable.getChildAt(i2);
                    for (int i3 = 1; i3 < tableRow.getChildCount() - 1; i3++) {
                        tableRow.getChildAt(i3).setAlpha(1.0f);
                    }
                    i2++;
                }
                return;
            }
            v.setTag(R.bool.view_tag_2, Boolean.TRUE);
            while (i2 < GroupMarksActivity.this.mMainTable.getChildCount()) {
                TableRow tableRow2 = (TableRow) GroupMarksActivity.this.mMainTable.getChildAt(i2);
                for (int i4 = 1; i4 < tableRow2.getChildCount() - 1; i4++) {
                    View childAt = tableRow2.getChildAt(i4);
                    if (((TableRow.LayoutParams) childAt.getLayoutParams()).column == i) {
                        childAt.setAlpha(1.0f);
                    } else {
                        childAt.setAlpha(0.15f);
                    }
                }
                i2++;
            }
        }
    };

    public void onSort(MenuItem item) {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groups_marks_layout);
        attachToolbar();
        this.mMainTable = (TableLayout) findViewById(R.id.main_table);
        this.typeface = ResourcesCompat.getFont(this, R.font.basefont_semibold);
        this.mLastUpdate = (TextView) findViewById(R.id.last_update);
        setActivityState(1);
        setTitle(getIntent().getStringExtra("subject_name"));
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_groupmarks, menu);
        return true;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity, com.m_myr.nuwm.nuwmschedule.domain.interfaces.BaseStateView, com.m_myr.nuwm.nuwmschedule.ui.activities.academicSuccess.AcademicSuccessOwner
    public void showError(String error) {
        setActivityState(-1, error);
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.activities.groupMarks.GroupMarksActivity$1 */
    class AnonymousClass1 implements View.OnLongClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View v) {
            if (v.getTag(R.bool.view_tag_1) != null && ((Boolean) v.getTag(R.bool.view_tag_1)).booleanValue()) {
                v.setTag(R.bool.view_tag_1, Boolean.FALSE);
                for (int i = 0; i < GroupMarksActivity.this.mMainTable.getChildCount(); i++) {
                    TableRow tableRow = (TableRow) GroupMarksActivity.this.mMainTable.getChildAt(i);
                    for (int i2 = 1; i2 < tableRow.getChildCount() - 1; i2++) {
                        tableRow.getChildAt(i2).setVisibility(0);
                    }
                }
                return true;
            }
            v.setTag(R.bool.view_tag_1, Boolean.TRUE);
            int i3 = ((TableRow.LayoutParams) v.getLayoutParams()).column;
            for (int i4 = 0; i4 < GroupMarksActivity.this.mMainTable.getChildCount(); i4++) {
                TableRow tableRow2 = (TableRow) GroupMarksActivity.this.mMainTable.getChildAt(i4);
                for (int i5 = 1; i5 < tableRow2.getChildCount() - 1; i5++) {
                    View childAt = tableRow2.getChildAt(i5);
                    if (((TableRow.LayoutParams) childAt.getLayoutParams()).column == i3) {
                        childAt.setVisibility(0);
                    } else {
                        childAt.setVisibility(8);
                    }
                }
            }
            return true;
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.groupMarks.IGroupMarksView
    public void showUpdate(ElevationUpdate data) {
        this.mLastUpdate.setText(data.getDate());
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.activities.groupMarks.GroupMarksActivity$2 */
    class AnonymousClass2 implements View.OnClickListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            int i = ((TableRow.LayoutParams) v.getLayoutParams()).column;
            int i2 = 0;
            if (v.getTag(R.bool.view_tag_2) != null && ((Boolean) v.getTag(R.bool.view_tag_2)).booleanValue()) {
                v.setTag(R.bool.view_tag_2, Boolean.FALSE);
                while (i2 < GroupMarksActivity.this.mMainTable.getChildCount()) {
                    TableRow tableRow = (TableRow) GroupMarksActivity.this.mMainTable.getChildAt(i2);
                    for (int i3 = 1; i3 < tableRow.getChildCount() - 1; i3++) {
                        tableRow.getChildAt(i3).setAlpha(1.0f);
                    }
                    i2++;
                }
                return;
            }
            v.setTag(R.bool.view_tag_2, Boolean.TRUE);
            while (i2 < GroupMarksActivity.this.mMainTable.getChildCount()) {
                TableRow tableRow2 = (TableRow) GroupMarksActivity.this.mMainTable.getChildAt(i2);
                for (int i4 = 1; i4 < tableRow2.getChildCount() - 1; i4++) {
                    View childAt = tableRow2.getChildAt(i4);
                    if (((TableRow.LayoutParams) childAt.getLayoutParams()).column == i) {
                        childAt.setAlpha(1.0f);
                    } else {
                        childAt.setAlpha(0.15f);
                    }
                }
                i2++;
            }
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.groupMarks.IGroupMarksView
    public void createTable(GroupMarksResponse data) {
        new Random();
        int dpToPx = ResourcesHelper.dpToPx(this, 1);
        TableRow tableRow = new TableRow(this);
        tableRow.setBackgroundColor(getResources().getColor(R.color.color_hintrow));
        TextView textView = new TextView(this);
        textView.setText("Студент");
        int i = dpToPx * 10;
        int i2 = dpToPx * 4;
        textView.setPadding(0, i, 0, i2);
        textView.setGravity(1);
        textView.setTextAppearance(this, R.style.BaseTextAppearance_Bold_Large);
        textView.setBackgroundColor(16777215);
        tableRow.addView(textView, new TableRow.LayoutParams(-2, -2, 2.0f));
        Iterator<DayGroupMarks> it = data.getMarks().iterator();
        int i3 = 1;
        while (it.hasNext()) {
            DayGroupMarks next = it.next();
            TextView textView2 = new TextView(this);
            textView2.setTextAppearance(this, R.style.BaseTextAppearance_Bold);
            textView2.setPadding(0, i, 0, i2);
            textView2.setText(next.getStrDate());
            textView2.setGravity(1);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(-1, -1, 1.0f);
            layoutParams.span = 2;
            layoutParams.column = i3;
            textView2.setLayoutParams(layoutParams);
            textView2.setClickable(true);
            textView2.setBackgroundColor(i3 % 2 == 0 ? 16777215 : -1996488705);
            textView2.setOnClickListener(this.onClickListenerType);
            textView2.setOnLongClickListener(this.onLongClickListener);
            i3++;
            tableRow.addView(textView2);
        }
        TextView textView3 = new TextView(this);
        textView3.setTextAppearance(this, R.style.BaseTextAppearance_Bold);
        textView3.setPadding(i, i, i, i2);
        textView3.setText("Всього");
        textView3.setGravity(1);
        textView3.setOnLongClickListener(this.onLongClickListener);
        TableRow.LayoutParams layoutParams2 = new TableRow.LayoutParams(-1, -1, 1.0f);
        layoutParams2.column = i3;
        textView3.setLayoutParams(layoutParams2);
        textView3.setBackgroundColor(i3 % 2 == 0 ? 16777215 : -1996488705);
        tableRow.addView(textView3);
        tableRow.setWeightSum(data.getMarks().size() + 3);
        this.mMainTable.addView(tableRow);
        TableRow tableRow2 = new TableRow(this);
        tableRow2.setBackgroundColor(getResources().getColor(R.color.color_hintrow2));
        TextView textView4 = new TextView(this);
        textView4.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        int i4 = dpToPx * 2;
        int i5 = dpToPx * 3;
        textView4.setPadding(0, i4, 0, i5);
        textView4.setGravity(1);
        textView4.setTextAppearance(this, R.style.BaseTextAppearance_Regular_Medium);
        textView4.setBackgroundColor(16777215);
        tableRow2.addView(textView4, new TableRow.LayoutParams(-2, -2, 2.0f));
        Iterator<DayGroupMarks> it2 = data.getMarks().iterator();
        int i6 = 1;
        while (it2.hasNext()) {
            DayGroupMarks next2 = it2.next();
            TextView textView5 = new TextView(this);
            textView5.setTextAppearance(this, R.style.BaseTextAppearance_Regular_Small);
            textView5.setTypeface(null, 2);
            textView5.setPadding(dpToPx, i4, dpToPx, i5);
            textView5.setText(next2.getType());
            textView5.setGravity(1);
            TableRow.LayoutParams layoutParams3 = new TableRow.LayoutParams(-1, -2, 1.0f);
            layoutParams3.span = 2;
            layoutParams3.column = i6;
            textView5.setLayoutParams(layoutParams3);
            textView5.setBackgroundColor(i6 % 2 == 0 ? 16777215 : -1996488705);
            i6++;
            tableRow2.addView(textView5);
        }
        TextView textView6 = new TextView(this);
        textView6.setTextAppearance(this, R.style.BaseTextAppearance_Regular_Small);
        textView6.setTypeface(null, 2);
        textView6.setPadding(dpToPx, i4, dpToPx, i5);
        textView6.setText("  ");
        textView6.setGravity(1);
        textView6.setLayoutParams(new TableRow.LayoutParams(-1, -1, 1.0f));
        textView6.setBackgroundColor(i6 % 2 == 0 ? 16777215 : -1996488705);
        tableRow2.addView(textView6);
        tableRow2.setWeightSum(data.getMarks().size() + 3);
        this.mMainTable.addView(tableRow2);
        Iterator<Map.Entry<Integer, SimpleUser>> it3 = data.getStudents().entrySet().iterator();
        int i7 = 2;
        while (it3.hasNext()) {
            SimpleUser value = it3.next().getValue();
            Log.e(getClass().getSimpleName(), "SimpleUser user=" + value.getId());
            TableRow tableRow3 = new TableRow(this);
            tableRow3.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.groupMarks.GroupMarksActivity.3
                AnonymousClass3() {
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    if (v.getTag() == null || !((Boolean) v.getTag()).booleanValue()) {
                        if (Build.VERSION.SDK_INT >= 23) {
                            v.setForeground(GroupMarksActivity.this.getDrawable(R.drawable.hintrow));
                        }
                        v.setTag(Boolean.TRUE);
                    } else {
                        if (Build.VERSION.SDK_INT >= 23) {
                            v.setForeground(null);
                        }
                        v.setTag(Boolean.FALSE);
                    }
                }
            });
            TextView textView7 = new TextView(this);
            textView7.setText(value.getFirstName() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + value.getLastName());
            textView7.setTextAppearance(this, R.style.BaseTextAppearance_Regular_Large);
            textView7.setPadding(dpToPx * 16, i4, dpToPx * 8, i4);
            tableRow3.setBackgroundColor(getResources().getColor(i7 % 2 == 0 ? R.color.color_hintrow : R.color.color_hintrow2));
            i7++;
            if (value.getId() == AppDataManager.getInstance().getNuwmUser().getId()) {
                tableRow3.setBackgroundColor(getResources().getColor(R.color.color_hintrow3));
            }
            tableRow3.addView(textView7);
            Iterator<DayGroupMarks> it4 = data.getMarks().iterator();
            float f = 0.0f;
            float f2 = 0.0f;
            int i8 = 1;
            while (it4.hasNext()) {
                DayGroupMarks next3 = it4.next();
                TextView textView8 = new TextView(this);
                TextView textView9 = new TextView(this);
                textView8.setTextAppearance(this, R.style.BaseTextAppearance_Regular);
                textView9.setTextAppearance(this, R.style.BaseTextAppearance_Regular);
                textView8.setGravity(1);
                textView9.setGravity(1);
                int i9 = dpToPx * 40;
                textView8.setMinWidth(i9);
                textView9.setMinWidth(i9);
                Mark mark = next3.getMarks().get(Integer.valueOf(value.getId()));
                if (mark != null) {
                    textView8.setText(mark.getFirstMark());
                    textView9.setText(mark.getSecondMark());
                    Utils.StringUtils.isBlank(mark.getSecondMark());
                    if (mark.isExamOrMoodle()) {
                        f2 += Utils.StringUtils.safeToFloat(mark.getFirstMark()) + Utils.StringUtils.safeToFloat(mark.getSecondMark());
                    }
                    f += Utils.StringUtils.safeToFloat(mark.getFirstMark()) + Utils.StringUtils.safeToFloat(mark.getSecondMark());
                } else {
                    textView8.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                    textView9.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                }
                textView8.setPadding(i4, 0, i4, 0);
                textView9.setPadding(i4, 0, i4, 0);
                int i10 = i8 % 2;
                textView8.setBackgroundColor(i10 == 0 ? 16777215 : -1996488705);
                textView9.setBackgroundColor(i10 == 0 ? 16777215 : -1996488705);
                TableRow.LayoutParams layoutParams4 = new TableRow.LayoutParams(-1, -1);
                Iterator<Map.Entry<Integer, SimpleUser>> it5 = it3;
                TableRow.LayoutParams layoutParams5 = new TableRow.LayoutParams(-1, -1);
                layoutParams4.column = i8;
                layoutParams5.column = i8;
                tableRow3.addView(textView8, layoutParams4);
                tableRow3.addView(textView9, layoutParams5);
                i8++;
                it3 = it5;
            }
            Iterator<Map.Entry<Integer, SimpleUser>> it6 = it3;
            TextView textView10 = new TextView(this);
            textView10.setTextAppearance(this, R.style.BaseTextAppearance_Regular);
            textView10.setGravity(17);
            textView10.setMinWidth(dpToPx * 30);
            if (f - f2 > 60.0f) {
                f = f2 + 60.0f;
            }
            textView10.setText(String.valueOf(round(f, 2)));
            textView10.setBackgroundColor(i8 % 2 == 0 ? 16777215 : -1996488705);
            tableRow3.addView(textView10, new TableRow.LayoutParams(-1, -1));
            tableRow3.setClickable(true);
            if (Build.VERSION.SDK_INT >= 23) {
                tableRow3.setForeground(ResourcesHelper.getAttrDrawable(this, R.attr.selectableItemBackground));
            }
            this.mMainTable.addView(tableRow3);
            it3 = it6;
        }
        setActivityState(0);
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.activities.groupMarks.GroupMarksActivity$3 */
    class AnonymousClass3 implements View.OnClickListener {
        AnonymousClass3() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            if (v.getTag() == null || !((Boolean) v.getTag()).booleanValue()) {
                if (Build.VERSION.SDK_INT >= 23) {
                    v.setForeground(GroupMarksActivity.this.getDrawable(R.drawable.hintrow));
                }
                v.setTag(Boolean.TRUE);
            } else {
                if (Build.VERSION.SDK_INT >= 23) {
                    v.setForeground(null);
                }
                v.setTag(Boolean.FALSE);
            }
        }
    }

    public static float round(float d, int decimalPlace) {
        return new BigDecimal(Float.toString(d)).setScale(decimalPlace, 4).floatValue();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == 0) {
            onShare(null);
        } else {
            Utils.showStub(this, "Спочатку надайде доступ для запису файлів");
        }
    }

    public void onShare(MenuItem item) {
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
            return;
        }
        getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
        View findViewById = findViewById(R.id.bitmapContent);
        findViewById.setBackgroundColor(-1);
        findViewById.buildDrawingCache();
        ((TextView) findViewById(R.id.tableLayoutTitle)).setText(getIntent().getStringExtra("subject_name"));
        ((TextView) findViewById(R.id.tableLayoutTitle)).setVisibility(0);
        ((TextView) findViewById(R.id.tableLayoutTitle)).postDelayed(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.groupMarks.GroupMarksActivity.4
            final /* synthetic */ View val$bitmapContent;

            AnonymousClass4(View findViewById2) {
                val$bitmapContent = findViewById2;
            }

            @Override // java.lang.Runnable
            public void run() {
                Bitmap createBitmap = Bitmap.createBitmap(val$bitmapContent.getMeasuredWidth(), val$bitmapContent.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
                val$bitmapContent.draw(new Canvas(createBitmap));
                ((TextView) GroupMarksActivity.this.findViewById(R.id.tableLayoutTitle)).setVisibility(8);
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("image/png");
                createBitmap.compress(Bitmap.CompressFormat.PNG, 100, new ByteArrayOutputStream());
                intent.putExtra("android.intent.extra.STREAM", Uri.parse(MediaStore.Images.Media.insertImage(GroupMarksActivity.this.getContentResolver(), createBitmap, GroupMarksActivity.this.getIntent().getStringExtra("subject_name"), (String) null)));
                GroupMarksActivity.this.startActivity(Intent.createChooser(intent, "Оберіть куди надіслати"));
            }
        }, 200L);
        ((TextView) findViewById(R.id.tableLayoutTitle)).invalidate();
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.activities.groupMarks.GroupMarksActivity$4 */
    class AnonymousClass4 implements Runnable {
        final /* synthetic */ View val$bitmapContent;

        AnonymousClass4(View findViewById2) {
            val$bitmapContent = findViewById2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Bitmap createBitmap = Bitmap.createBitmap(val$bitmapContent.getMeasuredWidth(), val$bitmapContent.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            val$bitmapContent.draw(new Canvas(createBitmap));
            ((TextView) GroupMarksActivity.this.findViewById(R.id.tableLayoutTitle)).setVisibility(8);
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("image/png");
            createBitmap.compress(Bitmap.CompressFormat.PNG, 100, new ByteArrayOutputStream());
            intent.putExtra("android.intent.extra.STREAM", Uri.parse(MediaStore.Images.Media.insertImage(GroupMarksActivity.this.getContentResolver(), createBitmap, GroupMarksActivity.this.getIntent().getStringExtra("subject_name"), (String) null)));
            GroupMarksActivity.this.startActivity(Intent.createChooser(intent, "Оберіть куди надіслати"));
        }
    }
}
