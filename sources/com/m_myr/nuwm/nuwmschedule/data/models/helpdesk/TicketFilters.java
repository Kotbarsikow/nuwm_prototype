package com.m_myr.nuwm.nuwmschedule.data.models.helpdesk;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TicketFilters.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\bHÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J7\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u00032\b\u0010\u001f\u001a\u0004\u0018\u00010 HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020$HÖ\u0001R\u001e\u0010\t\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006%"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/TicketFilters;", "Ljava/io/Serializable;", "showLastReplies", "", "selectCategory", "", "Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/TicketStatus;", "sortBy", "Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/SortBy;", "pinCriticalTicket", "(ZLjava/util/Set;Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/SortBy;Z)V", "getPinCriticalTicket", "()Z", "setPinCriticalTicket", "(Z)V", "getSelectCategory", "()Ljava/util/Set;", "setSelectCategory", "(Ljava/util/Set;)V", "getShowLastReplies", "setShowLastReplies", "getSortBy", "()Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/SortBy;", "setSortBy", "(Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/SortBy;)V", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "", "toString", "", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class TicketFilters implements Serializable {

    @SerializedName("pinCriticalTicket")
    private boolean pinCriticalTicket;

    @SerializedName("selectCategory")
    private Set<? extends TicketStatus> selectCategory;

    @SerializedName("showLastReplies")
    private boolean showLastReplies;

    @SerializedName("sortBy")
    private SortBy sortBy;

    public TicketFilters() {
        this(false, null, null, false, 15, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TicketFilters copy$default(TicketFilters ticketFilters, boolean z, Set set, SortBy sortBy, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = ticketFilters.showLastReplies;
        }
        if ((i & 2) != 0) {
            set = ticketFilters.selectCategory;
        }
        if ((i & 4) != 0) {
            sortBy = ticketFilters.sortBy;
        }
        if ((i & 8) != 0) {
            z2 = ticketFilters.pinCriticalTicket;
        }
        return ticketFilters.copy(z, set, sortBy, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getShowLastReplies() {
        return this.showLastReplies;
    }

    public final Set<TicketStatus> component2() {
        return this.selectCategory;
    }

    /* renamed from: component3, reason: from getter */
    public final SortBy getSortBy() {
        return this.sortBy;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getPinCriticalTicket() {
        return this.pinCriticalTicket;
    }

    public final TicketFilters copy(boolean showLastReplies, Set<? extends TicketStatus> selectCategory, SortBy sortBy, boolean pinCriticalTicket) {
        Intrinsics.checkNotNullParameter(selectCategory, "selectCategory");
        Intrinsics.checkNotNullParameter(sortBy, "sortBy");
        return new TicketFilters(showLastReplies, selectCategory, sortBy, pinCriticalTicket);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TicketFilters)) {
            return false;
        }
        TicketFilters ticketFilters = (TicketFilters) other;
        return this.showLastReplies == ticketFilters.showLastReplies && Intrinsics.areEqual(this.selectCategory, ticketFilters.selectCategory) && this.sortBy == ticketFilters.sortBy && this.pinCriticalTicket == ticketFilters.pinCriticalTicket;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    public int hashCode() {
        boolean z = this.showLastReplies;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int hashCode = ((((r0 * 31) + this.selectCategory.hashCode()) * 31) + this.sortBy.hashCode()) * 31;
        boolean z2 = this.pinCriticalTicket;
        return hashCode + (z2 ? 1 : z2 ? 1 : 0);
    }

    public String toString() {
        return "TicketFilters(showLastReplies=" + this.showLastReplies + ", selectCategory=" + this.selectCategory + ", sortBy=" + this.sortBy + ", pinCriticalTicket=" + this.pinCriticalTicket + ')';
    }

    public TicketFilters(boolean z, Set<? extends TicketStatus> selectCategory, SortBy sortBy, boolean z2) {
        Intrinsics.checkNotNullParameter(selectCategory, "selectCategory");
        Intrinsics.checkNotNullParameter(sortBy, "sortBy");
        this.showLastReplies = z;
        this.selectCategory = selectCategory;
        this.sortBy = sortBy;
        this.pinCriticalTicket = z2;
    }

    public final boolean getShowLastReplies() {
        return this.showLastReplies;
    }

    public final void setShowLastReplies(boolean z) {
        this.showLastReplies = z;
    }

    public /* synthetic */ TicketFilters(boolean z, Set set, SortBy sortBy, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z, (i & 2) != 0 ? SetsKt.emptySet() : set, (i & 4) != 0 ? SortBy.TIME : sortBy, (i & 8) != 0 ? false : z2);
    }

    public final Set<TicketStatus> getSelectCategory() {
        return this.selectCategory;
    }

    public final void setSelectCategory(Set<? extends TicketStatus> set) {
        Intrinsics.checkNotNullParameter(set, "<set-?>");
        this.selectCategory = set;
    }

    public final SortBy getSortBy() {
        return this.sortBy;
    }

    public final void setSortBy(SortBy sortBy) {
        Intrinsics.checkNotNullParameter(sortBy, "<set-?>");
        this.sortBy = sortBy;
    }

    public final boolean getPinCriticalTicket() {
        return this.pinCriticalTicket;
    }

    public final void setPinCriticalTicket(boolean z) {
        this.pinCriticalTicket = z;
    }
}
