package ch.zhaw.ads.prakt3;

import java.text.ParseException;

public class CompetitorParser {

    public static Competitor parseCompetitor(String competitorString) throws ParseException {
        if (competitorString == null) {
            throw new ParseException("competitorString is null", 0);
        }

        String[] splittedFields = competitorString.split(";");
        if (splittedFields.length != 5) {
            throw new ParseException("Not enough CSV Fields", 0);
        }

        int startNr = parseFieldInteger(splittedFields, 0);
        String name = parseField(splittedFields, 1);
        int jahrgang = parseFieldInteger(splittedFields, 2);
        String country = parseField(splittedFields, 3);
        String time = parseField(splittedFields, 4);

        return new Competitor(startNr, name, jahrgang, country, time);
    }

    private static String parseField(String[] parseString, int index) throws ParseException {
        String fieldContent = parseString[index];
        if (fieldContent.length() == 0) {
            throw new ParseException("Field is empty", index);
        }
        return fieldContent;
    }

    private static int parseFieldInteger(String[] parseString, int index) throws ParseException {
        try {
            return Integer.valueOf(parseField(parseString, index));
        } catch (NumberFormatException nfe) {
            throw new ParseException("Field is not a number", index);
        }
    }

}
