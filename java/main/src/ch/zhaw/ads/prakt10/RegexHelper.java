package ch.zhaw.ads.prakt10;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexHelper {

    public static final String EMAIL_REGEX = "^([A-Za-z0-9]|[!#$%&'*+-/=?^_`{|}~'])+@{1}([A-Za-z0-9]|[-.])+\\.[A-Za-z]+$";

    public static final String IP_REGEX = "^(((2[0-5][0-5])|([1]?[0-9]?[0-9]))\\.){3}((2[0-5][0-5])|([1]?[0-9]?[0-9]))$";

    public static boolean matches(String value, String regexPattern) {
        Pattern currentPattern = Pattern.compile(regexPattern);
        Matcher matcher = currentPattern.matcher(value);
        return matcher.matches();
    }

}
