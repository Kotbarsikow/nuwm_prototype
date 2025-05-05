package org.mortbay.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import org.mortbay.log.Log;

/* loaded from: classes3.dex */
public class UncheckedPrintWriter extends PrintWriter {
    private boolean autoFlush;
    private String lineSeparator;

    public UncheckedPrintWriter(Writer writer) {
        this(writer, false);
    }

    public UncheckedPrintWriter(Writer writer, boolean z) {
        super(writer, z);
        this.autoFlush = z;
        this.lineSeparator = System.getProperty("line.separator");
    }

    public UncheckedPrintWriter(OutputStream outputStream) {
        this(outputStream, false);
    }

    public UncheckedPrintWriter(OutputStream outputStream, boolean z) {
        this(new BufferedWriter(new OutputStreamWriter(outputStream)), z);
    }

    private void isOpen() throws IOException {
        if (((PrintWriter) this).out == null) {
            throw new IOException("Stream closed");
        }
    }

    @Override // java.io.PrintWriter, java.io.Writer, java.io.Flushable
    public void flush() {
        try {
            synchronized (this.lock) {
                isOpen();
                this.out.flush();
            }
        } catch (IOException e) {
            Log.debug(e);
            setError();
            throw new RuntimeIOException(e);
        }
    }

    @Override // java.io.PrintWriter, java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            synchronized (this.lock) {
                this.out.close();
            }
        } catch (IOException e) {
            Log.debug(e);
            setError();
            throw new RuntimeIOException(e);
        }
    }

    @Override // java.io.PrintWriter, java.io.Writer
    public void write(int i) {
        try {
            synchronized (this.lock) {
                isOpen();
                this.out.write(i);
            }
        } catch (InterruptedIOException unused) {
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            Log.debug(e);
            setError();
            throw new RuntimeIOException(e);
        }
    }

    @Override // java.io.PrintWriter, java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        try {
            synchronized (this.lock) {
                isOpen();
                this.out.write(cArr, i, i2);
            }
        } catch (InterruptedIOException unused) {
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            Log.debug(e);
            setError();
            throw new RuntimeIOException(e);
        }
    }

    @Override // java.io.PrintWriter, java.io.Writer
    public void write(char[] cArr) {
        write(cArr, 0, cArr.length);
    }

    @Override // java.io.PrintWriter, java.io.Writer
    public void write(String str, int i, int i2) {
        try {
            synchronized (this.lock) {
                isOpen();
                this.out.write(str, i, i2);
            }
        } catch (InterruptedIOException unused) {
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            Log.debug(e);
            setError();
            throw new RuntimeIOException(e);
        }
    }

    @Override // java.io.PrintWriter, java.io.Writer
    public void write(String str) {
        write(str, 0, str.length());
    }

    private void newLine() {
        try {
            synchronized (this.lock) {
                isOpen();
                this.out.write(this.lineSeparator);
                if (this.autoFlush) {
                    this.out.flush();
                }
            }
        } catch (InterruptedIOException unused) {
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            Log.debug(e);
            setError();
            throw new RuntimeIOException(e);
        }
    }

    @Override // java.io.PrintWriter
    public void print(boolean z) {
        write(z ? "true" : "false");
    }

    @Override // java.io.PrintWriter
    public void print(char c) {
        write(c);
    }

    @Override // java.io.PrintWriter
    public void print(int i) {
        write(String.valueOf(i));
    }

    @Override // java.io.PrintWriter
    public void print(long j) {
        write(String.valueOf(j));
    }

    @Override // java.io.PrintWriter
    public void print(float f) {
        write(String.valueOf(f));
    }

    @Override // java.io.PrintWriter
    public void print(double d) {
        write(String.valueOf(d));
    }

    @Override // java.io.PrintWriter
    public void print(char[] cArr) {
        write(cArr);
    }

    @Override // java.io.PrintWriter
    public void print(String str) {
        if (str == null) {
            str = "null";
        }
        write(str);
    }

    @Override // java.io.PrintWriter
    public void print(Object obj) {
        write(String.valueOf(obj));
    }

    @Override // java.io.PrintWriter
    public void println() {
        newLine();
    }

    @Override // java.io.PrintWriter
    public void println(boolean z) {
        synchronized (this.lock) {
            print(z);
            println();
        }
    }

    @Override // java.io.PrintWriter
    public void println(char c) {
        synchronized (this.lock) {
            print(c);
            println();
        }
    }

    @Override // java.io.PrintWriter
    public void println(int i) {
        synchronized (this.lock) {
            print(i);
            println();
        }
    }

    @Override // java.io.PrintWriter
    public void println(long j) {
        synchronized (this.lock) {
            print(j);
            println();
        }
    }

    @Override // java.io.PrintWriter
    public void println(float f) {
        synchronized (this.lock) {
            print(f);
            println();
        }
    }

    @Override // java.io.PrintWriter
    public void println(double d) {
        synchronized (this.lock) {
            print(d);
            println();
        }
    }

    @Override // java.io.PrintWriter
    public void println(char[] cArr) {
        synchronized (this.lock) {
            print(cArr);
            println();
        }
    }

    @Override // java.io.PrintWriter
    public void println(String str) {
        synchronized (this.lock) {
            print(str);
            println();
        }
    }

    @Override // java.io.PrintWriter
    public void println(Object obj) {
        synchronized (this.lock) {
            print(obj);
            println();
        }
    }
}
