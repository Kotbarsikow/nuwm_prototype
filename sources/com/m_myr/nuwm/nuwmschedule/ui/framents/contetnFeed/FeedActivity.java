package com.m_myr.nuwm.nuwmschedule.ui.framents.contetnFeed;

import android.os.Bundle;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.ui.activities.BaseToolbarActivity;
import kotlin.Metadata;

/* compiled from: FeedActivity.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\u0007"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/framents/contetnFeed/FeedActivity;", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/BaseToolbarActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FeedActivity extends BaseToolbarActivity {
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(App.getInstance().getAppTheme());
        setContentView(R.layout.news_feed_activity);
        attachToolbar();
        setTitle("Новини та оголошення");
        Bundle bundle = new Bundle();
        bundle.putInt("categoryId", getIntent().getIntExtra("categoryId", 0));
        ContentFeedFragment contentFeedFragment = new ContentFeedFragment();
        contentFeedFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.placeholder, contentFeedFragment).commit();
    }
}
