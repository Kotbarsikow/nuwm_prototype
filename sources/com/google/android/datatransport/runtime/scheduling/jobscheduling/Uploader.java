package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.runtime.EncodedPayload;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.backends.BackendRequest;
import com.google.android.datatransport.runtime.backends.BackendResponse;
import com.google.android.datatransport.runtime.backends.TransportBackend;
import com.google.android.datatransport.runtime.firebase.transport.ClientMetrics;
import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.scheduling.persistence.ClientHealthMetricsStore;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.PersistedEvent;
import com.google.android.datatransport.runtime.synchronization.SynchronizationException;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import com.google.android.datatransport.runtime.time.Clock;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class Uploader {
    private static final String CLIENT_HEALTH_METRICS_LOG_SOURCE = "GDT_CLIENT_METRICS";
    private static final String LOG_TAG = "Uploader";
    private final BackendRegistry backendRegistry;
    private final ClientHealthMetricsStore clientHealthMetricsStore;
    private final Clock clock;
    private final Context context;
    private final EventStore eventStore;
    private final Executor executor;
    private final SynchronizationGuard guard;
    private final Clock uptimeClock;
    private final WorkScheduler workScheduler;

    @Inject
    public Uploader(Context context, BackendRegistry backendRegistry, EventStore eventStore, WorkScheduler workScheduler, Executor executor, SynchronizationGuard synchronizationGuard, Clock clock, Clock clock2, ClientHealthMetricsStore clientHealthMetricsStore) {
        this.context = context;
        this.backendRegistry = backendRegistry;
        this.eventStore = eventStore;
        this.workScheduler = workScheduler;
        this.executor = executor;
        this.guard = synchronizationGuard;
        this.clock = clock;
        this.uptimeClock = clock2;
        this.clientHealthMetricsStore = clientHealthMetricsStore;
    }

    boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void upload(TransportContext transportContext, int i, Runnable runnable) {
        this.executor.execute(new Runnable() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader$$ExternalSyntheticLambda3
            public final /* synthetic */ TransportContext f$1;
            public final /* synthetic */ int f$2;
            public final /* synthetic */ Runnable f$3;

            public /* synthetic */ Uploader$$ExternalSyntheticLambda3(TransportContext transportContext2, int i2, Runnable runnable2) {
                r2 = transportContext2;
                r3 = i2;
                r4 = runnable2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                Uploader.this.m67x80c37673(r2, r3, r4);
            }
        });
    }

    /* renamed from: lambda$upload$1$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader */
    /* synthetic */ void m67x80c37673(TransportContext transportContext, int i, Runnable runnable) {
        try {
            try {
                SynchronizationGuard synchronizationGuard = this.guard;
                EventStore eventStore = this.eventStore;
                Objects.requireNonNull(eventStore);
                synchronizationGuard.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader$$ExternalSyntheticLambda0
                    public /* synthetic */ Uploader$$ExternalSyntheticLambda0() {
                    }

                    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
                    public final Object execute() {
                        return Integer.valueOf(EventStore.this.cleanUp());
                    }
                });
                if (!isNetworkAvailable()) {
                    this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader$$ExternalSyntheticLambda2
                        public final /* synthetic */ TransportContext f$1;
                        public final /* synthetic */ int f$2;

                        public /* synthetic */ Uploader$$ExternalSyntheticLambda2(TransportContext transportContext2, int i2) {
                            r2 = transportContext2;
                            r3 = i2;
                        }

                        @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
                        public final Object execute() {
                            return Uploader.this.m66x3eac4914(r2, r3);
                        }
                    });
                } else {
                    logAndUpdateState(transportContext2, i2);
                }
            } catch (SynchronizationException unused) {
                this.workScheduler.schedule(transportContext2, i2 + 1);
            }
        } finally {
            runnable.run();
        }
    }

    /* renamed from: lambda$upload$0$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader */
    /* synthetic */ Object m66x3eac4914(TransportContext transportContext, int i) {
        this.workScheduler.schedule(transportContext, i + 1);
        return null;
    }

    public BackendResponse logAndUpdateState(TransportContext transportContext, int i) {
        BackendResponse send;
        TransportBackend transportBackend = this.backendRegistry.get(transportContext.getBackendName());
        long j = 0;
        BackendResponse ok = BackendResponse.ok(0L);
        while (true) {
            long j2 = j;
            while (((Boolean) this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader$$ExternalSyntheticLambda5
                public final /* synthetic */ TransportContext f$1;

                public /* synthetic */ Uploader$$ExternalSyntheticLambda5(TransportContext transportContext2) {
                    r2 = transportContext2;
                }

                @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
                public final Object execute() {
                    return Uploader.this.m59x65f78bd8(r2);
                }
            })).booleanValue()) {
                Iterable iterable = (Iterable) this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader$$ExternalSyntheticLambda6
                    public final /* synthetic */ TransportContext f$1;

                    public /* synthetic */ Uploader$$ExternalSyntheticLambda6(TransportContext transportContext2) {
                        r2 = transportContext2;
                    }

                    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
                    public final Object execute() {
                        return Uploader.this.m60xa80eb937(r2);
                    }
                });
                if (!iterable.iterator().hasNext()) {
                    return ok;
                }
                if (transportBackend == null) {
                    Logging.d(LOG_TAG, "Unknown backend for %s, deleting event batch for it...", transportContext2);
                    send = BackendResponse.fatalError();
                } else {
                    ArrayList arrayList = new ArrayList();
                    Iterator it = iterable.iterator();
                    while (it.hasNext()) {
                        arrayList.add(((PersistedEvent) it.next()).getEvent());
                    }
                    if (transportContext2.shouldUploadClientHealthMetrics()) {
                        arrayList.add(createMetricsEvent(transportBackend));
                    }
                    send = transportBackend.send(BackendRequest.builder().setEvents(arrayList).setExtras(transportContext2.getExtras()).build());
                }
                ok = send;
                if (ok.getStatus() == BackendResponse.Status.TRANSIENT_ERROR) {
                    this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader$$ExternalSyntheticLambda7
                        public final /* synthetic */ Iterable f$1;
                        public final /* synthetic */ TransportContext f$2;
                        public final /* synthetic */ long f$3;

                        public /* synthetic */ Uploader$$ExternalSyntheticLambda7(Iterable iterable2, TransportContext transportContext2, long j22) {
                            r2 = iterable2;
                            r3 = transportContext2;
                            r4 = j22;
                        }

                        @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
                        public final Object execute() {
                            return Uploader.this.m61xea25e696(r2, r3, r4);
                        }
                    });
                    this.workScheduler.schedule(transportContext2, i + 1, true);
                    return ok;
                }
                this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader$$ExternalSyntheticLambda8
                    public final /* synthetic */ Iterable f$1;

                    public /* synthetic */ Uploader$$ExternalSyntheticLambda8(Iterable iterable2) {
                        r2 = iterable2;
                    }

                    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
                    public final Object execute() {
                        return Uploader.this.m62x2c3d13f5(r2);
                    }
                });
                if (ok.getStatus() == BackendResponse.Status.OK) {
                    j = Math.max(j22, ok.getNextRequestWaitMillis());
                    if (transportContext2.shouldUploadClientHealthMetrics()) {
                        this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader$$ExternalSyntheticLambda9
                            public /* synthetic */ Uploader$$ExternalSyntheticLambda9() {
                            }

                            @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
                            public final Object execute() {
                                return Uploader.this.m63x6e544154();
                            }
                        });
                    }
                } else if (ok.getStatus() == BackendResponse.Status.INVALID_PAYLOAD) {
                    HashMap hashMap = new HashMap();
                    Iterator it2 = iterable2.iterator();
                    while (it2.hasNext()) {
                        String transportName = ((PersistedEvent) it2.next()).getEvent().getTransportName();
                        if (!hashMap.containsKey(transportName)) {
                            hashMap.put(transportName, 1);
                        } else {
                            hashMap.put(transportName, Integer.valueOf(((Integer) hashMap.get(transportName)).intValue() + 1));
                        }
                    }
                    this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader$$ExternalSyntheticLambda10
                        public final /* synthetic */ Map f$1;

                        public /* synthetic */ Uploader$$ExternalSyntheticLambda10(Map hashMap2) {
                            r2 = hashMap2;
                        }

                        @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
                        public final Object execute() {
                            return Uploader.this.m64xb06b6eb3(r2);
                        }
                    });
                }
            }
            this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader$$ExternalSyntheticLambda1
                public final /* synthetic */ TransportContext f$1;
                public final /* synthetic */ long f$2;

                public /* synthetic */ Uploader$$ExternalSyntheticLambda1(TransportContext transportContext2, long j22) {
                    r2 = transportContext2;
                    r3 = j22;
                }

                @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
                public final Object execute() {
                    return Uploader.this.m65xf2829c12(r2, r3);
                }
            });
            return ok;
        }
    }

    /* renamed from: lambda$logAndUpdateState$2$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader */
    /* synthetic */ Boolean m59x65f78bd8(TransportContext transportContext) {
        return Boolean.valueOf(this.eventStore.hasPendingEventsFor(transportContext));
    }

    /* renamed from: lambda$logAndUpdateState$3$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader */
    /* synthetic */ Iterable m60xa80eb937(TransportContext transportContext) {
        return this.eventStore.loadBatch(transportContext);
    }

    /* renamed from: lambda$logAndUpdateState$4$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader */
    /* synthetic */ Object m61xea25e696(Iterable iterable, TransportContext transportContext, long j) {
        this.eventStore.recordFailure(iterable);
        this.eventStore.recordNextCallTime(transportContext, this.clock.getTime() + j);
        return null;
    }

    /* renamed from: lambda$logAndUpdateState$5$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader */
    /* synthetic */ Object m62x2c3d13f5(Iterable iterable) {
        this.eventStore.recordSuccess(iterable);
        return null;
    }

    /* renamed from: lambda$logAndUpdateState$6$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader */
    /* synthetic */ Object m63x6e544154() {
        this.clientHealthMetricsStore.resetClientMetrics();
        return null;
    }

    /* renamed from: lambda$logAndUpdateState$7$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader */
    /* synthetic */ Object m64xb06b6eb3(Map map) {
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            this.clientHealthMetricsStore.recordLogEventDropped(((Integer) r0.getValue()).intValue(), LogEventDropped.Reason.INVALID_PAYLOD, (String) ((Map.Entry) it.next()).getKey());
        }
        return null;
    }

    /* renamed from: lambda$logAndUpdateState$8$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader */
    /* synthetic */ Object m65xf2829c12(TransportContext transportContext, long j) {
        this.eventStore.recordNextCallTime(transportContext, this.clock.getTime() + j);
        return null;
    }

    public EventInternal createMetricsEvent(TransportBackend transportBackend) {
        SynchronizationGuard synchronizationGuard = this.guard;
        ClientHealthMetricsStore clientHealthMetricsStore = this.clientHealthMetricsStore;
        Objects.requireNonNull(clientHealthMetricsStore);
        return transportBackend.decorate(EventInternal.builder().setEventMillis(this.clock.getTime()).setUptimeMillis(this.uptimeClock.getTime()).setTransportName(CLIENT_HEALTH_METRICS_LOG_SOURCE).setEncodedPayload(new EncodedPayload(Encoding.of("proto"), ((ClientMetrics) synchronizationGuard.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader$$ExternalSyntheticLambda4
            public /* synthetic */ Uploader$$ExternalSyntheticLambda4() {
            }

            @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
            public final Object execute() {
                return ClientHealthMetricsStore.this.loadClientMetrics();
            }
        })).toByteArray())).build());
    }
}
