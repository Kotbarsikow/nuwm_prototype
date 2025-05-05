package com.m_myr.nuwm.nuwmschedule.domain.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.PersonShortInfoProvider;
import com.m_myr.nuwm.nuwmschedule.ui.activities.user.UserProfileActivity;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.util.List;

/* loaded from: classes2.dex */
public class WorkerAdapter extends RecyclerView.Adapter<WorkerHolder> {
    Context context;
    private List<? extends PersonShortInfoProvider> documents;

    public WorkerAdapter(Activity context, List<? extends PersonShortInfoProvider> documents) {
        this.context = context;
        this.documents = documents;
    }

    public static class WorkerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mImageView;
        private TextView mTitle;
        private TextView mType;

        public WorkerHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.mImageView = (ImageView) itemView.findViewById(R.id.image);
            this.mTitle = (TextView) itemView.findViewById(R.id.text);
            this.mType = (TextView) itemView.findViewById(R.id.info);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            UserProfileActivity.startById(v.getContext(), ((PersonShortInfoProvider) v.getTag()).getId());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public WorkerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WorkerHolder(LayoutInflater.from(this.context).inflate(R.layout.worker_item, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(WorkerHolder holder, int position) {
        PersonShortInfoProvider personShortInfoProvider = this.documents.get(position);
        holder.itemView.setTag(personShortInfoProvider);
        Glide.with(this.context).load(personShortInfoProvider.getImageUrl()).apply((BaseRequestOptions<?>) new RequestOptions().transform(new RoundedCorners(Utils.dpToPx(10)))).into(holder.mImageView);
        holder.mTitle.setText(personShortInfoProvider.getFullName());
        holder.mType.setText(personShortInfoProvider.getPost());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.documents.size();
    }
}
