package ch.zhaw.ads.prakt3;

import java.util.Comparator;

public class CompetitorComparator implements Comparator<Competitor> {
    @Override
    public int compare(Competitor competitor1, Competitor competitor2) {
        int nameCompareToResult = competitor1.getName().compareTo(competitor2.getName());

        if (nameCompareToResult == 0) {
            if (competitor1.getJg() == competitor2.getJg()) {
                return 0;
            } else if (competitor1.getJg() < competitor2.getJg()) {
                return -1;
            }
            return 1;
        }
        return nameCompareToResult;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
