package org.mortbay.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes3.dex */
public class RolloverFileOutputStream extends FilterOutputStream {
    static final String YYYY_MM_DD = "yyyy_mm_dd";
    private static Timer __rollover;
    static /* synthetic */ Class class$org$mortbay$util$RolloverFileOutputStream;
    private boolean _append;
    private File _file;
    private SimpleDateFormat _fileBackupFormat;
    private SimpleDateFormat _fileDateFormat;
    private String _filename;
    private int _retainDays;
    private RollTask _rollTask;

    public RolloverFileOutputStream(String str) throws IOException {
        this(str, true, Integer.getInteger("ROLLOVERFILE_RETAIN_DAYS", 31).intValue());
    }

    public RolloverFileOutputStream(String str, boolean z) throws IOException {
        this(str, z, Integer.getInteger("ROLLOVERFILE_RETAIN_DAYS", 31).intValue());
    }

    public RolloverFileOutputStream(String str, boolean z, int i) throws IOException {
        this(str, z, i, TimeZone.getDefault());
    }

    public RolloverFileOutputStream(String str, boolean z, int i, TimeZone timeZone) throws IOException {
        this(str, z, i, timeZone, null, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RolloverFileOutputStream(String str, boolean z, int i, TimeZone timeZone, String str2, String str3) throws IOException {
        super(null);
        this._fileDateFormat = new SimpleDateFormat(str2 == null ? System.getProperty("ROLLOVERFILE_DATE_FORMAT", "yyyy_MM_dd") : str2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str3 == null ? System.getProperty("ROLLOVERFILE_BACKUP_FORMAT", "HHmmssSSS") : str3);
        this._fileBackupFormat = simpleDateFormat;
        simpleDateFormat.setTimeZone(timeZone);
        this._fileDateFormat.setTimeZone(timeZone);
        if (str != null) {
            str = str.trim();
            if (str.length() == 0) {
                str = null;
            }
        }
        if (str == null) {
            throw new IllegalArgumentException("Invalid filename");
        }
        this._filename = str;
        this._append = z;
        this._retainDays = i;
        setFile();
        Class cls = class$org$mortbay$util$RolloverFileOutputStream;
        if (cls == null) {
            cls = class$("org.mortbay.util.RolloverFileOutputStream");
            class$org$mortbay$util$RolloverFileOutputStream = cls;
        }
        synchronized (cls) {
            if (__rollover == null) {
                __rollover = new Timer(true);
            }
            this._rollTask = new RollTask();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(timeZone);
            GregorianCalendar gregorianCalendar = new GregorianCalendar(calendar.get(1), calendar.get(2), calendar.get(5), 23, 0);
            gregorianCalendar.setTimeZone(timeZone);
            gregorianCalendar.add(10, 1);
            __rollover.scheduleAtFixedRate(this._rollTask, gregorianCalendar.getTime(), 86400000L);
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public String getFilename() {
        return this._filename;
    }

    public String getDatedFilename() {
        File file = this._file;
        if (file == null) {
            return null;
        }
        return file.toString();
    }

    public int getRetainDays() {
        return this._retainDays;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void setFile() throws IOException {
        this._filename = new File(this._filename).getCanonicalPath();
        File file = new File(this._filename);
        File file2 = new File(file.getParent());
        if (!file2.isDirectory() || !file2.canWrite()) {
            throw new IOException(new StringBuffer("Cannot write log directory ").append(file2).toString());
        }
        Date date = new Date();
        String name = file.getName();
        int indexOf = name.toLowerCase().indexOf(YYYY_MM_DD);
        if (indexOf >= 0) {
            file = new File(file2, new StringBuffer().append(name.substring(0, indexOf)).append(this._fileDateFormat.format(date)).append(name.substring(indexOf + 10)).toString());
        }
        if (file.exists() && !file.canWrite()) {
            throw new IOException(new StringBuffer("Cannot write log file ").append(file).toString());
        }
        if (this.out == null || !file.equals(this._file)) {
            this._file = file;
            if (!this._append && file.exists()) {
                file.renameTo(new File(new StringBuffer().append(file.toString()).append(".").append(this._fileBackupFormat.format(date)).toString()));
            }
            OutputStream outputStream = this.out;
            this.out = new FileOutputStream(file.toString(), this._append);
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeOldFiles() {
        if (this._retainDays > 0) {
            long currentTimeMillis = System.currentTimeMillis();
            File file = new File(this._filename);
            File file2 = new File(file.getParent());
            String name = file.getName();
            int indexOf = name.toLowerCase().indexOf(YYYY_MM_DD);
            if (indexOf < 0) {
                return;
            }
            String substring = name.substring(0, indexOf);
            String substring2 = name.substring(indexOf + 10);
            for (String str : file2.list()) {
                if (str.startsWith(substring) && str.indexOf(substring2, substring.length()) >= 0) {
                    File file3 = new File(file2, str);
                    if ((currentTimeMillis - file3.lastModified()) / 86400000 > this._retainDays) {
                        file3.delete();
                    }
                }
            }
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.out.write(bArr);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Class cls = class$org$mortbay$util$RolloverFileOutputStream;
        if (cls == null) {
            cls = class$("org.mortbay.util.RolloverFileOutputStream");
            class$org$mortbay$util$RolloverFileOutputStream = cls;
        }
        synchronized (cls) {
            try {
                try {
                    super.close();
                    this.out = null;
                    this._file = null;
                    this._rollTask.cancel();
                } catch (Throwable th) {
                    this.out = null;
                    this._file = null;
                    throw th;
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    private class RollTask extends TimerTask {
        private RollTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            try {
                RolloverFileOutputStream.this.setFile();
                RolloverFileOutputStream.this.removeOldFiles();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
