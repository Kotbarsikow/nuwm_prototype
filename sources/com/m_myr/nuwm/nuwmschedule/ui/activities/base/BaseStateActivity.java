package com.m_myr.nuwm.nuwmschedule.ui.activities.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.BaseStateView;
import com.m_myr.nuwm.nuwmschedule.ui.activities.BaseToolbarActivity;
import com.m_myr.nuwm.nuwmschedule.ui.view.ToolbarHides;
import com.m_myr.nuwm.nuwmschedule.utils.ResourcesHelper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes2.dex */
public abstract class BaseStateActivity extends BaseToolbarActivity implements BaseStateView {
    public static final int CONTENT = 0;
    public static final int ERROR = -1;
    public static final int LOADING = 1;
    private boolean allowFindOnBaseView;
    View inflatedContent;
    View inflatedLoading;
    View inflatedrror;
    private int layoutError;
    private int layoutLoading;
    boolean wasOverrides;
    private int baseActivityRes = R.layout.base_activity;
    int state = 0;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ActivityState {
    }

    public void OnRetry() {
    }

    public Context getContext() {
        return this;
    }

    protected void setScrollTitle(String title, int id) {
        setTitle(title);
        attachScrollTitleListener(id, R.id.toolbar);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int resId) {
        super.setTheme(resId);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ProcessVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Method arg registers not loaded: com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity$$ExternalSyntheticLambda0.<init>(android.view.View, int, com.m_myr.nuwm.nuwmschedule.ui.view.ToolbarHides, int):void, class status: GENERATED_AND_UNLOADED
        	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:290)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.isArgUnused(ProcessVariables.java:146)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.lambda$isVarUnused$0(ProcessVariables.java:131)
        	at jadx.core.utils.ListUtils.allMatch(ListUtils.java:193)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.isVarUnused(ProcessVariables.java:131)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.processBlock(ProcessVariables.java:82)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:64)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.removeUnusedResults(ProcessVariables.java:73)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.visit(ProcessVariables.java:48)
        */
    private void attachScrollTitleListener(int r5, int r6) {
        /*
            r4 = this;
            r0 = 4
            int r0 = com.m_myr.nuwm.nuwmschedule.utils.Utils.dpToPx(r0)
            int r1 = r0 / 4
            android.view.View r6 = r4.findBaseViewById(r6)
            com.m_myr.nuwm.nuwmschedule.ui.view.ToolbarHides r6 = (com.m_myr.nuwm.nuwmschedule.ui.view.ToolbarHides) r6
            android.view.View r1 = r4.findBaseViewById(r5)
            float r1 = r1.getPivotY()
            int r1 = (int) r1
            android.view.View r5 = r4.findBaseViewById(r5)
            int r5 = r5.getMeasuredHeight()
            int r1 = r1 + r5
            int r5 = r4.baseActivityRes
            r2 = 2131558444(0x7f0d002c, float:1.8742204E38)
            if (r5 == r2) goto L34
            r2 = 2131558443(0x7f0d002b, float:1.8742202E38)
            if (r5 != r2) goto L2c
            goto L34
        L2c:
            android.content.res.Resources$NotFoundException r5 = new android.content.res.Resources$NotFoundException
            java.lang.String r6 = "Can`t find scroll parent"
            r5.<init>(r6)
            throw r5
        L34:
            r5 = 2131362078(0x7f0a011e, float:1.8343926E38)
            android.view.View r5 = r4.findBaseViewById(r5)
            r2 = 2
            r5.setOverScrollMode(r2)
            r2 = 0
            r6.setElevation(r2)
            r6.setTextAlpha(r2)
            android.view.ViewTreeObserver r2 = r5.getViewTreeObserver()
            com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity$$ExternalSyntheticLambda0 r3 = new com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity$$ExternalSyntheticLambda0
            r3.<init>()
            r2.addOnScrollChangedListener(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity.attachScrollTitleListener(int, int):void");
    }

    static /* synthetic */ void lambda$attachScrollTitleListener$0(View view, int i, ToolbarHides toolbarHides, int i2) {
        float f;
        int scrollY = view.getScrollY();
        if (scrollY <= i) {
            f = (float) Math.pow(scrollY == 0 ? 0.0f : scrollY / i, 2.0d);
        } else {
            f = 1.0f;
        }
        toolbarHides.setTextAlpha(f);
        toolbarHides.setElevation(f * i2);
    }

    public final void overrideErrorId(int override_empty) {
        this.layoutError = override_empty;
        this.wasOverrides = true;
        this.inflatedrror = findBaseViewById(override_empty);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(int layoutResID) {
        setContentView(layoutResID, R.layout.error_layout, R.layout.loading_layout);
    }

    public void setScrollContentView(int layoutResID) {
        this.baseActivityRes = R.layout.base_scroll_activity;
        setContentView(layoutResID, R.layout.error_layout, R.layout.loading_layout);
    }

    public void setNestedScrollContentView(int layoutResID) {
        this.baseActivityRes = R.layout.base_nestedscroll_activity;
        setContentView(layoutResID, R.layout.error_layout, R.layout.loading_layout);
    }

    public void setContentView(ViewBinding binding) {
        setContentView(binding, R.layout.error_layout, R.layout.loading_layout);
    }

    public void setScrollContentView(ViewBinding binding) {
        this.baseActivityRes = R.layout.base_scroll_activity;
        setContentView(binding, R.layout.error_layout, R.layout.loading_layout);
    }

    public void setNestedScrollContentView(ViewBinding binding) {
        this.baseActivityRes = R.layout.base_nestedscroll_activity;
        setContentView(binding, R.layout.error_layout, R.layout.loading_layout);
    }

    public void setContentView(ViewBinding binding, int layoutContent, int layoutLoading) {
        this.layoutError = layoutContent;
        this.layoutLoading = layoutLoading;
        setTheme(App.getInstance().getAppTheme());
        super.setContentView(this.baseActivityRes);
        View root = binding.getRoot();
        this.inflatedContent = root;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) root.getLayoutParams();
        if (marginLayoutParams == null) {
            marginLayoutParams = new ViewGroup.MarginLayoutParams(-1, -1);
        }
        marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin + ResourcesHelper.getValueOfAttr(this, R.attr.actionBarSize), marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
        this.inflatedContent.setLayoutParams(marginLayoutParams);
        int i = this.baseActivityRes;
        if (i == R.layout.base_scroll_activity || i == R.layout.base_nestedscroll_activity) {
            ((ViewGroup) super.findViewById(R.id.content_layout_scroll)).addView(this.inflatedContent);
        } else {
            ((ViewGroup) super.findViewById(R.id.main_content)).addView(this.inflatedContent);
        }
        attachToolbar();
    }

    public void setContentView(int layoutContent, int layoutError, int layoutLoading) {
        this.layoutError = layoutError;
        this.layoutLoading = layoutLoading;
        setTheme(App.getInstance().getAppTheme());
        super.setContentView(this.baseActivityRes);
        int i = this.baseActivityRes;
        if (i == R.layout.base_scroll_activity || i == R.layout.base_nestedscroll_activity) {
            this.inflatedContent = getLayoutInflater().inflate(layoutContent, (ViewGroup) super.findViewById(R.id.content_layout_scroll), true);
        } else {
            ViewStub viewStub = (ViewStub) super.findViewById(R.id.content_layout_stub);
            viewStub.setLayoutResource(layoutContent);
            this.inflatedContent = viewStub.inflate();
        }
        attachToolbar();
    }

    @Deprecated
    public void setActivityState(int state) {
        setActivityState(state, null);
    }

    @Deprecated
    public void setActivityState(int state, String textError) {
        if (this.state == state) {
            return;
        }
        this.state = state;
        if (state == 1) {
            if (this.inflatedLoading == null) {
                ViewStub viewStub = (ViewStub) super.findViewById(R.id.error_layout_stub);
                viewStub.setLayoutResource(this.layoutLoading);
                this.inflatedLoading = viewStub.inflate();
            }
            this.inflatedLoading.setVisibility(0);
            View view = this.inflatedrror;
            if (view != null) {
                view.setVisibility(4);
            }
            View view2 = this.inflatedContent;
            if (view2 == null || this.wasOverrides) {
                return;
            }
            view2.setVisibility(4);
            return;
        }
        if (state != -1) {
            if (state == 0) {
                View view3 = this.inflatedLoading;
                if (view3 != null) {
                    view3.setVisibility(8);
                }
                View view4 = this.inflatedrror;
                if (view4 != null) {
                    view4.setVisibility(8);
                }
                this.inflatedContent.setVisibility(0);
                return;
            }
            return;
        }
        if (this.inflatedrror == null) {
            ViewStub viewStub2 = (ViewStub) super.findViewById(R.id.loading_layout_stub);
            viewStub2.setLayoutResource(this.layoutError);
            this.inflatedrror = viewStub2.inflate();
        }
        View view5 = this.inflatedLoading;
        if (view5 != null) {
            view5.setVisibility(4);
        }
        this.inflatedrror.setVisibility(0);
        View view6 = this.inflatedContent;
        if (view6 != null && !this.wasOverrides) {
            view6.setVisibility(4);
        }
        if (textError != null) {
            ((TextView) this.inflatedrror.findViewById(R.id.error_text)).setText(textError);
        }
        if (this.inflatedrror.findViewById(R.id.retry) != null) {
            this.inflatedrror.findViewById(R.id.retry).setVisibility(0);
        }
    }

    @Deprecated
    public void OnRetry(View view) {
        OnRetry();
    }

    protected void allowFindOnBaseView(boolean allowFindOnBaseView) {
        this.allowFindOnBaseView = allowFindOnBaseView;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity
    public <T extends View> T findViewById(int i) {
        if (i == this.inflatedContent.getId()) {
            return (T) this.inflatedContent;
        }
        T t = (T) this.inflatedContent.findViewById(i);
        return (t == null && this.allowFindOnBaseView) ? (T) findBaseViewById(i) : t;
    }

    public <T extends View> T findBaseViewById(int i) {
        return (T) super.findViewById(i);
    }

    public View getContentView() {
        return this.inflatedContent;
    }

    private ImageView findImageError() {
        return this.allowFindOnBaseView ? (ImageView) findBaseViewById(R.id.imageError) : (ImageView) this.inflatedrror.findViewById(R.id.imageError);
    }

    public void showError(String error) {
        setActivityState(-1, error);
        findImageError().setImageResource(R.drawable.astronaut);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.BaseStateView
    public void showEmpty(String title) {
        showEmpty(title, R.drawable.ic_empty_data);
    }

    public void showEmpty(String title, int icon) {
        setActivityState(-1, title);
        findImageError().setImageResource(icon);
        if (this.inflatedrror.findViewById(R.id.retry) != null) {
            this.inflatedrror.findViewById(R.id.retry).setVisibility(4);
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.BaseStateView
    public void showLoading() {
        setActivityState(1);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.BaseStateView
    public void showContent() {
        setActivityState(0);
    }
}
