package ch.zhaw.ads;

import ch.zhaw.ads.prakt1.KgvServer;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
public class KgvServerTest {
	
	KgvServer server;
	
	@Before
	public void setUp() throws Exception {
		server = new KgvServer();
	}
    
	@Test
	public void testKgv() {
		assertEquals(12, server.kgv(3,4));
		assertEquals(4, server.kgv(2,4));
		assertEquals(35, server.kgv(5,7));
		assertEquals(12, server.kgv(4,6));
	}	
}
