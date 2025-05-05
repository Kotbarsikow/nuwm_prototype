package com.m_myr.nuwm.nuwmschedule.ui.activities.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.EmployerInfo;
import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.models.Post;
import com.m_myr.nuwm.nuwmschedule.data.models.RepositoryItem;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableDay;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableIdentifier;
import com.m_myr.nuwm.nuwmschedule.data.models.YearStatRepo;
import com.m_myr.nuwm.nuwmschedule.domain.adapter.TimetableAdapter;
import com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.department.DepartmentProfileActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.repositoryPeople.UserRepositoryActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.timetable.GeneralTimetableActivity;
import com.m_myr.nuwm.nuwmschedule.ui.view.ProfileCardInflater;
import com.m_myr.nuwm.nuwmschedule.utils.LinksResolver;
import com.m_myr.nuwm.nuwmschedule.utils.ResourcesHelper;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class UserProfileActivity extends BaseStateActivity implements UserProfileView {
    private LinearLayout mContentScrollable;
    private TextView mDegree;
    private LinearLayout mDepartments;
    private TextView mDocCount;
    private BarChart mDownloadChart;
    private TextView mDownloads;
    private TextView mEmail;
    private ImageView mImage;
    private TextView mInfo;
    private AppCompatTextView mName;
    private ImageView mPhoto;
    private ProgressBar mProgressTimetable;
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerViewLibrary;
    private TextView mWikitext;
    private UserProfilePresenter presenter = new UserProfilePresenter(this);

    public static void startById(Context context, int id) {
        Intent intent = new Intent(context, (Class<?>) UserProfileActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    public static void findByName(Context context, String name) {
        Intent intent = new Intent(context, (Class<?>) UserProfileActivity.class);
        intent.putExtra(AppMeasurementSdk.ConditionalUserProperty.NAME, name);
        context.startActivity(intent);
    }

    public static void findByEmail(Context context, String email) {
        Intent intent = new Intent(context, (Class<?>) UserProfileActivity.class);
        intent.putExtra("email", email);
        context.startActivity(intent);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setNestedScrollContentView(R.layout.user_profile_activity);
        allowFindOnBaseView(true);
        setTitle(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        this.mPhoto = (ImageView) findViewById(R.id.photo);
        this.mName = (AppCompatTextView) findViewById(R.id.name);
        this.mDegree = (TextView) findViewById(R.id.degree);
        this.mEmail = (TextView) findViewById(R.id.email);
        this.mInfo = (TextView) findViewById(R.id.info);
        this.mImage = (ImageView) findViewById(R.id.image);
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.mWikitext = (TextView) findViewById(R.id.wikitext);
        this.mDownloadChart = (BarChart) findViewById(R.id.download_chart);
        this.mRecyclerViewLibrary = (RecyclerView) findViewById(R.id.recyclerViewLibrary);
        this.mContentScrollable = (LinearLayout) findViewById(R.id.content_scrollable);
        this.mDownloads = (TextView) findViewById(R.id.downloads);
        this.mDocCount = (TextView) findViewById(R.id.doc_count);
        this.mProgressTimetable = (ProgressBar) findViewById(R.id.progress_timetable);
        this.mDepartments = (LinearLayout) findViewById(R.id.departments);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.user.UserProfileView
    public void setPersonData(EmployerInfo data) {
        setScrollTitle(data.getFullName(), this.mName.getId());
        this.mName.setText(data.getFullName());
        this.mEmail.setText(data.getEmail());
        if (data.isHasWikitext()) {
            this.mWikitext.setText(Html.fromHtml(data.getWikitext()));
        } else {
            findViewById(R.id.biography).setVisibility(8);
        }
        this.mDegree.setText(data.getDegreeOrPost());
        if (!data.getDocuments().isEmpty()) {
            bindChart(data.getRepositoryYearStat());
            bindLibrary(data.getDocuments());
        } else {
            findBaseViewById(R.id.repository_block).setVisibility(8);
        }
        bindDepartments(data.getPosts());
        Glide.with((FragmentActivity) this).load(data.getImage()).apply((BaseRequestOptions<?>) new RequestOptions().transform(new RoundedCorners(Utils.dpToPx(10)))).into(this.mPhoto);
        findViewById(R.id.email_layout).setOnLongClickListener(new View.OnLongClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.user.UserProfileActivity$$ExternalSyntheticLambda0
            public /* synthetic */ UserProfileActivity$$ExternalSyntheticLambda0() {
            }

            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return UserProfileActivity.lambda$setPersonData$0(EmployerInfo.this, view);
            }
        });
        findViewById(R.id.email_layout).setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.user.UserProfileActivity$$ExternalSyntheticLambda1
            public final /* synthetic */ EmployerInfo f$1;

            public /* synthetic */ UserProfileActivity$$ExternalSyntheticLambda1(EmployerInfo data2) {
                r2 = data2;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserProfileActivity.this.m199x88e34c93(r2, view);
            }
        });
        if (data2.getEmail() == null) {
            findViewById(R.id.email_layout).setVisibility(8);
        }
        ProfileCardInflater.from(this, R.id.cards_container).inflate(data2.getCards());
    }

    static /* synthetic */ boolean lambda$setPersonData$0(EmployerInfo employerInfo, View view) {
        LinksResolver.copyToClipboard(employerInfo.getEmail());
        return true;
    }

    /* renamed from: lambda$setPersonData$1$com-m_myr-nuwm-nuwmschedule-ui-activities-user-UserProfileActivity */
    /* synthetic */ void m199x88e34c93(EmployerInfo employerInfo, View view) {
        LinksResolver.openEmail(this, employerInfo.getEmail());
    }

    private void bindDepartments(List<Post> posts) {
        for (Post post : posts) {
            View inflate = getLayoutInflater().inflate(R.layout.post_item, (ViewGroup) this.mDepartments, false);
            TextView textView = (TextView) inflate.findViewById(R.id.text1);
            TextView textView2 = (TextView) inflate.findViewById(R.id.text2);
            textView.setText(post.getDepartmentName());
            textView2.setText(post.getPostName());
            inflate.setTag(post);
            this.mDepartments.addView(inflate);
            inflate.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.user.UserProfileActivity$$ExternalSyntheticLambda2
                public /* synthetic */ UserProfileActivity$$ExternalSyntheticLambda2() {
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    UserProfileActivity.this.clickDepartment(view);
                }
            });
        }
    }

    public void clickDepartment(View view) {
        DepartmentProfileActivity.startById(this, ((Post) view.getTag()).getDepartmentId());
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.user.UserProfileView
    public void showEmptyTimetable() {
        this.mProgressTimetable.setVisibility(8);
        findViewById(R.id.empty_timetable).setVisibility(0);
    }

    private void bindLibrary(List<RepositoryItem> documents) {
        this.mRecyclerViewLibrary.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.mRecyclerViewLibrary.setAdapter(new UserPersonDocumentAdapter(this, documents, false));
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity
    public void OnRetry(View view) {
        this.presenter.OnRetry();
    }

    private void bindChart(List<YearStatRepo> repositoryYearStat) {
        ArrayList arrayList = new ArrayList();
        repositoryYearStat.size();
        int i = 0;
        int i2 = 0;
        for (YearStatRepo yearStatRepo : repositoryYearStat) {
            arrayList.add(new BarEntry(yearStatRepo.year, yearStatRepo.count));
            i2 += yearStatRepo.downloads;
            i += yearStatRepo.count;
        }
        this.mDocCount.setText(Utils.StringUtils.unitsFormat(i, "документ", "документи", "документів"));
        this.mDownloads.setText(Utils.getPrettyCount(i2) + "+ завантажень");
        BarDataSet barDataSet = new BarDataSet(arrayList, "");
        barDataSet.setDrawValues(true);
        barDataSet.setColor(ResourcesHelper.getAttrColor(this, R.attr.colorPrimaryDark));
        barDataSet.setValueFormatter(new ValueFormatter() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.user.UserProfileActivity.1
            AnonymousClass1() {
            }

            @Override // com.github.mikephil.charting.formatter.ValueFormatter
            public String getBarLabel(BarEntry barEntry) {
                return Integer.toString((int) barEntry.getY());
            }
        });
        BarData barData = new BarData(barDataSet);
        barData.setValueTextSize(10.0f);
        barData.setBarWidth(0.7f);
        barData.setValueTextColor(ResourcesHelper.getAttrColor(this, R.attr.colorTextTitle));
        this.mDownloadChart.setData(barData);
        this.mDownloadChart.setFitBars(true);
        this.mDownloadChart.setDoubleTapToZoomEnabled(false);
        this.mDownloadChart.setPinchZoom(false);
        this.mDownloadChart.setClickable(false);
        this.mDownloadChart.setFitBars(true);
        this.mDownloadChart.setDoubleTapToZoomEnabled(false);
        this.mDownloadChart.setPinchZoom(false);
        this.mDownloadChart.getAxisLeft().setDrawLabels(false);
        this.mDownloadChart.getAxisRight().setDrawLabels(false);
        this.mDownloadChart.getAxisRight().setEnabled(false);
        this.mDownloadChart.getAxisLeft().setEnabled(false);
        this.mDownloadChart.setScaleEnabled(false);
        this.mDownloadChart.getXAxis().setDrawLabels(true);
        this.mDownloadChart.getXAxis().setEnabled(true);
        this.mDownloadChart.getXAxis().setLabelCount(repositoryYearStat.size());
        this.mDownloadChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        this.mDownloadChart.getXAxis().setTextColor(ResourcesHelper.getAttrColor(this, R.attr.colorTextTitle));
        this.mDownloadChart.getXAxis().setTypeface(ResourcesCompat.getFont(this, R.font.basefont_semibold));
        this.mDownloadChart.getXAxis().setDrawAxisLine(false);
        this.mDownloadChart.getLegend().setEnabled(false);
        this.mDownloadChart.getAxisRight().setDrawAxisLine(false);
        this.mDownloadChart.getAxisRight().setDrawGridLines(false);
        this.mDownloadChart.getAxisLeft().setDrawAxisLine(false);
        this.mDownloadChart.getAxisLeft().setDrawGridLines(false);
        this.mDownloadChart.setMaxVisibleValueCount(20);
        this.mDownloadChart.setDrawBarShadow(false);
        this.mDownloadChart.setDrawValueAboveBar(true);
        this.mDownloadChart.getDescription().setEnabled(false);
        this.mDownloadChart.setDrawGridBackground(false);
        this.mDownloadChart.setTouchEnabled(true);
        this.mDownloadChart.setClickable(true);
        this.mDownloadChart.animateY(1800, Easing.EaseInOutCirc);
        this.mDownloadChart.getXAxis().setGridColor(0);
        this.mDownloadChart.getXAxis().setValueFormatter(new ValueFormatter() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.user.UserProfileActivity.2
            int i = 0;

            AnonymousClass2() {
            }

            @Override // com.github.mikephil.charting.formatter.ValueFormatter
            public String getAxisLabel(float value, AxisBase axis) {
                try {
                    int i3 = this.i + 1;
                    this.i = i3;
                    return i3 % 2 == 0 ? String.valueOf((int) value) : "";
                } catch (Exception unused) {
                    return "-";
                }
            }
        });
        this.mDownloadChart.invalidate();
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.activities.user.UserProfileActivity$1 */
    class AnonymousClass1 extends ValueFormatter {
        AnonymousClass1() {
        }

        @Override // com.github.mikephil.charting.formatter.ValueFormatter
        public String getBarLabel(BarEntry barEntry) {
            return Integer.toString((int) barEntry.getY());
        }
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.activities.user.UserProfileActivity$2 */
    class AnonymousClass2 extends ValueFormatter {
        int i = 0;

        AnonymousClass2() {
        }

        @Override // com.github.mikephil.charting.formatter.ValueFormatter
        public String getAxisLabel(float value, AxisBase axis) {
            try {
                int i3 = this.i + 1;
                this.i = i3;
                return i3 % 2 == 0 ? String.valueOf((int) value) : "";
            } catch (Exception unused) {
                return "-";
            }
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.user.UserProfileView
    public void hideSchedule() {
        findViewById(R.id.schedule).setVisibility(8);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.user.UserProfileView
    public void setTimetable(TimetableDay<Lesson> value) {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        TimetableAdapter timetableAdapter = new TimetableAdapter(this, false);
        timetableAdapter.setData(value.getItems());
        this.mRecyclerView.setAdapter(timetableAdapter);
        this.mProgressTimetable.setVisibility(8);
    }

    public void onShowMoreRepository(View view) {
        Intent intent = new Intent(this, (Class<?>) UserRepositoryActivity.class);
        intent.putExtra("author_name_str", this.presenter.getPersonInfo().getLastName());
        intent.putExtra("author_initials", this.presenter.getPersonInfo().getInitials());
        startActivity(intent);
    }

    public void onClickAllTimetable(View view) {
        Intent intent = new Intent(this, (Class<?>) GeneralTimetableActivity.class);
        intent.putExtra("identifier", TimetableIdentifier.byPersonName(this.presenter.getPersonInfo().getLastName(), this.presenter.getPersonInfo().getInitials()));
        intent.putExtra("title", this.presenter.getPersonInfo().getSimpleName());
        startActivity(intent);
    }
}
