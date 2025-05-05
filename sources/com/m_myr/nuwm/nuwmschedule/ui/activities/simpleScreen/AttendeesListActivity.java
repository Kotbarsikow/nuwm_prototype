package com.m_myr.nuwm.nuwmschedule.ui.activities.simpleScreen;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.firebase.messaging.Constants;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.Attendee;
import com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback;
import com.m_myr.nuwm.nuwmschedule.ui.AdapterContract;
import com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity;
import com.m_myr.nuwm.nuwmschedule.ui.framents.BaseListFragment;
import com.m_myr.nuwm.nuwmschedule.ui.view.MaterialTabLayout;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes2.dex */
public class AttendeesListActivity extends BaseStateActivity implements RequestObjectCallback<ArrayList<Attendee>> {
    private ArrayList<Attendee> attendeeArrayList;
    private MaterialTabLayout tabLayout;
    private ViewPager viewpager;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_tabs_activity_layout);
        this.viewpager = (ViewPager) findViewById(R.id.viewpager);
        this.tabLayout = (MaterialTabLayout) findViewById(R.id.tabLayout);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, 0, 0, 0);
        this.tabLayout.setLayoutParams(layoutParams);
        this.tabLayout.setTabMode(0);
        setTitle("Учасники");
        loadData();
    }

    private void loadData() {
        showLoading();
        FastRepository.from(this).call(APIMethods.getAttendees(getIntent().getStringExtra("eventId"))).into(this).start();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
    public void onError(ErrorResponse response) {
        showError(response.getMessage());
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity
    public void OnRetry(View view) {
        loadData();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
    public void onSuccessData(ArrayList<Attendee> data) {
        final Collator collator = Collator.getInstance(new Locale("uk", "UA"));
        Collections.sort(data, new Comparator() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.simpleScreen.AttendeesListActivity$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = collator.compare(((Attendee) obj).getName(), ((Attendee) obj2).getName());
                return compare;
            }
        });
        this.attendeeArrayList = data;
        this.viewpager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        this.tabLayout.setupWithViewPager(this.viewpager);
        this.viewpager.setOffscreenPageLimit(2);
        showContent();
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return 5;
        }

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm, 1);
        }

        @Override // androidx.fragment.app.FragmentStatePagerAdapter
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("adapter", new AttendeeAdapter());
            if (position == 0) {
                bundle.putSerializable(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, AttendeesListActivity.this.attendeeArrayList);
            }
            if (position == 1) {
                bundle.putSerializable(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, AttendeesListActivity.this.getFiltred("accepted"));
            }
            if (position == 2) {
                bundle.putSerializable(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, AttendeesListActivity.this.getFiltred("tentative"));
            }
            if (position == 3) {
                bundle.putSerializable(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, AttendeesListActivity.this.getFiltred("needsAction"));
            }
            if (position == 4) {
                bundle.putSerializable(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, AttendeesListActivity.this.getFiltred("declined"));
            }
            BaseListFragment baseListFragment = new BaseListFragment();
            baseListFragment.setArguments(bundle);
            return baseListFragment;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Всі учасники";
            }
            if (position == 1) {
                return "Підтердили участь";
            }
            if (position == 2) {
                return "Прийняли запрошення";
            }
            if (position == 3) {
                return "Не відповіли";
            }
            if (position != 4) {
                return null;
            }
            return "Відхилили";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<Attendee> getFiltred(String filter) {
        ArrayList<Attendee> arrayList = new ArrayList<>();
        Iterator<Attendee> it = this.attendeeArrayList.iterator();
        while (it.hasNext()) {
            Attendee next = it.next();
            if (filter.equals(next.getStatus())) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    private static class AttendeeAdapter extends AdapterContract<Attendee> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.m_myr.nuwm.nuwmschedule.ui.AdapterContract
        public void onClick(Context context, Attendee o) {
        }

        private AttendeeAdapter() {
        }

        @Override // com.m_myr.nuwm.nuwmschedule.ui.AdapterContract
        protected AdapterContract.ViewHolderGeneral inflate(ViewGroup parent) {
            return new AdapterContract.ViewHolderGeneral(LayoutInflater.from(parent.getContext()).inflate(R.layout.attendee_item, parent, false));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.m_myr.nuwm.nuwmschedule.ui.AdapterContract
        public void bind(AdapterContract.ViewHolderGeneral holder, Attendee o) {
            TextView textView = (TextView) holder.itemView.findViewById(R.id.name);
            TextView textView2 = (TextView) holder.itemView.findViewById(R.id.email);
            TextView textView3 = (TextView) holder.itemView.findViewById(R.id.post);
            textView.setText(o.getName());
            textView2.setText(o.getEmail());
            textView3.setText(o.getPost());
            if (o.getName() == null || o.getName().isEmpty()) {
                textView.setText("Без імені");
            }
            if (o.getPost() == null || o.getPost().isEmpty()) {
                textView3.setText("---");
            }
        }
    }
}
