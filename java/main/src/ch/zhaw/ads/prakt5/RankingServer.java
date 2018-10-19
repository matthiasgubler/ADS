package ch.zhaw.ads.prakt5;

import ch.zhaw.ads.CommandExecutor;
import ch.zhaw.ads.prakt3.Competitor;
import ch.zhaw.ads.prakt3.CompetitorParser;

import java.text.ParseException;
import java.util.Scanner;

public class RankingServer implements CommandExecutor {

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private Tree<Competitor> competitorTree;

    private PrepareRankingVisitor prepareRankingVisitor = new PrepareRankingVisitor();

    @Override
    public String execute(String fileContent) throws Exception {
        prepareRankingVisitor.clear();

        competitorTree = new SortedBinaryTree<>();
        parseFile(fileContent);
        fillRank();
        printCompetitorSorted();

        return prepareRankingVisitor.toString();
    }

    private void fillRank() {
        competitorTree.traversal().inorder(new SetRankingVisitor());
    }

    private void printCompetitorSorted() {
        competitorTree.traversal().inorder(prepareRankingVisitor);
    }

    private void parseFile(String fileContent) throws ParseException {
        Scanner scanner = new Scanner(fileContent);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            competitorTree.add(CompetitorParser.parseCompetitor(line));
        }
        scanner.close();
    }

}
