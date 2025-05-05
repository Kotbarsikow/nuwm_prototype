package com.m_myr.nuwm.nuwmschedule.ui.framents.roomAlert;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebView;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.roomLocation.FuncArea;
import com.m_myr.nuwm.nuwmschedule.data.models.roomLocation.RoomLocationInfo;
import com.m_myr.nuwm.nuwmschedule.ui.activities.room.RoomActivity;
import com.m_myr.nuwm.nuwmschedule.utils.RoomUtils;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.io.IOException;
import java.io.OutputStreamWriter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import org.mortbay.jetty.MimeTypes;

/* compiled from: RoomDialog.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010'\u001a\u00020(H\u0016R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR\u001a\u0010\u0010\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&¨\u0006)"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/framents/roomAlert/RoomDialog;", "Landroid/app/AlertDialog;", "context", "Landroid/content/Context;", "room", "", "(Landroid/content/Context;Ljava/lang/String;)V", FirebaseAnalytics.Param.CONTENT, "Landroid/view/View;", "getContent", "()Landroid/view/View;", "setContent", "(Landroid/view/View;)V", "contentInside", "getContentInside", "setContentInside", "realContent", "getRealContent", "setRealContent", "getRoom", "()Ljava/lang/String;", "switch3D", "Landroidx/appcompat/widget/SwitchCompat;", "getSwitch3D", "()Landroidx/appcompat/widget/SwitchCompat;", "setSwitch3D", "(Landroidx/appcompat/widget/SwitchCompat;)V", "textRoom", "Landroid/widget/TextView;", "getTextRoom", "()Landroid/widget/TextView;", "setTextRoom", "(Landroid/widget/TextView;)V", "webview", "Landroid/webkit/WebView;", "getWebview", "()Landroid/webkit/WebView;", "setWebview", "(Landroid/webkit/WebView;)V", "show", "", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RoomDialog extends AlertDialog {
    private View content;
    private View contentInside;
    private View realContent;
    private final String room;
    private SwitchCompat switch3D;
    private TextView textRoom;
    private WebView webview;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RoomDialog(Context context, String room) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(room, "room");
        this.room = room;
        View inflate = getLayoutInflater().inflate(R.layout.room_dialog, (ViewGroup) null);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.content = inflate;
        View findViewById = inflate.findViewById(R.id.contentInside);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.contentInside = findViewById;
        View findViewById2 = this.content.findViewById(R.id.realContent);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.realContent = findViewById2;
        View findViewById3 = this.content.findViewById(R.id.webview);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.webview = (WebView) findViewById3;
        View findViewById4 = this.content.findViewById(R.id.switch3D);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.switch3D = (SwitchCompat) findViewById4;
        View findViewById5 = this.content.findViewById(R.id.textRoom);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.textRoom = (TextView) findViewById5;
        setView(this.content);
    }

    public final String getRoom() {
        return this.room;
    }

    public final View getContent() {
        return this.content;
    }

    public final void setContent(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.content = view;
    }

    public final View getContentInside() {
        return this.contentInside;
    }

    public final void setContentInside(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.contentInside = view;
    }

    public final View getRealContent() {
        return this.realContent;
    }

    public final void setRealContent(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.realContent = view;
    }

    public final WebView getWebview() {
        return this.webview;
    }

    public final void setWebview(WebView webView) {
        Intrinsics.checkNotNullParameter(webView, "<set-?>");
        this.webview = webView;
    }

    public final SwitchCompat getSwitch3D() {
        return this.switch3D;
    }

    public final void setSwitch3D(SwitchCompat switchCompat) {
        Intrinsics.checkNotNullParameter(switchCompat, "<set-?>");
        this.switch3D = switchCompat;
    }

    public final TextView getTextRoom() {
        return this.textRoom;
    }

    public final void setTextRoom(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.textRoom = textView;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [T, android.graphics.Point] */
    @Override // android.app.Dialog
    public void show() {
        super.show();
        Object systemService = getContext().getSystemService("window");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
        Intrinsics.checkNotNullExpressionValue(defaultDisplay, "getDefaultDisplay(...)");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new Point();
        defaultDisplay.getSize((Point) objectRef.element);
        ((Point) objectRef.element).y -= Utils.dpToPx(20);
        ((Point) objectRef.element).x -= Utils.dpToPx(24);
        if (((Point) objectRef.element).y > Utils.dpToPx(460)) {
            ((Point) objectRef.element).y = Utils.dpToPx(460);
        }
        this.webview.getSettings().setAllowContentAccess(true);
        this.webview.getSettings().setAllowFileAccess(true);
        this.webview.getSettings().setJavaScriptEnabled(true);
        this.webview.getSettings().setAllowFileAccessFromFileURLs(true);
        this.webview.getSettings().setAllowUniversalAccessFromFileURLs(true);
        this.webview.getSettings().setCacheMode(2);
        final RoomUtils roomUtils = new RoomUtils();
        final RoomLocationInfo findRoomBuilding = roomUtils.findRoomBuilding(this.room);
        if (findRoomBuilding == null) {
            dismiss();
            Toast.makeText(getContext(), "Немає інформацію про це місце :С", 0).show();
            return;
        }
        TextView textView = this.textRoom;
        StringBuilder sb = new StringBuilder("Аудиторія ");
        sb.append(this.room);
        sb.append(',');
        FuncArea room = findRoomBuilding.getRoom();
        String str = room != null ? room.floor : null;
        if (str == null) {
            str = "";
        }
        String lowerCase = str.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
        sb.append(lowerCase);
        textView.setText(sb.toString());
        this.switch3D.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.roomAlert.RoomDialog$$ExternalSyntheticLambda0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                RoomDialog.show$lambda$0(RoomDialog.this, roomUtils, findRoomBuilding, compoundButton, z);
            }
        });
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        this.webview.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.roomAlert.RoomDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                RoomDialog.show$lambda$2(RoomDialog.this, booleanRef, roomUtils, findRoomBuilding, view, i, i2, i3, i4, i5, i6, i7, i8);
            }
        });
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getContext().openFileOutput("test.txt", 0));
            outputStreamWriter.write(roomUtils.generate3DRoomHtml(findRoomBuilding, false));
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e);
        }
        this.contentInside.findViewById(R.id.onClickDetails).setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.roomAlert.RoomDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RoomDialog.show$lambda$3(RoomDialog.this, view);
            }
        });
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        Window window = getWindow();
        Intrinsics.checkNotNull(window);
        layoutParams.copyFrom(window.getAttributes());
        layoutParams.width = -1;
        layoutParams.height = -1;
        Window window2 = getWindow();
        Intrinsics.checkNotNull(window2);
        window2.setAttributes(layoutParams);
        Window window3 = getWindow();
        Intrinsics.checkNotNull(window3);
        window3.setBackgroundDrawable(new ColorDrawable(0));
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.12f, 1.0f);
        ofFloat.setDuration(230L);
        ofFloat.setInterpolator(new DecelerateInterpolator());
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.roomAlert.RoomDialog$$ExternalSyntheticLambda3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                RoomDialog.show$lambda$5$lambda$4(RoomDialog.this, objectRef, valueAnimator);
            }
        });
        ofFloat.start();
        this.contentInside.getLayoutParams().height = MathKt.roundToInt(((Point) objectRef.element).y * 0.15f);
        this.content.requestLayout();
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(0.15f, 1.0f);
        ofFloat2.setDuration(400L);
        ofFloat2.setStartDelay(200L);
        ofFloat2.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.roomAlert.RoomDialog$$ExternalSyntheticLambda4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                RoomDialog.show$lambda$7$lambda$6(RoomDialog.this, objectRef, valueAnimator);
            }
        });
        ofFloat2.start();
        ValueAnimator ofFloat3 = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat3.setDuration(200L);
        ofFloat3.setStartDelay(540L);
        ofFloat3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.roomAlert.RoomDialog$$ExternalSyntheticLambda5
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                RoomDialog.show$lambda$9$lambda$8(RoomDialog.this, valueAnimator);
            }
        });
        ofFloat3.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void show$lambda$0(RoomDialog this$0, RoomUtils roomUtils, RoomLocationInfo roomLocationInfo, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(roomUtils, "$roomUtils");
        if (z) {
            this$0.webview.loadDataWithBaseURL("file:///android_asset/indoor3D/", roomUtils.generate3DRoomHtml(roomLocationInfo, false), MimeTypes.TEXT_HTML_UTF_8, "UTF-8", null);
        } else {
            this$0.webview.loadUrl("file:///android_asset/indoor3D/index2D.html");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void show$lambda$2(final RoomDialog this$0, Ref.BooleanRef onceChange, final RoomUtils roomUtils, final RoomLocationInfo roomLocationInfo, View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(onceChange, "$onceChange");
        Intrinsics.checkNotNullParameter(roomUtils, "$roomUtils");
        int width = this$0.webview.getWidth();
        this$0.webview.getHeight();
        if (!onceChange.element) {
            onceChange.element = true;
            this$0.webview.getLayoutParams().width = width;
            this$0.webview.requestLayout();
        }
        this$0.webview.postDelayed(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.roomAlert.RoomDialog$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                RoomDialog.show$lambda$2$lambda$1(RoomDialog.this, roomUtils, roomLocationInfo);
            }
        }, 10L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void show$lambda$2$lambda$1(RoomDialog this$0, RoomUtils roomUtils, RoomLocationInfo roomLocationInfo) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(roomUtils, "$roomUtils");
        if (this$0.switch3D.isChecked()) {
            this$0.webview.loadDataWithBaseURL("file:///android_asset/indoor3D/", roomUtils.generate3DRoomHtml(roomLocationInfo, false), MimeTypes.TEXT_HTML_UTF_8, "UTF-8", null);
        } else {
            this$0.webview.loadUrl("file:///android_asset/indoor3D/index2D.html");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void show$lambda$3(RoomDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        RoomActivity.Companion companion = RoomActivity.INSTANCE;
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        companion.findByName(context, this$0.room);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void show$lambda$5$lambda$4(RoomDialog this$0, Ref.ObjectRef size, ValueAnimator it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(size, "$size");
        Intrinsics.checkNotNullParameter(it, "it");
        ViewGroup.LayoutParams layoutParams = this$0.contentInside.getLayoutParams();
        Object animatedValue = it.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        layoutParams.width = MathKt.roundToInt(((Float) animatedValue).floatValue() * ((Point) size.element).x);
        this$0.content.requestLayout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void show$lambda$7$lambda$6(RoomDialog this$0, Ref.ObjectRef size, ValueAnimator it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(size, "$size");
        Intrinsics.checkNotNullParameter(it, "it");
        ViewGroup.LayoutParams layoutParams = this$0.contentInside.getLayoutParams();
        Object animatedValue = it.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        layoutParams.height = MathKt.roundToInt(((Float) animatedValue).floatValue() * ((Point) size.element).y);
        this$0.content.requestLayout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void show$lambda$9$lambda$8(RoomDialog this$0, ValueAnimator it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        View view = this$0.realContent;
        Object animatedValue = it.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        view.setAlpha(((Float) animatedValue).floatValue());
        this$0.realContent.setVisibility(0);
    }
}
