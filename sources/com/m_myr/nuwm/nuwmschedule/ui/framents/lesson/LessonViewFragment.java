package com.m_myr.nuwm.nuwmschedule.ui.framents.lesson;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.models.LessonEvent;
import com.m_myr.nuwm.nuwmschedule.data.models.LessonUser;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.data.repositories.FastCallRepository;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.TimetableActivityContract;
import com.m_myr.nuwm.nuwmschedule.network.APIMethod;
import com.m_myr.nuwm.nuwmschedule.network.APIObjectOkListener;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.ui.activities.groups.GroupsProfileActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.sendPush.SendPushActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.simpleScreen.StudentsListActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.stream.StreamProfileActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.user.UserProfileActivity;
import com.m_myr.nuwm.nuwmschedule.utils.ResourcesHelper;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;

/* loaded from: classes2.dex */
public class LessonViewFragment extends Fragment implements Toolbar.OnMenuItemClickListener, View.OnClickListener {
    Lesson lesson;
    private BottomAppBar mBottomAppBar;
    private ImageButton mCancel;
    private View mColorMark;
    private TextInputEditText mEditNoteView;
    private FloatingActionButton mFab;
    private LinearLayout mGroupLayout;
    private LinearLayout mLineTypeName;
    private LinearLayout mListDetals;
    private LinearLayout mLocationLayout;
    private TextView mLocationName;
    private CoordinatorLayout mMainContent;
    private LinearLayout mMeetLayout;
    private TextView mMeetLink;
    private LinearLayout mNoteEdit;
    private RelativeLayout mNoteLayout;
    private AppCompatTextView mNoteTextView;
    private LinearLayout mNoteView;
    private LinearLayout mOtherLayout;
    private TextView mOtherName;
    private ImageButton mSave;
    private TextView mStaticTeacherField;
    private TextView mStrSubject;
    private TextView mSubgroupName;
    private LinearLayout mTeacherLayout;
    private TextView mTeacherName;
    private TextView mTimeName;
    private Toolbar mToolbar;
    private TextView mTypeName;
    private boolean self;

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postponeEnterTransition();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.lesson_view_fragment, container, false);
        this.mToolbar = (Toolbar) inflate.findViewById(R.id.toolbar);
        this.mTeacherName = (TextView) inflate.findViewById(R.id.teacher_name);
        this.mStrSubject = (TextView) inflate.findViewById(R.id.str_subject);
        this.mStaticTeacherField = (TextView) inflate.findViewById(R.id.static_teacher_field);
        this.mTeacherLayout = (LinearLayout) inflate.findViewById(R.id.teacher_layout);
        this.mNoteTextView = (AppCompatTextView) inflate.findViewById(R.id.note_text_view);
        this.mNoteView = (LinearLayout) inflate.findViewById(R.id.note_view);
        this.mEditNoteView = (TextInputEditText) inflate.findViewById(R.id.edit_note_view);
        this.mSave = (ImageButton) inflate.findViewById(R.id.save);
        this.mCancel = (ImageButton) inflate.findViewById(R.id.cancel);
        this.mNoteEdit = (LinearLayout) inflate.findViewById(R.id.note_edit);
        this.mNoteLayout = (RelativeLayout) inflate.findViewById(R.id.note_layout);
        this.mTypeName = (TextView) inflate.findViewById(R.id.type_name);
        this.mLineTypeName = (LinearLayout) inflate.findViewById(R.id.line_type_name);
        this.mSubgroupName = (TextView) inflate.findViewById(R.id.subgroup_name);
        this.mGroupLayout = (LinearLayout) inflate.findViewById(R.id.group_layout);
        this.mTimeName = (TextView) inflate.findViewById(R.id.time_name);
        this.mLocationName = (TextView) inflate.findViewById(R.id.location_name);
        this.mLocationLayout = (LinearLayout) inflate.findViewById(R.id.location_layout);
        this.mOtherName = (TextView) inflate.findViewById(R.id.other_name);
        this.mOtherLayout = (LinearLayout) inflate.findViewById(R.id.other_layout);
        this.mListDetals = (LinearLayout) inflate.findViewById(R.id.list_detals);
        this.mBottomAppBar = (BottomAppBar) inflate.findViewById(R.id.bottom_app_bar);
        this.mFab = (FloatingActionButton) inflate.findViewById(R.id.fab);
        this.mMainContent = (CoordinatorLayout) inflate.findViewById(R.id.main_content);
        this.lesson = (Lesson) getArguments().getSerializable("item");
        this.self = getArguments().getBoolean("self");
        this.mColorMark = inflate.findViewById(R.id.color_mark);
        this.mMeetLink = (TextView) inflate.findViewById(R.id.meet_link);
        this.mMeetLayout = (LinearLayout) inflate.findViewById(R.id.meet_layout);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).setSupportActionBar(this.mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.mToolbar.setNavigationIcon(R.drawable.ic_close);
        this.mToolbar.getNavigationIcon().setTint(ResourcesHelper.getAttrColor(this, R.attr.colorTint));
        this.mToolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.lesson.LessonViewFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                LessonViewFragment.this.m202x470c08f(view2);
            }
        });
        this.mGroupLayout.setOnClickListener(this);
        BottomAppBar bottomAppBar = (BottomAppBar) view.findViewById(R.id.bottom_app_bar);
        bottomAppBar.replaceMenu(R.menu.menu_bottomappbar_lesson);
        bottomAppBar.setOnMenuItemClickListener(this);
        if (AppDataManager.getInstance().isTeacher()) {
            Menu menu = bottomAppBar.getMenu();
            menu.findItem(R.id.action_push).setVisible(true);
            if (this.lesson instanceof LessonEvent) {
                menu.findItem(R.id.action_edit_to_calendar).setVisible(true);
            } else {
                menu.findItem(R.id.action_add_to_calendar).setVisible(true);
            }
            if (this.lesson instanceof LessonUser) {
                menu.findItem(R.id.action_edit).setVisible(true);
            }
        }
        if (!this.self) {
            Menu menu2 = bottomAppBar.getMenu();
            this.mFab.hide();
            menu2.findItem(R.id.action_edit).setVisible(false);
            menu2.findItem(R.id.action_push).setVisible(false);
            menu2.findItem(R.id.action_add_to_calendar).setVisible(false);
        }
        updateData();
        Log.e(" generateInternalUid ", "lesson.generateInternalUid " + this.lesson.generateInternalUid());
        startPostponedEnterTransition();
    }

    /* renamed from: lambda$onViewCreated$0$com-m_myr-nuwm-nuwmschedule-ui-framents-lesson-LessonViewFragment, reason: not valid java name */
    /* synthetic */ void m202x470c08f(View view) {
        onBack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBack() {
        getParentFragmentManager().popBackStack();
    }

    private void updateData() {
        if (this.self && AppDataManager.getInstance().isTeacher()) {
            this.mTeacherName.setText("Ви");
        } else {
            this.mTeacherName.setText(this.lesson.getTeacher());
            if (this.lesson.getTeacher().contains("доцент")) {
                this.mStaticTeacherField.setText("Доцент");
                this.mTeacherName.setText(this.lesson.getTeacher().replace("доцент", "").trim());
            } else if (this.lesson.getTeacher().contains("ст. викладач")) {
                this.mStaticTeacherField.setText("Старший викладач");
                this.mTeacherName.setText(this.lesson.getTeacher().replace("ст. викладач", "").trim());
            }
            this.mTeacherLayout.setTag(this.mTeacherName.getText().toString());
            this.mTeacherLayout.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.lesson.LessonViewFragment$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LessonViewFragment.this.onClickPerson(view);
                }
            });
        }
        this.mStrSubject.setText(this.lesson.getSubject());
        this.mTypeName.setText(this.lesson.getType());
        this.mSubgroupName.setText(this.lesson.getSubgroup());
        this.mTimeName.setText(String.format("%s - %s, %s", this.lesson.getStartTime(), this.lesson.getEndTime(), this.lesson.getStartDateFormat()));
        this.mLocationName.setText(this.lesson.getRoom());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClickPerson(View view) {
        UserProfileActivity.findByName(getContext(), (String) view.getTag());
    }

    @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_to_calendar /* 2131361900 */:
                navigateToEventAdd();
                return true;
            case R.id.action_push /* 2131361920 */:
                navigateToEventCreatePush();
                return true;
            case R.id.action_share /* 2131361921 */:
                shareLesson();
                return true;
            default:
                return false;
        }
    }

    private void navigateToEventAdd() {
        if (AppDataManager.getInstance().getHint(AppDataManager.HINT_eventAdd)) {
            Utils.createAlertInfo(getContext(), AppDataManager.HINT_eventAdd, R.string.hint_eventadd);
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Створення події");
        builder.setMessage("Створити подію для всіх студентів (" + this.lesson.getAttendees() + ") цього заняття?");
        builder.setPositiveButton("Створити подію Google Meet", new DialogInterface.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.lesson.LessonViewFragment.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                LessonViewFragment.this.createMeet();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Лише собі", new DialogInterface.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.lesson.LessonViewFragment.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                LessonViewFragment.this.createMyEvent();
                dialog.dismiss();
            }
        });
        builder.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createMyEvent() {
        FastCallRepository fastCallRepository = new FastCallRepository();
        Lesson lesson = this.lesson;
        fastCallRepository.call(APIMethod.createEvent(lesson, lesson.generateInternalUid())).into(new APIObjectOkListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.lesson.LessonViewFragment.3
            @Override // com.m_myr.nuwm.nuwmschedule.network.APIObjectOkListener
            public void onSuccess() {
                Toast.makeText(LessonViewFragment.this.getActivity(), "Подію створено", 0).show();
                if (LessonViewFragment.this.getActivity() instanceof TimetableActivityContract) {
                    ((TimetableActivityContract) LessonViewFragment.this.getActivity()).onRefresh();
                }
                LessonViewFragment.this.onBack();
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.APIObjectOkListener, com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener
            protected void onError(ErrorResponse response) {
                Toast.makeText(LessonViewFragment.this.getActivity(), response.getMessage(), 0).show();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createMeet() {
        FastCallRepository fastCallRepository = new FastCallRepository();
        Lesson lesson = this.lesson;
        fastCallRepository.call(APIMethod.createLesson(lesson, lesson.generateInternalUid())).into(new APIObjectOkListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.lesson.LessonViewFragment.4
            @Override // com.m_myr.nuwm.nuwmschedule.network.APIObjectOkListener, com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener
            protected void onError(ErrorResponse response) {
                Toast.makeText(LessonViewFragment.this.getActivity(), response.getMessage(), 0).show();
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.APIObjectOkListener
            public void onSuccess() {
                Toast.makeText(LessonViewFragment.this.getActivity(), "Подію створено", 0).show();
                if (LessonViewFragment.this.getActivity() instanceof TimetableActivityContract) {
                    ((TimetableActivityContract) LessonViewFragment.this.getActivity()).onRefresh();
                }
                LessonViewFragment.this.onBack();
            }
        }).start();
    }

    private void navigateToEventCreatePush() {
        Intent intent = new Intent(getContext(), (Class<?>) SendPushActivity.class);
        intent.putExtra("lesson", this.lesson);
        startActivity(intent);
    }

    private void shareLesson() {
        startActivity(this.lesson.createShareIntent());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (v.getId() == this.mGroupLayout.getId()) {
            if ("вся група".equals(this.mSubgroupName.getText().toString().trim())) {
                if (this.self) {
                    startActivity(new Intent(getContext(), (Class<?>) StudentsListActivity.class));
                    return;
                } else {
                    this.mGroupLayout.setClickable(false);
                    return;
                }
            }
            if (this.mSubgroupName.getText().toString().contains("підгр")) {
                if (this.self) {
                    startActivity(new Intent(getContext(), (Class<?>) StudentsListActivity.class));
                    return;
                }
                GroupsProfileActivity.startByName(getContext(), this.lesson.getSubgroup().substring(0, Math.min(this.lesson.getSubgroup().indexOf(32), this.lesson.getSubgroup().length())));
                return;
            }
            if (this.lesson.getStreamConsists().isEmpty()) {
                GroupsProfileActivity.startByName(getContext(), this.lesson.getSubgroup().trim());
            } else {
                StreamProfileActivity.startByScheduleIds(getContext(), this.mSubgroupName.getText().toString(), this.lesson.getStreamConsists());
            }
        }
    }
}
