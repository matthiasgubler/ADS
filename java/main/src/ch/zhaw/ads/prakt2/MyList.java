package ch.zhaw.ads.prakt2;

import java.util.AbstractList;
import java.util.Optional;
import java.util.function.BiPredicate;

public class MyList<T extends Comparable<T>> extends AbstractList<T> {
    private final Entry head = new Entry();
    private int size;

    public MyList() {
        initList();
    }

    @Override
    public T get(int index) {
        if (!indexValid(index))
            throw new IndexOutOfBoundsException("invalid index");
        if (isLastIndex(index))
            return tail().value;
        return findFirstMatchingEntry((i, entry) -> i == index).map(entry -> entry.value).orElse(null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(T t) {
        return insertElement(t, head, head.prev);
    }

    @Override
    public boolean remove(Object o) {
        return findFirstMatchingEntry((index, currentEntry) -> currentEntry.value.compareTo((T) o) == 0).
                map(this::removeEntry).orElse(false);
    }

    protected boolean insertElement(T value, Entry nextEntry, Entry prevEntry) {
        link(createNewEntry(value, nextEntry, prevEntry), nextEntry);
        size++;
        return true;
    }

    private void link(Entry newEntry, Entry nextEntry) {
        nextEntry.prev.next = newEntry;
        nextEntry.prev = newEntry;
    }

    private Entry createNewEntry(T value, Entry nextEntry, Entry prevEntry) {
        Entry newEntry = new Entry();
        newEntry.value = value;
        newEntry.next = nextEntry;
        newEntry.prev = prevEntry;
        return newEntry;
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
        initList();
    }

    protected Optional<Entry> findFirstMatchingEntry(BiPredicate<Integer, Entry> entryConsumer) {
        Entry currentElement = head.next;
        for (int x=0; x<size; x++) {
            if (entryConsumer.test(x, currentElement))
                return Optional.of(currentElement);
            currentElement = currentElement.next;
        }
        return Optional.empty();
    }

    private void initList() {
        head.prev = head;
        head.next = head;
        head.value = null;
        size = 0;
    }

    private boolean isLastIndex(int index) {
        return index == (size - 1);
    }

    private boolean indexValid(int index) {
        return (index+1) <= size;
    }

    protected class Entry {
        T value;
        Entry next, prev;
    }
}
