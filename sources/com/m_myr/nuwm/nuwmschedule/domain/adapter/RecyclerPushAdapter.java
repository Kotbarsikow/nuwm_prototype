package com.m_myr.nuwm.nuwmschedule.domain.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.NewsViewItem;
import com.m_myr.nuwm.nuwmschedule.data.models.feed.ImagePost;
import com.m_myr.nuwm.nuwmschedule.data.models.feed.NewsPost;
import com.m_myr.nuwm.nuwmschedule.data.models.feed.PollPost;
import com.m_myr.nuwm.nuwmschedule.data.models.feed.PostInteractive;
import com.m_myr.nuwm.nuwmschedule.data.models.feed.PostMessage;
import com.m_myr.nuwm.nuwmschedule.data.models.feed.TextPost;
import com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository;
import com.m_myr.nuwm.nuwmschedule.domain.Analitics;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback;
import com.m_myr.nuwm.nuwmschedule.ui.activities.news.NewsViewInstant;
import com.m_myr.nuwm.nuwmschedule.ui.view.PoolsQuestionHolder;
import com.m_myr.nuwm.nuwmschedule.ui.view.ProfileCardInflater;
import com.m_myr.nuwm.nuwmschedule.utils.LinksResolver;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class RecyclerPushAdapter extends RecyclerView.Adapter<ViewHolder> implements PoolsQuestionHolder.PollListener, RequestObjectCallback<PollPost> {
    private static final int PUSH_HISTORY = 128;
    private final Context context;
    private RecyclerView mRecyclerView;
    private ArrayList<PostMessage> postMessages = new ArrayList<>();

    private boolean isDataPosition(int adapterPosition) {
        return adapterPosition > 0;
    }

    private void onBindViewHolderHistory(ViewHolderHistory holder) {
    }

    public void setData(ArrayList<PostMessage> data) {
        this.postMessages.clear();
        this.postMessages.addAll(data);
        notifyDataSetChanged();
    }

    public void updateItem(int position, PostMessage data) {
        if (position < this.postMessages.size()) {
            this.postMessages.set(position, data);
            notifyItemChanged(position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    public static class ViewHolderHistory extends ViewHolder {
        public ViewHolderHistory(View v) {
            super(v);
        }
    }

    public class ViewHolderNews extends ViewHolder implements View.OnClickListener {
        public TextView date;
        public TextView description;
        public ImageView image;
        public TextView title;

        public ViewHolderNews(View v) {
            super(v);
            this.title = (TextView) v.findViewById(R.id.card_img_title);
            this.image = (ImageView) v.findViewById(R.id.card_img_thumbnail);
            this.date = (TextView) v.findViewById(R.id.card_img_date);
            this.description = (TextView) v.findViewById(R.id.card_img_description);
            v.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), (Class<?>) NewsViewInstant.class);
            intent.putExtra("news", ((NewsPost) RecyclerPushAdapter.this.getObject(getAdapterPosition())).getNews());
            ActivityCompat.startActivity(v.getContext(), intent, ActivityOptionsCompat.makeSceneTransitionAnimation((AppCompatActivity) v.getContext(), this.image, "movie_thumbnail_transition").toBundle());
        }
    }

    public static class ViewHolderEmpty extends ViewHolder {
        public TextView description;
        public TextView title;

        public ViewHolderEmpty(View v) {
            super(v);
            this.title = (TextView) v.findViewById(R.id.card_img_title);
            this.description = (TextView) v.findViewById(R.id.card_img_description);
        }
    }

    public class ViewHolderImage extends ViewHolder implements View.OnClickListener {
        public ImageView mImage;
        public AppCompatTextView mSubtitle;
        public TextView mTitle;
        public RelativeLayout placeholder;

        public ViewHolderImage(View v) {
            super(v);
            this.mImage = (ImageView) v.findViewById(R.id.image);
            this.mTitle = (TextView) v.findViewById(R.id.title);
            this.mSubtitle = (AppCompatTextView) v.findViewById(R.id.subtitle);
            this.placeholder = (RelativeLayout) v.findViewById(R.id.placeholder);
            v.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            LinksResolver.openInAppOrChrome(v.getContext(), ((ImagePost) RecyclerPushAdapter.this.getObject(getAdapterPosition())).getLink());
        }
    }

    public static class ViewHolderTextPost extends ViewHolder implements View.OnLongClickListener {
        public AppCompatTextView mBody;
        public AppCompatTextView mSubtitle;
        public TextView mTitle;
        float x;
        float y;

        public ViewHolderTextPost(View v) {
            super(v);
            this.x = 0.0f;
            this.y = 0.0f;
            this.mTitle = (TextView) v.findViewById(R.id.title);
            this.mSubtitle = (AppCompatTextView) v.findViewById(R.id.subtitle);
            this.mBody = (AppCompatTextView) v.findViewById(R.id.body);
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View v) {
            new PopupWindow(LayoutInflater.from(v.getContext()).inflate(R.layout.context_menu, (ViewGroup) null), -2, -2).showAtLocation(v, 17, 0, 0);
            return true;
        }
    }

    public static class ViewHolderPoll extends ViewHolder {
        private ImageView mImage;
        private View mMultiple;
        private LinearLayout mQuestions;
        private TextView mSubtitle;
        public TextView mTitle;
        private TextView mVotes;

        public ViewHolderPoll(View v) {
            super(v);
            this.mImage = (ImageView) v.findViewById(R.id.image);
            this.mTitle = (TextView) v.findViewById(R.id.title);
            this.mSubtitle = (TextView) v.findViewById(R.id.subtitle);
            this.mQuestions = (LinearLayout) v.findViewById(R.id.questions);
            this.mVotes = (TextView) v.findViewById(R.id.votes);
            this.mMultiple = v.findViewById(R.id.multiply);
        }
    }

    public RecyclerPushAdapter(Context context, RecyclerView mRecyclerView) {
        this.context = context;
        this.mRecyclerView = mRecyclerView;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 128) {
            return new ViewHolderHistory(LayoutInflater.from(this.context).inflate(R.layout.item_push_history_card, parent, false));
        }
        if (viewType == 1) {
            return new ViewHolderNews(LayoutInflater.from(this.context).inflate(R.layout.item_push_news_card, parent, false));
        }
        if (viewType == 3) {
            return new ViewHolderPoll(LayoutInflater.from(this.context).inflate(R.layout.item_push_pool, parent, false));
        }
        if (viewType == 4) {
            return new ViewHolderImage(LayoutInflater.from(this.context).inflate(R.layout.item_push_image, parent, false));
        }
        if (viewType == 2) {
            return new ViewHolderTextPost(LayoutInflater.from(this.context).inflate(R.layout.item_push_text, parent, false));
        }
        if (viewType == 5) {
            return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_push_empty_card, parent, false));
        }
        return new ViewHolderEmpty(LayoutInflater.from(this.context).inflate(R.layout.item_push_empty, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getItemViewType(position) == 128) {
            onBindViewHolderHistory((ViewHolderHistory) holder);
            return;
        }
        if (getItemViewType(position) == 1) {
            onBindViewHolderNews((ViewHolderNews) holder, (NewsPost) getObject(position));
            return;
        }
        if (getItemViewType(position) == 3) {
            onBindViewHolderPoll((ViewHolderPoll) holder, (PollPost) getObject(position));
            return;
        }
        if (getItemViewType(position) == 4) {
            onBindViewHolderImage((ViewHolderImage) holder, (ImagePost) getObject(position));
            return;
        }
        if (getItemViewType(position) == 2) {
            onBindViewHolderText((ViewHolderTextPost) holder, (TextPost) getObject(position));
        } else if (getItemViewType(position) == 5) {
            onBindViewHolderPostInteractive(holder, (PostInteractive) getObject(position));
        } else {
            onBindViewHolderEmpty((ViewHolderEmpty) holder, getObject(position));
        }
    }

    private void onBindViewHolderPostInteractive(ViewHolder holder, PostInteractive object) {
        ViewGroup viewGroup = (ViewGroup) holder.itemView.findViewById(R.id.container);
        if (viewGroup.getChildCount() == 0) {
            ProfileCardInflater.from(this.context, viewGroup).inflate(object.getCard());
        }
    }

    private void onBindViewHolderText(ViewHolderTextPost holder, TextPost object) {
        holder.mTitle.setText(object.getTitle());
        if (object.getSubtitle() == null || object.getSubtitle().isEmpty()) {
            holder.mSubtitle.setVisibility(8);
        } else {
            holder.mSubtitle.setText(object.getSubtitle());
            holder.mSubtitle.setVisibility(0);
        }
        holder.mBody.setText(object.getMessage(this.context));
    }

    private void onBindViewHolderImage(ViewHolderImage holder, ImagePost push) {
        if (push.isConstraint()) {
            holder.itemView.measure(View.MeasureSpec.makeMeasureSpec(this.mRecyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
            holder.itemView.getLayoutParams().height = (int) (holder.itemView.getMeasuredWidth() * (push.getSize().y / push.getSize().x));
        } else {
            holder.placeholder.getLayoutParams().height = Utils.dpToPx(160);
        }
        holder.mTitle.setText(push.getTitle());
        holder.mTitle.setTextAlignment(push.getTextTitleGravity());
        holder.mSubtitle.setGravity(push.getTextSubtitleGravity());
        holder.mSubtitle.setText(push.getSubtitle());
        if (push.isShadowTextDark()) {
            holder.mTitle.setShadowLayer(12.0f, 2.0f, 2.0f, ViewCompat.MEASURED_STATE_MASK);
            holder.mSubtitle.setShadowLayer(12.0f, 2.0f, 2.0f, ViewCompat.MEASURED_STATE_MASK);
        }
        if (push.isShadowTextLight()) {
            holder.mTitle.setShadowLayer(12.0f, 2.0f, 2.0f, 1157627903);
            holder.mSubtitle.setShadowLayer(12.0f, 2.0f, 2.0f, 1157627903);
        }
        if (push.isExpandSubtitle()) {
            TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(holder.mSubtitle, 14, 90, 1, 2);
        }
        if (push.isItalicSubtitle()) {
            holder.mSubtitle.setTypeface(holder.mSubtitle.getTypeface(), 2);
        }
        if (push.isItalicTitle()) {
            holder.mTitle.setTypeface(holder.mTitle.getTypeface(), 2);
        }
        if (push.isTitleGiant()) {
            holder.mTitle.setTextSize(2, 26.0f);
        } else if (push.isTitleLarge()) {
            holder.mTitle.setTextSize(2, 21.0f);
        } else if (push.isTitleMedium()) {
            holder.mTitle.setTextSize(2, 16.0f);
        } else if (push.isTitleSmall()) {
            holder.mTitle.setTextSize(2, 14.0f);
        }
        if (push.isSubtitleLarge()) {
            holder.mSubtitle.setTextSize(2, 42.0f);
        } else if (push.isSubtitleMedium()) {
            holder.mSubtitle.setTextSize(2, 16.0f);
        } else if (push.isSubtitleSmall()) {
            holder.mSubtitle.setTextSize(2, 13.0f);
        }
        if (push.isSubtitleAutoSize()) {
            TextViewCompat.setAutoSizeTextTypeWithDefaults(holder.mSubtitle, 0);
        }
        Glide.with(this.context).load(push.getImage()).into(holder.mImage);
    }

    private void onBindViewHolderPoll(ViewHolderPoll holder, PollPost pollPush) {
        holder.mTitle.setText(pollPush.getTitle());
        holder.mSubtitle.setText(pollPush.getExtendDescription());
        holder.mVotes.setText(Utils.StringUtils.unitsFormat(pollPush.getTotalVote(), "голос", "голоси", "голосів"));
        holder.mTitle.setGravity(4);
        holder.mMultiple.setVisibility(pollPush.isMultipleChoice() ? 0 : 4);
        PoolsQuestionHolder.create(this.context, holder.mQuestions, pollPush, pollPush.getTotalVote(), this);
        if (pollPush.getAttachImage() == null) {
            holder.mImage.setVisibility(8);
        } else if ("@internal/preset_1".equals(pollPush.getAttachImage())) {
            holder.mImage.setImageResource(R.drawable.test2);
            holder.mImage.setVisibility(0);
        } else {
            Glide.with(this.context).load(pollPush.getAttachImage()).into(holder.mImage);
            holder.mImage.setVisibility(0);
        }
    }

    public PostMessage getObject(int position) {
        return this.postMessages.get(position - 1);
    }

    private void onBindViewHolderEmpty(ViewHolderEmpty holder, PostMessage emptyPush) {
        holder.title.setText(emptyPush.getTitle());
        holder.description.setText(emptyPush.getSubtitle());
    }

    private void onBindViewHolderNews(ViewHolderNews holder, NewsPost message) {
        NewsViewItem news = message.getNews();
        holder.title.setText(news.getTitle());
        holder.date.setText(news.getDate());
        holder.description.setText(news.getDesc());
        Glide.with(this.context).load(news.getImage()).into(holder.image);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        if (position == 0) {
            return 128;
        }
        return this.postMessages.get(position - 1).getType();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.postMessages.size() + 1;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.view.PoolsQuestionHolder.PollListener
    public void onCancel(int poolId) {
        FastRepository.call(APIMethods.cancelVote(poolId)).into(this).start();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.view.PoolsQuestionHolder.PollListener
    public void onVote(int poolId, byte answerId) {
        FastRepository.call(APIMethods.vote(poolId, answerId)).into(this).start();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
    public void onError(ErrorResponse response) {
        Toast.makeText(this.context, response.getMessage(), 0).show();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
    public void onSuccessData(PollPost data) {
        int indexOf = this.postMessages.indexOf(data);
        if (indexOf != -1) {
            this.postMessages.set(indexOf, data);
            notifyItemChanged(indexOf + 1);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow((RecyclerPushAdapter) holder);
        if (isDataPosition(holder.getAdapterPosition())) {
            PostMessage object = getObject(holder.getAdapterPosition());
            if (!object.isViewed()) {
                Analitics.markPostRead(object.getUid());
                object.setViewed();
                holder.itemView.startAnimation(AnimationUtils.loadAnimation(this.context, R.anim.blinking_animation));
            }
            if (object.isHighlight()) {
                object.clearHighlight();
                holder.itemView.startAnimation(AnimationUtils.loadAnimation(this.context, R.anim.blinking_animation_color));
            }
        }
    }
}
