package com.m_myr.nuwm.nuwmschedule.ui.framents.event;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.Event;
import com.m_myr.nuwm.nuwmschedule.data.models.EventLinks;
import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.data.repositories.SchedulerProvider;
import com.m_myr.nuwm.nuwmschedule.databinding.EventViewFragmentBinding;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.TimetableStorage;
import com.m_myr.nuwm.nuwmschedule.ui.activities.simpleScreen.AttendeesListActivity;
import com.m_myr.nuwm.nuwmschedule.ui.framents.lesson.LessonActivity;
import com.m_myr.nuwm.nuwmschedule.ui.view.HintHelpView;
import com.m_myr.nuwm.nuwmschedule.utils.LinksResolver;
import java.io.Serializable;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

/* compiled from: EventActivity.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0014J\b\u0010\u000b\u001a\u00020\fH\u0014J\b\u0010\r\u001a\u00020\fH\u0014J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\fH\u0002J\b\u0010\u001b\u001a\u00020\fH\u0002J\b\u0010\u001c\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/framents/event/EventActivity;", "Lcom/m_myr/nuwm/nuwmschedule/ui/framents/lesson/LessonActivity;", "()V", NotificationCompat.CATEGORY_EVENT, "Lcom/m_myr/nuwm/nuwmschedule/data/models/Event;", "merge", "", "view", "Lcom/m_myr/nuwm/nuwmschedule/databinding/EventViewFragmentBinding;", "getColor", "", "initData", "", "initView", "navigateToEventEdit", "id", "", "onClick", "v", "Landroid/view/View;", "onMenuItemClick", "item", "Landroid/view/MenuItem;", "openLesson", "lesson", "Lcom/m_myr/nuwm/nuwmschedule/data/models/Lesson;", "shareLesson", "showMergeInfo", "updatePost", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class EventActivity extends LessonActivity {
    private Event event;
    private boolean merge;
    private EventViewFragmentBinding view;

    @Override // com.m_myr.nuwm.nuwmschedule.ui.framents.lesson.LessonActivity
    protected int getColor() {
        Event event = this.event;
        if (event == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
            event = null;
        }
        return event.getColor();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.framents.lesson.LessonActivity
    protected void initView() {
        EventViewFragmentBinding inflate = EventViewFragmentBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.view = inflate;
        EventViewFragmentBinding eventViewFragmentBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        EventViewFragmentBinding eventViewFragmentBinding2 = this.view;
        if (eventViewFragmentBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            eventViewFragmentBinding2 = null;
        }
        BottomAppBar bottomAppBar = eventViewFragmentBinding2.bottomAppBar;
        Intrinsics.checkNotNullExpressionValue(bottomAppBar, "bottomAppBar");
        bottomAppBar.replaceMenu(R.menu.menu_bottomappbar_lesson);
        bottomAppBar.setOnMenuItemClickListener(this);
        Serializable serializableExtra = getIntent().getSerializableExtra("item");
        if (serializableExtra instanceof EventLinks) {
            Event resolve = ((EventLinks) serializableExtra).resolve();
            Intrinsics.checkNotNullExpressionValue(resolve, "resolve(...)");
            this.event = resolve;
        } else if (serializableExtra instanceof Event) {
            this.event = (Event) serializableExtra;
        }
        Event event = this.event;
        if (event == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
            event = null;
        }
        this.merge = event.isAttachLesson();
        bottomAppBar.setBackgroundTint(ColorStateList.valueOf(getColorBackgroundTop()));
        getWindow().setStatusBarColor(getColorBackground());
        Menu menu = bottomAppBar.getMenu();
        if (AppDataManager.getInstance().getUser().getPermission().isPushTeacher()) {
            menu.findItem(R.id.action_push).setVisible(true);
        }
        Event event2 = this.event;
        if (event2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
            event2 = null;
        }
        if (event2.isSelf()) {
            menu.findItem(R.id.action_edit_to_calendar).setVisible(true);
        }
        if (!getSelf()) {
            EventViewFragmentBinding eventViewFragmentBinding3 = this.view;
            if (eventViewFragmentBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                eventViewFragmentBinding3 = null;
            }
            eventViewFragmentBinding3.fab.hide();
        }
        EventViewFragmentBinding eventViewFragmentBinding4 = this.view;
        if (eventViewFragmentBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            eventViewFragmentBinding4 = null;
        }
        EventActivity eventActivity = this;
        eventViewFragmentBinding4.meetLayout.setOnClickListener(eventActivity);
        EventViewFragmentBinding eventViewFragmentBinding5 = this.view;
        if (eventViewFragmentBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            eventViewFragmentBinding5 = null;
        }
        eventViewFragmentBinding5.mergeLayout.setOnClickListener(eventActivity);
        EventViewFragmentBinding eventViewFragmentBinding6 = this.view;
        if (eventViewFragmentBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        } else {
            eventViewFragmentBinding = eventViewFragmentBinding6;
        }
        eventViewFragmentBinding.groupLayout.setOnClickListener(eventActivity);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.framents.lesson.LessonActivity
    protected void initData() {
        EventViewFragmentBinding eventViewFragmentBinding = this.view;
        EventViewFragmentBinding eventViewFragmentBinding2 = null;
        if (eventViewFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            eventViewFragmentBinding = null;
        }
        eventViewFragmentBinding.staticTeacherField.setText("Організатор");
        EventViewFragmentBinding eventViewFragmentBinding3 = this.view;
        if (eventViewFragmentBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            eventViewFragmentBinding3 = null;
        }
        TextView textView = eventViewFragmentBinding3.locationName;
        Event event = this.event;
        if (event == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
            event = null;
        }
        textView.setText(event.getLocation());
        EventViewFragmentBinding eventViewFragmentBinding4 = this.view;
        if (eventViewFragmentBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            eventViewFragmentBinding4 = null;
        }
        TextView textView2 = eventViewFragmentBinding4.subgroupName;
        Event event2 = this.event;
        if (event2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
            event2 = null;
        }
        textView2.setText(event2.getAttendees());
        EventViewFragmentBinding eventViewFragmentBinding5 = this.view;
        if (eventViewFragmentBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            eventViewFragmentBinding5 = null;
        }
        TextView textView3 = eventViewFragmentBinding5.teacherName;
        Event event3 = this.event;
        if (event3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
            event3 = null;
        }
        textView3.setText(event3.getOrganizer());
        EventViewFragmentBinding eventViewFragmentBinding6 = this.view;
        if (eventViewFragmentBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            eventViewFragmentBinding6 = null;
        }
        TextView textView4 = eventViewFragmentBinding6.strSubject;
        Event event4 = this.event;
        if (event4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
            event4 = null;
        }
        textView4.setText(event4.getTitle());
        Event event5 = this.event;
        if (event5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
            event5 = null;
        }
        String location = event5.getLocation();
        if (location == null || StringsKt.isBlank(location)) {
            EventViewFragmentBinding eventViewFragmentBinding7 = this.view;
            if (eventViewFragmentBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                eventViewFragmentBinding7 = null;
            }
            eventViewFragmentBinding7.locationLayout.setClickable(false);
            Event event6 = this.event;
            if (event6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
                event6 = null;
            }
            if (event6.isMeet()) {
                EventViewFragmentBinding eventViewFragmentBinding8 = this.view;
                if (eventViewFragmentBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    eventViewFragmentBinding8 = null;
                }
                eventViewFragmentBinding8.locationName.setText("Онлайн");
            } else {
                EventViewFragmentBinding eventViewFragmentBinding9 = this.view;
                if (eventViewFragmentBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    eventViewFragmentBinding9 = null;
                }
                eventViewFragmentBinding9.locationName.setText("Немає інформації");
            }
        }
        Event event7 = this.event;
        if (event7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
            event7 = null;
        }
        if (event7.isMultipleDays()) {
            EventViewFragmentBinding eventViewFragmentBinding10 = this.view;
            if (eventViewFragmentBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                eventViewFragmentBinding10 = null;
            }
            TextView textView5 = eventViewFragmentBinding10.timeName;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Event event8 = this.event;
            if (event8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
                event8 = null;
            }
            String startTime = event8.getStartTime();
            Event event9 = this.event;
            if (event9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
                event9 = null;
            }
            String startDateFormat = event9.getStartDateFormat();
            Event event10 = this.event;
            if (event10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
                event10 = null;
            }
            String endTime = event10.getEndTime();
            Event event11 = this.event;
            if (event11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
                event11 = null;
            }
            String format = String.format("%s, %s, -\n%s, %s", Arrays.copyOf(new Object[]{startTime, startDateFormat, endTime, event11.getEndDateFormat()}, 4));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            textView5.setText(format);
        } else {
            EventViewFragmentBinding eventViewFragmentBinding11 = this.view;
            if (eventViewFragmentBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                eventViewFragmentBinding11 = null;
            }
            TextView textView6 = eventViewFragmentBinding11.timeName;
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            Event event12 = this.event;
            if (event12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
                event12 = null;
            }
            String startTime2 = event12.getStartTime();
            Event event13 = this.event;
            if (event13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
                event13 = null;
            }
            String endTime2 = event13.getEndTime();
            Event event14 = this.event;
            if (event14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
                event14 = null;
            }
            String format2 = String.format("%s - %s, %s", Arrays.copyOf(new Object[]{startTime2, endTime2, event14.getStartDateFormat()}, 3));
            Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
            textView6.setText(format2);
        }
        EventViewFragmentBinding eventViewFragmentBinding12 = this.view;
        if (eventViewFragmentBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            eventViewFragmentBinding12 = null;
        }
        TextView textView7 = eventViewFragmentBinding12.typeName;
        Event event15 = this.event;
        if (event15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
            event15 = null;
        }
        textView7.setText(event15.getType());
        Drawable drawable = AppCompatResources.getDrawable(this, R.drawable.rectangle_corner);
        Intrinsics.checkNotNull(drawable);
        Drawable wrap = DrawableCompat.wrap(drawable);
        Intrinsics.checkNotNullExpressionValue(wrap, "wrap(...)");
        Event event16 = this.event;
        if (event16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
            event16 = null;
        }
        DrawableCompat.setTint(wrap, event16.getColor());
        EventViewFragmentBinding eventViewFragmentBinding13 = this.view;
        if (eventViewFragmentBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            eventViewFragmentBinding13 = null;
        }
        eventViewFragmentBinding13.colorMark.setBackground(wrap);
        EventViewFragmentBinding eventViewFragmentBinding14 = this.view;
        if (eventViewFragmentBinding14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            eventViewFragmentBinding14 = null;
        }
        eventViewFragmentBinding14.colorMark.setVisibility(0);
        Event event17 = this.event;
        if (event17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
            event17 = null;
        }
        if (event17.isMeet()) {
            EventViewFragmentBinding eventViewFragmentBinding15 = this.view;
            if (eventViewFragmentBinding15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                eventViewFragmentBinding15 = null;
            }
            eventViewFragmentBinding15.meetLayout.setVisibility(0);
            EventViewFragmentBinding eventViewFragmentBinding16 = this.view;
            if (eventViewFragmentBinding16 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                eventViewFragmentBinding16 = null;
            }
            TextView textView8 = eventViewFragmentBinding16.meetLink;
            Event event18 = this.event;
            if (event18 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
                event18 = null;
            }
            textView8.setText(event18.getHangoutLink());
        }
        EventViewFragmentBinding eventViewFragmentBinding17 = this.view;
        if (eventViewFragmentBinding17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            eventViewFragmentBinding17 = null;
        }
        TextView textView9 = eventViewFragmentBinding17.otherName;
        Event event19 = this.event;
        if (event19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
            event19 = null;
        }
        textView9.setText(Html.fromHtml(event19.getDescription()));
        EventViewFragmentBinding eventViewFragmentBinding18 = this.view;
        if (eventViewFragmentBinding18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            eventViewFragmentBinding18 = null;
        }
        eventViewFragmentBinding18.mergeLayout.postDelayed(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.event.EventActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                EventActivity.initData$lambda$0(EventActivity.this);
            }
        }, 26L);
        Event event20 = this.event;
        if (event20 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
            event20 = null;
        }
        if (event20.isAttachLesson() && this.merge) {
            EventViewFragmentBinding eventViewFragmentBinding19 = this.view;
            if (eventViewFragmentBinding19 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                eventViewFragmentBinding2 = eventViewFragmentBinding19;
            }
            eventViewFragmentBinding2.mergeLayout.setVisibility(0);
            showMergeInfo();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initData$lambda$0(EventActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.updatePost();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.framents.lesson.LessonActivity, android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        int id = v.getId();
        EventViewFragmentBinding eventViewFragmentBinding = this.view;
        Event event = null;
        if (eventViewFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            eventViewFragmentBinding = null;
        }
        if (id == eventViewFragmentBinding.meetLayout.getId()) {
            EventActivity eventActivity = this;
            Event event2 = this.event;
            if (event2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
                event2 = null;
            }
            LinksResolver.ActionView(eventActivity, event2.getHangoutLink());
        }
        EventViewFragmentBinding eventViewFragmentBinding2 = this.view;
        if (eventViewFragmentBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            eventViewFragmentBinding2 = null;
        }
        if (id == eventViewFragmentBinding2.mergeLayout.getId()) {
            Event event3 = this.event;
            if (event3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
                event3 = null;
            }
            String attachLessonUid = event3.getAttachLessonUid();
            TimetableStorage selfForce = SchedulerProvider.getSelfForce();
            Event event4 = this.event;
            if (event4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
                event4 = null;
            }
            Lesson lesson = selfForce.getLesson(event4.getStartDate(), attachLessonUid);
            if (lesson == null) {
                Toast.makeText(this, "Упс..", 0).show();
            } else {
                openLesson(lesson);
            }
        }
        EventViewFragmentBinding eventViewFragmentBinding3 = this.view;
        if (eventViewFragmentBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            eventViewFragmentBinding3 = null;
        }
        if (id == eventViewFragmentBinding3.groupLayout.getId()) {
            Intent intent = new Intent(this, (Class<?>) AttendeesListActivity.class);
            Event event5 = this.event;
            if (event5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
            } else {
                event = event5;
            }
            intent.putExtra("eventId", event.getId());
            startActivity(intent);
        }
    }

    private final void openLesson(Lesson lesson) {
        Lesson lesson2 = lesson;
        Intent intent = new Intent(this, (Class<?>) LessonActivity.class);
        intent.putExtra("self", getSelf());
        Intrinsics.checkNotNull(lesson2, "null cannot be cast to non-null type java.io.Serializable");
        intent.putExtra("item", lesson2);
        startActivity(intent);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.framents.lesson.LessonActivity, androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
    public boolean onMenuItemClick(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        switch (item.getItemId()) {
            case R.id.action_edit_to_calendar /* 2131361912 */:
                Event event = this.event;
                if (event == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
                    event = null;
                }
                String id = event.getId();
                Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
                navigateToEventEdit(id);
                return true;
            case R.id.action_push /* 2131361920 */:
                Toast.makeText(this, "Можливість відправки сповіщення для учасників подій тимчасово обмежена", 1).show();
                return true;
            case R.id.action_share /* 2131361921 */:
                shareLesson();
                return true;
            default:
                return false;
        }
    }

    private final void shareLesson() {
        Event event = this.event;
        if (event == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
            event = null;
        }
        startActivity(event.createShareIntent());
    }

    private final void navigateToEventEdit(String id) {
        EventActivity eventActivity = this;
        Event event = this.event;
        if (event == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_EVENT);
            event = null;
        }
        LinksResolver.ActionView(eventActivity, event.getHtmlLink());
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x008c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void updatePost() {
        /*
            r6 = this;
            com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager r0 = com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager.getInstance()
            boolean r0 = r0.isNuwmUser()
            java.lang.String r1 = "view"
            java.lang.String r2 = "event"
            r3 = 0
            if (r0 == 0) goto L3f
            com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager r0 = com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager.getInstance()
            com.m_myr.nuwm.nuwmschedule.data.models.UserNuwm r0 = r0.getNuwmUser()
            java.lang.String r0 = r0.getEmail()
            com.m_myr.nuwm.nuwmschedule.data.models.Event r4 = r6.event
            if (r4 != 0) goto L23
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r4 = r3
        L23:
            java.lang.String r4 = r4.getOrganizer()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r4)
            if (r0 == 0) goto L3f
            com.m_myr.nuwm.nuwmschedule.databinding.EventViewFragmentBinding r0 = r6.view
            if (r0 != 0) goto L35
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r0 = r3
        L35:
            android.widget.TextView r0 = r0.teacherName
            java.lang.String r4 = "Ви"
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r0.setText(r4)
            goto L68
        L3f:
            r0 = r6
            androidx.lifecycle.LifecycleOwner r0 = (androidx.lifecycle.LifecycleOwner) r0
            com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository$FastRepositoryOwner r0 = com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository.from(r0)
            com.m_myr.nuwm.nuwmschedule.data.models.Event r4 = r6.event
            if (r4 != 0) goto L4e
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r4 = r3
        L4e:
            java.lang.String r4 = r4.getOrganizer()
            com.m_myr.nuwm.nuwmschedule.network.api.ApiRequestBuilder r4 = com.m_myr.nuwm.nuwmschedule.network.api.APIMethods.getEmailInfo(r4)
            com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository r0 = r0.call(r4)
            com.m_myr.nuwm.nuwmschedule.ui.framents.event.EventActivity$updatePost$1 r4 = new com.m_myr.nuwm.nuwmschedule.ui.framents.event.EventActivity$updatePost$1
            r4.<init>()
            com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener r4 = (com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener) r4
            java.lang.Thread r0 = r0.into(r4)
            r0.start()
        L68:
            com.m_myr.nuwm.nuwmschedule.data.models.Event r0 = r6.event
            if (r0 != 0) goto L70
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r0 = r3
        L70:
            int r0 = r0.getAttendees_count()
            r4 = 9999999(0x98967f, float:1.4012983E-38)
            if (r0 < r4) goto L8c
            com.m_myr.nuwm.nuwmschedule.databinding.EventViewFragmentBinding r0 = r6.view
            if (r0 != 0) goto L81
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            goto L82
        L81:
            r3 = r0
        L82:
            android.widget.TextView r0 = r3.subgroupName
            java.lang.String r1 = "Ви та ще багато людей"
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r0.setText(r1)
            goto Ld5
        L8c:
            com.m_myr.nuwm.nuwmschedule.data.models.Event r0 = r6.event
            if (r0 != 0) goto L94
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r0 = r3
        L94:
            int r0 = r0.getAttendees_count()
            if (r0 <= 0) goto Lc3
            com.m_myr.nuwm.nuwmschedule.databinding.EventViewFragmentBinding r0 = r6.view
            if (r0 != 0) goto La2
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r0 = r3
        La2:
            android.widget.TextView r0 = r0.subgroupName
            com.m_myr.nuwm.nuwmschedule.data.models.Event r1 = r6.event
            if (r1 != 0) goto Lac
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            goto Lad
        Lac:
            r3 = r1
        Lad:
            int r1 = r3.getAttendees_count()
            java.lang.String r2 = "учасника"
            java.lang.String r3 = "учасників"
            java.lang.String r4 = "Ви, та ще "
            java.lang.String r5 = "учасник"
            java.lang.String r1 = com.m_myr.nuwm.nuwmschedule.utils.Utils.StringUtils.unitsFormat(r4, r1, r5, r2, r3)
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r0.setText(r1)
            goto Ld5
        Lc3:
            com.m_myr.nuwm.nuwmschedule.databinding.EventViewFragmentBinding r0 = r6.view
            if (r0 != 0) goto Lcb
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            goto Lcc
        Lcb:
            r3 = r0
        Lcc:
            android.widget.TextView r0 = r3.subgroupName
            java.lang.String r1 = "Лише ви"
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r0.setText(r1)
        Ld5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.m_myr.nuwm.nuwmschedule.ui.framents.event.EventActivity.updatePost():void");
    }

    private final void showMergeInfo() {
        EventViewFragmentBinding eventViewFragmentBinding = this.view;
        if (eventViewFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            eventViewFragmentBinding = null;
        }
        eventViewFragmentBinding.listDetals.postDelayed(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.event.EventActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                EventActivity.showMergeInfo$lambda$1(EventActivity.this);
            }
        }, 400L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMergeInfo$lambda$1(EventActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppDataManager.getInstance().getHint(AppDataManager.HINT_mergeLesson)) {
            HintHelpView hintHelpView = new HintHelpView(this$0);
            hintHelpView.attachHint(AppDataManager.HINT_mergeLesson, R.string.merge_lesson, R.drawable.call_merge);
            EventViewFragmentBinding eventViewFragmentBinding = this$0.view;
            if (eventViewFragmentBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                eventViewFragmentBinding = null;
            }
            eventViewFragmentBinding.listDetals.addView(hintHelpView, 0);
        }
    }
}
