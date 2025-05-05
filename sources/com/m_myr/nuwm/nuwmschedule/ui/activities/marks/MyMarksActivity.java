package com.m_myr.nuwm.nuwmschedule.ui.activities.marks;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import com.google.android.material.snackbar.Snackbar;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.Mark;
import com.m_myr.nuwm.nuwmschedule.data.models.SubjectEvaluation;
import com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback;
import com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.groupMarks.GroupMarksActivity;
import com.m_myr.nuwm.nuwmschedule.utils.AlertHelpers;
import com.m_myr.nuwm.nuwmschedule.utils.ResourcesHelper;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class MyMarksActivity extends BaseStateActivity {
    private int half;
    private int hintSelected;
    SubjectEvaluation subjectEvaluation;
    TableLayout tableLayout;
    private Typeface typeface;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setScrollContentView(R.layout.my_marks_layout);
        this.tableLayout = (TableLayout) findViewById(R.id.main_table);
        this.typeface = ResourcesCompat.getFont(this, R.font.basefont_semibold);
        OnRetry();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity
    public void OnRetry() {
        this.half = getIntent().getIntExtra("half", 1);
        SubjectEvaluation subjectEvaluation = (SubjectEvaluation) getIntent().getSerializableExtra("subjectEvaluation");
        if (subjectEvaluation != null) {
            initData(subjectEvaluation);
        } else if (getIntent().getIntExtra("subject_id", 0) != 0) {
            setTitle(getIntent().getStringExtra("subject_name"));
            loadData(getIntent().getIntExtra("subject_id", 0));
        }
        this.hintSelected = getIntent().getIntExtra("lesson_id", 0);
    }

    private void loadData(int subjectId) {
        showLoading();
        FastRepository.from(this).call(APIMethods.getMarks(subjectId, this.half)).into(new RequestObjectCallback<SubjectEvaluation>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.marks.MyMarksActivity.1
            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onError(ErrorResponse response) {
                MyMarksActivity.this.showError(response.getMessage());
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onSuccessData(SubjectEvaluation data) {
                MyMarksActivity.this.initData(data);
            }
        }).start();
    }

    void initData(SubjectEvaluation subjectEvaluation) {
        this.subjectEvaluation = subjectEvaluation;
        setTitle(subjectEvaluation.getName());
        createTable();
        showContent();
    }

    private void createTable() {
        TableRow tableRow = new TableRow(this);
        int dpToPx = ResourcesHelper.dpToPx(this, 1);
        int i = dpToPx * 12;
        int i2 = dpToPx * 8;
        tableRow.setPadding(i, i, i2, i2);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(-1, -2);
        tableRow.setLayoutParams(layoutParams);
        this.tableLayout.setLayoutParams(layoutParams);
        TextView textView = new TextView(this);
        textView.setTextAppearance(this, R.style.BaseTextAppearance_Bold_Large);
        textView.setText(" Дата");
        TextView textView2 = new TextView(this);
        textView2.setTextAppearance(this, R.style.BaseTextAppearance_Bold_Large);
        textView2.setText("Вид");
        TextView textView3 = new TextView(this);
        textView3.setTextAppearance(this, R.style.BaseTextAppearance_Bold_Large);
        textView3.setText("Бали");
        tableRow.addView(textView);
        tableRow.addView(textView2);
        tableRow.addView(textView3);
        this.tableLayout.addView(tableRow);
        Iterator<Mark> it = this.subjectEvaluation.getMarks().iterator();
        while (it.hasNext()) {
            final Mark next = it.next();
            TextView textView4 = new TextView(this);
            textView4.setText(next.getDate());
            textView4.setTypeface(this.typeface);
            TextView textView5 = new TextView(this);
            textView5.setText(next.getType());
            textView5.setTypeface(this.typeface, 2);
            TextView textView6 = new TextView(this);
            textView6.setText(next.getFirstMark() + "  " + next.getSecondMark());
            int i3 = dpToPx * 3;
            int i4 = dpToPx * 2;
            textView4.setPadding(i3, dpToPx, i4, dpToPx);
            textView5.setPadding(i3, dpToPx, i4, dpToPx);
            final TableRow tableRow2 = new TableRow(this);
            tableRow2.setBackground(ResourcesHelper.getAttrDrawable(this, R.attr.selectableItemBackground));
            int lessonId = next.getLessonId();
            int i5 = this.hintSelected;
            if (lessonId == i5 && i5 != 0) {
                ObjectAnimator ofInt = ObjectAnimator.ofInt(tableRow2, "backgroundColor", 0, getResources().getColor(R.color.colorAccentTransparent), 0);
                ofInt.setDuration(1100L);
                ofInt.addListener(new AnimatorListenerAdapter() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.marks.MyMarksActivity.2
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        tableRow2.setBackground(ResourcesHelper.getAttrDrawable(MyMarksActivity.this.getContext(), R.attr.selectableItemBackground));
                    }
                });
                ofInt.setEvaluator(new ArgbEvaluator());
                ofInt.setRepeatMode(2);
                ofInt.setRepeatCount(2);
                ofInt.start();
            }
            tableRow2.setClickable(true);
            textView4.setTextAppearance(this, R.style.BaseTextAppearance_Bold);
            textView5.setTextAppearance(this, R.style.BaseTextAppearance_Regular);
            textView6.setTextAppearance(this, R.style.BaseTextAppearance_Regular);
            int i6 = dpToPx * 4;
            tableRow2.setPadding(i, i6, i, i6);
            tableRow2.addView(textView4);
            tableRow2.addView(textView5);
            tableRow2.addView(textView6);
            this.tableLayout.addView(tableRow2);
            tableRow2.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.marks.MyMarksActivity.3
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    Snackbar.make(MyMarksActivity.this.findBaseViewById(R.id.main_content_based), "Викладач " + next.getTeacher(), -1).show();
                }
            });
        }
        TextView textView7 = new TextView(this);
        textView7.setText("Всього за заняття");
        textView7.setTypeface(this.typeface);
        textView7.setTextColor(ResourcesHelper.getAttrColor(this, R.attr.colorTextMain));
        TextView textView8 = new TextView(this);
        textView8.setTextColor(ResourcesHelper.getAttrColor(this, R.attr.colorTextMain));
        if (this.subjectEvaluation.getTotalLessonDiff() > 0.0f) {
            float totalLessonMark = this.subjectEvaluation.getTotalLessonMark();
            final float totalLessonMark2 = this.subjectEvaluation.getTotalLessonMark() + this.subjectEvaluation.getTotalLessonDiff();
            textView8.setText(Html.fromHtml(totalLessonMark + "  <small>(" + totalLessonMark2 + ")<sup>?</sup></small>"));
            textView8.setBackground(ResourcesHelper.getAttrDrawable(this, R.attr.selectableItemBackground));
            textView8.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.marks.MyMarksActivity$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MyMarksActivity.this.m184xe17ef76a(totalLessonMark2, view);
                }
            });
        } else {
            textView8.setText(String.valueOf(this.subjectEvaluation.getTotalLessonMark()));
        }
        int i7 = dpToPx * 2;
        textView7.setPadding(i, dpToPx, i7, dpToPx);
        textView8.setPadding(i, dpToPx, i7, dpToPx);
        TableRow tableRow3 = new TableRow(this);
        int i8 = dpToPx * 4;
        tableRow3.setPadding(0, i, i, i8);
        tableRow3.addView(textView7);
        tableRow3.addView(textView8);
        this.tableLayout.addView(tableRow3);
        TableRow.LayoutParams layoutParams2 = (TableRow.LayoutParams) textView7.getLayoutParams();
        layoutParams2.span = 2;
        textView7.setLayoutParams(layoutParams2);
        TextView textView9 = new TextView(this);
        textView9.setText("Всього");
        textView9.setTypeface(this.typeface);
        textView9.setTextSize(2, 16.0f);
        TextView textView10 = new TextView(this);
        textView10.setText("");
        TextView textView11 = new TextView(this);
        textView11.setText(String.valueOf(this.subjectEvaluation.getTotalMarks()));
        textView11.setTextSize(2, 16.0f);
        textView9.setTextColor(ResourcesHelper.getAttrColor(this, R.attr.colorTextMain));
        textView9.setPadding(i, dpToPx, i7, dpToPx);
        textView10.setTextColor(ResourcesHelper.getAttrColor(this, R.attr.colorTextMain));
        textView10.setPadding(dpToPx * 3, dpToPx, i7, dpToPx);
        textView11.setTextColor(ResourcesHelper.getAttrColor(this, R.attr.colorTextMain));
        textView11.setPadding(i, dpToPx, i7, dpToPx);
        TableRow tableRow4 = new TableRow(this);
        tableRow4.setPadding(0, i8, i, i8);
        tableRow4.addView(textView9);
        tableRow4.addView(textView10);
        tableRow4.addView(textView11);
        this.tableLayout.addView(tableRow4);
    }

    /* renamed from: lambda$createTable$0$com-m_myr-nuwm-nuwmschedule-ui-activities-marks-MyMarksActivity, reason: not valid java name */
    /* synthetic */ void m184xe17ef76a(float f, View view) {
        StringBuilder sb = new StringBuilder("За заняття ви отримали ");
        int i = (int) f;
        sb.append(Utils.StringUtils.unitsFormat(i, "бал", "бала", "балів"));
        sb.append(", оскільки максимальна оцінка за поточний контроль обмежена 60, додаткові ");
        sb.append(Utils.StringUtils.unitsFormat(i - 60, "бал", "бали", "балів"));
        sb.append(" не враховуються при розрахунку остаточної оцінки");
        AlertHelpers.createInfoAlert(this, "Застереження", sb.toString()).show();
    }

    public void onShowExtend(View view) {
        Intent intent = new Intent(this, (Class<?>) GroupMarksActivity.class);
        intent.putExtra("subject_id", this.subjectEvaluation.getId());
        intent.putExtra("subject_name", this.subjectEvaluation.getName());
        intent.putExtra("half", this.half);
        startActivity(intent);
    }
}
