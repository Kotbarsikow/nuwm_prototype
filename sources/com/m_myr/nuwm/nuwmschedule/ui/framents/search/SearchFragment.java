package com.m_myr.nuwm.nuwmschedule.ui.framents.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.search.BaseSearchResult;
import com.m_myr.nuwm.nuwmschedule.data.models.search.EmployerSearchItem;
import com.m_myr.nuwm.nuwmschedule.data.models.search.RepositorySearchItem;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.domain.adapter.SearchAdapter;
import com.m_myr.nuwm.nuwmschedule.domain.adapter.SimplePeopleAdapter;
import com.m_myr.nuwm.nuwmschedule.ui.activities.user.UserPersonDocumentAdapter;
import com.m_myr.nuwm.nuwmschedule.ui.view.MaterialEditText;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SearchFragment.kt */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0019\u001a\u00020\u001aJ&\u0010\u001b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010\"\u001a\u00020#H\u0016J\u001a\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0016\u0010&\u001a\u00020#2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(H\u0016J\u0016\u0010*\u001a\u00020#2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020-0,H\u0016J\u0016\u0010.\u001a\u00020#2\f\u0010/\u001a\b\u0012\u0004\u0012\u0002000,H\u0016J\b\u00101\u001a\u00020#H\u0002J\u0010\u00102\u001a\u00020#2\u0006\u00103\u001a\u00020\u001aH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/framents/search/SearchFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/m_myr/nuwm/nuwmschedule/ui/framents/search/ISearchView;", "()V", "adapter", "Lcom/m_myr/nuwm/nuwmschedule/domain/adapter/SearchAdapter;", "mClear", "Landroid/widget/ImageView;", "mDocTitile", "Landroid/view/View;", "mLastSearch", "Landroid/widget/LinearLayout;", "mPeopleTitle", "mQuery", "Lcom/m_myr/nuwm/nuwmschedule/ui/view/MaterialEditText;", "mRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "mRecyclerViewLastDoc", "mRecyclerViewLastPeople", "mToolbarTitle", "Landroidx/appcompat/widget/AppCompatTextView;", "presenter", "Lcom/m_myr/nuwm/nuwmschedule/ui/framents/search/SearchPresenter;", "progressBar", "Landroid/widget/ProgressBar;", "onBackPressed", "", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "", "onViewCreated", "view", "setHistoryDocuments", "repositoryItems", "", "Lcom/m_myr/nuwm/nuwmschedule/data/models/search/RepositorySearchItem;", "setHistoryEmployers", "employerItems", "Ljava/util/ArrayList;", "Lcom/m_myr/nuwm/nuwmschedule/data/models/search/EmployerSearchItem;", "setResult", "results", "Lcom/m_myr/nuwm/nuwmschedule/data/models/search/BaseSearchResult;", "setUpProfileIcon", "showInto", "show", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SearchFragment extends Fragment implements ISearchView {
    private SearchAdapter adapter;
    private ImageView mClear;
    private View mDocTitile;
    private LinearLayout mLastSearch;
    private View mPeopleTitle;
    private MaterialEditText mQuery;
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerViewLastDoc;
    private RecyclerView mRecyclerViewLastPeople;
    private AppCompatTextView mToolbarTitle;
    private final SearchPresenter presenter = new SearchPresenter(this);
    private ProgressBar progressBar;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.adapter = new SearchAdapter(getContext(), this.presenter);
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        if (AppDataManager.getInstance().getUserPermission().isIdCard()) {
            view.findViewById(R.id.idCard).setVisibility(0);
        }
        this.presenter.loadHistory();
        this.mDocTitile = view.findViewById(R.id.docTitile);
        this.mPeopleTitle = view.findViewById(R.id.peopleTitle);
        this.mToolbarTitle = (AppCompatTextView) view.findViewById(R.id.toolbar_title);
        this.mLastSearch = (LinearLayout) view.findViewById(R.id.last_search);
        this.mRecyclerViewLastPeople = (RecyclerView) view.findViewById(R.id.recyclerViewLastPeople);
        this.mRecyclerViewLastDoc = (RecyclerView) view.findViewById(R.id.recyclerViewLastDoc);
        this.progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        this.mQuery = (MaterialEditText) view.findViewById(R.id.query);
        this.mClear = (ImageView) view.findViewById(R.id.clear);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        this.mRecyclerView = recyclerView;
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView recyclerView2 = this.mRecyclerView;
        Intrinsics.checkNotNull(recyclerView2);
        recyclerView2.setAdapter(this.adapter);
        ImageView imageView = this.mClear;
        Intrinsics.checkNotNull(imageView);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.search.SearchFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SearchFragment.onViewCreated$lambda$0(SearchFragment.this, view2);
            }
        });
        MaterialEditText materialEditText = this.mQuery;
        Intrinsics.checkNotNull(materialEditText);
        materialEditText.addTextChangedListener(new TextWatcher() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.search.SearchFragment$onViewCreated$2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                SearchPresenter searchPresenter;
                ImageView imageView2;
                ProgressBar progressBar;
                Intrinsics.checkNotNullParameter(s, "s");
                searchPresenter = SearchFragment.this.presenter;
                if (searchPresenter.textChanged(s)) {
                    progressBar = SearchFragment.this.progressBar;
                    Intrinsics.checkNotNull(progressBar);
                    progressBar.setVisibility(0);
                }
                imageView2 = SearchFragment.this.mClear;
                Intrinsics.checkNotNull(imageView2);
                imageView2.setVisibility(4);
            }
        });
        setUpProfileIcon();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(SearchFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MaterialEditText materialEditText = this$0.mQuery;
        Intrinsics.checkNotNull(materialEditText);
        materialEditText.setText("");
    }

    private final void setUpProfileIcon() {
        Glide.with(this).load(AppDataManager.getInstance().getUser().getProfileImage()).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform()).into((ImageView) requireView().findViewById(R.id.profileIcon));
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.framents.search.ISearchView
    public void showInto(boolean show) {
        if (show) {
            requireView().findViewById(R.id.intoText).setVisibility(0);
            requireView().findViewById(R.id.imageView).setVisibility(0);
        } else {
            requireView().findViewById(R.id.intoText).setVisibility(8);
            requireView().findViewById(R.id.imageView).setVisibility(8);
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.framents.search.ISearchView
    public void setResult(ArrayList<BaseSearchResult> results) {
        Intrinsics.checkNotNullParameter(results, "results");
        SearchAdapter searchAdapter = this.adapter;
        Intrinsics.checkNotNull(searchAdapter);
        searchAdapter.setData(results);
        RecyclerView recyclerView = this.mRecyclerView;
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setVisibility(0);
        LinearLayout linearLayout = this.mLastSearch;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(8);
        ProgressBar progressBar = this.progressBar;
        Intrinsics.checkNotNull(progressBar);
        progressBar.setVisibility(4);
        MaterialEditText materialEditText = this.mQuery;
        Intrinsics.checkNotNull(materialEditText);
        if (materialEditText.getText().toString().length() == 0) {
            ImageView imageView = this.mClear;
            Intrinsics.checkNotNull(imageView);
            imageView.setVisibility(4);
        } else {
            ImageView imageView2 = this.mClear;
            Intrinsics.checkNotNull(imageView2);
            imageView2.setVisibility(0);
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.framents.search.ISearchView
    public void setHistoryEmployers(ArrayList<EmployerSearchItem> employerItems) {
        Intrinsics.checkNotNullParameter(employerItems, "employerItems");
        if (employerItems.isEmpty()) {
            RecyclerView recyclerView = this.mRecyclerViewLastPeople;
            Intrinsics.checkNotNull(recyclerView);
            recyclerView.setVisibility(8);
            View view = this.mPeopleTitle;
            Intrinsics.checkNotNull(view);
            view.setVisibility(8);
            requireView().findViewById(R.id.peopleTitle).setVisibility(8);
        } else {
            RecyclerView recyclerView2 = this.mRecyclerViewLastPeople;
            Intrinsics.checkNotNull(recyclerView2);
            recyclerView2.setVisibility(0);
            View view2 = this.mPeopleTitle;
            Intrinsics.checkNotNull(view2);
            view2.setVisibility(0);
            requireView().findViewById(R.id.peopleTitle).setVisibility(0);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), 0, true);
        linearLayoutManager.setStackFromEnd(true);
        RecyclerView recyclerView3 = this.mRecyclerViewLastPeople;
        Intrinsics.checkNotNull(recyclerView3);
        recyclerView3.setLayoutManager(linearLayoutManager);
        RecyclerView recyclerView4 = this.mRecyclerViewLastPeople;
        Intrinsics.checkNotNull(recyclerView4);
        recyclerView4.setAdapter(new SimplePeopleAdapter(getActivity(), employerItems, false));
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.framents.search.ISearchView
    public void setHistoryDocuments(List<? extends RepositorySearchItem> repositoryItems) {
        Intrinsics.checkNotNullParameter(repositoryItems, "repositoryItems");
        if (repositoryItems.isEmpty()) {
            RecyclerView recyclerView = this.mRecyclerViewLastDoc;
            Intrinsics.checkNotNull(recyclerView);
            recyclerView.setVisibility(8);
            View view = this.mDocTitile;
            Intrinsics.checkNotNull(view);
            view.setVisibility(8);
            requireView().findViewById(R.id.docTitile).setVisibility(8);
        } else {
            RecyclerView recyclerView2 = this.mRecyclerViewLastDoc;
            Intrinsics.checkNotNull(recyclerView2);
            recyclerView2.setVisibility(0);
            View view2 = this.mDocTitile;
            Intrinsics.checkNotNull(view2);
            view2.setVisibility(0);
            requireView().findViewById(R.id.docTitile).setVisibility(0);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), 0, true);
        linearLayoutManager.setStackFromEnd(true);
        RecyclerView recyclerView3 = this.mRecyclerViewLastDoc;
        Intrinsics.checkNotNull(recyclerView3);
        recyclerView3.setLayoutManager(linearLayoutManager);
        RecyclerView recyclerView4 = this.mRecyclerViewLastDoc;
        Intrinsics.checkNotNull(recyclerView4);
        recyclerView4.setAdapter(new UserPersonDocumentAdapter(getActivity(), repositoryItems, false));
    }

    public final boolean onBackPressed() {
        MaterialEditText materialEditText = this.mQuery;
        Intrinsics.checkNotNull(materialEditText);
        if (!materialEditText.isFocused()) {
            return false;
        }
        RecyclerView recyclerView = this.mRecyclerView;
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setVisibility(4);
        LinearLayout linearLayout = this.mLastSearch;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(0);
        MaterialEditText materialEditText2 = this.mQuery;
        Intrinsics.checkNotNull(materialEditText2);
        materialEditText2.setText("");
        MaterialEditText materialEditText3 = this.mQuery;
        Intrinsics.checkNotNull(materialEditText3);
        materialEditText3.clearFocus();
        this.presenter.loadHistory();
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        RecyclerView recyclerView = this.mRecyclerView;
        Intrinsics.checkNotNull(recyclerView);
        if (recyclerView.getVisibility() == 0) {
            showInto(false);
        }
    }
}
