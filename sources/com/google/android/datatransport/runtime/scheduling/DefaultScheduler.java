package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.TransportScheduleCallback;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.backends.TransportBackend;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.concurrent.Executor;
import java.util.logging.Logger;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class DefaultScheduler implements Scheduler {
    private static final Logger LOGGER = Logger.getLogger(TransportRuntime.class.getName());
    private final BackendRegistry backendRegistry;
    private final EventStore eventStore;
    private final Executor executor;
    private final SynchronizationGuard guard;
    private final WorkScheduler workScheduler;

    @Inject
    public DefaultScheduler(Executor executor, BackendRegistry backendRegistry, WorkScheduler workScheduler, EventStore eventStore, SynchronizationGuard synchronizationGuard) {
        this.executor = executor;
        this.backendRegistry = backendRegistry;
        this.workScheduler = workScheduler;
        this.eventStore = eventStore;
        this.guard = synchronizationGuard;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.Scheduler
    public void schedule(TransportContext transportContext, EventInternal eventInternal, TransportScheduleCallback transportScheduleCallback) {
        this.executor.execute(new Runnable() { // from class: com.google.android.datatransport.runtime.scheduling.DefaultScheduler$$ExternalSyntheticLambda0
            public final /* synthetic */ TransportContext f$1;
            public final /* synthetic */ TransportScheduleCallback f$2;
            public final /* synthetic */ EventInternal f$3;

            public /* synthetic */ DefaultScheduler$$ExternalSyntheticLambda0(TransportContext transportContext2, TransportScheduleCallback transportScheduleCallback2, EventInternal eventInternal2) {
                r2 = transportContext2;
                r3 = transportScheduleCallback2;
                r4 = eventInternal2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                DefaultScheduler.this.m57x41d0caed(r2, r3, r4);
            }
        });
    }

    /* renamed from: lambda$schedule$1$com-google-android-datatransport-runtime-scheduling-DefaultScheduler */
    /* synthetic */ void m57x41d0caed(TransportContext transportContext, TransportScheduleCallback transportScheduleCallback, EventInternal eventInternal) {
        try {
            TransportBackend transportBackend = this.backendRegistry.get(transportContext.getBackendName());
            if (transportBackend == null) {
                String format = String.format("Transport backend '%s' is not registered", transportContext.getBackendName());
                LOGGER.warning(format);
                transportScheduleCallback.onSchedule(new IllegalArgumentException(format));
            } else {
                this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.DefaultScheduler$$ExternalSyntheticLambda1
                    public final /* synthetic */ TransportContext f$1;
                    public final /* synthetic */ EventInternal f$2;

                    public /* synthetic */ DefaultScheduler$$ExternalSyntheticLambda1(TransportContext transportContext2, EventInternal eventInternal2) {
                        r2 = transportContext2;
                        r3 = eventInternal2;
                    }

                    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
                    public final Object execute() {
                        return DefaultScheduler.this.m56x8f06a4e(r2, r3);
                    }
                });
                transportScheduleCallback.onSchedule(null);
            }
        } catch (Exception e) {
            LOGGER.warning("Error scheduling event " + e.getMessage());
            transportScheduleCallback.onSchedule(e);
        }
    }

    /* renamed from: lambda$schedule$0$com-google-android-datatransport-runtime-scheduling-DefaultScheduler */
    /* synthetic */ Object m56x8f06a4e(TransportContext transportContext, EventInternal eventInternal) {
        this.eventStore.persist(transportContext, eventInternal);
        this.workScheduler.schedule(transportContext, 1);
        return null;
    }
}
