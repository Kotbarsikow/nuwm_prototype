package com.m_myr.nuwm.nuwmschedule.ui.framents.pushFeed;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.firebase.messaging.Constants;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.feed.PostMessage;
import com.m_myr.nuwm.nuwmschedule.domain.adapter.RecyclerPushAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PushFeedFragment.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J&\u0010\u0013\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0010\u0010\u0018\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012J \u0010\u0019\u001a\u00020\u00102\u0016\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u00020\u001c0\u001bj\b\u0012\u0004\u0012\u00020\u001c`\u001dH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/framents/pushFeed/PushFeedFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/m_myr/nuwm/nuwmschedule/ui/framents/pushFeed/PushFeedOwner;", "()V", "mAdapter", "Lcom/m_myr/nuwm/nuwmschedule/domain/adapter/RecyclerPushAdapter;", "mErrorLayout", "Landroid/view/View;", "mLoadingLayout", "mRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "mSwipeRefreshLayout", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "pushFeedPresenter", "Lcom/m_myr/nuwm/nuwmschedule/ui/framents/pushFeed/PushFeedPresenterCompat;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onRestoreInstanceState", "setData", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Ljava/util/ArrayList;", "Lcom/m_myr/nuwm/nuwmschedule/data/models/feed/PostMessage;", "Lkotlin/collections/ArrayList;", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PushFeedFragment extends Fragment implements PushFeedOwner {
    private RecyclerPushAdapter mAdapter;
    private View mErrorLayout;
    private View mLoadingLayout;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private final PushFeedPresenterCompat pushFeedPresenter = new PushFeedPresenterCompat(this);

    public final void onRestoreInstanceState(Bundle savedInstanceState) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        onRestoreInstanceState(savedInstanceState);
        SwipeRefreshLayout swipeRefreshLayout = null;
        View inflate = inflater.inflate(R.layout.push_feed, (ViewGroup) null);
        View findViewById = inflate.findViewById(R.id.swipeRefreshLayout);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        SwipeRefreshLayout swipeRefreshLayout2 = (SwipeRefreshLayout) findViewById;
        this.mSwipeRefreshLayout = swipeRefreshLayout2;
        if (swipeRefreshLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSwipeRefreshLayout");
            swipeRefreshLayout2 = null;
        }
        swipeRefreshLayout2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.pushFeed.PushFeedFragment$$ExternalSyntheticLambda0
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                PushFeedFragment.onCreateView$lambda$0(PushFeedFragment.this);
            }
        });
        View findViewById2 = inflate.findViewById(R.id.recycler_view);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        RecyclerView recyclerView = (RecyclerView) findViewById2;
        this.mRecyclerView = recyclerView;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
            recyclerView = null;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        View findViewById3 = inflate.findViewById(R.id.error_layout);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.mErrorLayout = findViewById3;
        View findViewById4 = inflate.findViewById(R.id.loading_layout);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.mLoadingLayout = findViewById4;
        if (findViewById4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLoadingLayout");
            findViewById4 = null;
        }
        findViewById4.setVisibility(8);
        View view = this.mErrorLayout;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mErrorLayout");
            view = null;
        }
        view.setVisibility(8);
        Context context = getContext();
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
            recyclerView2 = null;
        }
        this.mAdapter = new RecyclerPushAdapter(context, recyclerView2);
        RecyclerView recyclerView3 = this.mRecyclerView;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
            recyclerView3 = null;
        }
        RecyclerPushAdapter recyclerPushAdapter = this.mAdapter;
        if (recyclerPushAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            recyclerPushAdapter = null;
        }
        recyclerView3.setAdapter(recyclerPushAdapter);
        View view2 = this.mLoadingLayout;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLoadingLayout");
            view2 = null;
        }
        view2.setVisibility(0);
        View view3 = this.mErrorLayout;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mErrorLayout");
            view3 = null;
        }
        view3.setVisibility(4);
        RecyclerView recyclerView4 = this.mRecyclerView;
        if (recyclerView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
            recyclerView4 = null;
        }
        RecyclerView.ItemAnimator itemAnimator = recyclerView4.getItemAnimator();
        if (itemAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        }
        SwipeRefreshLayout swipeRefreshLayout3 = this.mSwipeRefreshLayout;
        if (swipeRefreshLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSwipeRefreshLayout");
        } else {
            swipeRefreshLayout = swipeRefreshLayout3;
        }
        swipeRefreshLayout.setColorScheme(R.color.colorPrimary, R.color.colorPrimaryMaterial, R.color.colorPrimaryDark, R.color.colorPrimaryBlue, R.color.colorAccent);
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateView$lambda$0(PushFeedFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.pushFeedPresenter.reload();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.framents.pushFeed.PushFeedOwner
    public void setData(ArrayList<PostMessage> data) {
        Object obj;
        Intrinsics.checkNotNullParameter(data, "data");
        SwipeRefreshLayout swipeRefreshLayout = this.mSwipeRefreshLayout;
        RecyclerView recyclerView = null;
        if (swipeRefreshLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSwipeRefreshLayout");
            swipeRefreshLayout = null;
        }
        swipeRefreshLayout.setRefreshing(false);
        RecyclerPushAdapter recyclerPushAdapter = this.mAdapter;
        if (recyclerPushAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            recyclerPushAdapter = null;
        }
        recyclerPushAdapter.setData(data);
        View view = this.mLoadingLayout;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLoadingLayout");
            view = null;
        }
        view.setVisibility(8);
        View view2 = this.mErrorLayout;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mErrorLayout");
            view2 = null;
        }
        view2.setVisibility(8);
        if (getArguments() == null || requireArguments().getInt("uid", -1) <= 0) {
            return;
        }
        Iterator<T> it = data.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (((PostMessage) obj).getUid() == requireArguments().getInt("uid", -1)) {
                    break;
                }
            }
        }
        PostMessage postMessage = (PostMessage) obj;
        if (postMessage != null) {
            requireArguments().remove("uid");
            PostMessage postMessage2 = data.get(data.indexOf(postMessage));
            Intrinsics.checkNotNullExpressionValue(postMessage2, "get(...)");
            PostMessage postMessage3 = postMessage2;
            postMessage3.highlight();
            RecyclerPushAdapter recyclerPushAdapter2 = this.mAdapter;
            if (recyclerPushAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
                recyclerPushAdapter2 = null;
            }
            recyclerPushAdapter2.updateItem(data.indexOf(postMessage), postMessage3);
            RecyclerView recyclerView2 = this.mRecyclerView;
            if (recyclerView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
            } else {
                recyclerView = recyclerView2;
            }
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
            ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(data.indexOf(postMessage), 0);
        }
    }
}
