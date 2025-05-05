package com.google.android.gms.common.images;

import android.content.Context;
import android.net.Uri;
import android.os.SystemClock;
import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.common.images.ImageManager.ImageReceiver;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.internal.base.zam;
import java.util.HashSet;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
/* loaded from: classes.dex */
final class zab implements Runnable {
    final /* synthetic */ ImageManager zaa;
    private final zag zab;

    public zab(ImageManager imageManager, zag zagVar) {
        this.zaa = imageManager;
        this.zab = zagVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Map map;
        zam zamVar;
        Map map2;
        Map map3;
        Object obj;
        HashSet hashSet;
        HashSet hashSet2;
        Map map4;
        Map map5;
        Map map6;
        zam zamVar2;
        Map map7;
        Asserts.checkMainThread("LoadImageRunnable must be executed on the main thread");
        map = this.zaa.zah;
        ImageManager.ImageReceiver imageReceiver = (ImageManager.ImageReceiver) map.get(this.zab);
        if (imageReceiver != null) {
            ImageManager imageManager = this.zaa;
            zag zagVar = this.zab;
            map7 = imageManager.zah;
            map7.remove(zagVar);
            imageReceiver.zac(this.zab);
        }
        zag zagVar2 = this.zab;
        zad zadVar = zagVar2.zaa;
        Uri uri = zadVar.zaa;
        if (uri == null) {
            ImageManager imageManager2 = this.zaa;
            Context context = imageManager2.zad;
            zamVar = imageManager2.zag;
            zagVar2.zab(context, zamVar, true);
            return;
        }
        map2 = this.zaa.zaj;
        Long l = (Long) map2.get(uri);
        if (l != null) {
            if (SystemClock.elapsedRealtime() - l.longValue() < 3600000) {
                zag zagVar3 = this.zab;
                ImageManager imageManager3 = this.zaa;
                Context context2 = imageManager3.zad;
                zamVar2 = imageManager3.zag;
                zagVar3.zab(context2, zamVar2, true);
                return;
            }
            ImageManager imageManager4 = this.zaa;
            Uri uri2 = zadVar.zaa;
            map6 = imageManager4.zaj;
            map6.remove(uri2);
        }
        this.zab.zaa(null, false, true, false);
        ImageManager imageManager5 = this.zaa;
        Uri uri3 = zadVar.zaa;
        map3 = imageManager5.zai;
        ImageManager.ImageReceiver imageReceiver2 = (ImageManager.ImageReceiver) map3.get(uri3);
        if (imageReceiver2 == null) {
            ImageManager.ImageReceiver imageReceiver3 = this.zaa.new ImageReceiver(zadVar.zaa);
            ImageManager imageManager6 = this.zaa;
            Uri uri4 = zadVar.zaa;
            map5 = imageManager6.zai;
            map5.put(uri4, imageReceiver3);
            imageReceiver2 = imageReceiver3;
        }
        imageReceiver2.zab(this.zab);
        zag zagVar4 = this.zab;
        if (!(zagVar4 instanceof zaf)) {
            map4 = this.zaa.zah;
            map4.put(zagVar4, imageReceiver2);
        }
        obj = ImageManager.zaa;
        synchronized (obj) {
            hashSet = ImageManager.zab;
            if (!hashSet.contains(zadVar.zaa)) {
                hashSet2 = ImageManager.zab;
                hashSet2.add(zadVar.zaa);
                imageReceiver2.zad();
            }
        }
    }
}
