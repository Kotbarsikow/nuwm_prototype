package com.google.api.client.http;

import com.google.api.client.repackaged.com.google.common.base.Splitter;
import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Types;
import com.google.api.client.util.escape.CharEscapers;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;
import kotlin.text.Typography;
import org.mortbay.jetty.security.Constraint;
import org.mortbay.util.URIUtil;

/* loaded from: classes2.dex */
public class UriTemplate {
    private static final String COMPOSITE_NON_EXPLODE_JOINER = ",";
    static final Map<Character, CompositeOutput> COMPOSITE_PREFIXES = new HashMap();

    static {
        CompositeOutput.values();
    }

    private enum CompositeOutput {
        PLUS('+', "", UriTemplate.COMPOSITE_NON_EXPLODE_JOINER, false, true),
        HASH('#', "#", UriTemplate.COMPOSITE_NON_EXPLODE_JOINER, false, true),
        DOT('.', ".", ".", false, false),
        FORWARD_SLASH('/', URIUtil.SLASH, URIUtil.SLASH, false, false),
        SEMI_COLON(';', ";", ";", true, false),
        QUERY('?', "?", "&", true, false),
        AMP(Character.valueOf(Typography.amp), "&", "&", true, false),
        SIMPLE(null, "", UriTemplate.COMPOSITE_NON_EXPLODE_JOINER, false, false);

        private final String explodeJoiner;
        private final String outputPrefix;
        private final Character propertyPrefix;
        private final boolean requiresVarAssignment;
        private final boolean reservedExpansion;

        CompositeOutput(Character ch, String str, String str2, boolean z, boolean z2) {
            this.propertyPrefix = ch;
            this.outputPrefix = (String) Preconditions.checkNotNull(str);
            this.explodeJoiner = (String) Preconditions.checkNotNull(str2);
            this.requiresVarAssignment = z;
            this.reservedExpansion = z2;
            if (ch != null) {
                UriTemplate.COMPOSITE_PREFIXES.put(ch, this);
            }
        }

        String getOutputPrefix() {
            return this.outputPrefix;
        }

        String getExplodeJoiner() {
            return this.explodeJoiner;
        }

        boolean requiresVarAssignment() {
            return this.requiresVarAssignment;
        }

        int getVarNameStartIndex() {
            return this.propertyPrefix == null ? 0 : 1;
        }

        String getEncodedValue(String str) {
            if (this.reservedExpansion) {
                return CharEscapers.escapeUriPath(str);
            }
            return CharEscapers.escapeUri(str);
        }

        boolean getReservedExpansion() {
            return this.reservedExpansion;
        }
    }

    static CompositeOutput getCompositeOutput(String str) {
        CompositeOutput compositeOutput = COMPOSITE_PREFIXES.get(Character.valueOf(str.charAt(0)));
        return compositeOutput == null ? CompositeOutput.SIMPLE : compositeOutput;
    }

    private static Map<String, Object> getMap(Object obj) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, Object> entry : Data.mapOf(obj).entrySet()) {
            Object value = entry.getValue();
            if (value != null && !Data.isNull(value)) {
                linkedHashMap.put(entry.getKey(), value);
            }
        }
        return linkedHashMap;
    }

    public static String expand(String str, String str2, Object obj, boolean z) {
        String concat;
        if (str2.startsWith(URIUtil.SLASH)) {
            GenericUrl genericUrl = new GenericUrl(str);
            genericUrl.setRawPath(null);
            String valueOf = String.valueOf(genericUrl.build());
            String valueOf2 = String.valueOf(str2);
            if (valueOf2.length() != 0) {
                concat = valueOf.concat(valueOf2);
                str2 = concat;
            } else {
                str2 = new String(valueOf);
            }
        } else if (!str2.startsWith("http://") && !str2.startsWith("https://")) {
            String valueOf3 = String.valueOf(str);
            String valueOf4 = String.valueOf(str2);
            if (valueOf4.length() != 0) {
                concat = valueOf3.concat(valueOf4);
                str2 = concat;
            } else {
                str2 = new String(valueOf3);
            }
        }
        return expand(str2, obj, z);
    }

    public static String expand(String str, Object obj, boolean z) {
        Object listPropertyValue;
        Map<String, Object> map = getMap(obj);
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            int indexOf = str.indexOf(123, i);
            if (indexOf != -1) {
                sb.append(str.substring(i, indexOf));
                int indexOf2 = str.indexOf(125, indexOf + 2);
                int i2 = indexOf2 + 1;
                String substring = str.substring(indexOf + 1, indexOf2);
                CompositeOutput compositeOutput = getCompositeOutput(substring);
                ListIterator<String> listIterator = Splitter.on(',').splitToList(substring).listIterator();
                boolean z2 = true;
                while (listIterator.hasNext()) {
                    String next = listIterator.next();
                    boolean endsWith = next.endsWith(Constraint.ANY_ROLE);
                    int varNameStartIndex = listIterator.nextIndex() == 1 ? compositeOutput.getVarNameStartIndex() : 0;
                    int length2 = next.length();
                    if (endsWith) {
                        length2--;
                    }
                    String substring2 = next.substring(varNameStartIndex, length2);
                    Object remove = map.remove(substring2);
                    if (remove != null) {
                        if (!z2) {
                            sb.append(compositeOutput.getExplodeJoiner());
                        } else {
                            sb.append(compositeOutput.getOutputPrefix());
                            z2 = false;
                        }
                        if (remove instanceof Iterator) {
                            listPropertyValue = getListPropertyValue(substring2, (Iterator) remove, endsWith, compositeOutput);
                        } else if ((remove instanceof Iterable) || remove.getClass().isArray()) {
                            listPropertyValue = getListPropertyValue(substring2, Types.iterableOf(remove).iterator(), endsWith, compositeOutput);
                        } else if (remove.getClass().isEnum()) {
                            if (FieldInfo.of((Enum<?>) remove).getName() != null) {
                                if (compositeOutput.requiresVarAssignment()) {
                                    remove = String.format("%s=%s", substring2, remove);
                                }
                                remove = CharEscapers.escapeUriPath(remove.toString());
                            }
                            listPropertyValue = remove;
                        } else if (!Data.isValueOfPrimitiveType(remove)) {
                            listPropertyValue = getMapPropertyValue(substring2, getMap(remove), endsWith, compositeOutput);
                        } else {
                            if (compositeOutput.requiresVarAssignment()) {
                                remove = String.format("%s=%s", substring2, remove);
                            }
                            if (compositeOutput.getReservedExpansion()) {
                                listPropertyValue = CharEscapers.escapeUriPathWithoutReserved(remove.toString());
                            } else {
                                listPropertyValue = CharEscapers.escapeUriPath(remove.toString());
                            }
                        }
                        sb.append(listPropertyValue);
                    }
                }
                i = i2;
            } else {
                if (i == 0 && !z) {
                    return str;
                }
                sb.append(str.substring(i));
            }
        }
        if (z) {
            GenericUrl.addQueryParams(map.entrySet(), sb);
        }
        return sb.toString();
    }

    private static String getListPropertyValue(String str, Iterator<?> it, boolean z, CompositeOutput compositeOutput) {
        String str2;
        if (!it.hasNext()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (z) {
            str2 = compositeOutput.getExplodeJoiner();
        } else {
            if (compositeOutput.requiresVarAssignment()) {
                sb.append(CharEscapers.escapeUriPath(str));
                sb.append("=");
            }
            str2 = COMPOSITE_NON_EXPLODE_JOINER;
        }
        while (it.hasNext()) {
            if (z && compositeOutput.requiresVarAssignment()) {
                sb.append(CharEscapers.escapeUriPath(str));
                sb.append("=");
            }
            sb.append(compositeOutput.getEncodedValue(it.next().toString()));
            if (it.hasNext()) {
                sb.append(str2);
            }
        }
        return sb.toString();
    }

    private static String getMapPropertyValue(String str, Map<String, Object> map, boolean z, CompositeOutput compositeOutput) {
        String str2;
        if (map.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String str3 = "=";
        if (z) {
            str2 = compositeOutput.getExplodeJoiner();
        } else {
            if (compositeOutput.requiresVarAssignment()) {
                sb.append(CharEscapers.escapeUriPath(str));
                sb.append("=");
            }
            str3 = COMPOSITE_NON_EXPLODE_JOINER;
            str2 = COMPOSITE_NON_EXPLODE_JOINER;
        }
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> next = it.next();
            String encodedValue = compositeOutput.getEncodedValue(next.getKey());
            String encodedValue2 = compositeOutput.getEncodedValue(next.getValue().toString());
            sb.append(encodedValue);
            sb.append(str3);
            sb.append(encodedValue2);
            if (it.hasNext()) {
                sb.append(str2);
            }
        }
        return sb.toString();
    }
}
