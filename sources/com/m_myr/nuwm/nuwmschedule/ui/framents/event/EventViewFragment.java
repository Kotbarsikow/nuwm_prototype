package com.m_myr.nuwm.nuwmschedule.ui.framents.event;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.Event;
import com.m_myr.nuwm.nuwmschedule.data.models.EventLinks;
import com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract;
import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository;
import com.m_myr.nuwm.nuwmschedule.data.repositories.SchedulerProvider;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.TimetableActivityContract;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener;
import com.m_myr.nuwm.nuwmschedule.network.models.EmailInfo;
import com.m_myr.nuwm.nuwmschedule.ui.activities.sendPush.SendPushActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.simpleScreen.AttendeesListActivity;
import com.m_myr.nuwm.nuwmschedule.ui.framents.lesson.LessonViewFragment;
import com.m_myr.nuwm.nuwmschedule.ui.view.HintHelpView;
import com.m_myr.nuwm.nuwmschedule.utils.LinksResolver;
import com.m_myr.nuwm.nuwmschedule.utils.ResourcesHelper;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;

@Deprecated
/* loaded from: classes2.dex */
public class EventViewFragment extends Fragment implements Toolbar.OnMenuItemClickListener, View.OnClickListener {
    Event event;
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
    private LinearLayout mMergeLayout;
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
    private boolean merge;
    private boolean self;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.event_view_fragment, container, false);
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
        this.mColorMark = inflate.findViewById(R.id.color_mark);
        this.mMeetLink = (TextView) inflate.findViewById(R.id.meet_link);
        this.mMeetLayout = (LinearLayout) inflate.findViewById(R.id.meet_layout);
        this.mMergeLayout = (LinearLayout) inflate.findViewById(R.id.merge_layout);
        Bundle arguments = getArguments();
        this.self = arguments.getBoolean("self");
        ItemTimetableContract itemTimetableContract = (ItemTimetableContract) arguments.getSerializable("item");
        if (itemTimetableContract instanceof EventLinks) {
            this.event = ((EventLinks) itemTimetableContract).resolve();
        } else if (itemTimetableContract instanceof Event) {
            this.event = (Event) itemTimetableContract;
        }
        this.merge = this.event.isAttachLesson();
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.e("LessonViewFragment", "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).setSupportActionBar(this.mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.mToolbar.setNavigationIcon(R.drawable.ic_close);
        this.mToolbar.getNavigationIcon().setTint(ResourcesHelper.getAttrColor(this, R.attr.colorTint));
        this.mToolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.event.EventViewFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EventViewFragment.this.onBack();
            }
        });
        this.mMeetLayout.setOnClickListener(this);
        this.mMergeLayout.setOnClickListener(this);
        this.mGroupLayout.setOnClickListener(this);
        BottomAppBar bottomAppBar = (BottomAppBar) view.findViewById(R.id.bottom_app_bar);
        bottomAppBar.replaceMenu(R.menu.menu_bottomappbar_event);
        bottomAppBar.setOnMenuItemClickListener(this);
        Menu menu = bottomAppBar.getMenu();
        if (AppDataManager.getInstance().getUser().getPermission().isPushTeacher()) {
            menu.findItem(R.id.action_push).setVisible(true);
        }
        if (this.event.isSelf()) {
            menu.findItem(R.id.action_edit_to_calendar).setVisible(true);
        }
        if (!this.self) {
            this.mFab.hide();
        }
        updateData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBack() {
        getParentFragmentManager().popBackStack();
    }

    private void updateData() {
        this.mStaticTeacherField.setText("Організатор");
        this.mLocationName.setText(this.event.getLocation());
        this.mSubgroupName.setText(this.event.getAttendees());
        this.mTeacherName.setText(this.event.getOrganizer());
        this.mStrSubject.setText(this.event.getTitle());
        if (this.event.isMultipleDays()) {
            this.mTimeName.setText(String.format("%s, %s, -\n%s, %s", this.event.getStartTime(), this.event.getStartDateFormat(), this.event.getEndTime(), this.event.getEndDateFormat()));
        } else {
            this.mTimeName.setText(String.format("%s - %s, %s", this.event.getStartTime(), this.event.getEndTime(), this.event.getStartDateFormat()));
        }
        this.mTypeName.setText(this.event.getType());
        Drawable wrap = DrawableCompat.wrap(AppCompatResources.getDrawable(getContext(), R.drawable.rectangle_corner));
        DrawableCompat.setTint(wrap, this.event.getColor());
        this.mColorMark.setBackground(wrap);
        this.mColorMark.setVisibility(0);
        if (this.event.isMeet()) {
            this.mMeetLayout.setVisibility(0);
            this.mMeetLink.setText(this.event.getHangoutLink());
        }
        this.mOtherName.setText(Html.fromHtml(this.event.getDescription()));
        getView().postDelayed(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.event.EventViewFragment.2
            @Override // java.lang.Runnable
            public void run() {
                EventViewFragment.this.updatePost();
            }
        }, 26L);
        if (this.event.isAttachLesson() && this.merge) {
            this.mMergeLayout.setVisibility(0);
            showMergeInfo();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePost() {
        if (AppDataManager.getInstance().isNuwmUser() && AppDataManager.getInstance().getNuwmUser().getEmail().equals(this.event.getOrganizer())) {
            this.mTeacherName.setText("Ви");
        } else {
            FastRepository.from(this).call(APIMethods.getEmailInfo(this.event.getOrganizer())).into((APIObjectListener) new APIObjectListener<EmailInfo>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.event.EventViewFragment.3
                @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
                public void onError(ErrorResponse response) {
                }

                @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
                public void onSuccessData(EmailInfo data) {
                    EventViewFragment.this.mTeacherName.setText(data.getFullname());
                }
            }).start();
        }
        if (this.event.getAttendees_count() >= 9999999) {
            this.mSubgroupName.setText("Ви та ще багато людей");
        } else if (this.event.getAttendees_count() > 0) {
            this.mSubgroupName.setText(Utils.StringUtils.unitsFormat("Ви, та ще ", this.event.getAttendees_count(), "учасник", "учасника", "учасників"));
        } else {
            this.mSubgroupName.setText("Лише ви");
        }
    }

    private void showMergeInfo() {
        getView().postDelayed(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.event.EventViewFragment.4
            @Override // java.lang.Runnable
            public void run() {
                if (AppDataManager.getInstance().getHint(AppDataManager.HINT_mergeLesson)) {
                    HintHelpView hintHelpView = new HintHelpView(EventViewFragment.this.getContext());
                    hintHelpView.attachHint(AppDataManager.HINT_mergeLesson, R.string.merge_lesson, R.drawable.call_merge);
                    EventViewFragment.this.mListDetals.addView(hintHelpView, 0);
                }
            }
        }, 400L);
    }

    @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit_to_calendar /* 2131361912 */:
                navigateToEventEdit(this.event.getId());
                return true;
            case R.id.action_push /* 2131361920 */:
                Toast.makeText(getContext(), "Можливість відправки сповіщення для учасників подій тимчасово обмежена", 1).show();
                return true;
            case R.id.action_share /* 2131361921 */:
                shareLesson();
                return true;
            default:
                return false;
        }
    }

    private void navigateToEventCreatePush() {
        startActivity(new Intent(getContext(), (Class<?>) SendPushActivity.class));
    }

    private void shareLesson() {
        startActivity(this.event.createShareIntent());
    }

    private void navigateToEventEdit(String id) {
        LinksResolver.ActionView(getContext(), this.event.getHtmlLink());
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234) {
            if (getActivity() instanceof TimetableActivityContract) {
                ((TimetableActivityContract) getActivity()).onRefresh();
            }
            onBack();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        int id = v.getId();
        if (id == this.mMeetLayout.getId()) {
            LinksResolver.ActionView(getContext(), this.event.getHangoutLink());
        }
        if (id == this.mMergeLayout.getId()) {
            Lesson lesson = SchedulerProvider.getSelfForce().getLesson(this.event.getStartDate(), this.event.getAttachLessonUid());
            if (lesson == null) {
                Toast.makeText(getContext(), "Упс..", 0).show();
            } else {
                openLesson(lesson);
            }
        }
        if (id == this.mGroupLayout.getId()) {
            Intent intent = new Intent(getContext(), (Class<?>) AttendeesListActivity.class);
            intent.putExtra("eventId", this.event.getId());
            startActivity(intent);
        }
    }

    private void openLesson(Lesson lesson) {
        Bundle bundle = new Bundle();
        LessonViewFragment lessonViewFragment = new LessonViewFragment();
        bundle.putBoolean("self", this.self);
        bundle.putSerializable("item", lesson);
        lessonViewFragment.setArguments(bundle);
        FragmentTransaction beginTransaction = ((AppCompatActivity) getActivity()).getSupportFragmentManager().beginTransaction();
        beginTransaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down, R.anim.slide_up, R.anim.slide_down);
        beginTransaction.add(android.R.id.content, lessonViewFragment);
        beginTransaction.addToBackStack(null);
        beginTransaction.commit();
    }
}
