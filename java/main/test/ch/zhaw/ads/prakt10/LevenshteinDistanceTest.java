package ch.zhaw.ads.prakt10;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LevenshteinDistanceTest {

    @Test
    public void testDistance() throws Exception {
        assertEquals(2, LevenshteinDistance.computeLevenshteinDistance("AUSTAUSCH", "AUFBAUSCH"));
        assertEquals(2, LevenshteinDistance.computeLevenshteinDistance("BARBAREN", "BARBARA"));
        assertEquals(3, LevenshteinDistance.computeLevenshteinDistance("COCACOLA", "COCAINA"));
    }

}