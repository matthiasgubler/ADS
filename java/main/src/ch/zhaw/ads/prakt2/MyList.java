package src.ch.zhaw.ads.prakt2;

import java.util.AbstractList;

public class MyList<T extends Comparable<T>> extends AbstractList<T> {

    private final Entry head = new Entry();
    private int size;

    public MyList() {
        resetList();
    }

    private void resetList() {
        head.prev = head;
        head.next = head;
        head.value = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        if (size <= (index-1)) {
            throw new IndexOutOfBoundsException("invalid index");
        }
        Entry element = head.next;
        for (int x=0; x<index; x++) {
            element = element.next;
        }
        return element.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(T t) {
        if (t == null) {
            throw new IllegalArgumentException("parameter t must not be null");
        }
        createEntry(t, head, head.prev);
        return true;
    }

    protected Entry createEntry(T value, Entry nextEntry, Entry prevEntry) {
        Entry newEntry = new Entry();
        newEntry.value = value;
        newEntry.next = nextEntry;
        newEntry.prev = prevEntry;

        nextEntry.prev.next = newEntry;
        nextEntry.prev = newEntry;
        size++;
        return newEntry;
    }

    private boolean removeEntry(Entry entry) {
        entry.prev.next = entry.next;
        entry.next.prev = entry.prev;
        size--;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Entry currentEntry = head.next;
        for (int x=0; x<size; x++) {
            if (currentEntry.value.compareTo((T) o) == 0) {
                return removeEntry(currentEntry);
            }
            currentEntry = currentEntry.next;
        }
        return false;
    }

    public Entry head() {
        return head;
    }

    public Entry tail() {
        return head.prev;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        resetList();
    }

    protected class Entry {
        T value;
        Entry next, prev;
    }
}
