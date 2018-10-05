package ch.zhaw.ads.prakt3;

import ch.zhaw.ads.CommandExecutor;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class RankingServer implements CommandExecutor {

    private List<Competitor> competitorList = new ArrayList<>();

    @Override
    public String execute(String fileContent) throws Exception {
        competitorList.clear();
        parseFile(fileContent);
        return printCompetitorSorted();

    }

    private String printCompetitorSorted() {
        StringBuilder sb = new StringBuilder();

        Collections.sort(competitorList);

        for (int i = 0; i < competitorList.size(); i++) {
            Competitor competitor = competitorList.get(i);
            competitor.setRank(i + 1);
            sb.append(competitor.toString());
            sb.append(System.getProperty("line.separator"));
        }

        return sb.toString();
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
