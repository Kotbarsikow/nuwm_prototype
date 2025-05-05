package com.fasterxml.jackson.core.sym;

import com.fasterxml.jackson.core.util.InternCache;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class CharsToNameCanonicalizer {
    protected static final int DEFAULT_TABLE_SIZE = 64;
    public static final int HASH_MULT = 33;
    static final int MAX_COLL_CHAIN_FOR_REUSE = 63;
    static final int MAX_COLL_CHAIN_LENGTH = 255;
    static final int MAX_ENTRIES_FOR_REUSE = 12000;
    protected static final int MAX_TABLE_SIZE = 65536;
    static final CharsToNameCanonicalizer sBootstrapSymbolTable = new CharsToNameCanonicalizer();
    protected Bucket[] _buckets;
    protected final boolean _canonicalize;
    protected boolean _dirty;
    private final int _hashSeed;
    protected int _indexMask;
    protected final boolean _intern;
    protected int _longestCollisionList;
    protected CharsToNameCanonicalizer _parent;
    protected int _size;
    protected int _sizeThreshold;
    protected String[] _symbols;

    private static int _thresholdSize(int i) {
        return i - (i >> 2);
    }

    public static CharsToNameCanonicalizer createRoot() {
        long currentTimeMillis = System.currentTimeMillis();
        return createRoot((((int) currentTimeMillis) + ((int) (currentTimeMillis >>> 32))) | 1);
    }

    protected static CharsToNameCanonicalizer createRoot(int i) {
        return sBootstrapSymbolTable.makeOrphan(i);
    }

    private CharsToNameCanonicalizer() {
        this._canonicalize = true;
        this._intern = true;
        this._dirty = true;
        this._hashSeed = 0;
        this._longestCollisionList = 0;
        initTables(64);
    }

    private void initTables(int i) {
        this._symbols = new String[i];
        this._buckets = new Bucket[i >> 1];
        this._indexMask = i - 1;
        this._size = 0;
        this._longestCollisionList = 0;
        this._sizeThreshold = _thresholdSize(i);
    }

    private CharsToNameCanonicalizer(CharsToNameCanonicalizer charsToNameCanonicalizer, boolean z, boolean z2, String[] strArr, Bucket[] bucketArr, int i, int i2, int i3) {
        this._parent = charsToNameCanonicalizer;
        this._canonicalize = z;
        this._intern = z2;
        this._symbols = strArr;
        this._buckets = bucketArr;
        this._size = i;
        this._hashSeed = i2;
        int length = strArr.length;
        this._sizeThreshold = _thresholdSize(length);
        this._indexMask = length - 1;
        this._longestCollisionList = i3;
        this._dirty = false;
    }

    public CharsToNameCanonicalizer makeChild(boolean z, boolean z2) {
        String[] strArr;
        Bucket[] bucketArr;
        int i;
        int i2;
        int i3;
        synchronized (this) {
            strArr = this._symbols;
            bucketArr = this._buckets;
            i = this._size;
            i2 = this._hashSeed;
            i3 = this._longestCollisionList;
        }
        return new CharsToNameCanonicalizer(this, z, z2, strArr, bucketArr, i, i2, i3);
    }

    private CharsToNameCanonicalizer makeOrphan(int i) {
        return new CharsToNameCanonicalizer(null, true, true, this._symbols, this._buckets, this._size, i, this._longestCollisionList);
    }

    private void mergeChild(CharsToNameCanonicalizer charsToNameCanonicalizer) {
        if (charsToNameCanonicalizer.size() > MAX_ENTRIES_FOR_REUSE || charsToNameCanonicalizer._longestCollisionList > 63) {
            synchronized (this) {
                initTables(64);
                this._dirty = false;
            }
        } else {
            if (charsToNameCanonicalizer.size() <= size()) {
                return;
            }
            synchronized (this) {
                this._symbols = charsToNameCanonicalizer._symbols;
                this._buckets = charsToNameCanonicalizer._buckets;
                this._size = charsToNameCanonicalizer._size;
                this._sizeThreshold = charsToNameCanonicalizer._sizeThreshold;
                this._indexMask = charsToNameCanonicalizer._indexMask;
                this._longestCollisionList = charsToNameCanonicalizer._longestCollisionList;
                this._dirty = false;
            }
        }
    }

    public void release() {
        CharsToNameCanonicalizer charsToNameCanonicalizer;
        if (maybeDirty() && (charsToNameCanonicalizer = this._parent) != null) {
            charsToNameCanonicalizer.mergeChild(this);
            this._dirty = false;
        }
    }

    public int size() {
        return this._size;
    }

    public int bucketCount() {
        return this._symbols.length;
    }

    public boolean maybeDirty() {
        return this._dirty;
    }

    public int hashSeed() {
        return this._hashSeed;
    }

    public int collisionCount() {
        int i = 0;
        for (Bucket bucket : this._buckets) {
            if (bucket != null) {
                i += bucket.length();
            }
        }
        return i;
    }

    public int maxCollisionLength() {
        return this._longestCollisionList;
    }

    public String findSymbol(char[] cArr, int i, int i2, int i3) {
        String find;
        if (i2 < 1) {
            return "";
        }
        if (!this._canonicalize) {
            return new String(cArr, i, i2);
        }
        int _hashToIndex = _hashToIndex(i3);
        String str = this._symbols[_hashToIndex];
        if (str != null) {
            if (str.length() == i2) {
                int i4 = 0;
                while (str.charAt(i4) == cArr[i + i4] && (i4 = i4 + 1) < i2) {
                }
                if (i4 == i2) {
                    return str;
                }
            }
            Bucket bucket = this._buckets[_hashToIndex >> 1];
            if (bucket != null && (find = bucket.find(cArr, i, i2)) != null) {
                return find;
            }
        }
        if (!this._dirty) {
            copyArrays();
            this._dirty = true;
        } else if (this._size >= this._sizeThreshold) {
            rehash();
            _hashToIndex = _hashToIndex(calcHash(cArr, i, i2));
        }
        String str2 = new String(cArr, i, i2);
        if (this._intern) {
            str2 = InternCache.instance.intern(str2);
        }
        this._size++;
        String[] strArr = this._symbols;
        if (strArr[_hashToIndex] == null) {
            strArr[_hashToIndex] = str2;
        } else {
            int i5 = _hashToIndex >> 1;
            Bucket bucket2 = new Bucket(str2, this._buckets[i5]);
            this._buckets[i5] = bucket2;
            int max = Math.max(bucket2.length(), this._longestCollisionList);
            this._longestCollisionList = max;
            if (max > 255) {
                reportTooManyCollisions(255);
            }
        }
        return str2;
    }

    public int _hashToIndex(int i) {
        return (i + (i >>> 15)) & this._indexMask;
    }

    public int calcHash(char[] cArr, int i, int i2) {
        int i3 = this._hashSeed;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 = (i3 * 33) + cArr[i4];
        }
        if (i3 == 0) {
            return 1;
        }
        return i3;
    }

    public int calcHash(String str) {
        int length = str.length();
        int i = this._hashSeed;
        for (int i2 = 0; i2 < length; i2++) {
            i = (i * 33) + str.charAt(i2);
        }
        if (i == 0) {
            return 1;
        }
        return i;
    }

    private void copyArrays() {
        String[] strArr = this._symbols;
        int length = strArr.length;
        String[] strArr2 = new String[length];
        this._symbols = strArr2;
        System.arraycopy(strArr, 0, strArr2, 0, length);
        Bucket[] bucketArr = this._buckets;
        int length2 = bucketArr.length;
        Bucket[] bucketArr2 = new Bucket[length2];
        this._buckets = bucketArr2;
        System.arraycopy(bucketArr, 0, bucketArr2, 0, length2);
    }

    private void rehash() {
        String[] strArr = this._symbols;
        int length = strArr.length;
        int i = length + length;
        if (i > 65536) {
            this._size = 0;
            Arrays.fill(strArr, (Object) null);
            Arrays.fill(this._buckets, (Object) null);
            this._dirty = true;
            return;
        }
        Bucket[] bucketArr = this._buckets;
        this._symbols = new String[i];
        this._buckets = new Bucket[i >> 1];
        this._indexMask = i - 1;
        this._sizeThreshold = _thresholdSize(i);
        int i2 = 0;
        int i3 = 0;
        for (String str : strArr) {
            if (str != null) {
                i2++;
                int _hashToIndex = _hashToIndex(calcHash(str));
                String[] strArr2 = this._symbols;
                if (strArr2[_hashToIndex] == null) {
                    strArr2[_hashToIndex] = str;
                } else {
                    int i4 = _hashToIndex >> 1;
                    Bucket bucket = new Bucket(str, this._buckets[i4]);
                    this._buckets[i4] = bucket;
                    i3 = Math.max(i3, bucket.length());
                }
            }
        }
        int i5 = length >> 1;
        for (int i6 = 0; i6 < i5; i6++) {
            for (Bucket bucket2 = bucketArr[i6]; bucket2 != null; bucket2 = bucket2.getNext()) {
                i2++;
                String symbol = bucket2.getSymbol();
                int _hashToIndex2 = _hashToIndex(calcHash(symbol));
                String[] strArr3 = this._symbols;
                if (strArr3[_hashToIndex2] == null) {
                    strArr3[_hashToIndex2] = symbol;
                } else {
                    int i7 = _hashToIndex2 >> 1;
                    Bucket bucket3 = new Bucket(symbol, this._buckets[i7]);
                    this._buckets[i7] = bucket3;
                    i3 = Math.max(i3, bucket3.length());
                }
            }
        }
        this._longestCollisionList = i3;
        if (i2 == this._size) {
            return;
        }
        throw new Error("Internal error on SymbolTable.rehash(): had " + this._size + " entries; now have " + i2 + ".");
    }

    protected void reportTooManyCollisions(int i) {
        throw new IllegalStateException("Longest collision chain in symbol table (of size " + this._size + ") now exceeds maximum, " + i + " -- suspect a DoS attack based on hash collisions");
    }

    static final class Bucket {
        private final int _length;
        private final Bucket _next;
        private final String _symbol;

        public Bucket(String str, Bucket bucket) {
            this._symbol = str;
            this._next = bucket;
            this._length = bucket != null ? 1 + bucket._length : 1;
        }

        public String getSymbol() {
            return this._symbol;
        }

        public Bucket getNext() {
            return this._next;
        }

        public int length() {
            return this._length;
        }

        public String find(char[] cArr, int i, int i2) {
            String str = this._symbol;
            Bucket bucket = this._next;
            while (true) {
                if (str.length() == i2) {
                    int i3 = 0;
                    while (str.charAt(i3) == cArr[i + i3] && (i3 = i3 + 1) < i2) {
                    }
                    if (i3 == i2) {
                        return str;
                    }
                }
                if (bucket == null) {
                    return null;
                }
                str = bucket.getSymbol();
                bucket = bucket.getNext();
            }
        }
    }
}
