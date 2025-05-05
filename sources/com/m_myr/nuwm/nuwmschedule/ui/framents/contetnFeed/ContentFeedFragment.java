package com.m_myr.nuwm.nuwmschedule.ui.framents.contetnFeed;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.NewsViewItem;
import com.m_myr.nuwm.nuwmschedule.domain.adapter.RecyclerNewsAdapter;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class ContentFeedFragment extends Fragment implements IContentFeedView {
    private View error_layout;
    private View loading_layout;
    private RecyclerNewsAdapter mAdapter;
    private WrapStaggeredGridLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private int pastVisibleItems;
    private int totalItemCount;
    private int visibleItemCount;
    private ContentFeedPresenter contentFeedPresenter = new ContentFeedPresenter(this);
    RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.contetnFeed.ContentFeedFragment.1
        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            ContentFeedFragment contentFeedFragment = ContentFeedFragment.this;
            contentFeedFragment.visibleItemCount = contentFeedFragment.mLayoutManager.getChildCount();
            ContentFeedFragment contentFeedFragment2 = ContentFeedFragment.this;
            contentFeedFragment2.totalItemCount = contentFeedFragment2.mLayoutManager.getItemCount();
            int[] findFirstVisibleItemPositions = ContentFeedFragment.this.mLayoutManager.findFirstVisibleItemPositions(null);
            if (findFirstVisibleItemPositions != null && findFirstVisibleItemPositions.length > 0) {
                ContentFeedFragment.this.pastVisibleItems = findFirstVisibleItemPositions[0];
            }
            ContentFeedFragment.this.contentFeedPresenter.onScroll(ContentFeedFragment.this.visibleItemCount, ContentFeedFragment.this.pastVisibleItems, ContentFeedFragment.this.totalItemCount);
        }
    };

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.news_feed, (ViewGroup) null);
        this.mRecyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_view);
        WrapStaggeredGridLayoutManager wrapStaggeredGridLayoutManager = new WrapStaggeredGridLayoutManager(Utils.calculateNoOfColumns(getContext()), 1);
        this.mLayoutManager = wrapStaggeredGridLayoutManager;
        this.mRecyclerView.setLayoutManager(wrapStaggeredGridLayoutManager);
        this.loading_layout = inflate.findViewById(R.id.loading_layout);
        this.error_layout = inflate.findViewById(R.id.error_layout);
        this.loading_layout.setVisibility(0);
        this.error_layout.setVisibility(8);
        this.mRecyclerView.addOnScrollListener(this.scrollListener);
        RecyclerNewsAdapter recyclerNewsAdapter = new RecyclerNewsAdapter(getContext(), new ArrayList());
        this.mAdapter = recyclerNewsAdapter;
        this.mRecyclerView.setAdapter(recyclerNewsAdapter);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.contentFeedPresenter.get(getArguments());
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        WrapStaggeredGridLayoutManager wrapStaggeredGridLayoutManager = new WrapStaggeredGridLayoutManager(Utils.calculateNoOfColumns(getContext()), 1);
        this.mLayoutManager = wrapStaggeredGridLayoutManager;
        this.mRecyclerView.setLayoutManager(wrapStaggeredGridLayoutManager);
        this.mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.framents.contetnFeed.IContentFeedView
    public void updateList(ArrayList<NewsViewItem> arrayList) {
        this.mAdapter.addAll(arrayList);
        this.loading_layout.setVisibility(8);
    }
}
