package ch.zhaw.ads.prakt5;

import ch.zhaw.ads.prakt3.Competitor;

public class PrepareRankingVisitor implements Visitor<Competitor> {

    private StringBuilder sb;

    public PrepareRankingVisitor() {
        clear();
    }

    public void clear() {
        sb = new StringBuilder();
    }

    @Override
    public void visit(Competitor competitor) {
        sb.append(competitor.toString());
        sb.append(RankingServer.LINE_SEPARATOR);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
