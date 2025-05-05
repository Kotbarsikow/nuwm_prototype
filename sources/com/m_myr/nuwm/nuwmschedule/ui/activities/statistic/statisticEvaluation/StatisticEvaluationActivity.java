package com.m_myr.nuwm.nuwmschedule.ui.activities.statistic.statisticEvaluation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.exifinterface.media.ExifInterface;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.EctsScale;
import com.m_myr.nuwm.nuwmschedule.data.models.Rating;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.domain.AppPreferences;
import com.m_myr.nuwm.nuwmschedule.network.models.StatEvaluation;
import com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.statistic.instituteStatistic.StatisticInstituteEvaluationActivity;
import com.m_myr.nuwm.nuwmschedule.ui.view.barview.BarChart;
import com.m_myr.nuwm.nuwmschedule.ui.view.barview.BarDataEntry;
import com.m_myr.nuwm.nuwmschedule.utils.ResourcesHelper;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class StatisticEvaluationActivity extends BaseStateActivity implements StatisticEvaluationOwner {
    public static final int[] NUWM_COLORS = {Color.rgb(50, 94, 134), Color.rgb(133, 161, 193), Color.rgb(198, 204, 216), Color.rgb(63, 77, 99), Color.rgb(32, 32, 34)};
    public static final int[] NUWM_COLORS_S = {Color.argb(200, 133, 161, 193), Color.rgb(50, 94, 134)};
    Typeface fontMedium;
    TextView progressBarText;
    private StatEvaluation statisticData;
    TextView txtClickAllTime;
    TextView txtClickCourse;
    TextView txtClickCurrentHalf;
    TextView txtClickGlobal;
    TextView txtClickInstitute;
    TextView txtClickProf;
    TextView txtRate1;
    TextView txtRate2;
    TextView txtRateInGlobal;
    StatisticEvaluationPresenterCompat presenter = new StatisticEvaluationPresenterCompat(this);
    long myId = AppDataManager.getInstance().getStudent().getId();
    private boolean cancelloading = false;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setScrollContentView(R.layout.evaluation_stat_activity);
        getSupportActionBar().setTitle("Статистика оцінок");
        setActivityState(1);
        TextView textView = (TextView) findBaseViewById(R.id.progressBarText);
        this.progressBarText = textView;
        textView.setVisibility(0);
        this.progressBarText.setText("Рахуємо оцінки групи...");
        this.fontMedium = ResourcesCompat.getFont(this, R.font.basefont_semibold);
        this.txtClickGlobal = (TextView) findViewById(R.id.click_global);
        this.txtClickCourse = (TextView) findViewById(R.id.click_course);
        this.txtClickProf = (TextView) findViewById(R.id.click_prof);
        this.txtClickAllTime = (TextView) findViewById(R.id.click_all_time);
        this.txtClickCurrentHalf = (TextView) findViewById(R.id.click_current_half);
        this.txtClickInstitute = (TextView) findViewById(R.id.click_institute);
        this.txtRate1 = (TextView) findViewById(R.id.textRate1);
        this.txtRate2 = (TextView) findViewById(R.id.textRate2);
        this.cancelloading = false;
        new Handler().postDelayed(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.statistic.statisticEvaluation.StatisticEvaluationActivity.1
            @Override // java.lang.Runnable
            public void run() {
                if (StatisticEvaluationActivity.this.cancelloading) {
                    return;
                }
                StatisticEvaluationActivity.this.progressBarText.setText("Рахуємо оцінки курсу...");
                StatisticEvaluationActivity.this.toNextStep1();
            }
        }, 600L);
        if (AppPreferences.getInstance().isEnableTestFunction()) {
            this.txtClickInstitute.setVisibility(0);
        } else {
            this.txtClickInstitute.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toNextStep1() {
        new Handler().postDelayed(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.statistic.statisticEvaluation.StatisticEvaluationActivity.2
            @Override // java.lang.Runnable
            public void run() {
                if (StatisticEvaluationActivity.this.cancelloading) {
                    return;
                }
                StatisticEvaluationActivity.this.progressBarText.setText("Рахуємо загальний рейтинг...");
                StatisticEvaluationActivity.this.toNextStep2();
            }
        }, 600L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toNextStep2() {
        new Handler().postDelayed(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.statistic.statisticEvaluation.StatisticEvaluationActivity.3
            @Override // java.lang.Runnable
            public void run() {
                if (StatisticEvaluationActivity.this.cancelloading) {
                    return;
                }
                StatisticEvaluationActivity.this.progressBarText.setText("Шукаємо вас у рейтингу...");
                StatisticEvaluationActivity.this.toNextStep3();
            }
        }, 600L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toNextStep3() {
        new Handler().postDelayed(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.statistic.statisticEvaluation.StatisticEvaluationActivity.4
            @Override // java.lang.Runnable
            public void run() {
                if (StatisticEvaluationActivity.this.cancelloading) {
                    return;
                }
                StatisticEvaluationActivity.this.progressBarText.setText("Рахуємо єнотів...");
                StatisticEvaluationActivity.this.toNextStep4();
            }
        }, 600L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toNextStep4() {
        new Handler().postDelayed(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.statistic.statisticEvaluation.StatisticEvaluationActivity.5
            @Override // java.lang.Runnable
            public void run() {
                if (StatisticEvaluationActivity.this.cancelloading) {
                    return;
                }
                StatisticEvaluationActivity.this.progressBarText.setText("Останні підрахунки...");
            }
        }, 600L);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity, com.m_myr.nuwm.nuwmschedule.domain.interfaces.BaseStateView, com.m_myr.nuwm.nuwmschedule.ui.activities.academicSuccess.AcademicSuccessOwner
    public void showError(String message) {
        setActivityState(-1, message);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.statistic.statisticEvaluation.StatisticEvaluationOwner
    public void setData(StatEvaluation statisticData) {
        this.statisticData = statisticData;
        ((TextView) findViewById(R.id.str_main_avr)).setText(String.valueOf(statisticData.getAvg()));
        this.txtRateInGlobal = (TextView) findViewById(R.id.rate_in_global);
        showECTSChart(statisticData.getEcts());
        onClickGlobal(null);
        onClickCurrent(null);
        this.cancelloading = true;
        this.progressBarText.setVisibility(8);
        setActivityState(0);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        this.cancelloading = true;
    }

    public void onClickAllTime(View view) {
        if (showGroupChart(this.statisticData.getAllHalf(), false)) {
            this.txtClickAllTime.setTypeface(null, 1);
            this.txtClickCurrentHalf.setTypeface(Typeface.DEFAULT);
            this.txtClickAllTime.setTextColor(getResources().getColor(R.color.colorPrimaryMaterialDark));
            this.txtClickCurrentHalf.setTextColor(ResourcesHelper.getAttrColor(this, R.attr.colorTextMain));
        }
    }

    public void onClickInstituteDetails(View view) {
        startActivity(new Intent(this, (Class<?>) StatisticInstituteEvaluationActivity.class));
    }

    public void onClickCurrent(View view) {
        if (showGroupChart(this.statisticData.getCurrentHalf(), true)) {
            this.txtClickAllTime.setTypeface(Typeface.DEFAULT);
            this.txtClickCurrentHalf.setTypeface(null, 1);
            this.txtClickAllTime.setTextColor(ResourcesHelper.getAttrColor(this, R.attr.colorTextMain));
            this.txtClickCurrentHalf.setTextColor(getResources().getColor(R.color.colorPrimaryMaterialDark));
        }
    }

    public void onClickInstitute(View view) {
        showRateChart((LineChart) findViewById(R.id.chart_in_univer), this.statisticData.getRating().getInstituteCounter(), this.statisticData.getAvg(), 3);
        String valueOf = String.valueOf(100 - Math.round((this.statisticData.getRating().getMyInstituteScore() / this.statisticData.getRating().getAllInstituteCount()) * 100.0f));
        if (this.statisticData.getRating().getMyInstituteScore() < 10.0f) {
            this.txtRate1.setVisibility(8);
            this.txtRate2.setVisibility(8);
            this.txtRateInGlobal.setText("Ви в десятці найуспішніших!");
        } else if (valueOf.equals("100")) {
            this.txtRate1.setVisibility(0);
            this.txtRate2.setVisibility(0);
            this.txtRateInGlobal.setText("99.9");
        } else {
            this.txtRate1.setVisibility(0);
            this.txtRate2.setVisibility(0);
            this.txtRateInGlobal.setText(valueOf);
        }
        this.txtClickGlobal.setTypeface(Typeface.DEFAULT);
        this.txtClickInstitute.setTypeface(null, 1);
        this.txtClickCourse.setTypeface(Typeface.DEFAULT);
        this.txtClickProf.setTypeface(Typeface.DEFAULT);
        this.txtClickGlobal.setTextColor(ResourcesHelper.getAttrColor(this, R.attr.colorTextMain));
        this.txtClickInstitute.setTextColor(getResources().getColor(R.color.colorPrimaryMaterialDark));
        this.txtClickCourse.setTextColor(ResourcesHelper.getAttrColor(this, R.attr.colorTextMain));
        this.txtClickProf.setTextColor(ResourcesHelper.getAttrColor(this, R.attr.colorTextMain));
    }

    public void onClickGlobal(View view) {
        showRateChart((LineChart) findViewById(R.id.chart_in_univer), this.statisticData.getRating().getAllCounter(), this.statisticData.getAvg(), 3);
        String valueOf = String.valueOf(100 - Math.round((this.statisticData.getRating().getMyGlobalScore() / this.statisticData.getRating().getAllGlobalCount()) * 100.0f));
        if (this.statisticData.getRating().getMyGlobalScore() < 10) {
            this.txtRate1.setVisibility(8);
            this.txtRate2.setVisibility(8);
            this.txtRateInGlobal.setText("Ви в десятці найуспішніших!");
        } else if (valueOf.equals("100")) {
            this.txtRate1.setVisibility(0);
            this.txtRate2.setVisibility(0);
            this.txtRateInGlobal.setText("99.9");
        } else {
            this.txtRate1.setVisibility(0);
            this.txtRate2.setVisibility(0);
            this.txtRateInGlobal.setText(valueOf);
        }
        this.txtClickGlobal.setTypeface(null, 1);
        this.txtClickInstitute.setTypeface(Typeface.DEFAULT);
        this.txtClickCourse.setTypeface(Typeface.DEFAULT);
        this.txtClickProf.setTypeface(Typeface.DEFAULT);
        this.txtClickGlobal.setTextColor(getResources().getColor(R.color.colorPrimaryMaterialDark));
        this.txtClickInstitute.setTextColor(ResourcesHelper.getAttrColor(this, R.attr.colorTextMain));
        this.txtClickCourse.setTextColor(ResourcesHelper.getAttrColor(this, R.attr.colorTextMain));
        this.txtClickProf.setTextColor(ResourcesHelper.getAttrColor(this, R.attr.colorTextMain));
    }

    public void onClickCourse(View view) {
        showRateChart((LineChart) findViewById(R.id.chart_in_univer), this.statisticData.getRating().getCourseCounter(), this.statisticData.getAvg(), 2);
        String valueOf = String.valueOf(100 - Math.round((this.statisticData.getRating().getMyCourseScore() / this.statisticData.getRating().getAll_CourseCount()) * 100.0f));
        this.txtRateInGlobal.setText(valueOf);
        if (this.statisticData.getRating().getMyCourseScore() < 10.0f && this.statisticData.getRating().getAll_CourseCount() > 100.0f) {
            this.txtRate1.setVisibility(8);
            this.txtRate2.setVisibility(8);
            this.txtRateInGlobal.setText("Ви в десятці найуспішніших!");
        } else {
            this.txtRate1.setVisibility(0);
            this.txtRate2.setVisibility(0);
            this.txtRateInGlobal.setText(valueOf);
        }
        this.txtClickGlobal.setTypeface(Typeface.DEFAULT);
        this.txtClickInstitute.setTypeface(Typeface.DEFAULT);
        this.txtClickCourse.setTypeface(null, 1);
        this.txtClickProf.setTypeface(Typeface.DEFAULT);
        this.txtClickGlobal.setTextColor(ResourcesHelper.getAttrColor(this, R.attr.colorTextMain));
        this.txtClickInstitute.setTextColor(ResourcesHelper.getAttrColor(this, R.attr.colorTextMain));
        this.txtClickCourse.setTextColor(getResources().getColor(R.color.colorPrimaryMaterialDark));
        this.txtClickProf.setTextColor(ResourcesHelper.getAttrColor(this, R.attr.colorTextMain));
    }

    public void onClickProf(View view) {
        showRateChart((LineChart) findViewById(R.id.chart_in_univer), this.statisticData.getRating().getProfCounter(), this.statisticData.getAvg(), 2);
        this.txtRateInGlobal.setText(String.valueOf(100 - Math.round((this.statisticData.getRating().getMyProfScore() / this.statisticData.getRating().getAllProfCount()) * 100.0f)));
        this.txtRate1.setVisibility(0);
        this.txtRate2.setVisibility(0);
        this.txtClickGlobal.setTypeface(Typeface.DEFAULT);
        this.txtClickInstitute.setTypeface(Typeface.DEFAULT);
        this.txtClickCourse.setTypeface(Typeface.DEFAULT);
        this.txtClickProf.setTypeface(null, 1);
        this.txtRate1.setVisibility(0);
        this.txtRate2.setVisibility(0);
        this.txtClickGlobal.setTextColor(ResourcesHelper.getAttrColor(this, R.attr.colorTextMain));
        this.txtClickInstitute.setTextColor(ResourcesHelper.getAttrColor(this, R.attr.colorTextMain));
        this.txtClickCourse.setTextColor(ResourcesHelper.getAttrColor(this, R.attr.colorTextMain));
        this.txtClickProf.setTextColor(getResources().getColor(R.color.colorPrimaryMaterialDark));
    }

    private void showRateChart(LineChart chart, ArrayList<Rating.RoundCount> counts, float avg, int incr) {
        boolean z;
        ArrayList arrayList = new ArrayList();
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_person_pin);
        arrayList.add(new Entry(0.0f, 0.0f));
        if (avg < 1.0f) {
            findViewById(R.id.globalRateText).setVisibility(4);
            ((TextView) findViewById(R.id.globalRateSubtext)).setText("ви з'явитеся у рейтингу наступного семестру");
            z = true;
        } else {
            z = false;
        }
        for (int i = 0; i < counts.size(); i++) {
            if (counts.get(i).round >= Math.round(avg) && !z) {
                arrayList.add(new Entry(avg, r8.count, drawable));
                z = true;
            } else {
                arrayList.add(new Entry(r8.round, r8.count));
            }
        }
        for (int x = ((int) ((Entry) arrayList.get(arrayList.size() - 1)).getX()) + 1; x <= 100; x++) {
            float f = x;
            if (f >= avg && !z) {
                arrayList.add(new Entry(f, 1.0f, drawable));
                z = true;
            } else {
                arrayList.add(new Entry(f, 0.0f));
            }
        }
        LineDataSet lineDataSet = new LineDataSet(arrayList, "");
        lineDataSet.setDrawIcons(true);
        lineDataSet.setMode(LineDataSet.Mode.STEPPED);
        lineDataSet.setColor(Color.rgb(50, 94, 134));
        lineDataSet.setDrawFilled(true);
        lineDataSet.setDrawIcons(false);
        lineDataSet.setIconsOffset(new MPPointF(0.0f, -14.0f));
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawValues(false);
        lineDataSet.setFillColor(Color.rgb(133, 161, 193));
        LineData lineData = new LineData(lineDataSet);
        lineData.setValueTextSize(13.0f);
        lineData.setValueTextColor(-1);
        lineData.setValueTypeface(this.fontMedium);
        chart.setData(lineData);
        chart.setContentDescription(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        chart.animateX(800, Easing.EaseInOutCirc);
        chart.setScaleEnabled(false);
        chart.setTouchEnabled(false);
        chart.setScaleYEnabled(true);
        Iterator it = ((LineData) chart.getData()).getDataSets().iterator();
        while (it.hasNext()) {
            ((IDataSet) it.next()).setDrawIcons(true);
        }
        chart.getAxisLeft().setStartAtZero(true);
        chart.getAxisRight().setStartAtZero(true);
        chart.getAxisLeft().setEnabled(false);
        chart.getAxisRight().setEnabled(false);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getXAxis().setTextColor(ResourcesHelper.getAttrColor(this, R.attr.colorTextSub));
        chart.getXAxis().setDrawGridLines(false);
        Description description = new Description();
        description.setEnabled(false);
        chart.setDescription(description);
        chart.getLegend().setEnabled(false);
        if (avg > 1.0f) {
            chart.setMarkerView(new CustomMarkerView(this, R.layout.custom_marker_view_layout));
        }
        chart.invalidate();
    }

    public class CustomMarkerView extends MarkerView {
        @Override // com.github.mikephil.charting.components.MarkerView, com.github.mikephil.charting.components.IMarker
        public void refreshContent(Entry e, Highlight highlight) {
        }

        public CustomMarkerView(Context context, int layoutResource) {
            super(context, layoutResource);
            setOffset(-24.0f, -52.0f);
        }
    }

    private boolean showGroupChart(ArrayList<StatEvaluation.MyGroupUser> group, boolean showTotals) {
        if (group.isEmpty()) {
            Toast.makeText(this, "Немає даних для відображення", 0).show();
            return false;
        }
        BarChart barChart = (BarChart) findViewById(R.id.group_chart);
        ArrayList<BarDataEntry> arrayList = new ArrayList<>();
        for (int i = 0; i < group.size(); i++) {
            String name = group.get(i).getName();
            StatEvaluation.MyGroupUser myGroupUser = group.get(i);
            BarDataEntry barDataEntry = new BarDataEntry(name, showTotals ? myGroupUser.getTotals() : myGroupUser.getAvg(), group.get(i));
            if (group.get(i).getId() == this.myId) {
                barDataEntry.hint(true);
            }
            arrayList.add(barDataEntry);
        }
        barChart.animateX(1200, Easing.EaseInOutCirc);
        barChart.addData(arrayList);
        barChart.setValueFormatter(new BarChart.ValueFormatter() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.statistic.statisticEvaluation.StatisticEvaluationActivity.6
            DecimalFormat df = new DecimalFormat();

            @Override // com.m_myr.nuwm.nuwmschedule.ui.view.barview.BarChart.ValueFormatter
            public String onFormat(float val) {
                this.df.setMaximumFractionDigits(1);
                DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
                decimalFormatSymbols.setDecimalSeparator('.');
                this.df.setDecimalFormatSymbols(decimalFormatSymbols);
                return this.df.format(val);
            }
        });
        barChart.invalidate();
        return true;
    }

    private void showECTSChart(final EctsScale ects) {
        ArrayList arrayList = new ArrayList();
        if (ects.getA() > 0) {
            arrayList.add(new PieEntry(ects.getA(), ExifInterface.GPS_MEASUREMENT_IN_PROGRESS));
        }
        if (ects.getB() > 0) {
            arrayList.add(new PieEntry(ects.getB(), "B"));
        }
        if (ects.getC() > 0) {
            arrayList.add(new PieEntry(ects.getC(), "C"));
        }
        if (ects.getD() > 0) {
            arrayList.add(new PieEntry(ects.getD(), "D"));
        }
        if (ects.getE() > 0) {
            arrayList.add(new PieEntry(ects.getE(), ExifInterface.LONGITUDE_EAST));
        }
        if (ects.getF() > 0) {
            arrayList.add(new PieEntry(ects.getF(), "F"));
        }
        PieDataSet pieDataSet = new PieDataSet(arrayList, "");
        PieChart pieChart = (PieChart) findViewById(R.id.chart);
        pieDataSet.setDrawIcons(false);
        pieDataSet.setValueFormatter(new ValueFormatter() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.statistic.statisticEvaluation.StatisticEvaluationActivity.7
            @Override // com.github.mikephil.charting.formatter.ValueFormatter
            public String getFormattedValue(float value) {
                return Math.round((value / ects.getTotal()) * 100.0f) + " %";
            }
        });
        ArrayList arrayList2 = new ArrayList();
        for (int i : NUWM_COLORS) {
            arrayList2.add(Integer.valueOf(i));
        }
        pieDataSet.setColors(arrayList2);
        pieDataSet.setSliceSpace(3.0f);
        pieDataSet.setIconsOffset(new MPPointF(0.0f, 40.0f));
        pieDataSet.setSelectionShift(5.0f);
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(13.0f);
        pieData.setValueTextColor(-1);
        pieData.setValueTypeface(this.fontMedium);
        pieChart.setCenterText("ECTS");
        pieChart.setCenterTextTypeface(this.fontMedium);
        pieChart.setCenterTextSize(15.0f);
        pieChart.setData(pieData);
        pieChart.highlightValues(null);
        pieChart.setContentDescription("Text");
        pieChart.animateX(800, Easing.EaseInOutCirc);
        Description description = new Description();
        description.setEnabled(false);
        pieChart.setDescription(description);
        pieChart.getLegend().setEnabled(false);
        pieChart.invalidate();
    }
}
