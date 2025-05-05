package com.m_myr.nuwm.nuwmschedule.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.DocumentInfo;
import com.m_myr.nuwm.nuwmschedule.ui.activities.imageview.ImageViewActivity;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MaterialFutureDocumentHolderItem.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/view/MaterialFutureDocumentHolderItem;", "Lcom/m_myr/nuwm/nuwmschedule/ui/view/MaterialDocumentHolderItem;", "inflater", "Landroid/view/LayoutInflater;", "parent", "Landroid/view/ViewGroup;", "(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;)V", "progressBar", "Lcom/google/android/material/progressindicator/LinearProgressIndicator;", "getProgressBar", "()Lcom/google/android/material/progressindicator/LinearProgressIndicator;", "future", "fileName", "", "initDocument", "doc", "Lcom/m_myr/nuwm/nuwmschedule/data/models/DocumentInfo;", "setProgress", "", "progress", "", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MaterialFutureDocumentHolderItem extends MaterialDocumentHolderItem {
    private final LinearProgressIndicator progressBar;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public MaterialFutureDocumentHolderItem(android.view.LayoutInflater r3, android.view.ViewGroup r4) {
        /*
            r2 = this;
            java.lang.String r0 = "inflater"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "parent"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            r0 = 2131558500(0x7f0d0064, float:1.8742318E38)
            r1 = 0
            android.view.View r3 = r3.inflate(r0, r4, r1)
            java.lang.String r4 = "inflate(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            r2.<init>(r3)
            android.view.View r3 = r2.getView()
            r4 = 2131362532(0x7f0a02e4, float:1.8344847E38)
            android.view.View r3 = r3.findViewById(r4)
            java.lang.String r4 = "findViewById(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            com.google.android.material.progressindicator.LinearProgressIndicator r3 = (com.google.android.material.progressindicator.LinearProgressIndicator) r3
            r2.progressBar = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.m_myr.nuwm.nuwmschedule.ui.view.MaterialFutureDocumentHolderItem.<init>(android.view.LayoutInflater, android.view.ViewGroup):void");
    }

    public final LinearProgressIndicator getProgressBar() {
        return this.progressBar;
    }

    public final MaterialFutureDocumentHolderItem future(String fileName) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        this.txt1.setText(fileName);
        this.txt2.setVisibility(8);
        this.progressBar.setVisibility(0);
        return this;
    }

    public final void setProgress(int progress) {
        this.progressBar.setIndeterminate(false);
        this.progressBar.setProgress(progress);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.view.MaterialDocumentHolderItem
    public MaterialFutureDocumentHolderItem initDocument(final DocumentInfo doc) {
        Intrinsics.checkNotNullParameter(doc, "doc");
        super.initDocument(doc);
        this.progressBar.setVisibility(8);
        this.txt2.setVisibility(0);
        if (Utils.contentTypeIsPicture(doc.getType())) {
            getView().findViewById(R.id.file_ico).setBackground(null);
            final ImageView imageView = (ImageView) getView().findViewById(R.id.icon_inside);
            imageView.setPadding(0, 0, 0, 0);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageTintList(null);
            getView().setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.MaterialFutureDocumentHolderItem$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MaterialFutureDocumentHolderItem.initDocument$lambda$0(imageView, doc, view);
                }
            });
            RequestOptions transforms = new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(16));
            Intrinsics.checkNotNullExpressionValue(transforms, "transforms(...)");
            Glide.with(imageView.getContext()).load(doc.getUrl()).placeholder(R.drawable.q_round).error(R.drawable.q_round).apply((BaseRequestOptions<?>) transforms).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDocument$lambda$0(ImageView imageView, DocumentInfo doc, View view) {
        Intrinsics.checkNotNullParameter(doc, "$doc");
        Intent intent = new Intent(imageView.getContext(), (Class<?>) ImageViewActivity.class);
        intent.putExtra(ImagesContract.URL, doc.getUrl());
        Context context = imageView.getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
        ActivityOptionsCompat makeSceneTransitionAnimation = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, imageView, "image");
        Intrinsics.checkNotNullExpressionValue(makeSceneTransitionAnimation, "makeSceneTransitionAnimation(...)");
        ActivityCompat.startActivity(imageView.getContext(), intent, makeSceneTransitionAnimation.toBundle());
    }
}
