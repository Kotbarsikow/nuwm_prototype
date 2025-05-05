package org.apache.commons.codec.language.bm;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import j$.util.DesugarCollections;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.apache.commons.codec.language.bm.Languages;

/* loaded from: classes3.dex */
public class Rule {
    public static final String ALL = "ALL";
    private static final String DOUBLE_QUOTE = "\"";
    private static final String HASH_INCLUDE = "#include";
    private final RPattern lContext;
    private final String pattern;
    private final PhonemeExpr phoneme;
    private final RPattern rContext;
    public static final RPattern ALL_STRINGS_RMATCHER = new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule.1
        @Override // org.apache.commons.codec.language.bm.Rule.RPattern
        public boolean isMatch(CharSequence charSequence) {
            return true;
        }

        AnonymousClass1() {
        }
    };
    private static final Map<NameType, Map<RuleType, Map<String, Map<String, List<Rule>>>>> RULES = new EnumMap(NameType.class);

    public interface PhonemeExpr {
        Iterable<Phoneme> getPhonemes();
    }

    public interface RPattern {
        boolean isMatch(CharSequence charSequence);
    }

    public static final class Phoneme implements PhonemeExpr {
        public static final Comparator<Phoneme> COMPARATOR = new Comparator<Phoneme>() { // from class: org.apache.commons.codec.language.bm.Rule.Phoneme.1
            AnonymousClass1() {
            }

            @Override // java.util.Comparator
            public int compare(Phoneme phoneme, Phoneme phoneme2) {
                for (int i = 0; i < phoneme.phonemeText.length(); i++) {
                    if (i >= phoneme2.phonemeText.length()) {
                        return 1;
                    }
                    int charAt = phoneme.phonemeText.charAt(i) - phoneme2.phonemeText.charAt(i);
                    if (charAt != 0) {
                        return charAt;
                    }
                }
                return phoneme.phonemeText.length() < phoneme2.phonemeText.length() ? -1 : 0;
            }
        };
        private final Languages.LanguageSet languages;
        private final StringBuilder phonemeText;

        /* renamed from: org.apache.commons.codec.language.bm.Rule$Phoneme$1 */
        static class AnonymousClass1 implements Comparator<Phoneme> {
            AnonymousClass1() {
            }

            @Override // java.util.Comparator
            public int compare(Phoneme phoneme, Phoneme phoneme2) {
                for (int i = 0; i < phoneme.phonemeText.length(); i++) {
                    if (i >= phoneme2.phonemeText.length()) {
                        return 1;
                    }
                    int charAt = phoneme.phonemeText.charAt(i) - phoneme2.phonemeText.charAt(i);
                    if (charAt != 0) {
                        return charAt;
                    }
                }
                return phoneme.phonemeText.length() < phoneme2.phonemeText.length() ? -1 : 0;
            }
        }

        public Phoneme(CharSequence charSequence, Languages.LanguageSet languageSet) {
            this.phonemeText = new StringBuilder(charSequence);
            this.languages = languageSet;
        }

        public Phoneme(Phoneme phoneme, Phoneme phoneme2) {
            this(phoneme.phonemeText, phoneme.languages);
            this.phonemeText.append((CharSequence) phoneme2.phonemeText);
        }

        public Phoneme(Phoneme phoneme, Phoneme phoneme2, Languages.LanguageSet languageSet) {
            this(phoneme.phonemeText, languageSet);
            this.phonemeText.append((CharSequence) phoneme2.phonemeText);
        }

        public Phoneme append(CharSequence charSequence) {
            this.phonemeText.append(charSequence);
            return this;
        }

        public Languages.LanguageSet getLanguages() {
            return this.languages;
        }

        @Override // org.apache.commons.codec.language.bm.Rule.PhonemeExpr
        public Iterable<Phoneme> getPhonemes() {
            return Collections.singleton(this);
        }

        public CharSequence getPhonemeText() {
            return this.phonemeText;
        }

        @Deprecated
        public Phoneme join(Phoneme phoneme) {
            return new Phoneme(this.phonemeText.toString() + phoneme.phonemeText.toString(), this.languages.restrictTo(phoneme.languages));
        }

        public Phoneme mergeWithLanguage(Languages.LanguageSet languageSet) {
            return new Phoneme(this.phonemeText.toString(), this.languages.merge(languageSet));
        }

        public String toString() {
            return this.phonemeText.toString() + "[" + this.languages + "]";
        }
    }

    public static final class PhonemeList implements PhonemeExpr {
        private final List<Phoneme> phonemes;

        public PhonemeList(List<Phoneme> list) {
            this.phonemes = list;
        }

        @Override // org.apache.commons.codec.language.bm.Rule.PhonemeExpr
        public List<Phoneme> getPhonemes() {
            return this.phonemes;
        }
    }

    /* renamed from: org.apache.commons.codec.language.bm.Rule$1 */
    static class AnonymousClass1 implements RPattern {
        @Override // org.apache.commons.codec.language.bm.Rule.RPattern
        public boolean isMatch(CharSequence charSequence) {
            return true;
        }

        AnonymousClass1() {
        }
    }

    static {
        for (NameType nameType : NameType.values()) {
            EnumMap enumMap = new EnumMap(RuleType.class);
            for (RuleType ruleType : RuleType.values()) {
                HashMap hashMap = new HashMap();
                for (String str : Languages.getInstance(nameType).getLanguages()) {
                    try {
                        hashMap.put(str, parseRules(createScanner(nameType, ruleType, str), createResourceName(nameType, ruleType, str)));
                    } catch (IllegalStateException e) {
                        throw new IllegalStateException("Problem processing " + createResourceName(nameType, ruleType, str), e);
                    }
                }
                if (!ruleType.equals(RuleType.RULES)) {
                    hashMap.put("common", parseRules(createScanner(nameType, ruleType, "common"), createResourceName(nameType, ruleType, "common")));
                }
                enumMap.put((EnumMap) ruleType, (RuleType) DesugarCollections.unmodifiableMap(hashMap));
            }
            RULES.put(nameType, DesugarCollections.unmodifiableMap(enumMap));
        }
    }

    public static boolean contains(CharSequence charSequence, char c) {
        for (int i = 0; i < charSequence.length(); i++) {
            if (charSequence.charAt(i) == c) {
                return true;
            }
        }
        return false;
    }

    private static String createResourceName(NameType nameType, RuleType ruleType, String str) {
        return String.format("org/apache/commons/codec/language/bm/%s_%s_%s.txt", nameType.getName(), ruleType.getName(), str);
    }

    private static Scanner createScanner(NameType nameType, RuleType ruleType, String str) {
        String createResourceName = createResourceName(nameType, ruleType, str);
        InputStream resourceAsStream = Languages.class.getClassLoader().getResourceAsStream(createResourceName);
        if (resourceAsStream == null) {
            throw new IllegalArgumentException("Unable to load resource: " + createResourceName);
        }
        return new Scanner(resourceAsStream, "UTF-8");
    }

    private static Scanner createScanner(String str) {
        String format = String.format("org/apache/commons/codec/language/bm/%s.txt", str);
        InputStream resourceAsStream = Languages.class.getClassLoader().getResourceAsStream(format);
        if (resourceAsStream == null) {
            throw new IllegalArgumentException("Unable to load resource: " + format);
        }
        return new Scanner(resourceAsStream, "UTF-8");
    }

    public static boolean endsWith(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence2.length() > charSequence.length()) {
            return false;
        }
        int length = charSequence.length() - 1;
        for (int length2 = charSequence2.length() - 1; length2 >= 0; length2--) {
            if (charSequence.charAt(length) != charSequence2.charAt(length2)) {
                return false;
            }
            length--;
        }
        return true;
    }

    public static List<Rule> getInstance(NameType nameType, RuleType ruleType, Languages.LanguageSet languageSet) {
        Map<String, List<Rule>> instanceMap = getInstanceMap(nameType, ruleType, languageSet);
        ArrayList arrayList = new ArrayList();
        Iterator<List<Rule>> it = instanceMap.values().iterator();
        while (it.hasNext()) {
            arrayList.addAll(it.next());
        }
        return arrayList;
    }

    public static List<Rule> getInstance(NameType nameType, RuleType ruleType, String str) {
        return getInstance(nameType, ruleType, Languages.LanguageSet.from(new HashSet(Arrays.asList(str))));
    }

    public static Map<String, List<Rule>> getInstanceMap(NameType nameType, RuleType ruleType, Languages.LanguageSet languageSet) {
        return getInstanceMap(nameType, ruleType, languageSet.isSingleton() ? languageSet.getAny() : Languages.ANY);
    }

    public static Map<String, List<Rule>> getInstanceMap(NameType nameType, RuleType ruleType, String str) {
        Map<String, List<Rule>> map = RULES.get(nameType).get(ruleType).get(str);
        if (map != null) {
            return map;
        }
        throw new IllegalArgumentException(String.format("No rules found for %s, %s, %s.", nameType.getName(), ruleType.getName(), str));
    }

    private static Phoneme parsePhoneme(String str) {
        int indexOf = str.indexOf("[");
        if (indexOf >= 0) {
            if (!str.endsWith("]")) {
                throw new IllegalArgumentException("Phoneme expression contains a '[' but does not end in ']'");
            }
            return new Phoneme(str.substring(0, indexOf), Languages.LanguageSet.from(new HashSet(Arrays.asList(str.substring(indexOf + 1, str.length() - 1).split("[+]")))));
        }
        return new Phoneme(str, Languages.ANY_LANGUAGE);
    }

    private static PhonemeExpr parsePhonemeExpr(String str) {
        if (str.startsWith("(")) {
            if (!str.endsWith(")")) {
                throw new IllegalArgumentException("Phoneme starts with '(' so must end with ')'");
            }
            ArrayList arrayList = new ArrayList();
            String substring = str.substring(1, str.length() - 1);
            for (String str2 : substring.split("[|]")) {
                arrayList.add(parsePhoneme(str2));
            }
            if (substring.startsWith("|") || substring.endsWith("|")) {
                arrayList.add(new Phoneme("", Languages.ANY_LANGUAGE));
            }
            return new PhonemeList(arrayList);
        }
        return parsePhoneme(str);
    }

    private static Map<String, List<Rule>> parseRules(Scanner scanner, String str) {
        String str2;
        String stripQuotes;
        String stripQuotes2;
        String stripQuotes3;
        HashMap hashMap = new HashMap();
        int i = 0;
        int i2 = 0;
        boolean z = false;
        while (scanner.hasNextLine()) {
            int i3 = i2 + 1;
            String nextLine = scanner.nextLine();
            if (z) {
                if (nextLine.endsWith("*/")) {
                    z = false;
                }
            } else if (nextLine.startsWith("/*")) {
                z = true;
            } else {
                int indexOf = nextLine.indexOf("//");
                String trim = (indexOf >= 0 ? nextLine.substring(i, indexOf) : nextLine).trim();
                if (trim.length() == 0) {
                    i2 = i3;
                } else if (trim.startsWith(HASH_INCLUDE)) {
                    String trim2 = trim.substring(8).trim();
                    if (trim2.contains(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)) {
                        throw new IllegalArgumentException("Malformed import statement '" + nextLine + "' in " + str);
                    }
                    hashMap.putAll(parseRules(createScanner(trim2), str + "->" + trim2));
                } else {
                    String[] split = trim.split("\\s+");
                    if (split.length != 4) {
                        throw new IllegalArgumentException("Malformed rule statement split into " + split.length + " parts: " + nextLine + " in " + str);
                    }
                    try {
                        stripQuotes = stripQuotes(split[i]);
                        stripQuotes2 = stripQuotes(split[1]);
                        stripQuotes3 = stripQuotes(split[2]);
                        str2 = "' in ";
                    } catch (IllegalArgumentException e) {
                        e = e;
                        str2 = "' in ";
                    }
                    try {
                        AnonymousClass2 anonymousClass2 = new Rule(stripQuotes, stripQuotes2, stripQuotes3, parsePhonemeExpr(stripQuotes(split[3])), i3, str, stripQuotes, stripQuotes2, stripQuotes3) { // from class: org.apache.commons.codec.language.bm.Rule.2
                            private final String loc;
                            private final int myLine;
                            final /* synthetic */ int val$cLine;
                            final /* synthetic */ String val$lCon;
                            final /* synthetic */ String val$location;
                            final /* synthetic */ String val$pat;
                            final /* synthetic */ String val$rCon;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass2(String stripQuotes4, String stripQuotes22, String stripQuotes32, PhonemeExpr phonemeExpr, int i32, String str3, String stripQuotes42, String stripQuotes222, String stripQuotes322) {
                                super(stripQuotes42, stripQuotes222, stripQuotes322, phonemeExpr);
                                this.val$cLine = i32;
                                this.val$location = str3;
                                this.val$pat = stripQuotes42;
                                this.val$lCon = stripQuotes222;
                                this.val$rCon = stripQuotes322;
                                this.myLine = i32;
                                this.loc = str3;
                            }

                            public String toString() {
                                return "Rule{line=" + this.myLine + ", loc='" + this.loc + "', pat='" + this.val$pat + "', lcon='" + this.val$lCon + "', rcon='" + this.val$rCon + "'}";
                            }
                        };
                        String substring = ((Rule) anonymousClass2).pattern.substring(0, 1);
                        List list = (List) hashMap.get(substring);
                        if (list == null) {
                            list = new ArrayList();
                            hashMap.put(substring, list);
                        }
                        list.add(anonymousClass2);
                    } catch (IllegalArgumentException e2) {
                        e = e2;
                        throw new IllegalStateException("Problem parsing line '" + i32 + str2 + str3, e);
                    }
                }
            }
            i2 = i32;
            i = 0;
        }
        return hashMap;
    }

    /* renamed from: org.apache.commons.codec.language.bm.Rule$2 */
    static class AnonymousClass2 extends Rule {
        private final String loc;
        private final int myLine;
        final /* synthetic */ int val$cLine;
        final /* synthetic */ String val$lCon;
        final /* synthetic */ String val$location;
        final /* synthetic */ String val$pat;
        final /* synthetic */ String val$rCon;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String stripQuotes42, String stripQuotes222, String stripQuotes322, PhonemeExpr phonemeExpr, int i32, String str3, String stripQuotes422, String stripQuotes2222, String stripQuotes3222) {
            super(stripQuotes422, stripQuotes2222, stripQuotes3222, phonemeExpr);
            this.val$cLine = i32;
            this.val$location = str3;
            this.val$pat = stripQuotes422;
            this.val$lCon = stripQuotes2222;
            this.val$rCon = stripQuotes3222;
            this.myLine = i32;
            this.loc = str3;
        }

        public String toString() {
            return "Rule{line=" + this.myLine + ", loc='" + this.loc + "', pat='" + this.val$pat + "', lcon='" + this.val$lCon + "', rcon='" + this.val$rCon + "'}";
        }
    }

    private static RPattern pattern(String str) {
        boolean startsWith = str.startsWith("^");
        boolean endsWith = str.endsWith("$");
        int length = str.length();
        if (endsWith) {
            length--;
        }
        String substring = str.substring(startsWith ? 1 : 0, length);
        if (substring.contains("[")) {
            boolean startsWith2 = substring.startsWith("[");
            boolean endsWith2 = substring.endsWith("]");
            if (startsWith2 && endsWith2) {
                String substring2 = substring.substring(1, substring.length() - 1);
                if (!substring2.contains("[")) {
                    boolean startsWith3 = substring2.startsWith("^");
                    if (startsWith3) {
                        substring2 = substring2.substring(1);
                    }
                    boolean z = !startsWith3;
                    if (startsWith && endsWith) {
                        return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule.7
                            final /* synthetic */ String val$bContent;
                            final /* synthetic */ boolean val$shouldMatch;

                            AnonymousClass7(String substring22, boolean z2) {
                                r1 = substring22;
                                r2 = z2;
                            }

                            @Override // org.apache.commons.codec.language.bm.Rule.RPattern
                            public boolean isMatch(CharSequence charSequence) {
                                return charSequence.length() == 1 && Rule.contains(r1, charSequence.charAt(0)) == r2;
                            }
                        };
                    }
                    if (startsWith) {
                        return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule.8
                            final /* synthetic */ String val$bContent;
                            final /* synthetic */ boolean val$shouldMatch;

                            AnonymousClass8(String substring22, boolean z2) {
                                r1 = substring22;
                                r2 = z2;
                            }

                            @Override // org.apache.commons.codec.language.bm.Rule.RPattern
                            public boolean isMatch(CharSequence charSequence) {
                                return charSequence.length() > 0 && Rule.contains(r1, charSequence.charAt(0)) == r2;
                            }
                        };
                    }
                    if (endsWith) {
                        return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule.9
                            final /* synthetic */ String val$bContent;
                            final /* synthetic */ boolean val$shouldMatch;

                            AnonymousClass9(String substring22, boolean z2) {
                                r1 = substring22;
                                r2 = z2;
                            }

                            @Override // org.apache.commons.codec.language.bm.Rule.RPattern
                            public boolean isMatch(CharSequence charSequence) {
                                return charSequence.length() > 0 && Rule.contains(r1, charSequence.charAt(charSequence.length() - 1)) == r2;
                            }
                        };
                    }
                }
            }
        } else {
            if (startsWith && endsWith) {
                if (substring.length() == 0) {
                    return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule.3
                        AnonymousClass3() {
                        }

                        @Override // org.apache.commons.codec.language.bm.Rule.RPattern
                        public boolean isMatch(CharSequence charSequence) {
                            return charSequence.length() == 0;
                        }
                    };
                }
                return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule.4
                    final /* synthetic */ String val$content;

                    AnonymousClass4(String substring3) {
                        r1 = substring3;
                    }

                    @Override // org.apache.commons.codec.language.bm.Rule.RPattern
                    public boolean isMatch(CharSequence charSequence) {
                        return charSequence.equals(r1);
                    }
                };
            }
            if ((startsWith || endsWith) && substring3.length() == 0) {
                return ALL_STRINGS_RMATCHER;
            }
            if (startsWith) {
                return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule.5
                    final /* synthetic */ String val$content;

                    AnonymousClass5(String substring3) {
                        r1 = substring3;
                    }

                    @Override // org.apache.commons.codec.language.bm.Rule.RPattern
                    public boolean isMatch(CharSequence charSequence) {
                        return Rule.startsWith(charSequence, r1);
                    }
                };
            }
            if (endsWith) {
                return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule.6
                    final /* synthetic */ String val$content;

                    AnonymousClass6(String substring3) {
                        r1 = substring3;
                    }

                    @Override // org.apache.commons.codec.language.bm.Rule.RPattern
                    public boolean isMatch(CharSequence charSequence) {
                        return Rule.endsWith(charSequence, r1);
                    }
                };
            }
        }
        return new RPattern(str) { // from class: org.apache.commons.codec.language.bm.Rule.10
            Pattern pattern;
            final /* synthetic */ String val$regex;

            AnonymousClass10(String str2) {
                this.val$regex = str2;
                this.pattern = Pattern.compile(str2);
            }

            @Override // org.apache.commons.codec.language.bm.Rule.RPattern
            public boolean isMatch(CharSequence charSequence) {
                return this.pattern.matcher(charSequence).find();
            }
        };
    }

    /* renamed from: org.apache.commons.codec.language.bm.Rule$3 */
    static class AnonymousClass3 implements RPattern {
        AnonymousClass3() {
        }

        @Override // org.apache.commons.codec.language.bm.Rule.RPattern
        public boolean isMatch(CharSequence charSequence) {
            return charSequence.length() == 0;
        }
    }

    /* renamed from: org.apache.commons.codec.language.bm.Rule$4 */
    static class AnonymousClass4 implements RPattern {
        final /* synthetic */ String val$content;

        AnonymousClass4(String substring3) {
            r1 = substring3;
        }

        @Override // org.apache.commons.codec.language.bm.Rule.RPattern
        public boolean isMatch(CharSequence charSequence) {
            return charSequence.equals(r1);
        }
    }

    /* renamed from: org.apache.commons.codec.language.bm.Rule$5 */
    static class AnonymousClass5 implements RPattern {
        final /* synthetic */ String val$content;

        AnonymousClass5(String substring3) {
            r1 = substring3;
        }

        @Override // org.apache.commons.codec.language.bm.Rule.RPattern
        public boolean isMatch(CharSequence charSequence) {
            return Rule.startsWith(charSequence, r1);
        }
    }

    /* renamed from: org.apache.commons.codec.language.bm.Rule$6 */
    static class AnonymousClass6 implements RPattern {
        final /* synthetic */ String val$content;

        AnonymousClass6(String substring3) {
            r1 = substring3;
        }

        @Override // org.apache.commons.codec.language.bm.Rule.RPattern
        public boolean isMatch(CharSequence charSequence) {
            return Rule.endsWith(charSequence, r1);
        }
    }

    /* renamed from: org.apache.commons.codec.language.bm.Rule$7 */
    static class AnonymousClass7 implements RPattern {
        final /* synthetic */ String val$bContent;
        final /* synthetic */ boolean val$shouldMatch;

        AnonymousClass7(String substring22, boolean z2) {
            r1 = substring22;
            r2 = z2;
        }

        @Override // org.apache.commons.codec.language.bm.Rule.RPattern
        public boolean isMatch(CharSequence charSequence) {
            return charSequence.length() == 1 && Rule.contains(r1, charSequence.charAt(0)) == r2;
        }
    }

    /* renamed from: org.apache.commons.codec.language.bm.Rule$8 */
    static class AnonymousClass8 implements RPattern {
        final /* synthetic */ String val$bContent;
        final /* synthetic */ boolean val$shouldMatch;

        AnonymousClass8(String substring22, boolean z2) {
            r1 = substring22;
            r2 = z2;
        }

        @Override // org.apache.commons.codec.language.bm.Rule.RPattern
        public boolean isMatch(CharSequence charSequence) {
            return charSequence.length() > 0 && Rule.contains(r1, charSequence.charAt(0)) == r2;
        }
    }

    /* renamed from: org.apache.commons.codec.language.bm.Rule$9 */
    static class AnonymousClass9 implements RPattern {
        final /* synthetic */ String val$bContent;
        final /* synthetic */ boolean val$shouldMatch;

        AnonymousClass9(String substring22, boolean z2) {
            r1 = substring22;
            r2 = z2;
        }

        @Override // org.apache.commons.codec.language.bm.Rule.RPattern
        public boolean isMatch(CharSequence charSequence) {
            return charSequence.length() > 0 && Rule.contains(r1, charSequence.charAt(charSequence.length() - 1)) == r2;
        }
    }

    /* renamed from: org.apache.commons.codec.language.bm.Rule$10 */
    static class AnonymousClass10 implements RPattern {
        Pattern pattern;
        final /* synthetic */ String val$regex;

        AnonymousClass10(String str2) {
            this.val$regex = str2;
            this.pattern = Pattern.compile(str2);
        }

        @Override // org.apache.commons.codec.language.bm.Rule.RPattern
        public boolean isMatch(CharSequence charSequence) {
            return this.pattern.matcher(charSequence).find();
        }
    }

    public static boolean startsWith(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence2.length() > charSequence.length()) {
            return false;
        }
        for (int i = 0; i < charSequence2.length(); i++) {
            if (charSequence.charAt(i) != charSequence2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static String stripQuotes(String str) {
        if (str.startsWith(DOUBLE_QUOTE)) {
            str = str.substring(1);
        }
        return str.endsWith(DOUBLE_QUOTE) ? str.substring(0, str.length() - 1) : str;
    }

    public Rule(String str, String str2, String str3, PhonemeExpr phonemeExpr) {
        this.pattern = str;
        this.lContext = pattern(str2 + "$");
        StringBuilder sb = new StringBuilder("^");
        sb.append(str3);
        this.rContext = pattern(sb.toString());
        this.phoneme = phonemeExpr;
    }

    public RPattern getLContext() {
        return this.lContext;
    }

    public String getPattern() {
        return this.pattern;
    }

    public PhonemeExpr getPhoneme() {
        return this.phoneme;
    }

    public RPattern getRContext() {
        return this.rContext;
    }

    public boolean patternAndContextMatches(CharSequence charSequence, int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Can not match pattern at negative indexes");
        }
        int length = this.pattern.length() + i;
        if (length <= charSequence.length() && charSequence.subSequence(i, length).equals(this.pattern) && this.rContext.isMatch(charSequence.subSequence(length, charSequence.length()))) {
            return this.lContext.isMatch(charSequence.subSequence(0, i));
        }
        return false;
    }
}
