package com.example.datastructure.hashtable;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

public class LinearProbingHashTable<K, V> implements HashTable {

    private static final float LOAD_FACTOR = 0.6f;
    private Entry<K,V>[] tables;
    private int numberOfItems;

    public LinearProbingHashTable(int capacity) {
        this.tables = new Entry<>[capacity];
        this.numberOfItems = 0;
    }

    @Override
    public void put(Object key, Object value) {
        if (isResizingRequired()) {
            resize();
        }

        Entry<K, V> objectEntry = new Entry<>((K)key, (V)value);
        int index = hashCode((K) key);
        boolean work = false;

        for (int i=0; i<tables.length; i++) {
            if (tables[index] == null) {
                tables[hashCode((K) key)] = objectEntry;
                work = true;
                break;
            } else {
                index = (index + 1) % tables.length;
            }
        }
        if (!work) {
            throw new RuntimeException();
        }
        numberOfItems ++;
    }

    private void resize() {
        int oldLength = this.tables.length;
        Entry<K,V>[] newList = new Entry<>[oldLength * 2];

        for (int i = 0; i< tables.length; i ++) {
            if (i < oldLength) {
                newList[hashCode(tables[i].getKey())] = this.tables[i];
            }
        }
        this.tables = newList;
    }

    private boolean isResizingRequired() {
        return ((float) numberOfItems / tables.length) > LOAD_FACTOR;
    }

    @Override
    public void remove(Object key) {
        int index = hashCode((K) key);

        for (int i=0; i<tables.length; i++) {
            if (tables[index].getKey().equals(key)) {
                tables[index] = null;
                numberOfItems --;
            }
            index = (index+1)% tables.length;
        }
    }

    @Override
    public Object get(Object key) {
        int index = hashCode((K) key);

        for (int i=0; i<tables.length; i++) {
            if (tables[index] != null && tables[index].getKey().equals(key)) {
                return tables[index];
            }
            index = (index+1)% tables.length;
        }

        throw new NoSuchElementException();
    }

    @Override
    public boolean isEmpty() {
        return numberOfItems == 0;
    }

    @Override
    public Collection keys() {
        return Arrays.stream(tables).map(Entry::getKey).collect(Collectors.toList());
    }

    @Override
    public Collection values() {
        return Arrays.stream(tables).map(Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinearProbingHashTable<?, ?> that = (LinearProbingHashTable<?, ?>) o;
        return numberOfItems == that.numberOfItems && Arrays.equals(tables, that.tables);
    }

    public int hashCode(K key) {
        return Math.abs(key.hashCode()) % tables.length;
    }
}
