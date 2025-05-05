package j$.util.concurrent;

import j$.sun.misc.DesugarUnsafe;
import j$.util.Collection;
import j$.util.Iterator;
import j$.util.Spliterator;
import j$.util.stream.Stream;
import j$.util.stream.StreamSupport;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;

/* loaded from: classes4.dex */
public class ConcurrentHashMap<K, V> extends AbstractMap<K, V> implements java.util.concurrent.ConcurrentMap<K, V>, Serializable, ConcurrentMap<K, V> {
    private static final int ABASE;
    private static final int ASHIFT;
    private static final long BASECOUNT;
    private static final long CELLSBUSY;
    private static final long CELLVALUE;
    static final int NCPU = Runtime.getRuntime().availableProcessors();
    private static final long SIZECTL;
    private static final long TRANSFERINDEX;
    private static final DesugarUnsafe U;
    private static final ObjectStreamField[] serialPersistentFields;
    private static final long serialVersionUID = 7249069246763182397L;
    private volatile transient long baseCount;
    private volatile transient int cellsBusy;
    private volatile transient CounterCell[] counterCells;
    private transient EntrySetView entrySet;
    private transient KeySetView keySet;
    private volatile transient Node[] nextTable;
    private volatile transient int sizeCtl;
    volatile transient Node[] table;
    private volatile transient int transferIndex;
    private transient ValuesView values;

    final class Segment extends ReentrantLock implements Serializable {
        private static final long serialVersionUID = 2249069246763182397L;
    }

    final class TableStack {
        int index;
        int length;
        TableStack next;
        Node[] tab;
    }

    static final int spread(int i) {
        return (i ^ (i >>> 16)) & Integer.MAX_VALUE;
    }

    final class EntrySpliterator extends Traverser implements Spliterator {
        long est;
        final ConcurrentHashMap map;

        @Override // j$.util.Spliterator
        public final int characteristics() {
            return 4353;
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ long getExactSizeIfKnown() {
            return Iterator.EL.$default$getExactSizeIfKnown(this);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ boolean hasCharacteristics(int i) {
            return Iterator.EL.$default$hasCharacteristics(this, i);
        }

        @Override // j$.util.Spliterator
        public final Comparator getComparator() {
            throw new IllegalStateException();
        }

        EntrySpliterator(Node[] nodeArr, int i, int i2, int i3, long j, ConcurrentHashMap concurrentHashMap) {
            super(nodeArr, i, i2, i3);
            this.map = concurrentHashMap;
            this.est = j;
        }

        @Override // j$.util.Spliterator
        public final Spliterator trySplit() {
            int i = this.baseIndex;
            int i2 = this.baseLimit;
            int i3 = (i + i2) >>> 1;
            if (i3 <= i) {
                return null;
            }
            Node[] nodeArr = this.tab;
            this.baseLimit = i3;
            long j = this.est >>> 1;
            this.est = j;
            return new EntrySpliterator(nodeArr, this.baseSize, i3, i2, j, this.map);
        }

        @Override // j$.util.Spliterator
        public final void forEachRemaining(Consumer consumer) {
            consumer.getClass();
            while (true) {
                Node advance = advance();
                if (advance == null) {
                    return;
                } else {
                    consumer.accept(new MapEntry(advance.key, advance.val, this.map));
                }
            }
        }

        @Override // j$.util.Spliterator
        public final boolean tryAdvance(Consumer consumer) {
            consumer.getClass();
            Node advance = advance();
            if (advance == null) {
                return false;
            }
            consumer.accept(new MapEntry(advance.key, advance.val, this.map));
            return true;
        }

        @Override // j$.util.Spliterator
        public final long estimateSize() {
            return this.est;
        }
    }

    final class KeySpliterator extends Traverser implements Spliterator {
        public final /* synthetic */ int $r8$classId;
        long est;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ KeySpliterator(Node[] nodeArr, int i, int i2, int i3, long j, int i4) {
            super(nodeArr, i, i2, i3);
            this.$r8$classId = i4;
            this.est = j;
        }

        @Override // j$.util.Spliterator
        public final int characteristics() {
            switch (this.$r8$classId) {
                case 0:
                    return 4353;
                default:
                    return 4352;
            }
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ long getExactSizeIfKnown() {
            switch (this.$r8$classId) {
            }
            return Iterator.EL.$default$getExactSizeIfKnown(this);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ boolean hasCharacteristics(int i) {
            switch (this.$r8$classId) {
            }
            return Iterator.EL.$default$hasCharacteristics(this, i);
        }

        @Override // j$.util.Spliterator
        public final Comparator getComparator() {
            switch (this.$r8$classId) {
                case 0:
                    throw new IllegalStateException();
                default:
                    throw new IllegalStateException();
            }
        }

        @Override // j$.util.Spliterator
        public final Spliterator trySplit() {
            switch (this.$r8$classId) {
                case 0:
                    int i = this.baseIndex;
                    int i2 = this.baseLimit;
                    int i3 = (i + i2) >>> 1;
                    if (i3 <= i) {
                        return null;
                    }
                    Node[] nodeArr = this.tab;
                    this.baseLimit = i3;
                    long j = this.est >>> 1;
                    this.est = j;
                    return new KeySpliterator(nodeArr, this.baseSize, i3, i2, j, 0);
                default:
                    int i4 = this.baseIndex;
                    int i5 = this.baseLimit;
                    int i6 = (i4 + i5) >>> 1;
                    if (i6 <= i4) {
                        return null;
                    }
                    Node[] nodeArr2 = this.tab;
                    this.baseLimit = i6;
                    long j2 = this.est >>> 1;
                    this.est = j2;
                    return new KeySpliterator(nodeArr2, this.baseSize, i6, i5, j2, 1);
            }
        }

        @Override // j$.util.Spliterator
        public final void forEachRemaining(Consumer consumer) {
            switch (this.$r8$classId) {
                case 0:
                    consumer.getClass();
                    while (true) {
                        Node advance = advance();
                        if (advance == null) {
                            break;
                        } else {
                            consumer.accept(advance.key);
                        }
                    }
                default:
                    consumer.getClass();
                    while (true) {
                        Node advance2 = advance();
                        if (advance2 == null) {
                            break;
                        } else {
                            consumer.accept(advance2.val);
                        }
                    }
            }
        }

        @Override // j$.util.Spliterator
        public final boolean tryAdvance(Consumer consumer) {
            switch (this.$r8$classId) {
                case 0:
                    consumer.getClass();
                    Node advance = advance();
                    if (advance != null) {
                        consumer.accept(advance.key);
                        break;
                    }
                    break;
                default:
                    consumer.getClass();
                    Node advance2 = advance();
                    if (advance2 != null) {
                        consumer.accept(advance2.val);
                        break;
                    }
                    break;
            }
            return true;
        }

        @Override // j$.util.Spliterator
        public final long estimateSize() {
            switch (this.$r8$classId) {
            }
            return this.est;
        }
    }

    static {
        ObjectStreamField objectStreamField = new ObjectStreamField("segments", Segment[].class);
        Class cls = Integer.TYPE;
        serialPersistentFields = new ObjectStreamField[]{objectStreamField, new ObjectStreamField("segmentMask", cls), new ObjectStreamField("segmentShift", cls)};
        DesugarUnsafe unsafe = DesugarUnsafe.getUnsafe();
        U = unsafe;
        SIZECTL = unsafe.objectFieldOffset(ConcurrentHashMap.class, "sizeCtl");
        TRANSFERINDEX = unsafe.objectFieldOffset(ConcurrentHashMap.class, "transferIndex");
        BASECOUNT = unsafe.objectFieldOffset(ConcurrentHashMap.class, "baseCount");
        CELLSBUSY = unsafe.objectFieldOffset(ConcurrentHashMap.class, "cellsBusy");
        CELLVALUE = unsafe.objectFieldOffset(CounterCell.class, "value");
        ABASE = unsafe.arrayBaseOffset(Node[].class);
        int arrayIndexScale = unsafe.arrayIndexScale(Node[].class);
        if (((arrayIndexScale - 1) & arrayIndexScale) != 0) {
            throw new ExceptionInInitializerError("array index scale not a power of two");
        }
        ASHIFT = 31 - Integer.numberOfLeadingZeros(arrayIndexScale);
    }

    class Node implements Map.Entry {
        final int hash;
        final Object key;
        volatile Node next;
        volatile Object val;

        Node(int i, Object obj, Object obj2) {
            this.hash = i;
            this.key = obj;
            this.val = obj2;
        }

        Node(int i, Object obj, Object obj2, Node node) {
            this(i, obj, obj2);
            this.next = node;
        }

        @Override // java.util.Map.Entry
        public final Object getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public final Object getValue() {
            return this.val;
        }

        @Override // java.util.Map.Entry
        public final int hashCode() {
            return this.key.hashCode() ^ this.val.hashCode();
        }

        public final String toString() {
            return Helpers.mapEntryToString(this.key, this.val);
        }

        @Override // java.util.Map.Entry
        public final Object setValue(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map.Entry
        public final boolean equals(Object obj) {
            Map.Entry entry;
            Object key;
            Object value;
            Object obj2;
            Object obj3;
            return (obj instanceof Map.Entry) && (key = (entry = (Map.Entry) obj).getKey()) != null && (value = entry.getValue()) != null && (key == (obj2 = this.key) || key.equals(obj2)) && (value == (obj3 = this.val) || value.equals(obj3));
        }

        Node find(int i, Object obj) {
            Object obj2;
            if (obj == null) {
                return null;
            }
            Node node = this;
            do {
                if (node.hash == i && ((obj2 = node.key) == obj || (obj2 != null && obj.equals(obj2)))) {
                    return node;
                }
                node = node.next;
            } while (node != null);
            return null;
        }
    }

    private static final int tableSizeFor(int i) {
        int numberOfLeadingZeros = (-1) >>> Integer.numberOfLeadingZeros(i - 1);
        if (numberOfLeadingZeros < 0) {
            return 1;
        }
        if (numberOfLeadingZeros >= 1073741824) {
            return 1073741824;
        }
        return 1 + numberOfLeadingZeros;
    }

    static Class comparableClassFor(Object obj) {
        Type[] actualTypeArguments;
        if (!(obj instanceof Comparable)) {
            return null;
        }
        Class<?> cls = obj.getClass();
        if (cls == String.class) {
            return cls;
        }
        Type[] genericInterfaces = cls.getGenericInterfaces();
        if (genericInterfaces == null) {
            return null;
        }
        for (Type type : genericInterfaces) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                if (parameterizedType.getRawType() == Comparable.class && (actualTypeArguments = parameterizedType.getActualTypeArguments()) != null && actualTypeArguments.length == 1 && actualTypeArguments[0] == cls) {
                    return cls;
                }
            }
        }
        return null;
    }

    static final Node tabAt(Node[] nodeArr, int i) {
        return (Node) U.getObjectAcquire(nodeArr, (i << ASHIFT) + ABASE);
    }

    static final boolean casTabAt(Node[] nodeArr, int i, Node node) {
        return U.compareAndSetObject(nodeArr, (i << ASHIFT) + ABASE, node);
    }

    static final void setTabAt(Node[] nodeArr, int i, Node node) {
        U.putObjectRelease(nodeArr, (i << ASHIFT) + ABASE, node);
    }

    public ConcurrentHashMap() {
    }

    public ConcurrentHashMap(int i, float f, int i2) {
        if (f <= 0.0f || i < 0 || i2 <= 0) {
            throw new IllegalArgumentException();
        }
        long j = (long) (((i < i2 ? i2 : i) / f) + 1.0d);
        this.sizeCtl = j >= 1073741824 ? 1073741824 : tableSizeFor((int) j);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        long sumCount = sumCount();
        if (sumCount < 0) {
            return 0;
        }
        if (sumCount > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) sumCount;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return sumCount() <= 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x004d, code lost:
    
        return (V) r1.val;
     */
    @Override // java.util.AbstractMap, java.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public V get(java.lang.Object r5) {
        /*
            r4 = this;
            int r0 = r5.hashCode()
            int r0 = spread(r0)
            j$.util.concurrent.ConcurrentHashMap$Node[] r1 = r4.table
            r2 = 0
            if (r1 == 0) goto L4e
            int r3 = r1.length
            if (r3 <= 0) goto L4e
            int r3 = r3 + (-1)
            r3 = r3 & r0
            j$.util.concurrent.ConcurrentHashMap$Node r1 = tabAt(r1, r3)
            if (r1 == 0) goto L4e
            int r3 = r1.hash
            if (r3 != r0) goto L2c
            java.lang.Object r3 = r1.key
            if (r3 == r5) goto L29
            if (r3 == 0) goto L37
            boolean r3 = r5.equals(r3)
            if (r3 == 0) goto L37
        L29:
            java.lang.Object r5 = r1.val
            return r5
        L2c:
            if (r3 >= 0) goto L37
            j$.util.concurrent.ConcurrentHashMap$Node r5 = r1.find(r0, r5)
            if (r5 == 0) goto L36
            java.lang.Object r2 = r5.val
        L36:
            return r2
        L37:
            j$.util.concurrent.ConcurrentHashMap$Node r1 = r1.next
            if (r1 == 0) goto L4e
            int r3 = r1.hash
            if (r3 != r0) goto L37
            java.lang.Object r3 = r1.key
            if (r3 == r5) goto L4b
            if (r3 == 0) goto L37
            boolean r3 = r5.equals(r3)
            if (r3 == 0) goto L37
        L4b:
            java.lang.Object r5 = r1.val
            return r5
        L4e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.get(java.lang.Object):java.lang.Object");
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsValue(Object obj) {
        obj.getClass();
        Node[] nodeArr = this.table;
        if (nodeArr != null) {
            Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
            while (true) {
                Node advance = traverser.advance();
                if (advance == null) {
                    break;
                }
                Object obj2 = advance.val;
                if (obj2 == obj) {
                    return true;
                }
                if (obj2 != null && obj.equals(obj2)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        return (V) putVal(k, v, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:57:0x00b4, code lost:
    
        addCount(1, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00b9, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x00a5, code lost:
    
        throw new java.lang.IllegalStateException("Recursive update");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final java.lang.Object putVal(java.lang.Object r9, java.lang.Object r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 195
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.putVal(java.lang.Object, java.lang.Object, boolean):java.lang.Object");
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void putAll(Map map) {
        tryPresize(map.size());
        for (Map.Entry<K, V> entry : map.entrySet()) {
            putVal(entry.getKey(), entry.getValue(), false);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        return (V) replaceNode(obj, null, null);
    }

    final Object replaceNode(Object obj, Object obj2, Object obj3) {
        int length;
        int i;
        Node tabAt;
        boolean z;
        Object obj4;
        TreeNode findTreeNode;
        Object obj5;
        int spread = spread(obj.hashCode());
        Node[] nodeArr = this.table;
        while (true) {
            if (nodeArr == null || (length = nodeArr.length) == 0 || (tabAt = tabAt(nodeArr, (i = (length - 1) & spread))) == null) {
                break;
            }
            int i2 = tabAt.hash;
            if (i2 == -1) {
                nodeArr = helpTransfer(nodeArr, tabAt);
            } else {
                synchronized (tabAt) {
                    try {
                        if (tabAt(nodeArr, i) == tabAt) {
                            z = true;
                            if (i2 >= 0) {
                                Node node = null;
                                Node node2 = tabAt;
                                while (true) {
                                    if (node2.hash == spread && ((obj5 = node2.key) == obj || (obj5 != null && obj.equals(obj5)))) {
                                        break;
                                    }
                                    Node node3 = node2.next;
                                    if (node3 == null) {
                                        break;
                                    }
                                    node = node2;
                                    node2 = node3;
                                }
                                obj4 = node2.val;
                                if (obj3 == null || obj3 == obj4 || (obj4 != null && obj3.equals(obj4))) {
                                    if (obj2 != null) {
                                        node2.val = obj2;
                                    } else if (node != null) {
                                        node.next = node2.next;
                                    } else {
                                        setTabAt(nodeArr, i, node2.next);
                                    }
                                }
                                obj4 = null;
                            } else if (tabAt instanceof TreeBin) {
                                TreeBin treeBin = (TreeBin) tabAt;
                                TreeNode treeNode = treeBin.root;
                                if (treeNode != null && (findTreeNode = treeNode.findTreeNode(spread, obj, null)) != null) {
                                    obj4 = findTreeNode.val;
                                    if (obj3 == null || obj3 == obj4 || (obj4 != null && obj3.equals(obj4))) {
                                        if (obj2 != null) {
                                            findTreeNode.val = obj2;
                                        } else if (treeBin.removeTreeNode(findTreeNode)) {
                                            setTabAt(nodeArr, i, untreeify(treeBin.first));
                                        }
                                    }
                                }
                                obj4 = null;
                            } else if (tabAt instanceof ReservationNode) {
                                throw new IllegalStateException("Recursive update");
                            }
                        }
                        z = false;
                        obj4 = null;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                if (z) {
                    if (obj4 != null) {
                        if (obj2 == null) {
                            addCount(-1L, -1);
                        }
                        return obj4;
                    }
                }
            }
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        Node tabAt;
        Node node;
        Node[] nodeArr = this.table;
        long j = 0;
        loop0: while (true) {
            int i = 0;
            while (nodeArr != null && i < nodeArr.length) {
                tabAt = tabAt(nodeArr, i);
                if (tabAt == null) {
                    i++;
                } else {
                    int i2 = tabAt.hash;
                    if (i2 == -1) {
                        break;
                    }
                    synchronized (tabAt) {
                        try {
                            if (tabAt(nodeArr, i) == tabAt) {
                                if (i2 >= 0) {
                                    node = tabAt;
                                } else {
                                    node = tabAt instanceof TreeBin ? ((TreeBin) tabAt).first : null;
                                }
                                while (node != null) {
                                    j--;
                                    node = node.next;
                                }
                                setTabAt(nodeArr, i, null);
                                i++;
                            }
                        } finally {
                        }
                    }
                }
            }
            nodeArr = helpTransfer(nodeArr, tabAt);
        }
        if (j != 0) {
            addCount(j, -1);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        KeySetView keySetView = this.keySet;
        if (keySetView != null) {
            return keySetView;
        }
        KeySetView keySetView2 = new KeySetView(this);
        this.keySet = keySetView2;
        return keySetView2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        ValuesView valuesView = this.values;
        if (valuesView != null) {
            return valuesView;
        }
        ValuesView valuesView2 = new ValuesView(this);
        this.values = valuesView2;
        return valuesView2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        EntrySetView entrySetView = this.entrySet;
        if (entrySetView != null) {
            return entrySetView;
        }
        EntrySetView entrySetView2 = new EntrySetView(this);
        this.entrySet = entrySetView2;
        return entrySetView2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        Node[] nodeArr = this.table;
        int i = 0;
        if (nodeArr != null) {
            Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
            while (true) {
                Node advance = traverser.advance();
                if (advance == null) {
                    break;
                }
                i += advance.val.hashCode() ^ advance.key.hashCode();
            }
        }
        return i;
    }

    @Override // java.util.AbstractMap
    public String toString() {
        Node[] nodeArr = this.table;
        int length = nodeArr == null ? 0 : nodeArr.length;
        Traverser traverser = new Traverser(nodeArr, length, 0, length);
        StringBuilder sb = new StringBuilder("{");
        Node advance = traverser.advance();
        if (advance != null) {
            while (true) {
                Object obj = advance.key;
                Object obj2 = advance.val;
                if (obj == this) {
                    obj = "(this Map)";
                }
                sb.append(obj);
                sb.append('=');
                if (obj2 == this) {
                    obj2 = "(this Map)";
                }
                sb.append(obj2);
                advance = traverser.advance();
                if (advance == null) {
                    break;
                }
                sb.append(", ");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        V value;
        V v;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        Node[] nodeArr = this.table;
        int length = nodeArr == null ? 0 : nodeArr.length;
        Traverser traverser = new Traverser(nodeArr, length, 0, length);
        while (true) {
            Node advance = traverser.advance();
            if (advance != null) {
                Object obj2 = advance.val;
                Object obj3 = map.get(advance.key);
                if (obj3 == null || (obj3 != obj2 && !obj3.equals(obj2))) {
                    break;
                }
            } else {
                for (Map.Entry<K, V> entry : map.entrySet()) {
                    K key = entry.getKey();
                    if (key == null || (value = entry.getValue()) == null || (v = get(key)) == null || (value != v && !value.equals(v))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        int i = 1;
        int i2 = 0;
        while (i < 16) {
            i2++;
            i <<= 1;
        }
        int i3 = 32 - i2;
        int i4 = i - 1;
        Segment[] segmentArr = new Segment[16];
        for (int i5 = 0; i5 < 16; i5++) {
            segmentArr[i5] = new Segment();
        }
        ObjectOutputStream.PutField putFields = objectOutputStream.putFields();
        putFields.put("segments", segmentArr);
        putFields.put("segmentShift", i3);
        putFields.put("segmentMask", i4);
        objectOutputStream.writeFields();
        Node[] nodeArr = this.table;
        if (nodeArr != null) {
            Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
            while (true) {
                Node advance = traverser.advance();
                if (advance == null) {
                    break;
                }
                objectOutputStream.writeObject(advance.key);
                objectOutputStream.writeObject(advance.val);
            }
        }
        objectOutputStream.writeObject(null);
        objectOutputStream.writeObject(null);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        long j;
        boolean z;
        boolean z2;
        Object obj;
        this.sizeCtl = -1;
        objectInputStream.defaultReadObject();
        long j2 = 0;
        long j3 = 0;
        Node node = null;
        while (true) {
            Object readObject = objectInputStream.readObject();
            Object readObject2 = objectInputStream.readObject();
            j = 1;
            if (readObject == null || readObject2 == null) {
                break;
            }
            j3++;
            node = new Node(spread(readObject.hashCode()), readObject, readObject2, node);
        }
        if (j3 == 0) {
            this.sizeCtl = 0;
            return;
        }
        long j4 = (long) ((j3 / 0.75f) + 1.0d);
        int tableSizeFor = j4 >= 1073741824 ? 1073741824 : tableSizeFor((int) j4);
        Node[] nodeArr = new Node[tableSizeFor];
        int i = tableSizeFor - 1;
        while (node != null) {
            Node node2 = node.next;
            int i2 = node.hash;
            int i3 = i2 & i;
            Node tabAt = tabAt(nodeArr, i3);
            if (tabAt == null) {
                z2 = true;
            } else {
                Object obj2 = node.key;
                if (tabAt.hash >= 0) {
                    int i4 = 0;
                    for (Node node3 = tabAt; node3 != null; node3 = node3.next) {
                        if (node3.hash == i2 && ((obj = node3.key) == obj2 || (obj != null && obj2.equals(obj)))) {
                            z = false;
                            break;
                        }
                        i4++;
                    }
                    z = true;
                    if (!z || i4 < 8) {
                        z2 = z;
                    } else {
                        long j5 = j2 + 1;
                        node.next = tabAt;
                        Node node4 = node;
                        TreeNode treeNode = null;
                        TreeNode treeNode2 = null;
                        while (node4 != null) {
                            long j6 = j5;
                            TreeNode treeNode3 = new TreeNode(node4.hash, node4.key, node4.val, null, null);
                            treeNode3.prev = treeNode2;
                            if (treeNode2 == null) {
                                treeNode = treeNode3;
                            } else {
                                treeNode2.next = treeNode3;
                            }
                            node4 = node4.next;
                            treeNode2 = treeNode3;
                            j5 = j6;
                        }
                        setTabAt(nodeArr, i3, new TreeBin(treeNode));
                        j2 = j5;
                    }
                } else if (((TreeBin) tabAt).putTreeVal(i2, obj2, node.val) == null) {
                    j2 += j;
                }
                z2 = false;
            }
            j = 1;
            if (z2) {
                j2++;
                node.next = tabAt;
                setTabAt(nodeArr, i3, node);
            }
            node = node2;
        }
        this.table = nodeArr;
        this.sizeCtl = tableSizeFor - (tableSizeFor >>> 2);
        this.baseCount = j2;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap, j$.util.Map
    public V putIfAbsent(K k, V v) {
        return (V) putVal(k, v, true);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap, j$.util.Map
    public boolean remove(Object obj, Object obj2) {
        obj.getClass();
        return (obj2 == null || replaceNode(obj, null, obj2) == null) ? false : true;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap, j$.util.Map
    public boolean replace(K k, V v, V v2) {
        if (k == null || v == null || v2 == null) {
            throw null;
        }
        return replaceNode(k, v2, v) != null;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap, j$.util.Map
    public final Object replace(Object obj, Object obj2) {
        if (obj == null) {
            throw null;
        }
        if (obj2 == null) {
            throw null;
        }
        return replaceNode(obj, obj2, null);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap, j$.util.concurrent.ConcurrentMap, j$.util.Map
    public final Object getOrDefault(Object obj, Object obj2) {
        V v = get(obj);
        return v == null ? obj2 : v;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap, j$.util.concurrent.ConcurrentMap, j$.util.Map
    public final void forEach(BiConsumer biConsumer) {
        biConsumer.getClass();
        Node[] nodeArr = this.table;
        if (nodeArr == null) {
            return;
        }
        Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
        while (true) {
            Node advance = traverser.advance();
            if (advance == null) {
                return;
            } else {
                biConsumer.accept(advance.key, advance.val);
            }
        }
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap, j$.util.concurrent.ConcurrentMap, j$.util.Map
    public final void replaceAll(BiFunction biFunction) {
        biFunction.getClass();
        Node[] nodeArr = this.table;
        if (nodeArr == null) {
            return;
        }
        Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
        while (true) {
            Node advance = traverser.advance();
            if (advance == null) {
                return;
            }
            Object obj = advance.val;
            Object obj2 = advance.key;
            do {
                Object apply = biFunction.apply(obj2, obj);
                apply.getClass();
                if (replaceNode(obj2, apply, obj) == null) {
                    obj = get(obj2);
                }
            } while (obj != null);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x00f0, code lost:
    
        if (r5 == null) goto L229;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00f2, code lost:
    
        addCount(1, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00f7, code lost:
    
        return r5;
     */
    @Override // java.util.Map, java.util.concurrent.ConcurrentMap, j$.util.concurrent.ConcurrentMap, j$.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object computeIfAbsent(java.lang.Object r12, java.util.function.Function r13) {
        /*
            Method dump skipped, instructions count: 257
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.computeIfAbsent(java.lang.Object, java.util.function.Function):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x00aa, code lost:
    
        throw new java.lang.IllegalStateException("Recursive update");
     */
    @Override // java.util.Map, java.util.concurrent.ConcurrentMap, j$.util.concurrent.ConcurrentMap, j$.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object computeIfPresent(java.lang.Object r14, java.util.function.BiFunction r15) {
        /*
            r13 = this;
            r0 = 0
            if (r14 == 0) goto Lbd
            if (r15 == 0) goto Lbd
            int r1 = r14.hashCode()
            int r1 = spread(r1)
            j$.util.concurrent.ConcurrentHashMap$Node[] r2 = r13.table
            r3 = 0
            r5 = r0
            r4 = 0
        L12:
            if (r2 == 0) goto Lb7
            int r6 = r2.length
            if (r6 != 0) goto L19
            goto Lb7
        L19:
            int r6 = r6 + (-1)
            r6 = r6 & r1
            j$.util.concurrent.ConcurrentHashMap$Node r7 = tabAt(r2, r6)
            if (r7 != 0) goto L24
            goto Lae
        L24:
            int r8 = r7.hash
            r9 = -1
            if (r8 != r9) goto L2e
            j$.util.concurrent.ConcurrentHashMap$Node[] r2 = r13.helpTransfer(r2, r7)
            goto L12
        L2e:
            monitor-enter(r7)
            j$.util.concurrent.ConcurrentHashMap$Node r10 = tabAt(r2, r6)     // Catch: java.lang.Throwable -> L4b
            if (r10 != r7) goto Lab
            if (r8 < 0) goto L70
            r4 = 1
            r10 = r0
            r8 = r7
        L3a:
            int r11 = r8.hash     // Catch: java.lang.Throwable -> L4b
            if (r11 != r1) goto L65
            java.lang.Object r11 = r8.key     // Catch: java.lang.Throwable -> L4b
            if (r11 == r14) goto L4e
            if (r11 == 0) goto L65
            boolean r11 = r14.equals(r11)     // Catch: java.lang.Throwable -> L4b
            if (r11 == 0) goto L65
            goto L4e
        L4b:
            r14 = move-exception
            goto Lb5
        L4e:
            java.lang.Object r5 = r8.val     // Catch: java.lang.Throwable -> L4b
            java.lang.Object r5 = r15.apply(r14, r5)     // Catch: java.lang.Throwable -> L4b
            if (r5 == 0) goto L59
            r8.val = r5     // Catch: java.lang.Throwable -> L4b
            goto Lab
        L59:
            j$.util.concurrent.ConcurrentHashMap$Node r3 = r8.next     // Catch: java.lang.Throwable -> L4b
            if (r10 == 0) goto L60
            r10.next = r3     // Catch: java.lang.Throwable -> L4b
            goto L63
        L60:
            setTabAt(r2, r6, r3)     // Catch: java.lang.Throwable -> L4b
        L63:
            r3 = -1
            goto Lab
        L65:
            j$.util.concurrent.ConcurrentHashMap$Node r10 = r8.next     // Catch: java.lang.Throwable -> L4b
            if (r10 != 0) goto L6a
            goto Lab
        L6a:
            int r4 = r4 + 1
            r12 = r10
            r10 = r8
            r8 = r12
            goto L3a
        L70:
            boolean r8 = r7 instanceof j$.util.concurrent.ConcurrentHashMap.TreeBin     // Catch: java.lang.Throwable -> L4b
            if (r8 == 0) goto L9e
            r4 = r7
            j$.util.concurrent.ConcurrentHashMap$TreeBin r4 = (j$.util.concurrent.ConcurrentHashMap.TreeBin) r4     // Catch: java.lang.Throwable -> L4b
            j$.util.concurrent.ConcurrentHashMap$TreeNode r8 = r4.root     // Catch: java.lang.Throwable -> L4b
            if (r8 == 0) goto L9c
            j$.util.concurrent.ConcurrentHashMap$TreeNode r8 = r8.findTreeNode(r1, r14, r0)     // Catch: java.lang.Throwable -> L4b
            if (r8 == 0) goto L9c
            java.lang.Object r5 = r8.val     // Catch: java.lang.Throwable -> L4b
            java.lang.Object r5 = r15.apply(r14, r5)     // Catch: java.lang.Throwable -> L4b
            if (r5 == 0) goto L8c
            r8.val = r5     // Catch: java.lang.Throwable -> L4b
            goto L9c
        L8c:
            boolean r3 = r4.removeTreeNode(r8)     // Catch: java.lang.Throwable -> L4b
            if (r3 == 0) goto L9b
            j$.util.concurrent.ConcurrentHashMap$TreeNode r3 = r4.first     // Catch: java.lang.Throwable -> L4b
            j$.util.concurrent.ConcurrentHashMap$Node r3 = untreeify(r3)     // Catch: java.lang.Throwable -> L4b
            setTabAt(r2, r6, r3)     // Catch: java.lang.Throwable -> L4b
        L9b:
            r3 = -1
        L9c:
            r4 = 2
            goto Lab
        L9e:
            boolean r6 = r7 instanceof j$.util.concurrent.ConcurrentHashMap.ReservationNode     // Catch: java.lang.Throwable -> L4b
            if (r6 != 0) goto La3
            goto Lab
        La3:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L4b
            java.lang.String r15 = "Recursive update"
            r14.<init>(r15)     // Catch: java.lang.Throwable -> L4b
            throw r14     // Catch: java.lang.Throwable -> L4b
        Lab:
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L4b
            if (r4 == 0) goto L12
        Lae:
            if (r3 == 0) goto Lb4
            long r14 = (long) r3
            r13.addCount(r14, r4)
        Lb4:
            return r5
        Lb5:
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L4b
            throw r14
        Lb7:
            j$.util.concurrent.ConcurrentHashMap$Node[] r2 = r13.initTable()
            goto L12
        Lbd:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.computeIfPresent(java.lang.Object, java.util.function.BiFunction):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:89:0x0112, code lost:
    
        if (r4 == 0) goto L234;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0114, code lost:
    
        addCount(r4, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0118, code lost:
    
        return r5;
     */
    @Override // java.util.Map, java.util.concurrent.ConcurrentMap, j$.util.concurrent.ConcurrentMap, j$.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object compute(java.lang.Object r14, java.util.function.BiFunction r15) {
        /*
            Method dump skipped, instructions count: 290
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.compute(java.lang.Object, java.util.function.BiFunction):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:73:0x00dd, code lost:
    
        throw new java.lang.IllegalStateException("Recursive update");
     */
    @Override // java.util.Map, java.util.concurrent.ConcurrentMap, j$.util.concurrent.ConcurrentMap, j$.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object merge(java.lang.Object r18, java.lang.Object r19, java.util.function.BiFunction r20) {
        /*
            Method dump skipped, instructions count: 250
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.merge(java.lang.Object, java.lang.Object, java.util.function.BiFunction):java.lang.Object");
    }

    abstract class CollectionView implements Collection, Serializable {
        private static final long serialVersionUID = 7249069246763182397L;
        final ConcurrentHashMap map;

        @Override // java.util.Collection
        public abstract boolean contains(Object obj);

        @Override // java.util.Collection, java.lang.Iterable
        public abstract java.util.Iterator iterator();

        @Override // java.util.Collection
        public abstract boolean remove(Object obj);

        @Override // java.util.Collection
        public final Object[] toArray() {
            long sumCount = this.map.sumCount();
            if (sumCount < 0) {
                sumCount = 0;
            }
            if (sumCount > 2147483639) {
                throw new OutOfMemoryError("Required array size too large");
            }
            int i = (int) sumCount;
            Object[] objArr = new Object[i];
            java.util.Iterator it = iterator();
            int i2 = 0;
            while (it.hasNext()) {
                Object next = it.next();
                if (i2 == i) {
                    if (i >= 2147483639) {
                        throw new OutOfMemoryError("Required array size too large");
                    }
                    int i3 = i < 1073741819 ? (i >>> 1) + 1 + i : 2147483639;
                    objArr = Arrays.copyOf(objArr, i3);
                    i = i3;
                }
                objArr[i2] = next;
                i2++;
            }
            return i2 == i ? objArr : Arrays.copyOf(objArr, i2);
        }

        @Override // java.util.Collection
        public final Object[] toArray(Object[] objArr) {
            long sumCount = this.map.sumCount();
            if (sumCount < 0) {
                sumCount = 0;
            }
            if (sumCount > 2147483639) {
                throw new OutOfMemoryError("Required array size too large");
            }
            int i = (int) sumCount;
            Object[] objArr2 = objArr.length >= i ? objArr : (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i);
            int length = objArr2.length;
            java.util.Iterator it = iterator();
            int i2 = 0;
            while (it.hasNext()) {
                Object next = it.next();
                if (i2 == length) {
                    if (length >= 2147483639) {
                        throw new OutOfMemoryError("Required array size too large");
                    }
                    int i3 = length < 1073741819 ? (length >>> 1) + 1 + length : 2147483639;
                    objArr2 = Arrays.copyOf(objArr2, i3);
                    length = i3;
                }
                objArr2[i2] = next;
                i2++;
            }
            if (objArr != objArr2 || i2 >= length) {
                return i2 == length ? objArr2 : Arrays.copyOf(objArr2, i2);
            }
            objArr2[i2] = null;
            return objArr2;
        }

        CollectionView(ConcurrentHashMap concurrentHashMap) {
            this.map = concurrentHashMap;
        }

        @Override // java.util.Collection
        public final void clear() {
            this.map.clear();
        }

        @Override // java.util.Collection
        public final int size() {
            return this.map.size();
        }

        @Override // java.util.Collection
        public final boolean isEmpty() {
            return this.map.isEmpty();
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("[");
            java.util.Iterator it = iterator();
            if (it.hasNext()) {
                while (true) {
                    Object next = it.next();
                    if (next == this) {
                        next = "(this Collection)";
                    }
                    sb.append(next);
                    if (!it.hasNext()) {
                        break;
                    }
                    sb.append(", ");
                }
            }
            sb.append(']');
            return sb.toString();
        }

        @Override // java.util.Collection
        public final boolean containsAll(Collection collection) {
            if (collection == this) {
                return true;
            }
            for (Object obj : collection) {
                if (obj == null || !contains(obj)) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Collection
        public boolean removeAll(Collection collection) {
            collection.getClass();
            Node[] nodeArr = this.map.table;
            boolean z = false;
            if (nodeArr == null) {
                return false;
            }
            if ((collection instanceof Set) && collection.size() > nodeArr.length) {
                java.util.Iterator it = iterator();
                while (it.hasNext()) {
                    if (collection.contains(it.next())) {
                        it.remove();
                        z = true;
                    }
                }
            } else {
                java.util.Iterator it2 = collection.iterator();
                while (it2.hasNext()) {
                    z |= remove(it2.next());
                }
            }
            return z;
        }

        @Override // java.util.Collection
        public final boolean retainAll(Collection collection) {
            collection.getClass();
            java.util.Iterator it = iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (!collection.contains(it.next())) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }
    }

    final class ForwardingNode extends Node {
        final Node[] nextTable;

        ForwardingNode(Node[] nodeArr) {
            super(-1, null, null);
            this.nextTable = nodeArr;
        }

        @Override // j$.util.concurrent.ConcurrentHashMap.Node
        final Node find(int i, Object obj) {
            int length;
            Node tabAt;
            Object obj2;
            Node[] nodeArr = this.nextTable;
            loop0: while (obj != null && nodeArr != null && (length = nodeArr.length) != 0 && (tabAt = ConcurrentHashMap.tabAt(nodeArr, (length - 1) & i)) != null) {
                do {
                    int i2 = tabAt.hash;
                    if (i2 == i && ((obj2 = tabAt.key) == obj || (obj2 != null && obj.equals(obj2)))) {
                        return tabAt;
                    }
                    if (i2 < 0) {
                        if (tabAt instanceof ForwardingNode) {
                            nodeArr = ((ForwardingNode) tabAt).nextTable;
                        } else {
                            return tabAt.find(i, obj);
                        }
                    } else {
                        tabAt = tabAt.next;
                    }
                } while (tabAt != null);
            }
            return null;
        }
    }

    final class ReservationNode extends Node {
        @Override // j$.util.concurrent.ConcurrentHashMap.Node
        final Node find(int i, Object obj) {
            return null;
        }

        ReservationNode() {
            super(-3, null, null);
        }
    }

    private final Node[] initTable() {
        while (true) {
            Node[] nodeArr = this.table;
            if (nodeArr != null && nodeArr.length != 0) {
                return nodeArr;
            }
            int i = this.sizeCtl;
            if (i < 0) {
                Thread.yield();
            } else if (U.compareAndSetInt(this, SIZECTL, i, -1)) {
                try {
                    Node[] nodeArr2 = this.table;
                    if (nodeArr2 != null) {
                        if (nodeArr2.length == 0) {
                        }
                        this.sizeCtl = i;
                        return nodeArr2;
                    }
                    int i2 = i > 0 ? i : 16;
                    Node[] nodeArr3 = new Node[i2];
                    this.table = nodeArr3;
                    i = i2 - (i2 >>> 2);
                    nodeArr2 = nodeArr3;
                    this.sizeCtl = i;
                    return nodeArr2;
                } catch (Throwable th) {
                    this.sizeCtl = i;
                    throw th;
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:155:0x013f, code lost:
    
        if (r25.counterCells != r7) goto L328;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x0141, code lost:
    
        r25.counterCells = (j$.util.concurrent.ConcurrentHashMap.CounterCell[]) java.util.Arrays.copyOf(r7, r8 << 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x001a, code lost:
    
        if (r1.compareAndSetLong(r25, r3, r5, r14) == false) goto L184;
     */
    /* JADX WARN: Removed duplicated region for block: B:124:0x019f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x00ba A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void addCount(long r26, int r28) {
        /*
            Method dump skipped, instructions count: 416
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.addCount(long, int):void");
    }

    final Node[] helpTransfer(Node[] nodeArr, Node node) {
        int i;
        if (node instanceof ForwardingNode) {
            Node[] nodeArr2 = ((ForwardingNode) node).nextTable;
            int numberOfLeadingZeros = Integer.numberOfLeadingZeros(nodeArr.length) | 32768;
            while (true) {
                if (nodeArr2 != this.nextTable || this.table != nodeArr || (i = this.sizeCtl) >= 0 || (i >>> 16) != numberOfLeadingZeros || i == numberOfLeadingZeros + 1 || i == 65535 + numberOfLeadingZeros || this.transferIndex <= 0) {
                    break;
                }
                if (U.compareAndSetInt(this, SIZECTL, i, i + 1)) {
                    transfer(nodeArr, nodeArr2);
                    break;
                }
            }
            return nodeArr2;
        }
        return this.table;
    }

    private final void tryPresize(int i) {
        int length;
        int tableSizeFor = i >= 536870912 ? 1073741824 : tableSizeFor(i + (i >>> 1) + 1);
        while (true) {
            int i2 = this.sizeCtl;
            if (i2 < 0) {
                return;
            }
            Node[] nodeArr = this.table;
            if (nodeArr == null || (length = nodeArr.length) == 0) {
                int i3 = i2 > tableSizeFor ? i2 : tableSizeFor;
                if (U.compareAndSetInt(this, SIZECTL, i2, -1)) {
                    try {
                        if (this.table == nodeArr) {
                            this.table = new Node[i3];
                            i2 = i3 - (i3 >>> 2);
                        }
                    } finally {
                        this.sizeCtl = i2;
                    }
                } else {
                    continue;
                }
            } else {
                if (tableSizeFor <= i2 || length >= 1073741824) {
                    return;
                }
                if (nodeArr == this.table) {
                    if (U.compareAndSetInt(this, SIZECTL, i2, ((Integer.numberOfLeadingZeros(length) | 32768) << 16) + 2)) {
                        transfer(nodeArr, null);
                    }
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v10, types: [j$.util.concurrent.ConcurrentHashMap$Node] */
    /* JADX WARN: Type inference failed for: r13v12, types: [j$.util.concurrent.ConcurrentHashMap$Node] */
    /* JADX WARN: Type inference failed for: r4v0, types: [j$.util.concurrent.ConcurrentHashMap$Node] */
    /* JADX WARN: Type inference failed for: r5v17, types: [j$.util.concurrent.ConcurrentHashMap$Node] */
    /* JADX WARN: Type inference failed for: r5v22, types: [j$.util.concurrent.ConcurrentHashMap$Node] */
    private final void transfer(Node[] nodeArr, Node[] nodeArr2) {
        Node[] nodeArr3;
        int i;
        int i2;
        ForwardingNode forwardingNode;
        ConcurrentHashMap<K, V> concurrentHashMap;
        int i3;
        Node treeBin;
        Node treeBin2;
        TreeNode treeNode;
        int i4;
        ConcurrentHashMap<K, V> concurrentHashMap2 = this;
        Node[] nodeArr4 = nodeArr;
        int length = nodeArr4.length;
        int i5 = NCPU;
        int i6 = i5 > 1 ? (length >>> 3) / i5 : length;
        int i7 = i6 < 16 ? 16 : i6;
        if (nodeArr2 == null) {
            try {
                Node[] nodeArr5 = new Node[length << 1];
                concurrentHashMap2.nextTable = nodeArr5;
                concurrentHashMap2.transferIndex = length;
                nodeArr3 = nodeArr5;
            } catch (Throwable unused) {
                concurrentHashMap2.sizeCtl = Integer.MAX_VALUE;
                return;
            }
        } else {
            nodeArr3 = nodeArr2;
        }
        int length2 = nodeArr3.length;
        ForwardingNode forwardingNode2 = new ForwardingNode(nodeArr3);
        int i8 = 0;
        int i9 = 0;
        boolean z = true;
        boolean z2 = false;
        while (true) {
            if (z) {
                int i10 = i9 - 1;
                if (i10 >= i8 || z2) {
                    i8 = i8;
                    i9 = i10;
                } else {
                    int i11 = concurrentHashMap2.transferIndex;
                    if (i11 <= 0) {
                        i9 = -1;
                    } else {
                        DesugarUnsafe desugarUnsafe = U;
                        long j = TRANSFERINDEX;
                        int i12 = i11 > i7 ? i11 - i7 : 0;
                        int i13 = i8;
                        if (desugarUnsafe.compareAndSetInt(this, j, i11, i12)) {
                            i9 = i11 - 1;
                            i8 = i12;
                        } else {
                            i8 = i13;
                            i9 = i10;
                        }
                    }
                }
                z = false;
            } else {
                int i14 = i8;
                TreeNode treeNode2 = null;
                if (i9 < 0 || i9 >= length || (i3 = i9 + length) >= length2) {
                    i = i7;
                    i2 = length2;
                    forwardingNode = forwardingNode2;
                    if (z2) {
                        this.nextTable = null;
                        this.table = nodeArr3;
                        this.sizeCtl = (length << 1) - (length >>> 1);
                        return;
                    }
                    concurrentHashMap = this;
                    DesugarUnsafe desugarUnsafe2 = U;
                    long j2 = SIZECTL;
                    int i15 = concurrentHashMap.sizeCtl;
                    int i16 = i9;
                    if (!desugarUnsafe2.compareAndSetInt(this, j2, i15, i15 - 1)) {
                        i9 = i16;
                    } else {
                        if (i15 - 2 != ((Integer.numberOfLeadingZeros(length) | 32768) << 16)) {
                            return;
                        }
                        i9 = length;
                        z = true;
                        z2 = true;
                    }
                } else {
                    ?? tabAt = tabAt(nodeArr4, i9);
                    if (tabAt == 0) {
                        z = casTabAt(nodeArr4, i9, forwardingNode2);
                        concurrentHashMap = concurrentHashMap2;
                        i = i7;
                        i2 = length2;
                        forwardingNode = forwardingNode2;
                    } else {
                        int i17 = tabAt.hash;
                        if (i17 == -1) {
                            concurrentHashMap = concurrentHashMap2;
                            i = i7;
                            i2 = length2;
                            forwardingNode = forwardingNode2;
                            z = true;
                        } else {
                            synchronized (tabAt) {
                                try {
                                    if (tabAt(nodeArr4, i9) == tabAt) {
                                        if (i17 >= 0) {
                                            int i18 = i17 & length;
                                            TreeNode treeNode3 = tabAt;
                                            for (TreeNode treeNode4 = tabAt.next; treeNode4 != null; treeNode4 = treeNode4.next) {
                                                int i19 = treeNode4.hash & length;
                                                if (i19 != i18) {
                                                    treeNode3 = treeNode4;
                                                    i18 = i19;
                                                }
                                            }
                                            if (i18 == 0) {
                                                treeNode = null;
                                                treeNode2 = treeNode3;
                                            } else {
                                                treeNode = treeNode3;
                                            }
                                            Node node = tabAt;
                                            while (node != treeNode3) {
                                                int i20 = node.hash;
                                                Object obj = node.key;
                                                int i21 = i7;
                                                Object obj2 = node.val;
                                                if ((i20 & length) == 0) {
                                                    i4 = length2;
                                                    treeNode2 = new Node(i20, obj, obj2, treeNode2);
                                                } else {
                                                    i4 = length2;
                                                    treeNode = new Node(i20, obj, obj2, treeNode);
                                                }
                                                node = node.next;
                                                i7 = i21;
                                                length2 = i4;
                                            }
                                            i = i7;
                                            i2 = length2;
                                            setTabAt(nodeArr3, i9, treeNode2);
                                            setTabAt(nodeArr3, i3, treeNode);
                                            setTabAt(nodeArr4, i9, forwardingNode2);
                                            forwardingNode = forwardingNode2;
                                        } else {
                                            i = i7;
                                            i2 = length2;
                                            if (tabAt instanceof TreeBin) {
                                                TreeBin treeBin3 = (TreeBin) tabAt;
                                                TreeNode treeNode5 = null;
                                                TreeNode treeNode6 = null;
                                                Node node2 = treeBin3.first;
                                                int i22 = 0;
                                                int i23 = 0;
                                                TreeNode treeNode7 = null;
                                                while (node2 != null) {
                                                    TreeBin treeBin4 = treeBin3;
                                                    int i24 = node2.hash;
                                                    ForwardingNode forwardingNode3 = forwardingNode2;
                                                    TreeNode treeNode8 = new TreeNode(i24, node2.key, node2.val, null, null);
                                                    if ((i24 & length) == 0) {
                                                        treeNode8.prev = treeNode6;
                                                        if (treeNode6 == null) {
                                                            treeNode2 = treeNode8;
                                                        } else {
                                                            treeNode6.next = treeNode8;
                                                        }
                                                        i22++;
                                                        treeNode6 = treeNode8;
                                                    } else {
                                                        treeNode8.prev = treeNode5;
                                                        if (treeNode5 == null) {
                                                            treeNode7 = treeNode8;
                                                        } else {
                                                            treeNode5.next = treeNode8;
                                                        }
                                                        i23++;
                                                        treeNode5 = treeNode8;
                                                    }
                                                    node2 = node2.next;
                                                    treeBin3 = treeBin4;
                                                    forwardingNode2 = forwardingNode3;
                                                }
                                                TreeBin treeBin5 = treeBin3;
                                                ForwardingNode forwardingNode4 = forwardingNode2;
                                                if (i22 <= 6) {
                                                    treeBin = untreeify(treeNode2);
                                                } else {
                                                    treeBin = i23 != 0 ? new TreeBin(treeNode2) : treeBin5;
                                                }
                                                if (i23 <= 6) {
                                                    treeBin2 = untreeify(treeNode7);
                                                } else {
                                                    treeBin2 = i22 != 0 ? new TreeBin(treeNode7) : treeBin5;
                                                }
                                                setTabAt(nodeArr3, i9, treeBin);
                                                setTabAt(nodeArr3, i3, treeBin2);
                                                nodeArr4 = nodeArr;
                                                forwardingNode = forwardingNode4;
                                                setTabAt(nodeArr4, i9, forwardingNode);
                                            }
                                        }
                                        z = true;
                                    } else {
                                        i = i7;
                                        i2 = length2;
                                    }
                                    forwardingNode = forwardingNode2;
                                } finally {
                                }
                            }
                            concurrentHashMap = this;
                        }
                    }
                }
                forwardingNode2 = forwardingNode;
                concurrentHashMap2 = concurrentHashMap;
                i8 = i14;
                i7 = i;
                length2 = i2;
            }
        }
    }

    final class CounterCell {
        volatile long value;

        CounterCell(long j) {
            this.value = j;
        }
    }

    final long sumCount() {
        CounterCell[] counterCellArr = this.counterCells;
        long j = this.baseCount;
        if (counterCellArr != null) {
            for (CounterCell counterCell : counterCellArr) {
                if (counterCell != null) {
                    j += counterCell.value;
                }
            }
        }
        return j;
    }

    private final void treeifyBin(Node[] nodeArr, int i) {
        int length = nodeArr.length;
        if (length < 64) {
            tryPresize(length << 1);
            return;
        }
        Node tabAt = tabAt(nodeArr, i);
        if (tabAt == null || tabAt.hash < 0) {
            return;
        }
        synchronized (tabAt) {
            try {
                if (tabAt(nodeArr, i) == tabAt) {
                    TreeNode treeNode = null;
                    Node node = tabAt;
                    TreeNode treeNode2 = null;
                    while (node != null) {
                        TreeNode treeNode3 = new TreeNode(node.hash, node.key, node.val, null, null);
                        treeNode3.prev = treeNode2;
                        if (treeNode2 == null) {
                            treeNode = treeNode3;
                        } else {
                            treeNode2.next = treeNode3;
                        }
                        node = node.next;
                        treeNode2 = treeNode3;
                    }
                    setTabAt(nodeArr, i, new TreeBin(treeNode));
                }
            } finally {
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v2, types: [j$.util.concurrent.ConcurrentHashMap$Node] */
    static Node untreeify(TreeNode treeNode) {
        Node node = null;
        Node node2 = null;
        for (TreeNode treeNode2 = treeNode; treeNode2 != null; treeNode2 = treeNode2.next) {
            Node node3 = new Node(treeNode2.hash, treeNode2.key, treeNode2.val);
            if (node2 == null) {
                node = node3;
            } else {
                node2.next = node3;
            }
            node2 = node3;
        }
        return node;
    }

    final class TreeNode extends Node {
        TreeNode left;
        TreeNode parent;
        TreeNode prev;
        boolean red;
        TreeNode right;

        TreeNode(int i, Object obj, Object obj2, Node node, TreeNode treeNode) {
            super(i, obj, obj2, node);
            this.parent = treeNode;
        }

        @Override // j$.util.concurrent.ConcurrentHashMap.Node
        final Node find(int i, Object obj) {
            return findTreeNode(i, obj, null);
        }

        final TreeNode findTreeNode(int i, Object obj, Class cls) {
            if (obj == null) {
                return null;
            }
            TreeNode treeNode = this;
            do {
                TreeNode treeNode2 = treeNode.left;
                TreeNode treeNode3 = treeNode.right;
                int i2 = treeNode.hash;
                if (i2 <= i) {
                    if (i2 >= i) {
                        Object obj2 = treeNode.key;
                        if (obj2 == obj || (obj2 != null && obj.equals(obj2))) {
                            return treeNode;
                        }
                        if (treeNode2 != null) {
                            if (treeNode3 != null) {
                                if (cls != null || (cls = ConcurrentHashMap.comparableClassFor(obj)) != null) {
                                    int i3 = ConcurrentHashMap.NCPU;
                                    int compareTo = (obj2 == null || obj2.getClass() != cls) ? 0 : ((Comparable) obj).compareTo(obj2);
                                    if (compareTo != 0) {
                                        if (compareTo >= 0) {
                                            treeNode2 = treeNode3;
                                        }
                                    }
                                }
                                TreeNode findTreeNode = treeNode3.findTreeNode(i, obj, cls);
                                if (findTreeNode != null) {
                                    return findTreeNode;
                                }
                            }
                        }
                    }
                    treeNode = treeNode3;
                }
                treeNode = treeNode2;
            } while (treeNode != null);
            return null;
        }
    }

    final class TreeBin extends Node {
        private static final long LOCKSTATE;
        private static final DesugarUnsafe U;
        volatile TreeNode first;
        volatile int lockState;
        TreeNode root;
        volatile Thread waiter;

        static int tieBreakOrder(Object obj, Object obj2) {
            int compareTo;
            return (obj == null || obj2 == null || (compareTo = obj.getClass().getName().compareTo(obj2.getClass().getName())) == 0) ? System.identityHashCode(obj) <= System.identityHashCode(obj2) ? -1 : 1 : compareTo;
        }

        TreeBin(TreeNode treeNode) {
            super(-2, null, null);
            int i;
            this.first = treeNode;
            TreeNode treeNode2 = null;
            while (treeNode != null) {
                TreeNode treeNode3 = (TreeNode) treeNode.next;
                treeNode.right = null;
                treeNode.left = null;
                if (treeNode2 == null) {
                    treeNode.parent = null;
                    treeNode.red = false;
                } else {
                    Object obj = treeNode.key;
                    int i2 = treeNode.hash;
                    TreeNode treeNode4 = treeNode2;
                    Class<?> cls = null;
                    while (true) {
                        Object obj2 = treeNode4.key;
                        int i3 = treeNode4.hash;
                        if (i3 > i2) {
                            i = -1;
                        } else if (i3 < i2) {
                            i = 1;
                        } else {
                            if (cls != null || (cls = ConcurrentHashMap.comparableClassFor(obj)) != null) {
                                int i4 = ConcurrentHashMap.NCPU;
                                int compareTo = (obj2 == null || obj2.getClass() != cls) ? 0 : ((Comparable) obj).compareTo(obj2);
                                if (compareTo != 0) {
                                    i = compareTo;
                                }
                            }
                            i = tieBreakOrder(obj, obj2);
                        }
                        TreeNode treeNode5 = i <= 0 ? treeNode4.left : treeNode4.right;
                        if (treeNode5 == null) {
                            break;
                        } else {
                            treeNode4 = treeNode5;
                        }
                    }
                    treeNode.parent = treeNode4;
                    if (i <= 0) {
                        treeNode4.left = treeNode;
                    } else {
                        treeNode4.right = treeNode;
                    }
                    treeNode = balanceInsertion(treeNode2, treeNode);
                }
                treeNode2 = treeNode;
                treeNode = treeNode3;
            }
            this.root = treeNode2;
        }

        private final void lockRoot() {
            if (U.compareAndSetInt(this, LOCKSTATE, 0, 1)) {
                return;
            }
            boolean z = false;
            while (true) {
                int i = this.lockState;
                if ((i & (-3)) == 0) {
                    if (U.compareAndSetInt(this, LOCKSTATE, i, 1)) {
                        break;
                    }
                } else if ((i & 2) == 0) {
                    if (U.compareAndSetInt(this, LOCKSTATE, i, i | 2)) {
                        this.waiter = Thread.currentThread();
                        z = true;
                    }
                } else if (z) {
                    LockSupport.park(this);
                }
            }
            if (z) {
                this.waiter = null;
            }
        }

        @Override // j$.util.concurrent.ConcurrentHashMap.Node
        final Node find(int i, Object obj) {
            Object obj2;
            Thread thread;
            Thread thread2;
            TreeNode treeNode = null;
            if (obj != null) {
                Node node = this.first;
                while (node != null) {
                    int i2 = this.lockState;
                    if ((i2 & 3) != 0) {
                        if (node.hash == i && ((obj2 = node.key) == obj || (obj2 != null && obj.equals(obj2)))) {
                            return node;
                        }
                        node = node.next;
                    } else {
                        DesugarUnsafe desugarUnsafe = U;
                        long j = LOCKSTATE;
                        if (desugarUnsafe.compareAndSetInt(this, j, i2, i2 + 4)) {
                            try {
                                TreeNode treeNode2 = this.root;
                                if (treeNode2 != null) {
                                    treeNode = treeNode2.findTreeNode(i, obj, null);
                                }
                                if (desugarUnsafe.getAndAddInt(this, j) == 6 && (thread2 = this.waiter) != null) {
                                    LockSupport.unpark(thread2);
                                }
                                return treeNode;
                            } catch (Throwable th) {
                                if (U.getAndAddInt(this, LOCKSTATE) == 6 && (thread = this.waiter) != null) {
                                    LockSupport.unpark(thread);
                                }
                                throw th;
                            }
                        }
                    }
                }
            }
            return null;
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x00b3, code lost:
        
            return null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0070, code lost:
        
            return r3;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        final j$.util.concurrent.ConcurrentHashMap.TreeNode putTreeVal(int r16, java.lang.Object r17, java.lang.Object r18) {
            /*
                Method dump skipped, instructions count: 188
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.TreeBin.putTreeVal(int, java.lang.Object, java.lang.Object):j$.util.concurrent.ConcurrentHashMap$TreeNode");
        }

        /* JADX WARN: Removed duplicated region for block: B:41:0x0091 A[Catch: all -> 0x0052, TryCatch #0 {all -> 0x0052, blocks: (B:21:0x0030, B:25:0x0039, B:29:0x003f, B:31:0x004d, B:32:0x0068, B:34:0x006e, B:35:0x0070, B:41:0x0091, B:44:0x00a2, B:45:0x0099, B:47:0x009d, B:48:0x00a0, B:49:0x00a8, B:52:0x00b1, B:54:0x00b5, B:56:0x00b9, B:58:0x00bd, B:59:0x00c6, B:61:0x00c0, B:63:0x00c4, B:66:0x00ad, B:68:0x007a, B:70:0x007e, B:71:0x0081, B:72:0x0055, B:74:0x005b, B:76:0x005f, B:77:0x0062, B:78:0x0064), top: B:20:0x0030 }] */
        /* JADX WARN: Removed duplicated region for block: B:51:0x00ac  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x00b5 A[Catch: all -> 0x0052, TryCatch #0 {all -> 0x0052, blocks: (B:21:0x0030, B:25:0x0039, B:29:0x003f, B:31:0x004d, B:32:0x0068, B:34:0x006e, B:35:0x0070, B:41:0x0091, B:44:0x00a2, B:45:0x0099, B:47:0x009d, B:48:0x00a0, B:49:0x00a8, B:52:0x00b1, B:54:0x00b5, B:56:0x00b9, B:58:0x00bd, B:59:0x00c6, B:61:0x00c0, B:63:0x00c4, B:66:0x00ad, B:68:0x007a, B:70:0x007e, B:71:0x0081, B:72:0x0055, B:74:0x005b, B:76:0x005f, B:77:0x0062, B:78:0x0064), top: B:20:0x0030 }] */
        /* JADX WARN: Removed duplicated region for block: B:58:0x00bd A[Catch: all -> 0x0052, TryCatch #0 {all -> 0x0052, blocks: (B:21:0x0030, B:25:0x0039, B:29:0x003f, B:31:0x004d, B:32:0x0068, B:34:0x006e, B:35:0x0070, B:41:0x0091, B:44:0x00a2, B:45:0x0099, B:47:0x009d, B:48:0x00a0, B:49:0x00a8, B:52:0x00b1, B:54:0x00b5, B:56:0x00b9, B:58:0x00bd, B:59:0x00c6, B:61:0x00c0, B:63:0x00c4, B:66:0x00ad, B:68:0x007a, B:70:0x007e, B:71:0x0081, B:72:0x0055, B:74:0x005b, B:76:0x005f, B:77:0x0062, B:78:0x0064), top: B:20:0x0030 }] */
        /* JADX WARN: Removed duplicated region for block: B:61:0x00c0 A[Catch: all -> 0x0052, TryCatch #0 {all -> 0x0052, blocks: (B:21:0x0030, B:25:0x0039, B:29:0x003f, B:31:0x004d, B:32:0x0068, B:34:0x006e, B:35:0x0070, B:41:0x0091, B:44:0x00a2, B:45:0x0099, B:47:0x009d, B:48:0x00a0, B:49:0x00a8, B:52:0x00b1, B:54:0x00b5, B:56:0x00b9, B:58:0x00bd, B:59:0x00c6, B:61:0x00c0, B:63:0x00c4, B:66:0x00ad, B:68:0x007a, B:70:0x007e, B:71:0x0081, B:72:0x0055, B:74:0x005b, B:76:0x005f, B:77:0x0062, B:78:0x0064), top: B:20:0x0030 }] */
        /* JADX WARN: Removed duplicated region for block: B:66:0x00ad A[Catch: all -> 0x0052, TryCatch #0 {all -> 0x0052, blocks: (B:21:0x0030, B:25:0x0039, B:29:0x003f, B:31:0x004d, B:32:0x0068, B:34:0x006e, B:35:0x0070, B:41:0x0091, B:44:0x00a2, B:45:0x0099, B:47:0x009d, B:48:0x00a0, B:49:0x00a8, B:52:0x00b1, B:54:0x00b5, B:56:0x00b9, B:58:0x00bd, B:59:0x00c6, B:61:0x00c0, B:63:0x00c4, B:66:0x00ad, B:68:0x007a, B:70:0x007e, B:71:0x0081, B:72:0x0055, B:74:0x005b, B:76:0x005f, B:77:0x0062, B:78:0x0064), top: B:20:0x0030 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        final boolean removeTreeNode(j$.util.concurrent.ConcurrentHashMap.TreeNode r11) {
            /*
                Method dump skipped, instructions count: 207
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.ConcurrentHashMap.TreeBin.removeTreeNode(j$.util.concurrent.ConcurrentHashMap$TreeNode):boolean");
        }

        static TreeNode rotateLeft(TreeNode treeNode, TreeNode treeNode2) {
            TreeNode treeNode3;
            if (treeNode2 != null && (treeNode3 = treeNode2.right) != null) {
                TreeNode treeNode4 = treeNode3.left;
                treeNode2.right = treeNode4;
                if (treeNode4 != null) {
                    treeNode4.parent = treeNode2;
                }
                TreeNode treeNode5 = treeNode2.parent;
                treeNode3.parent = treeNode5;
                if (treeNode5 == null) {
                    treeNode3.red = false;
                    treeNode = treeNode3;
                } else if (treeNode5.left == treeNode2) {
                    treeNode5.left = treeNode3;
                } else {
                    treeNode5.right = treeNode3;
                }
                treeNode3.left = treeNode2;
                treeNode2.parent = treeNode3;
            }
            return treeNode;
        }

        static TreeNode rotateRight(TreeNode treeNode, TreeNode treeNode2) {
            TreeNode treeNode3;
            if (treeNode2 != null && (treeNode3 = treeNode2.left) != null) {
                TreeNode treeNode4 = treeNode3.right;
                treeNode2.left = treeNode4;
                if (treeNode4 != null) {
                    treeNode4.parent = treeNode2;
                }
                TreeNode treeNode5 = treeNode2.parent;
                treeNode3.parent = treeNode5;
                if (treeNode5 == null) {
                    treeNode3.red = false;
                    treeNode = treeNode3;
                } else if (treeNode5.right == treeNode2) {
                    treeNode5.right = treeNode3;
                } else {
                    treeNode5.left = treeNode3;
                }
                treeNode3.right = treeNode2;
                treeNode2.parent = treeNode3;
            }
            return treeNode;
        }

        static TreeNode balanceInsertion(TreeNode treeNode, TreeNode treeNode2) {
            TreeNode treeNode3;
            treeNode2.red = true;
            while (true) {
                TreeNode treeNode4 = treeNode2.parent;
                if (treeNode4 == null) {
                    treeNode2.red = false;
                    return treeNode2;
                }
                if (!treeNode4.red || (treeNode3 = treeNode4.parent) == null) {
                    break;
                }
                TreeNode treeNode5 = treeNode3.left;
                if (treeNode4 == treeNode5) {
                    TreeNode treeNode6 = treeNode3.right;
                    if (treeNode6 != null && treeNode6.red) {
                        treeNode6.red = false;
                        treeNode4.red = false;
                        treeNode3.red = true;
                        treeNode2 = treeNode3;
                    } else {
                        if (treeNode2 == treeNode4.right) {
                            treeNode = rotateLeft(treeNode, treeNode4);
                            TreeNode treeNode7 = treeNode4.parent;
                            treeNode3 = treeNode7 == null ? null : treeNode7.parent;
                            treeNode4 = treeNode7;
                            treeNode2 = treeNode4;
                        }
                        if (treeNode4 != null) {
                            treeNode4.red = false;
                            if (treeNode3 != null) {
                                treeNode3.red = true;
                                treeNode = rotateRight(treeNode, treeNode3);
                            }
                        }
                    }
                } else if (treeNode5 != null && treeNode5.red) {
                    treeNode5.red = false;
                    treeNode4.red = false;
                    treeNode3.red = true;
                    treeNode2 = treeNode3;
                } else {
                    if (treeNode2 == treeNode4.left) {
                        treeNode = rotateRight(treeNode, treeNode4);
                        TreeNode treeNode8 = treeNode4.parent;
                        treeNode3 = treeNode8 == null ? null : treeNode8.parent;
                        treeNode4 = treeNode8;
                        treeNode2 = treeNode4;
                    }
                    if (treeNode4 != null) {
                        treeNode4.red = false;
                        if (treeNode3 != null) {
                            treeNode3.red = true;
                            treeNode = rotateLeft(treeNode, treeNode3);
                        }
                    }
                }
            }
            return treeNode;
        }

        static TreeNode balanceDeletion(TreeNode treeNode, TreeNode treeNode2) {
            while (treeNode2 != null && treeNode2 != treeNode) {
                TreeNode treeNode3 = treeNode2.parent;
                if (treeNode3 == null) {
                    treeNode2.red = false;
                    return treeNode2;
                }
                if (treeNode2.red) {
                    treeNode2.red = false;
                    return treeNode;
                }
                TreeNode treeNode4 = treeNode3.left;
                if (treeNode4 == treeNode2) {
                    TreeNode treeNode5 = treeNode3.right;
                    if (treeNode5 != null && treeNode5.red) {
                        treeNode5.red = false;
                        treeNode3.red = true;
                        treeNode = rotateLeft(treeNode, treeNode3);
                        treeNode3 = treeNode2.parent;
                        treeNode5 = treeNode3 == null ? null : treeNode3.right;
                    }
                    if (treeNode5 != null) {
                        TreeNode treeNode6 = treeNode5.left;
                        TreeNode treeNode7 = treeNode5.right;
                        if ((treeNode7 == null || !treeNode7.red) && (treeNode6 == null || !treeNode6.red)) {
                            treeNode5.red = true;
                        } else {
                            if (treeNode7 == null || !treeNode7.red) {
                                if (treeNode6 != null) {
                                    treeNode6.red = false;
                                }
                                treeNode5.red = true;
                                treeNode = rotateRight(treeNode, treeNode5);
                                treeNode3 = treeNode2.parent;
                                treeNode5 = treeNode3 != null ? treeNode3.right : null;
                            }
                            if (treeNode5 != null) {
                                treeNode5.red = treeNode3 == null ? false : treeNode3.red;
                                TreeNode treeNode8 = treeNode5.right;
                                if (treeNode8 != null) {
                                    treeNode8.red = false;
                                }
                            }
                            if (treeNode3 != null) {
                                treeNode3.red = false;
                                treeNode = rotateLeft(treeNode, treeNode3);
                            }
                            treeNode2 = treeNode;
                        }
                    }
                    treeNode2 = treeNode3;
                } else {
                    if (treeNode4 != null && treeNode4.red) {
                        treeNode4.red = false;
                        treeNode3.red = true;
                        treeNode = rotateRight(treeNode, treeNode3);
                        treeNode3 = treeNode2.parent;
                        treeNode4 = treeNode3 == null ? null : treeNode3.left;
                    }
                    if (treeNode4 != null) {
                        TreeNode treeNode9 = treeNode4.left;
                        TreeNode treeNode10 = treeNode4.right;
                        if ((treeNode9 == null || !treeNode9.red) && (treeNode10 == null || !treeNode10.red)) {
                            treeNode4.red = true;
                        } else {
                            if (treeNode9 == null || !treeNode9.red) {
                                if (treeNode10 != null) {
                                    treeNode10.red = false;
                                }
                                treeNode4.red = true;
                                treeNode = rotateLeft(treeNode, treeNode4);
                                treeNode3 = treeNode2.parent;
                                treeNode4 = treeNode3 != null ? treeNode3.left : null;
                            }
                            if (treeNode4 != null) {
                                treeNode4.red = treeNode3 == null ? false : treeNode3.red;
                                TreeNode treeNode11 = treeNode4.left;
                                if (treeNode11 != null) {
                                    treeNode11.red = false;
                                }
                            }
                            if (treeNode3 != null) {
                                treeNode3.red = false;
                                treeNode = rotateRight(treeNode, treeNode3);
                            }
                            treeNode2 = treeNode;
                        }
                    }
                    treeNode2 = treeNode3;
                }
            }
            return treeNode;
        }

        static {
            DesugarUnsafe unsafe = DesugarUnsafe.getUnsafe();
            U = unsafe;
            LOCKSTATE = unsafe.objectFieldOffset(TreeBin.class, "lockState");
        }
    }

    class Traverser {
        int baseIndex;
        int baseLimit;
        final int baseSize;
        int index;
        Node next = null;
        TableStack spare;
        TableStack stack;
        Node[] tab;

        Traverser(Node[] nodeArr, int i, int i2, int i3) {
            this.tab = nodeArr;
            this.baseSize = i;
            this.index = i2;
            this.baseIndex = i2;
            this.baseLimit = i3;
        }

        final Node advance() {
            Node[] nodeArr;
            int length;
            int i;
            TableStack tableStack;
            Node node = this.next;
            if (node != null) {
                node = node.next;
            }
            while (node == null) {
                if (this.baseIndex >= this.baseLimit || (nodeArr = this.tab) == null || (length = nodeArr.length) <= (i = this.index) || i < 0) {
                    this.next = null;
                    return null;
                }
                Node tabAt = ConcurrentHashMap.tabAt(nodeArr, i);
                if (tabAt == null || tabAt.hash >= 0) {
                    node = tabAt;
                } else if (tabAt instanceof ForwardingNode) {
                    this.tab = ((ForwardingNode) tabAt).nextTable;
                    TableStack tableStack2 = this.spare;
                    if (tableStack2 == null) {
                        tableStack2 = new TableStack();
                    } else {
                        this.spare = tableStack2.next;
                    }
                    tableStack2.tab = nodeArr;
                    tableStack2.length = length;
                    tableStack2.index = i;
                    tableStack2.next = this.stack;
                    this.stack = tableStack2;
                    node = null;
                } else {
                    node = tabAt instanceof TreeBin ? ((TreeBin) tabAt).first : null;
                }
                if (this.stack != null) {
                    while (true) {
                        tableStack = this.stack;
                        if (tableStack == null) {
                            break;
                        }
                        int i2 = this.index;
                        int i3 = tableStack.length;
                        int i4 = i2 + i3;
                        this.index = i4;
                        if (i4 < length) {
                            break;
                        }
                        this.index = tableStack.index;
                        this.tab = tableStack.tab;
                        tableStack.tab = null;
                        TableStack tableStack3 = tableStack.next;
                        tableStack.next = this.spare;
                        this.stack = tableStack3;
                        this.spare = tableStack;
                        length = i3;
                    }
                    if (tableStack == null) {
                        int i5 = this.index + this.baseSize;
                        this.index = i5;
                        if (i5 >= length) {
                            int i6 = this.baseIndex + 1;
                            this.baseIndex = i6;
                            this.index = i6;
                        }
                    }
                } else {
                    int i7 = i + this.baseSize;
                    this.index = i7;
                    if (i7 >= length) {
                        int i8 = this.baseIndex + 1;
                        this.baseIndex = i8;
                        this.index = i8;
                    }
                }
            }
            this.next = node;
            return node;
        }
    }

    abstract class BaseIterator extends Traverser {
        Node lastReturned;
        final ConcurrentHashMap map;

        BaseIterator(Node[] nodeArr, int i, int i2, ConcurrentHashMap concurrentHashMap) {
            super(nodeArr, i, 0, i2);
            this.map = concurrentHashMap;
            advance();
        }

        public final boolean hasNext() {
            return this.next != null;
        }

        public final boolean hasMoreElements() {
            return this.next != null;
        }

        public final void remove() {
            Node node = this.lastReturned;
            if (node == null) {
                throw new IllegalStateException();
            }
            this.lastReturned = null;
            this.map.replaceNode(node.key, null, null);
        }
    }

    final class KeyIterator extends BaseIterator implements java.util.Iterator, Enumeration {
        public final /* synthetic */ int $r8$classId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ KeyIterator(Node[] nodeArr, int i, int i2, ConcurrentHashMap concurrentHashMap, int i3) {
            super(nodeArr, i, i2, concurrentHashMap);
            this.$r8$classId = i3;
        }

        @Override // java.util.Iterator
        public final Object next() {
            switch (this.$r8$classId) {
                case 0:
                    Node node = this.next;
                    if (node == null) {
                        throw new NoSuchElementException();
                    }
                    this.lastReturned = node;
                    advance();
                    return node.key;
                default:
                    Node node2 = this.next;
                    if (node2 == null) {
                        throw new NoSuchElementException();
                    }
                    Object obj = node2.val;
                    this.lastReturned = node2;
                    advance();
                    return obj;
            }
        }

        @Override // java.util.Enumeration
        public final Object nextElement() {
            switch (this.$r8$classId) {
            }
            return next();
        }
    }

    final class EntryIterator extends BaseIterator implements java.util.Iterator {
        @Override // java.util.Iterator
        public final Object next() {
            Node node = this.next;
            if (node == null) {
                throw new NoSuchElementException();
            }
            Object obj = node.key;
            Object obj2 = node.val;
            this.lastReturned = node;
            advance();
            return new MapEntry(obj, obj2, this.map);
        }
    }

    final class MapEntry implements Map.Entry {
        final Object key;
        final ConcurrentHashMap map;
        Object val;

        MapEntry(Object obj, Object obj2, ConcurrentHashMap concurrentHashMap) {
            this.key = obj;
            this.val = obj2;
            this.map = concurrentHashMap;
        }

        @Override // java.util.Map.Entry
        public final Object getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public final Object getValue() {
            return this.val;
        }

        @Override // java.util.Map.Entry
        public final int hashCode() {
            return this.key.hashCode() ^ this.val.hashCode();
        }

        public final String toString() {
            return Helpers.mapEntryToString(this.key, this.val);
        }

        @Override // java.util.Map.Entry
        public final boolean equals(Object obj) {
            Map.Entry entry;
            Object key;
            Object value;
            Object obj2;
            Object obj3;
            return (obj instanceof Map.Entry) && (key = (entry = (Map.Entry) obj).getKey()) != null && (value = entry.getValue()) != null && (key == (obj2 = this.key) || key.equals(obj2)) && (value == (obj3 = this.val) || value.equals(obj3));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Map.Entry
        public final Object setValue(Object obj) {
            obj.getClass();
            Object obj2 = this.val;
            this.val = obj;
            this.map.put(this.key, obj);
            return obj2;
        }
    }

    public final class KeySetView extends CollectionView implements Set, j$.util.Collection {
        private static final long serialVersionUID = 7249069246763182397L;

        @Override // java.util.Collection, j$.util.Collection
        public final /* synthetic */ Stream parallelStream() {
            Stream stream;
            stream = StreamSupport.stream(Iterator.EL.spliterator(this), true);
            return stream;
        }

        @Override // java.util.Collection
        public final /* synthetic */ java.util.stream.Stream parallelStream() {
            Stream stream;
            stream = StreamSupport.stream(Iterator.EL.spliterator(this), true);
            return Stream.Wrapper.convert(stream);
        }

        @Override // java.util.Collection, j$.util.Collection
        public final /* synthetic */ boolean removeIf(Predicate predicate) {
            return Collection.CC.$default$removeIf(this, predicate);
        }

        @Override // java.util.Collection, java.lang.Iterable, java.util.Set
        public final /* synthetic */ java.util.Spliterator spliterator() {
            return Spliterator.Wrapper.convert(spliterator());
        }

        @Override // java.util.Collection, j$.util.Collection
        public final /* synthetic */ Stream stream() {
            Stream stream;
            stream = StreamSupport.stream(Iterator.EL.spliterator(this), false);
            return stream;
        }

        @Override // java.util.Collection
        public final /* synthetic */ java.util.stream.Stream stream() {
            Stream stream;
            stream = StreamSupport.stream(Iterator.EL.spliterator(this), false);
            return Stream.Wrapper.convert(stream);
        }

        @Override // java.util.Collection, j$.util.Collection
        public final /* synthetic */ Object[] toArray(IntFunction intFunction) {
            Object[] array;
            array = toArray((Object[]) intFunction.apply(0));
            return array;
        }

        @Override // j$.util.concurrent.ConcurrentHashMap.CollectionView, java.util.Collection
        public final boolean contains(Object obj) {
            return this.map.containsKey(obj);
        }

        @Override // j$.util.concurrent.ConcurrentHashMap.CollectionView, java.util.Collection
        public final boolean remove(Object obj) {
            return this.map.remove(obj) != null;
        }

        @Override // j$.util.concurrent.ConcurrentHashMap.CollectionView, java.util.Collection, java.lang.Iterable
        public final java.util.Iterator iterator() {
            ConcurrentHashMap concurrentHashMap = this.map;
            Node[] nodeArr = concurrentHashMap.table;
            int length = nodeArr == null ? 0 : nodeArr.length;
            return new KeyIterator(nodeArr, length, length, concurrentHashMap, 0);
        }

        @Override // java.util.Collection, java.util.Set
        public final boolean add(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection, java.util.Set
        public final boolean addAll(java.util.Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection, java.util.Set
        public final int hashCode() {
            Object it = iterator();
            int i = 0;
            while (((BaseIterator) it).hasNext()) {
                i += ((KeyIterator) it).next().hashCode();
            }
            return i;
        }

        @Override // java.util.Collection, java.util.Set
        public final boolean equals(Object obj) {
            Set set;
            return (obj instanceof Set) && ((set = (Set) obj) == this || (containsAll(set) && set.containsAll(this)));
        }

        @Override // java.util.Collection, java.lang.Iterable, java.util.Set, j$.util.Collection
        public final Spliterator spliterator() {
            ConcurrentHashMap concurrentHashMap = this.map;
            long sumCount = concurrentHashMap.sumCount();
            Node[] nodeArr = concurrentHashMap.table;
            int length = nodeArr == null ? 0 : nodeArr.length;
            return new KeySpliterator(nodeArr, length, 0, length, sumCount < 0 ? 0L : sumCount, 0);
        }

        @Override // java.lang.Iterable, j$.util.Collection
        public final void forEach(Consumer consumer) {
            consumer.getClass();
            Node[] nodeArr = this.map.table;
            if (nodeArr == null) {
                return;
            }
            Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
            while (true) {
                Node advance = traverser.advance();
                if (advance == null) {
                    return;
                } else {
                    consumer.accept(advance.key);
                }
            }
        }
    }

    final class ValuesView extends CollectionView implements j$.util.Collection {
        private static final long serialVersionUID = 2249069246763182397L;

        @Override // java.util.Collection, j$.util.Collection
        public final /* synthetic */ Stream parallelStream() {
            Stream stream;
            stream = StreamSupport.stream(Iterator.EL.spliterator(this), true);
            return stream;
        }

        @Override // java.util.Collection
        public final /* synthetic */ java.util.stream.Stream parallelStream() {
            Stream stream;
            stream = StreamSupport.stream(Iterator.EL.spliterator(this), true);
            return Stream.Wrapper.convert(stream);
        }

        @Override // java.util.Collection, java.lang.Iterable
        public final /* synthetic */ java.util.Spliterator spliterator() {
            return Spliterator.Wrapper.convert(spliterator());
        }

        @Override // java.util.Collection, j$.util.Collection
        public final /* synthetic */ Stream stream() {
            Stream stream;
            stream = StreamSupport.stream(Iterator.EL.spliterator(this), false);
            return stream;
        }

        @Override // java.util.Collection
        public final /* synthetic */ java.util.stream.Stream stream() {
            Stream stream;
            stream = StreamSupport.stream(Iterator.EL.spliterator(this), false);
            return Stream.Wrapper.convert(stream);
        }

        @Override // java.util.Collection, j$.util.Collection
        public final /* synthetic */ Object[] toArray(IntFunction intFunction) {
            Object[] array;
            array = toArray((Object[]) intFunction.apply(0));
            return array;
        }

        @Override // j$.util.concurrent.ConcurrentHashMap.CollectionView, java.util.Collection
        public final boolean contains(Object obj) {
            return this.map.containsValue(obj);
        }

        @Override // j$.util.concurrent.ConcurrentHashMap.CollectionView, java.util.Collection
        public final boolean remove(Object obj) {
            BaseIterator baseIterator;
            if (obj == null) {
                return false;
            }
            Object it = iterator();
            do {
                baseIterator = (BaseIterator) it;
                if (!baseIterator.hasNext()) {
                    return false;
                }
            } while (!obj.equals(((KeyIterator) it).next()));
            baseIterator.remove();
            return true;
        }

        @Override // j$.util.concurrent.ConcurrentHashMap.CollectionView, java.util.Collection, java.lang.Iterable
        public final java.util.Iterator iterator() {
            ConcurrentHashMap concurrentHashMap = this.map;
            Node[] nodeArr = concurrentHashMap.table;
            int length = nodeArr == null ? 0 : nodeArr.length;
            return new KeyIterator(nodeArr, length, length, concurrentHashMap, 1);
        }

        @Override // java.util.Collection
        public final boolean add(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public final boolean addAll(java.util.Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // j$.util.concurrent.ConcurrentHashMap.CollectionView, java.util.Collection
        public final boolean removeAll(java.util.Collection collection) {
            collection.getClass();
            Object it = iterator();
            boolean z = false;
            while (true) {
                BaseIterator baseIterator = (BaseIterator) it;
                if (!baseIterator.hasNext()) {
                    return z;
                }
                if (collection.contains(((KeyIterator) it).next())) {
                    baseIterator.remove();
                    z = true;
                }
            }
        }

        @Override // java.util.Collection, j$.util.Collection
        public final boolean removeIf(Predicate predicate) {
            ConcurrentHashMap concurrentHashMap = this.map;
            concurrentHashMap.getClass();
            predicate.getClass();
            Node[] nodeArr = concurrentHashMap.table;
            boolean z = false;
            if (nodeArr != null) {
                Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
                while (true) {
                    Node advance = traverser.advance();
                    if (advance == null) {
                        break;
                    }
                    Object obj = advance.key;
                    Object obj2 = advance.val;
                    if (predicate.test(obj2) && concurrentHashMap.replaceNode(obj, null, obj2) != null) {
                        z = true;
                    }
                }
            }
            return z;
        }

        @Override // java.util.Collection, java.lang.Iterable, j$.util.Collection
        public final Spliterator spliterator() {
            ConcurrentHashMap concurrentHashMap = this.map;
            long sumCount = concurrentHashMap.sumCount();
            Node[] nodeArr = concurrentHashMap.table;
            int length = nodeArr == null ? 0 : nodeArr.length;
            return new KeySpliterator(nodeArr, length, 0, length, sumCount < 0 ? 0L : sumCount, 1);
        }

        @Override // java.lang.Iterable, j$.util.Collection
        public final void forEach(Consumer consumer) {
            consumer.getClass();
            Node[] nodeArr = this.map.table;
            if (nodeArr == null) {
                return;
            }
            Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
            while (true) {
                Node advance = traverser.advance();
                if (advance == null) {
                    return;
                } else {
                    consumer.accept(advance.val);
                }
            }
        }
    }

    final class EntrySetView extends CollectionView implements Set, j$.util.Collection {
        private static final long serialVersionUID = 2249069246763182397L;

        @Override // java.util.Collection, j$.util.Collection
        public final /* synthetic */ Stream parallelStream() {
            Stream stream;
            stream = StreamSupport.stream(Iterator.EL.spliterator(this), true);
            return stream;
        }

        @Override // java.util.Collection
        public final /* synthetic */ java.util.stream.Stream parallelStream() {
            Stream stream;
            stream = StreamSupport.stream(Iterator.EL.spliterator(this), true);
            return Stream.Wrapper.convert(stream);
        }

        @Override // java.util.Collection, java.lang.Iterable, java.util.Set
        public final /* synthetic */ java.util.Spliterator spliterator() {
            return Spliterator.Wrapper.convert(spliterator());
        }

        @Override // java.util.Collection, j$.util.Collection
        public final /* synthetic */ Stream stream() {
            Stream stream;
            stream = StreamSupport.stream(Iterator.EL.spliterator(this), false);
            return stream;
        }

        @Override // java.util.Collection
        public final /* synthetic */ java.util.stream.Stream stream() {
            Stream stream;
            stream = StreamSupport.stream(Iterator.EL.spliterator(this), false);
            return Stream.Wrapper.convert(stream);
        }

        @Override // java.util.Collection, j$.util.Collection
        public final /* synthetic */ Object[] toArray(IntFunction intFunction) {
            Object[] array;
            array = toArray((Object[]) intFunction.apply(0));
            return array;
        }

        @Override // java.util.Collection, java.util.Set
        public final boolean add(Object obj) {
            Map.Entry entry = (Map.Entry) obj;
            return this.map.putVal(entry.getKey(), entry.getValue(), false) == null;
        }

        @Override // j$.util.concurrent.ConcurrentHashMap.CollectionView, java.util.Collection
        public final boolean contains(Object obj) {
            Map.Entry entry;
            Object key;
            Object obj2;
            Object value;
            return (!(obj instanceof Map.Entry) || (key = (entry = (Map.Entry) obj).getKey()) == null || (obj2 = this.map.get(key)) == null || (value = entry.getValue()) == null || (value != obj2 && !value.equals(obj2))) ? false : true;
        }

        @Override // j$.util.concurrent.ConcurrentHashMap.CollectionView, java.util.Collection
        public final boolean remove(Object obj) {
            Map.Entry entry;
            Object key;
            Object value;
            return (obj instanceof Map.Entry) && (key = (entry = (Map.Entry) obj).getKey()) != null && (value = entry.getValue()) != null && this.map.remove(key, value);
        }

        @Override // j$.util.concurrent.ConcurrentHashMap.CollectionView, java.util.Collection, java.lang.Iterable
        public final java.util.Iterator iterator() {
            ConcurrentHashMap concurrentHashMap = this.map;
            Node[] nodeArr = concurrentHashMap.table;
            int length = nodeArr == null ? 0 : nodeArr.length;
            return new EntryIterator(nodeArr, length, length, concurrentHashMap);
        }

        @Override // java.util.Collection, java.util.Set
        public final boolean addAll(java.util.Collection collection) {
            java.util.Iterator it = collection.iterator();
            boolean z = false;
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (this.map.putVal(entry.getKey(), entry.getValue(), false) == null) {
                    z = true;
                }
            }
            return z;
        }

        @Override // java.util.Collection, j$.util.Collection
        public final boolean removeIf(Predicate predicate) {
            ConcurrentHashMap concurrentHashMap = this.map;
            concurrentHashMap.getClass();
            predicate.getClass();
            Node[] nodeArr = concurrentHashMap.table;
            boolean z = false;
            if (nodeArr != null) {
                Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
                while (true) {
                    Node advance = traverser.advance();
                    if (advance == null) {
                        break;
                    }
                    Object obj = advance.key;
                    Object obj2 = advance.val;
                    if (predicate.test(new AbstractMap.SimpleImmutableEntry(obj, obj2)) && concurrentHashMap.replaceNode(obj, null, obj2) != null) {
                        z = true;
                    }
                }
            }
            return z;
        }

        @Override // java.util.Collection, java.util.Set
        public final int hashCode() {
            Node[] nodeArr = this.map.table;
            int i = 0;
            if (nodeArr != null) {
                Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
                while (true) {
                    Node advance = traverser.advance();
                    if (advance == null) {
                        break;
                    }
                    i += advance.hashCode();
                }
            }
            return i;
        }

        @Override // java.util.Collection, java.util.Set
        public final boolean equals(Object obj) {
            Set set;
            return (obj instanceof Set) && ((set = (Set) obj) == this || (containsAll(set) && set.containsAll(this)));
        }

        @Override // java.util.Collection, java.lang.Iterable, java.util.Set, j$.util.Collection
        public final Spliterator spliterator() {
            ConcurrentHashMap concurrentHashMap = this.map;
            long sumCount = concurrentHashMap.sumCount();
            Node[] nodeArr = concurrentHashMap.table;
            int length = nodeArr == null ? 0 : nodeArr.length;
            return new EntrySpliterator(nodeArr, length, 0, length, sumCount >= 0 ? sumCount : 0L, concurrentHashMap);
        }

        @Override // java.lang.Iterable, j$.util.Collection
        public final void forEach(Consumer consumer) {
            consumer.getClass();
            Node[] nodeArr = this.map.table;
            if (nodeArr == null) {
                return;
            }
            Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
            while (true) {
                Node advance = traverser.advance();
                if (advance == null) {
                    return;
                } else {
                    consumer.accept(new MapEntry(advance.key, advance.val, this.map));
                }
            }
        }
    }
}
