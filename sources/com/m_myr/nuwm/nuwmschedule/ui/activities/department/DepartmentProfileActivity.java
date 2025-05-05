package com.m_myr.nuwm.nuwmschedule.ui.activities.department;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.google.firebase.messaging.Constants;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.DepartmentChild;
import com.m_myr.nuwm.nuwmschedule.data.models.DepartmentProfile;
import com.m_myr.nuwm.nuwmschedule.data.models.NewsViewItem;
import com.m_myr.nuwm.nuwmschedule.domain.adapter.RecyclerNewsAdapter;
import com.m_myr.nuwm.nuwmschedule.domain.adapter.WorkerAdapter;
import com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity;
import com.m_myr.nuwm.nuwmschedule.ui.framents.contetnFeed.FeedActivity;
import com.m_myr.nuwm.nuwmschedule.ui.view.ProfileCardInflater;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DepartmentProfileActivity.kt */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 *2\u00020\u00012\u00020\u0002:\u0001*B\u0005¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0002J\u0010\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u0017H\u0002J\u0010\u0010\u001f\u001a\u00020\u00192\b\u0010\u001e\u001a\u0004\u0018\u00010\u0017J\u0012\u0010 \u001a\u00020\u00192\b\u0010!\u001a\u0004\u0018\u00010\"H\u0014J\u0010\u0010#\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%H\u0016J\u001a\u0010&\u001a\u00020\u00192\b\u0010$\u001a\u0004\u0018\u00010'2\u0006\u0010(\u001a\u00020)H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/department/DepartmentProfileActivity;", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/base/BaseStateActivity;", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/department/DepartmentProfileView;", "()V", "mDepartments", "Landroid/widget/LinearLayout;", "mLocation", "Landroid/widget/TextView;", "mName", "mNews", "mParent", "mPhoto", "Landroid/widget/ImageView;", "mProgressNews", "Landroid/widget/ProgressBar;", "mWikitext", "mWorkers", "Landroidx/recyclerview/widget/RecyclerView;", "newsPlaceholder", "Landroid/widget/FrameLayout;", "presenter", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/department/DepartmentProfilePresenter;", "readAllNews", "Landroid/view/View;", "bindDepartments", "", "childDepartment", "", "Lcom/m_myr/nuwm/nuwmschedule/data/models/DepartmentChild;", "clickDepartment", "view", "onClickParent", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setData", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Lcom/m_myr/nuwm/nuwmschedule/data/models/DepartmentProfile;", "setNewsData", "Lcom/m_myr/nuwm/nuwmschedule/data/models/NewsViewItem;", "categoryId", "", "Companion", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DepartmentProfileActivity extends BaseStateActivity implements DepartmentProfileView {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private LinearLayout mDepartments;
    private TextView mLocation;
    private TextView mName;
    private LinearLayout mNews;
    private TextView mParent;
    private ImageView mPhoto;
    private ProgressBar mProgressNews;
    private TextView mWikitext;
    private RecyclerView mWorkers;
    private FrameLayout newsPlaceholder;
    private final DepartmentProfilePresenter presenter = new DepartmentProfilePresenter(this);
    private View readAllNews;

    @JvmStatic
    public static final void startById(Context context, int i) {
        INSTANCE.startById(context, i);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setNestedScrollContentView(R.layout.department_profile_activity);
        allowFindOnBaseView(true);
        this.mPhoto = (ImageView) findViewById(R.id.photo);
        this.mName = (TextView) findViewById(R.id.name);
        this.mParent = (TextView) findViewById(R.id.parent);
        this.mWikitext = (TextView) findViewById(R.id.wikitext);
        this.mLocation = (TextView) findViewById(R.id.location);
        this.mWorkers = (RecyclerView) findViewById(R.id.workers);
        this.mDepartments = (LinearLayout) findViewById(R.id.departments);
        this.mProgressNews = (ProgressBar) findViewById(R.id.progressNews);
        this.mNews = (LinearLayout) findViewById(R.id.news);
        this.newsPlaceholder = (FrameLayout) findViewById(R.id.newsPlaceholder);
        this.readAllNews = findViewById(R.id.readAllNews);
        View findViewById = findViewById(R.id.wikiTitle);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById).setText("Про підрозділ");
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.department.DepartmentProfileView
    public void setData(DepartmentProfile data) {
        Intrinsics.checkNotNullParameter(data, "data");
        String name = data.getName();
        TextView textView = this.mName;
        Intrinsics.checkNotNull(textView);
        setScrollTitle(name, textView.getId());
        TextView textView2 = this.mName;
        Intrinsics.checkNotNull(textView2);
        textView2.setText(data.getName());
        TextView textView3 = this.mParent;
        Intrinsics.checkNotNull(textView3);
        textView3.setText(data.getParentName());
        TextView textView4 = this.mWikitext;
        Intrinsics.checkNotNull(textView4);
        textView4.setText(Html.fromHtml(data.getWikitext()));
        TextView textView5 = this.mWikitext;
        Intrinsics.checkNotNull(textView5);
        textView5.setMovementMethod(LinkMovementMethod.getInstance());
        TextView textView6 = this.mLocation;
        Intrinsics.checkNotNull(textView6);
        textView6.setText(data.getLocation());
        if (TextUtils.isEmpty(data.getLocation())) {
            findViewById(R.id.relativeLayoutLocation).setVisibility(8);
        }
        if (TextUtils.isEmpty(data.getPhone())) {
            findViewById(R.id.relativeLayoutPhone).setVisibility(8);
        }
        DepartmentProfileActivity departmentProfileActivity = this;
        ProfileCardInflater.from(departmentProfileActivity, R.id.cards_container).inflate(data.getCards());
        RequestBuilder<Drawable> load = Glide.with((FragmentActivity) this).load(data.getImage());
        ImageView imageView = this.mPhoto;
        Intrinsics.checkNotNull(imageView);
        load.into(imageView);
        RecyclerView recyclerView = this.mWorkers;
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView2 = this.mWorkers;
        Intrinsics.checkNotNull(recyclerView2);
        recyclerView2.setAdapter(new WorkerAdapter(departmentProfileActivity, data.getWorkers()));
        List<DepartmentChild> childDepartment = data.getChildDepartment();
        Intrinsics.checkNotNullExpressionValue(childDepartment, "getChildDepartment(...)");
        bindDepartments(childDepartment);
        LinearLayout linearLayout = this.mNews;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(data.getNews() == 0 ? 8 : 0);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.department.DepartmentProfileView
    public void setNewsData(NewsViewItem data, final int categoryId) {
        LinearLayout linearLayout = this.mNews;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(data == null ? 8 : 0);
        if (data != null) {
            RecyclerNewsAdapter recyclerNewsAdapter = new RecyclerNewsAdapter(this, CollectionsKt.arrayListOf(data));
            FrameLayout frameLayout = this.newsPlaceholder;
            Intrinsics.checkNotNull(frameLayout);
            RecyclerNewsAdapter.ViewHolder onCreateViewHolder = recyclerNewsAdapter.onCreateViewHolder((ViewGroup) frameLayout, 0);
            recyclerNewsAdapter.onBindViewHolder(onCreateViewHolder, data);
            FrameLayout frameLayout2 = this.newsPlaceholder;
            Intrinsics.checkNotNull(frameLayout2);
            frameLayout2.removeAllViews();
            FrameLayout frameLayout3 = this.newsPlaceholder;
            Intrinsics.checkNotNull(frameLayout3);
            frameLayout3.addView(onCreateViewHolder.itemView);
            ProgressBar progressBar = this.mProgressNews;
            Intrinsics.checkNotNull(progressBar);
            progressBar.setVisibility(8);
            View view = this.readAllNews;
            Intrinsics.checkNotNull(view);
            view.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.department.DepartmentProfileActivity$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    DepartmentProfileActivity.setNewsData$lambda$0(DepartmentProfileActivity.this, categoryId, view2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setNewsData$lambda$0(DepartmentProfileActivity this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent(this$0, (Class<?>) FeedActivity.class);
        intent.putExtra("categoryId", i);
        this$0.startActivity(intent);
    }

    private final void bindDepartments(List<? extends DepartmentChild> childDepartment) {
        for (DepartmentChild departmentChild : childDepartment) {
            View inflate = getLayoutInflater().inflate(R.layout.post_item, (ViewGroup) this.mDepartments, false);
            TextView textView = (TextView) inflate.findViewById(R.id.text1);
            TextView textView2 = (TextView) inflate.findViewById(R.id.text2);
            textView.setText(departmentChild.getName());
            textView2.setText(departmentChild.getType_name());
            inflate.setTag(departmentChild);
            LinearLayout linearLayout = this.mDepartments;
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.addView(inflate);
            inflate.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.department.DepartmentProfileActivity$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DepartmentProfileActivity.bindDepartments$lambda$1(DepartmentProfileActivity.this, view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindDepartments$lambda$1(DepartmentProfileActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "view");
        this$0.clickDepartment(view);
    }

    private final void clickDepartment(View view) {
        Object tag = view.getTag();
        Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type com.m_myr.nuwm.nuwmschedule.data.models.DepartmentChild");
        INSTANCE.startById(this, ((DepartmentChild) tag).getId());
    }

    public final void onClickParent(View view) {
        INSTANCE.startById(this, this.presenter.getDepartmentProfile().getParentId());
    }

    /* compiled from: DepartmentProfileActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/department/DepartmentProfileActivity$Companion;", "", "()V", "startById", "", "context", "Landroid/content/Context;", "id", "", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final void startById(Context context, int id) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, (Class<?>) DepartmentProfileActivity.class);
            intent.putExtra("id", id);
            context.startActivity(intent);
        }
    }
}
