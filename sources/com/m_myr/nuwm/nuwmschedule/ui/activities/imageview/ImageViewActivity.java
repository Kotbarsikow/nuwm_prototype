package com.m_myr.nuwm.nuwmschedule.ui.activities.imageview;

import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.google.android.gms.common.internal.ImagesContract;
import com.m_myr.nuwm.nuwmschedule.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageViewActivity.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014J\b\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/imageview/ImageViewActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onSupportNavigateUp", "", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ImageViewActivity extends AppCompatActivity {
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        Intrinsics.checkNotNullExpressionValue(window, "getWindow(...)");
        window.addFlags(Integer.MIN_VALUE);
        getWindow().getDecorView().setSystemUiVisibility(0);
        window.setStatusBarColor(ViewCompat.MEASURED_STATE_MASK);
        window.setNavigationBarColor(ViewCompat.MEASURED_STATE_MASK);
        setContentView(R.layout.image_view_activity);
        Glide.with((FragmentActivity) this).load(getIntent().getStringExtra(ImagesContract.URL)).into((ImageView) findViewById(R.id.myZoomageView));
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
