package com.m_myr.nuwm.nuwmschedule.ui.activities.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.RepositoryDocInfoProvider;
import com.m_myr.nuwm.nuwmschedule.ui.activities.repository.RepositoryItemActivity;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.util.List;

/* loaded from: classes2.dex */
public class UserPersonDocumentAdapter extends RecyclerView.Adapter<ViewDocHolder> {
    Context context;
    private List<? extends RepositoryDocInfoProvider> documents;
    private FrameLayout.LayoutParams layoutParams;

    public UserPersonDocumentAdapter(Activity context, List<? extends RepositoryDocInfoProvider> documents, boolean fillHorizontal) {
        this.context = context;
        this.documents = documents;
        if (fillHorizontal) {
            this.layoutParams = new FrameLayout.LayoutParams(-1, -2);
        } else {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            this.layoutParams = new FrameLayout.LayoutParams((displayMetrics.widthPixels / 5) * 4, -1);
        }
        this.layoutParams.setMargins(Utils.dpToPx(12), Utils.dpToPx(8), Utils.dpToPx(12), Utils.dpToPx(8));
    }

    public static class ViewDocHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mImageView;
        private TextView mTitle;
        private TextView mType;
        private TextView mYear;

        public ViewDocHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.mImageView = (ImageView) itemView.findViewById(R.id.imageView4);
            this.mTitle = (TextView) itemView.findViewById(R.id.title);
            this.mType = (TextView) itemView.findViewById(R.id.type);
            this.mYear = (TextView) itemView.findViewById(R.id.date_year);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            RepositoryDocInfoProvider repositoryDocInfoProvider = (RepositoryDocInfoProvider) v.getTag();
            Intent intent = new Intent(v.getContext(), (Class<?>) RepositoryItemActivity.class);
            intent.putExtra("eprintid", repositoryDocInfoProvider.getEprintid());
            v.getContext().startActivity(intent);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewDocHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.document_card_item, parent, false);
        inflate.setLayoutParams(this.layoutParams);
        return new ViewDocHolder(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewDocHolder holder, int position) {
        RepositoryDocInfoProvider repositoryDocInfoProvider = this.documents.get(position);
        holder.itemView.setTag(repositoryDocInfoProvider);
        Glide.with(this.context).load(repositoryDocInfoProvider.getImageUrl()).apply((BaseRequestOptions<?>) new RequestOptions().transform(new RoundedCorners(Utils.dpToPx(12)))).into(holder.mImageView);
        holder.mTitle.setText(repositoryDocInfoProvider.getTitle());
        holder.mType.setText(repositoryDocInfoProvider.getDocumentTypeName());
        holder.mYear.setText(String.valueOf(repositoryDocInfoProvider.getDateYear()));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.documents.size();
    }
}
