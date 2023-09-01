package com.example.datastructure.hashtable;

import java.util.*;
import java.util.stream.Collectors;

public class SeparateChaningHashTable<K, V> implements HashTable{

    private static final float LOAD_FACTOR = 0.75f;
    private LinkedList<Entry<K, V>>[] tables;
    private int numberOfItems;

    public SeparateChaningHashTable(int capacity) {
        this.tables = new LinkedList[capacity];
        this.numberOfItems = 0;

        for (int i=0; i<capacity; i++) {
            tables[i] = new LinkedList<>();
        }
    }

    @Override
    public void put(Object key, Object value) {
        if (isResizingRequired()) {
            resize();
        }

        Entry<K, V> objectObjectEntry = new Entry<>((K)key, (V)value);
        tables[hashCode(key)].add(objectObjectEntry);
        numberOfItems ++;
    }

    private void resize() {
        int oldLength = this.tables.length;
        LinkedList[] linkedLists = new LinkedList[oldLength * 2];

        for (int i = 0; i< linkedLists.length; i ++) {
            if (i < oldLength) {
                linkedLists[i] = this.tables[i];
            } else {
                linkedLists[i] = new LinkedList<>();
            }
        }
        this.tables = linkedLists;
    }

    private boolean isResizingRequired() {
        return ((float) numberOfItems / tables.length) > LOAD_FACTOR;
    }

    @Override
    public void remove(Object key) {
        tables[hashCode(key)].remove(key);
        numberOfItems --;
    }

    @Override
    public Object get(Object key) {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return tables[hashCode(key)].stream()
                .filter(e -> e.getKey().equals(key))
                .map(Entry::getValue).findAny()
                .orElseThrow(() -> new NoSuchElementException("해당하는 요소가 없습니다."));
    }

    @Override
    public boolean isEmpty() {
        return numberOfItems==0;
    }

    @Override
    public Collection keys() {
        Collection<K> keys = new ArrayList<>();
        for (LinkedList<Entry<K, V>> table : tables) {
            List<K> collect = table.stream().map(Entry::getKey).toList();
            keys.addAll(collect);
        }
        return keys;
    }

    @Override
    public Collection values() {
        Collection<V> values = new ArrayList<>();
        for (LinkedList<Entry<K, V>> table : tables) {
            List<V> collect = table.stream().map(Entry::getValue).toList();
            values.addAll(collect);
        }
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeparateChaningHashTable<?, ?> that = (SeparateChaningHashTable<?, ?>) o;
        return numberOfItems == that.numberOfItems && Arrays.equals(tables, that.tables);
    }

    public int hashCode(Object key) {
        return Math.abs(key.hashCode()) % tables.length;
    }
}
