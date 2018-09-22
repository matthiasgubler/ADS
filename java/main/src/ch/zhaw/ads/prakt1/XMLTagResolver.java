package ch.zhaw.ads.prakt1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLTagResolver {

    private static final String EXTRACTED_XML_REGEX = "<*>*/*=*\"*'*";
    private static String XML_TAG_REGEX = "<.[^(><.)/]+>";
    private static String OPENING_TAG_REGEX = "<[^/].[^(><.)/]+>";
    private static String CLOSING_TAG_REGEX = "</.[^(><.)/]+>";

    private String input;

    private Pattern xmlTagPattern = Pattern.compile(XML_TAG_REGEX);

    private Matcher xmlTagMatcher;

    public XMLTagResolver(String input) {
        this.input = input;
        xmlTagMatcher = xmlTagPattern.matcher(input);

        //Erster Wert "vorlesen"
        xmlTagMatcher.find();
    }

    public String nextToken() {
        int start = xmlTagMatcher.start();
        int end = xmlTagMatcher.end();
        String nextToken = input.substring(start, end);
        xmlTagMatcher.find();
        return nextToken;
    }

    private static String extractTagName(String tag) {
        String extractedTag = tag.replaceAll(EXTRACTED_XML_REGEX, "");
        return extractedTag.split(" ")[0];
    }

    public boolean everythingRead() {
        return xmlTagMatcher.hitEnd();
    }

    public static boolean isOpeningTag(String tag) {
        return Pattern.matches(OPENING_TAG_REGEX, tag);
    }

    public static boolean isCorrespondingTag(String openingTag, String closingTag) {
        if (openingTag == null || closingTag == null) {
            return false;
        }
        String openingTagName = extractTagName(openingTag);
        String closingTagName = extractTagName(closingTag);
        return openingTagName.compareTo(closingTagName) == 0;
    }

}
