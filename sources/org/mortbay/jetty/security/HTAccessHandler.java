package org.mortbay.jetty.security;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.language.bm.Languages;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Request;
import org.mortbay.log.Log;
import org.mortbay.log.Logger;
import org.mortbay.resource.Resource;

/* loaded from: classes3.dex */
public class HTAccessHandler extends SecurityHandler {
    static /* synthetic */ Class class$org$mortbay$jetty$security$HTAccessHandler;
    private static Logger log;
    private Handler protegee;
    String _default = null;
    String _accessFile = ".htaccess";
    transient HashMap _htCache = new HashMap();

    static {
        Class cls = class$org$mortbay$jetty$security$HTAccessHandler;
        if (cls == null) {
            cls = class$("org.mortbay.jetty.security.HTAccessHandler");
            class$org$mortbay$jetty$security$HTAccessHandler = cls;
        }
        log = Log.getLogger(cls.getName());
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    class DummyPrincipal implements Principal {
        private String _userName;

        public DummyPrincipal(String str) {
            this._userName = str;
        }

        @Override // java.security.Principal
        public String getName() {
            return this._userName;
        }

        @Override // java.security.Principal
        public String toString() {
            return getName();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:99:0x01a3, code lost:
    
        if (r8.getLastModified() != r12.lastModified()) goto L62;
     */
    /* JADX WARN: Removed duplicated region for block: B:106:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x016f A[Catch: Exception -> 0x02b0, TryCatch #1 {Exception -> 0x02b0, blocks: (B:16:0x00a2, B:21:0x00b2, B:23:0x00d7, B:25:0x00fb, B:27:0x0101, B:32:0x0110, B:34:0x0114, B:36:0x011e, B:41:0x012b, B:43:0x0135, B:45:0x0150, B:49:0x016f, B:51:0x0177, B:52:0x018f, B:102:0x02a3, B:104:0x02a7, B:109:0x0108, B:114:0x00ac), top: B:15:0x00a2 }] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:96:? A[RETURN, SYNTHETIC] */
    @Override // org.mortbay.jetty.security.SecurityHandler, org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.Handler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void handle(java.lang.String r19, javax.servlet.http.HttpServletRequest r20, javax.servlet.http.HttpServletResponse r21, int r22) throws java.io.IOException, javax.servlet.ServletException {
        /*
            Method dump skipped, instructions count: 707
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.security.HTAccessHandler.handle(java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, int):void");
    }

    private void callWrappedHandler(String str, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int i) throws IOException, ServletException {
        Handler handler = getHandler();
        if (handler != null) {
            handler.handle(str, httpServletRequest, httpServletResponse, i);
        }
    }

    public Principal getPrincipal(String str, UserRealm userRealm) {
        if (userRealm == null) {
            return new DummyPrincipal(str);
        }
        return userRealm.getPrincipal(str);
    }

    public void setDefault(String str) {
        this._default = str;
    }

    public void setAccessFile(String str) {
        if (str == null) {
            this._accessFile = ".htaccess";
        } else {
            this._accessFile = str;
        }
    }

    private static class HTAccess {
        static final int ALL = 1;
        static final int ANY = 0;
        static final String GROUP = "group";
        static final String USER = "user";
        static final String VALID_USER = "valid-user";
        boolean _forbidden;
        String _groupFile;
        long _groupModified;
        Resource _groupResource;
        long _lastModified;
        String _name;
        int _order;
        String _requireName;
        String _type;
        String _userFile;
        long _userModified;
        Resource _userResource;
        HashMap _users = null;
        HashMap _groups = null;
        int _satisfy = 0;
        HashMap _methods = new HashMap();
        HashSet _requireEntities = new HashSet();
        ArrayList _allowList = new ArrayList();
        ArrayList _denyList = new ArrayList();

        public HTAccess(Resource resource) {
            this._forbidden = false;
            try {
                parse(new BufferedReader(new InputStreamReader(resource.getInputStream())));
                this._lastModified = resource.lastModified();
                String str = this._userFile;
                if (str != null) {
                    Resource newResource = Resource.newResource(str);
                    this._userResource = newResource;
                    if (newResource.exists()) {
                        if (HTAccessHandler.log.isDebugEnabled()) {
                            HTAccessHandler.log.debug(new StringBuffer("user file: ").append(this._userResource).toString(), null, null);
                        }
                    } else {
                        this._forbidden = true;
                        HTAccessHandler.log.warn(new StringBuffer("Could not find ht user file: ").append(this._userFile).toString(), null, null);
                    }
                }
                String str2 = this._groupFile;
                if (str2 != null) {
                    Resource newResource2 = Resource.newResource(str2);
                    this._groupResource = newResource2;
                    if (newResource2.exists()) {
                        if (HTAccessHandler.log.isDebugEnabled()) {
                            HTAccessHandler.log.debug(new StringBuffer("group file: ").append(this._groupResource).toString(), null, null);
                        }
                    } else {
                        this._forbidden = true;
                        HTAccessHandler.log.warn(new StringBuffer("Could not find ht group file: ").append(this._groupResource).toString(), null, null);
                    }
                }
            } catch (IOException e) {
                this._forbidden = true;
                HTAccessHandler.log.warn("LogSupport.EXCEPTION", e);
            }
        }

        public boolean isForbidden() {
            return this._forbidden;
        }

        public HashMap getMethods() {
            return this._methods;
        }

        public long getLastModified() {
            return this._lastModified;
        }

        public Resource getUserResource() {
            return this._userResource;
        }

        public Resource getGroupResource() {
            return this._groupResource;
        }

        public int getSatisfy() {
            return this._satisfy;
        }

        public String getName() {
            return this._name;
        }

        public String getType() {
            return this._type;
        }

        public boolean checkAccess(String str, String str2) {
            boolean z;
            boolean z2;
            if (this._allowList.size() == 0 && this._denyList.size() == 0) {
                return true;
            }
            for (int i = 0; i < this._allowList.size(); i++) {
                String str3 = (String) this._allowList.get(i);
                if (!str3.equals("all")) {
                    char charAt = str3.charAt(0);
                    if (charAt >= '0' && charAt <= '9') {
                        if (!str2.startsWith(str3)) {
                        }
                    } else if (!str.endsWith(str3)) {
                    }
                }
                z = true;
            }
            z = false;
            for (int i2 = 0; i2 < this._denyList.size(); i2++) {
                String str4 = (String) this._denyList.get(i2);
                if (!str4.equals("all")) {
                    char charAt2 = str4.charAt(0);
                    if (charAt2 >= '0' && charAt2 <= '9') {
                        if (!str2.startsWith(str4)) {
                        }
                    } else if (!str.endsWith(str4)) {
                    }
                }
                z2 = true;
            }
            z2 = false;
            return this._order < 0 ? !z2 || z : z && !z2;
        }

        public boolean checkAuth(String str, String str2, UserRealm userRealm, Request request) {
            if (this._requireName == null) {
                return true;
            }
            String str3 = null;
            if ((userRealm == null ? null : userRealm.authenticate(str, str2, request)) == null) {
                String userCode = getUserCode(str);
                String substring = userCode != null ? userCode.substring(0, 2) : str;
                if (str != null && str2 != null) {
                    str3 = UnixCrypt.crypt(str2, substring);
                }
                if (userCode == null || ((userCode.equals("") && !str2.equals("")) || !userCode.equals(str3))) {
                    return false;
                }
            }
            if (this._requireName.equalsIgnoreCase(USER)) {
                if (this._requireEntities.contains(str)) {
                    return true;
                }
            } else if (this._requireName.equalsIgnoreCase(GROUP)) {
                ArrayList userGroups = getUserGroups(str);
                if (userGroups != null) {
                    int size = userGroups.size();
                    while (true) {
                        int i = size - 1;
                        if (size <= 0) {
                            break;
                        }
                        if (this._requireEntities.contains(userGroups.get(i))) {
                            return true;
                        }
                        size = i;
                    }
                }
            } else if (this._requireName.equalsIgnoreCase(VALID_USER)) {
                return true;
            }
            return false;
        }

        public boolean isAccessLimited() {
            return this._allowList.size() > 0 || this._denyList.size() > 0;
        }

        public boolean isAuthLimited() {
            return this._requireName != null;
        }

        /* JADX WARN: Removed duplicated region for block: B:50:0x00ba A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private java.lang.String getUserCode(java.lang.String r9) {
            /*
                r8 = this;
                java.lang.String r0 = "LogSupport.EXCEPTION"
                org.mortbay.resource.Resource r1 = r8._userResource
                r2 = 0
                if (r1 != 0) goto L8
                return r2
            L8:
                java.util.HashMap r3 = r8._users
                if (r3 == 0) goto L16
                long r3 = r8._userModified
                long r5 = r1.lastModified()
                int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r1 == 0) goto Lad
            L16:
                org.mortbay.log.Logger r1 = org.mortbay.jetty.security.HTAccessHandler.access$000()
                boolean r1 = r1.isDebugEnabled()
                if (r1 == 0) goto L38
                org.mortbay.log.Logger r1 = org.mortbay.jetty.security.HTAccessHandler.access$000()
                java.lang.StringBuffer r3 = new java.lang.StringBuffer
                java.lang.String r4 = "LOAD "
                r3.<init>(r4)
                org.mortbay.resource.Resource r4 = r8._userResource
                java.lang.StringBuffer r3 = r3.append(r4)
                java.lang.String r3 = r3.toString()
                r1.debug(r3, r2, r2)
            L38:
                java.util.HashMap r1 = new java.util.HashMap
                r1.<init>()
                r8._users = r1
                java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L9b java.io.IOException -> L9d
                java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L9b java.io.IOException -> L9d
                org.mortbay.resource.Resource r4 = r8._userResource     // Catch: java.lang.Throwable -> L9b java.io.IOException -> L9d
                java.io.InputStream r4 = r4.getInputStream()     // Catch: java.lang.Throwable -> L9b java.io.IOException -> L9d
                r3.<init>(r4)     // Catch: java.lang.Throwable -> L9b java.io.IOException -> L9d
                r1.<init>(r3)     // Catch: java.lang.Throwable -> L9b java.io.IOException -> L9d
                org.mortbay.resource.Resource r2 = r8._userResource     // Catch: java.io.IOException -> L99 java.lang.Throwable -> Lb6
                long r2 = r2.lastModified()     // Catch: java.io.IOException -> L99 java.lang.Throwable -> Lb6
                r8._userModified = r2     // Catch: java.io.IOException -> L99 java.lang.Throwable -> Lb6
            L57:
                java.lang.String r2 = r1.readLine()     // Catch: java.io.IOException -> L99 java.lang.Throwable -> Lb6
                if (r2 == 0) goto L8c
                java.lang.String r2 = r2.trim()     // Catch: java.io.IOException -> L99 java.lang.Throwable -> Lb6
                java.lang.String r3 = "#"
                boolean r3 = r2.startsWith(r3)     // Catch: java.io.IOException -> L99 java.lang.Throwable -> Lb6
                if (r3 == 0) goto L6a
                goto L57
            L6a:
                r3 = 58
                int r3 = r2.indexOf(r3)     // Catch: java.io.IOException -> L99 java.lang.Throwable -> Lb6
                if (r3 >= 0) goto L73
                goto L57
            L73:
                r4 = 0
                java.lang.String r4 = r2.substring(r4, r3)     // Catch: java.io.IOException -> L99 java.lang.Throwable -> Lb6
                java.lang.String r4 = r4.trim()     // Catch: java.io.IOException -> L99 java.lang.Throwable -> Lb6
                int r3 = r3 + 1
                java.lang.String r2 = r2.substring(r3)     // Catch: java.io.IOException -> L99 java.lang.Throwable -> Lb6
                java.lang.String r2 = r2.trim()     // Catch: java.io.IOException -> L99 java.lang.Throwable -> Lb6
                java.util.HashMap r3 = r8._users     // Catch: java.io.IOException -> L99 java.lang.Throwable -> Lb6
                r3.put(r4, r2)     // Catch: java.io.IOException -> L99 java.lang.Throwable -> Lb6
                goto L57
            L8c:
                r1.close()     // Catch: java.io.IOException -> L90
                goto Lad
            L90:
                r1 = move-exception
                org.mortbay.log.Logger r2 = org.mortbay.jetty.security.HTAccessHandler.access$000()
                r2.warn(r0, r1)
                goto Lad
            L99:
                r2 = move-exception
                goto La1
            L9b:
                r9 = move-exception
                goto Lb8
            L9d:
                r1 = move-exception
                r7 = r2
                r2 = r1
                r1 = r7
            La1:
                org.mortbay.log.Logger r3 = org.mortbay.jetty.security.HTAccessHandler.access$000()     // Catch: java.lang.Throwable -> Lb6
                r3.warn(r0, r2)     // Catch: java.lang.Throwable -> Lb6
                if (r1 == 0) goto Lad
                r1.close()     // Catch: java.io.IOException -> L90
            Lad:
                java.util.HashMap r0 = r8._users
                java.lang.Object r9 = r0.get(r9)
                java.lang.String r9 = (java.lang.String) r9
                return r9
            Lb6:
                r9 = move-exception
                r2 = r1
            Lb8:
                if (r2 == 0) goto Lc6
                r2.close()     // Catch: java.io.IOException -> Lbe
                goto Lc6
            Lbe:
                r1 = move-exception
                org.mortbay.log.Logger r2 = org.mortbay.jetty.security.HTAccessHandler.access$000()
                r2.warn(r0, r1)
            Lc6:
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.security.HTAccessHandler.HTAccess.getUserCode(java.lang.String):java.lang.String");
        }

        /* JADX WARN: Removed duplicated region for block: B:67:0x00d9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private java.util.ArrayList getUserGroups(java.lang.String r9) {
            /*
                r8 = this;
                java.lang.String r0 = "LogSupport.EXCEPTION"
                org.mortbay.resource.Resource r1 = r8._groupResource
                r2 = 0
                if (r1 != 0) goto L8
                return r2
            L8:
                java.util.HashMap r3 = r8._groups
                if (r3 == 0) goto L16
                long r3 = r8._groupModified
                long r5 = r1.lastModified()
                int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r1 == 0) goto Lcc
            L16:
                org.mortbay.log.Logger r1 = org.mortbay.jetty.security.HTAccessHandler.access$000()
                boolean r1 = r1.isDebugEnabled()
                if (r1 == 0) goto L38
                org.mortbay.log.Logger r1 = org.mortbay.jetty.security.HTAccessHandler.access$000()
                java.lang.StringBuffer r3 = new java.lang.StringBuffer
                java.lang.String r4 = "LOAD "
                r3.<init>(r4)
                org.mortbay.resource.Resource r4 = r8._groupResource
                java.lang.StringBuffer r3 = r3.append(r4)
                java.lang.String r3 = r3.toString()
                r1.debug(r3, r2, r2)
            L38:
                java.util.HashMap r1 = new java.util.HashMap
                r1.<init>()
                r8._groups = r1
                java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> Lba java.io.IOException -> Lbc
                java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> Lba java.io.IOException -> Lbc
                org.mortbay.resource.Resource r4 = r8._groupResource     // Catch: java.lang.Throwable -> Lba java.io.IOException -> Lbc
                java.io.InputStream r4 = r4.getInputStream()     // Catch: java.lang.Throwable -> Lba java.io.IOException -> Lbc
                r3.<init>(r4)     // Catch: java.lang.Throwable -> Lba java.io.IOException -> Lbc
                r1.<init>(r3)     // Catch: java.lang.Throwable -> Lba java.io.IOException -> Lbc
                org.mortbay.resource.Resource r2 = r8._groupResource     // Catch: java.io.IOException -> Lb8 java.lang.Throwable -> Ld5
                long r2 = r2.lastModified()     // Catch: java.io.IOException -> Lb8 java.lang.Throwable -> Ld5
                r8._groupModified = r2     // Catch: java.io.IOException -> Lb8 java.lang.Throwable -> Ld5
            L57:
                java.lang.String r2 = r1.readLine()     // Catch: java.io.IOException -> Lb8 java.lang.Throwable -> Ld5
                if (r2 == 0) goto Lab
                java.lang.String r2 = r2.trim()     // Catch: java.io.IOException -> Lb8 java.lang.Throwable -> Ld5
                java.lang.String r3 = "#"
                boolean r3 = r2.startsWith(r3)     // Catch: java.io.IOException -> Lb8 java.lang.Throwable -> Ld5
                if (r3 != 0) goto L57
                int r3 = r2.length()     // Catch: java.io.IOException -> Lb8 java.lang.Throwable -> Ld5
                if (r3 != 0) goto L70
                goto L57
            L70:
                java.util.StringTokenizer r3 = new java.util.StringTokenizer     // Catch: java.io.IOException -> Lb8 java.lang.Throwable -> Ld5
                java.lang.String r4 = ": \t"
                r3.<init>(r2, r4)     // Catch: java.io.IOException -> Lb8 java.lang.Throwable -> Ld5
                boolean r2 = r3.hasMoreTokens()     // Catch: java.io.IOException -> Lb8 java.lang.Throwable -> Ld5
                if (r2 != 0) goto L7e
                goto L57
            L7e:
                java.lang.String r2 = r3.nextToken()     // Catch: java.io.IOException -> Lb8 java.lang.Throwable -> Ld5
                boolean r4 = r3.hasMoreTokens()     // Catch: java.io.IOException -> Lb8 java.lang.Throwable -> Ld5
                if (r4 != 0) goto L89
                goto L57
            L89:
                boolean r4 = r3.hasMoreTokens()     // Catch: java.io.IOException -> Lb8 java.lang.Throwable -> Ld5
                if (r4 == 0) goto L57
                java.lang.String r4 = r3.nextToken()     // Catch: java.io.IOException -> Lb8 java.lang.Throwable -> Ld5
                java.util.HashMap r5 = r8._groups     // Catch: java.io.IOException -> Lb8 java.lang.Throwable -> Ld5
                java.lang.Object r5 = r5.get(r4)     // Catch: java.io.IOException -> Lb8 java.lang.Throwable -> Ld5
                java.util.ArrayList r5 = (java.util.ArrayList) r5     // Catch: java.io.IOException -> Lb8 java.lang.Throwable -> Ld5
                if (r5 != 0) goto La7
                java.util.ArrayList r5 = new java.util.ArrayList     // Catch: java.io.IOException -> Lb8 java.lang.Throwable -> Ld5
                r5.<init>()     // Catch: java.io.IOException -> Lb8 java.lang.Throwable -> Ld5
                java.util.HashMap r6 = r8._groups     // Catch: java.io.IOException -> Lb8 java.lang.Throwable -> Ld5
                r6.put(r4, r5)     // Catch: java.io.IOException -> Lb8 java.lang.Throwable -> Ld5
            La7:
                r5.add(r2)     // Catch: java.io.IOException -> Lb8 java.lang.Throwable -> Ld5
                goto L89
            Lab:
                r1.close()     // Catch: java.io.IOException -> Laf
                goto Lcc
            Laf:
                r1 = move-exception
                org.mortbay.log.Logger r2 = org.mortbay.jetty.security.HTAccessHandler.access$000()
                r2.warn(r0, r1)
                goto Lcc
            Lb8:
                r2 = move-exception
                goto Lc0
            Lba:
                r9 = move-exception
                goto Ld7
            Lbc:
                r1 = move-exception
                r7 = r2
                r2 = r1
                r1 = r7
            Lc0:
                org.mortbay.log.Logger r3 = org.mortbay.jetty.security.HTAccessHandler.access$000()     // Catch: java.lang.Throwable -> Ld5
                r3.warn(r0, r2)     // Catch: java.lang.Throwable -> Ld5
                if (r1 == 0) goto Lcc
                r1.close()     // Catch: java.io.IOException -> Laf
            Lcc:
                java.util.HashMap r0 = r8._groups
                java.lang.Object r9 = r0.get(r9)
                java.util.ArrayList r9 = (java.util.ArrayList) r9
                return r9
            Ld5:
                r9 = move-exception
                r2 = r1
            Ld7:
                if (r2 == 0) goto Le5
                r2.close()     // Catch: java.io.IOException -> Ldd
                goto Le5
            Ldd:
                r1 = move-exception
                org.mortbay.log.Logger r2 = org.mortbay.jetty.security.HTAccessHandler.access$000()
                r2.warn(r0, r1)
            Le5:
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.security.HTAccessHandler.HTAccess.getUserGroups(java.lang.String):java.util.ArrayList");
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer("AuthUserFile=");
            stringBuffer.append(this._userFile);
            stringBuffer.append(", AuthGroupFile=");
            stringBuffer.append(this._groupFile);
            stringBuffer.append(", AuthName=");
            stringBuffer.append(this._name);
            stringBuffer.append(", AuthType=");
            stringBuffer.append(this._type);
            stringBuffer.append(", Methods=");
            stringBuffer.append(this._methods);
            stringBuffer.append(", satisfy=");
            stringBuffer.append(this._satisfy);
            int i = this._order;
            if (i < 0) {
                stringBuffer.append(", order=deny,allow");
            } else if (i > 0) {
                stringBuffer.append(", order=allow,deny");
            } else {
                stringBuffer.append(", order=mutual-failure");
            }
            stringBuffer.append(", Allow from=");
            stringBuffer.append(this._allowList);
            stringBuffer.append(", deny from=");
            stringBuffer.append(this._denyList);
            stringBuffer.append(", requireName=");
            stringBuffer.append(this._requireName);
            stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            stringBuffer.append(this._requireEntities);
            return stringBuffer.toString();
        }

        private void parse(BufferedReader bufferedReader) throws IOException {
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return;
                }
                String trim = readLine.trim();
                if (!trim.startsWith("#")) {
                    if (trim.startsWith("AuthUserFile")) {
                        this._userFile = trim.substring(13).trim();
                    } else if (trim.startsWith("AuthGroupFile")) {
                        this._groupFile = trim.substring(14).trim();
                    } else if (trim.startsWith("AuthName")) {
                        this._name = trim.substring(8).trim();
                    } else if (trim.startsWith("AuthType")) {
                        this._type = trim.substring(8).trim();
                    } else if (trim.startsWith("<Limit")) {
                        int length = trim.length();
                        int indexOf = trim.indexOf(62);
                        if (indexOf >= 0) {
                            length = indexOf;
                        }
                        StringTokenizer stringTokenizer = new StringTokenizer(trim.substring(6, length));
                        while (stringTokenizer.hasMoreTokens()) {
                            this._methods.put(stringTokenizer.nextToken(), Boolean.TRUE);
                        }
                        while (true) {
                            String readLine2 = bufferedReader.readLine();
                            if (readLine2 != null) {
                                String trim2 = readLine2.trim();
                                if (!trim2.startsWith("#")) {
                                    int i = 7;
                                    if (trim2.startsWith("satisfy")) {
                                        int length2 = trim2.length();
                                        while (i < length2 && trim2.charAt(i) <= ' ') {
                                            i++;
                                        }
                                        int i2 = i;
                                        while (i2 < length2 && trim2.charAt(i2) > ' ') {
                                            i2++;
                                        }
                                        String substring = trim2.substring(i, i2);
                                        if (substring.equals("all")) {
                                            this._satisfy = 1;
                                        } else if (substring.equals(Languages.ANY)) {
                                            this._satisfy = 0;
                                        }
                                    } else if (trim2.startsWith("require")) {
                                        int length3 = trim2.length();
                                        while (i < length3 && trim2.charAt(i) <= ' ') {
                                            i++;
                                        }
                                        int i3 = i;
                                        while (i3 < length3 && trim2.charAt(i3) > ' ') {
                                            i3++;
                                        }
                                        String lowerCase = trim2.substring(i, i3).toLowerCase();
                                        this._requireName = lowerCase;
                                        if (USER.equals(lowerCase)) {
                                            this._requireName = USER;
                                        } else if (GROUP.equals(this._requireName)) {
                                            this._requireName = GROUP;
                                        } else if (VALID_USER.equals(this._requireName)) {
                                            this._requireName = VALID_USER;
                                        }
                                        int i4 = i3 + 1;
                                        if (i4 < length3) {
                                            while (i4 < length3 && trim2.charAt(i4) <= ' ') {
                                                i4++;
                                            }
                                            StringTokenizer stringTokenizer2 = new StringTokenizer(trim2.substring(i4));
                                            while (stringTokenizer2.hasMoreTokens()) {
                                                this._requireEntities.add(stringTokenizer2.nextToken());
                                            }
                                        }
                                    } else if (trim2.startsWith("order")) {
                                        if (HTAccessHandler.log.isDebugEnabled()) {
                                            HTAccessHandler.log.debug(new StringBuffer("orderline=").append(trim2).append("order=").append(this._order).toString(), null, null);
                                        }
                                        if (trim2.indexOf("allow,deny") > 0) {
                                            HTAccessHandler.log.debug("==>allow+deny", null, null);
                                            this._order = 1;
                                        } else if (trim2.indexOf("deny,allow") > 0) {
                                            HTAccessHandler.log.debug("==>deny,allow", null, null);
                                            this._order = -1;
                                        } else if (trim2.indexOf("mutual-failure") > 0) {
                                            HTAccessHandler.log.debug("==>mutual", null, null);
                                            this._order = 0;
                                        }
                                    } else if (trim2.startsWith("allow from")) {
                                        int length4 = trim2.length();
                                        int i5 = 10;
                                        while (i5 < length4 && trim2.charAt(i5) <= ' ') {
                                            i5++;
                                        }
                                        if (HTAccessHandler.log.isDebugEnabled()) {
                                            HTAccessHandler.log.debug(new StringBuffer("allow process:").append(trim2.substring(i5)).toString(), null, null);
                                        }
                                        StringTokenizer stringTokenizer3 = new StringTokenizer(trim2.substring(i5));
                                        while (stringTokenizer3.hasMoreTokens()) {
                                            this._allowList.add(stringTokenizer3.nextToken());
                                        }
                                    } else if (trim2.startsWith("deny from")) {
                                        int length5 = trim2.length();
                                        int i6 = 9;
                                        while (i6 < length5 && trim2.charAt(i6) <= ' ') {
                                            i6++;
                                        }
                                        if (HTAccessHandler.log.isDebugEnabled()) {
                                            HTAccessHandler.log.debug(new StringBuffer("deny process:").append(trim2.substring(i6)).toString(), null, null);
                                        }
                                        StringTokenizer stringTokenizer4 = new StringTokenizer(trim2.substring(i6));
                                        while (stringTokenizer4.hasMoreTokens()) {
                                            this._denyList.add(stringTokenizer4.nextToken());
                                        }
                                    } else if (trim2.startsWith("</Limit>")) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    protected Handler getProtegee() {
        return this.protegee;
    }

    public void setProtegee(Handler handler) {
        this.protegee = handler;
    }
}
