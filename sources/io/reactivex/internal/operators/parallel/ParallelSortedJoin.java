package io.reactivex.internal.operators.parallel;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes2.dex */
public final class ParallelSortedJoin<T> extends Flowable<T> {
    final Comparator<? super T> comparator;
    final ParallelFlowable<List<T>> source;

    public ParallelSortedJoin(ParallelFlowable<List<T>> parallelFlowable, Comparator<? super T> comparator) {
        this.source = parallelFlowable;
        this.comparator = comparator;
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super T> subscriber) {
        SortedJoinSubscription sortedJoinSubscription = new SortedJoinSubscription(subscriber, this.source.parallelism(), this.comparator);
        subscriber.onSubscribe(sortedJoinSubscription);
        this.source.subscribe(sortedJoinSubscription.subscribers);
    }

    static final class SortedJoinSubscription<T> extends AtomicInteger implements Subscription {
        private static final long serialVersionUID = 3481980673745556697L;
        volatile boolean cancelled;
        final Comparator<? super T> comparator;
        final Subscriber<? super T> downstream;
        final int[] indexes;
        final List<T>[] lists;
        final SortedJoinInnerSubscriber<T>[] subscribers;
        final AtomicLong requested = new AtomicLong();
        final AtomicInteger remaining = new AtomicInteger();
        final AtomicReference<Throwable> error = new AtomicReference<>();

        SortedJoinSubscription(Subscriber<? super T> subscriber, int i, Comparator<? super T> comparator) {
            this.downstream = subscriber;
            this.comparator = comparator;
            SortedJoinInnerSubscriber<T>[] sortedJoinInnerSubscriberArr = new SortedJoinInnerSubscriber[i];
            for (int i2 = 0; i2 < i; i2++) {
                sortedJoinInnerSubscriberArr[i2] = new SortedJoinInnerSubscriber<>(this, i2);
            }
            this.subscribers = sortedJoinInnerSubscriberArr;
            this.lists = new List[i];
            this.indexes = new int[i];
            this.remaining.lazySet(i);
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                if (this.remaining.get() == 0) {
                    drain();
                }
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            cancelAll();
            if (getAndIncrement() == 0) {
                Arrays.fill(this.lists, (Object) null);
            }
        }

        void cancelAll() {
            for (SortedJoinInnerSubscriber<T> sortedJoinInnerSubscriber : this.subscribers) {
                sortedJoinInnerSubscriber.cancel();
            }
        }

        void innerNext(List<T> list, int i) {
            this.lists[i] = list;
            if (this.remaining.decrementAndGet() == 0) {
                drain();
            }
        }

        void innerError(Throwable th) {
            if (LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.error, null, th)) {
                drain();
            } else if (th != this.error.get()) {
                RxJavaPlugins.onError(th);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:50:0x009e, code lost:
        
            if (r11 != r7) goto L140;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x00a2, code lost:
        
            if (r16.cancelled == false) goto L129;
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x00a9, code lost:
        
            r10 = r16.error.get();
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00b2, code lost:
        
            if (r10 == null) goto L133;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x00be, code lost:
        
            if (r13 >= r4) goto L159;
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x00c8, code lost:
        
            if (r0[r13] == r3[r13].size()) goto L137;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x00cb, code lost:
        
            r13 = r13 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x00ce, code lost:
        
            java.util.Arrays.fill(r3, (java.lang.Object) null);
            r2.onComplete();
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x00d5, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x00b4, code lost:
        
            cancelAll();
            java.util.Arrays.fill(r3, (java.lang.Object) null);
            r2.onError(r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x00bd, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:67:0x00a4, code lost:
        
            java.util.Arrays.fill(r3, (java.lang.Object) null);
         */
        /* JADX WARN: Code restructure failed: missing block: B:68:0x00a8, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:70:0x00da, code lost:
        
            if (r11 == 0) goto L145;
         */
        /* JADX WARN: Code restructure failed: missing block: B:72:0x00e3, code lost:
        
            if (r7 == Long.MAX_VALUE) goto L145;
         */
        /* JADX WARN: Code restructure failed: missing block: B:73:0x00e5, code lost:
        
            r16.requested.addAndGet(-r11);
         */
        /* JADX WARN: Code restructure failed: missing block: B:74:0x00eb, code lost:
        
            r5 = get();
         */
        /* JADX WARN: Code restructure failed: missing block: B:75:0x00ef, code lost:
        
            if (r5 != r6) goto L160;
         */
        /* JADX WARN: Code restructure failed: missing block: B:76:0x00f1, code lost:
        
            r5 = addAndGet(-r6);
         */
        /* JADX WARN: Code restructure failed: missing block: B:77:0x00f6, code lost:
        
            if (r5 != 0) goto L161;
         */
        /* JADX WARN: Code restructure failed: missing block: B:79:0x00f8, code lost:
        
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void drain() {
            /*
                Method dump skipped, instructions count: 252
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.parallel.ParallelSortedJoin.SortedJoinSubscription.drain():void");
        }
    }

    static final class SortedJoinInnerSubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<List<T>> {
        private static final long serialVersionUID = 6751017204873808094L;
        final int index;
        final SortedJoinSubscription<T> parent;

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
        }

        SortedJoinInnerSubscriber(SortedJoinSubscription<T> sortedJoinSubscription, int i) {
            this.parent = sortedJoinSubscription;
            this.index = i;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            SubscriptionHelper.setOnce(this, subscription, Long.MAX_VALUE);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(List<T> list) {
            this.parent.innerNext(list, this.index);
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.parent.innerError(th);
        }

        void cancel() {
            SubscriptionHelper.cancel(this);
        }
    }
}
