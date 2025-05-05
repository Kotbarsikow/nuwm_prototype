package com.m_myr.nuwm.nuwmschedule.ui.activities.room;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableDay;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableIdentifier;
import com.m_myr.nuwm.nuwmschedule.databinding.RoomActivityLayoutBinding;
import com.m_myr.nuwm.nuwmschedule.domain.adapter.TimetableAdapter;
import com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.timetable.GeneralTimetableActivity;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: RoomActivity.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \"2\u00020\u00012\u00020\u0002:\u0001\"B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\u0018\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u000f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018J\u0012\u0010\u0019\u001a\u00020\u000f2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\u000e\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0018J\u0018\u0010\u001d\u001a\u00020\u000f2\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u001fH\u0017J\b\u0010!\u001a\u00020\u000fH\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006#"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/room/RoomActivity;", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/base/BaseStateActivity;", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/room/IRoomActivityView;", "()V", "binding", "Lcom/m_myr/nuwm/nuwmschedule/databinding/RoomActivityLayoutBinding;", "getBinding", "()Lcom/m_myr/nuwm/nuwmschedule/databinding/RoomActivityLayoutBinding;", "setBinding", "(Lcom/m_myr/nuwm/nuwmschedule/databinding/RoomActivityLayoutBinding;)V", "presenter", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/room/RoomPresenter;", "getPresenter", "()Lcom/m_myr/nuwm/nuwmschedule/ui/activities/room/RoomPresenter;", "bindMap", "", "hideSchedule", "navigateToFullTimetable", "identifier", "Lcom/m_myr/nuwm/nuwmschedule/data/models/TimetableIdentifier;", "title", "", "onClickAllTimetable", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "openFullScreen", "setTimetable", "value", "Lcom/m_myr/nuwm/nuwmschedule/data/models/TimetableDay;", "Lcom/m_myr/nuwm/nuwmschedule/data/models/Lesson;", "showEmptyTimetable", "Companion", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RoomActivity extends BaseStateActivity implements IRoomActivityView {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public RoomActivityLayoutBinding binding;
    private final RoomPresenter presenter = new RoomPresenter(this);

    public final RoomPresenter getPresenter() {
        return this.presenter;
    }

    /* compiled from: RoomActivity.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u0016\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b¨\u0006\f"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/room/RoomActivity$Companion;", "", "()V", "findByName", "", "context", "Landroid/content/Context;", AppMeasurementSdk.ConditionalUserProperty.NAME, "", "startById", "id", "", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void startById(Context context, int id) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, (Class<?>) RoomActivity.class);
            intent.putExtra("roomId", id);
            context.startActivity(intent);
        }

        public final void findByName(Context context, String name) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, (Class<?>) RoomActivity.class);
            intent.putExtra("room", name);
            context.startActivity(intent);
        }
    }

    public final RoomActivityLayoutBinding getBinding() {
        RoomActivityLayoutBinding roomActivityLayoutBinding = this.binding;
        if (roomActivityLayoutBinding != null) {
            return roomActivityLayoutBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding(RoomActivityLayoutBinding roomActivityLayoutBinding) {
        Intrinsics.checkNotNullParameter(roomActivityLayoutBinding, "<set-?>");
        this.binding = roomActivityLayoutBinding;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_White);
        super.onCreate(savedInstanceState);
        RoomActivityLayoutBinding inflate = RoomActivityLayoutBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        setBinding(inflate);
        allowFindOnBaseView(true);
        setNestedScrollContentView(getBinding());
        bindMap();
        ((TextView) findViewById(R.id.wikiTitle)).setText("Про місце");
        findViewById(R.id.biography).setVisibility(8);
    }

    private final void bindMap() {
        getBinding().webview.getSettings().setAllowContentAccess(true);
        getBinding().webview.getSettings().setAllowFileAccess(true);
        getBinding().webview.getSettings().setJavaScriptEnabled(true);
        getBinding().webview.getSettings().setAllowFileAccessFromFileURLs(true);
        getBinding().webview.getSettings().setAllowUniversalAccessFromFileURLs(true);
        getBinding().webview.getSettings().setCacheMode(2);
        getBinding().switch3D.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.room.RoomActivity$$ExternalSyntheticLambda2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                RoomActivity.bindMap$lambda$0(RoomActivity.this, compoundButton, z);
            }
        });
        getBinding().webview.loadUrl("file:///android_asset/indoor3D/index.html");
        getBinding().switch3D.setChecked(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindMap$lambda$0(RoomActivity this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            this$0.getBinding().webview.loadUrl("file:///android_asset/indoor3D/index.html");
        } else {
            this$0.getBinding().webview.loadUrl("file:///android_asset/indoor3D/index2D.html");
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.room.IRoomActivityView
    public void hideSchedule() {
        findViewById(R.id.schedule).setVisibility(8);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.room.IRoomActivityView
    public void showEmptyTimetable() {
        ((ProgressBar) findViewById(R.id.progress_timetable)).setVisibility(8);
        findViewById(R.id.empty_timetable).setVisibility(0);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.room.IRoomActivityView
    public void setTimetable(TimetableDay<Lesson> value) {
        RoomActivity roomActivity = this;
        ((RecyclerView) findViewById(R.id.recyclerView)).setLayoutManager(new LinearLayoutManager(roomActivity));
        TimetableAdapter timetableAdapter = new TimetableAdapter(roomActivity, false, true);
        Intrinsics.checkNotNull(value);
        timetableAdapter.setData(value.getItems());
        ((RecyclerView) findViewById(R.id.recyclerView)).setAdapter(timetableAdapter);
        ((RecyclerView) findViewById(R.id.recyclerView)).setNestedScrollingEnabled(false);
        ((ProgressBar) findViewById(R.id.progress_timetable)).setVisibility(8);
    }

    public final void onClickAllTimetable(View view) {
        this.presenter.getAllTimeTable();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.room.IRoomActivityView
    public void navigateToFullTimetable(TimetableIdentifier identifier, String title) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        Intrinsics.checkNotNullParameter(title, "title");
        Intent intent = new Intent(this, (Class<?>) GeneralTimetableActivity.class);
        intent.putExtra("identifier", identifier);
        intent.putExtra("title", title);
        intent.putExtra("replaceRoomToTeacher", true);
        startActivity(intent);
    }

    public final void openFullScreen(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (Intrinsics.areEqual(getBinding().webview.getTag(), (Object) true)) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(Utils.dpToPxRaw(400.0f), Utils.dpToPxRaw(140.0f));
            ofFloat.setDuration(400L);
            ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.room.RoomActivity$$ExternalSyntheticLambda0
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    RoomActivity.openFullScreen$lambda$2$lambda$1(RoomActivity.this, valueAnimator);
                }
            });
            ofFloat.addListener(new Animator.AnimatorListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.room.RoomActivity$openFullScreen$1$2
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                    RoomActivity.this.getBinding().webview.setTag(false);
                    RoomActivity.this.getBinding().openIcon.setImageResource(R.drawable.ic_baseline_fullscreen);
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                    RoomActivity.this.getBinding().webview.reload();
                }
            });
            ofFloat.start();
            return;
        }
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(Utils.dpToPxRaw(140.0f), Utils.dpToPxRaw(400.0f));
        ofFloat2.setDuration(400L);
        ofFloat2.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.room.RoomActivity$$ExternalSyntheticLambda1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                RoomActivity.openFullScreen$lambda$4$lambda$3(RoomActivity.this, valueAnimator);
            }
        });
        ofFloat2.addListener(new Animator.AnimatorListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.room.RoomActivity$openFullScreen$2$2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                RoomActivity.this.getBinding().webview.setTag(true);
                RoomActivity.this.getBinding().openIcon.setImageResource(R.drawable.ic_baseline_fullscreen_exit);
                ((NestedScrollView) RoomActivity.this.findBaseViewById(R.id.content_layout_scroll)).smoothScrollTo(0, RoomActivity.this.getBinding().mapsCard.getBottom());
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                RoomActivity.this.getBinding().webview.reload();
            }
        });
        ofFloat2.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openFullScreen$lambda$2$lambda$1(RoomActivity this$0, ValueAnimator it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        ViewGroup.LayoutParams layoutParams = this$0.getBinding().webview.getLayoutParams();
        Object animatedValue = it.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        layoutParams.height = MathKt.roundToInt(((Float) animatedValue).floatValue());
        this$0.getBinding().webview.requestLayout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openFullScreen$lambda$4$lambda$3(RoomActivity this$0, ValueAnimator it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        ViewGroup.LayoutParams layoutParams = this$0.getBinding().webview.getLayoutParams();
        Object animatedValue = it.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        layoutParams.height = MathKt.roundToInt(((Float) animatedValue).floatValue());
        this$0.getBinding().webview.requestLayout();
    }
}
