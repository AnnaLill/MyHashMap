package org.example;

public class MyHashMap<K, V> {
    private static class Entry<K, V> {
        K key;
        V value;
        Entry next;

        public Entry(K key, V value, Entry next) {
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

    public void put(K key, V value) {
        int index = getIndex(key);
        Entry current = table[index];

        while (current != null) {
            if (key.equals(current.key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        table[index] = new Entry(key, value, table[index]);
        size++;
    }

    public V get(K key) {
        int index = getIndex(key);
        Entry current = table[index];

        while (current != null) {
            if (key.equals(current.key)) {
                return (V) current.value;
            }
            current = current.next;
        }
        return null;
    }

    public void remove(K key) {
        int index = getIndex(key);
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
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public int size() {
        return size;
    }


    @Override
    public String toString() {
        if (table == null) return "{}";

        StringBuilder sb = new StringBuilder("{");
        boolean first = true;

        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                if (!first) {
                    sb.append(", ");
                }
                sb.append(table[i].key).append("=").append(table[i].value);
                first = false;
            }
        }

        sb.append("}");
        return sb.toString();
    }


}
