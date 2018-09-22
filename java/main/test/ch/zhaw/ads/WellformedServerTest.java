package ch.zhaw.ads;

import ch.zhaw.ads.prakt1.WellformedServer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WellformedServerTest {

    WellformedServer ws;

    @Before
    public void setUp() throws Exception {
        ws = new WellformedServer();
    }

    private void test(boolean expected, String s) {
        assertEquals(s, expected, ws.checkWellformed(s));
    }

    @Test
    public void testWellformedSimple() {
        test(true, "<tag></tag><asdfasdf></asdfasdf>");
        test(true, "<tag /><asdfasdf></asdfasdf>");
        test(true, "<tag attribute='sdvsd' /><asdfasdf id=\"Freddy\"></asdfasdf>");
        test(true, "");
        test(true, "<tag />");
        test(true, "Lalala><");
        test(false, "<tag><asdfasdf></asdfasdf>");
        test(false, "<tag>");
        test(false, "</ tag>");
    }


}

