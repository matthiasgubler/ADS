package ch.zhaw.ads.prakt10;

import ch.zhaw.ads.CommandExecutor;
import ch.zhaw.ads.prakt3.CompetitorParser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FuzzySearchServer implements CommandExecutor {

    private TrigramIndex trigramIndex;

    public FuzzySearchServer() {
        trigramIndex = new TrigramIndex();
    }

    @Override
    public String execute(String s) throws Exception {
        if (s.indexOf("FIND ") >= 0) {
            //Search Operation
            return "Possible Value found: " + trigramIndex.fuzzySearch(s.substring(4)) + " \n";
        } else {
            //Initialize Index Operation
            trigramIndex.initialize(parseFile(s));
            return "Index Initialized\n";
        }
    }


    private List<String> parseFile(String fileContent) throws ParseException {
        List<String> competitorNames = new ArrayList();

        Scanner scanner = new Scanner(fileContent);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            competitorNames.add(CompetitorParser.parseCompetitor(line).getName());
        }
        scanner.close();
        return competitorNames;
    }

}