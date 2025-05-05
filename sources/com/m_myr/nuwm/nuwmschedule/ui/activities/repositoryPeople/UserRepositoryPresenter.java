package com.m_myr.nuwm.nuwmschedule.ui.activities.repositoryPeople;

import android.content.Intent;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.RepositoryItem;
import com.m_myr.nuwm.nuwmschedule.domain.RepositoryPresenter;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener;
import com.m_myr.nuwm.nuwmschedule.network.models.RepositoryPage;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes2.dex */
public class UserRepositoryPresenter extends RepositoryPresenter<UserRepositoryView> {
    private ArrayList<RepositoryItem> fullData;
    int maxY;
    int minY;

    public UserRepositoryPresenter(UserRepositoryView view) {
        super(view);
        this.minY = 2022;
        this.maxY = 1990;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    public void onInit(UserRepositoryView view) {
        view.showFilter(false);
        Intent intent = view.getIntent();
        String stringExtra = intent.getStringExtra("author_name_str");
        String stringExtra2 = intent.getStringExtra("author_initials");
        view.setTitle("Репозиторій " + stringExtra + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + stringExtra2);
        getRepository().call(APIMethods.getRepositoryItemsByUser(stringExtra, stringExtra2)).async(new AnonymousClass1(view));
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.activities.repositoryPeople.UserRepositoryPresenter$1, reason: invalid class name */
    class AnonymousClass1 extends APIObjectListener<RepositoryPage> {
        final /* synthetic */ UserRepositoryView val$view;

        AnonymousClass1(final UserRepositoryView val$view) {
            this.val$view = val$view;
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
        public void onError(ErrorResponse response) {
            this.val$view.showError(response.getMessage());
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
        public void onSuccessData(RepositoryPage data) {
            UserRepositoryPresenter.this.fullData = data.getItems();
            Collections.sort(UserRepositoryPresenter.this.fullData, new Comparator() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.repositoryPeople.UserRepositoryPresenter$1$$ExternalSyntheticLambda0
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    int compare;
                    compare = Integer.compare(((RepositoryItem) obj2).getDateYear(), ((RepositoryItem) obj).getDateYear());
                    return compare;
                }
            });
            if (!UserRepositoryPresenter.this.fullData.isEmpty()) {
                Iterator it = UserRepositoryPresenter.this.fullData.iterator();
                while (it.hasNext()) {
                    RepositoryItem repositoryItem = (RepositoryItem) it.next();
                    if (repositoryItem.getDateYear() < UserRepositoryPresenter.this.minY) {
                        UserRepositoryPresenter.this.minY = repositoryItem.getDateYear();
                    }
                    if (repositoryItem.getDateYear() > UserRepositoryPresenter.this.maxY) {
                        UserRepositoryPresenter.this.maxY = repositoryItem.getDateYear();
                    }
                }
                this.val$view.setFilterRange(UserRepositoryPresenter.this.minY, UserRepositoryPresenter.this.maxY);
                this.val$view.setFilterTypes(data.getDocumentTypes());
                this.val$view.onDataSet(UserRepositoryPresenter.this.fullData);
                return;
            }
            this.val$view.showEmpty("Порожньо");
        }
    }

    public void onFilterChanged(ArrayList<String> filterCheckedType, List<Float> values, int checkedChipId) {
        int intValue = ((Float) Collections.min(values)).intValue();
        int intValue2 = ((Float) Collections.max(values)).intValue();
        if (intValue2 == intValue && intValue == 0) {
            intValue2 = Integer.MAX_VALUE;
        }
        ArrayList<RepositoryItem> arrayList = new ArrayList<>();
        Iterator<RepositoryItem> it = this.fullData.iterator();
        while (it.hasNext()) {
            RepositoryItem next = it.next();
            if (next.getDateYear() >= intValue && next.getDateYear() <= intValue2 && (filterCheckedType.isEmpty() || filterCheckedType.contains(next.getTypeDocument()))) {
                arrayList.add(next);
            }
        }
        final Collator collator = Collator.getInstance(new Locale("uk", "UA"));
        if (checkedChipId == R.id.sortByDate) {
            Collections.sort(arrayList, new Comparator() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.repositoryPeople.UserRepositoryPresenter$$ExternalSyntheticLambda0
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    int compare;
                    compare = Integer.compare(((RepositoryItem) obj2).getDateYear(), ((RepositoryItem) obj).getDateYear());
                    return compare;
                }
            });
        }
        if (checkedChipId == R.id.sortByName) {
            Collections.sort(arrayList, new Comparator() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.repositoryPeople.UserRepositoryPresenter$$ExternalSyntheticLambda1
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    int compare;
                    compare = collator.compare(((RepositoryItem) obj).getTitle(), ((RepositoryItem) obj2).getTitle());
                    return compare;
                }
            });
        }
        if (checkedChipId == R.id.sortByType) {
            Collections.sort(arrayList, new Comparator() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.repositoryPeople.UserRepositoryPresenter$$ExternalSyntheticLambda2
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    int compare;
                    compare = collator.compare(((RepositoryItem) obj).getDocumentTypeName(), ((RepositoryItem) obj2).getDocumentTypeName());
                    return compare;
                }
            });
        }
        ((UserRepositoryView) this.view).onDataSet(arrayList);
        ((UserRepositoryView) this.view).showFilter(false);
    }

    public void reset() {
        ((UserRepositoryView) this.view).setFilterRange(this.minY, this.maxY);
    }
}
