package j$.util.concurrent;

import j$.util.Map;
import j$.util.Objects;
import j$.util.function.Consumer$$ExternalSyntheticLambda0;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/* loaded from: classes4.dex */
public interface ConcurrentMap<K, V> extends Map<K, V> {
    @Override // java.util.concurrent.ConcurrentMap, j$.util.concurrent.ConcurrentMap, j$.util.Map
    V compute(K k, BiFunction<? super K, ? super V, ? extends V> biFunction);

    @Override // java.util.concurrent.ConcurrentMap, j$.util.concurrent.ConcurrentMap, j$.util.Map
    V computeIfAbsent(K k, Function<? super K, ? extends V> function);

    @Override // java.util.concurrent.ConcurrentMap, j$.util.concurrent.ConcurrentMap, j$.util.Map
    V computeIfPresent(K k, BiFunction<? super K, ? super V, ? extends V> biFunction);

    @Override // java.util.concurrent.ConcurrentMap, j$.util.concurrent.ConcurrentMap, j$.util.Map
    void forEach(BiConsumer<? super K, ? super V> biConsumer);

    @Override // java.util.concurrent.ConcurrentMap, j$.util.concurrent.ConcurrentMap, j$.util.Map
    V getOrDefault(Object obj, V v);

    @Override // java.util.concurrent.ConcurrentMap, j$.util.concurrent.ConcurrentMap, j$.util.Map
    V merge(K k, V v, BiFunction<? super V, ? super V, ? extends V> biFunction);

    @Override // java.util.concurrent.ConcurrentMap, j$.util.concurrent.ConcurrentMap, j$.util.Map
    void replaceAll(BiFunction<? super K, ? super V, ? extends V> biFunction);

    /* renamed from: j$.util.concurrent.ConcurrentMap$-CC */
    public final /* synthetic */ class CC {
        public static Object $default$getOrDefault(java.util.concurrent.ConcurrentMap concurrentMap, Object obj, Object obj2) {
            Object obj3 = concurrentMap.get(obj);
            return obj3 != null ? obj3 : obj2;
        }

        public static void $default$forEach(java.util.concurrent.ConcurrentMap concurrentMap, BiConsumer biConsumer) {
            Objects.requireNonNull(biConsumer);
            for (Map.Entry<K, V> entry : concurrentMap.entrySet()) {
                try {
                    biConsumer.accept(entry.getKey(), entry.getValue());
                } catch (IllegalStateException unused) {
                }
            }
        }

        public static void $default$replaceAll(java.util.concurrent.ConcurrentMap concurrentMap, BiFunction biFunction) {
            Objects.requireNonNull(biFunction);
            Consumer$$ExternalSyntheticLambda0 consumer$$ExternalSyntheticLambda0 = new Consumer$$ExternalSyntheticLambda0(1, concurrentMap, biFunction);
            if (concurrentMap instanceof ConcurrentMap) {
                ((ConcurrentMap) concurrentMap).forEach(consumer$$ExternalSyntheticLambda0);
            } else {
                $default$forEach(concurrentMap, consumer$$ExternalSyntheticLambda0);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static Object $default$computeIfAbsent(java.util.concurrent.ConcurrentMap concurrentMap, Object obj, Function function) {
            Object apply;
            Objects.requireNonNull(function);
            Object obj2 = concurrentMap.get(obj);
            return (obj2 == null && (apply = function.apply(obj)) != null && (obj2 = concurrentMap.putIfAbsent(obj, apply)) == null) ? apply : obj2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static Object $default$computeIfPresent(java.util.concurrent.ConcurrentMap concurrentMap, Object obj, BiFunction biFunction) {
            Object apply;
            Objects.requireNonNull(biFunction);
            while (true) {
                Object obj2 = concurrentMap.get(obj);
                if (obj2 == null) {
                    return null;
                }
                apply = biFunction.apply(obj, obj2);
                if (apply == null) {
                    if (concurrentMap.remove(obj, obj2)) {
                        break;
                    }
                } else if (concurrentMap.replace(obj, obj2, apply)) {
                    break;
                }
            }
            return apply;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static Object $default$compute(java.util.concurrent.ConcurrentMap concurrentMap, Object obj, BiFunction biFunction) {
            Object apply;
            while (true) {
                Object obj2 = concurrentMap.get(obj);
                do {
                    apply = biFunction.apply(obj, obj2);
                    if (apply != null) {
                        if (obj2 != null) {
                            if (concurrentMap.replace(obj, obj2, apply)) {
                                return apply;
                            }
                        } else {
                            obj2 = concurrentMap.putIfAbsent(obj, apply);
                        }
                    } else if (obj2 == null || concurrentMap.remove(obj, obj2)) {
                        return null;
                    }
                } while (obj2 != null);
                return apply;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static Object $default$merge(java.util.concurrent.ConcurrentMap concurrentMap, Object obj, Object obj2, BiFunction biFunction) {
            Objects.requireNonNull(biFunction);
            Objects.requireNonNull(obj2);
            while (true) {
                Object obj3 = concurrentMap.get(obj);
                while (obj3 == null) {
                    obj3 = concurrentMap.putIfAbsent(obj, obj2);
                    if (obj3 == null) {
                        return obj2;
                    }
                }
                Object apply = biFunction.apply(obj3, obj2);
                if (apply != null) {
                    if (concurrentMap.replace(obj, obj3, apply)) {
                        return apply;
                    }
                } else if (concurrentMap.remove(obj, obj3)) {
                    return null;
                }
            }
        }
    }
}
