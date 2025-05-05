package org.mortbay.util;

import j$.util.DesugarCollections;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.mortbay.log.Log;

/* loaded from: classes3.dex */
public class Scanner {
    private FilenameFilter _filter;
    private List _scanDirs;
    private int _scanInterval;
    private TimerTask _task;
    private Timer _timer;
    private List _listeners = DesugarCollections.synchronizedList(new ArrayList());
    private Map _prevScan = new HashMap();
    private Map _currentScan = new HashMap();
    private volatile boolean _running = false;
    private boolean _reportExisting = true;
    private boolean _recursive = true;

    public interface BulkListener extends Listener {
        void filesChanged(List list) throws Exception;
    }

    public interface DiscreteListener extends Listener {
        void fileAdded(String str) throws Exception;

        void fileChanged(String str) throws Exception;

        void fileRemoved(String str) throws Exception;
    }

    public interface Listener {
    }

    public int getScanInterval() {
        return this._scanInterval;
    }

    public synchronized void setScanInterval(int i) {
        this._scanInterval = i;
        schedule();
    }

    public void setScanDir(File file) {
        ArrayList arrayList = new ArrayList();
        this._scanDirs = arrayList;
        arrayList.add(file);
    }

    public File getScanDir() {
        List list = this._scanDirs;
        if (list == null) {
            return null;
        }
        return (File) list.get(0);
    }

    public void setScanDirs(List list) {
        this._scanDirs = list;
    }

    public List getScanDirs() {
        return this._scanDirs;
    }

    public void setRecursive(boolean z) {
        this._recursive = z;
    }

    public boolean getRecursive() {
        return this._recursive;
    }

    public void setFilenameFilter(FilenameFilter filenameFilter) {
        this._filter = filenameFilter;
    }

    public FilenameFilter getFilenameFilter() {
        return this._filter;
    }

    public void setReportExistingFilesOnStartup(boolean z) {
        this._reportExisting = z;
    }

    public synchronized void addListener(Listener listener) {
        if (listener == null) {
            return;
        }
        this._listeners.add(listener);
    }

    public synchronized void removeListener(Listener listener) {
        if (listener == null) {
            return;
        }
        this._listeners.remove(listener);
    }

    public synchronized void start() {
        if (this._running) {
            return;
        }
        this._running = true;
        if (this._reportExisting) {
            scan();
        } else {
            scanFiles();
            this._prevScan.putAll(this._currentScan);
        }
        schedule();
    }

    public TimerTask newTimerTask() {
        return new TimerTask() { // from class: org.mortbay.util.Scanner.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                Scanner.this.scan();
            }
        };
    }

    public Timer newTimer() {
        return new Timer(true);
    }

    public void schedule() {
        if (this._running) {
            Timer timer = this._timer;
            if (timer != null) {
                timer.cancel();
            }
            TimerTask timerTask = this._task;
            if (timerTask != null) {
                timerTask.cancel();
            }
            if (getScanInterval() > 0) {
                this._timer = newTimer();
                TimerTask newTimerTask = newTimerTask();
                this._task = newTimerTask;
                this._timer.schedule(newTimerTask, getScanInterval() * 1000, 1000 * getScanInterval());
            }
        }
    }

    public synchronized void stop() {
        if (this._running) {
            this._running = false;
            Timer timer = this._timer;
            if (timer != null) {
                timer.cancel();
            }
            TimerTask timerTask = this._task;
            if (timerTask != null) {
                timerTask.cancel();
            }
            this._task = null;
            this._timer = null;
        }
    }

    public void scan() {
        scanFiles();
        reportDifferences(this._currentScan, this._prevScan);
        this._prevScan.clear();
        this._prevScan.putAll(this._currentScan);
    }

    public void scanFiles() {
        if (this._scanDirs == null) {
            return;
        }
        this._currentScan.clear();
        for (File file : this._scanDirs) {
            if (file != null && file.exists()) {
                scanFile(file, this._currentScan);
            }
        }
    }

    public void reportDifferences(Map map, Map map2) {
        ArrayList arrayList = new ArrayList();
        HashSet<String> hashSet = new HashSet(map2.keySet());
        for (Map.Entry entry : map.entrySet()) {
            if (!hashSet.contains(entry.getKey())) {
                Log.debug(new StringBuffer("File added: ").append(entry.getKey()).toString());
                reportAddition((String) entry.getKey());
                arrayList.add(entry.getKey());
            } else if (!map2.get(entry.getKey()).equals(entry.getValue())) {
                Log.debug(new StringBuffer("File changed: ").append(entry.getKey()).toString());
                reportChange((String) entry.getKey());
                hashSet.remove(entry.getKey());
                arrayList.add(entry.getKey());
            } else {
                hashSet.remove(entry.getKey());
            }
        }
        if (!hashSet.isEmpty()) {
            for (String str : hashSet) {
                Log.debug(new StringBuffer("File removed: ").append(str).toString());
                reportRemoval(str);
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        reportBulkChanges(arrayList);
    }

    private void scanFile(File file, Map map) {
        try {
            if (file.exists()) {
                if (file.isFile()) {
                    FilenameFilter filenameFilter = this._filter;
                    if (filenameFilter == null || (filenameFilter != null && filenameFilter.accept(file.getParentFile(), file.getName()))) {
                        map.put(file.getCanonicalPath(), new Long(file.lastModified()));
                        return;
                    }
                    return;
                }
                if (file.isDirectory()) {
                    if (this._recursive || this._scanDirs.contains(file)) {
                        for (File file2 : file.listFiles()) {
                            scanFile(file2, map);
                        }
                    }
                }
            }
        } catch (IOException e) {
            Log.warn("Error scanning watched files", (Throwable) e);
        }
    }

    private void warn(Object obj, String str, Throwable th) {
        Log.warn(th);
        Log.warn(new StringBuffer().append(obj).append(" failed on '").append(str).toString());
    }

    private void reportAddition(String str) {
        for (Object obj : this._listeners) {
            try {
                if (obj instanceof DiscreteListener) {
                    ((DiscreteListener) obj).fileAdded(str);
                }
            } catch (Error e) {
                warn(obj, str, e);
            } catch (Exception e2) {
                warn(obj, str, e2);
            }
        }
    }

    private void reportRemoval(String str) {
        for (Object obj : this._listeners) {
            try {
                if (obj instanceof DiscreteListener) {
                    ((DiscreteListener) obj).fileRemoved(str);
                }
            } catch (Error e) {
                warn(obj, str, e);
            } catch (Exception e2) {
                warn(obj, str, e2);
            }
        }
    }

    private void reportChange(String str) {
        for (Object obj : this._listeners) {
            try {
                if (obj instanceof DiscreteListener) {
                    ((DiscreteListener) obj).fileChanged(str);
                }
            } catch (Error e) {
                warn(obj, str, e);
            } catch (Exception e2) {
                warn(obj, str, e2);
            }
        }
    }

    private void reportBulkChanges(List list) {
        for (Object obj : this._listeners) {
            try {
                if (obj instanceof BulkListener) {
                    ((BulkListener) obj).filesChanged(list);
                }
            } catch (Error e) {
                warn(obj, list.toString(), e);
            } catch (Exception e2) {
                warn(obj, list.toString(), e2);
            }
        }
    }
}
