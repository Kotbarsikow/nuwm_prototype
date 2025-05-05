package com.m_myr.nuwm.nuwmschedule.domain.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.SubjectEvaluation;
import com.m_myr.nuwm.nuwmschedule.ui.activities.marks.MyMarksActivity;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class LessonsAdapter {
    private final Context context;
    private int half;
    private LayoutInflater inflater;
    private boolean showProgress;
    private final ArrayList<SubjectEvaluation> subject;

    public LessonsAdapter(Context context, ArrayList<SubjectEvaluation> subject, boolean showProgress, int half) {
        this.context = context;
        this.subject = subject;
        this.showProgress = showProgress;
        this.half = half;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, ViewGroup parent) {
        final SubjectEvaluation subjectEvaluation = this.subject.get(position);
        View inflate = this.inflater.inflate(R.layout.item_subject, parent, false);
        inflate.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.domain.adapter.LessonsAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LessonsAdapter.this.m152x2ca7df3(subjectEvaluation, view);
            }
        });
        TextView textView = (TextView) inflate.findViewById(R.id.str_subject);
        ProgressBar progressBar = (ProgressBar) inflate.findViewById(R.id.progress);
        TextView textView2 = (TextView) inflate.findViewById(R.id.txtProgress);
        textView.setText(subjectEvaluation.getName());
        progressBar.setProgress((int) subjectEvaluation.getTotalMarks());
        textView2.setText(String.valueOf((int) subjectEvaluation.getTotalMarks()));
        if (this.showProgress) {
            progressBar.setProgress((int) subjectEvaluation.getTotalMarks());
        } else {
            progressBar.setProgress(0);
        }
        return inflate;
    }

    /* renamed from: lambda$getView$0$com-m_myr-nuwm-nuwmschedule-domain-adapter-LessonsAdapter, reason: not valid java name */
    /* synthetic */ void m152x2ca7df3(SubjectEvaluation subjectEvaluation, View view) {
        Intent intent = new Intent(this.context, (Class<?>) MyMarksActivity.class);
        intent.putExtra("subjectEvaluation", subjectEvaluation);
        intent.putExtra("half", this.half);
        this.context.startActivity(intent);
    }
}
