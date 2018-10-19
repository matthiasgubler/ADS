package ch.zhaw.ads.prakt5;

import ch.zhaw.ads.prakt3.Competitor;

public class SetRankingVisitor implements Visitor<Competitor> {

    int rank = 1;

    public SetRankingVisitor() {

    }

    @Override
    public void visit(Competitor competitor) {
        competitor.setRank(rank++);
    }

}
