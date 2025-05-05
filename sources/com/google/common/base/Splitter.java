package com.google.common.base;

import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes2.dex */
public final class Splitter {
    private final int limit;
    private final boolean omitEmptyStrings;
    private final Strategy strategy;
    private final CharMatcher trimmer;

    private interface Strategy {
        Iterator<String> iterator(Splitter splitter, CharSequence charSequence);
    }

    private Splitter(Strategy strategy) {
        this(strategy, false, CharMatcher.none(), Integer.MAX_VALUE);
    }

    private Splitter(Strategy strategy, boolean z, CharMatcher charMatcher, int i) {
        this.strategy = strategy;
        this.omitEmptyStrings = z;
        this.trimmer = charMatcher;
        this.limit = i;
    }

    public static Splitter on(char c) {
        return on(CharMatcher.is(c));
    }

    public static Splitter on(CharMatcher charMatcher) {
        Preconditions.checkNotNull(charMatcher);
        return new Splitter(new Strategy() { // from class: com.google.common.base.Splitter.1
            AnonymousClass1() {
            }

            /* renamed from: com.google.common.base.Splitter$1$1 */
            class C00231 extends SplittingIterator {
                @Override // com.google.common.base.Splitter.SplittingIterator
                int separatorEnd(int i) {
                    return i + 1;
                }

                C00231(Splitter splitter, CharSequence charSequence) {
                    super(splitter, charSequence);
                }

                @Override // com.google.common.base.Splitter.SplittingIterator
                int separatorStart(int i) {
                    return CharMatcher.this.indexIn(this.toSplit, i);
                }
            }

            @Override // com.google.common.base.Splitter.Strategy
            public SplittingIterator iterator(Splitter splitter, CharSequence charSequence) {
                return new SplittingIterator(splitter, charSequence) { // from class: com.google.common.base.Splitter.1.1
                    @Override // com.google.common.base.Splitter.SplittingIterator
                    int separatorEnd(int i) {
                        return i + 1;
                    }

                    C00231(Splitter splitter2, CharSequence charSequence2) {
                        super(splitter2, charSequence2);
                    }

                    @Override // com.google.common.base.Splitter.SplittingIterator
                    int separatorStart(int i) {
                        return CharMatcher.this.indexIn(this.toSplit, i);
                    }
                };
            }
        });
    }

    /* renamed from: com.google.common.base.Splitter$1 */
    class AnonymousClass1 implements Strategy {
        AnonymousClass1() {
        }

        /* renamed from: com.google.common.base.Splitter$1$1 */
        class C00231 extends SplittingIterator {
            @Override // com.google.common.base.Splitter.SplittingIterator
            int separatorEnd(int i) {
                return i + 1;
            }

            C00231(Splitter splitter2, CharSequence charSequence2) {
                super(splitter2, charSequence2);
            }

            @Override // com.google.common.base.Splitter.SplittingIterator
            int separatorStart(int i) {
                return CharMatcher.this.indexIn(this.toSplit, i);
            }
        }

        @Override // com.google.common.base.Splitter.Strategy
        public SplittingIterator iterator(Splitter splitter2, CharSequence charSequence2) {
            return new SplittingIterator(splitter2, charSequence2) { // from class: com.google.common.base.Splitter.1.1
                @Override // com.google.common.base.Splitter.SplittingIterator
                int separatorEnd(int i) {
                    return i + 1;
                }

                C00231(Splitter splitter22, CharSequence charSequence22) {
                    super(splitter22, charSequence22);
                }

                @Override // com.google.common.base.Splitter.SplittingIterator
                int separatorStart(int i) {
                    return CharMatcher.this.indexIn(this.toSplit, i);
                }
            };
        }
    }

    public static Splitter on(String str) {
        Preconditions.checkArgument(str.length() != 0, "The separator may not be the empty string.");
        if (str.length() == 1) {
            return on(str.charAt(0));
        }
        return new Splitter(new Strategy() { // from class: com.google.common.base.Splitter.2
            final /* synthetic */ String val$separator;

            AnonymousClass2(String str2) {
                r1 = str2;
            }

            /* renamed from: com.google.common.base.Splitter$2$1 */
            class AnonymousClass1 extends SplittingIterator {
                AnonymousClass1(Splitter splitter, CharSequence charSequence) {
                    super(splitter, charSequence);
                }

                /* JADX WARN: Code restructure failed: missing block: B:9:0x0026, code lost:
                
                    r6 = r6 + 1;
                 */
                @Override // com.google.common.base.Splitter.SplittingIterator
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public int separatorStart(int r6) {
                    /*
                        r5 = this;
                        com.google.common.base.Splitter$2 r0 = com.google.common.base.Splitter.AnonymousClass2.this
                        java.lang.String r0 = r1
                        int r0 = r0.length()
                        java.lang.CharSequence r1 = r5.toSplit
                        int r1 = r1.length()
                        int r1 = r1 - r0
                    Lf:
                        if (r6 > r1) goto L2d
                        r2 = 0
                    L12:
                        if (r2 >= r0) goto L2c
                        java.lang.CharSequence r3 = r5.toSplit
                        int r4 = r2 + r6
                        char r3 = r3.charAt(r4)
                        com.google.common.base.Splitter$2 r4 = com.google.common.base.Splitter.AnonymousClass2.this
                        java.lang.String r4 = r1
                        char r4 = r4.charAt(r2)
                        if (r3 == r4) goto L29
                        int r6 = r6 + 1
                        goto Lf
                    L29:
                        int r2 = r2 + 1
                        goto L12
                    L2c:
                        return r6
                    L2d:
                        r6 = -1
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.common.base.Splitter.AnonymousClass2.AnonymousClass1.separatorStart(int):int");
                }

                @Override // com.google.common.base.Splitter.SplittingIterator
                public int separatorEnd(int i) {
                    return i + r1.length();
                }
            }

            @Override // com.google.common.base.Splitter.Strategy
            public SplittingIterator iterator(Splitter splitter, CharSequence charSequence) {
                return new SplittingIterator(splitter, charSequence) { // from class: com.google.common.base.Splitter.2.1
                    AnonymousClass1(Splitter splitter2, CharSequence charSequence2) {
                        super(splitter2, charSequence2);
                    }

                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorStart(int i) {
                        /*
                            this = this;
                            com.google.common.base.Splitter$2 r0 = com.google.common.base.Splitter.AnonymousClass2.this
                            java.lang.String r0 = r1
                            int r0 = r0.length()
                            java.lang.CharSequence r1 = r5.toSplit
                            int r1 = r1.length()
                            int r1 = r1 - r0
                        Lf:
                            if (r6 > r1) goto L2d
                            r2 = 0
                        L12:
                            if (r2 >= r0) goto L2c
                            java.lang.CharSequence r3 = r5.toSplit
                            int r4 = r2 + r6
                            char r3 = r3.charAt(r4)
                            com.google.common.base.Splitter$2 r4 = com.google.common.base.Splitter.AnonymousClass2.this
                            java.lang.String r4 = r1
                            char r4 = r4.charAt(r2)
                            if (r3 == r4) goto L29
                            int r6 = r6 + 1
                            goto Lf
                        L29:
                            int r2 = r2 + 1
                            goto L12
                        L2c:
                            return r6
                        L2d:
                            r6 = -1
                            return r6
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.common.base.Splitter.AnonymousClass2.AnonymousClass1.separatorStart(int):int");
                    }

                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorEnd(int i) {
                        return i + r1.length();
                    }
                };
            }
        });
    }

    /* renamed from: com.google.common.base.Splitter$2 */
    class AnonymousClass2 implements Strategy {
        final /* synthetic */ String val$separator;

        AnonymousClass2(String str2) {
            r1 = str2;
        }

        /* renamed from: com.google.common.base.Splitter$2$1 */
        class AnonymousClass1 extends SplittingIterator {
            AnonymousClass1(Splitter splitter2, CharSequence charSequence2) {
                super(splitter2, charSequence2);
            }

            @Override // com.google.common.base.Splitter.SplittingIterator
            public int separatorStart(int i) {
                /*
                    this = this;
                    com.google.common.base.Splitter$2 r0 = com.google.common.base.Splitter.AnonymousClass2.this
                    java.lang.String r0 = r1
                    int r0 = r0.length()
                    java.lang.CharSequence r1 = r5.toSplit
                    int r1 = r1.length()
                    int r1 = r1 - r0
                Lf:
                    if (r6 > r1) goto L2d
                    r2 = 0
                L12:
                    if (r2 >= r0) goto L2c
                    java.lang.CharSequence r3 = r5.toSplit
                    int r4 = r2 + r6
                    char r3 = r3.charAt(r4)
                    com.google.common.base.Splitter$2 r4 = com.google.common.base.Splitter.AnonymousClass2.this
                    java.lang.String r4 = r1
                    char r4 = r4.charAt(r2)
                    if (r3 == r4) goto L29
                    int r6 = r6 + 1
                    goto Lf
                L29:
                    int r2 = r2 + 1
                    goto L12
                L2c:
                    return r6
                L2d:
                    r6 = -1
                    return r6
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.common.base.Splitter.AnonymousClass2.AnonymousClass1.separatorStart(int):int");
            }

            @Override // com.google.common.base.Splitter.SplittingIterator
            public int separatorEnd(int i) {
                return i + r1.length();
            }
        }

        @Override // com.google.common.base.Splitter.Strategy
        public SplittingIterator iterator(Splitter splitter2, CharSequence charSequence2) {
            return new SplittingIterator(splitter2, charSequence2) { // from class: com.google.common.base.Splitter.2.1
                AnonymousClass1(Splitter splitter22, CharSequence charSequence22) {
                    super(splitter22, charSequence22);
                }

                @Override // com.google.common.base.Splitter.SplittingIterator
                public int separatorStart(int i) {
                    /*
                        this = this;
                        com.google.common.base.Splitter$2 r0 = com.google.common.base.Splitter.AnonymousClass2.this
                        java.lang.String r0 = r1
                        int r0 = r0.length()
                        java.lang.CharSequence r1 = r5.toSplit
                        int r1 = r1.length()
                        int r1 = r1 - r0
                    Lf:
                        if (r6 > r1) goto L2d
                        r2 = 0
                    L12:
                        if (r2 >= r0) goto L2c
                        java.lang.CharSequence r3 = r5.toSplit
                        int r4 = r2 + r6
                        char r3 = r3.charAt(r4)
                        com.google.common.base.Splitter$2 r4 = com.google.common.base.Splitter.AnonymousClass2.this
                        java.lang.String r4 = r1
                        char r4 = r4.charAt(r2)
                        if (r3 == r4) goto L29
                        int r6 = r6 + 1
                        goto Lf
                    L29:
                        int r2 = r2 + 1
                        goto L12
                    L2c:
                        return r6
                    L2d:
                        r6 = -1
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.common.base.Splitter.AnonymousClass2.AnonymousClass1.separatorStart(int):int");
                }

                @Override // com.google.common.base.Splitter.SplittingIterator
                public int separatorEnd(int i) {
                    return i + r1.length();
                }
            };
        }
    }

    public static Splitter on(Pattern pattern) {
        return on(new JdkPattern(pattern));
    }

    private static Splitter on(CommonPattern commonPattern) {
        Preconditions.checkArgument(!commonPattern.matcher("").matches(), "The pattern may not match the empty string: %s", commonPattern);
        return new Splitter(new Strategy() { // from class: com.google.common.base.Splitter.3
            AnonymousClass3() {
            }

            /* renamed from: com.google.common.base.Splitter$3$1 */
            class AnonymousClass1 extends SplittingIterator {
                final /* synthetic */ CommonMatcher val$matcher;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(AnonymousClass3 anonymousClass3, Splitter splitter, CharSequence charSequence, CommonMatcher commonMatcher) {
                    super(splitter, charSequence);
                    r4 = commonMatcher;
                }

                @Override // com.google.common.base.Splitter.SplittingIterator
                public int separatorStart(int i) {
                    if (r4.find(i)) {
                        return r4.start();
                    }
                    return -1;
                }

                @Override // com.google.common.base.Splitter.SplittingIterator
                public int separatorEnd(int i) {
                    return r4.end();
                }
            }

            @Override // com.google.common.base.Splitter.Strategy
            public SplittingIterator iterator(Splitter splitter, CharSequence charSequence) {
                return new SplittingIterator(this, splitter, charSequence) { // from class: com.google.common.base.Splitter.3.1
                    final /* synthetic */ CommonMatcher val$matcher;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(AnonymousClass3 this, Splitter splitter2, CharSequence charSequence2, CommonMatcher commonMatcher) {
                        super(splitter2, charSequence2);
                        r4 = commonMatcher;
                    }

                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorStart(int i) {
                        if (r4.find(i)) {
                            return r4.start();
                        }
                        return -1;
                    }

                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorEnd(int i) {
                        return r4.end();
                    }
                };
            }
        });
    }

    /* renamed from: com.google.common.base.Splitter$3 */
    class AnonymousClass3 implements Strategy {
        AnonymousClass3() {
        }

        /* renamed from: com.google.common.base.Splitter$3$1 */
        class AnonymousClass1 extends SplittingIterator {
            final /* synthetic */ CommonMatcher val$matcher;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(AnonymousClass3 this, Splitter splitter2, CharSequence charSequence2, CommonMatcher commonMatcher) {
                super(splitter2, charSequence2);
                r4 = commonMatcher;
            }

            @Override // com.google.common.base.Splitter.SplittingIterator
            public int separatorStart(int i) {
                if (r4.find(i)) {
                    return r4.start();
                }
                return -1;
            }

            @Override // com.google.common.base.Splitter.SplittingIterator
            public int separatorEnd(int i) {
                return r4.end();
            }
        }

        @Override // com.google.common.base.Splitter.Strategy
        public SplittingIterator iterator(Splitter splitter2, CharSequence charSequence2) {
            return new SplittingIterator(this, splitter2, charSequence2) { // from class: com.google.common.base.Splitter.3.1
                final /* synthetic */ CommonMatcher val$matcher;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(AnonymousClass3 this, Splitter splitter22, CharSequence charSequence22, CommonMatcher commonMatcher) {
                    super(splitter22, charSequence22);
                    r4 = commonMatcher;
                }

                @Override // com.google.common.base.Splitter.SplittingIterator
                public int separatorStart(int i) {
                    if (r4.find(i)) {
                        return r4.start();
                    }
                    return -1;
                }

                @Override // com.google.common.base.Splitter.SplittingIterator
                public int separatorEnd(int i) {
                    return r4.end();
                }
            };
        }
    }

    public static Splitter onPattern(String str) {
        return on(Platform.compilePattern(str));
    }

    public static Splitter fixedLength(int i) {
        Preconditions.checkArgument(i > 0, "The length may not be less than 1");
        return new Splitter(new Strategy() { // from class: com.google.common.base.Splitter.4
            final /* synthetic */ int val$length;

            AnonymousClass4(int i2) {
                r1 = i2;
            }

            /* renamed from: com.google.common.base.Splitter$4$1 */
            class AnonymousClass1 extends SplittingIterator {
                @Override // com.google.common.base.Splitter.SplittingIterator
                public int separatorEnd(int i) {
                    return i;
                }

                AnonymousClass1(Splitter splitter, CharSequence charSequence) {
                    super(splitter, charSequence);
                }

                @Override // com.google.common.base.Splitter.SplittingIterator
                public int separatorStart(int i) {
                    int i2 = i + r1;
                    if (i2 < this.toSplit.length()) {
                        return i2;
                    }
                    return -1;
                }
            }

            @Override // com.google.common.base.Splitter.Strategy
            public SplittingIterator iterator(Splitter splitter, CharSequence charSequence) {
                return new SplittingIterator(splitter, charSequence) { // from class: com.google.common.base.Splitter.4.1
                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorEnd(int i2) {
                        return i2;
                    }

                    AnonymousClass1(Splitter splitter2, CharSequence charSequence2) {
                        super(splitter2, charSequence2);
                    }

                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorStart(int i2) {
                        int i22 = i2 + r1;
                        if (i22 < this.toSplit.length()) {
                            return i22;
                        }
                        return -1;
                    }
                };
            }
        });
    }

    /* renamed from: com.google.common.base.Splitter$4 */
    class AnonymousClass4 implements Strategy {
        final /* synthetic */ int val$length;

        AnonymousClass4(int i2) {
            r1 = i2;
        }

        /* renamed from: com.google.common.base.Splitter$4$1 */
        class AnonymousClass1 extends SplittingIterator {
            @Override // com.google.common.base.Splitter.SplittingIterator
            public int separatorEnd(int i2) {
                return i2;
            }

            AnonymousClass1(Splitter splitter2, CharSequence charSequence2) {
                super(splitter2, charSequence2);
            }

            @Override // com.google.common.base.Splitter.SplittingIterator
            public int separatorStart(int i2) {
                int i22 = i2 + r1;
                if (i22 < this.toSplit.length()) {
                    return i22;
                }
                return -1;
            }
        }

        @Override // com.google.common.base.Splitter.Strategy
        public SplittingIterator iterator(Splitter splitter2, CharSequence charSequence2) {
            return new SplittingIterator(splitter2, charSequence2) { // from class: com.google.common.base.Splitter.4.1
                @Override // com.google.common.base.Splitter.SplittingIterator
                public int separatorEnd(int i2) {
                    return i2;
                }

                AnonymousClass1(Splitter splitter22, CharSequence charSequence22) {
                    super(splitter22, charSequence22);
                }

                @Override // com.google.common.base.Splitter.SplittingIterator
                public int separatorStart(int i2) {
                    int i22 = i2 + r1;
                    if (i22 < this.toSplit.length()) {
                        return i22;
                    }
                    return -1;
                }
            };
        }
    }

    public Splitter omitEmptyStrings() {
        return new Splitter(this.strategy, true, this.trimmer, this.limit);
    }

    public Splitter limit(int i) {
        Preconditions.checkArgument(i > 0, "must be greater than zero: %s", i);
        return new Splitter(this.strategy, this.omitEmptyStrings, this.trimmer, i);
    }

    public Splitter trimResults() {
        return trimResults(CharMatcher.whitespace());
    }

    public Splitter trimResults(CharMatcher charMatcher) {
        Preconditions.checkNotNull(charMatcher);
        return new Splitter(this.strategy, this.omitEmptyStrings, charMatcher, this.limit);
    }

    public Iterable<String> split(CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        return new Iterable<String>() { // from class: com.google.common.base.Splitter.5
            final /* synthetic */ CharSequence val$sequence;

            AnonymousClass5(CharSequence charSequence2) {
                r2 = charSequence2;
            }

            @Override // java.lang.Iterable
            public Iterator<String> iterator() {
                return Splitter.this.splittingIterator(r2);
            }

            public String toString() {
                Joiner on = Joiner.on(", ");
                StringBuilder sb = new StringBuilder();
                sb.append('[');
                StringBuilder appendTo = on.appendTo(sb, (Iterable<? extends Object>) this);
                appendTo.append(']');
                return appendTo.toString();
            }
        };
    }

    /* renamed from: com.google.common.base.Splitter$5 */
    class AnonymousClass5 implements Iterable<String> {
        final /* synthetic */ CharSequence val$sequence;

        AnonymousClass5(CharSequence charSequence2) {
            r2 = charSequence2;
        }

        @Override // java.lang.Iterable
        public Iterator<String> iterator() {
            return Splitter.this.splittingIterator(r2);
        }

        public String toString() {
            Joiner on = Joiner.on(", ");
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            StringBuilder appendTo = on.appendTo(sb, (Iterable<? extends Object>) this);
            appendTo.append(']');
            return appendTo.toString();
        }
    }

    public Iterator<String> splittingIterator(CharSequence charSequence) {
        return this.strategy.iterator(this, charSequence);
    }

    public List<String> splitToList(CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        Iterator<String> splittingIterator = splittingIterator(charSequence);
        ArrayList arrayList = new ArrayList();
        while (splittingIterator.hasNext()) {
            arrayList.add(splittingIterator.next());
        }
        return DesugarCollections.unmodifiableList(arrayList);
    }

    public MapSplitter withKeyValueSeparator(String str) {
        return withKeyValueSeparator(on(str));
    }

    public MapSplitter withKeyValueSeparator(char c) {
        return withKeyValueSeparator(on(c));
    }

    public MapSplitter withKeyValueSeparator(Splitter splitter) {
        return new MapSplitter(splitter);
    }

    public static final class MapSplitter {
        private static final String INVALID_ENTRY_MESSAGE = "Chunk [%s] is not a valid entry";
        private final Splitter entrySplitter;
        private final Splitter outerSplitter;

        /* synthetic */ MapSplitter(Splitter splitter, Splitter splitter2, AnonymousClass1 anonymousClass1) {
            this(splitter, splitter2);
        }

        private MapSplitter(Splitter splitter, Splitter splitter2) {
            this.outerSplitter = splitter;
            this.entrySplitter = (Splitter) Preconditions.checkNotNull(splitter2);
        }

        public Map<String, String> split(CharSequence charSequence) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (String str : this.outerSplitter.split(charSequence)) {
                Iterator splittingIterator = this.entrySplitter.splittingIterator(str);
                Preconditions.checkArgument(splittingIterator.hasNext(), INVALID_ENTRY_MESSAGE, str);
                String str2 = (String) splittingIterator.next();
                Preconditions.checkArgument(!linkedHashMap.containsKey(str2), "Duplicate key [%s] found.", str2);
                Preconditions.checkArgument(splittingIterator.hasNext(), INVALID_ENTRY_MESSAGE, str);
                linkedHashMap.put(str2, (String) splittingIterator.next());
                Preconditions.checkArgument(!splittingIterator.hasNext(), INVALID_ENTRY_MESSAGE, str);
            }
            return DesugarCollections.unmodifiableMap(linkedHashMap);
        }
    }

    private static abstract class SplittingIterator extends AbstractIterator<String> {
        int limit;
        int offset = 0;
        final boolean omitEmptyStrings;
        final CharSequence toSplit;
        final CharMatcher trimmer;

        abstract int separatorEnd(int i);

        abstract int separatorStart(int i);

        protected SplittingIterator(Splitter splitter, CharSequence charSequence) {
            this.trimmer = splitter.trimmer;
            this.omitEmptyStrings = splitter.omitEmptyStrings;
            this.limit = splitter.limit;
            this.toSplit = charSequence;
        }

        @Override // com.google.common.base.AbstractIterator
        @CheckForNull
        public String computeNext() {
            int separatorStart;
            int i = this.offset;
            while (true) {
                int i2 = this.offset;
                if (i2 != -1) {
                    separatorStart = separatorStart(i2);
                    if (separatorStart == -1) {
                        separatorStart = this.toSplit.length();
                        this.offset = -1;
                    } else {
                        this.offset = separatorEnd(separatorStart);
                    }
                    int i3 = this.offset;
                    if (i3 == i) {
                        int i4 = i3 + 1;
                        this.offset = i4;
                        if (i4 > this.toSplit.length()) {
                            this.offset = -1;
                        }
                    } else {
                        while (i < separatorStart && this.trimmer.matches(this.toSplit.charAt(i))) {
                            i++;
                        }
                        while (separatorStart > i && this.trimmer.matches(this.toSplit.charAt(separatorStart - 1))) {
                            separatorStart--;
                        }
                        if (!this.omitEmptyStrings || i != separatorStart) {
                            break;
                        }
                        i = this.offset;
                    }
                } else {
                    return endOfData();
                }
            }
            int i5 = this.limit;
            if (i5 == 1) {
                separatorStart = this.toSplit.length();
                this.offset = -1;
                while (separatorStart > i && this.trimmer.matches(this.toSplit.charAt(separatorStart - 1))) {
                    separatorStart--;
                }
            } else {
                this.limit = i5 - 1;
            }
            return this.toSplit.subSequence(i, separatorStart).toString();
        }
    }
}
