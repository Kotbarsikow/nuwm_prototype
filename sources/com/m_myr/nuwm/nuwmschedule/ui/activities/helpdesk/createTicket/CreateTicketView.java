package com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.createTicket;

import androidx.lifecycle.LifecycleOwner;
import com.m_myr.nuwm.nuwmschedule.data.models.HelpdeskCategory;
import java.util.ArrayList;
import kotlin.Metadata;

/* compiled from: CreateTicketView.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0016\u0010\u0006\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH&J\u0012\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\fH&J\u0012\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000eH&¨\u0006\u0010"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/createTicket/CreateTicketView;", "Landroidx/lifecycle/LifecycleOwner;", "created", "", "id", "", "setCategories", "list", "Ljava/util/ArrayList;", "Lcom/m_myr/nuwm/nuwmschedule/data/models/HelpdeskCategory;", "showError", "message", "", "validate", "", "finally", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface CreateTicketView extends LifecycleOwner {
    void created(int id);

    void setCategories(ArrayList<HelpdeskCategory> list);

    void showError(String message);

    boolean validate(boolean r1);

    /* compiled from: CreateTicketView.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ boolean validate$default(CreateTicketView createTicketView, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: validate");
            }
            if ((i & 1) != 0) {
                z = false;
            }
            return createTicketView.validate(z);
        }
    }
}
