package j$.sun.misc;

import j$.util.function.BiConsumer$CC;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import sun.misc.Unsafe;

/* loaded from: classes4.dex */
public final class DesugarUnsafe {
    private static final DesugarUnsafe theUnsafeWrapper;
    private final Unsafe theUnsafe;

    static {
        Field unsafeField = getUnsafeField();
        unsafeField.setAccessible(true);
        try {
            theUnsafeWrapper = new DesugarUnsafe((Unsafe) unsafeField.get(null));
        } catch (IllegalAccessException e) {
            throw new AssertionError("Couldn't get the Unsafe", e);
        }
    }

    DesugarUnsafe(Unsafe unsafe) {
        this.theUnsafe = unsafe;
    }

    private static Field getUnsafeField() {
        try {
            return Unsafe.class.getDeclaredField("theUnsafe");
        } catch (NoSuchFieldException e) {
            for (Field field : Unsafe.class.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers()) && Unsafe.class.isAssignableFrom(field.getType())) {
                    return field;
                }
            }
            throw new AssertionError("Couldn't find the Unsafe", e);
        }
    }

    public static DesugarUnsafe getUnsafe() {
        return theUnsafeWrapper;
    }

    public final int getAndAddInt(Object obj, long j) {
        int intVolatile;
        do {
            intVolatile = this.theUnsafe.getIntVolatile(obj, j);
        } while (!this.theUnsafe.compareAndSwapInt(obj, j, intVolatile, intVolatile - 4));
        return intVolatile;
    }

    public final long objectFieldOffset(Field field) {
        return this.theUnsafe.objectFieldOffset(field);
    }

    public final long objectFieldOffset(Class cls, String str) {
        try {
            return objectFieldOffset(cls.getDeclaredField(str));
        } catch (NoSuchFieldException e) {
            throw new AssertionError("Cannot find field:", e);
        }
    }

    public final int arrayBaseOffset(Class cls) {
        return this.theUnsafe.arrayBaseOffset(cls);
    }

    public final int arrayIndexScale(Class cls) {
        return this.theUnsafe.arrayIndexScale(cls);
    }

    public final Object getObjectAcquire(Object obj, long j) {
        return this.theUnsafe.getObjectVolatile(obj, j);
    }

    public final void putObjectRelease(Object obj, long j, Object obj2) {
        this.theUnsafe.putObjectVolatile(obj, j, obj2);
    }

    public final boolean compareAndSetInt(Object obj, long j, int i, int i2) {
        return this.theUnsafe.compareAndSwapInt(obj, j, i, i2);
    }

    public final boolean compareAndSetLong(Object obj, long j, long j2, long j3) {
        return this.theUnsafe.compareAndSwapLong(obj, j, j2, j3);
    }

    public final boolean compareAndSetObject(Object obj, long j, Object obj2) {
        return BiConsumer$CC.m(this.theUnsafe, obj, j, obj2);
    }
}
