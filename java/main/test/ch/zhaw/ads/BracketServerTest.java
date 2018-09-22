package ch.zhaw.ads;

import ch.zhaw.ads.prakt1.BracketServer;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class BracketServerTest {

    BracketServer bs;

    @Before
    public void setUp() throws Exception {
        bs = new BracketServer();
    }

    private void test(boolean expected, String s) {
        assertEquals(s, expected, bs.checkBrackets(s));
    }

    @Test
    public void testBracket() {
        test(true, "()");
        test(false, "(()]");
        test(true, "((([([])])))");
        test(false, "[(])");
        test(true, "[(3 +3)* 35 +3]* {3 +2}");
        test(false, "[({3 +3)* 35} +3]* {3 +2}");
        test(false, "(");
        test(false, ")");
        test(true, "<(<>)>");
        test(false, "<(<)>>");
        //test(true, "<*(<*<>*>)*>");
        //test(false, "<(<**>)*>");
    }

}

