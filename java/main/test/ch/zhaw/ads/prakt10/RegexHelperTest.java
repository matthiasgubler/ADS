package ch.zhaw.ads.prakt10;

import org.junit.Test;

import static ch.zhaw.ads.prakt10.RegexHelper.EMAIL_REGEX;
import static ch.zhaw.ads.prakt10.RegexHelper.IP_REGEX;
import static org.junit.Assert.assertEquals;

public class RegexHelperTest {

    @Test
    public void testEmailMatcher() throws Exception {
        assertEquals(true, RegexHelper.matches("hans.muster@zhaw.ch", EMAIL_REGEX));
        assertEquals(true, RegexHelper.matches("rege@zhaw.ch", EMAIL_REGEX));
        assertEquals(true, RegexHelper.matches("gublema6@students.zhaw.ch", EMAIL_REGEX));
        assertEquals(false, RegexHelper.matches("mail.ch", EMAIL_REGEX));
        assertEquals(false, RegexHelper.matches("", EMAIL_REGEX));
        assertEquals(false, RegexHelper.matches("mail@my@address.ch", EMAIL_REGEX));
        assertEquals(false, RegexHelper.matches("127.0.0.1", EMAIL_REGEX));
    }

    @Test
    public void testIPMatcher() throws Exception {
        assertEquals(true, RegexHelper.matches("0.0.0.0", IP_REGEX));
        assertEquals(true, RegexHelper.matches("127.0.0.1", IP_REGEX));
        assertEquals(true, RegexHelper.matches("255.255.255.255", IP_REGEX));
        assertEquals(true, RegexHelper.matches("8.8.8.8", IP_REGEX));
        assertEquals(true, RegexHelper.matches("192.168.0.1", IP_REGEX));
        assertEquals(false, RegexHelper.matches("277.0.0.1", IP_REGEX));
        assertEquals(false, RegexHelper.matches("233.288.0.1", IP_REGEX));
        assertEquals(false, RegexHelper.matches("233.0.999.1", IP_REGEX));
        assertEquals(false, RegexHelper.matches("233.0.9.999", IP_REGEX));
        assertEquals(false, RegexHelper.matches(".0.9.999", IP_REGEX));
        assertEquals(false, RegexHelper.matches("hans.muster@zhaw.ch", IP_REGEX));
    }

}