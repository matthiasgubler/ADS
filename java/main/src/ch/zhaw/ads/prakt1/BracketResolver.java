package ch.zhaw.ads.prakt1;

import java.util.Arrays;
import java.util.stream.Stream;

public class BracketResolver {

    private static String[] OPENING_BRACKETS_ARRAY = {"<*", "(", "[", "{", "<"};
    private static String[] CLOSING_BRACKETS_ARRAY = {"*>", ")", "]", "}", ">"};
    private static String[] BRACKETS = Stream.concat(Arrays.stream(OPENING_BRACKETS_ARRAY), Arrays.stream(CLOSING_BRACKETS_ARRAY)).toArray(String[]::new);

    private String input;

    private int index = 0;

    public BracketResolver(String input) {
        this.input = input;
    }

    public static boolean isOpeningBracket(String possibleBracket) {
        return Arrays.stream(OPENING_BRACKETS_ARRAY).anyMatch(bracket -> bracket.compareTo(possibleBracket) == 0);
    }

    public static boolean isCorrespondingBracket(String openingBracket, String closingBracket) {
        int bracketIndex = 0;
        while (OPENING_BRACKETS_ARRAY[bracketIndex].compareTo(openingBracket) != 0) {
            bracketIndex++;
        }

        return CLOSING_BRACKETS_ARRAY[bracketIndex].compareTo(closingBracket) == 0;
    }

    public String nextBracket() {
        while (!everythingRead()) {
            for (String bracket : BRACKETS) {
                if (input.indexOf(bracket) == 0) {
                    input = input.substring(bracket.length());
                    return bracket;
                }
            }

            input = input.substring(1);
        }
        return null;
    }

    public boolean everythingRead() {
        return index == input.length();
    }

}
