package com.m_myr.nuwm.nuwmschedule.ui.activities.teacherGroups;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.messaging.Constants;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.CompositeGroup;
import com.m_myr.nuwm.nuwmschedule.data.models.Group;
import com.m_myr.nuwm.nuwmschedule.data.models.Stream;
import com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback;
import com.m_myr.nuwm.nuwmschedule.network.models.TeacherGroupsResponse;
import com.m_myr.nuwm.nuwmschedule.ui.AdapterContract;
import com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.groups.GroupsProfileActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.simpleScreen.StudentsListActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.stream.StreamProfileActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.teacherGroups.MyGroupsActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.worload.WorkloadActivity;
import com.m_myr.nuwm.nuwmschedule.ui.framents.BaseListFragment;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;

/* loaded from: classes2.dex */
public class MyGroupsActivity extends BaseStateActivity implements RequestObjectCallback<TeacherGroupsResponse> {
    private TeacherGroupsResponse data;
    private TabLayout tabLayout;
    private ViewPager viewpager;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_tabs_activity_layout);
        this.viewpager = (ViewPager) findViewById(R.id.viewpager);
        this.tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        setTitle("Мої студенти");
        loadData();
    }

    private void loadData() {
        showLoading();
        FastRepository.from(this).call(APIMethods.getTeacherGroup()).into(this).start();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
    public void onError(ErrorResponse response) {
        showError(response.getMessage());
    }

    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
    public void onSuccessData(TeacherGroupsResponse data) {
        this.data = data;
        this.viewpager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        this.tabLayout.setupWithViewPager(this.viewpager);
        this.viewpager.setOffscreenPageLimit(2);
        showContent();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity
    public void OnRetry(View view) {
        loadData();
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return 3;
        }

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm, 1);
        }

        @Override // androidx.fragment.app.FragmentStatePagerAdapter
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            if (position == 0) {
                bundle.putSerializable(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, MyGroupsActivity.this.data.getGroups());
                bundle.putSerializable("adapter", new GroupAdapters());
            }
            if (position == 1) {
                bundle.putSerializable(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, MyGroupsActivity.this.data.getStreams());
                bundle.putSerializable("adapter", new StreamAdapters());
            }
            if (position == 2) {
                bundle.putSerializable(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, MyGroupsActivity.this.data.getCompositeGroup());
                bundle.putSerializable("adapter", new CompositeGroupAdapters());
            }
            BaseListFragment baseListFragment = new BaseListFragment();
            baseListFragment.setArguments(bundle);
            return baseListFragment;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Групи";
            }
            if (position == 1) {
                return "Потоки";
            }
            if (position != 2) {
                return null;
            }
            return "Збірні групи";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class GroupAdapters extends AdapterContract<Group> {
        private GroupAdapters() {
        }

        @Override // com.m_myr.nuwm.nuwmschedule.ui.AdapterContract
        protected AdapterContract.ViewHolderGeneral inflate(ViewGroup parent) {
            return new AdapterContract.ViewHolderGeneral(LayoutInflater.from(parent.getContext()).inflate(R.layout.group_list, parent, false));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.m_myr.nuwm.nuwmschedule.ui.AdapterContract
        public void bind(AdapterContract.ViewHolderGeneral holder, Group o) {
            TextView textView = (TextView) holder.itemView.findViewById(R.id.name);
            TextView textView2 = (TextView) holder.itemView.findViewById(R.id.info);
            TextView textView3 = (TextView) holder.itemView.findViewById(R.id.faculty);
            textView.setText(o.getName());
            textView3.setText(o.getFaculty());
            textView2.setText(o.getCourse() + " курс, " + Utils.StringUtils.unitsFormat(o.getAmount(), "студент", "студенти", "студентів"));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.m_myr.nuwm.nuwmschedule.ui.AdapterContract
        public void onClick(final Context context, final Group o) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Група " + o.getName());
            builder.setItems(new String[]{"Список групи", "Навчальне навантаження", "(В розробці) Журнал успішності", "Інформація про групу", "(В розробці)  Надіслати сповіщення"}, new DialogInterface.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.teacherGroups.MyGroupsActivity$GroupAdapters$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    MyGroupsActivity.GroupAdapters.lambda$onClick$0(context, o, dialogInterface, i);
                }
            });
            builder.show();
        }

        static /* synthetic */ void lambda$onClick$0(Context context, Group group, DialogInterface dialogInterface, int i) {
            if (i == 0) {
                StudentsListActivity.start(context, group.getId(), group.getName());
            } else if (i == 1) {
                WorkloadActivity.start(context, group.getId(), group.getName());
            } else if (i == 3) {
                GroupsProfileActivity.startById(context, group.getId());
            } else {
                Utils.getStub(context).show();
            }
            dialogInterface.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class StreamAdapters extends AdapterContract<Stream> {
        private StreamAdapters() {
        }

        @Override // com.m_myr.nuwm.nuwmschedule.ui.AdapterContract
        protected AdapterContract.ViewHolderGeneral inflate(ViewGroup parent) {
            return new AdapterContract.ViewHolderGeneral(LayoutInflater.from(parent.getContext()).inflate(R.layout.stream_list_item, parent, false));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.m_myr.nuwm.nuwmschedule.ui.AdapterContract
        public void bind(AdapterContract.ViewHolderGeneral holder, Stream o) {
            TextView textView = (TextView) holder.itemView.findViewById(R.id.name);
            TextView textView2 = (TextView) holder.itemView.findViewById(R.id.info);
            textView.setText("Потік " + o.getStream());
            textView2.setText(o.getCourses() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + Utils.StringUtils.units(o.getCoursesCount(), "курс", "курси") + ", " + Utils.StringUtils.unitsFormat(o.getAmount(), "студент", "студенти", "студентів"));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.m_myr.nuwm.nuwmschedule.ui.AdapterContract
        public void onClick(final Context context, final Stream o) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Потік " + o.getName());
            builder.setItems(new String[]{"Список потоку", "Інформація про потік", "(В розробці) Надіслати сповіщення"}, new DialogInterface.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.teacherGroups.MyGroupsActivity$StreamAdapters$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    MyGroupsActivity.StreamAdapters.lambda$onClick$0(context, o, dialogInterface, i);
                }
            });
            builder.show();
        }

        static /* synthetic */ void lambda$onClick$0(Context context, Stream stream, DialogInterface dialogInterface, int i) {
            if (i == 0) {
                StudentsListActivity.startFromIds(context, stream.getGroupList(), "Потік " + stream.getName());
                return;
            }
            if (i == 1) {
                StreamProfileActivity.startByGroups(context, "Потік " + stream.getName(), stream.getGroupList());
                return;
            }
            Utils.getStub(context).show();
        }
    }

    private static class CompositeGroupAdapters extends AdapterContract<CompositeGroup> {
        private CompositeGroupAdapters() {
        }

        @Override // com.m_myr.nuwm.nuwmschedule.ui.AdapterContract
        protected AdapterContract.ViewHolderGeneral inflate(ViewGroup parent) {
            return new AdapterContract.ViewHolderGeneral(LayoutInflater.from(parent.getContext()).inflate(R.layout.compgroup_list_item, parent, false));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.m_myr.nuwm.nuwmschedule.ui.AdapterContract
        public void bind(AdapterContract.ViewHolderGeneral holder, CompositeGroup o) {
            TextView textView = (TextView) holder.itemView.findViewById(R.id.name);
            TextView textView2 = (TextView) holder.itemView.findViewById(R.id.info);
            TextView textView3 = (TextView) holder.itemView.findViewById(R.id.stream);
            textView.setText(o.getName());
            textView2.setText(o.getCourses() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + Utils.StringUtils.units(o.getCoursesCount(), "курс", "курси") + ", ≈ " + Utils.StringUtils.unitsFormat(o.getAmount(), "студент", "студенти", "студентів"));
            textView3.setText(o.getStream());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.m_myr.nuwm.nuwmschedule.ui.AdapterContract
        public void onClick(Context context, CompositeGroup o) {
            Toast.makeText(context, "Немає доступних дій", 0).show();
        }
    }
}
