package com.github.mikephil.charting.data;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public abstract class DataSet<T extends Entry> extends BaseDataSet<T> {
    protected List<T> mValues;
    protected float mXMax;
    protected float mXMin;
    protected float mYMax;
    protected float mYMin;

    public enum Rounding {
        UP,
        DOWN,
        CLOSEST
    }

    public abstract DataSet<T> copy();

    public DataSet(List<T> list, String str) {
        super(str);
        this.mYMax = -3.4028235E38f;
        this.mYMin = Float.MAX_VALUE;
        this.mXMax = -3.4028235E38f;
        this.mXMin = Float.MAX_VALUE;
        this.mValues = list;
        if (list == null) {
            this.mValues = new ArrayList();
        }
        calcMinMax();
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public void calcMinMax() {
        List<T> list = this.mValues;
        if (list == null || list.isEmpty()) {
            return;
        }
        this.mYMax = -3.4028235E38f;
        this.mYMin = Float.MAX_VALUE;
        this.mXMax = -3.4028235E38f;
        this.mXMin = Float.MAX_VALUE;
        Iterator<T> it = this.mValues.iterator();
        while (it.hasNext()) {
            calcMinMax(it.next());
        }
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public void calcMinMaxY(float f, float f2) {
        List<T> list = this.mValues;
        if (list == null || list.isEmpty()) {
            return;
        }
        this.mYMax = -3.4028235E38f;
        this.mYMin = Float.MAX_VALUE;
        int entryIndex = getEntryIndex(f2, Float.NaN, Rounding.UP);
        for (int entryIndex2 = getEntryIndex(f, Float.NaN, Rounding.DOWN); entryIndex2 <= entryIndex; entryIndex2++) {
            calcMinMaxY(this.mValues.get(entryIndex2));
        }
    }

    protected void calcMinMax(T t) {
        if (t == null) {
            return;
        }
        calcMinMaxX(t);
        calcMinMaxY(t);
    }

    protected void calcMinMaxX(T t) {
        if (t.getX() < this.mXMin) {
            this.mXMin = t.getX();
        }
        if (t.getX() > this.mXMax) {
            this.mXMax = t.getX();
        }
    }

    protected void calcMinMaxY(T t) {
        if (t.getY() < this.mYMin) {
            this.mYMin = t.getY();
        }
        if (t.getY() > this.mYMax) {
            this.mYMax = t.getY();
        }
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public int getEntryCount() {
        return this.mValues.size();
    }

    public List<T> getValues() {
        return this.mValues;
    }

    public void setValues(List<T> list) {
        this.mValues = list;
        notifyDataSetChanged();
    }

    protected void copy(DataSet dataSet) {
        super.copy((BaseDataSet) dataSet);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(toSimpleString());
        for (int i = 0; i < this.mValues.size(); i++) {
            stringBuffer.append(this.mValues.get(i).toString() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        }
        return stringBuffer.toString();
    }

    public String toSimpleString() {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder sb = new StringBuilder("DataSet, label: ");
        sb.append(getLabel() == null ? "" : getLabel());
        sb.append(", entries: ");
        sb.append(this.mValues.size());
        sb.append("\n");
        stringBuffer.append(sb.toString());
        return stringBuffer.toString();
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public float getYMin() {
        return this.mYMin;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public float getYMax() {
        return this.mYMax;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public float getXMin() {
        return this.mXMin;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public float getXMax() {
        return this.mXMax;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public void addEntryOrdered(T t) {
        if (t == null) {
            return;
        }
        if (this.mValues == null) {
            this.mValues = new ArrayList();
        }
        calcMinMax(t);
        if (this.mValues.size() > 0) {
            if (this.mValues.get(r0.size() - 1).getX() > t.getX()) {
                this.mValues.add(getEntryIndex(t.getX(), t.getY(), Rounding.UP), t);
                return;
            }
        }
        this.mValues.add(t);
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public void clear() {
        this.mValues.clear();
        notifyDataSetChanged();
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public boolean addEntry(T t) {
        if (t == null) {
            return false;
        }
        List<T> values = getValues();
        if (values == null) {
            values = new ArrayList<>();
        }
        calcMinMax(t);
        return values.add(t);
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public boolean removeEntry(T t) {
        List<T> list;
        if (t == null || (list = this.mValues) == null) {
            return false;
        }
        boolean remove = list.remove(t);
        if (remove) {
            calcMinMax();
        }
        return remove;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public int getEntryIndex(Entry entry) {
        return this.mValues.indexOf(entry);
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public T getEntryForXValue(float f, float f2, Rounding rounding) {
        int entryIndex = getEntryIndex(f, f2, rounding);
        if (entryIndex > -1) {
            return this.mValues.get(entryIndex);
        }
        return null;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public T getEntryForXValue(float f, float f2) {
        return getEntryForXValue(f, f2, Rounding.CLOSEST);
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public T getEntryForIndex(int i) {
        return this.mValues.get(i);
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public int getEntryIndex(float f, float f2, Rounding rounding) {
        int i;
        T t;
        List<T> list = this.mValues;
        if (list == null || list.isEmpty()) {
            return -1;
        }
        int size = this.mValues.size() - 1;
        int i2 = 0;
        while (i2 < size) {
            int i3 = (i2 + size) / 2;
            float x = this.mValues.get(i3).getX() - f;
            int i4 = i3 + 1;
            float x2 = this.mValues.get(i4).getX() - f;
            float abs = Math.abs(x);
            float abs2 = Math.abs(x2);
            if (abs2 >= abs) {
                if (abs >= abs2) {
                    double d = x;
                    if (d < Utils.DOUBLE_EPSILON) {
                        if (d < Utils.DOUBLE_EPSILON) {
                        }
                    }
                }
                size = i3;
            }
            i2 = i4;
        }
        if (size == -1) {
            return size;
        }
        float x3 = this.mValues.get(size).getX();
        if (rounding == Rounding.UP) {
            if (x3 < f && size < this.mValues.size() - 1) {
                size++;
            }
        } else if (rounding == Rounding.DOWN && x3 > f && size > 0) {
            size--;
        }
        if (Float.isNaN(f2)) {
            return size;
        }
        while (size > 0 && this.mValues.get(size - 1).getX() == x3) {
            size--;
        }
        float y = this.mValues.get(size).getY();
        loop2: while (true) {
            i = size;
            do {
                size++;
                if (size >= this.mValues.size()) {
                    break loop2;
                }
                t = this.mValues.get(size);
                if (t.getX() != x3) {
                    break loop2;
                }
            } while (Math.abs(t.getY() - f2) >= Math.abs(y - f2));
            y = f2;
        }
        return i;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public List<T> getEntriesForXValue(float f) {
        ArrayList arrayList = new ArrayList();
        int size = this.mValues.size() - 1;
        int i = 0;
        while (true) {
            if (i > size) {
                break;
            }
            int i2 = (size + i) / 2;
            T t = this.mValues.get(i2);
            if (f == t.getX()) {
                while (i2 > 0 && this.mValues.get(i2 - 1).getX() == f) {
                    i2--;
                }
                int size2 = this.mValues.size();
                while (i2 < size2) {
                    T t2 = this.mValues.get(i2);
                    if (t2.getX() != f) {
                        break;
                    }
                    arrayList.add(t2);
                    i2++;
                }
            } else if (f > t.getX()) {
                i = i2 + 1;
            } else {
                size = i2 - 1;
            }
        }
        return arrayList;
    }
}
