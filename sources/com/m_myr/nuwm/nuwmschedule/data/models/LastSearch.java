package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.data.models.search.BaseSearchResult;
import com.m_myr.nuwm.nuwmschedule.data.models.search.EmployerSearchItem;
import com.m_myr.nuwm.nuwmschedule.data.models.search.RepositorySearchItem;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.StorageModel;
import com.m_myr.nuwm.nuwmschedule.network.StorageModelName;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@StorageModelName("last_search")
/* loaded from: classes2.dex */
public class LastSearch implements StorageModel {

    @SerializedName("employer")
    private TreeMap<Long, EmployerSearchItem> employerItems = new TreeMap<>();

    @SerializedName("repository")
    private TreeMap<Long, RepositorySearchItem> repositoryItems = new TreeMap<>();

    public List<RepositorySearchItem> getRepositoryItems() {
        return new ArrayList(this.repositoryItems.values());
    }

    public ArrayList<EmployerSearchItem> getEmployerItems() {
        return new ArrayList<>(this.employerItems.values());
    }

    private void addEmployerItem(EmployerSearchItem item) {
        addToCollection(item, this.employerItems);
    }

    private <T> void addToCollection(T item, TreeMap<Long, T> collection) {
        if (!collection.containsValue(item)) {
            collection.put(Long.valueOf(System.currentTimeMillis()), item);
            if (collection.size() > 10) {
                collection.remove(collection.firstKey());
                return;
            }
            return;
        }
        if (collection != null && !collection.isEmpty()) {
            for (Map.Entry<Long, T> entry : collection.entrySet()) {
                if (entry.getValue().equals(item)) {
                    collection.remove(entry.getKey());
                }
            }
        }
        collection.put(Long.valueOf(System.currentTimeMillis()), item);
    }

    void addRepositoryItem(RepositorySearchItem item) {
        item.generatePreviewImage();
        addToCollection(item, this.repositoryItems);
    }

    public void addSearch(BaseSearchResult object) {
        if (object.getType() == 1) {
            addRepositoryItem((RepositorySearchItem) object);
        } else if (object.getType() == 2) {
            addEmployerItem((EmployerSearchItem) object);
        }
    }
}
