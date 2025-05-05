package com.m_myr.nuwm.nuwmschedule.ui.activities.repositoryPeople;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.slider.RangeSlider;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.KeyPairValue;
import com.m_myr.nuwm.nuwmschedule.data.models.RepositoryItem;
import com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.user.UserPersonDocumentAdapter;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class UserRepositoryActivity extends BaseStateActivity implements UserRepositoryView {
    BottomSheetBehavior<LinearLayout> behavior;
    private LinearLayout mBottomSheet;
    private MaterialButton mBtSearch;
    private RangeSlider mRangeYears;
    private RecyclerView mRecyclerView;
    private View mShading;
    private ChipGroup mSortFilters;
    private TextView mToolbarReset;
    private ChipGroup mTypeFilters;
    UserRepositoryPresenter presenter = new UserRepositoryPresenter(this);

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_repository_activity);
        overrideErrorId(R.id.override_empty);
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.mToolbarReset = (TextView) findViewById(R.id.toolbar_reset);
        this.mRangeYears = (RangeSlider) findViewById(R.id.rangeYears);
        this.mBtSearch = (MaterialButton) findViewById(R.id.bt_search);
        this.mBottomSheet = (LinearLayout) findViewById(R.id.bottom_sheet);
        this.mShading = findViewById(R.id.shading);
        this.mTypeFilters = (ChipGroup) findViewById(R.id.typeFilters);
        this.mSortFilters = (ChipGroup) findViewById(R.id.sortFilters);
        BottomSheetBehavior<LinearLayout> from = BottomSheetBehavior.from(this.mBottomSheet);
        this.behavior = from;
        from.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.repositoryPeople.UserRepositoryActivity.1
            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onStateChanged(View bottomSheet, int newState) {
                if (newState == 5) {
                    UserRepositoryActivity.this.mShading.setVisibility(8);
                }
            }

            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onSlide(View bottomSheet, float slideOffset) {
                if (slideOffset > -1.0f) {
                    UserRepositoryActivity.this.mShading.setVisibility(0);
                    UserRepositoryActivity.this.mShading.setAlpha((slideOffset + 1.0f) / 2.0f);
                } else {
                    UserRepositoryActivity.this.mShading.setVisibility(8);
                }
            }
        });
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.repositoryPeople.UserRepositoryView
    public void setFilterRange(int minY, int maxY) {
        if (minY == maxY) {
            this.mRangeYears.setEnabled(false);
            return;
        }
        float f = minY;
        this.mRangeYears.setValueFrom(f);
        float f2 = maxY;
        this.mRangeYears.setValueTo(f2);
        this.mRangeYears.setValues(Float.valueOf(f), Float.valueOf(f2));
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.repository_menu, menu);
        return true;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.repositoryPeople.UserRepositoryView
    public void setFilterTypes(ArrayList<KeyPairValue<String>> documentTypes) {
        Iterator<KeyPairValue<String>> it = documentTypes.iterator();
        while (it.hasNext()) {
            KeyPairValue<String> next = it.next();
            Chip chip = (Chip) getLayoutInflater().inflate(R.layout.chip_layout_choice, (ViewGroup) null, false);
            chip.setText(next.getValue());
            chip.setTag(next.getKey());
            this.mTypeFilters.addView(chip);
        }
    }

    public void onFilters(MenuItem item) {
        this.behavior.setState(3);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.repositoryPeople.UserRepositoryView
    public void showFilter(boolean show) {
        this.behavior.setState(show ? 3 : 5);
        this.mShading.setVisibility(show ? 0 : 8);
        this.mShading.setAlpha(show ? 1.0f : 0.0f);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.repositoryPeople.UserRepositoryView
    public void onDataSet(ArrayList<RepositoryItem> fullData) {
        this.mRecyclerView.setAdapter(new UserPersonDocumentAdapter(this, fullData, true));
        if (fullData.isEmpty()) {
            showEmpty("Порожньо");
        } else {
            showContent();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.behavior.getState() != 5) {
            showFilter(false);
        } else {
            super.onBackPressed();
        }
    }

    public void onFilterChanged(View view) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < this.mTypeFilters.getChildCount(); i++) {
            Chip chip = (Chip) this.mTypeFilters.getChildAt(i);
            if (chip.isChecked()) {
                arrayList.add((String) chip.getTag());
            }
        }
        this.presenter.onFilterChanged(arrayList, this.mRangeYears.getValues(), this.mSortFilters.getCheckedChipId());
    }

    public void onReset(View view) {
        for (int i = 0; i < this.mTypeFilters.getChildCount(); i++) {
            ((Chip) this.mTypeFilters.getChildAt(i)).setChecked(false);
        }
        ((Chip) this.mSortFilters.findViewById(R.id.sortByDate)).setChecked(true);
        this.presenter.reset();
    }
}
