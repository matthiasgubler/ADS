package ch.zhaw.ads.prakt4;

import org.junit.Test;

public class HanoiTest {

    @Test
    public void test() throws Exception{
        HanoiServer server = new HanoiServer();
        System.out.println(server.execute("3"));
    }
}
