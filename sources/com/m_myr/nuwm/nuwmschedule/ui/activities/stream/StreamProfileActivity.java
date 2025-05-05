package com.m_myr.nuwm.nuwmschedule.ui.activities.stream;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.Group;
import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableDay;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableIdentifier;
import com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository;
import com.m_myr.nuwm.nuwmschedule.domain.adapter.TimetableAdapter;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.FieldGetter;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener;
import com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback;
import com.m_myr.nuwm.nuwmschedule.network.models.ScheduleResponse;
import com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.groups.GroupsProfileActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.simpleScreen.StudentsListActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.timetable.GeneralTimetableActivity;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class StreamProfileActivity extends BaseStateActivity implements RequestObjectCallback<ArrayList<Group>> {
    private ArrayList<Group> groups;
    private TextView mConsists;
    private LinearLayout mGroups;
    private AppCompatTextView mName;
    private ProgressBar mProgressTimetable;
    private RecyclerView mRecyclerView;
    private String streamName;
    private String streamSheduleIds = "";
    boolean loadTimetable = true;

    public static void startByScheduleIds(Context context, String text, String streamConsists) {
        Intent intent = new Intent(context, (Class<?>) StreamProfileActivity.class);
        intent.putExtra(AppMeasurementSdk.ConditionalUserProperty.NAME, text);
        if (streamConsists == null) {
            streamConsists = "";
        }
        intent.putExtra(APIMethods.SCHEDULE_IDS, streamConsists);
        context.startActivity(intent);
    }

    public static void startByGroups(Context context, String text, List<Group> groups) {
        Intent intent = new Intent(context, (Class<?>) StreamProfileActivity.class);
        intent.putExtra(AppMeasurementSdk.ConditionalUserProperty.NAME, text);
        intent.putExtra("groups", Utils.StringUtils.join(",", groups));
        context.startActivity(intent);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setNestedScrollContentView(R.layout.stream_profile_activity);
        allowFindOnBaseView(true);
        initView();
        setTitle(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        Intent intent = getIntent();
        this.mName.setText(intent.getStringExtra(AppMeasurementSdk.ConditionalUserProperty.NAME));
        String stringExtra = intent.getStringExtra(APIMethods.SCHEDULE_IDS);
        String stringExtra2 = intent.getStringExtra("groups");
        if (stringExtra != null) {
            this.loadTimetable = false;
            loadFromScheduleIds(stringExtra);
        } else if (stringExtra2 != null) {
            this.loadTimetable = true;
            loadFromGroups(stringExtra2);
        }
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadFromGroups(String groups) {
        FastRepository.from(this).call(APIMethods.getGroupsOfStream(APIMethods.GROUP_IDS, groups)).into(this).start();
    }

    private void loadFromScheduleIds(String consists) {
        this.streamSheduleIds = consists;
        String[] split = consists.split(",");
        if (split.length == 0) {
            showError("Це не потік");
            return;
        }
        this.mConsists.setText(Utils.StringUtils.unitsFormat(split.length, "група", "групи", "груп"));
        FastRepository.from(this).call(APIMethods.getGroupsOfStream(APIMethods.SCHEDULE_IDS, consists)).into(this).start();
        loadTimetable();
        this.loadTimetable = false;
    }

    public void showEmptyTimetable() {
        this.mProgressTimetable.setVisibility(8);
        findViewById(R.id.empty_timetable).setVisibility(0);
    }

    private void initView() {
        this.mConsists = (TextView) findViewById(R.id.consists);
        this.mName = (AppCompatTextView) findViewById(R.id.name);
        this.mGroups = (LinearLayout) findViewById(R.id.groups);
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.mProgressTimetable = (ProgressBar) findViewById(R.id.progress_timetable);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
    public void onError(ErrorResponse response) {
        showError(response.getMessage());
    }

    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
    public void onSuccessData(ArrayList<Group> data) {
        this.groups = data;
        String str = "Потік " + Utils.StringUtils.join(", ", data, new FieldGetter() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.stream.StreamProfileActivity$$ExternalSyntheticLambda0
            @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.FieldGetter
            public final String getFieldToString(Object obj) {
                return ((Group) obj).getName();
            }
        });
        this.streamName = str;
        this.mName.setText(str);
        setScrollTitle(this.streamName, R.id.name);
        this.mConsists.setText(Utils.StringUtils.unitsFormat(data.size(), "група", "групи", "груп"));
        Iterator<Group> it = data.iterator();
        int i = 0;
        while (it.hasNext()) {
            Group next = it.next();
            View inflate = getLayoutInflater().inflate(R.layout.group_list, (ViewGroup) this.mGroups, false);
            TextView textView = (TextView) inflate.findViewById(R.id.name);
            TextView textView2 = (TextView) inflate.findViewById(R.id.info);
            TextView textView3 = (TextView) inflate.findViewById(R.id.faculty);
            textView.setText(next.getName());
            textView3.setText(next.getFaculty());
            textView2.setText(next.getCourse() + " курс, " + Utils.StringUtils.unitsFormat(next.getAmount(), "студент", "студенти", "студентів"));
            inflate.setTag(next);
            inflate.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.stream.StreamProfileActivity$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    StreamProfileActivity.this.clickGroup(view);
                }
            });
            i += next.getAmount();
            this.mGroups.addView(inflate);
            HashSet hashSet = new HashSet(Arrays.asList(this.streamSheduleIds.split(",")));
            hashSet.add(String.valueOf(next.getScheduleId()));
            this.streamSheduleIds = Utils.StringUtils.joinIterableString(",", hashSet);
        }
        ((TextView) findViewById(R.id.students_list)).setText(Utils.StringUtils.unitsFormat(i, "студент", "студенти", "студентів"));
        if (this.loadTimetable) {
            loadTimetable();
        }
        showContent();
    }

    private void loadTimetable() {
        FastRepository.from(this).call(APIMethods.getTimetableToday(TimetableIdentifier.byStreams(this.streamSheduleIds))).into((APIObjectListener) new APIObjectListener<ScheduleResponse>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.stream.StreamProfileActivity.1
            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onError(ErrorResponse response) {
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onSuccessData(ScheduleResponse data) {
                ViewGroup viewGroup = (ViewGroup) StreamProfileActivity.this.findViewById(R.id.schedule);
                TextView textView = new TextView(StreamProfileActivity.this);
                textView.setText("У розкладі потоку відображаються лише заняття спільні для всіх груп потоку!");
                int dpToPx = Utils.dpToPx(20);
                textView.setPadding(dpToPx, 0, dpToPx, dpToPx / 2);
                viewGroup.addView(textView, 3);
                TimetableAdapter timetableAdapter = new TimetableAdapter(StreamProfileActivity.this, false);
                Map.Entry<Integer, TimetableDay<Lesson>> next = data.timetable.entrySet().iterator().next();
                if (next.getValue().getItems().isEmpty()) {
                    StreamProfileActivity.this.showEmptyTimetable();
                    return;
                }
                timetableAdapter.setData(next.getValue().getItems());
                StreamProfileActivity.this.mRecyclerView.setAdapter(timetableAdapter);
                StreamProfileActivity.this.mProgressTimetable.setVisibility(8);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickGroup(View view) {
        GroupsProfileActivity.startById(this, ((Group) view.getTag()).getId());
    }

    public void onClickAllTimetable(View view) {
        Intent intent = new Intent(this, (Class<?>) GeneralTimetableActivity.class);
        intent.putExtra("identifier", TimetableIdentifier.byStreams(this.streamSheduleIds));
        intent.putExtra("title", this.streamName);
        startActivity(intent);
    }

    public void onClickShowStudents(View view) {
        StudentsListActivity.startFromIds(this, this.groups, this.streamName);
    }
}
