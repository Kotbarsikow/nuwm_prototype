package org.simmetrics.simplifiers;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public final class Simplifiers {

    static final class ChainSimplifier implements Simplifier {
        private final List<Simplifier> simplifiers;

        ChainSimplifier(List<Simplifier> list) {
            Preconditions.checkArgument(!list.contains(null));
            this.simplifiers = ImmutableList.copyOf((Collection) list);
        }

        List<Simplifier> getSimplifiers() {
            return this.simplifiers;
        }

        @Override // org.simmetrics.simplifiers.Simplifier
        public String simplify(String str) {
            Preconditions.checkNotNull(str);
            Iterator<Simplifier> it = this.simplifiers.iterator();
            while (it.hasNext()) {
                str = it.next().simplify(str);
            }
            return str;
        }

        public String toString() {
            return Joiner.on(" -> ").join(this.simplifiers);
        }
    }

    static final class Normalize implements Simplifier {
        private final Normalizer.Form form;

        public Normalize(Normalizer.Form form) {
            this.form = form;
        }

        @Override // org.simmetrics.simplifiers.Simplifier
        public String simplify(String str) {
            return Normalizer.normalize(str, this.form);
        }

        public String toString() {
            return "Normalize[" + this.form + "]";
        }

        Normalizer.Form getForm() {
            return this.form;
        }
    }

    static final class RemoveDiacritics implements Simplifier {
        private static final Pattern DIACRITICS_AND_FRIENDS = Pattern.compile("[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+");

        RemoveDiacritics() {
        }

        @Override // org.simmetrics.simplifiers.Simplifier
        public String simplify(String str) {
            return DIACRITICS_AND_FRIENDS.matcher(Normalizer.normalize(str, Normalizer.Form.NFD)).replaceAll("");
        }

        public String toString() {
            return "RemoveDiacritics";
        }
    }

    static final class ReplaceAll implements Simplifier {
        private final Pattern pattern;
        private final String repplacement;

        public ReplaceAll(Pattern pattern, String str) {
            Preconditions.checkNotNull(str);
            Preconditions.checkNotNull(pattern);
            this.pattern = pattern;
            this.repplacement = str;
        }

        @Override // org.simmetrics.simplifiers.Simplifier
        public String simplify(String str) {
            return this.pattern.matcher(str).replaceAll(this.repplacement);
        }

        public String toString() {
            return "Replace [" + this.pattern + " -> '" + this.repplacement + "' ]";
        }
    }

    static final class ToLowerCase implements Simplifier {
        private final Locale locale;

        ToLowerCase(Locale locale) {
            this.locale = locale;
        }

        @Override // org.simmetrics.simplifiers.Simplifier
        public String simplify(String str) {
            return str.toLowerCase(this.locale);
        }

        public String toString() {
            return "ToLowerCase [locale=" + this.locale + "]";
        }
    }

    static final class ToUpperCase implements Simplifier {
        private final Locale locale;

        ToUpperCase(Locale locale) {
            this.locale = locale;
        }

        @Override // org.simmetrics.simplifiers.Simplifier
        public String simplify(String str) {
            return str.toUpperCase(this.locale);
        }

        public String toString() {
            return "ToUpperCase [locale=" + this.locale + "]";
        }
    }

    public static Simplifier chain(List<Simplifier> list) {
        if (list.size() == 1) {
            return list.get(0);
        }
        return new ChainSimplifier(flatten(list));
    }

    public static Simplifier chain(Simplifier simplifier, Simplifier... simplifierArr) {
        Preconditions.checkArgument(simplifier != null);
        return simplifierArr.length == 0 ? simplifier : chain(Lists.asList(simplifier, simplifierArr));
    }

    private static List<Simplifier> flatten(List<Simplifier> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (Simplifier simplifier : list) {
            if (simplifier instanceof ChainSimplifier) {
                arrayList.addAll(((ChainSimplifier) simplifier).getSimplifiers());
            } else {
                arrayList.add(simplifier);
            }
        }
        return arrayList;
    }

    public static Simplifier normalize(Normalizer.Form form) {
        return new Normalize(form);
    }

    public static Simplifier removeAll(String str) {
        return removeAll(Pattern.compile(str));
    }

    public static Simplifier removeAll(Pattern pattern) {
        return new ReplaceAll(pattern, "");
    }

    public static Simplifier removeDiacritics() {
        return new RemoveDiacritics();
    }

    public static Simplifier removeNonWord() {
        return removeNonWord("");
    }

    public static Simplifier removeNonWord(String str) {
        return removeAll("\\W+");
    }

    public static Simplifier replaceAll(String str, String str2) {
        return replaceAll(Pattern.compile(str), str2);
    }

    public static Simplifier replaceAll(Pattern pattern, String str) {
        return new ReplaceAll(pattern, str);
    }

    public static Simplifier replaceNonWord() {
        return replaceNonWord(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
    }

    public static Simplifier replaceNonWord(String str) {
        return replaceAll("\\W", str);
    }

    public static Simplifier toLowerCase() {
        return toLowerCase(Locale.getDefault());
    }

    public static Simplifier toLowerCase(Locale locale) {
        return new ToLowerCase(locale);
    }

    public static Simplifier toUpperCase() {
        return toUpperCase(Locale.getDefault());
    }

    public static Simplifier toUpperCase(Locale locale) {
        return new ToUpperCase(locale);
    }

    private Simplifiers() {
    }
}
