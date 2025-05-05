package com.m_myr.nuwm.nuwmschedule.ui.activities.lastMarks;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.MarkItem;
import com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback;
import com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class LastMarksActivity extends BaseStateActivity {
    private RecyclerView mRecyclerView;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.last_marks_activity);
        setTitle("Історія оцінок");
        initView();
        OnRetry();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity
    public void OnRetry() {
        showLoading();
        FastRepository.from(this).call(APIMethods.getMarksHistory()).into(new RequestObjectCallback<ArrayList<MarkItem>>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.lastMarks.LastMarksActivity.1
            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onError(ErrorResponse response) {
                LastMarksActivity.this.showError(response.getMessage());
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onSuccessData(ArrayList<MarkItem> data) {
                LastMarksActivity.this.mRecyclerView.setAdapter(new MarksHistoryAdapter(LastMarksActivity.this.getContext(), data));
                LastMarksActivity.this.showContent();
            }
        }).start();
    }

    private void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.mRecyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
