package com.m_myr.nuwm.nuwmschedule.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.io.Serializable;

/* loaded from: classes2.dex */
public abstract class AdapterContract<T extends Serializable> implements Serializable, View.OnClickListener {
    protected abstract void bind(ViewHolderGeneral holder, T o);

    protected abstract ViewHolderGeneral inflate(ViewGroup parent);

    protected void onClick(Context context, View v, T o) {
    }

    @Deprecated
    protected abstract void onClick(Context context, T o);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View.OnClickListener
    public final void onClick(View v) {
        onClick(v.getContext(), v, (Serializable) v.getTag());
        onClick(v.getContext(), (Serializable) v.getTag());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static class ViewHolderGeneral extends RecyclerView.ViewHolder {
        public ViewHolderGeneral(View itemView) {
            super(itemView);
        }
    }

    public void bindAndAttach(ViewHolderGeneral holder, T o) {
        bind(holder, o);
        holder.itemView.setTag(o);
        holder.itemView.setOnClickListener(this);
    }
}
