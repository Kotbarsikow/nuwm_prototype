package com.m_myr.nuwm.nuwmschedule.ui.activities.simpleScreen;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.StudentNumw;
import com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.DataUniqueId;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener;
import com.m_myr.nuwm.nuwmschedule.network.api.ApiRequestBuilder;
import com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.imageview.ImageViewActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.simpleScreen.StudentsListActivity;
import com.m_myr.nuwm.nuwmschedule.utils.LinksResolver;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes2.dex */
public class StudentsListActivity extends BaseStateActivity {
    private ListView mList;
    boolean multipleGroups;
    private ArrayList<StudentNumw> studentNumws;

    public static void start(Context context, int id, String groupName) {
        Intent intent = new Intent(context, (Class<?>) StudentsListActivity.class);
        intent.putExtra(FirebaseAnalytics.Param.GROUP_ID, id);
        intent.putExtra("group_name", groupName);
        context.startActivity(intent);
    }

    public static void startFromIds(Context context, List<? extends DataUniqueId> dataUniqueIds, String groupName) {
        Intent intent = new Intent(context, (Class<?>) StudentsListActivity.class);
        intent.putExtra("group_ids", Utils.StringUtils.join(",", dataUniqueIds));
        intent.putExtra("group_name", groupName);
        context.startActivity(intent);
    }

    public static void startFromName(Context context, List<String> groupsName, String groupName) {
        Intent intent = new Intent(context, (Class<?>) StudentsListActivity.class);
        intent.putExtra("group_names", Utils.StringUtils.joinString(",", groupsName));
        intent.putExtra("group_name", groupName);
        context.startActivity(intent);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_group_activity_layout);
        this.mList = (ListView) findViewById(R.id.list);
        loadData();
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_groups, menu);
        if (this.multipleGroups) {
            menu.findItem(R.id.item4).setVisible(true);
        }
        return true;
    }

    private void loadData() {
        ApiRequestBuilder<ArrayList<StudentNumw>> myGroupStudents;
        if (getIntent().getStringExtra("group_names") != null) {
            myGroupStudents = APIMethods.findStudentsByGroupsName(getIntent().getStringExtra("group_names"));
            String stringExtra = getIntent().getStringExtra("group_name");
            setTitle(stringExtra != null ? stringExtra : "Список груп");
            this.multipleGroups = true;
        } else if (getIntent().getIntExtra(FirebaseAnalytics.Param.GROUP_ID, 0) != 0) {
            myGroupStudents = APIMethods.getStudentsByGroup(getIntent().getIntExtra(FirebaseAnalytics.Param.GROUP_ID, -1));
            String stringExtra2 = getIntent().getStringExtra("group_name");
            if (stringExtra2 == null) {
                stringExtra2 = "Список групи";
            }
            setTitle(stringExtra2);
        } else if (getIntent().getStringExtra("group_ids") != null) {
            myGroupStudents = APIMethods.getStudentsByGroupsIds(getIntent().getStringExtra("group_ids"));
            String stringExtra3 = getIntent().getStringExtra("group_name");
            setTitle(stringExtra3 != null ? stringExtra3 : "Список груп");
            this.multipleGroups = true;
        } else {
            myGroupStudents = APIMethods.getMyGroupStudents();
            setTitle("Моя група");
        }
        setActivityState(1);
        FastRepository.from(this).call(myGroupStudents).into((APIObjectListener) new APIObjectListener<ArrayList<StudentNumw>>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.simpleScreen.StudentsListActivity.1
            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onError(ErrorResponse response) {
                StudentsListActivity.this.setActivityState(-1, response.getMessage());
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onSuccessData(ArrayList<StudentNumw> data) {
                Iterator<StudentNumw> it = data.iterator();
                while (it.hasNext()) {
                    it.next().setId_fac(Utils.getColorOfId(r1.getId()));
                }
                StudentsListActivity.this.studentNumws = data;
                StudentsListActivity studentsListActivity = StudentsListActivity.this;
                studentsListActivity.setData(studentsListActivity.studentNumws);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setData(ArrayList<StudentNumw> data) {
        this.mList.setAdapter((ListAdapter) new StudentNumwListAdapter(this, data, this.multipleGroups));
        if (data.size() == 0) {
            showEmpty("Порожньо");
        } else {
            showContent();
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity
    public void OnRetry(View view) {
        loadData();
    }

    public void onCopy(MenuItem item) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < this.studentNumws.size()) {
            int i2 = i + 1;
            sb.append(i2);
            sb.append(". ");
            sb.append(this.studentNumws.get(i).getFullName());
            sb.append("\n");
            i = i2;
        }
        LinksResolver.copyToClipboard(sb.toString());
    }

    public void onSort(MenuItem item) {
        Comparator comparator;
        final Collator collator = Collator.getInstance(new Locale("uk", "UA"));
        switch (item.getItemId()) {
            case R.id.item1 /* 2131362298 */:
                comparator = new Comparator() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.simpleScreen.StudentsListActivity$$ExternalSyntheticLambda0
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        int compare;
                        compare = collator.compare(((StudentNumw) obj).getFirstName(), ((StudentNumw) obj2).getFirstName());
                        return compare;
                    }
                };
                break;
            case R.id.item2 /* 2131362299 */:
                comparator = new Comparator() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.simpleScreen.StudentsListActivity$$ExternalSyntheticLambda1
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        int compare;
                        compare = collator.compare(((StudentNumw) obj).getLastName(), ((StudentNumw) obj2).getLastName());
                        return compare;
                    }
                };
                break;
            case R.id.item3 /* 2131362300 */:
                comparator = new Comparator() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.simpleScreen.StudentsListActivity$$ExternalSyntheticLambda2
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        int compareTo;
                        compareTo = ((StudentNumw) obj).getFfinans().compareTo(((StudentNumw) obj2).getFfinans());
                        return compareTo;
                    }
                };
                break;
            case R.id.item4 /* 2131362301 */:
                comparator = new Comparator() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.simpleScreen.StudentsListActivity$$ExternalSyntheticLambda3
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        int compareTo;
                        compareTo = ((StudentNumw) obj).getGroupName().compareTo(((StudentNumw) obj2).getGroupName());
                        return compareTo;
                    }
                };
                break;
            default:
                comparator = null;
                break;
        }
        Collections.sort(this.studentNumws, comparator);
        setData(this.studentNumws);
    }

    protected static class StudentNumwListAdapter extends ArrayAdapter<StudentNumw> {
        private Context context;
        private final ArrayList<StudentNumw> data;
        boolean showGroups;

        public StudentNumwListAdapter(Context context, ArrayList<StudentNumw> dataList, boolean multipleGroups) {
            super(context, R.layout.student_list_item, dataList);
            this.context = context;
            this.data = dataList;
            this.showGroups = multipleGroups;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.student_list_item, (ViewGroup) null);
                viewHolder = new ViewHolder();
                viewHolder.mProfileImage = (ImageView) convertView.findViewById(R.id.profile_image);
                viewHolder.mUsername = (TextView) convertView.findViewById(R.id.username);
                viewHolder.mUsertype = (TextView) convertView.findViewById(R.id.usertype);
                viewHolder.mProfileLetter = (TextView) convertView.findViewById(R.id.profile_image_letter);
                viewHolder.mOnline = convertView.findViewById(R.id.online);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            final StudentNumw item = getItem(position);
            viewHolder.mUsername.setText(item.getFullName());
            if (this.showGroups) {
                viewHolder.mUsertype.setText(item.getGroupName() + ", " + Utils.StringUtils.safeTrim(item.getFfinans()).toLowerCase());
            } else {
                viewHolder.mUsertype.setText(item.getFfinans());
            }
            if (item.isAppUsage()) {
                viewHolder.mOnline.setVisibility(0);
            } else {
                viewHolder.mOnline.setVisibility(4);
            }
            if (!Utils.StringUtils.isBlank(item.getProfileImage())) {
                viewHolder.mProfileImage.setVisibility(0);
                Glide.with(getContext()).load(item.getProfileImage()).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform()).into(viewHolder.mProfileImage);
                viewHolder.mProfileLetter.setVisibility(4);
            } else {
                viewHolder.mProfileLetter.setVisibility(0);
                ViewCompat.setBackgroundTintList(viewHolder.mProfileLetter, ColorStateList.valueOf(item.getId_fac()));
                viewHolder.mProfileLetter.setText(item.getFirstName().substring(0, 1));
                viewHolder.mProfileImage.setVisibility(4);
            }
            convertView.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.simpleScreen.StudentsListActivity.StudentNumwListAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    StudentNumwListAdapter.this.showInfoAlert(position, item);
                }
            });
            return convertView;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showInfoAlert(int position, final StudentNumw myDataItem) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this.context).inflate(R.layout.student_list_item_extended, (ViewGroup) null);
            ((TextView) viewGroup.findViewById(R.id.email)).setText(Html.fromHtml("<b>Email: </b>" + myDataItem.getEmail()));
            viewGroup.findViewById(R.id.send_email).setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.simpleScreen.StudentsListActivity$StudentNumwListAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    StudentsListActivity.StudentNumwListAdapter.this.m197x8c1c22ae(myDataItem, view);
                }
            });
            viewGroup.findViewById(R.id.copy_email).setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.simpleScreen.StudentsListActivity$StudentNumwListAdapter$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LinksResolver.copyToClipboard(StudentNumw.this.getEmail());
                }
            });
            View view = getView(position, null, null);
            view.setClickable(false);
            viewGroup.addView(view, 0);
            ((ImageView) view.findViewById(R.id.profile_image)).setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.simpleScreen.StudentsListActivity$StudentNumwListAdapter$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    StudentsListActivity.StudentNumwListAdapter.lambda$showInfoAlert$2(StudentNumw.this, view2);
                }
            });
            builder.setPositiveButton("Ok", (DialogInterface.OnClickListener) null);
            builder.setView(viewGroup);
            builder.show();
        }

        /* renamed from: lambda$showInfoAlert$0$com-m_myr-nuwm-nuwmschedule-ui-activities-simpleScreen-StudentsListActivity$StudentNumwListAdapter, reason: not valid java name */
        /* synthetic */ void m197x8c1c22ae(StudentNumw studentNumw, View view) {
            LinksResolver.openEmail(getContext(), studentNumw.getEmail());
        }

        static /* synthetic */ void lambda$showInfoAlert$2(StudentNumw studentNumw, View view) {
            Intent intent = new Intent(view.getContext(), (Class<?>) ImageViewActivity.class);
            intent.putExtra(ImagesContract.URL, studentNumw.getProfileImage());
            ActivityCompat.startActivity(view.getContext(), intent, ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) view.getContext(), view, "image").toBundle());
        }

        public class ViewHolder {
            View mOnline;
            ImageView mProfileImage;
            TextView mProfileLetter;
            TextView mUsername;
            TextView mUsertype;

            public ViewHolder() {
            }
        }
    }
}
