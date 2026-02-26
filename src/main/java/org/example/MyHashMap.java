package org.example;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MyHashMap<K, V> implements Map<K, V> {
    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Entry[] table = new Entry[16];
    private int size = 0;

    private int getIndex(K key) {
        int hash = key.hashCode();
        return (table.length - 1) & hash;
    }


    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public V put(K key, V value) {
        int index = getIndex(key);
        Entry current = table[index];

        while (current != null) {
            if (key.equals(current.key)) {
                V oldValue = (V) current.value;
                current.value = value;
                return oldValue;
            }
            current = current.next;
        }

        table[index] = new Entry(key, value, table[index]);
        size++;
        return null;
    }


    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public V remove(Object key) {
        int index = getIndex((K) key);
        Entry current = table[index];
        Entry prev = null;

        while (current != null) {
            if (key.equals(current.key)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return (V) current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        if (key == null) return null;
        int index = getIndex((K) key);
        Entry current = table[index];

        while (current != null) {
            if (Objects.equals(key, current.key)) {
                return (V) current.value;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public String toString() {
        if (size == 0) return "{}";

        StringBuilder sb = new StringBuilder("{");
        boolean first = true;

        for (Entry e : table) {
            for (Entry current = e; current != null; current = current.next) {
                if (!first) sb.append(", ");
                sb.append(current.key).append("=").append(current.value);
                first = false;
            }
        }
        return sb.append("}").toString();
    }
}
