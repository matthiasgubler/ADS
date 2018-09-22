package ch.zhaw.ads.prakt1;

public class BracketResolver {

    public static String OPENING_BRACKETS = "([{<";
    public static char[] OPENING_BRACKETS_CA = OPENING_BRACKETS.toCharArray();
    public static String CLOSING_BRACKETS = ")]}>";
    public static char[] CLOSING_BRACKETS_CA = CLOSING_BRACKETS.toCharArray();
    private static String BRACKETS = OPENING_BRACKETS + CLOSING_BRACKETS;

    private char[] input;

    private int index = 0;

    public BracketResolver(String input) {
        this.input = input.toCharArray();
    }

    public char nextBracket() {
        while (!everythingRead()) {
            if (BRACKETS.contains(input[index] + "")) {
                index++;
                return input[index - 1];
            }

            index++;
        }
        return '\0';
    }

    public boolean everythingRead() {
        return index == input.length;
    }

    public static boolean isCorrespondingBracket(char openingBracket, char closingBracket) {
        int bracketIndex = 0;
        while (OPENING_BRACKETS_CA[bracketIndex] != openingBracket) {
            bracketIndex++;
        }

        return CLOSING_BRACKETS_CA[bracketIndex] == closingBracket;
    }

}
