package ch.zhaw.ads.prakt9;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyHashtable<K, V> implements java.util.Map<K, V> {
    private final K DELETED = (K) new Object();

    private int maxSize;

    private K[] keys;

    private V[] values;

    private int size;

    public MyHashtable(int maxSize) {
        this.maxSize = maxSize;
        clear();
    }

    private int hash(Object k) {
        int h = Math.abs(k.hashCode());
        return h % keys.length;
    }

    private void resize() {
        K[] oldKeys = keys;
        V[] oldValues = values;
        maxSize *= 2;
        clear();
        for (int i = 0; i < oldKeys.length; i++) {
            if (oldKeys[i] != null && oldKeys[i] != DELETED) {
                this.put(oldKeys[i], oldValues[i]);
            }
        }
    }

    public void clear() {
        keys = (K[]) new Object[maxSize];
        values = (V[]) new Object[maxSize];
        size = 0;
    }

    private int findPosition(K key) {
        int collisionNum = 0;
        int currentPos = hash(key);
        while (keys[currentPos] != null && !keys[currentPos].equals(key) && collisionNum < maxSize) {
            currentPos += 2 * collisionNum++;
            currentPos %= keys.length;
        }

        return collisionNum == maxSize ? -1 : currentPos;
    }

    public V put(K key, V value) {
        int keyIndex = findPosition(key);
        if (keyIndex < 0) {
            // Wenn alle Plätze durch "gelöschte Einträge" besetzt sind, resize und alles wieder freigeben
            resize();
            return put(key, value);
        }
        if (keys[keyIndex] == null) {
            if (size > 0.8 * maxSize) {
                resize();
            }

            size++;
            keys[keyIndex] = key;
            values[keyIndex] = value;
        }

        return value;
    }

    public V get(Object key) {
        int keyIndex = findPosition((K) key);
        return keyIndex < 0 ? null : values[keyIndex];
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public V remove(Object key) {
        int keyIndex = findPosition((K) key);
        V previousValue = null;
        if (keyIndex >= 0 && keys[keyIndex] != null) {
            previousValue = values[keyIndex];
            size--;
            keys[keyIndex] = DELETED;
            values[keyIndex] = null;
        }

        return previousValue;
    }

    //  Returns the number of key-value mappings in this map. 
    public int size() {
        return this.size;
    }

    // =======================================================================
    //  Returns a set view of the keys contained in this map. 
    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    //  Copies all of the mappings from the specified map to this map (optional operation). 
    public void putAll(Map t) {
        throw new UnsupportedOperationException();
    }

    //  Returns a collection view of the values contained in this map. 
    public Collection values() {
        throw new UnsupportedOperationException();
    }

    //  Returns true if this map contains a mapping for the specified key.
    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException();
    }

    //  Returns true if this map maps one or more keys to the specified value.
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
    }


    //  Returns a set view of the mappings contained in this map.
    public Set entrySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

}