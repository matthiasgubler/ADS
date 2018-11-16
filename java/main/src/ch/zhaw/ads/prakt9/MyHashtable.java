package ch.zhaw.ads.prakt9;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyHashtable<K, V> implements java.util.Map<K, V> {
    private int maxSize;
    private int resizeFactor = 1;

    private K[] keys;

    private V[] values;

    private int size = 0;

    public MyHashtable(int maxSize) {
        this.maxSize = maxSize;
        clear();
    }

    private int hash(Object k) {
        int h = Math.abs(k.hashCode());
        return h % keys.length;
    }

    private void resize() {
        K[] oldKeys = Arrays.copyOf(keys, maxSize);
        V[] oldValues = Arrays.copyOf(values, maxSize);

        maxSize = maxSize * 2;

        clear();
        for (int i = 0; i < oldKeys.length; i++) {
            K currentKey = oldKeys[i];
            V currentValue = oldValues[i];
            this.put(currentKey, currentValue);
        }

        resizeFactor++;
    }

    //  Removes all mappings from this map (optional operation). 
    public void clear() {
        keys = (K[]) new Object[maxSize];
        values = (V[]) new Object[maxSize];
    }

    //  Associates the specified value with the specified key in this map (optional operation).
    public V put(K key, V value) {
        int keyIndex = findPosition(key);
        if (keys[keyIndex] == null) {
            size++;
            keys[keyIndex] = key;
            values[keyIndex] = value;
            if (size == maxSize) {
                resize();
            }
        }
        return value;
    }

    private int findPosition(K key) {
        int collisionNum = 0;
        int currentPos = hash(key);
        while (keys[currentPos] != null &&
                !keys[currentPos].equals(key)) {
            currentPos += 2 * ++collisionNum - 1;
            currentPos = currentPos % keys.length;
        }
        if (keys[currentPos] != null &&
                keys[currentPos].equals(key)) {
        }
        return currentPos;
    }

    //  Returns the value to which this map maps the specified key. 
    public V get(Object key) {
        int keyIndex = hash(key);
        return values[keyIndex];
    }

    //  Returns true if this map contains no key-value mappings. 
    public boolean isEmpty() {
        return size() == 0;
    }

    public V remove(Object key) {
        int keyIndex = findPosition((K) key);
        V previousValue = values[keyIndex];
        if (keys[keyIndex] != null) {
            //System.out.println("Deleting at index="+keyIndex);

            size--;
            keys[keyIndex] = null;
            values[keyIndex] = null;
        } else {
            //Does not exist in Map
            //System.out.println("Nothing to delete to index="+keyIndex);
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
        return Arrays.asList(values);
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyHashtable<?, ?> that = (MyHashtable<?, ?>) o;
        return Arrays.equals(keys, that.keys) &&
                Arrays.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(keys);
        result = 31 * result + Arrays.hashCode(values);
        return result;
    }

}