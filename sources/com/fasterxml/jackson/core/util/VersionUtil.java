package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.Version;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.regex.Pattern;
import org.mortbay.util.URIUtil;

/* loaded from: classes.dex */
public class VersionUtil {
    public static final String VERSION_FILE = "VERSION.txt";
    private static final Pattern VERSION_SEPARATOR = Pattern.compile("[-_./;:]");
    private final Version _version;

    protected VersionUtil() {
        Version version;
        try {
            version = versionFor(getClass());
        } catch (Exception unused) {
            System.err.println("ERROR: Failed to load Version information for bundle (via " + getClass().getName() + ").");
            version = null;
        }
        this._version = version == null ? Version.unknownVersion() : version;
    }

    public Version version() {
        return this._version;
    }

    public static Version versionFor(Class<?> cls) {
        String str;
        String str2;
        Version version = null;
        try {
            InputStream resourceAsStream = cls.getResourceAsStream(VERSION_FILE);
            if (resourceAsStream != null) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, "UTF-8"));
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        str2 = bufferedReader.readLine();
                        if (str2 != null) {
                            str2 = str2.trim();
                            str = bufferedReader.readLine();
                            if (str != null) {
                                str = str.trim();
                            }
                        } else {
                            str = null;
                        }
                    } else {
                        str = null;
                        str2 = null;
                    }
                    version = parseVersion(readLine, str2, str);
                    try {
                        resourceAsStream.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } catch (Throwable th) {
                    try {
                        resourceAsStream.close();
                        throw th;
                    } catch (IOException e2) {
                        throw new RuntimeException(e2);
                    }
                }
            }
        } catch (IOException unused) {
        }
        return version == null ? Version.unknownVersion() : version;
    }

    public static Version mavenVersionFor(ClassLoader classLoader, String str, String str2) {
        InputStream resourceAsStream = classLoader.getResourceAsStream("META-INF/maven/" + str.replaceAll("\\.", URIUtil.SLASH) + URIUtil.SLASH + str2 + "/pom.properties");
        if (resourceAsStream != null) {
            try {
                try {
                    Properties properties = new Properties();
                    properties.load(resourceAsStream);
                    Version parseVersion = parseVersion(properties.getProperty("version"), properties.getProperty("groupId"), properties.getProperty("artifactId"));
                    try {
                        resourceAsStream.close();
                    } catch (IOException unused) {
                    }
                    return parseVersion;
                } catch (IOException unused2) {
                    resourceAsStream.close();
                } catch (Throwable th) {
                    try {
                        resourceAsStream.close();
                    } catch (IOException unused3) {
                    }
                    throw th;
                }
            } catch (IOException unused4) {
            }
        }
        return Version.unknownVersion();
    }

    @Deprecated
    public static Version parseVersion(String str) {
        return parseVersion(str, null, null);
    }

    public static Version parseVersion(String str, String str2, String str3) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.length() == 0) {
            return null;
        }
        String[] split = VERSION_SEPARATOR.split(trim);
        return new Version(parseVersionPart(split[0]), split.length > 1 ? parseVersionPart(split[1]) : 0, split.length > 2 ? parseVersionPart(split[2]) : 0, split.length > 3 ? split[3] : null, str2, str3);
    }

    protected static int parseVersionPart(String str) {
        String str2 = str.toString();
        int length = str2.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str2.charAt(i2);
            if (charAt > '9' || charAt < '0') {
                break;
            }
            i = (i * 10) + (charAt - '0');
        }
        return i;
    }
}
