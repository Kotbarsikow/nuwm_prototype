package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class HelpdeskChatMainHeadBinding implements ViewBinding {
    public final FrameLayout attachmentsCard;
    public final LinearLayout attachmentsFile;
    public final TextView message;
    private final MaterialCardView rootView;
    public final Chip status;
    public final LinearLayout statusRow;
    public final TextView time;
    public final TextView title;
    public final TextView topic;

    private HelpdeskChatMainHeadBinding(MaterialCardView rootView, FrameLayout attachmentsCard, LinearLayout attachmentsFile, TextView message, Chip status, LinearLayout statusRow, TextView time, TextView title, TextView topic) {
        this.rootView = rootView;
        this.attachmentsCard = attachmentsCard;
        this.attachmentsFile = attachmentsFile;
        this.message = message;
        this.status = status;
        this.statusRow = statusRow;
        this.time = time;
        this.title = title;
        this.topic = topic;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialCardView getRoot() {
        return this.rootView;
    }

    public static HelpdeskChatMainHeadBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static HelpdeskChatMainHeadBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.helpdesk_chat_main_head, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static HelpdeskChatMainHeadBinding bind(View rootView) {
        int i = R.id.attachmentsCard;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.attachmentsCard);
        if (frameLayout != null) {
            i = R.id.attachments_file;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.attachments_file);
            if (linearLayout != null) {
                i = R.id.message;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.message);
                if (textView != null) {
                    i = R.id.status;
                    Chip chip = (Chip) ViewBindings.findChildViewById(rootView, R.id.status);
                    if (chip != null) {
                        i = R.id.status_row;
                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.status_row);
                        if (linearLayout2 != null) {
                            i = R.id.time;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.time);
                            if (textView2 != null) {
                                i = R.id.title;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.title);
                                if (textView3 != null) {
                                    i = R.id.topic;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.topic);
                                    if (textView4 != null) {
                                        return new HelpdeskChatMainHeadBinding((MaterialCardView) rootView, frameLayout, linearLayout, textView, chip, linearLayout2, textView2, textView3, textView4);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
