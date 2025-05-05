package com.m_myr.nuwm.nuwmschedule.domain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.Note;
import com.m_myr.nuwm.nuwmschedule.domain.adapter.NotesRecyclerViewAdapter;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.ItemClick;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.ViewHolderClick;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class NotesRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> implements ViewHolderClick {
    private Context context;
    private ItemClick<Note> itemClick;
    private ArrayList<Note> notes;

    public void addAll(List<Note> notes) {
        this.notes.clear();
        this.notes.addAll(notes);
        notifyDataSetChanged();
    }

    public void update(Note note, int position) {
        if (position == -1) {
            notifyDataSetChanged();
            return;
        }
        this.notes.remove(note);
        this.notes.add(0, note);
        notifyDataSetChanged();
    }

    public void remove(Note note, int position) {
        if (position == -1) {
            notifyDataSetChanged();
            return;
        }
        int indexOf = this.notes.indexOf(note);
        this.notes.remove(indexOf);
        notifyItemRemoved(indexOf);
    }

    public void add(Note note) {
        this.notes.add(0, note);
        notifyItemInserted(0);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView mCardView;
        TextView mNoteDate;
        TextView mNoteText;
        TextView mNoteTextTitle;

        ViewHolder(View v, final ViewHolderClick holderClick) {
            super(v);
            this.mNoteTextTitle = (TextView) v.findViewById(R.id.note_text_title);
            this.mNoteDate = (TextView) v.findViewById(R.id.note_date);
            this.mNoteText = (TextView) v.findViewById(R.id.note_text);
            this.mCardView = (MaterialCardView) v.findViewById(R.id.card_view);
            v.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.domain.adapter.NotesRecyclerViewAdapter$ViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    NotesRecyclerViewAdapter.ViewHolder.this.m153x31c4f5a7(holderClick, view);
                }
            });
        }

        /* renamed from: lambda$new$0$com-m_myr-nuwm-nuwmschedule-domain-adapter-NotesRecyclerViewAdapter$ViewHolder, reason: not valid java name */
        /* synthetic */ void m153x31c4f5a7(ViewHolderClick viewHolderClick, View view) {
            viewHolderClick.onClick(getAdapterPosition());
        }
    }

    public NotesRecyclerViewAdapter(Context context, ArrayList<Note> notes, ItemClick<Note> itemClick) {
        this.notes = notes;
        this.context = context;
        this.itemClick = itemClick;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.note_card, parent, false), this);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note note = this.notes.get(position);
        holder.mNoteTextTitle.setText(note.getTitle());
        holder.mNoteText.setText(note.getBodyHtml());
        holder.mCardView.setCardBackgroundColor(note.getColorNote());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.notes.size();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.ViewHolderClick
    public void onClick(int position) {
        this.itemClick.onClick(position, this.notes.get(position));
    }
}
