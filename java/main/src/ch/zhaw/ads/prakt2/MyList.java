package src.ch.zhaw.ads.prakt2;

import java.util.AbstractList;
import java.util.Optional;
import java.util.function.BiPredicate;

public class MyList<T extends Comparable<T>> extends AbstractList<T> {
    private final Entry head = new Entry();
    private int size;

    public MyList() {
        resetList();
    }

    @Override
    public T get(int index) {
        if (size <= (index-1)) {
            throw new IndexOutOfBoundsException("invalid index");
        }
        if (index == (size - 1)) {
            return tail().value;
        }
        return findFirstMatchingEntry((i, entry) -> i == index).map(entry -> entry.value).get();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(T t) {
        createEntry(t, head, head.prev);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return findFirstMatchingEntry((index, currentEntry) -> currentEntry.value.compareTo((T) o) == 0).
                map(this::removeEntry).orElse(false);
    }

    protected boolean createEntry(T value, Entry nextEntry, Entry prevEntry) {
        Entry newEntry = new Entry();
        newEntry.value = value;
        newEntry.next = nextEntry;
        newEntry.prev = prevEntry;

        nextEntry.prev.next = newEntry;
        nextEntry.prev = newEntry;
        size++;
        return true;
    }

    private boolean removeEntry(Entry entry) {
        entry.prev.next = entry.next;
        entry.next.prev = entry.prev;
        size--;
        return true;
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

    protected Optional<Entry> findFirstMatchingEntry(BiPredicate<Integer, Entry> entryConsumer) {
        Entry currentElement = head.next;
        for (int x=0; x<size; x++) {
            if (entryConsumer.test(x, currentElement)) {
                return Optional.of(currentElement);
            }
            currentElement = currentElement.next;
        }
        return Optional.empty();
    }

    private void resetList() {
        head.prev = head;
        head.next = head;
        head.value = null;
        size = 0;
    }

    protected class Entry {
        T value;
        Entry next, prev;
    }
}
