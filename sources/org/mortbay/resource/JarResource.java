package org.mortbay.resource;

import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import org.mortbay.log.Log;
import org.mortbay.util.IO;

/* loaded from: classes3.dex */
public class JarResource extends URLResource {
    protected transient JarURLConnection _jarConnection;

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public File getFile() throws IOException {
        return null;
    }

    JarResource(URL url) {
        super(url, null);
    }

    JarResource(URL url, boolean z) {
        super(url, null, z);
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public synchronized void release() {
        this._jarConnection = null;
        super.release();
    }

    @Override // org.mortbay.resource.URLResource
    protected boolean checkConnection() {
        super.checkConnection();
        try {
            if (this._jarConnection != this._connection) {
                newConnection();
            }
        } catch (IOException e) {
            Log.ignore(e);
            this._jarConnection = null;
        }
        return this._jarConnection != null;
    }

    protected void newConnection() throws IOException {
        this._jarConnection = (JarURLConnection) this._connection;
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public boolean exists() {
        if (this._urlString.endsWith("!/")) {
            return checkConnection();
        }
        return super.exists();
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public InputStream getInputStream() throws IOException {
        checkConnection();
        if (!this._urlString.endsWith("!/")) {
            return new FilterInputStream(super.getInputStream()) { // from class: org.mortbay.resource.JarResource.1
                AnonymousClass1(InputStream inputStream) {
                    super(inputStream);
                }

                @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    this.in = IO.getClosedStream();
                }
            };
        }
        return new URL(this._urlString.substring(4, this._urlString.length() - 2)).openStream();
    }

    /* renamed from: org.mortbay.resource.JarResource$1 */
    class AnonymousClass1 extends FilterInputStream {
        AnonymousClass1(InputStream inputStream) {
            super(inputStream);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.in = IO.getClosedStream();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x00c7, code lost:
    
        if (r3.equals("") == false) goto L136;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00d2, code lost:
    
        r7 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x00d0, code lost:
    
        if (r3.startsWith(r12) == false) goto L135;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void extract(org.mortbay.resource.Resource r12, java.io.File r13, boolean r14) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 432
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.resource.JarResource.extract(org.mortbay.resource.Resource, java.io.File, boolean):void");
    }

    public void extract(File file, boolean z) throws IOException {
        extract(this, file, z);
    }
}
