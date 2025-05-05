package com.m_myr.nuwm.nuwmschedule.data.models.feed;

import android.text.Spanned;
import androidx.core.text.HtmlCompat;
import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.data.models.PoolAnswer;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class PollPost extends PostMessage {
    public static final int POST_POLL = 3;

    @SerializedName("anonymously")
    boolean anonymously;

    @SerializedName("answers")
    ArrayList<PoolAnswer> answers;

    @SerializedName("image")
    String attachImage;

    @SerializedName("extend_description")
    String extendDescription;
    private Spanned extendDescriptionSpannable;

    @SerializedName("multiple_choice")
    boolean multipleChoice;

    @SerializedName("poll_id")
    int poolId;

    @SerializedName("votes")
    int totalsVote = 0;

    public static int getPostPoll() {
        return 3;
    }

    public String getAttachImage() {
        return this.attachImage;
    }

    public Spanned getExtendDescription() {
        String str = this.extendDescription;
        if (str == null) {
            return null;
        }
        if (this.extendDescriptionSpannable == null) {
            this.extendDescriptionSpannable = HtmlCompat.fromHtml(str, 257);
        }
        return this.extendDescriptionSpannable;
    }

    public ArrayList<PoolAnswer> getAnswers() {
        return this.answers;
    }

    public boolean isMultipleChoice() {
        return this.multipleChoice;
    }

    public boolean isAnonymously() {
        return this.anonymously;
    }

    public int getPoolId() {
        return this.poolId;
    }

    public int getTotalVote() {
        return this.totalsVote;
    }

    public boolean isVoted() {
        Iterator<PoolAnswer> it = this.answers.iterator();
        while (it.hasNext()) {
            if (it.next().isVoted()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.feed.PostMessage
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
