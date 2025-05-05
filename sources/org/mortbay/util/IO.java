package org.mortbay.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import org.mortbay.log.Log;
import org.mortbay.thread.BoundedThreadPool;

/* loaded from: classes3.dex */
public class IO extends BoundedThreadPool {
    public static final String CRLF = "\r\n";
    public static final byte[] CRLF_BYTES = {13, 10};
    public static int bufferSize = 16384;
    private static NullOS __nullStream = new NullOS();
    private static ClosedIS __closedStream = new ClosedIS();
    private static NullWrite __nullWriter = new NullWrite();

    private static class Singleton {
        static final IO __instance;

        private Singleton() {
        }

        static {
            IO io2 = new IO();
            __instance = io2;
            try {
                io2.start();
            } catch (Exception e) {
                Log.warn(e);
                System.exit(1);
            }
        }
    }

    public static IO instance() {
        return Singleton.__instance;
    }

    static class Job implements Runnable {
        InputStream in;
        OutputStream out;
        Reader read;
        Writer write;

        Job(InputStream inputStream, OutputStream outputStream) {
            this.in = inputStream;
            this.out = outputStream;
            this.read = null;
            this.write = null;
        }

        Job(Reader reader, Writer writer) {
            this.in = null;
            this.out = null;
            this.read = reader;
            this.write = writer;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                InputStream inputStream = this.in;
                if (inputStream != null) {
                    IO.copy(inputStream, this.out, -1L);
                } else {
                    IO.copy(this.read, this.write, -1L);
                }
            } catch (IOException e) {
                Log.ignore(e);
                try {
                    OutputStream outputStream = this.out;
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    Writer writer = this.write;
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException e2) {
                    Log.ignore(e2);
                }
            }
        }
    }

    public static void copyThread(InputStream inputStream, OutputStream outputStream) {
        try {
            Job job = new Job(inputStream, outputStream);
            if (instance().dispatch(job)) {
                return;
            }
            job.run();
        } catch (Exception e) {
            Log.warn(e);
        }
    }

    public static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        copy(inputStream, outputStream, -1L);
    }

    public static void copyThread(Reader reader, Writer writer) {
        try {
            Job job = new Job(reader, writer);
            if (instance().dispatch(job)) {
                return;
            }
            job.run();
        } catch (Exception e) {
            Log.warn(e);
        }
    }

    public static void copy(Reader reader, Writer writer) throws IOException {
        copy(reader, writer, -1L);
    }

    public static void copy(InputStream inputStream, OutputStream outputStream, long j) throws IOException {
        int read;
        byte[] bArr = new byte[bufferSize];
        if (j >= 0) {
            while (j > 0) {
                int i = bufferSize;
                if (j < i) {
                    read = inputStream.read(bArr, 0, (int) j);
                } else {
                    read = inputStream.read(bArr, 0, i);
                }
                if (read == -1) {
                    return;
                }
                j -= read;
                outputStream.write(bArr, 0, read);
            }
            return;
        }
        while (true) {
            int read2 = inputStream.read(bArr, 0, bufferSize);
            if (read2 < 0) {
                return;
            } else {
                outputStream.write(bArr, 0, read2);
            }
        }
    }

    public static void copy(Reader reader, Writer writer, long j) throws IOException {
        int read;
        int read2;
        char[] cArr = new char[bufferSize];
        if (j >= 0) {
            while (j > 0) {
                int i = bufferSize;
                if (j < i) {
                    read2 = reader.read(cArr, 0, (int) j);
                } else {
                    read2 = reader.read(cArr, 0, i);
                }
                if (read2 == -1) {
                    return;
                }
                j -= read2;
                writer.write(cArr, 0, read2);
            }
            return;
        }
        if (writer instanceof PrintWriter) {
            PrintWriter printWriter = (PrintWriter) writer;
            while (!printWriter.checkError() && (read = reader.read(cArr, 0, bufferSize)) != -1) {
                writer.write(cArr, 0, read);
            }
            return;
        }
        while (true) {
            int read3 = reader.read(cArr, 0, bufferSize);
            if (read3 == -1) {
                return;
            } else {
                writer.write(cArr, 0, read3);
            }
        }
    }

    public static void copy(File file, File file2) throws IOException {
        if (file.isDirectory()) {
            copyDir(file, file2);
        } else {
            copyFile(file, file2);
        }
    }

    public static void copyDir(File file, File file2) throws IOException {
        if (file2.exists()) {
            if (!file2.isDirectory()) {
                throw new IllegalArgumentException(file2.toString());
            }
        } else {
            file2.mkdirs();
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; i++) {
                String name = listFiles[i].getName();
                if (!".".equals(name) && !"..".equals(name)) {
                    copy(listFiles[i], new File(file2, name));
                }
            }
        }
    }

    public static void copyFile(File file, File file2) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        copy(fileInputStream, fileOutputStream);
        fileInputStream.close();
        fileOutputStream.close();
    }

    public static String toString(InputStream inputStream) throws IOException {
        return toString(inputStream, null);
    }

    public static String toString(InputStream inputStream, String str) throws IOException {
        StringWriter stringWriter = new StringWriter();
        copy(str == null ? new InputStreamReader(inputStream) : new InputStreamReader(inputStream, str), stringWriter);
        return stringWriter.toString();
    }

    public static String toString(Reader reader) throws IOException {
        StringWriter stringWriter = new StringWriter();
        copy(reader, stringWriter);
        return stringWriter.toString();
    }

    public static boolean delete(File file) {
        if (!file.exists()) {
            return false;
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            for (int i = 0; listFiles != null && i < listFiles.length; i++) {
                delete(listFiles[i]);
            }
        }
        return file.delete();
    }

    public static void close(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                Log.ignore(e);
            }
        }
    }

    public static byte[] readBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        copy(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static void close(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                Log.ignore(e);
            }
        }
    }

    public static OutputStream getNullStream() {
        return __nullStream;
    }

    public static InputStream getClosedStream() {
        return __closedStream;
    }

    private static class NullOS extends OutputStream {
        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() {
        }

        @Override // java.io.OutputStream
        public void write(int i) {
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) {
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) {
        }

        private NullOS() {
        }

        /* synthetic */ NullOS(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    private static class ClosedIS extends InputStream {
        @Override // java.io.InputStream
        public int read() throws IOException {
            return -1;
        }

        private ClosedIS() {
        }

        /* synthetic */ ClosedIS(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public static Writer getNullWriter() {
        return __nullWriter;
    }

    private static class NullWrite extends Writer {
        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        @Override // java.io.Writer
        public void write(int i) {
        }

        @Override // java.io.Writer
        public void write(String str) {
        }

        @Override // java.io.Writer
        public void write(String str, int i, int i2) {
        }

        @Override // java.io.Writer
        public void write(char[] cArr) {
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) {
        }

        private NullWrite() {
        }

        /* synthetic */ NullWrite(AnonymousClass1 anonymousClass1) {
            this();
        }
    }
}
