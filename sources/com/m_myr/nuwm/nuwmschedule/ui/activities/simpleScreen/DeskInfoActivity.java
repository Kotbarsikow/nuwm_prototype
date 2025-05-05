package com.m_myr.nuwm.nuwmschedule.ui.activities.simpleScreen;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.HtmlData;
import com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener;
import com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity;

/* loaded from: classes2.dex */
public class DeskInfoActivity extends BaseStateActivity {
    private TextView mTextHtml;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setScrollContentView(R.layout.desc_about_activity_layout);
        setTitle("Ваші анкетні дані  ");
        this.mTextHtml = (TextView) findViewById(R.id.text_html);
        loadData();
    }

    private void loadData() {
        setActivityState(1);
        FastRepository.from(this).call(APIMethods.getUserInfo()).into((APIObjectListener) new APIObjectListener<HtmlData>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.simpleScreen.DeskInfoActivity.1
            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onError(ErrorResponse response) {
                DeskInfoActivity.this.setActivityState(-1, response.getMessage());
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onSuccessData(HtmlData data) {
                DeskInfoActivity.this.mTextHtml.setText(data.getHtml());
                DeskInfoActivity.this.setActivityState(0);
            }
        }).start();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity
    public void OnRetry(View view) {
        loadData();
    }
}
