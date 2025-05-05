package com.m_myr.nuwm.nuwmschedule.ui.activities.lastMarks;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.MarkItem;
import com.m_myr.nuwm.nuwmschedule.ui.activities.marks.MyMarksActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/* loaded from: classes2.dex */
public class MarksHistoryAdapter extends RecyclerView.Adapter<ViewHolder> implements View.OnClickListener {
    private Context context;
    ArrayList<MarkItem> mData;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMMM HH:mm");

    public void setData(ArrayList<MarkItem> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        MarkItem markItem = this.mData.get(((Integer) v.getTag()).intValue());
        Intent intent = new Intent(this.context, (Class<?>) MyMarksActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("subject_id", markItem.getId());
        intent.putExtra("subject_name", markItem.getSubjectName());
        intent.putExtra("half", markItem.getHalf());
        intent.putExtra("lesson_id", markItem.getLessonId());
        this.context.startActivity(intent);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView1;
        private final TextView textView15;
        private final TextView textView2;
        private final TextView textView3;

        public ViewHolder(View v) {
            super(v);
            this.textView1 = (TextView) v.findViewById(R.id.text1);
            this.textView15 = (TextView) v.findViewById(R.id.text15);
            this.textView2 = (TextView) v.findViewById(R.id.text2);
            this.textView3 = (TextView) v.findViewById(R.id.mark);
        }
    }

    public MarksHistoryAdapter(Context context, ArrayList<MarkItem> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.last_marks_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        MarkItem markItem = this.mData.get(position);
        viewHolder.textView1.setText(markItem.getSubjectName());
        viewHolder.textView15.setText(markItem.getType());
        viewHolder.textView2.setText(this.simpleDateFormat.format(new Date(markItem.getTime())) + ", " + markItem.getTeacher());
        viewHolder.textView3.setText(markItem.getValue());
        viewHolder.itemView.setTag(Integer.valueOf(position));
        viewHolder.itemView.setOnClickListener(this);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mData.size();
    }
}
