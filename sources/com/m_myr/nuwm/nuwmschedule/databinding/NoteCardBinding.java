package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class NoteCardBinding implements ViewBinding {
    public final MaterialCardView cardView;
    public final TextView noteDate;
    public final TextView noteText;
    public final TextView noteTextTitle;
    private final MaterialCardView rootView;

    private NoteCardBinding(MaterialCardView rootView, MaterialCardView cardView, TextView noteDate, TextView noteText, TextView noteTextTitle) {
        this.rootView = rootView;
        this.cardView = cardView;
        this.noteDate = noteDate;
        this.noteText = noteText;
        this.noteTextTitle = noteTextTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialCardView getRoot() {
        return this.rootView;
    }

    public static NoteCardBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static NoteCardBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.note_card, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static NoteCardBinding bind(View rootView) {
        MaterialCardView materialCardView = (MaterialCardView) rootView;
        int i = R.id.note_date;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.note_date);
        if (textView != null) {
            i = R.id.note_text;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.note_text);
            if (textView2 != null) {
                i = R.id.note_text_title;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.note_text_title);
                if (textView3 != null) {
                    return new NoteCardBinding(materialCardView, materialCardView, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
