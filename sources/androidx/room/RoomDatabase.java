package androidx.room;

import android.app.ActivityManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Looper;
import android.util.Log;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.collection.SparseArrayCompat;
import androidx.core.app.ActivityManagerCompat;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes.dex */
public abstract class RoomDatabase {
    private static final String DB_IMPL_SUFFIX = "_Impl";
    public static final int MAX_BIND_PARAMETER_CNT = 999;
    private boolean mAllowMainThreadQueries;
    protected List<Callback> mCallbacks;
    protected volatile SupportSQLiteDatabase mDatabase;
    private SupportSQLiteOpenHelper mOpenHelper;
    private Executor mQueryExecutor;
    boolean mWriteAheadLoggingEnabled;
    private final ReentrantLock mCloseLock = new ReentrantLock();
    private final InvalidationTracker mInvalidationTracker = createInvalidationTracker();

    public static abstract class Callback {
        public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
        }

        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
        }
    }

    public abstract void clearAllTables();

    protected abstract InvalidationTracker createInvalidationTracker();

    protected abstract SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration);

    Lock getCloseLock() {
        return this.mCloseLock;
    }

    public void init(DatabaseConfiguration databaseConfiguration) {
        this.mOpenHelper = createOpenHelper(databaseConfiguration);
        boolean z = databaseConfiguration.journalMode == JournalMode.WRITE_AHEAD_LOGGING;
        this.mOpenHelper.setWriteAheadLoggingEnabled(z);
        this.mCallbacks = databaseConfiguration.callbacks;
        this.mQueryExecutor = databaseConfiguration.queryExecutor;
        this.mAllowMainThreadQueries = databaseConfiguration.allowMainThreadQueries;
        this.mWriteAheadLoggingEnabled = z;
    }

    public SupportSQLiteOpenHelper getOpenHelper() {
        return this.mOpenHelper;
    }

    public boolean isOpen() {
        SupportSQLiteDatabase supportSQLiteDatabase = this.mDatabase;
        return supportSQLiteDatabase != null && supportSQLiteDatabase.isOpen();
    }

    public void close() {
        if (isOpen()) {
            try {
                this.mCloseLock.lock();
                this.mOpenHelper.close();
            } finally {
                this.mCloseLock.unlock();
            }
        }
    }

    public void assertNotMainThread() {
        if (!this.mAllowMainThreadQueries && isMainThread()) {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
        }
    }

    public Cursor query(String str, Object[] objArr) {
        return this.mOpenHelper.getWritableDatabase().query(new SimpleSQLiteQuery(str, objArr));
    }

    public Cursor query(SupportSQLiteQuery supportSQLiteQuery) {
        assertNotMainThread();
        return this.mOpenHelper.getWritableDatabase().query(supportSQLiteQuery);
    }

    public SupportSQLiteStatement compileStatement(String str) {
        assertNotMainThread();
        return this.mOpenHelper.getWritableDatabase().compileStatement(str);
    }

    public void beginTransaction() {
        assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = this.mOpenHelper.getWritableDatabase();
        this.mInvalidationTracker.syncTriggers(writableDatabase);
        writableDatabase.beginTransaction();
    }

    public void endTransaction() {
        this.mOpenHelper.getWritableDatabase().endTransaction();
        if (inTransaction()) {
            return;
        }
        this.mInvalidationTracker.refreshVersionsAsync();
    }

    public Executor getQueryExecutor() {
        return this.mQueryExecutor;
    }

    public void setTransactionSuccessful() {
        this.mOpenHelper.getWritableDatabase().setTransactionSuccessful();
    }

    public void runInTransaction(Runnable runnable) {
        beginTransaction();
        try {
            runnable.run();
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public <V> V runInTransaction(Callable<V> callable) {
        beginTransaction();
        try {
            try {
                V call = callable.call();
                setTransactionSuccessful();
                return call;
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                throw new RuntimeException("Exception in transaction", e2);
            }
        } finally {
            endTransaction();
        }
    }

    protected void internalInitInvalidationTracker(SupportSQLiteDatabase supportSQLiteDatabase) {
        this.mInvalidationTracker.internalInit(supportSQLiteDatabase);
    }

    public InvalidationTracker getInvalidationTracker() {
        return this.mInvalidationTracker;
    }

    public boolean inTransaction() {
        return this.mOpenHelper.getWritableDatabase().inTransaction();
    }

    public enum JournalMode {
        AUTOMATIC,
        TRUNCATE,
        WRITE_AHEAD_LOGGING;

        JournalMode resolve(Context context) {
            if (this != AUTOMATIC) {
                return this;
            }
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager != null && !ActivityManagerCompat.isLowRamDevice(activityManager)) {
                return WRITE_AHEAD_LOGGING;
            }
            return TRUNCATE;
        }
    }

    public static class Builder<T extends RoomDatabase> {
        private boolean mAllowMainThreadQueries;
        private ArrayList<Callback> mCallbacks;
        private final Context mContext;
        private final Class<T> mDatabaseClass;
        private SupportSQLiteOpenHelper.Factory mFactory;
        private Set<Integer> mMigrationStartAndEndVersions;
        private Set<Integer> mMigrationsNotRequiredFrom;
        private final String mName;
        private Executor mQueryExecutor;
        private JournalMode mJournalMode = JournalMode.AUTOMATIC;
        private boolean mRequireMigration = true;
        private final MigrationContainer mMigrationContainer = new MigrationContainer();

        Builder(Context context, Class<T> cls, String str) {
            this.mContext = context;
            this.mDatabaseClass = cls;
            this.mName = str;
        }

        public Builder<T> openHelperFactory(SupportSQLiteOpenHelper.Factory factory) {
            this.mFactory = factory;
            return this;
        }

        public Builder<T> addMigrations(Migration... migrationArr) {
            if (this.mMigrationStartAndEndVersions == null) {
                this.mMigrationStartAndEndVersions = new HashSet();
            }
            for (Migration migration : migrationArr) {
                this.mMigrationStartAndEndVersions.add(Integer.valueOf(migration.startVersion));
                this.mMigrationStartAndEndVersions.add(Integer.valueOf(migration.endVersion));
            }
            this.mMigrationContainer.addMigrations(migrationArr);
            return this;
        }

        public Builder<T> allowMainThreadQueries() {
            this.mAllowMainThreadQueries = true;
            return this;
        }

        public Builder<T> setJournalMode(JournalMode journalMode) {
            this.mJournalMode = journalMode;
            return this;
        }

        public Builder<T> setQueryExecutor(Executor executor) {
            this.mQueryExecutor = executor;
            return this;
        }

        public Builder<T> fallbackToDestructiveMigration() {
            this.mRequireMigration = false;
            return this;
        }

        public Builder<T> fallbackToDestructiveMigrationFrom(int... iArr) {
            if (this.mMigrationsNotRequiredFrom == null) {
                this.mMigrationsNotRequiredFrom = new HashSet(iArr.length);
            }
            for (int i : iArr) {
                this.mMigrationsNotRequiredFrom.add(Integer.valueOf(i));
            }
            return this;
        }

        public Builder<T> addCallback(Callback callback) {
            if (this.mCallbacks == null) {
                this.mCallbacks = new ArrayList<>();
            }
            this.mCallbacks.add(callback);
            return this;
        }

        public T build() {
            if (this.mContext == null) {
                throw new IllegalArgumentException("Cannot provide null context for the database.");
            }
            if (this.mDatabaseClass == null) {
                throw new IllegalArgumentException("Must provide an abstract class that extends RoomDatabase");
            }
            if (this.mQueryExecutor == null) {
                this.mQueryExecutor = ArchTaskExecutor.getIOThreadExecutor();
            }
            Set<Integer> set = this.mMigrationStartAndEndVersions;
            if (set != null && this.mMigrationsNotRequiredFrom != null) {
                for (Integer num : set) {
                    if (this.mMigrationsNotRequiredFrom.contains(num)) {
                        throw new IllegalArgumentException("Inconsistency detected. A Migration was supplied to addMigration(Migration... migrations) that has a start or end version equal to a start version supplied to fallbackToDestructiveMigrationFrom(int... startVersions). Start version: " + num);
                    }
                }
            }
            if (this.mFactory == null) {
                this.mFactory = new FrameworkSQLiteOpenHelperFactory();
            }
            Context context = this.mContext;
            DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration(context, this.mName, this.mFactory, this.mMigrationContainer, this.mCallbacks, this.mAllowMainThreadQueries, this.mJournalMode.resolve(context), this.mQueryExecutor, this.mRequireMigration, this.mMigrationsNotRequiredFrom);
            T t = (T) Room.getGeneratedImplementation(this.mDatabaseClass, RoomDatabase.DB_IMPL_SUFFIX);
            t.init(databaseConfiguration);
            return t;
        }
    }

    public static class MigrationContainer {
        private SparseArrayCompat<SparseArrayCompat<Migration>> mMigrations = new SparseArrayCompat<>();

        public void addMigrations(Migration... migrationArr) {
            for (Migration migration : migrationArr) {
                addMigration(migration);
            }
        }

        private void addMigration(Migration migration) {
            int i = migration.startVersion;
            int i2 = migration.endVersion;
            SparseArrayCompat<Migration> sparseArrayCompat = this.mMigrations.get(i);
            if (sparseArrayCompat == null) {
                sparseArrayCompat = new SparseArrayCompat<>();
                this.mMigrations.put(i, sparseArrayCompat);
            }
            Migration migration2 = sparseArrayCompat.get(i2);
            if (migration2 != null) {
                Log.w("ROOM", "Overriding migration " + migration2 + " with " + migration);
            }
            sparseArrayCompat.append(i2, migration);
        }

        public List<Migration> findMigrationPath(int i, int i2) {
            if (i == i2) {
                return Collections.emptyList();
            }
            return findUpMigrationPath(new ArrayList(), i2 > i, i, i2);
        }

        /* JADX WARN: Removed duplicated region for block: B:36:0x0019 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:9:0x001a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private java.util.List<androidx.room.migration.Migration> findUpMigrationPath(java.util.List<androidx.room.migration.Migration> r10, boolean r11, int r12, int r13) {
            /*
                r9 = this;
                r0 = -1
                r1 = 1
                if (r11 == 0) goto L6
                r2 = -1
                goto L7
            L6:
                r2 = 1
            L7:
                if (r11 == 0) goto Lc
                if (r12 >= r13) goto L47
                goto Le
            Lc:
                if (r12 <= r13) goto L47
            Le:
                androidx.collection.SparseArrayCompat<androidx.collection.SparseArrayCompat<androidx.room.migration.Migration>> r3 = r9.mMigrations
                java.lang.Object r3 = r3.get(r12)
                androidx.collection.SparseArrayCompat r3 = (androidx.collection.SparseArrayCompat) r3
                r4 = 0
                if (r3 != 0) goto L1a
                return r4
            L1a:
                int r5 = r3.size()
                r6 = 0
                if (r11 == 0) goto L25
                int r5 = r5 + (-1)
                r7 = -1
                goto L27
            L25:
                r7 = r5
                r5 = 0
            L27:
                if (r5 == r7) goto L44
                int r8 = r3.keyAt(r5)
                if (r11 == 0) goto L34
                if (r8 > r13) goto L42
                if (r8 <= r12) goto L42
                goto L38
            L34:
                if (r8 < r13) goto L42
                if (r8 >= r12) goto L42
            L38:
                java.lang.Object r12 = r3.valueAt(r5)
                r10.add(r12)
                r12 = r8
                r6 = 1
                goto L44
            L42:
                int r5 = r5 + r2
                goto L27
            L44:
                if (r6 != 0) goto L7
                return r4
            L47:
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomDatabase.MigrationContainer.findUpMigrationPath(java.util.List, boolean, int, int):java.util.List");
        }
    }

    private static boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
