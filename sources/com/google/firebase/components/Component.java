package com.google.firebase.components;

import j$.util.DesugarCollections;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes2.dex */
public final class Component<T> {
    private final Set<Dependency> dependencies;
    private final ComponentFactory<T> factory;
    private final int instantiation;
    private final String name;
    private final Set<Qualified<? super T>> providedInterfaces;
    private final Set<Class<?>> publishedEvents;
    private final int type;

    static /* synthetic */ Object lambda$intoSet$3(Object obj, ComponentContainer componentContainer) {
        return obj;
    }

    static /* synthetic */ Object lambda$intoSet$4(Object obj, ComponentContainer componentContainer) {
        return obj;
    }

    static /* synthetic */ Object lambda$of$0(Object obj, ComponentContainer componentContainer) {
        return obj;
    }

    static /* synthetic */ Object lambda$of$1(Object obj, ComponentContainer componentContainer) {
        return obj;
    }

    static /* synthetic */ Object lambda$of$2(Object obj, ComponentContainer componentContainer) {
        return obj;
    }

    /* synthetic */ Component(String str, Set set, Set set2, int i, int i2, ComponentFactory componentFactory, Set set3, AnonymousClass1 anonymousClass1) {
        this(str, set, set2, i, i2, componentFactory, set3);
    }

    private Component(String str, Set<Qualified<? super T>> set, Set<Dependency> set2, int i, int i2, ComponentFactory<T> componentFactory, Set<Class<?>> set3) {
        this.name = str;
        this.providedInterfaces = DesugarCollections.unmodifiableSet(set);
        this.dependencies = DesugarCollections.unmodifiableSet(set2);
        this.instantiation = i;
        this.type = i2;
        this.factory = componentFactory;
        this.publishedEvents = DesugarCollections.unmodifiableSet(set3);
    }

    public String getName() {
        return this.name;
    }

    public Set<Qualified<? super T>> getProvidedInterfaces() {
        return this.providedInterfaces;
    }

    public Set<Dependency> getDependencies() {
        return this.dependencies;
    }

    public ComponentFactory<T> getFactory() {
        return this.factory;
    }

    public Set<Class<?>> getPublishedEvents() {
        return this.publishedEvents;
    }

    public boolean isLazy() {
        return this.instantiation == 0;
    }

    public boolean isAlwaysEager() {
        return this.instantiation == 1;
    }

    public boolean isEagerInDefaultApp() {
        return this.instantiation == 2;
    }

    public boolean isValue() {
        return this.type == 0;
    }

    public Component<T> withFactory(ComponentFactory<T> componentFactory) {
        return new Component<>(this.name, this.providedInterfaces, this.dependencies, this.instantiation, this.type, componentFactory, this.publishedEvents);
    }

    public String toString() {
        return "Component<" + Arrays.toString(this.providedInterfaces.toArray()) + ">{" + this.instantiation + ", type=" + this.type + ", deps=" + Arrays.toString(this.dependencies.toArray()) + "}";
    }

    public static <T> Builder<T> builder(Class<T> cls) {
        return new Builder<>(cls, new Class[0]);
    }

    @SafeVarargs
    public static <T> Builder<T> builder(Class<T> cls, Class<? super T>... clsArr) {
        return new Builder<>(cls, clsArr);
    }

    public static <T> Builder<T> builder(Qualified<T> qualified) {
        return new Builder<>(qualified, new Qualified[0]);
    }

    @SafeVarargs
    public static <T> Builder<T> builder(Qualified<T> qualified, Qualified<? super T>... qualifiedArr) {
        return new Builder<>(qualified, qualifiedArr);
    }

    @Deprecated
    public static <T> Component<T> of(Class<T> cls, T t) {
        return builder(cls).factory(new ComponentFactory() { // from class: com.google.firebase.components.Component$$ExternalSyntheticLambda3
            public final /* synthetic */ Object f$0;

            public /* synthetic */ Component$$ExternalSyntheticLambda3(Object t2) {
                r1 = t2;
            }

            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return Component.lambda$of$0(r1, componentContainer);
            }
        }).build();
    }

    @SafeVarargs
    public static <T> Component<T> of(T t, Class<T> cls, Class<? super T>... clsArr) {
        return builder(cls, clsArr).factory(new ComponentFactory() { // from class: com.google.firebase.components.Component$$ExternalSyntheticLambda4
            public final /* synthetic */ Object f$0;

            public /* synthetic */ Component$$ExternalSyntheticLambda4(Object t2) {
                r1 = t2;
            }

            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return Component.lambda$of$1(r1, componentContainer);
            }
        }).build();
    }

    @SafeVarargs
    public static <T> Component<T> of(T t, Qualified<T> qualified, Qualified<? super T>... qualifiedArr) {
        return builder(qualified, qualifiedArr).factory(new ComponentFactory() { // from class: com.google.firebase.components.Component$$ExternalSyntheticLambda0
            public final /* synthetic */ Object f$0;

            public /* synthetic */ Component$$ExternalSyntheticLambda0(Object t2) {
                r1 = t2;
            }

            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return Component.lambda$of$2(r1, componentContainer);
            }
        }).build();
    }

    public static <T> Builder<T> intoSetBuilder(Class<T> cls) {
        return builder(cls).intoSet();
    }

    public static <T> Builder<T> intoSetBuilder(Qualified<T> qualified) {
        return builder(qualified).intoSet();
    }

    public static <T> Component<T> intoSet(T t, Class<T> cls) {
        return intoSetBuilder(cls).factory(new ComponentFactory() { // from class: com.google.firebase.components.Component$$ExternalSyntheticLambda2
            public final /* synthetic */ Object f$0;

            public /* synthetic */ Component$$ExternalSyntheticLambda2(Object t2) {
                r1 = t2;
            }

            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return Component.lambda$intoSet$3(r1, componentContainer);
            }
        }).build();
    }

    public static <T> Component<T> intoSet(T t, Qualified<T> qualified) {
        return intoSetBuilder(qualified).factory(new ComponentFactory() { // from class: com.google.firebase.components.Component$$ExternalSyntheticLambda1
            public final /* synthetic */ Object f$0;

            public /* synthetic */ Component$$ExternalSyntheticLambda1(Object t2) {
                r1 = t2;
            }

            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return Component.lambda$intoSet$4(r1, componentContainer);
            }
        }).build();
    }

    public static class Builder<T> {
        private final Set<Dependency> dependencies;
        private ComponentFactory<T> factory;
        private int instantiation;
        private String name;
        private final Set<Qualified<? super T>> providedInterfaces;
        private final Set<Class<?>> publishedEvents;
        private int type;

        /* synthetic */ Builder(Qualified qualified, Qualified[] qualifiedArr, AnonymousClass1 anonymousClass1) {
            this(qualified, qualifiedArr);
        }

        /* synthetic */ Builder(Class cls, Class[] clsArr, AnonymousClass1 anonymousClass1) {
            this(cls, clsArr);
        }

        @SafeVarargs
        private Builder(Class<T> cls, Class<? super T>... clsArr) {
            this.name = null;
            HashSet hashSet = new HashSet();
            this.providedInterfaces = hashSet;
            this.dependencies = new HashSet();
            this.instantiation = 0;
            this.type = 0;
            this.publishedEvents = new HashSet();
            Preconditions.checkNotNull(cls, "Null interface");
            hashSet.add(Qualified.unqualified(cls));
            for (Class<? super T> cls2 : clsArr) {
                Preconditions.checkNotNull(cls2, "Null interface");
                this.providedInterfaces.add(Qualified.unqualified(cls2));
            }
        }

        @SafeVarargs
        private Builder(Qualified<T> qualified, Qualified<? super T>... qualifiedArr) {
            this.name = null;
            HashSet hashSet = new HashSet();
            this.providedInterfaces = hashSet;
            this.dependencies = new HashSet();
            this.instantiation = 0;
            this.type = 0;
            this.publishedEvents = new HashSet();
            Preconditions.checkNotNull(qualified, "Null interface");
            hashSet.add(qualified);
            for (Qualified<? super T> qualified2 : qualifiedArr) {
                Preconditions.checkNotNull(qualified2, "Null interface");
            }
            Collections.addAll(this.providedInterfaces, qualifiedArr);
        }

        public Builder<T> name(String str) {
            this.name = str;
            return this;
        }

        public Builder<T> add(Dependency dependency) {
            Preconditions.checkNotNull(dependency, "Null dependency");
            validateInterface(dependency.getInterface());
            this.dependencies.add(dependency);
            return this;
        }

        public Builder<T> alwaysEager() {
            return setInstantiation(1);
        }

        public Builder<T> eagerInDefaultApp() {
            return setInstantiation(2);
        }

        public Builder<T> publishes(Class<?> cls) {
            this.publishedEvents.add(cls);
            return this;
        }

        private Builder<T> setInstantiation(int i) {
            Preconditions.checkState(this.instantiation == 0, "Instantiation type has already been set.");
            this.instantiation = i;
            return this;
        }

        private void validateInterface(Qualified<?> qualified) {
            Preconditions.checkArgument(!this.providedInterfaces.contains(qualified), "Components are not allowed to depend on interfaces they themselves provide.");
        }

        public Builder<T> factory(ComponentFactory<T> componentFactory) {
            this.factory = (ComponentFactory) Preconditions.checkNotNull(componentFactory, "Null factory");
            return this;
        }

        public Builder<T> intoSet() {
            this.type = 1;
            return this;
        }

        public Component<T> build() {
            Preconditions.checkState(this.factory != null, "Missing required property: factory.");
            return new Component<>(this.name, new HashSet(this.providedInterfaces), new HashSet(this.dependencies), this.instantiation, this.type, this.factory, this.publishedEvents);
        }
    }
}
