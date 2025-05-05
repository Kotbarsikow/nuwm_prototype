package com.google.api.client.repackaged.com.google.common.base;

import com.google.common.base.Ascii;
import com.hootsuite.nachos.tokenizer.SpanChipTokenizer;
import java.util.Arrays;
import java.util.BitSet;
import javax.annotation.CheckReturnValue;

/* loaded from: classes2.dex */
public abstract class CharMatcher implements Predicate<Character> {
    public static final CharMatcher ANY;
    public static final CharMatcher DIGIT;
    private static final int DISTINCT_CHARS = 65536;
    public static final CharMatcher INVISIBLE;
    public static final CharMatcher JAVA_DIGIT;
    public static final CharMatcher JAVA_ISO_CONTROL;
    public static final CharMatcher JAVA_LETTER;
    public static final CharMatcher JAVA_LETTER_OR_DIGIT;
    public static final CharMatcher JAVA_LOWER_CASE;
    public static final CharMatcher JAVA_UPPER_CASE;
    private static final String NINES;
    public static final CharMatcher NONE;
    public static final CharMatcher SINGLE_WIDTH;
    public static final CharMatcher WHITESPACE;
    static final int WHITESPACE_MULTIPLIER = 1682554634;
    static final int WHITESPACE_SHIFT;
    static final String WHITESPACE_TABLE = "\u2002\u3000\r\u0085\u200a\u2005\u2000\u3000\u2029\u000b\u3000\u2008\u2003\u205f\u3000\u1680\t \u2006\u2001  \f\u2009\u3000\u2004\u3000\u3000\u2028\n \u3000";
    private static final String ZEROES = "0٠۰߀०০੦૦୦௦౦೦൦๐໐༠၀႐០᠐᥆᧐᭐᮰᱀᱐꘠꣐꤀꩐０";
    final String description;
    public static final CharMatcher BREAKING_WHITESPACE = new CharMatcher() { // from class: com.google.api.client.repackaged.com.google.common.base.CharMatcher.1
        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
        public boolean matches(char c) {
            if (c != ' ' && c != 133 && c != 5760) {
                if (c == 8199) {
                    return false;
                }
                if (c != 8287 && c != 12288 && c != 8232 && c != 8233) {
                    switch (c) {
                        case '\t':
                        case '\n':
                        case 11:
                        case '\f':
                        case '\r':
                            break;
                        default:
                            return c >= 8192 && c <= 8202;
                    }
                }
            }
            return true;
        }

        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher, com.google.api.client.repackaged.com.google.common.base.Predicate
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.BREAKING_WHITESPACE";
        }
    };
    public static final CharMatcher ASCII = inRange(0, Ascii.MAX, "CharMatcher.ASCII");

    private static boolean isSmall(int i, int i2) {
        return i <= 1023 && i2 > i * 64;
    }

    public abstract boolean matches(char c);

    static {
        StringBuilder sb = new StringBuilder(31);
        for (int i = 0; i < 31; i++) {
            sb.append((char) (ZEROES.charAt(i) + '\t'));
        }
        String sb2 = sb.toString();
        NINES = sb2;
        DIGIT = new RangesMatcher("CharMatcher.DIGIT", ZEROES.toCharArray(), sb2.toCharArray());
        JAVA_DIGIT = new CharMatcher("CharMatcher.JAVA_DIGIT") { // from class: com.google.api.client.repackaged.com.google.common.base.CharMatcher.2
            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher, com.google.api.client.repackaged.com.google.common.base.Predicate
            public /* bridge */ /* synthetic */ boolean apply(Character ch) {
                return super.apply(ch);
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public boolean matches(char c) {
                return Character.isDigit(c);
            }
        };
        JAVA_LETTER = new CharMatcher("CharMatcher.JAVA_LETTER") { // from class: com.google.api.client.repackaged.com.google.common.base.CharMatcher.3
            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher, com.google.api.client.repackaged.com.google.common.base.Predicate
            public /* bridge */ /* synthetic */ boolean apply(Character ch) {
                return super.apply(ch);
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public boolean matches(char c) {
                return Character.isLetter(c);
            }
        };
        JAVA_LETTER_OR_DIGIT = new CharMatcher("CharMatcher.JAVA_LETTER_OR_DIGIT") { // from class: com.google.api.client.repackaged.com.google.common.base.CharMatcher.4
            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher, com.google.api.client.repackaged.com.google.common.base.Predicate
            public /* bridge */ /* synthetic */ boolean apply(Character ch) {
                return super.apply(ch);
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public boolean matches(char c) {
                return Character.isLetterOrDigit(c);
            }
        };
        JAVA_UPPER_CASE = new CharMatcher("CharMatcher.JAVA_UPPER_CASE") { // from class: com.google.api.client.repackaged.com.google.common.base.CharMatcher.5
            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher, com.google.api.client.repackaged.com.google.common.base.Predicate
            public /* bridge */ /* synthetic */ boolean apply(Character ch) {
                return super.apply(ch);
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public boolean matches(char c) {
                return Character.isUpperCase(c);
            }
        };
        JAVA_LOWER_CASE = new CharMatcher("CharMatcher.JAVA_LOWER_CASE") { // from class: com.google.api.client.repackaged.com.google.common.base.CharMatcher.6
            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher, com.google.api.client.repackaged.com.google.common.base.Predicate
            public /* bridge */ /* synthetic */ boolean apply(Character ch) {
                return super.apply(ch);
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public boolean matches(char c) {
                return Character.isLowerCase(c);
            }
        };
        JAVA_ISO_CONTROL = inRange((char) 0, SpanChipTokenizer.CHIP_SPAN_SEPARATOR).or(inRange(Ascii.MAX, (char) 159)).withToString("CharMatcher.JAVA_ISO_CONTROL");
        INVISIBLE = new RangesMatcher("CharMatcher.INVISIBLE", "\u0000\u007f\u00ad\u0600\u061c\u06dd\u070f\u1680\u180e\u2000\u2028\u205f\u2066\u2067\u2068\u2069\u206a\u3000\ud800\ufeff\ufff9\ufffa".toCharArray(), "  \u00ad\u0604\u061c\u06dd\u070f\u1680\u180e\u200f \u2064\u2066\u2067\u2068\u2069\u206f\u3000\uf8ff\ufeff\ufff9\ufffb".toCharArray());
        SINGLE_WIDTH = new RangesMatcher("CharMatcher.SINGLE_WIDTH", "\u0000־א׳\u0600ݐ\u0e00Ḁ℀ﭐﹰ｡".toCharArray(), "ӹ־ת״ۿݿ\u0e7f₯℺﷿\ufeffￜ".toCharArray());
        ANY = new FastMatcher("CharMatcher.ANY") { // from class: com.google.api.client.repackaged.com.google.common.base.CharMatcher.7
            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public boolean matches(char c) {
                return true;
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public int indexIn(CharSequence charSequence) {
                return charSequence.length() == 0 ? -1 : 0;
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public int indexIn(CharSequence charSequence, int i2) {
                int length = charSequence.length();
                Preconditions.checkPositionIndex(i2, length);
                if (i2 == length) {
                    return -1;
                }
                return i2;
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public int lastIndexIn(CharSequence charSequence) {
                return charSequence.length() - 1;
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public boolean matchesAllOf(CharSequence charSequence) {
                Preconditions.checkNotNull(charSequence);
                return true;
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public boolean matchesNoneOf(CharSequence charSequence) {
                return charSequence.length() == 0;
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public String removeFrom(CharSequence charSequence) {
                Preconditions.checkNotNull(charSequence);
                return "";
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public String replaceFrom(CharSequence charSequence, char c) {
                char[] cArr = new char[charSequence.length()];
                Arrays.fill(cArr, c);
                return new String(cArr);
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public String replaceFrom(CharSequence charSequence, CharSequence charSequence2) {
                StringBuilder sb3 = new StringBuilder(charSequence.length() * charSequence2.length());
                for (int i2 = 0; i2 < charSequence.length(); i2++) {
                    sb3.append(charSequence2);
                }
                return sb3.toString();
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public String collapseFrom(CharSequence charSequence, char c) {
                return charSequence.length() == 0 ? "" : String.valueOf(c);
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public String trimFrom(CharSequence charSequence) {
                Preconditions.checkNotNull(charSequence);
                return "";
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public int countIn(CharSequence charSequence) {
                return charSequence.length();
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public CharMatcher and(CharMatcher charMatcher) {
                return (CharMatcher) Preconditions.checkNotNull(charMatcher);
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public CharMatcher or(CharMatcher charMatcher) {
                Preconditions.checkNotNull(charMatcher);
                return this;
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher.FastMatcher, com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public CharMatcher negate() {
                return NONE;
            }
        };
        NONE = new FastMatcher("CharMatcher.NONE") { // from class: com.google.api.client.repackaged.com.google.common.base.CharMatcher.8
            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public boolean matches(char c) {
                return false;
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public int indexIn(CharSequence charSequence) {
                Preconditions.checkNotNull(charSequence);
                return -1;
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public int indexIn(CharSequence charSequence, int i2) {
                Preconditions.checkPositionIndex(i2, charSequence.length());
                return -1;
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public int lastIndexIn(CharSequence charSequence) {
                Preconditions.checkNotNull(charSequence);
                return -1;
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public boolean matchesAllOf(CharSequence charSequence) {
                return charSequence.length() == 0;
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public boolean matchesNoneOf(CharSequence charSequence) {
                Preconditions.checkNotNull(charSequence);
                return true;
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public String removeFrom(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public String replaceFrom(CharSequence charSequence, char c) {
                return charSequence.toString();
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public String replaceFrom(CharSequence charSequence, CharSequence charSequence2) {
                Preconditions.checkNotNull(charSequence2);
                return charSequence.toString();
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public String collapseFrom(CharSequence charSequence, char c) {
                return charSequence.toString();
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public String trimFrom(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public String trimLeadingFrom(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public String trimTrailingFrom(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public int countIn(CharSequence charSequence) {
                Preconditions.checkNotNull(charSequence);
                return 0;
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public CharMatcher and(CharMatcher charMatcher) {
                Preconditions.checkNotNull(charMatcher);
                return this;
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public CharMatcher or(CharMatcher charMatcher) {
                return (CharMatcher) Preconditions.checkNotNull(charMatcher);
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher.FastMatcher, com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public CharMatcher negate() {
                return ANY;
            }
        };
        WHITESPACE_SHIFT = Integer.numberOfLeadingZeros(31);
        WHITESPACE = new FastMatcher("WHITESPACE") { // from class: com.google.api.client.repackaged.com.google.common.base.CharMatcher.15
            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public boolean matches(char c) {
                return CharMatcher.WHITESPACE_TABLE.charAt((CharMatcher.WHITESPACE_MULTIPLIER * c) >>> WHITESPACE_SHIFT) == c;
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            void setBits(BitSet bitSet) {
                for (int i2 = 0; i2 < 32; i2++) {
                    bitSet.set(CharMatcher.WHITESPACE_TABLE.charAt(i2));
                }
            }
        };
    }

    private static class RangesMatcher extends CharMatcher {
        private final char[] rangeEnds;
        private final char[] rangeStarts;

        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher, com.google.api.client.repackaged.com.google.common.base.Predicate
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        RangesMatcher(String str, char[] cArr, char[] cArr2) {
            super(str);
            this.rangeStarts = cArr;
            this.rangeEnds = cArr2;
            Preconditions.checkArgument(cArr.length == cArr2.length);
            int i = 0;
            while (i < cArr.length) {
                Preconditions.checkArgument(cArr[i] <= cArr2[i]);
                int i2 = i + 1;
                if (i2 < cArr.length) {
                    Preconditions.checkArgument(cArr2[i] < cArr[i2]);
                }
                i = i2;
            }
        }

        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
        public boolean matches(char c) {
            int binarySearch = Arrays.binarySearch(this.rangeStarts, c);
            if (binarySearch >= 0) {
                return true;
            }
            int i = (~binarySearch) - 1;
            return i >= 0 && c <= this.rangeEnds[i];
        }
    }

    private static String showCharacter(char c) {
        char[] cArr = new char[6];
        cArr[0] = '\\';
        cArr[1] = 'u';
        cArr[2] = 0;
        cArr[3] = 0;
        cArr[4] = 0;
        cArr[5] = 0;
        for (int i = 0; i < 4; i++) {
            cArr[5 - i] = "0123456789ABCDEF".charAt(c & 15);
            c = (char) (c >> 4);
        }
        return String.copyValueOf(cArr);
    }

    public static CharMatcher is(final char c) {
        return new FastMatcher("CharMatcher.is('" + showCharacter(c) + "')") { // from class: com.google.api.client.repackaged.com.google.common.base.CharMatcher.9
            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public boolean matches(char c2) {
                return c2 == c;
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public String replaceFrom(CharSequence charSequence, char c2) {
                return charSequence.toString().replace(c, c2);
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public CharMatcher and(CharMatcher charMatcher) {
                return charMatcher.matches(c) ? this : NONE;
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public CharMatcher or(CharMatcher charMatcher) {
                return charMatcher.matches(c) ? charMatcher : super.or(charMatcher);
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher.FastMatcher, com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public CharMatcher negate() {
                return isNot(c);
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            void setBits(BitSet bitSet) {
                bitSet.set(c);
            }
        };
    }

    public static CharMatcher isNot(final char c) {
        return new FastMatcher("CharMatcher.isNot('" + showCharacter(c) + "')") { // from class: com.google.api.client.repackaged.com.google.common.base.CharMatcher.10
            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public boolean matches(char c2) {
                return c2 != c;
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public CharMatcher and(CharMatcher charMatcher) {
                return charMatcher.matches(c) ? super.and(charMatcher) : charMatcher;
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public CharMatcher or(CharMatcher charMatcher) {
                return charMatcher.matches(c) ? ANY : this;
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            void setBits(BitSet bitSet) {
                bitSet.set(0, c);
                bitSet.set(c + 1, 65536);
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher.FastMatcher, com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public CharMatcher negate() {
                return is(c);
            }
        };
    }

    public static CharMatcher anyOf(CharSequence charSequence) {
        int length = charSequence.length();
        if (length == 0) {
            return NONE;
        }
        if (length == 1) {
            return is(charSequence.charAt(0));
        }
        if (length == 2) {
            return isEither(charSequence.charAt(0), charSequence.charAt(1));
        }
        final char[] charArray = charSequence.toString().toCharArray();
        Arrays.sort(charArray);
        StringBuilder sb = new StringBuilder("CharMatcher.anyOf(\"");
        for (char c : charArray) {
            sb.append(showCharacter(c));
        }
        sb.append("\")");
        return new CharMatcher(sb.toString()) { // from class: com.google.api.client.repackaged.com.google.common.base.CharMatcher.11
            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher, com.google.api.client.repackaged.com.google.common.base.Predicate
            public /* bridge */ /* synthetic */ boolean apply(Character ch) {
                return super.apply(ch);
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public boolean matches(char c2) {
                return Arrays.binarySearch(charArray, c2) >= 0;
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            void setBits(BitSet bitSet) {
                for (char c2 : charArray) {
                    bitSet.set(c2);
                }
            }
        };
    }

    private static CharMatcher isEither(final char c, final char c2) {
        return new FastMatcher("CharMatcher.anyOf(\"" + showCharacter(c) + showCharacter(c2) + "\")") { // from class: com.google.api.client.repackaged.com.google.common.base.CharMatcher.12
            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public boolean matches(char c3) {
                return c3 == c || c3 == c2;
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            void setBits(BitSet bitSet) {
                bitSet.set(c);
                bitSet.set(c2);
            }
        };
    }

    public static CharMatcher noneOf(CharSequence charSequence) {
        return anyOf(charSequence).negate();
    }

    public static CharMatcher inRange(char c, char c2) {
        Preconditions.checkArgument(c2 >= c);
        return inRange(c, c2, "CharMatcher.inRange('" + showCharacter(c) + "', '" + showCharacter(c2) + "')");
    }

    static CharMatcher inRange(final char c, final char c2, String str) {
        return new FastMatcher(str) { // from class: com.google.api.client.repackaged.com.google.common.base.CharMatcher.13
            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public boolean matches(char c3) {
                return c <= c3 && c3 <= c2;
            }

            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            void setBits(BitSet bitSet) {
                bitSet.set(c, c2 + 1);
            }
        };
    }

    public static CharMatcher forPredicate(final Predicate<? super Character> predicate) {
        Preconditions.checkNotNull(predicate);
        if (predicate instanceof CharMatcher) {
            return (CharMatcher) predicate;
        }
        return new CharMatcher("CharMatcher.forPredicate(" + predicate + ")") { // from class: com.google.api.client.repackaged.com.google.common.base.CharMatcher.14
            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
            public boolean matches(char c) {
                return predicate.apply(Character.valueOf(c));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher, com.google.api.client.repackaged.com.google.common.base.Predicate
            public boolean apply(Character ch) {
                return predicate.apply(Preconditions.checkNotNull(ch));
            }
        };
    }

    CharMatcher(String str) {
        this.description = str;
    }

    protected CharMatcher() {
        this.description = super.toString();
    }

    public CharMatcher negate() {
        return new NegatedMatcher(this);
    }

    private static class NegatedMatcher extends CharMatcher {
        final CharMatcher original;

        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher, com.google.api.client.repackaged.com.google.common.base.Predicate
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        NegatedMatcher(String str, CharMatcher charMatcher) {
            super(str);
            this.original = charMatcher;
        }

        NegatedMatcher(CharMatcher charMatcher) {
            this(charMatcher + ".negate()", charMatcher);
        }

        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return !this.original.matches(c);
        }

        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
        public boolean matchesAllOf(CharSequence charSequence) {
            return this.original.matchesNoneOf(charSequence);
        }

        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
        public boolean matchesNoneOf(CharSequence charSequence) {
            return this.original.matchesAllOf(charSequence);
        }

        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
        public int countIn(CharSequence charSequence) {
            return charSequence.length() - this.original.countIn(charSequence);
        }

        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
        void setBits(BitSet bitSet) {
            BitSet bitSet2 = new BitSet();
            this.original.setBits(bitSet2);
            bitSet2.flip(0, 65536);
            bitSet.or(bitSet2);
        }

        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return this.original;
        }

        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
        CharMatcher withToString(String str) {
            return new NegatedMatcher(str, this.original);
        }
    }

    public CharMatcher and(CharMatcher charMatcher) {
        return new And(this, (CharMatcher) Preconditions.checkNotNull(charMatcher));
    }

    private static class And extends CharMatcher {
        final CharMatcher first;
        final CharMatcher second;

        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher, com.google.api.client.repackaged.com.google.common.base.Predicate
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        And(CharMatcher charMatcher, CharMatcher charMatcher2) {
            this(charMatcher, charMatcher2, "CharMatcher.and(" + charMatcher + ", " + charMatcher2 + ")");
        }

        And(CharMatcher charMatcher, CharMatcher charMatcher2, String str) {
            super(str);
            this.first = (CharMatcher) Preconditions.checkNotNull(charMatcher);
            this.second = (CharMatcher) Preconditions.checkNotNull(charMatcher2);
        }

        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return this.first.matches(c) && this.second.matches(c);
        }

        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
        void setBits(BitSet bitSet) {
            BitSet bitSet2 = new BitSet();
            this.first.setBits(bitSet2);
            BitSet bitSet3 = new BitSet();
            this.second.setBits(bitSet3);
            bitSet2.and(bitSet3);
            bitSet.or(bitSet2);
        }

        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
        CharMatcher withToString(String str) {
            return new And(this.first, this.second, str);
        }
    }

    public CharMatcher or(CharMatcher charMatcher) {
        return new Or(this, (CharMatcher) Preconditions.checkNotNull(charMatcher));
    }

    private static class Or extends CharMatcher {
        final CharMatcher first;
        final CharMatcher second;

        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher, com.google.api.client.repackaged.com.google.common.base.Predicate
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        Or(CharMatcher charMatcher, CharMatcher charMatcher2, String str) {
            super(str);
            this.first = (CharMatcher) Preconditions.checkNotNull(charMatcher);
            this.second = (CharMatcher) Preconditions.checkNotNull(charMatcher2);
        }

        Or(CharMatcher charMatcher, CharMatcher charMatcher2) {
            this(charMatcher, charMatcher2, "CharMatcher.or(" + charMatcher + ", " + charMatcher2 + ")");
        }

        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
        void setBits(BitSet bitSet) {
            this.first.setBits(bitSet);
            this.second.setBits(bitSet);
        }

        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return this.first.matches(c) || this.second.matches(c);
        }

        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
        CharMatcher withToString(String str) {
            return new Or(this.first, this.second, str);
        }
    }

    public CharMatcher precomputed() {
        return Platform.precomputeCharMatcher(this);
    }

    CharMatcher withToString(String str) {
        throw new UnsupportedOperationException();
    }

    CharMatcher precomputedInternal() {
        String str;
        BitSet bitSet = new BitSet();
        setBits(bitSet);
        int cardinality = bitSet.cardinality();
        if (cardinality * 2 <= 65536) {
            return precomputedPositive(cardinality, bitSet, this.description);
        }
        bitSet.flip(0, 65536);
        int i = 65536 - cardinality;
        if (this.description.endsWith(".negate()")) {
            str = this.description.substring(0, r1.length() - 9);
        } else {
            str = this.description + ".negate()";
        }
        return new NegatedFastMatcher(toString(), precomputedPositive(i, bitSet, str));
    }

    static abstract class FastMatcher extends CharMatcher {
        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
        public final CharMatcher precomputed() {
            return this;
        }

        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher, com.google.api.client.repackaged.com.google.common.base.Predicate
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        FastMatcher() {
        }

        FastMatcher(String str) {
            super(str);
        }

        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return new NegatedFastMatcher(this);
        }
    }

    static final class NegatedFastMatcher extends NegatedMatcher {
        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
        public final CharMatcher precomputed() {
            return this;
        }

        NegatedFastMatcher(CharMatcher charMatcher) {
            super(charMatcher);
        }

        NegatedFastMatcher(String str, CharMatcher charMatcher) {
            super(str, charMatcher);
        }

        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher.NegatedMatcher, com.google.api.client.repackaged.com.google.common.base.CharMatcher
        CharMatcher withToString(String str) {
            return new NegatedFastMatcher(str, this.original);
        }
    }

    private static CharMatcher precomputedPositive(int i, BitSet bitSet, String str) {
        if (i == 0) {
            return NONE;
        }
        if (i == 1) {
            return is((char) bitSet.nextSetBit(0));
        }
        if (i != 2) {
            return isSmall(i, bitSet.length()) ? SmallCharMatcher.from(bitSet, str) : new BitSetMatcher(bitSet, str);
        }
        char nextSetBit = (char) bitSet.nextSetBit(0);
        return isEither(nextSetBit, (char) bitSet.nextSetBit(nextSetBit + 1));
    }

    private static class BitSetMatcher extends FastMatcher {
        private final BitSet table;

        private BitSetMatcher(BitSet bitSet, String str) {
            super(str);
            this.table = bitSet.length() + 64 < bitSet.size() ? (BitSet) bitSet.clone() : bitSet;
        }

        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return this.table.get(c);
        }

        @Override // com.google.api.client.repackaged.com.google.common.base.CharMatcher
        void setBits(BitSet bitSet) {
            bitSet.or(this.table);
        }
    }

    void setBits(BitSet bitSet) {
        for (int i = 65535; i >= 0; i--) {
            if (matches((char) i)) {
                bitSet.set(i);
            }
        }
    }

    public boolean matchesAnyOf(CharSequence charSequence) {
        return !matchesNoneOf(charSequence);
    }

    public boolean matchesAllOf(CharSequence charSequence) {
        for (int length = charSequence.length() - 1; length >= 0; length--) {
            if (!matches(charSequence.charAt(length))) {
                return false;
            }
        }
        return true;
    }

    public boolean matchesNoneOf(CharSequence charSequence) {
        return indexIn(charSequence) == -1;
    }

    public int indexIn(CharSequence charSequence) {
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (matches(charSequence.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    public int indexIn(CharSequence charSequence, int i) {
        int length = charSequence.length();
        Preconditions.checkPositionIndex(i, length);
        while (i < length) {
            if (matches(charSequence.charAt(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public int lastIndexIn(CharSequence charSequence) {
        for (int length = charSequence.length() - 1; length >= 0; length--) {
            if (matches(charSequence.charAt(length))) {
                return length;
            }
        }
        return -1;
    }

    public int countIn(CharSequence charSequence) {
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            if (matches(charSequence.charAt(i2))) {
                i++;
            }
        }
        return i;
    }

    @CheckReturnValue
    public String removeFrom(CharSequence charSequence) {
        String charSequence2 = charSequence.toString();
        int indexIn = indexIn(charSequence2);
        if (indexIn == -1) {
            return charSequence2;
        }
        char[] charArray = charSequence2.toCharArray();
        int i = 1;
        while (true) {
            indexIn++;
            while (indexIn != charArray.length) {
                if (matches(charArray[indexIn])) {
                    break;
                }
                charArray[indexIn - i] = charArray[indexIn];
                indexIn++;
            }
            return new String(charArray, 0, indexIn - i);
            i++;
        }
    }

    @CheckReturnValue
    public String retainFrom(CharSequence charSequence) {
        return negate().removeFrom(charSequence);
    }

    @CheckReturnValue
    public String replaceFrom(CharSequence charSequence, char c) {
        String charSequence2 = charSequence.toString();
        int indexIn = indexIn(charSequence2);
        if (indexIn == -1) {
            return charSequence2;
        }
        char[] charArray = charSequence2.toCharArray();
        charArray[indexIn] = c;
        while (true) {
            indexIn++;
            if (indexIn < charArray.length) {
                if (matches(charArray[indexIn])) {
                    charArray[indexIn] = c;
                }
            } else {
                return new String(charArray);
            }
        }
    }

    @CheckReturnValue
    public String replaceFrom(CharSequence charSequence, CharSequence charSequence2) {
        int length = charSequence2.length();
        if (length == 0) {
            return removeFrom(charSequence);
        }
        int i = 0;
        if (length == 1) {
            return replaceFrom(charSequence, charSequence2.charAt(0));
        }
        String charSequence3 = charSequence.toString();
        int indexIn = indexIn(charSequence3);
        if (indexIn == -1) {
            return charSequence3;
        }
        int length2 = charSequence3.length();
        StringBuilder sb = new StringBuilder(((length2 * 3) / 2) + 16);
        do {
            sb.append((CharSequence) charSequence3, i, indexIn);
            sb.append(charSequence2);
            i = indexIn + 1;
            indexIn = indexIn(charSequence3, i);
        } while (indexIn != -1);
        sb.append((CharSequence) charSequence3, i, length2);
        return sb.toString();
    }

    @CheckReturnValue
    public String trimFrom(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && matches(charSequence.charAt(i))) {
            i++;
        }
        int i2 = length - 1;
        while (i2 > i && matches(charSequence.charAt(i2))) {
            i2--;
        }
        return charSequence.subSequence(i, i2 + 1).toString();
    }

    @CheckReturnValue
    public String trimLeadingFrom(CharSequence charSequence) {
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!matches(charSequence.charAt(i))) {
                return charSequence.subSequence(i, length).toString();
            }
        }
        return "";
    }

    @CheckReturnValue
    public String trimTrailingFrom(CharSequence charSequence) {
        for (int length = charSequence.length() - 1; length >= 0; length--) {
            if (!matches(charSequence.charAt(length))) {
                return charSequence.subSequence(0, length + 1).toString();
            }
        }
        return "";
    }

    @CheckReturnValue
    public String collapseFrom(CharSequence charSequence, char c) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (matches(charAt)) {
                if (charAt != c || (i != length - 1 && matches(charSequence.charAt(i + 1)))) {
                    StringBuilder sb = new StringBuilder(length);
                    sb.append(charSequence.subSequence(0, i));
                    sb.append(c);
                    return finishCollapseFrom(charSequence, i + 1, length, c, sb, true);
                }
                i++;
            }
            i++;
        }
        return charSequence.toString();
    }

    @CheckReturnValue
    public String trimAndCollapseFrom(CharSequence charSequence, char c) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && matches(charSequence.charAt(i))) {
            i++;
        }
        int i2 = length - 1;
        int i3 = i2;
        while (i3 > i && matches(charSequence.charAt(i3))) {
            i3--;
        }
        if (i == 0 && i3 == i2) {
            return collapseFrom(charSequence, c);
        }
        int i4 = i3 + 1;
        return finishCollapseFrom(charSequence, i, i4, c, new StringBuilder(i4 - i), false);
    }

    private String finishCollapseFrom(CharSequence charSequence, int i, int i2, char c, StringBuilder sb, boolean z) {
        while (i < i2) {
            char charAt = charSequence.charAt(i);
            if (!matches(charAt)) {
                sb.append(charAt);
                z = false;
            } else if (!z) {
                sb.append(c);
                z = true;
            }
            i++;
        }
        return sb.toString();
    }

    @Override // com.google.api.client.repackaged.com.google.common.base.Predicate
    @Deprecated
    public boolean apply(Character ch) {
        return matches(ch.charValue());
    }

    public String toString() {
        return this.description;
    }
}
