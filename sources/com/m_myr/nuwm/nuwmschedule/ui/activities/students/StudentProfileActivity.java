package com.m_myr.nuwm.nuwmschedule.ui.activities.students;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.models.StudentInfo;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableDay;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableIdentifier;
import com.m_myr.nuwm.nuwmschedule.domain.adapter.TimetableAdapter;
import com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.groups.GroupsProfileActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.timetable.GeneralTimetableActivity;
import com.m_myr.nuwm.nuwmschedule.ui.view.ProfileCardInflater;
import com.m_myr.nuwm.nuwmschedule.utils.LinksResolver;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;

/* loaded from: classes2.dex */
public class StudentProfileActivity extends BaseStateActivity implements StudentProfileView {
    private TextView mEmail;
    private TextView mGroupName;
    private TextView mGroupNameFull;
    private TextView mName;
    private ImageView mPhoto;
    private TextView mProfShirt;
    private ProgressBar mProgressTimetable;
    private RecyclerView mRecyclerView;
    private StudentProfilePresenter presenter = new StudentProfilePresenter(this);

    public static void startById(Context context, int id) {
        Intent intent = new Intent(context, (Class<?>) StudentProfileActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setNestedScrollContentView(R.layout.student_profile_activity);
        this.mPhoto = (ImageView) findViewById(R.id.photo);
        this.mName = (TextView) findViewById(R.id.name);
        this.mGroupName = (TextView) findViewById(R.id.group_name);
        this.mGroupNameFull = (TextView) findViewById(R.id.group_name_full);
        this.mProfShirt = (TextView) findViewById(R.id.prof_shirt);
        this.mEmail = (TextView) findViewById(R.id.email);
        this.mProgressTimetable = (ProgressBar) findViewById(R.id.progress_timetable);
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ((TextView) findViewById(R.id.timetableName)).setText("Розклад студента");
    }

    public void onClickGroup(View view) {
        GroupsProfileActivity.startById(this, this.presenter.getData().getGroup().getId());
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.students.StudentProfileView
    public void setTimetable(TimetableDay<Lesson> value) {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        TimetableAdapter timetableAdapter = new TimetableAdapter(this, false);
        timetableAdapter.setData(value.getItems());
        this.mRecyclerView.setAdapter(timetableAdapter);
        this.mProgressTimetable.setVisibility(8);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.students.StudentProfileView
    public void hideTimetable() {
        findViewById(R.id.schedule).setVisibility(8);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.students.StudentProfileView
    public void setPersonData(StudentInfo data) {
        this.mName.setText(data.getFullName());
        setScrollTitle(data.getGender(), this.mName.getId());
        if (data.getProfShirt().isEmpty()) {
            this.mProfShirt.setText("Немає даних");
        } else {
            this.mProfShirt.setText(data.getProfShirt());
        }
        Glide.with((FragmentActivity) this).load(data.getImage()).apply((BaseRequestOptions<?>) new RequestOptions().transform(new RoundedCorners(Utils.dpToPx(10)))).into(this.mPhoto);
        if (data.getGroup().getCourse() == 0) {
            this.mGroupNameFull.setText("Студент");
        } else {
            this.mGroupNameFull.setText(data.getGender() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + data.getGroup().getCourse() + " курсу, " + data.getFormPg().toLowerCase());
        }
        this.mEmail.setText(data.getEmail());
        if (data.getGroup().getName().isEmpty()) {
            this.mGroupName.setText("Немає даних");
            findViewById(R.id.group_layout).setClickable(false);
        } else {
            this.mGroupName.setText(data.getGroup().getName());
        }
        findViewById(R.id.email_layout).setOnLongClickListener(new View.OnLongClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.students.StudentProfileActivity$$ExternalSyntheticLambda0
            public /* synthetic */ StudentProfileActivity$$ExternalSyntheticLambda0() {
            }

            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return StudentProfileActivity.lambda$setPersonData$0(StudentInfo.this, view);
            }
        });
        ProfileCardInflater.from(this, R.id.cards_container).inflate(data.getCards());
    }

    static /* synthetic */ boolean lambda$setPersonData$0(StudentInfo studentInfo, View view) {
        LinksResolver.copyToClipboard(studentInfo.getEmail());
        return true;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.students.StudentProfileView
    public void showEmptyTimetable() {
        this.mProgressTimetable.setVisibility(8);
        findViewById(R.id.empty_timetable).setVisibility(0);
    }

    public void onClickAllTimetable(View view) {
        Intent intent = new Intent(this, (Class<?>) GeneralTimetableActivity.class);
        intent.putExtra("identifier", TimetableIdentifier.byScheduleId(this.presenter.getData().getGroup().getScheduleId()));
        intent.putExtra("title", this.presenter.getData().getSimpleName() + " (" + this.presenter.getData().getGroup().getName() + ")");
        startActivity(intent);
    }
}
