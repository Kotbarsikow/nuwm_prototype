package com.m_myr.nuwm.nuwmschedule.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import com.google.android.material.card.MaterialCardView;
import com.google.common.base.Ascii;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.PoolAnswer;
import com.m_myr.nuwm.nuwmschedule.data.models.feed.PollPost;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class PoolsQuestionHolder implements View.OnClickListener, View.OnLongClickListener {
    private byte answerId;
    private MaterialCardView cardView;
    private byte flags;
    private TextView mPercent;
    private ProgressBar mProgressBar;
    private TextView mText;
    private PollListener pollListener;
    private int poolId;
    private View viewGroup;

    public interface PollListener {
        void onCancel(int poolId);

        void onVote(int poolId, byte answerId);
    }

    private PoolsQuestionHolder(View viewGroup) {
        this.viewGroup = viewGroup;
        this.mProgressBar = (ProgressBar) viewGroup.findViewById(R.id.progressBar);
        this.mText = (TextView) viewGroup.findViewById(R.id.text);
        this.mPercent = (TextView) viewGroup.findViewById(R.id.percent);
        this.cardView = (MaterialCardView) viewGroup.findViewById(R.id.card);
    }

    public PoolsQuestionHolder(Context context, int poll_item, LinearLayout mQuestions, PollListener pollListener) {
        this(LayoutInflater.from(context).inflate(poll_item, (ViewGroup) mQuestions, false));
        this.pollListener = pollListener;
    }

    private void attach(PoolAnswer q, int totals, int poolId) {
        this.poolId = poolId;
        this.answerId = q.getAnswerId();
        float countVoters = totals == 0 ? 0.0f : 100.0f * (q.getCountVoters() / totals);
        this.mProgressBar.setProgress((int) countVoters);
        this.mText.setText(q.getTitle());
        this.mPercent.setText(String.format("%s%%", Integer.valueOf(Math.round(countVoters))));
        byte b = this.flags;
        if ((b & 4) == 4) {
            this.viewGroup.setOnClickListener(this);
            this.viewGroup.setClickable(true);
        } else if ((b & 4) != 4 && (b & 8) == 8) {
            this.viewGroup.setLongClickable(true);
            this.viewGroup.setOnLongClickListener(this);
            this.viewGroup.setClickable(false);
        } else {
            this.viewGroup.setLongClickable(false);
            this.viewGroup.setClickable(false);
        }
        if (q.isVoted()) {
            this.flags = (byte) (this.flags | Ascii.SYN);
            int color = this.cardView.getContext().getResources().getColor(R.color.colorPrimary);
            this.cardView.setStrokeColor(color);
            this.mPercent.setTextColor(color);
            this.mText.setTextColor(color);
            this.mPercent.setTypeface(null, 1);
            this.mText.setTypeface(null, 1);
            this.mProgressBar.setProgressDrawable(ContextCompat.getDrawable(this.cardView.getContext(), R.drawable.progress_poll_voted));
            this.viewGroup.setLongClickable(true);
            this.viewGroup.setOnLongClickListener(this);
            this.viewGroup.setClickable(false);
        }
    }

    private View getView() {
        return this.viewGroup;
    }

    public static void create(Context context, LinearLayout mQuestions, PollPost questions, int totals, PollListener pollListener) {
        mQuestions.removeAllViews();
        Iterator<PoolAnswer> it = questions.getAnswers().iterator();
        while (it.hasNext()) {
            PoolAnswer next = it.next();
            PoolsQuestionHolder poolsQuestionHolder = new PoolsQuestionHolder(context, R.layout.poll_item, mQuestions, AppDataManager.getInstance().getUser().getPermission().isVote() ? pollListener : null);
            poolsQuestionHolder.setVotable(!questions.isVoted() || questions.isMultipleChoice(), Utils.timeInterval(questions.getTime(), TimeUnit.HOURS, 2));
            poolsQuestionHolder.attach(next, totals, questions.getPoolId());
            mQuestions.addView(poolsQuestionHolder.getView());
        }
    }

    public void setVotable(boolean votable, boolean cancelable) {
        if (votable) {
            this.flags = (byte) (this.flags | 4);
        }
        if (cancelable) {
            this.flags = (byte) (this.flags | 8);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if ((this.flags & Ascii.SYN) == 22) {
            return;
        }
        PollListener pollListener = this.pollListener;
        if (pollListener != null) {
            pollListener.onVote(this.poolId, this.answerId);
        } else {
            Toast.makeText(App.getInstance(), "Ви не можете голосувати з цього акаунту", 0).show();
        }
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View v) {
        PollListener pollListener = this.pollListener;
        if (pollListener == null) {
            return true;
        }
        pollListener.onCancel(this.poolId);
        return true;
    }
}
