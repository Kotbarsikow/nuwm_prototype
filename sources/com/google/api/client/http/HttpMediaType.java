package com.google.api.client.http;

import com.google.api.client.util.Preconditions;
import j$.util.DesugarCollections;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public final class HttpMediaType {
    private static final Pattern FULL_MEDIA_TYPE_REGEX;
    private static final Pattern PARAMETER_REGEX;
    private String cachedBuildResult;
    private static final Pattern TYPE_REGEX = Pattern.compile("[\\w!#$&.+\\-\\^_]+|[*]");
    private static final Pattern TOKEN_REGEX = Pattern.compile("[\\p{ASCII}&&[^\\p{Cntrl} ;/=\\[\\]\\(\\)\\<\\>\\@\\,\\:\\\"\\?\\=]]+");
    private String type = "application";
    private String subType = "octet-stream";
    private final SortedMap<String, String> parameters = new TreeMap();

    static {
        StringBuilder sb = new StringBuilder(37);
        sb.append("\\s*([^\\s/=;\"]+)/([^\\s/=;\"]+)\\s*(;.*)?");
        FULL_MEDIA_TYPE_REGEX = Pattern.compile(sb.toString(), 32);
        StringBuilder sb2 = new StringBuilder(18);
        sb2.append("\"([^\"]*)\"|[^\\s;\"]*");
        String valueOf = String.valueOf(String.valueOf(sb2.toString()));
        StringBuilder sb3 = new StringBuilder(22 + valueOf.length());
        sb3.append("\\s*;\\s*([^\\s/=;\"]+)=(");
        sb3.append(valueOf);
        sb3.append(")");
        PARAMETER_REGEX = Pattern.compile(sb3.toString());
    }

    public HttpMediaType(String str, String str2) {
        setType(str);
        setSubType(str2);
    }

    public HttpMediaType(String str) {
        fromString(str);
    }

    public HttpMediaType setType(String str) {
        Preconditions.checkArgument(TYPE_REGEX.matcher(str).matches(), "Type contains reserved characters");
        this.type = str;
        this.cachedBuildResult = null;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public HttpMediaType setSubType(String str) {
        Preconditions.checkArgument(TYPE_REGEX.matcher(str).matches(), "Subtype contains reserved characters");
        this.subType = str;
        this.cachedBuildResult = null;
        return this;
    }

    public String getSubType() {
        return this.subType;
    }

    private HttpMediaType fromString(String str) {
        Matcher matcher = FULL_MEDIA_TYPE_REGEX.matcher(str);
        Preconditions.checkArgument(matcher.matches(), "Type must be in the 'maintype/subtype; parameter=value' format");
        setType(matcher.group(1));
        setSubType(matcher.group(2));
        String group = matcher.group(3);
        if (group != null) {
            Matcher matcher2 = PARAMETER_REGEX.matcher(group);
            while (matcher2.find()) {
                String group2 = matcher2.group(1);
                String group3 = matcher2.group(3);
                if (group3 == null) {
                    group3 = matcher2.group(2);
                }
                setParameter(group2, group3);
            }
        }
        return this;
    }

    public HttpMediaType setParameter(String str, String str2) {
        if (str2 == null) {
            removeParameter(str);
            return this;
        }
        Preconditions.checkArgument(TOKEN_REGEX.matcher(str).matches(), "Name contains reserved characters");
        this.cachedBuildResult = null;
        this.parameters.put(str.toLowerCase(), str2);
        return this;
    }

    public String getParameter(String str) {
        return this.parameters.get(str.toLowerCase());
    }

    public HttpMediaType removeParameter(String str) {
        this.cachedBuildResult = null;
        this.parameters.remove(str.toLowerCase());
        return this;
    }

    public void clearParameters() {
        this.cachedBuildResult = null;
        this.parameters.clear();
    }

    public Map<String, String> getParameters() {
        return DesugarCollections.unmodifiableMap(this.parameters);
    }

    static boolean matchesToken(String str) {
        return TOKEN_REGEX.matcher(str).matches();
    }

    private static String quoteString(String str) {
        String valueOf = String.valueOf(String.valueOf(str.replace("\\", "\\\\").replace("\"", "\\\"")));
        StringBuilder sb = new StringBuilder(valueOf.length() + 2);
        sb.append("\"");
        sb.append(valueOf);
        sb.append("\"");
        return sb.toString();
    }

    public String build() {
        String str = this.cachedBuildResult;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.type);
        sb.append('/');
        sb.append(this.subType);
        SortedMap<String, String> sortedMap = this.parameters;
        if (sortedMap != null) {
            for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
                String value = entry.getValue();
                sb.append("; ");
                sb.append(entry.getKey());
                sb.append("=");
                if (!matchesToken(value)) {
                    value = quoteString(value);
                }
                sb.append(value);
            }
        }
        String sb2 = sb.toString();
        this.cachedBuildResult = sb2;
        return sb2;
    }

    public String toString() {
        return build();
    }

    public boolean equalsIgnoreParameters(HttpMediaType httpMediaType) {
        return httpMediaType != null && getType().equalsIgnoreCase(httpMediaType.getType()) && getSubType().equalsIgnoreCase(httpMediaType.getSubType());
    }

    public static boolean equalsIgnoreParameters(String str, String str2) {
        return (str == null && str2 == null) || !(str == null || str2 == null || !new HttpMediaType(str).equalsIgnoreParameters(new HttpMediaType(str2)));
    }

    public HttpMediaType setCharsetParameter(Charset charset) {
        setParameter("charset", charset == null ? null : charset.name());
        return this;
    }

    public Charset getCharsetParameter() {
        String parameter = getParameter("charset");
        if (parameter == null) {
            return null;
        }
        return Charset.forName(parameter);
    }

    public int hashCode() {
        return build().hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof HttpMediaType)) {
            return false;
        }
        HttpMediaType httpMediaType = (HttpMediaType) obj;
        return equalsIgnoreParameters(httpMediaType) && this.parameters.equals(httpMediaType.parameters);
    }
}
