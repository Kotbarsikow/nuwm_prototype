package com.m_myr.nuwm.nuwmschedule.ui.view;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.hootsuite.nachos.tokenizer.SpanChipTokenizer;
import com.m_myr.nuwm.nuwmschedule.data.models.DocumentInfo;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MaterialDocumentHolderItem.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB\r\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¨\u0006\u0010"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/view/MaterialDocumentHolderItem;", "Lcom/m_myr/nuwm/nuwmschedule/ui/view/DocumentHolderItem;", "inflater", "Landroid/view/LayoutInflater;", "parent", "Landroid/view/ViewGroup;", "(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;)V", "id", "", "(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;I)V", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "initDocument", "doc", "Lcom/m_myr/nuwm/nuwmschedule/data/models/DocumentInfo;", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class MaterialDocumentHolderItem extends DocumentHolderItem {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MaterialDocumentHolderItem(View view) {
        super(view);
        Intrinsics.checkNotNullParameter(view, "view");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public MaterialDocumentHolderItem(android.view.LayoutInflater r3, android.view.ViewGroup r4) {
        /*
            r2 = this;
            java.lang.String r0 = "inflater"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "parent"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            r0 = 2131558498(0x7f0d0062, float:1.8742314E38)
            r1 = 0
            android.view.View r3 = r3.inflate(r0, r4, r1)
            java.lang.String r4 = "inflate(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            r2.<init>(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.m_myr.nuwm.nuwmschedule.ui.view.MaterialDocumentHolderItem.<init>(android.view.LayoutInflater, android.view.ViewGroup):void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public MaterialDocumentHolderItem(android.view.LayoutInflater r2, android.view.ViewGroup r3, int r4) {
        /*
            r1 = this;
            java.lang.String r0 = "inflater"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "parent"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            r0 = 0
            android.view.View r2 = r2.inflate(r4, r3, r0)
            java.lang.String r3 = "inflate(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.m_myr.nuwm.nuwmschedule.ui.view.MaterialDocumentHolderItem.<init>(android.view.LayoutInflater, android.view.ViewGroup, int):void");
    }

    public MaterialDocumentHolderItem initDocument(final DocumentInfo doc) {
        Intrinsics.checkNotNullParameter(doc, "doc");
        this.txt1.setText(doc.getName());
        this.txt2.setText(Utils.humanReadableByteCountSI(doc.getSize()) + SpanChipTokenizer.AUTOCORRECT_SEPARATOR + doc.getExtension());
        this.v.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.MaterialDocumentHolderItem$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MaterialDocumentHolderItem.initDocument$lambda$0(DocumentInfo.this, this, view);
            }
        });
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDocument$lambda$0(DocumentInfo doc, MaterialDocumentHolderItem this$0, View view) {
        Intrinsics.checkNotNullParameter(doc, "$doc");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(doc.getUrl()));
        this$0.v.getContext().startActivity(intent);
    }
}
