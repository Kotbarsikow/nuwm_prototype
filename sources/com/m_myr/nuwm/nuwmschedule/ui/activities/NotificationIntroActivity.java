package com.m_myr.nuwm.nuwmschedule.ui.activities;

import android.os.Bundle;
import android.view.View;
import com.m_myr.nuwm.nuwmschedule.R;
import kotlin.Metadata;

/* compiled from: NotificationIntroActivity.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\u0007"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/NotificationIntroActivity;", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/BaseToolbarActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NotificationIntroActivity extends BaseToolbarActivity {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(View view) {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_into);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.NotificationIntroActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NotificationIntroActivity.onCreate$lambda$0(view);
            }
        });
    }
}
