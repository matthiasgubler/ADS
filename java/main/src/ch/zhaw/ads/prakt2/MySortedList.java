package ch.zhaw.ads.prakt2;

public class MySortedList<T extends Comparable<T>> extends MyList<T> {

    @Override
    public boolean add(T t) {
        if (isBiggerThanLastElement(t))
            return super.add(t);
        return findFirstMatchingEntry((index, currentElement) -> t.compareTo(currentElement.value) <= 0).
                map(entry -> insertElement(t, entry, entry.prev)).orElseGet(() -> super.add(t));
    }

    private boolean isBiggerThanLastElement(T t) {
        return size() > 0 && t.compareTo(tail().value) >=0;
    }
}
