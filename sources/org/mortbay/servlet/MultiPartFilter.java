package org.mortbay.servlet;

import j$.util.DesugarCollections;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.mortbay.jetty.servlet.ServletHandler;
import org.mortbay.util.LazyList;
import org.mortbay.util.MultiMap;

/* loaded from: classes3.dex */
public class MultiPartFilter implements Filter {
    private static final String FILES = "org.mortbay.servlet.MultiPartFilter.files";
    private ServletContext _context;
    private boolean _deleteFiles;
    private int _fileOutputBuffer = 0;
    private File tempdir;

    @Override // javax.servlet.Filter
    public void destroy() {
    }

    @Override // javax.servlet.Filter
    public void init(FilterConfig filterConfig) throws ServletException {
        this.tempdir = (File) filterConfig.getServletContext().getAttribute(ServletHandler.__J_S_CONTEXT_TEMPDIR);
        this._deleteFiles = "true".equals(filterConfig.getInitParameter("deleteFiles"));
        String initParameter = filterConfig.getInitParameter("fileOutputBuffer");
        if (initParameter != null) {
            this._fileOutputBuffer = Integer.parseInt(initParameter);
        }
        this._context = filterConfig.getServletContext();
    }

    /* JADX WARN: Code restructure failed: missing block: B:105:0x01bd, code lost:
    
        if (r7 != 13) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x01bf, code lost:
    
        r1 = r6.read();
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x01c8, code lost:
    
        if (r4 <= 0) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x01cd, code lost:
    
        if (r4 < (r8.length - 2)) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x01d4, code lost:
    
        if (r14 == false) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x01d6, code lost:
    
        r13.write(13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x01d9, code lost:
    
        if (r15 == false) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x01db, code lost:
    
        r13.write(10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x01e0, code lost:
    
        r13.write(r8, 0, r4);
        r4 = -1;
        r14 = false;
        r15 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x01e7, code lost:
    
        if (r4 > 0) goto L182;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x01ea, code lost:
    
        if (r7 != (-1)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x01ed, code lost:
    
        if (r14 == false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x01ef, code lost:
    
        r13.write(13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x01f2, code lost:
    
        if (r15 == false) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x01f4, code lost:
    
        r0 = 10;
        r13.write(10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x01fc, code lost:
    
        if (r7 != 13) goto L112;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x01fe, code lost:
    
        r14 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x0201, code lost:
    
        if (r7 == r0) goto L117;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x0203, code lost:
    
        if (r1 != r0) goto L116;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x0206, code lost:
    
        r15 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x0209, code lost:
    
        if (r1 != r0) goto L185;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x020b, code lost:
    
        r1 = -2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x020c, code lost:
    
        r4 = r19;
        r7 = -2;
        r19 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x0208, code lost:
    
        r15 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x0200, code lost:
    
        r14 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x01fa, code lost:
    
        r0 = 10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x021a, code lost:
    
        if (r4 != r8.length) goto L124;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x021d, code lost:
    
        r17 = r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x021f, code lost:
    
        r13.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x0222, code lost:
    
        if (r10 != null) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x0224, code lost:
    
        r9.add(r12, ((java.io.ByteArrayOutputStream) r13).toByteArray());
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x022d, code lost:
    
        r1 = r21;
        r2 = r22;
        r13 = r16;
        r10 = r17;
        r4 = r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x01d2, code lost:
    
        if (r4 != (r8.length - 1)) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x01c4, code lost:
    
        r1 = -2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x01c6, code lost:
    
        r1 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x017a, code lost:
    
        r7 = r6.read();
        r20 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x0126, code lost:
    
        if (r7.length() <= 0) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x0128, code lost:
    
        r10 = java.io.File.createTempFile("MultiPart", "", r1.tempdir);
        r13 = new java.io.FileOutputStream(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x0139, code lost:
    
        if (r1._fileOutputBuffer <= 0) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x013b, code lost:
    
        r13 = new java.io.BufferedOutputStream(r13, r1._fileOutputBuffer);
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x0143, code lost:
    
        r2.setAttribute(r12, r10);
        r9.add(r12, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x014b, code lost:
    
        if (r1._deleteFiles == false) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x014d, code lost:
    
        r10.deleteOnExit();
        r7 = (java.util.ArrayList) r2.getAttribute(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x0156, code lost:
    
        if (r7 != null) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x0158, code lost:
    
        r7 = new java.util.ArrayList();
        r2.setAttribute(r4, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x0160, code lost:
    
        r7.add(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x023b, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x023c, code lost:
    
        r12 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x0240, code lost:
    
        r12.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x0243, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x023e, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x023f, code lost:
    
        r12 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x024b, code lost:
    
        throw new java.io.IOException("Missing content-disposition");
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00c2, code lost:
    
        if (r13 == null) goto L169;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00c4, code lost:
    
        r14 = new java.util.StringTokenizer(r13, ";");
        r7 = null;
        r12 = null;
        r15 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00ce, code lost:
    
        r17 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00d4, code lost:
    
        if (r14.hasMoreTokens() == false) goto L177;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00d6, code lost:
    
        r18 = r10;
        r10 = r14.nextToken().trim();
        r16 = r13;
        r13 = r10.toLowerCase();
        r19 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00ee, code lost:
    
        if (r10.startsWith("form-data") == false) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00f0, code lost:
    
        r15 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x010b, code lost:
    
        r13 = r16;
        r10 = r18;
        r14 = r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00f8, code lost:
    
        if (r13.startsWith("name=") == false) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00fa, code lost:
    
        r12 = r1.value(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0105, code lost:
    
        if (r13.startsWith("filename=") == false) goto L181;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0107, code lost:
    
        r7 = r1.value(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0112, code lost:
    
        r18 = r10;
        r16 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0116, code lost:
    
        if (r15 != false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0119, code lost:
    
        if (r12 != null) goto L170;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x011b, code lost:
    
        r13 = r16;
        r10 = r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0120, code lost:
    
        if (r7 == null) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0164, code lost:
    
        r13 = new java.io.ByteArrayOutputStream();
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0169, code lost:
    
        r10 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x016a, code lost:
    
        r7 = -2;
        r14 = false;
        r15 = false;
        r19 = -2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x016f, code lost:
    
        r1 = r19;
        r19 = r4;
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0174, code lost:
    
        if (r1 == r7) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0176, code lost:
    
        r7 = r1;
        r20 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0187, code lost:
    
        if (r7 == (-1)) goto L186;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0189, code lost:
    
        if (r7 == 13) goto L187;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x018b, code lost:
    
        if (r7 != 10) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x018e, code lost:
    
        if (r4 < 0) goto L189;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0191, code lost:
    
        if (r4 >= r8.length) goto L190;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0195, code lost:
    
        if (r7 != r8[r4]) goto L191;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x01a0, code lost:
    
        if (r14 == false) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x01a2, code lost:
    
        r13.write(13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x01a5, code lost:
    
        if (r15 == false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x01a7, code lost:
    
        r13.write(10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01aa, code lost:
    
        if (r4 <= 0) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x01ac, code lost:
    
        r13.write(r8, 0, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01b0, code lost:
    
        r13.write(r7);
        r1 = -2;
        r4 = -1;
        r7 = -2;
        r14 = false;
        r15 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0197, code lost:
    
        r4 = r4 + 1;
        r1 = -2;
        r7 = -2;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v19, types: [int] */
    /* JADX WARN: Type inference failed for: r20v0, types: [int] */
    /* JADX WARN: Type inference failed for: r24v0, types: [javax.servlet.FilterChain] */
    @Override // javax.servlet.Filter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void doFilter(javax.servlet.ServletRequest r22, javax.servlet.ServletResponse r23, javax.servlet.FilterChain r24) throws java.io.IOException, javax.servlet.ServletException {
        /*
            Method dump skipped, instructions count: 692
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.servlet.MultiPartFilter.doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain):void");
    }

    private void deleteFiles(ServletRequest servletRequest) {
        ArrayList arrayList = (ArrayList) servletRequest.getAttribute(FILES);
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                File file = (File) it.next();
                try {
                    file.delete();
                } catch (Exception e) {
                    this._context.log(new StringBuffer("failed to delete ").append(file).toString(), e);
                }
            }
        }
    }

    private String value(String str) {
        String trim = str.substring(str.indexOf(61) + 1).trim();
        int indexOf = trim.indexOf(59);
        if (indexOf > 0) {
            trim = trim.substring(0, indexOf);
        }
        if (trim.startsWith("\"")) {
            return trim.substring(1, trim.indexOf(34, 1));
        }
        int indexOf2 = trim.indexOf(32);
        return indexOf2 > 0 ? trim.substring(0, indexOf2) : trim;
    }

    private static class Wrapper extends HttpServletRequestWrapper {
        String encoding;
        MultiMap map;

        @Override // javax.servlet.ServletRequestWrapper, javax.servlet.ServletRequest
        public int getContentLength() {
            return 0;
        }

        public Wrapper(HttpServletRequest httpServletRequest, MultiMap multiMap) {
            super(httpServletRequest);
            this.encoding = "UTF-8";
            this.map = multiMap;
        }

        @Override // javax.servlet.ServletRequestWrapper, javax.servlet.ServletRequest
        public String getParameter(String str) {
            Object obj = this.map.get(str);
            if (!(obj instanceof byte[]) && LazyList.size(obj) > 0) {
                obj = LazyList.get(obj, 0);
            }
            if (!(obj instanceof byte[])) {
                if (obj != null) {
                    return String.valueOf(obj);
                }
                return null;
            }
            try {
                return new String((byte[]) obj, this.encoding);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override // javax.servlet.ServletRequestWrapper, javax.servlet.ServletRequest
        public Map getParameterMap() {
            return DesugarCollections.unmodifiableMap(this.map.toStringArrayMap());
        }

        @Override // javax.servlet.ServletRequestWrapper, javax.servlet.ServletRequest
        public Enumeration getParameterNames() {
            return Collections.enumeration(this.map.keySet());
        }

        @Override // javax.servlet.ServletRequestWrapper, javax.servlet.ServletRequest
        public String[] getParameterValues(String str) {
            List values = this.map.getValues(str);
            if (values == null || values.size() == 0) {
                return new String[0];
            }
            String[] strArr = new String[values.size()];
            for (int i = 0; i < values.size(); i++) {
                Object obj = values.get(i);
                if (obj instanceof byte[]) {
                    try {
                        strArr[i] = new String((byte[]) obj, this.encoding);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (obj instanceof String) {
                    strArr[i] = (String) obj;
                }
            }
            return strArr;
        }

        @Override // javax.servlet.ServletRequestWrapper, javax.servlet.ServletRequest
        public void setCharacterEncoding(String str) throws UnsupportedEncodingException {
            this.encoding = str;
        }
    }
}
