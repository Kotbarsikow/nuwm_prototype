package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class TicketChatLayoutBinding implements ViewBinding {
    public final ImageView attachFile;
    public final LinearLayout bottomContainer;
    public final BottomSheetHelpdeskTicketBinding bottomSheet;
    public final ImageView cancelLoad;
    public final RelativeLayout fileIco;
    public final EditText message;
    public final CircularProgressIndicator messageSending;
    public final TextView progress;
    public final LinearProgressIndicator progressBar;
    public final CardView progressCard;
    public final RecyclerView recyclerView;
    private final CoordinatorLayout rootView;
    public final ImageView send;
    public final MaterialCardView textInputLayout;

    private TicketChatLayoutBinding(CoordinatorLayout rootView, ImageView attachFile, LinearLayout bottomContainer, BottomSheetHelpdeskTicketBinding bottomSheet, ImageView cancelLoad, RelativeLayout fileIco, EditText message, CircularProgressIndicator messageSending, TextView progress, LinearProgressIndicator progressBar, CardView progressCard, RecyclerView recyclerView, ImageView send, MaterialCardView textInputLayout) {
        this.rootView = rootView;
        this.attachFile = attachFile;
        this.bottomContainer = bottomContainer;
        this.bottomSheet = bottomSheet;
        this.cancelLoad = cancelLoad;
        this.fileIco = fileIco;
        this.message = message;
        this.messageSending = messageSending;
        this.progress = progress;
        this.progressBar = progressBar;
        this.progressCard = progressCard;
        this.recyclerView = recyclerView;
        this.send = send;
        this.textInputLayout = textInputLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static TicketChatLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static TicketChatLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.ticket_chat_layout, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static TicketChatLayoutBinding bind(View rootView) {
        int i = R.id.attachFile;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.attachFile);
        if (imageView != null) {
            i = R.id.bottom_container;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.bottom_container);
            if (linearLayout != null) {
                i = R.id.bottom_sheet;
                View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.bottom_sheet);
                if (findChildViewById != null) {
                    BottomSheetHelpdeskTicketBinding bind = BottomSheetHelpdeskTicketBinding.bind(findChildViewById);
                    i = R.id.cancelLoad;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.cancelLoad);
                    if (imageView2 != null) {
                        i = R.id.file_ico;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.file_ico);
                        if (relativeLayout != null) {
                            i = R.id.message;
                            EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.message);
                            if (editText != null) {
                                i = R.id.messageSending;
                                CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) ViewBindings.findChildViewById(rootView, R.id.messageSending);
                                if (circularProgressIndicator != null) {
                                    i = R.id.progress;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.progress);
                                    if (textView != null) {
                                        i = R.id.progressBar;
                                        LinearProgressIndicator linearProgressIndicator = (LinearProgressIndicator) ViewBindings.findChildViewById(rootView, R.id.progressBar);
                                        if (linearProgressIndicator != null) {
                                            i = R.id.progressCard;
                                            CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, R.id.progressCard);
                                            if (cardView != null) {
                                                i = R.id.recyclerView;
                                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerView);
                                                if (recyclerView != null) {
                                                    i = R.id.send;
                                                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.send);
                                                    if (imageView3 != null) {
                                                        i = R.id.textInputLayout;
                                                        MaterialCardView materialCardView = (MaterialCardView) ViewBindings.findChildViewById(rootView, R.id.textInputLayout);
                                                        if (materialCardView != null) {
                                                            return new TicketChatLayoutBinding((CoordinatorLayout) rootView, imageView, linearLayout, bind, imageView2, relativeLayout, editText, circularProgressIndicator, textView, linearProgressIndicator, cardView, recyclerView, imageView3, materialCardView);
                                                        }
                                                    }
                                                }
                                            }
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
