package org.mortbay.jetty.servlet;

import j$.util.DesugarCollections;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.http.HttpServletRequest;
import org.mortbay.jetty.servlet.AbstractSessionManager;
import org.mortbay.log.Log;
import org.mortbay.util.LazyList;

/* loaded from: classes3.dex */
public class HashSessionManager extends AbstractSessionManager {
    private TimerTask _saveTask;
    protected Map _sessions;
    private File _storeDir;
    private TimerTask _task;
    private Timer _timer;
    private int _scavengePeriodMs = 30000;
    private int _savePeriodMs = 0;
    private boolean _lazyLoad = false;
    private boolean _sessionsLoaded = false;

    @Override // org.mortbay.jetty.servlet.AbstractSessionManager, org.mortbay.component.AbstractLifeCycle
    public void doStart() throws Exception {
        this._sessions = new HashMap();
        super.doStart();
        this._timer = new Timer(true);
        setScavengePeriod(getScavengePeriod());
        File file = this._storeDir;
        if (file != null) {
            if (!file.exists()) {
                this._storeDir.mkdir();
            }
            if (!this._lazyLoad) {
                restoreSessions();
            }
        }
        setSavePeriod(getSavePeriod());
    }

    @Override // org.mortbay.jetty.servlet.AbstractSessionManager, org.mortbay.component.AbstractLifeCycle
    public void doStop() throws Exception {
        if (this._storeDir != null) {
            saveSessions();
        }
        super.doStop();
        this._sessions.clear();
        this._sessions = null;
        synchronized (this) {
            TimerTask timerTask = this._saveTask;
            if (timerTask != null) {
                timerTask.cancel();
            }
            TimerTask timerTask2 = this._task;
            if (timerTask2 != null) {
                timerTask2.cancel();
            }
            Timer timer = this._timer;
            if (timer != null) {
                timer.cancel();
            }
            this._timer = null;
        }
    }

    public int getScavengePeriod() {
        return this._scavengePeriodMs / 1000;
    }

    @Override // org.mortbay.jetty.servlet.AbstractSessionManager
    public Map getSessionMap() {
        return DesugarCollections.unmodifiableMap(this._sessions);
    }

    @Override // org.mortbay.jetty.servlet.AbstractSessionManager
    public int getSessions() {
        return this._sessions.size();
    }

    @Override // org.mortbay.jetty.servlet.AbstractSessionManager, org.mortbay.jetty.SessionManager
    public void setMaxInactiveInterval(int i) {
        super.setMaxInactiveInterval(i);
        if (this._dftMaxIdleSecs <= 0 || this._scavengePeriodMs <= this._dftMaxIdleSecs * 1000) {
            return;
        }
        setScavengePeriod((this._dftMaxIdleSecs + 9) / 10);
    }

    public void setSavePeriod(int i) {
        int i2 = i * 1000;
        if (i2 < 0) {
            i2 = 0;
        }
        this._savePeriodMs = i2;
        if (this._timer != null) {
            synchronized (this) {
                TimerTask timerTask = this._saveTask;
                if (timerTask != null) {
                    timerTask.cancel();
                }
                if (this._savePeriodMs > 0 && this._storeDir != null) {
                    AnonymousClass1 anonymousClass1 = new TimerTask() { // from class: org.mortbay.jetty.servlet.HashSessionManager.1
                        AnonymousClass1() {
                        }

                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            try {
                                HashSessionManager.this.saveSessions();
                            } catch (Exception e) {
                                Log.warn(e);
                            }
                        }
                    };
                    this._saveTask = anonymousClass1;
                    Timer timer = this._timer;
                    int i3 = this._savePeriodMs;
                    timer.schedule(anonymousClass1, i3, i3);
                }
            }
        }
    }

    /* renamed from: org.mortbay.jetty.servlet.HashSessionManager$1 */
    class AnonymousClass1 extends TimerTask {
        AnonymousClass1() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            try {
                HashSessionManager.this.saveSessions();
            } catch (Exception e) {
                Log.warn(e);
            }
        }
    }

    public int getSavePeriod() {
        int i = this._savePeriodMs;
        if (i <= 0) {
            return 0;
        }
        return i / 1000;
    }

    public void setScavengePeriod(int i) {
        if (i == 0) {
            i = 60;
        }
        int i2 = this._scavengePeriodMs;
        int i3 = i * 1000;
        if (i3 > 60000) {
            i3 = 60000;
        }
        int i4 = i3 >= 1000 ? i3 : 1000;
        this._scavengePeriodMs = i4;
        if (this._timer != null) {
            if (i4 != i2 || this._task == null) {
                synchronized (this) {
                    TimerTask timerTask = this._task;
                    if (timerTask != null) {
                        timerTask.cancel();
                    }
                    AnonymousClass2 anonymousClass2 = new TimerTask() { // from class: org.mortbay.jetty.servlet.HashSessionManager.2
                        AnonymousClass2() {
                        }

                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            HashSessionManager.this.scavenge();
                        }
                    };
                    this._task = anonymousClass2;
                    Timer timer = this._timer;
                    int i5 = this._scavengePeriodMs;
                    timer.schedule(anonymousClass2, i5, i5);
                }
            }
        }
    }

    /* renamed from: org.mortbay.jetty.servlet.HashSessionManager$2 */
    class AnonymousClass2 extends TimerTask {
        AnonymousClass2() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            HashSessionManager.this.scavenge();
        }
    }

    public void scavenge() {
        Object obj;
        if (isStopping() || isStopped()) {
            return;
        }
        Thread currentThread = Thread.currentThread();
        ClassLoader contextClassLoader = currentThread.getContextClassLoader();
        try {
            if (this._loader != null) {
                currentThread.setContextClassLoader(this._loader);
            }
            long currentTimeMillis = System.currentTimeMillis();
            try {
                if (!this._sessionsLoaded && this._lazyLoad) {
                    restoreSessions();
                }
            } catch (Exception e) {
                Log.debug(e);
            }
            synchronized (this) {
                obj = null;
                for (Session session : this._sessions.values()) {
                    long j = session._maxIdleMs;
                    if (j > 0 && session._accessed + j < currentTimeMillis) {
                        obj = LazyList.add(obj, session);
                    }
                }
            }
            int size = LazyList.size(obj);
            while (true) {
                int i = size - 1;
                if (size <= 0) {
                    break;
                }
                Session session2 = (Session) LazyList.get(obj, i);
                long j2 = session2._maxIdleMs;
                if (j2 > 0 && session2._accessed + j2 < System.currentTimeMillis()) {
                    session2.timeout();
                    int size2 = this._sessions.size();
                    if (size2 < this._minSessions) {
                        this._minSessions = size2;
                    }
                }
                size = i;
            }
        } catch (Throwable th) {
            try {
                if (th instanceof ThreadDeath) {
                    throw th;
                }
                Log.warn("Problem scavenging sessions", (Throwable) th);
            } finally {
                currentThread.setContextClassLoader(contextClassLoader);
            }
        }
    }

    @Override // org.mortbay.jetty.servlet.AbstractSessionManager
    protected void addSession(AbstractSessionManager.Session session) {
        this._sessions.put(session.getClusterId(), session);
    }

    @Override // org.mortbay.jetty.servlet.AbstractSessionManager
    public AbstractSessionManager.Session getSession(String str) {
        try {
            if (!this._sessionsLoaded && this._lazyLoad) {
                restoreSessions();
            }
        } catch (Exception e) {
            Log.warn(e);
        }
        Map map = this._sessions;
        if (map == null) {
            return null;
        }
        return (Session) map.get(str);
    }

    @Override // org.mortbay.jetty.servlet.AbstractSessionManager
    protected void invalidateSessions() {
        Iterator it = new ArrayList(this._sessions.values()).iterator();
        while (it.hasNext()) {
            ((Session) it.next()).invalidate();
        }
        this._sessions.clear();
    }

    @Override // org.mortbay.jetty.servlet.AbstractSessionManager
    protected AbstractSessionManager.Session newSession(HttpServletRequest httpServletRequest) {
        return new Session(httpServletRequest);
    }

    @Override // org.mortbay.jetty.servlet.AbstractSessionManager
    protected void removeSession(String str) {
        this._sessions.remove(str);
    }

    public void setStoreDirectory(File file) {
        this._storeDir = file;
    }

    public File getStoreDirectory() {
        return this._storeDir;
    }

    public void setLazyLoad(boolean z) {
        this._lazyLoad = z;
    }

    public boolean isLazyLoad() {
        return this._lazyLoad;
    }

    public void restoreSessions() throws Exception {
        File file = this._storeDir;
        if (file == null || !file.exists()) {
            return;
        }
        if (!this._storeDir.canRead()) {
            Log.warn(new StringBuffer("Unable to restore Sessions: Cannot read from Session storage directory ").append(this._storeDir.getAbsolutePath()).toString());
            return;
        }
        File[] listFiles = this._storeDir.listFiles();
        for (int i = 0; listFiles != null && i < listFiles.length; i++) {
            try {
                FileInputStream fileInputStream = new FileInputStream(listFiles[i]);
                Session restoreSession = restoreSession(fileInputStream);
                fileInputStream.close();
                addSession(restoreSession, false);
                listFiles[i].delete();
            } catch (Exception e) {
                Log.warn(new StringBuffer("Problem restoring session ").append(listFiles[i].getName()).toString(), (Throwable) e);
            }
        }
        this._sessionsLoaded = true;
    }

    public void saveSessions() throws Exception {
        File file = this._storeDir;
        if (file == null || !file.exists()) {
            return;
        }
        if (!this._storeDir.canWrite()) {
            Log.warn(new StringBuffer("Unable to save Sessions: Session persistence storage directory ").append(this._storeDir.getAbsolutePath()).append(" is not writeable").toString());
            return;
        }
        synchronized (this) {
            for (Map.Entry entry : this._sessions.entrySet()) {
                String str = (String) entry.getKey();
                Session session = (Session) entry.getValue();
                try {
                    File file2 = new File(this._storeDir, str);
                    if (file2.exists()) {
                        file2.delete();
                    }
                    file2.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    session.save(fileOutputStream);
                    fileOutputStream.close();
                } catch (Exception e) {
                    Log.warn(new StringBuffer().append("Problem persisting session ").append(str).toString(), (Throwable) e);
                }
            }
        }
    }

    public Session restoreSession(FileInputStream fileInputStream) throws Exception {
        DataInputStream dataInputStream = new DataInputStream(fileInputStream);
        String readUTF = dataInputStream.readUTF();
        dataInputStream.readUTF();
        dataInputStream.readBoolean();
        long readLong = dataInputStream.readLong();
        long readLong2 = dataInputStream.readLong();
        dataInputStream.readLong();
        long readLong3 = dataInputStream.readLong();
        dataInputStream.readInt();
        Session session = new Session(readLong, readUTF);
        session._cookieSet = readLong2;
        session._lastAccessed = readLong3;
        int readInt = dataInputStream.readInt();
        if (readInt > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                arrayList.add(dataInputStream.readUTF());
            }
            ClassLoadingObjectInputStream classLoadingObjectInputStream = new ClassLoadingObjectInputStream(dataInputStream);
            for (int i2 = 0; i2 < readInt; i2++) {
                session.setAttribute((String) arrayList.get(i2), classLoadingObjectInputStream.readObject());
            }
            classLoadingObjectInputStream.close();
        } else {
            session.initValues();
        }
        dataInputStream.close();
        return session;
    }

    protected class Session extends AbstractSessionManager.Session {
        private static final long serialVersionUID = -2134521374206116367L;

        protected Session(HttpServletRequest httpServletRequest) {
            super(httpServletRequest);
        }

        protected Session(long j, String str) {
            super(j, str);
        }

        @Override // org.mortbay.jetty.servlet.AbstractSessionManager.Session, javax.servlet.http.HttpSession
        public void setMaxInactiveInterval(int i) {
            super.setMaxInactiveInterval(i);
            if (this._maxIdleMs <= 0 || this._maxIdleMs / 10 >= HashSessionManager.this._scavengePeriodMs) {
                return;
            }
            HashSessionManager.this.setScavengePeriod((i + 9) / 10);
        }

        @Override // org.mortbay.jetty.servlet.AbstractSessionManager.Session
        protected Map newAttributeMap() {
            return new HashMap(3);
        }

        @Override // org.mortbay.jetty.servlet.AbstractSessionManager.Session, javax.servlet.http.HttpSession
        public void invalidate() throws IllegalStateException {
            super.invalidate();
            remove(getId());
        }

        public void remove(String str) {
            if (str == null || HashSessionManager.this.isStopping() || HashSessionManager.this.isStopped() || HashSessionManager.this._storeDir == null || !HashSessionManager.this._storeDir.exists()) {
                return;
            }
            new File(HashSessionManager.this._storeDir, str).delete();
        }

        public void save(FileOutputStream fileOutputStream) throws IOException {
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
            dataOutputStream.writeUTF(this._clusterId);
            dataOutputStream.writeUTF(this._nodeId);
            dataOutputStream.writeBoolean(this._idChanged);
            dataOutputStream.writeLong(this._created);
            dataOutputStream.writeLong(this._cookieSet);
            dataOutputStream.writeLong(this._accessed);
            dataOutputStream.writeLong(this._lastAccessed);
            dataOutputStream.writeInt(this._requests);
            if (this._values != null) {
                dataOutputStream.writeInt(this._values.size());
                Iterator it = this._values.keySet().iterator();
                while (it.hasNext()) {
                    dataOutputStream.writeUTF((String) it.next());
                }
                Iterator it2 = this._values.values().iterator();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(dataOutputStream);
                while (it2.hasNext()) {
                    objectOutputStream.writeObject(it2.next());
                }
                objectOutputStream.close();
            } else {
                dataOutputStream.writeInt(0);
            }
            dataOutputStream.close();
        }
    }

    protected class ClassLoadingObjectInputStream extends ObjectInputStream {
        public ClassLoadingObjectInputStream(InputStream inputStream) throws IOException {
            super(inputStream);
        }

        public ClassLoadingObjectInputStream() throws IOException {
        }

        @Override // java.io.ObjectInputStream
        public Class resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
            try {
                return Class.forName(objectStreamClass.getName(), false, Thread.currentThread().getContextClassLoader());
            } catch (ClassNotFoundException unused) {
                return super.resolveClass(objectStreamClass);
            }
        }
    }
}
