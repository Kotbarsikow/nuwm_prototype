package com.google.api.client.util.store;

import com.google.api.client.util.IOUtils;
import com.google.api.client.util.Maps;
import com.google.api.client.util.Throwables;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
public class FileDataStoreFactory extends AbstractDataStoreFactory {
    private static final Logger LOGGER = Logger.getLogger(FileDataStoreFactory.class.getName());
    private final File dataDirectory;

    public FileDataStoreFactory(File file) throws IOException {
        File canonicalFile = file.getCanonicalFile();
        this.dataDirectory = canonicalFile;
        if (IOUtils.isSymbolicLink(canonicalFile)) {
            String valueOf = String.valueOf(String.valueOf(canonicalFile));
            StringBuilder sb = new StringBuilder(valueOf.length() + 31);
            sb.append("unable to use a symbolic link: ");
            sb.append(valueOf);
            throw new IOException(sb.toString());
        }
        if (!canonicalFile.exists() && !canonicalFile.mkdirs()) {
            String valueOf2 = String.valueOf(String.valueOf(canonicalFile));
            StringBuilder sb2 = new StringBuilder(valueOf2.length() + 28);
            sb2.append("unable to create directory: ");
            sb2.append(valueOf2);
            throw new IOException(sb2.toString());
        }
        setPermissionsToOwnerOnly(canonicalFile);
    }

    public final File getDataDirectory() {
        return this.dataDirectory;
    }

    @Override // com.google.api.client.util.store.AbstractDataStoreFactory
    protected <V extends Serializable> DataStore<V> createDataStore(String str) throws IOException {
        return new FileDataStore(this, this.dataDirectory, str);
    }

    static class FileDataStore<V extends Serializable> extends AbstractMemoryDataStore<V> {
        private final File dataFile;

        FileDataStore(FileDataStoreFactory fileDataStoreFactory, File file, String str) throws IOException {
            super(fileDataStoreFactory, str);
            File file2 = new File(file, str);
            this.dataFile = file2;
            if (IOUtils.isSymbolicLink(file2)) {
                String valueOf = String.valueOf(String.valueOf(file2));
                StringBuilder sb = new StringBuilder(valueOf.length() + 31);
                sb.append("unable to use a symbolic link: ");
                sb.append(valueOf);
                throw new IOException(sb.toString());
            }
            if (file2.createNewFile()) {
                this.keyValueMap = Maps.newHashMap();
                save();
            } else {
                this.keyValueMap = (HashMap) IOUtils.deserialize(new FileInputStream(file2));
            }
        }

        @Override // com.google.api.client.util.store.AbstractMemoryDataStore
        void save() throws IOException {
            IOUtils.serialize(this.keyValueMap, new FileOutputStream(this.dataFile));
        }

        @Override // com.google.api.client.util.store.AbstractDataStore, com.google.api.client.util.store.DataStore
        public FileDataStoreFactory getDataStoreFactory() {
            return (FileDataStoreFactory) super.getDataStoreFactory();
        }
    }

    static void setPermissionsToOwnerOnly(File file) throws IOException {
        try {
            Class cls = Boolean.TYPE;
            Method method = File.class.getMethod("setReadable", cls, cls);
            Class cls2 = Boolean.TYPE;
            Method method2 = File.class.getMethod("setWritable", cls2, cls2);
            Class cls3 = Boolean.TYPE;
            Method method3 = File.class.getMethod("setExecutable", cls3, cls3);
            if (!((Boolean) method.invoke(file, false, false)).booleanValue() || !((Boolean) method2.invoke(file, false, false)).booleanValue() || !((Boolean) method3.invoke(file, false, false)).booleanValue()) {
                Logger logger = LOGGER;
                String valueOf = String.valueOf(String.valueOf(file));
                StringBuilder sb = new StringBuilder(valueOf.length() + 44);
                sb.append("unable to change permissions for everybody: ");
                sb.append(valueOf);
                logger.warning(sb.toString());
            }
            if (((Boolean) method.invoke(file, true, true)).booleanValue() && ((Boolean) method2.invoke(file, true, true)).booleanValue() && ((Boolean) method3.invoke(file, true, true)).booleanValue()) {
                return;
            }
            Logger logger2 = LOGGER;
            String valueOf2 = String.valueOf(String.valueOf(file));
            StringBuilder sb2 = new StringBuilder(valueOf2.length() + 40);
            sb2.append("unable to change permissions for owner: ");
            sb2.append(valueOf2);
            logger2.warning(sb2.toString());
        } catch (IllegalAccessException | IllegalArgumentException | SecurityException unused) {
        } catch (NoSuchMethodException unused2) {
            Logger logger3 = LOGGER;
            String valueOf3 = String.valueOf(String.valueOf(file));
            StringBuilder sb3 = new StringBuilder(valueOf3.length() + 93);
            sb3.append("Unable to set permissions for ");
            sb3.append(valueOf3);
            sb3.append(", likely because you are running a version of Java prior to 1.6");
            logger3.warning(sb3.toString());
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            Throwables.propagateIfPossible(cause, IOException.class);
            throw new RuntimeException(cause);
        }
    }
}
