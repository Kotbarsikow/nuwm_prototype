package org.mortbay.util;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/* loaded from: classes3.dex */
public class SingletonList extends AbstractList {
    private Object o;

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return 1;
    }

    private SingletonList(Object obj) {
        this.o = obj;
    }

    public static SingletonList newSingletonList(Object obj) {
        return new SingletonList(obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public Object get(int i) {
        if (i != 0) {
            throw new IndexOutOfBoundsException(new StringBuffer("index ").append(i).toString());
        }
        return this.o;
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator listIterator() {
        return new SIterator();
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator listIterator(int i) {
        return new SIterator(i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator iterator() {
        return new SIterator();
    }

    private class SIterator implements ListIterator {
        int i;

        SIterator() {
            this.i = 0;
        }

        SIterator(int i) {
            if (i < 0 || i > 1) {
                throw new IndexOutOfBoundsException(new StringBuffer("index ").append(i).toString());
            }
            this.i = i;
        }

        @Override // java.util.ListIterator
        public void add(Object obj) {
            throw new UnsupportedOperationException("SingletonList.add()");
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.i == 0;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.i == 1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public Object next() {
            int i = this.i;
            if (i != 0) {
                throw new NoSuchElementException();
            }
            this.i = i + 1;
            return SingletonList.this.o;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.i;
        }

        @Override // java.util.ListIterator
        public Object previous() {
            int i = this.i;
            if (i != 1) {
                throw new NoSuchElementException();
            }
            this.i = i - 1;
            return SingletonList.this.o;
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.i - 1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("SingletonList.remove()");
        }

        @Override // java.util.ListIterator
        public void set(Object obj) {
            throw new UnsupportedOperationException("SingletonList.add()");
        }
    }
}
