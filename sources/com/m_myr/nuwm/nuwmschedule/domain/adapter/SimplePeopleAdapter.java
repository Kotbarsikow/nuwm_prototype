package com.m_myr.nuwm.nuwmschedule.domain.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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
public class SimplePeopleAdapter extends RecyclerView.Adapter<ViewPersonHolder> {
    Context context;
    private List<? extends PersonShortInfoProvider> documents;
    private FrameLayout.LayoutParams layoutParams;

    public SimplePeopleAdapter(Activity context, List<? extends PersonShortInfoProvider> documents, boolean fillHorizontal) {
        this.context = context;
        this.documents = documents;
        if (fillHorizontal) {
            this.layoutParams = new FrameLayout.LayoutParams(-1, -2);
        } else {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            this.layoutParams = new FrameLayout.LayoutParams((displayMetrics.widthPixels / 5) * 4, -1);
        }
        this.layoutParams.setMargins(Utils.dpToPx(12), Utils.dpToPx(8), Utils.dpToPx(6), Utils.dpToPx(8));
    }

    public static class ViewPersonHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mImageView;
        private TextView mTitle;
        private TextView mType;

        public ViewPersonHolder(View itemView) {
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
    public ViewPersonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.short_person_item, parent, false);
        inflate.setLayoutParams(this.layoutParams);
        return new ViewPersonHolder(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewPersonHolder holder, int position) {
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
