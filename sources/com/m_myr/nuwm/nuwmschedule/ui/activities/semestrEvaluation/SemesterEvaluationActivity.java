package com.m_myr.nuwm.nuwmschedule.ui.activities.semestrEvaluation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.network.models.SemesterSubjectEvaluation;
import com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

/* loaded from: classes2.dex */
public class SemesterEvaluationActivity extends BaseStateActivity implements SemesterEvaluationView {
    private RecyclerView mRecyclerView;
    private SemesterEvaluationPresenter presenter = new SemesterEvaluationPresenter(this);
    private SubjectRecyclerAdapter subjectRecyclerAdapter;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.mRecyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SubjectRecyclerAdapter subjectRecyclerAdapter = new SubjectRecyclerAdapter(this);
        this.subjectRecyclerAdapter = subjectRecyclerAdapter;
        this.mRecyclerView.setAdapter(subjectRecyclerAdapter);
        setTitle("Семестрові бали");
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.semestrEvaluation.SemesterEvaluationView
    public void setData(ArrayList<SemesterSubjectEvaluation> data) {
        this.subjectRecyclerAdapter.setData(data);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.evaluation_sort, menu);
        return true;
    }

    public void onSort(MenuItem item) {
        Comparator<SemesterSubjectEvaluation> comparator;
        final Collator collator = Collator.getInstance(new Locale("uk", "UA"));
        switch (item.getItemId()) {
            case R.id.item1 /* 2131362298 */:
                comparator = new Comparator() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.semestrEvaluation.SemesterEvaluationActivity$$ExternalSyntheticLambda0
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        int compare;
                        compare = collator.compare(((SemesterSubjectEvaluation) obj).getSubjectName(), ((SemesterSubjectEvaluation) obj2).getSubjectName());
                        return compare;
                    }
                };
                break;
            case R.id.item2 /* 2131362299 */:
                comparator = new Comparator() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.semestrEvaluation.SemesterEvaluationActivity$$ExternalSyntheticLambda1
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        int compareTo;
                        compareTo = ((SemesterSubjectEvaluation) obj2).getLastUpdate().compareTo(((SemesterSubjectEvaluation) obj).getLastUpdate());
                        return compareTo;
                    }
                };
                break;
            case R.id.item3 /* 2131362300 */:
                comparator = new Comparator() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.semestrEvaluation.SemesterEvaluationActivity$$ExternalSyntheticLambda2
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        int compare;
                        compare = Integer.compare(((SemesterSubjectEvaluation) obj2).getTotalHours(), ((SemesterSubjectEvaluation) obj).getTotalHours());
                        return compare;
                    }
                };
                break;
            case R.id.item4 /* 2131362301 */:
                comparator = new Comparator() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.semestrEvaluation.SemesterEvaluationActivity$$ExternalSyntheticLambda3
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        int compare;
                        compare = Integer.compare(((SemesterSubjectEvaluation) obj2).getMark(), ((SemesterSubjectEvaluation) obj).getMark());
                        return compare;
                    }
                };
                break;
            default:
                comparator = null;
                break;
        }
        this.presenter.sortBy(comparator);
    }
}
