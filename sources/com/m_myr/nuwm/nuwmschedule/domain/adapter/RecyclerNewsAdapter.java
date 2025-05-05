package com.m_myr.nuwm.nuwmschedule.domain.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.bumptech.glide.Glide;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.NewsViewItem;
import com.m_myr.nuwm.nuwmschedule.domain.adapter.RecyclerNewsAdapter;
import com.m_myr.nuwm.nuwmschedule.ui.activities.news.NewsViewInstant;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RecyclerNewsAdapter.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 %2\u000e\u0012\n\u0012\b\u0018\u00010\u0002R\u00020\u00000\u0001:\u0004%&'(B)\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u001a\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\b¢\u0006\u0002\u0010\tJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0007J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0007J\"\u0010\u0018\u001a\u00020\u00152\u001a\u0010\u0019\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\bJ\u0006\u0010\u001a\u001a\u00020\u0015J\b\u0010\u001b\u001a\u00020\u000bH\u0016J\u0010\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u000bH\u0016J\u001a\u0010\u001e\u001a\u00020\u00152\n\u0010\u001f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0007J\u001c\u0010\u001e\u001a\u00020\u00152\n\u0010\u001f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u000bH\u0016J\u001c\u0010 \u001a\u00060\u0002R\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u000bH\u0016J\u000e\u0010$\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u000bR\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u0013\u0010\f\u001a\u0004\u0018\u00010\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\"\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/domain/adapter/RecyclerNewsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/m_myr/nuwm/nuwmschedule/domain/adapter/RecyclerNewsAdapter$ViewHolder;", "context", "Landroid/content/Context;", "mDataSet", "Ljava/util/ArrayList;", "Lcom/m_myr/nuwm/nuwmschedule/data/models/NewsViewItem;", "Lkotlin/collections/ArrayList;", "(Landroid/content/Context;Ljava/util/ArrayList;)V", "TYPE_LOADING", "", "all", "Ljava/io/Serializable;", "getAll", "()Ljava/io/Serializable;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "add", "", "newsViewItem", "i", "addAll", "articles", "clear", "getItemCount", "getItemViewType", "position", "onBindViewHolder", "viewHolder", "onCreateViewHolder", "viewGroup", "Landroid/view/ViewGroup;", "viewType", "remove", "Companion", "ProgressViewHolder", "ViewHolder", "ViewHolderImage", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RecyclerNewsAdapter extends RecyclerView.Adapter<ViewHolder> {
    public static final int TYPE_WHITH_IMAGE = 0;
    private final int TYPE_LOADING;
    private Context context;
    private final ArrayList<NewsViewItem> mDataSet;

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.context = context;
    }

    public RecyclerNewsAdapter(Context context, ArrayList<NewsViewItem> arrayList) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.mDataSet = arrayList;
        this.TYPE_LOADING = 1;
    }

    public final void addAll(ArrayList<NewsViewItem> articles) {
        ArrayList<NewsViewItem> arrayList = this.mDataSet;
        Intrinsics.checkNotNull(arrayList);
        Intrinsics.checkNotNull(articles);
        arrayList.addAll(articles);
        notifyDataSetChanged();
    }

    public final void add(NewsViewItem newsViewItem) {
        Intrinsics.checkNotNullParameter(newsViewItem, "newsViewItem");
        ArrayList<NewsViewItem> arrayList = this.mDataSet;
        Intrinsics.checkNotNull(arrayList);
        arrayList.add(newsViewItem);
    }

    public final void add(int i, NewsViewItem newsViewItem) {
        Intrinsics.checkNotNullParameter(newsViewItem, "newsViewItem");
        ArrayList<NewsViewItem> arrayList = this.mDataSet;
        Intrinsics.checkNotNull(arrayList);
        arrayList.add(i, newsViewItem);
    }

    public final void remove(int i) {
        if (i >= 0) {
            ArrayList<NewsViewItem> arrayList = this.mDataSet;
            Intrinsics.checkNotNull(arrayList);
            if (arrayList.size() > 0) {
                this.mDataSet.remove(i);
            }
        }
    }

    public final void clear() {
        ArrayList<NewsViewItem> arrayList = this.mDataSet;
        if (arrayList != null) {
            arrayList.clear();
        }
    }

    public final Serializable getAll() {
        return this.mDataSet;
    }

    /* compiled from: RecyclerNewsAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0096\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/domain/adapter/RecyclerNewsAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "v", "Landroid/view/View;", "(Lcom/m_myr/nuwm/nuwmschedule/domain/adapter/RecyclerNewsAdapter;Landroid/view/View;)V", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public class ViewHolder extends RecyclerView.ViewHolder {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(View view) {
            super(view);
            Intrinsics.checkNotNull(view);
        }
    }

    /* compiled from: RecyclerNewsAdapter.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\t\"\u0004\b\u0017\u0010\u000b¨\u0006\u0018"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/domain/adapter/RecyclerNewsAdapter$ViewHolderImage;", "Lcom/m_myr/nuwm/nuwmschedule/domain/adapter/RecyclerNewsAdapter$ViewHolder;", "Lcom/m_myr/nuwm/nuwmschedule/domain/adapter/RecyclerNewsAdapter;", "itemView", "Landroid/view/View;", "(Lcom/m_myr/nuwm/nuwmschedule/domain/adapter/RecyclerNewsAdapter;Landroid/view/View;)V", "date", "Landroid/widget/TextView;", "getDate", "()Landroid/widget/TextView;", "setDate", "(Landroid/widget/TextView;)V", "description", "getDescription", "setDescription", "image", "Landroid/widget/ImageView;", "getImage", "()Landroid/widget/ImageView;", "setImage", "(Landroid/widget/ImageView;)V", "title", "getTitle", "setTitle", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class ViewHolderImage extends ViewHolder {
        private TextView date;
        private TextView description;
        private ImageView image;
        final /* synthetic */ RecyclerNewsAdapter this$0;
        private TextView title;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolderImage(RecyclerNewsAdapter recyclerNewsAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = recyclerNewsAdapter;
            View findViewById = itemView.findViewById(R.id.card_img_title);
            Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
            this.title = (TextView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.card_img_thumbnail);
            Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.ImageView");
            this.image = (ImageView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.card_img_date);
            Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.TextView");
            this.date = (TextView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.card_img_description);
            Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.TextView");
            this.description = (TextView) findViewById4;
            itemView.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.domain.adapter.RecyclerNewsAdapter$ViewHolderImage$$ExternalSyntheticLambda0
                public final /* synthetic */ RecyclerNewsAdapter f$1;

                public /* synthetic */ RecyclerNewsAdapter$ViewHolderImage$$ExternalSyntheticLambda0(RecyclerNewsAdapter recyclerNewsAdapter2) {
                    r2 = recyclerNewsAdapter2;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RecyclerNewsAdapter.ViewHolderImage._init_$lambda$0(RecyclerNewsAdapter.ViewHolderImage.this, r2, view);
                }
            });
        }

        public final TextView getTitle() {
            return this.title;
        }

        public final void setTitle(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.title = textView;
        }

        public final TextView getDate() {
            return this.date;
        }

        public final void setDate(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.date = textView;
        }

        public final TextView getDescription() {
            return this.description;
        }

        public final void setDescription(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.description = textView;
        }

        public final ImageView getImage() {
            return this.image;
        }

        public final void setImage(ImageView imageView) {
            Intrinsics.checkNotNullParameter(imageView, "<set-?>");
            this.image = imageView;
        }

        public static final void _init_$lambda$0(ViewHolderImage this$0, RecyclerNewsAdapter this$1, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            int max = Math.max(0, this$0.getAdapterPosition());
            Intent intent = new Intent(view.getContext(), (Class<?>) NewsViewInstant.class);
            ArrayList arrayList = this$1.mDataSet;
            Intrinsics.checkNotNull(arrayList);
            intent.putExtra("news", (Serializable) arrayList.get(max));
            Context context = view.getContext();
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
            ActivityOptionsCompat makeSceneTransitionAnimation = ActivityOptionsCompat.makeSceneTransitionAnimation((AppCompatActivity) context, this$0.image, "movie_thumbnail_transition");
            Intrinsics.checkNotNullExpressionValue(makeSceneTransitionAnimation, "makeSceneTransitionAnimation(...)");
            ActivityCompat.startActivity(view.getContext(), intent, makeSceneTransitionAnimation.toBundle());
        }
    }

    /* compiled from: RecyclerNewsAdapter.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0080\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/domain/adapter/RecyclerNewsAdapter$ProgressViewHolder;", "Lcom/m_myr/nuwm/nuwmschedule/domain/adapter/RecyclerNewsAdapter$ViewHolder;", "Lcom/m_myr/nuwm/nuwmschedule/domain/adapter/RecyclerNewsAdapter;", "v", "Landroid/view/View;", "(Lcom/m_myr/nuwm/nuwmschedule/domain/adapter/RecyclerNewsAdapter;Landroid/view/View;)V", "progressBar", "Landroid/widget/ProgressBar;", "getProgressBar", "()Landroid/widget/ProgressBar;", "setProgressBar", "(Landroid/widget/ProgressBar;)V", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class ProgressViewHolder extends ViewHolder {
        private ProgressBar progressBar;
        final /* synthetic */ RecyclerNewsAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ProgressViewHolder(RecyclerNewsAdapter recyclerNewsAdapter, View v) {
            super(v);
            Intrinsics.checkNotNullParameter(v, "v");
            this.this$0 = recyclerNewsAdapter;
            View findViewById = v.findViewById(R.id.progressBar1);
            Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.ProgressBar");
            this.progressBar = (ProgressBar) findViewById;
        }

        public final ProgressBar getProgressBar() {
            return this.progressBar;
        }

        public final void setProgressBar(ProgressBar progressBar) {
            Intrinsics.checkNotNullParameter(progressBar, "<set-?>");
            this.progressBar = progressBar;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        if (viewType == 0) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_image, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
            return new ViewHolderImage(this, inflate);
        }
        View inflate2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.progressbar_item, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate2, "inflate(...)");
        return new ProgressViewHolder(this, inflate2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        if (viewHolder.getItemViewType() == 0) {
            ArrayList<NewsViewItem> arrayList = this.mDataSet;
            Intrinsics.checkNotNull(arrayList);
            NewsViewItem newsViewItem = arrayList.get(position);
            Intrinsics.checkNotNullExpressionValue(newsViewItem, "get(...)");
            onBindViewHolder(viewHolder, newsViewItem);
            return;
        }
        ViewGroup.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.recyclerview.widget.StaggeredGridLayoutManager.LayoutParams");
        StaggeredGridLayoutManager.LayoutParams layoutParams2 = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
        layoutParams2.setFullSpan(true);
        viewHolder.itemView.setLayoutParams(layoutParams2);
        ProgressViewHolder progressViewHolder = (ProgressViewHolder) viewHolder;
        progressViewHolder.itemView.findViewById(R.id.progressError).setVisibility(4);
        progressViewHolder.getProgressBar().setVisibility(0);
        progressViewHolder.getProgressBar().setIndeterminate(true);
    }

    public final void onBindViewHolder(ViewHolder viewHolder, NewsViewItem newsViewItem) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        Intrinsics.checkNotNullParameter(newsViewItem, "newsViewItem");
        ViewHolderImage viewHolderImage = (ViewHolderImage) viewHolder;
        viewHolderImage.getTitle().setText(newsViewItem.getTitle());
        viewHolderImage.getDate().setText(newsViewItem.getDate());
        viewHolderImage.getDescription().setText(newsViewItem.getDesc());
        Glide.with(this.context).load(newsViewItem.getImage()).into(viewHolderImage.getImage());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<NewsViewItem> arrayList = this.mDataSet;
        Intrinsics.checkNotNull(arrayList);
        return arrayList.size() + 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        ArrayList<NewsViewItem> arrayList = this.mDataSet;
        Intrinsics.checkNotNull(arrayList);
        if (position == arrayList.size()) {
            return this.TYPE_LOADING;
        }
        return 0;
    }
}
