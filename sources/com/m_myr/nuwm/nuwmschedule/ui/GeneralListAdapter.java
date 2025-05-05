package com.m_myr.nuwm.nuwmschedule.ui;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.m_myr.nuwm.nuwmschedule.ui.AdapterContract;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes2.dex */
public class GeneralListAdapter extends RecyclerView.Adapter<AdapterContract.ViewHolderGeneral> {
    AdapterContract adapterContract;
    List<Serializable> objects;

    public GeneralListAdapter(AdapterContract adapter, Serializable data) {
        this.adapterContract = adapter;
        this.objects = (List) data;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public AdapterContract.ViewHolderGeneral onCreateViewHolder(ViewGroup parent, int viewType) {
        return this.adapterContract.inflate(parent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(AdapterContract.ViewHolderGeneral holder, int position) {
        this.adapterContract.bindAndAttach(holder, this.objects.get(position));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.objects.size();
    }
}
