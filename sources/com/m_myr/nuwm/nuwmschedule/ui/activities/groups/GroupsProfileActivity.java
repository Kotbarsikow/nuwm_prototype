package com.m_myr.nuwm.nuwmschedule.ui.activities.groups;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.GroupExtended;
import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableDay;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableIdentifier;
import com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository;
import com.m_myr.nuwm.nuwmschedule.domain.adapter.TimetableAdapter;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener;
import com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback;
import com.m_myr.nuwm.nuwmschedule.network.models.ScheduleResponse;
import com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.simpleScreen.StudentsListActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.timetable.GeneralTimetableActivity;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.util.Map;

/* loaded from: classes2.dex */
public class GroupsProfileActivity extends BaseStateActivity implements RequestObjectCallback<GroupExtended> {
    private GroupExtended group;
    private TextView mFaculty;
    private TextView mProfShirt;
    private ProgressBar mProgressTimetable;
    private RecyclerView mRecyclerView;
    private String streamName;

    public static void startById(Context context, int id) {
        if (id >= 0) {
            throw new RuntimeException("Group id must be lower that 0");
        }
        Intent intent = new Intent(context, (Class<?>) GroupsProfileActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    public static void startByName(Context context, String name) {
        Intent intent = new Intent(context, (Class<?>) GroupsProfileActivity.class);
        intent.putExtra(AppMeasurementSdk.ConditionalUserProperty.NAME, name);
        context.startActivity(intent);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setNestedScrollContentView(R.layout.group_profile_activity);
        allowFindOnBaseView(true);
        initView();
        setTitle(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        Intent intent = getIntent();
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (intent.getIntExtra("id", 0) != 0) {
            FastRepository.from(this).call(APIMethods.getGroup(intent.getIntExtra("id", 0), false)).into(this).start();
        } else if (intent.getStringExtra(AppMeasurementSdk.ConditionalUserProperty.NAME) != null) {
            setTitle(intent.getStringExtra(AppMeasurementSdk.ConditionalUserProperty.NAME));
            FastRepository.from(this).call(APIMethods.findGroup(intent.getStringExtra(AppMeasurementSdk.ConditionalUserProperty.NAME), false)).into(this).start();
        } else {
            showError("Не знайдено групи");
        }
    }

    public void showEmptyTimetable() {
        this.mProgressTimetable.setVisibility(8);
        findViewById(R.id.empty_timetable).setVisibility(0);
    }

    private void initView() {
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.mProgressTimetable = (ProgressBar) findViewById(R.id.progress_timetable);
        this.mFaculty = (TextView) findViewById(R.id.faculty);
        this.mProfShirt = (TextView) findViewById(R.id.prof_shirt);
        showLoading();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
    public void onError(ErrorResponse response) {
        showError(response.getMessage());
    }

    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
    public void onSuccessData(GroupExtended data) {
        this.group = data;
        String str = "Група " + data.getName();
        this.streamName = str;
        setTitle(str);
        this.mFaculty.setText(data.getFaculty());
        this.mProfShirt.setText(data.getProfessionShirt());
        ((TextView) findViewById(R.id.students_list)).setText(Utils.StringUtils.unitsFormat(data.getAmount(), "студент", "студенти", "студентів"));
        loadTimetable(data.getScheduleId());
        showContent();
    }

    private void loadTimetable(int scheduleId) {
        FastRepository.from(this).call(APIMethods.getTimetableToday(TimetableIdentifier.byScheduleId(scheduleId))).into((APIObjectListener) new APIObjectListener<ScheduleResponse>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.groups.GroupsProfileActivity.1
            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onError(ErrorResponse response) {
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onSuccessData(ScheduleResponse data) {
                TimetableAdapter timetableAdapter = new TimetableAdapter(GroupsProfileActivity.this, false);
                Map.Entry<Integer, TimetableDay<Lesson>> next = data.timetable.entrySet().iterator().next();
                if (next.getValue().getItems().isEmpty()) {
                    GroupsProfileActivity.this.showEmptyTimetable();
                    return;
                }
                timetableAdapter.setData(next.getValue().getItems());
                GroupsProfileActivity.this.mRecyclerView.setAdapter(timetableAdapter);
                GroupsProfileActivity.this.mProgressTimetable.setVisibility(8);
            }
        }).start();
    }

    public void onClickAllTimetable(View view) {
        Intent intent = new Intent(this, (Class<?>) GeneralTimetableActivity.class);
        intent.putExtra("identifier", TimetableIdentifier.byScheduleId(this.group.getScheduleId()));
        intent.putExtra("title", this.group.getName());
        startActivity(intent);
    }

    public void onClickShowStudents(View view) {
        StudentsListActivity.start(this, this.group.getId(), this.group.getName());
    }
}
