package ch.zhaw.ads.prakt3;

import ch.zhaw.ads.CommandExecutor;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class RankingServer implements CommandExecutor {

    private List<Competitor> competitorList = new ArrayList<>();

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Override
    public String execute(String fileContent) throws Exception {
        StringBuilder sb = new StringBuilder();

        competitorList.clear();
        parseFile(fileContent);

        Collections.sort(competitorList);
        fillRank();

        sb.append("==========Rangliste==========");
        sb.append(LINE_SEPARATOR);
        printCompetitorSorted(sb);

        sb.append(LINE_SEPARATOR);
        Collections.sort(competitorList, new CompetitorComparator());
        sb.append("==========Namensliste==========");
        sb.append(LINE_SEPARATOR);
        printCompetitorSorted(sb);

        return sb.toString();
    }

    private void fillRank() {
        for (int i = 0; i < competitorList.size(); i++) {
            competitorList.get(i).setRank(i + 1);
        }
    }

    private void printCompetitorSorted(StringBuilder sb) {
        for (int i = 0; i < competitorList.size(); i++) {
            Competitor competitor = competitorList.get(i);
            sb.append(competitor.toString());
            sb.append(LINE_SEPARATOR);
        }
    }

    private void parseFile(String fileContent) throws ParseException {
        Scanner scanner = new Scanner(fileContent);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            competitorList.add(CompetitorParser.parseCompetitor(line));
        }
        scanner.close();
    }

}
