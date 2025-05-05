package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class CreateNewTicketActivityBinding implements ViewBinding {
    public final ImageView attach;
    public final MaterialCardView attachmentsCard;
    public final LinearLayout attachmentsFile;
    private final LinearLayout rootView;
    public final ImageView send;
    public final TextInputLayout textField;
    public final ImageView textFormat;
    public final TextInputEditText ticketMessage;
    public final TextInputLayout ticketMessageLayout;
    public final TextInputLayout ticketTheme;
    public final Toolbar toolbar;

    private CreateNewTicketActivityBinding(LinearLayout rootView, ImageView attach, MaterialCardView attachmentsCard, LinearLayout attachmentsFile, ImageView send, TextInputLayout textField, ImageView textFormat, TextInputEditText ticketMessage, TextInputLayout ticketMessageLayout, TextInputLayout ticketTheme, Toolbar toolbar) {
        this.rootView = rootView;
        this.attach = attach;
        this.attachmentsCard = attachmentsCard;
        this.attachmentsFile = attachmentsFile;
        this.send = send;
        this.textField = textField;
        this.textFormat = textFormat;
        this.ticketMessage = ticketMessage;
        this.ticketMessageLayout = ticketMessageLayout;
        this.ticketTheme = ticketTheme;
        this.toolbar = toolbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static CreateNewTicketActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static CreateNewTicketActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.create_new_ticket_activity, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static CreateNewTicketActivityBinding bind(View rootView) {
        int i = R.id.attach;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.attach);
        if (imageView != null) {
            i = R.id.attachmentsCard;
            MaterialCardView materialCardView = (MaterialCardView) ViewBindings.findChildViewById(rootView, R.id.attachmentsCard);
            if (materialCardView != null) {
                i = R.id.attachments_file;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.attachments_file);
                if (linearLayout != null) {
                    i = R.id.send;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.send);
                    if (imageView2 != null) {
                        i = R.id.textField;
                        TextInputLayout textInputLayout = (TextInputLayout) ViewBindings.findChildViewById(rootView, R.id.textField);
                        if (textInputLayout != null) {
                            i = R.id.textFormat;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.textFormat);
                            if (imageView3 != null) {
                                i = R.id.ticketMessage;
                                TextInputEditText textInputEditText = (TextInputEditText) ViewBindings.findChildViewById(rootView, R.id.ticketMessage);
                                if (textInputEditText != null) {
                                    i = R.id.ticketMessageLayout;
                                    TextInputLayout textInputLayout2 = (TextInputLayout) ViewBindings.findChildViewById(rootView, R.id.ticketMessageLayout);
                                    if (textInputLayout2 != null) {
                                        i = R.id.ticketTheme;
                                        TextInputLayout textInputLayout3 = (TextInputLayout) ViewBindings.findChildViewById(rootView, R.id.ticketTheme);
                                        if (textInputLayout3 != null) {
                                            i = R.id.toolbar;
                                            Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(rootView, R.id.toolbar);
                                            if (toolbar != null) {
                                                return new CreateNewTicketActivityBinding((LinearLayout) rootView, imageView, materialCardView, linearLayout, imageView2, textInputLayout, imageView3, textInputEditText, textInputLayout2, textInputLayout3, toolbar);
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
