package com.m_myr.nuwm.nuwmschedule.ui.activities.semestrEvaluation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.network.models.SemesterSubjectEvaluation;
import com.m_myr.nuwm.nuwmschedule.ui.activities.semestrEvaluation.details.SemestrEvaluationDetails;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class SubjectRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    ArrayList<SemesterSubjectEvaluation> mData = new ArrayList<>();

    public void setData(ArrayList<SemesterSubjectEvaluation> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView1;
        private final TextView textView2;
        private final TextView textView3;

        public ViewHolder(View v) {
            super(v);
            this.textView1 = (TextView) v.findViewById(R.id.text1);
            this.textView2 = (TextView) v.findViewById(R.id.text2);
            this.textView3 = (TextView) v.findViewById(R.id.mark);
        }
    }

    public SubjectRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.semester_mark_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final SemesterSubjectEvaluation semesterSubjectEvaluation = this.mData.get(position);
        viewHolder.textView1.setText(semesterSubjectEvaluation.getSubjectName());
        viewHolder.textView2.setText(Utils.StringUtils.unitsFormat(semesterSubjectEvaluation.getSemesters().size(), "семестр", "семестри", "семестрів") + ", " + Utils.StringUtils.unitsFormat(semesterSubjectEvaluation.getTotalHours(), "година", "години", "годин"));
        viewHolder.textView3.setText(String.valueOf(semesterSubjectEvaluation.getMark()));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.semestrEvaluation.SubjectRecyclerAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intent intent = new Intent(SubjectRecyclerAdapter.this.context, (Class<?>) SemestrEvaluationDetails.class);
                intent.putExtra("subject", semesterSubjectEvaluation);
                SubjectRecyclerAdapter.this.context.startActivity(intent);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mData.size();
    }
}
