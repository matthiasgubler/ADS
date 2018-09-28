package src.ch.zhaw.ads.prakt2;

public class MySortedList<T extends Comparable<T>> extends MyList<T> {
    @Override
    public boolean add(T t) {
        if (t == null) {
            throw new IllegalArgumentException("parameter t must not be null");
        }
        if (size() > 0 && t.compareTo(tail().value) >=0) {
            return super.add(t);
        }
        Entry currentEntry = head().next;
        for (int x = 0; x<size(); x++) {
            if (t.compareTo(currentEntry.value) <= 0) {
                createEntry(t, currentEntry, currentEntry.prev);
                return true;
            }
            currentEntry = currentEntry.next;
        }
        return super.add(t);
    }
}
