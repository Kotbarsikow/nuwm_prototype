package com.m_myr.nuwm.nuwmschedule.domain.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.search.BaseSearchResult;
import com.m_myr.nuwm.nuwmschedule.data.models.search.DepartmentSearchItem;
import com.m_myr.nuwm.nuwmschedule.data.models.search.EmployerSearchItem;
import com.m_myr.nuwm.nuwmschedule.data.models.search.GroupSearchItem;
import com.m_myr.nuwm.nuwmschedule.data.models.search.RepositorySearchItem;
import com.m_myr.nuwm.nuwmschedule.data.models.search.StudentSearchItem;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.ItemClick;
import com.m_myr.nuwm.nuwmschedule.ui.activities.department.DepartmentProfileActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.groups.GroupsProfileActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.repository.RepositoryItemActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.students.StudentProfileActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.user.UserProfileActivity;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class SearchAdapter extends RecyclerView.Adapter<ViewHolderBase> {
    Context context;
    private ItemClick<BaseSearchResult> itemClickListener;
    ArrayList<BaseSearchResult> results;

    public SearchAdapter(Context context, ItemClick<BaseSearchResult> itemClickListener) {
        this(context, new ArrayList(), itemClickListener);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SearchAdapter(Context context, ArrayList<? extends BaseSearchResult> results, ItemClick<BaseSearchResult> itemClickListener) {
        new ArrayList();
        this.context = context;
        this.itemClickListener = itemClickListener;
        this.results = results;
    }

    public void setData(ArrayList<BaseSearchResult> results) {
        this.results = results;
        notifyDataSetChanged();
    }

    public class ViewHolderBase extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView info;
        TextView text;

        public ViewHolderBase(View itemView) {
            super(itemView);
            this.text = (TextView) itemView.findViewById(R.id.text);
            this.info = (TextView) itemView.findViewById(R.id.info);
            itemView.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            BaseSearchResult baseSearchResult = SearchAdapter.this.results.get(getAdapterPosition());
            if (SearchAdapter.this.itemClickListener != null) {
                SearchAdapter.this.itemClickListener.onClick(getAdapterPosition(), baseSearchResult);
            }
            Utils.sendAnalytic("search_click", new Pair("type", Integer.valueOf(baseSearchResult.type)));
            if (baseSearchResult.getType() == 1) {
                Intent intent = new Intent(v.getContext(), (Class<?>) RepositoryItemActivity.class);
                intent.putExtra("eprintid", ((RepositorySearchItem) baseSearchResult).getEprintid());
                v.getContext().startActivity(intent);
            } else {
                if (baseSearchResult.getType() == 2) {
                    UserProfileActivity.startById(v.getContext(), baseSearchResult.getId());
                    return;
                }
                if (baseSearchResult.getType() == 4) {
                    DepartmentProfileActivity.startById(v.getContext(), baseSearchResult.getId());
                    return;
                }
                if (baseSearchResult.getType() == 5) {
                    GroupsProfileActivity.startById(v.getContext(), baseSearchResult.getId());
                } else if (baseSearchResult.getType() == 3) {
                    StudentProfileActivity.startById(v.getContext(), baseSearchResult.getId());
                } else {
                    Toast.makeText(v.getContext(), "Інформація про місця/книги стане доступною у наступній версії", 1).show();
                }
            }
        }
    }

    public class ViewHolderInfoImage extends ViewHolderBase {
        ImageView image;

        public ViewHolderInfoImage(View itemView) {
            super(itemView);
            this.image = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolderBase onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1 || viewType == 2 || viewType == 4) {
            return new ViewHolderInfoImage(LayoutInflater.from(this.context).inflate(R.layout.multi_search, parent, false));
        }
        return new ViewHolderBase(LayoutInflater.from(this.context).inflate(R.layout.base_search, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolderBase holder, int position) {
        final BaseSearchResult baseSearchResult = this.results.get(position);
        if (baseSearchResult.getType() == 1) {
            RepositorySearchItem repositorySearchItem = (RepositorySearchItem) baseSearchResult;
            holder.text.setText(Html.fromHtml(baseSearchResult.getHighlight()));
            ViewHolderInfoImage viewHolderInfoImage = (ViewHolderInfoImage) holder;
            viewHolderInfoImage.info.setText(repositorySearchItem.getDescription());
            if (repositorySearchItem.getImageUrl() != null && !repositorySearchItem.getImageUrl().isEmpty()) {
                Glide.with(this.context).load(repositorySearchItem.getImageUrl()).error(R.drawable.circle_stroke).apply((BaseRequestOptions<?>) new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(16))).into(viewHolderInfoImage.image);
                viewHolderInfoImage.image.setVisibility(0);
            } else {
                viewHolderInfoImage.image.setVisibility(4);
            }
        } else if (baseSearchResult.getType() == 2) {
            EmployerSearchItem employerSearchItem = (EmployerSearchItem) baseSearchResult;
            holder.text.setText(Html.fromHtml(baseSearchResult.getHighlight()));
            ViewHolderInfoImage viewHolderInfoImage2 = (ViewHolderInfoImage) holder;
            viewHolderInfoImage2.info.setText(employerSearchItem.getDescription());
            Log.e("dfwefw", employerSearchItem.getImageUrl());
            if (employerSearchItem.getImageUrl() != null && !employerSearchItem.getImageUrl().isEmpty()) {
                Glide.with(this.context).load(employerSearchItem.getImageUrl()).error(R.drawable.circle_stroke).apply((BaseRequestOptions<?>) new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(16))).into(viewHolderInfoImage2.image);
                viewHolderInfoImage2.image.setVisibility(0);
            } else {
                viewHolderInfoImage2.image.setVisibility(4);
            }
        } else if (baseSearchResult.getType() == 4) {
            DepartmentSearchItem departmentSearchItem = (DepartmentSearchItem) baseSearchResult;
            holder.text.setText(Html.fromHtml(baseSearchResult.getHighlight()));
            ViewHolderInfoImage viewHolderInfoImage3 = (ViewHolderInfoImage) holder;
            viewHolderInfoImage3.info.setText(departmentSearchItem.getParentName());
            if (departmentSearchItem.getImageUrl() != null && !departmentSearchItem.getImageUrl().isEmpty()) {
                Glide.with(this.context).load(departmentSearchItem.getImageUrl()).error(R.drawable.circle_stroke).into(viewHolderInfoImage3.image);
                viewHolderInfoImage3.image.setVisibility(0);
            } else {
                viewHolderInfoImage3.image.setVisibility(4);
            }
        } else if (baseSearchResult.getType() == 3) {
            holder.text.setText(Html.fromHtml(baseSearchResult.getHighlight()));
            holder.info.setText(((StudentSearchItem) baseSearchResult).getDescription());
        } else if (baseSearchResult.getType() == 5) {
            holder.text.setText(Html.fromHtml(baseSearchResult.getHighlight()));
            holder.info.setText(((GroupSearchItem) baseSearchResult).getDescription());
        } else {
            holder.text.setText(baseSearchResult.getText());
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.domain.adapter.SearchAdapter.1
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View v) {
                Toast.makeText(SearchAdapter.this.context, baseSearchResult.getText(), 0).show();
                return false;
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return this.results.get(position).getType();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.results.size();
    }
}
