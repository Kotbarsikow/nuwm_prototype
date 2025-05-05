package com.m_myr.nuwm.nuwmschedule.ui.framents.lesson;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.transition.platform.MaterialContainerTransform;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;
import com.hootsuite.nachos.tokenizer.SpanChipTokenizer;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.models.LessonEvent;
import com.m_myr.nuwm.nuwmschedule.data.models.LessonUser;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.data.repositories.FastCallRepository;
import com.m_myr.nuwm.nuwmschedule.databinding.LessonViewFragmentBinding;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.network.APIMethod;
import com.m_myr.nuwm.nuwmschedule.network.APIObjectOkListener;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.models.EmptyResult;
import com.m_myr.nuwm.nuwmschedule.ui.activities.BaseToolbarActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.groups.GroupsProfileActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.sendPush.SendPushActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.simpleScreen.StudentsListActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.stream.StreamProfileActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.user.UserProfileActivity;
import com.m_myr.nuwm.nuwmschedule.utils.ResourcesHelper;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.io.Serializable;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

/* compiled from: LessonActivity.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0013H\u0014J\b\u0010\u0015\u001a\u00020\u0013H\u0014J\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017H\u0002J\b\u0010\u0019\u001a\u00020\u0010H\u0014J\b\u0010\u001a\u001a\u00020\u0010H\u0014J\b\u0010\u001b\u001a\u00020\u0010H\u0002J\b\u0010\u001c\u001a\u00020\u0010H\u0002J\u0010\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u001fH\u0002J\u0012\u0010!\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017H\u0014J\u0010\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020\u0010H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/framents/lesson/LessonActivity;", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/BaseToolbarActivity;", "Landroid/view/View$OnClickListener;", "Landroidx/appcompat/widget/Toolbar$OnMenuItemClickListener;", "()V", "lesson", "Lcom/m_myr/nuwm/nuwmschedule/data/models/Lesson;", "self", "", "getSelf", "()Z", "setSelf", "(Z)V", "view", "Lcom/m_myr/nuwm/nuwmschedule/databinding/LessonViewFragmentBinding;", "createMeet", "", "createMyEvent", "getColor", "", "getColorBackground", "getColorBackgroundTop", "initActivity", "Landroid/os/Bundle;", "savedInstanceState", "initData", "initView", "navigateToEventAdd", "navigateToEventCreatePush", "onClick", "v", "Landroid/view/View;", "onClickPerson", "onCreate", "onMenuItemClick", "item", "Landroid/view/MenuItem;", "shareLesson", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class LessonActivity extends BaseToolbarActivity implements View.OnClickListener, Toolbar.OnMenuItemClickListener {
    private Lesson lesson;
    private boolean self;
    private LessonViewFragmentBinding view;

    protected final boolean getSelf() {
        return this.self;
    }

    protected final void setSelf(boolean z) {
        this.self = z;
    }

    protected int getColor() {
        Lesson lesson = this.lesson;
        if (lesson == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lesson");
            lesson = null;
        }
        return lesson.getColor();
    }

    protected int getColorBackground() {
        float[] fArr = new float[3];
        if (getColor() == 1) {
            Color.colorToHSV(ResourcesHelper.getAttrColor(this, R.attr.colorAccent), fArr);
        } else {
            Color.colorToHSV(getColor(), fArr);
        }
        if (ResourcesHelper.getIsNightMode(this)) {
            fArr[1] = 0.32f;
            fArr[2] = 0.22f;
        } else {
            fArr[1] = 0.04f;
            fArr[2] = 1.0f;
        }
        return Color.HSVToColor(fArr);
    }

    protected int getColorBackgroundTop() {
        float[] fArr = new float[3];
        if (getColor() == 1) {
            Color.colorToHSV(ResourcesHelper.getAttrColor(this, R.attr.colorAccent), fArr);
        } else {
            Color.colorToHSV(getColor(), fArr);
        }
        if (ResourcesHelper.getIsNightMode(this)) {
            fArr[1] = 0.32f;
            fArr[2] = 0.15f;
        } else {
            fArr[1] = 0.09f;
            fArr[2] = 0.92f;
        }
        return Color.HSVToColor(fArr);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(App.getInstance().getAppTheme());
        super.onCreate(initActivity(savedInstanceState));
        initView();
        attachToolbar();
        getMaterialToolbar().setNavigationIcon(R.drawable.ic_close);
        Drawable navigationIcon = getMaterialToolbar().getNavigationIcon();
        if (navigationIcon != null) {
            navigationIcon.setTint(ResourcesHelper.getAttrColor(this, R.attr.colorOnSurface));
        }
        this.self = getIntent().getBooleanExtra("self", false);
        findViewById(R.id.main_frame_lesson).setBackgroundColor(getColorBackground());
        initData();
        startPostponedEnterTransition();
    }

    private final Bundle initActivity(Bundle savedInstanceState) {
        postponeEnterTransition();
        setTheme(App.getInstance().getAppTheme());
        getWindow().requestFeature(13);
        setEnterSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
        findViewById(android.R.id.content).setTransitionName(getIntent().getStringExtra("sharedElementName"));
        Window window = getWindow();
        MaterialContainerTransform materialContainerTransform = new MaterialContainerTransform();
        materialContainerTransform.addTarget(android.R.id.content);
        materialContainerTransform.setDuration(500L);
        window.setSharedElementEnterTransition(materialContainerTransform);
        Window window2 = getWindow();
        MaterialContainerTransform materialContainerTransform2 = new MaterialContainerTransform();
        materialContainerTransform2.addTarget(android.R.id.content);
        materialContainerTransform2.setDuration(300L);
        window2.setSharedElementReturnTransition(materialContainerTransform2);
        return savedInstanceState;
    }

    protected void initView() {
        LessonViewFragmentBinding inflate = LessonViewFragmentBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.view = inflate;
        LessonViewFragmentBinding lessonViewFragmentBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        Serializable serializableExtra = getIntent().getSerializableExtra("item");
        Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.m_myr.nuwm.nuwmschedule.data.models.Lesson");
        this.lesson = (Lesson) serializableExtra;
        LessonViewFragmentBinding lessonViewFragmentBinding2 = this.view;
        if (lessonViewFragmentBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            lessonViewFragmentBinding2 = null;
        }
        lessonViewFragmentBinding2.groupLayout.setOnClickListener(this);
        LessonViewFragmentBinding lessonViewFragmentBinding3 = this.view;
        if (lessonViewFragmentBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            lessonViewFragmentBinding3 = null;
        }
        BottomAppBar bottomAppBar = lessonViewFragmentBinding3.bottomAppBar;
        Intrinsics.checkNotNullExpressionValue(bottomAppBar, "bottomAppBar");
        bottomAppBar.replaceMenu(R.menu.menu_bottomappbar_lesson);
        bottomAppBar.setOnMenuItemClickListener(this);
        Color.colorToHSV(getColorBackground(), new float[3]);
        bottomAppBar.setBackgroundTint(ColorStateList.valueOf(getColorBackgroundTop()));
        getWindow().setStatusBarColor(getColorBackground());
        if (AppDataManager.getInstance().isTeacher()) {
            Menu menu = bottomAppBar.getMenu();
            menu.findItem(R.id.action_push).setVisible(true);
            Lesson lesson = this.lesson;
            if (lesson == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lesson");
                lesson = null;
            }
            if (lesson instanceof LessonEvent) {
                menu.findItem(R.id.action_edit_to_calendar).setVisible(true);
            } else {
                menu.findItem(R.id.action_add_to_calendar).setVisible(true);
            }
            Lesson lesson2 = this.lesson;
            if (lesson2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lesson");
                lesson2 = null;
            }
            if (lesson2 instanceof LessonUser) {
                menu.findItem(R.id.action_edit).setVisible(true);
            }
        }
        if (this.self) {
            return;
        }
        Menu menu2 = bottomAppBar.getMenu();
        LessonViewFragmentBinding lessonViewFragmentBinding4 = this.view;
        if (lessonViewFragmentBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        } else {
            lessonViewFragmentBinding = lessonViewFragmentBinding4;
        }
        lessonViewFragmentBinding.fab.hide();
        menu2.findItem(R.id.action_edit).setVisible(false);
        menu2.findItem(R.id.action_push).setVisible(false);
        menu2.findItem(R.id.action_add_to_calendar).setVisible(false);
    }

    protected void initData() {
        Lesson lesson = null;
        if (this.self && AppDataManager.getInstance().isTeacher()) {
            LessonViewFragmentBinding lessonViewFragmentBinding = this.view;
            if (lessonViewFragmentBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                lessonViewFragmentBinding = null;
            }
            lessonViewFragmentBinding.teacherName.setText("Ви");
        } else {
            Lesson lesson2 = this.lesson;
            if (lesson2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lesson");
                lesson2 = null;
            }
            String teacher = lesson2.getTeacher();
            LessonViewFragmentBinding lessonViewFragmentBinding2 = this.view;
            if (lessonViewFragmentBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                lessonViewFragmentBinding2 = null;
            }
            String str = teacher;
            lessonViewFragmentBinding2.teacherName.setText(str);
            Intrinsics.checkNotNull(teacher);
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) "ст. викладач", false, 2, (Object) null)) {
                LessonViewFragmentBinding lessonViewFragmentBinding3 = this.view;
                if (lessonViewFragmentBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    lessonViewFragmentBinding3 = null;
                }
                lessonViewFragmentBinding3.staticTeacherField.setText("Старший викладач");
                LessonViewFragmentBinding lessonViewFragmentBinding4 = this.view;
                if (lessonViewFragmentBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    lessonViewFragmentBinding4 = null;
                }
                TextView textView = lessonViewFragmentBinding4.teacherName;
                String replace$default = StringsKt.replace$default(teacher, "ст. викладач", "", false, 4, (Object) null);
                int length = replace$default.length() - 1;
                int i = 0;
                boolean z = false;
                while (i <= length) {
                    boolean z2 = Intrinsics.compare((int) replace$default.charAt(!z ? i : length), 32) <= 0;
                    if (z) {
                        if (!z2) {
                            break;
                        } else {
                            length--;
                        }
                    } else if (z2) {
                        i++;
                    } else {
                        z = true;
                    }
                }
                textView.setText(replace$default.subSequence(i, length + 1).toString());
            } else if (str.length() > 0 && !Character.isUpperCase(teacher.charAt(0))) {
                int length2 = str.length();
                int i2 = 0;
                while (true) {
                    if (i2 >= length2) {
                        i2 = -1;
                        break;
                    } else if (Character.isUpperCase(str.charAt(i2))) {
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i2 > 0) {
                    LessonViewFragmentBinding lessonViewFragmentBinding5 = this.view;
                    if (lessonViewFragmentBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view");
                        lessonViewFragmentBinding5 = null;
                    }
                    TextView textView2 = lessonViewFragmentBinding5.staticTeacherField;
                    String substring = teacher.substring(0, i2);
                    Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                    textView2.setText(StringsKt.capitalize(substring));
                    LessonViewFragmentBinding lessonViewFragmentBinding6 = this.view;
                    if (lessonViewFragmentBinding6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view");
                        lessonViewFragmentBinding6 = null;
                    }
                    TextView textView3 = lessonViewFragmentBinding6.teacherName;
                    String substring2 = teacher.substring(i2);
                    Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String).substring(startIndex)");
                    textView3.setText(substring2);
                }
            }
            LessonViewFragmentBinding lessonViewFragmentBinding7 = this.view;
            if (lessonViewFragmentBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                lessonViewFragmentBinding7 = null;
            }
            LinearLayout linearLayout = lessonViewFragmentBinding7.teacherLayout;
            LessonViewFragmentBinding lessonViewFragmentBinding8 = this.view;
            if (lessonViewFragmentBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                lessonViewFragmentBinding8 = null;
            }
            linearLayout.setTag(lessonViewFragmentBinding8.teacherName.getText().toString());
            LessonViewFragmentBinding lessonViewFragmentBinding9 = this.view;
            if (lessonViewFragmentBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                lessonViewFragmentBinding9 = null;
            }
            lessonViewFragmentBinding9.teacherLayout.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.lesson.LessonActivity$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LessonActivity.initData$lambda$4(LessonActivity.this, view);
                }
            });
        }
        LessonViewFragmentBinding lessonViewFragmentBinding10 = this.view;
        if (lessonViewFragmentBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            lessonViewFragmentBinding10 = null;
        }
        TextView textView4 = lessonViewFragmentBinding10.strSubject;
        Lesson lesson3 = this.lesson;
        if (lesson3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lesson");
            lesson3 = null;
        }
        textView4.setText(lesson3.getSubject());
        LessonViewFragmentBinding lessonViewFragmentBinding11 = this.view;
        if (lessonViewFragmentBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            lessonViewFragmentBinding11 = null;
        }
        TextView textView5 = lessonViewFragmentBinding11.typeName;
        Lesson lesson4 = this.lesson;
        if (lesson4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lesson");
            lesson4 = null;
        }
        textView5.setText(lesson4.getType());
        LessonViewFragmentBinding lessonViewFragmentBinding12 = this.view;
        if (lessonViewFragmentBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            lessonViewFragmentBinding12 = null;
        }
        TextView textView6 = lessonViewFragmentBinding12.subgroupName;
        Lesson lesson5 = this.lesson;
        if (lesson5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lesson");
            lesson5 = null;
        }
        textView6.setText(lesson5.getSubgroup());
        LessonViewFragmentBinding lessonViewFragmentBinding13 = this.view;
        if (lessonViewFragmentBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            lessonViewFragmentBinding13 = null;
        }
        TextView textView7 = lessonViewFragmentBinding13.timeName;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Lesson lesson6 = this.lesson;
        if (lesson6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lesson");
            lesson6 = null;
        }
        String startTime = lesson6.getStartTime();
        Lesson lesson7 = this.lesson;
        if (lesson7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lesson");
            lesson7 = null;
        }
        String endTime = lesson7.getEndTime();
        Lesson lesson8 = this.lesson;
        if (lesson8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lesson");
            lesson8 = null;
        }
        String format = String.format("%s - %s, %s", Arrays.copyOf(new Object[]{startTime, endTime, lesson8.getStartDateFormat()}, 3));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        textView7.setText(format);
        LessonViewFragmentBinding lessonViewFragmentBinding14 = this.view;
        if (lessonViewFragmentBinding14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            lessonViewFragmentBinding14 = null;
        }
        TextView textView8 = lessonViewFragmentBinding14.locationName;
        Lesson lesson9 = this.lesson;
        if (lesson9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lesson");
        } else {
            lesson = lesson9;
        }
        textView8.setText(lesson.getRoom());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initData$lambda$4(LessonActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "view");
        this$0.onClickPerson(view);
    }

    private final void onClickPerson(View view) {
        Object tag = view.getTag();
        Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type kotlin.String");
        UserProfileActivity.findByName(this, (String) tag);
    }

    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        int id = v.getId();
        LessonViewFragmentBinding lessonViewFragmentBinding = this.view;
        Lesson lesson = null;
        LessonViewFragmentBinding lessonViewFragmentBinding2 = null;
        Lesson lesson2 = null;
        Lesson lesson3 = null;
        if (lessonViewFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            lessonViewFragmentBinding = null;
        }
        if (id == lessonViewFragmentBinding.groupLayout.getId()) {
            LessonViewFragmentBinding lessonViewFragmentBinding3 = this.view;
            if (lessonViewFragmentBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                lessonViewFragmentBinding3 = null;
            }
            String obj = lessonViewFragmentBinding3.subgroupName.getText().toString();
            int length = obj.length() - 1;
            int i = 0;
            boolean z = false;
            while (i <= length) {
                boolean z2 = Intrinsics.compare((int) obj.charAt(!z ? i : length), 32) <= 0;
                if (z) {
                    if (!z2) {
                        break;
                    } else {
                        length--;
                    }
                } else if (z2) {
                    i++;
                } else {
                    z = true;
                }
            }
            if (Intrinsics.areEqual("вся група", obj.subSequence(i, length + 1).toString())) {
                if (this.self) {
                    startActivity(new Intent(this, (Class<?>) StudentsListActivity.class));
                    return;
                }
                LessonViewFragmentBinding lessonViewFragmentBinding4 = this.view;
                if (lessonViewFragmentBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                } else {
                    lessonViewFragmentBinding2 = lessonViewFragmentBinding4;
                }
                lessonViewFragmentBinding2.groupLayout.setClickable(false);
                return;
            }
            LessonViewFragmentBinding lessonViewFragmentBinding5 = this.view;
            if (lessonViewFragmentBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                lessonViewFragmentBinding5 = null;
            }
            if (StringsKt.contains$default((CharSequence) lessonViewFragmentBinding5.subgroupName.getText().toString(), (CharSequence) "підгр", false, 2, (Object) null)) {
                if (this.self) {
                    startActivity(new Intent(this, (Class<?>) StudentsListActivity.class));
                    return;
                }
                Lesson lesson4 = this.lesson;
                if (lesson4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("lesson");
                    lesson4 = null;
                }
                String subgroup = lesson4.getSubgroup();
                Intrinsics.checkNotNullExpressionValue(subgroup, "getSubgroup(...)");
                int indexOf$default = StringsKt.indexOf$default((CharSequence) subgroup, SpanChipTokenizer.AUTOCORRECT_SEPARATOR, 0, false, 6, (Object) null);
                Lesson lesson5 = this.lesson;
                if (lesson5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("lesson");
                    lesson5 = null;
                }
                String subgroup2 = lesson5.getSubgroup();
                Intrinsics.checkNotNullExpressionValue(subgroup2, "getSubgroup(...)");
                Lesson lesson6 = this.lesson;
                if (lesson6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("lesson");
                } else {
                    lesson2 = lesson6;
                }
                String substring = subgroup2.substring(0, Math.min(indexOf$default, lesson2.getSubgroup().length()));
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                GroupsProfileActivity.startByName(this, substring);
                return;
            }
            Lesson lesson7 = this.lesson;
            if (lesson7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lesson");
                lesson7 = null;
            }
            String streamConsists = lesson7.getStreamConsists();
            Intrinsics.checkNotNullExpressionValue(streamConsists, "getStreamConsists(...)");
            if (streamConsists.length() == 0) {
                Lesson lesson8 = this.lesson;
                if (lesson8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("lesson");
                } else {
                    lesson3 = lesson8;
                }
                String subgroup3 = lesson3.getSubgroup();
                Intrinsics.checkNotNullExpressionValue(subgroup3, "getSubgroup(...)");
                String str = subgroup3;
                int length2 = str.length() - 1;
                int i2 = 0;
                boolean z3 = false;
                while (i2 <= length2) {
                    boolean z4 = Intrinsics.compare((int) str.charAt(!z3 ? i2 : length2), 32) <= 0;
                    if (z3) {
                        if (!z4) {
                            break;
                        } else {
                            length2--;
                        }
                    } else if (z4) {
                        i2++;
                    } else {
                        z3 = true;
                    }
                }
                GroupsProfileActivity.startByName(this, str.subSequence(i2, length2 + 1).toString());
                return;
            }
            LessonActivity lessonActivity = this;
            LessonViewFragmentBinding lessonViewFragmentBinding6 = this.view;
            if (lessonViewFragmentBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                lessonViewFragmentBinding6 = null;
            }
            String obj2 = lessonViewFragmentBinding6.subgroupName.getText().toString();
            Lesson lesson9 = this.lesson;
            if (lesson9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lesson");
            } else {
                lesson = lesson9;
            }
            StreamProfileActivity.startByScheduleIds(lessonActivity, obj2, lesson.getStreamConsists());
        }
    }

    public boolean onMenuItemClick(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
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

    private final void navigateToEventAdd() {
        if (AppDataManager.getInstance().getHint(AppDataManager.HINT_eventAdd)) {
            Utils.createAlertInfo(this, AppDataManager.HINT_eventAdd, R.string.hint_eventadd);
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Створення події");
        StringBuilder sb = new StringBuilder("Створити подію для всіх студентів (");
        Lesson lesson = this.lesson;
        if (lesson == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lesson");
            lesson = null;
        }
        sb.append(lesson.getAttendees());
        sb.append(") цього заняття?");
        builder.setMessage(sb.toString());
        builder.setPositiveButton("Створити подію Google Meet", new DialogInterface.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.lesson.LessonActivity$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                LessonActivity.navigateToEventAdd$lambda$7(LessonActivity.this, dialogInterface, i);
            }
        });
        builder.setNegativeButton("Лише собі", new DialogInterface.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.lesson.LessonActivity$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                LessonActivity.navigateToEventAdd$lambda$8(LessonActivity.this, dialogInterface, i);
            }
        });
        builder.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void navigateToEventAdd$lambda$7(LessonActivity this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.createMeet();
        dialogInterface.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void navigateToEventAdd$lambda$8(LessonActivity this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.createMyEvent();
        dialogInterface.dismiss();
    }

    private final void createMyEvent() {
        FastCallRepository<EmptyResult> create = FastCallRepository.create();
        Lesson lesson = this.lesson;
        Lesson lesson2 = null;
        if (lesson == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lesson");
            lesson = null;
        }
        Lesson lesson3 = this.lesson;
        if (lesson3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lesson");
        } else {
            lesson2 = lesson3;
        }
        create.call(APIMethod.createEvent(lesson, lesson2.generateInternalUid())).into(new APIObjectOkListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.lesson.LessonActivity$createMyEvent$1
            @Override // com.m_myr.nuwm.nuwmschedule.network.APIObjectOkListener
            public void onSuccess() {
                Toast.makeText(LessonActivity.this, "Подію створено", 0).show();
                LessonActivity.this.setResult(4);
                LessonActivity.this.onBackPressed();
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.APIObjectOkListener, com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener
            protected void onError(ErrorResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                Toast.makeText(LessonActivity.this, response.getMessage(), 0).show();
            }
        }).start();
    }

    private final void createMeet() {
        FastCallRepository<EmptyResult> create = FastCallRepository.create();
        Lesson lesson = this.lesson;
        Lesson lesson2 = null;
        if (lesson == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lesson");
            lesson = null;
        }
        Lesson lesson3 = this.lesson;
        if (lesson3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lesson");
        } else {
            lesson2 = lesson3;
        }
        create.call(APIMethod.createLesson(lesson, lesson2.generateInternalUid())).into(new APIObjectOkListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.lesson.LessonActivity$createMeet$1
            @Override // com.m_myr.nuwm.nuwmschedule.network.APIObjectOkListener, com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener
            protected void onError(ErrorResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                Toast.makeText(LessonActivity.this, response.getMessage(), 0).show();
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.APIObjectOkListener
            public void onSuccess() {
                Toast.makeText(LessonActivity.this, "Подію створено", 0).show();
                LessonActivity.this.setResult(4);
                LessonActivity.this.onBackPressed();
            }
        }).start();
    }

    private final void navigateToEventCreatePush() {
        Intent intent = new Intent(this, (Class<?>) SendPushActivity.class);
        Lesson lesson = this.lesson;
        if (lesson == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lesson");
            lesson = null;
        }
        intent.putExtra("lesson", lesson);
        startActivity(intent);
    }

    private final void shareLesson() {
        Lesson lesson = this.lesson;
        if (lesson == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lesson");
            lesson = null;
        }
        startActivity(lesson.createShareIntent());
    }
}
