package j$.util.stream;

/* loaded from: classes4.dex */
abstract class AbstractSpinedBuffer {
    protected int elementIndex;
    protected final int initialChunkPower;
    protected long[] priorElementCount;
    protected int spineIndex;

    public abstract void clear();

    protected AbstractSpinedBuffer() {
        this.initialChunkPower = 4;
    }

    protected AbstractSpinedBuffer(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + i);
        }
        this.initialChunkPower = Math.max(4, 32 - Integer.numberOfLeadingZeros(i - 1));
    }

    public final long count() {
        int i = this.spineIndex;
        if (i == 0) {
            return this.elementIndex;
        }
        return this.priorElementCount[i] + this.elementIndex;
    }
}
