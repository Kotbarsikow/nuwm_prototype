package com.google.android.gms.measurement.internal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PersistableBundle;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.util.Clock;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
public final class zznq extends zznr {
    private final AlarmManager zza;
    private zzav zzb;
    private Integer zzc;

    private final int zzv() {
        if (this.zzc == null) {
            this.zzc = Integer.valueOf(("measurement" + zza().getPackageName()).hashCode());
        }
        return this.zzc.intValue();
    }

    private final PendingIntent zzw() {
        Context zza = zza();
        return com.google.android.gms.internal.measurement.zzcw.zza(zza, 0, new Intent().setClassName(zza, "com.google.android.gms.measurement.AppMeasurementReceiver").setAction("com.google.android.gms.measurement.UPLOAD"), com.google.android.gms.internal.measurement.zzcw.zza);
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzns
    public final /* bridge */ /* synthetic */ zzt zzg() {
        return super.zzg();
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ zzab zzd() {
        return super.zzd();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzag zze() {
        return super.zze();
    }

    @Override // com.google.android.gms.measurement.internal.zzns
    public final /* bridge */ /* synthetic */ zzal zzh() {
        return super.zzh();
    }

    private final zzav zzx() {
        if (this.zzb == null) {
            this.zzb = new zznp(this, this.zzg.zzk());
        }
        return this.zzb;
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzaz zzf() {
        return super.zzf();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzi() {
        return super.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ zzgo zzj() {
        return super.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzha zzk() {
        return super.zzk();
    }

    @Override // com.google.android.gms.measurement.internal.zzns
    public final /* bridge */ /* synthetic */ zzhl zzm() {
        return super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ zzhv zzl() {
        return super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzns
    public final /* bridge */ /* synthetic */ zzmw zzn() {
        return super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzns
    public final /* bridge */ /* synthetic */ zznu zzo() {
        return super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzns
    public final /* bridge */ /* synthetic */ zzoo g_() {
        return super.g_();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzos zzq() {
        return super.zzq();
    }

    protected zznq(zznv zznvVar) {
        super(zznvVar);
        this.zza = (AlarmManager) zza().getSystemService(NotificationCompat.CATEGORY_ALARM);
    }

    public final void zzu() {
        zzal();
        zzj().zzp().zza("Unscheduling upload");
        AlarmManager alarmManager = this.zza;
        if (alarmManager != null) {
            alarmManager.cancel(zzw());
        }
        zzx().zza();
        if (Build.VERSION.SDK_INT >= 24) {
            zzy();
        }
    }

    private final void zzy() {
        JobScheduler jobScheduler = (JobScheduler) zza().getSystemService("jobscheduler");
        if (jobScheduler != null) {
            jobScheduler.cancel(zzv());
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    public final /* bridge */ /* synthetic */ void zzr() {
        super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    public final /* bridge */ /* synthetic */ void zzt() {
        super.zzt();
    }

    public final void zza(long j) {
        zzal();
        Context zza = zza();
        if (!zzos.zza(zza)) {
            zzj().zzc().zza("Receiver not registered/enabled");
        }
        if (!zzos.zza(zza, false)) {
            zzj().zzc().zza("Service not registered/enabled");
        }
        zzu();
        zzj().zzp().zza("Scheduling upload, millis", Long.valueOf(j));
        long elapsedRealtime = zzb().elapsedRealtime() + j;
        if (j < Math.max(0L, zzbh.zzy.zza(null).longValue()) && !zzx().zzc()) {
            zzx().zza(j);
        }
        if (Build.VERSION.SDK_INT >= 24) {
            Context zza2 = zza();
            ComponentName componentName = new ComponentName(zza2, "com.google.android.gms.measurement.AppMeasurementJobService");
            int zzv = zzv();
            PersistableBundle persistableBundle = new PersistableBundle();
            persistableBundle.putString("action", "com.google.android.gms.measurement.UPLOAD");
            com.google.android.gms.internal.measurement.zzcv.zza(zza2, new JobInfo.Builder(zzv, componentName).setMinimumLatency(j).setOverrideDeadline(j << 1).setExtras(persistableBundle).build(), "com.google.android.gms", "UploadAlarm");
            return;
        }
        AlarmManager alarmManager = this.zza;
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(2, elapsedRealtime, Math.max(zzbh.zzt.zza(null).longValue(), j), zzw());
        }
    }

    @Override // com.google.android.gms.measurement.internal.zznr
    protected final boolean zzc() {
        AlarmManager alarmManager = this.zza;
        if (alarmManager != null) {
            alarmManager.cancel(zzw());
        }
        if (Build.VERSION.SDK_INT < 24) {
            return false;
        }
        zzy();
        return false;
    }
}
