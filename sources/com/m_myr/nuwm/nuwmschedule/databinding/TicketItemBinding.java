package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.chip.Chip;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class TicketItemBinding implements ViewBinding {
    public final TextView lastMessage;
    public final LinearLayout lastMessageRow;
    public final TextView message;
    private final RelativeLayout rootView;
    public final Chip status;
    public final LinearLayout statusRow;
    public final TextView time;
    public final LinearLayout timeColumn;
    public final TextView title;
    public final TextView topic;

    private TicketItemBinding(RelativeLayout rootView, TextView lastMessage, LinearLayout lastMessageRow, TextView message, Chip status, LinearLayout statusRow, TextView time, LinearLayout timeColumn, TextView title, TextView topic) {
        this.rootView = rootView;
        this.lastMessage = lastMessage;
        this.lastMessageRow = lastMessageRow;
        this.message = message;
        this.status = status;
        this.statusRow = statusRow;
        this.time = time;
        this.timeColumn = timeColumn;
        this.title = title;
        this.topic = topic;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static TicketItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static TicketItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.ticket_item, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static TicketItemBinding bind(View rootView) {
        int i = R.id.lastMessage;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.lastMessage);
        if (textView != null) {
            i = R.id.lastMessage_row;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.lastMessage_row);
            if (linearLayout != null) {
                i = R.id.message;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.message);
                if (textView2 != null) {
                    i = R.id.status;
                    Chip chip = (Chip) ViewBindings.findChildViewById(rootView, R.id.status);
                    if (chip != null) {
                        i = R.id.status_row;
                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.status_row);
                        if (linearLayout2 != null) {
                            i = R.id.time;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.time);
                            if (textView3 != null) {
                                i = R.id.time_column;
                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.time_column);
                                if (linearLayout3 != null) {
                                    i = R.id.title;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.title);
                                    if (textView4 != null) {
                                        i = R.id.topic;
                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.topic);
                                        if (textView5 != null) {
                                            return new TicketItemBinding((RelativeLayout) rootView, textView, linearLayout, textView2, chip, linearLayout2, textView3, linearLayout3, textView4, textView5);
                                        }
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
