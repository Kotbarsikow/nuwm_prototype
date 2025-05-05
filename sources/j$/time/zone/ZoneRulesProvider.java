package j$.time.zone;

import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public abstract class ZoneRulesProvider {
    private static final CopyOnWriteArrayList PROVIDERS;
    private static final ConcurrentHashMap ZONES;

    protected abstract ZoneRules provideRules(String str);

    protected abstract Set provideZoneIds();

    static {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        PROVIDERS = copyOnWriteArrayList;
        ZONES = new ConcurrentHashMap(512, 0.75f, 2);
        final ArrayList arrayList = new ArrayList();
        AccessController.doPrivileged(new PrivilegedAction() { // from class: j$.time.zone.ZoneRulesProvider.1
            @Override // java.security.PrivilegedAction
            public final Object run() {
                String property = System.getProperty("java.time.zone.DefaultZoneRulesProvider");
                if (property != null) {
                    try {
                        ZoneRulesProvider zoneRulesProvider = (ZoneRulesProvider) ZoneRulesProvider.class.cast(Class.forName(property, true, ZoneRulesProvider.class.getClassLoader()).newInstance());
                        ZoneRulesProvider.registerProvider(zoneRulesProvider);
                        arrayList.add(zoneRulesProvider);
                        return null;
                    } catch (Exception e) {
                        throw new Error(e);
                    }
                }
                ZoneRulesProvider.registerProvider(new TimeZoneRulesProvider());
                return null;
            }
        });
        copyOnWriteArrayList.addAll(arrayList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ZoneRules getRules(String str, boolean z) {
        Objects.requireNonNull(str, "zoneId");
        ConcurrentHashMap concurrentHashMap = ZONES;
        ZoneRulesProvider zoneRulesProvider = (ZoneRulesProvider) concurrentHashMap.get(str);
        if (zoneRulesProvider != null) {
            return zoneRulesProvider.provideRules(str);
        }
        if (concurrentHashMap.isEmpty()) {
            throw new ZoneRulesException("No time-zone data files registered");
        }
        throw new ZoneRulesException("Unknown time-zone ID: ".concat(str));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void registerProvider(ZoneRulesProvider zoneRulesProvider) {
        Objects.requireNonNull(zoneRulesProvider, "provider");
        synchronized (ZoneRulesProvider.class) {
            try {
                for (String str : zoneRulesProvider.provideZoneIds()) {
                    Objects.requireNonNull(str, "zoneId");
                    if (((ZoneRulesProvider) ZONES.putIfAbsent(str, zoneRulesProvider)) != null) {
                        throw new ZoneRulesException("Unable to register zone as one already registered with that ID: " + str + ", currently loading from provider: " + zoneRulesProvider);
                    }
                }
                Collections.unmodifiableSet(new HashSet(ZONES.keySet()));
            } catch (Throwable th) {
                throw th;
            }
        }
        PROVIDERS.add(zoneRulesProvider);
    }

    final class TimeZoneRulesProvider extends ZoneRulesProvider {
        private final Set zoneIds;

        TimeZoneRulesProvider() {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (String str : TimeZone.getAvailableIDs()) {
                linkedHashSet.add(str);
            }
            this.zoneIds = Collections.unmodifiableSet(linkedHashSet);
        }

        @Override // j$.time.zone.ZoneRulesProvider
        protected final Set provideZoneIds() {
            return this.zoneIds;
        }

        @Override // j$.time.zone.ZoneRulesProvider
        protected final ZoneRules provideRules(String str) {
            if (this.zoneIds.contains(str)) {
                return new ZoneRules(TimeZone.getTimeZone(str));
            }
            throw new ZoneRulesException("Not a built-in time zone: " + str);
        }
    }
}
