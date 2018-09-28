package src.ch.zhaw.ads.prakt2;

public class MySortedList<T extends Comparable<T>> extends MyList<T> {
    @Override
    public boolean add(T t) {
        if (size() > 0 && t.compareTo(tail().value) >=0) {
            return super.add(t);
        }
        return findFirstMatchingEntry((index, currentElement) -> t.compareTo(currentElement.value) <= 0).
                map(entry -> createEntry(t, entry, entry.prev)).orElseGet(() -> super.add(t));
    }
}
