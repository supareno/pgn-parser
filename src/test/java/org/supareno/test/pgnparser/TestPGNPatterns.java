/*
 * TestPGNPatterns.java
 */
package org.supareno.test.pgnparser;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


import org.junit.jupiter.api.Test;
import org.supareno.pgnparser.pgn.parser.PGNParser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This test class is used to test the {@link Pattern} objects used in the {@link PGNParser} class.
 *
 * @author reno
 */
public class TestPGNPatterns {

    /**
     * List of valid single hits.
     */
    public static List<String> VALID_SINGLE_HITS = new ArrayList<String>();

    static {
        VALID_SINGLE_HITS.add("a8=Q");
        VALID_SINGLE_HITS.add("a8=Q+");
        VALID_SINGLE_HITS.add("e8=Q#");
        VALID_SINGLE_HITS.add("K1");
        VALID_SINGLE_HITS.add("Bb5");
        VALID_SINGLE_HITS.add("Bxf7+");
        VALID_SINGLE_HITS.add("Nxh3");
        VALID_SINGLE_HITS.add("O-O");
        VALID_SINGLE_HITS.add("O-O-O");
        VALID_SINGLE_HITS.add("e-p");
        VALID_SINGLE_HITS.add("Rxf7");
        VALID_SINGLE_HITS.add("e4");
        VALID_SINGLE_HITS.add("Rae8");
        VALID_SINGLE_HITS.add("R3e5");
    }

    /**
     * List of invalid single hits.
     */
    public static List<String> INVALID_SINGLE_HITS = new ArrayList<String>();

    static {
        INVALID_SINGLE_HITS.add("1");
        INVALID_SINGLE_HITS.add("e");
        INVALID_SINGLE_HITS.add("aaa");
        INVALID_SINGLE_HITS.add("1e");
        INVALID_SINGLE_HITS.add("e44");
        INVALID_SINGLE_HITS.add("a0");
        INVALID_SINGLE_HITS.add("b9");
    }

    /**
     * List of valid complex hits.
     */
    public static List<String> VALID_HITS = new ArrayList<String>();

    static {
        VALID_HITS.add("1. e4 e5");
        VALID_HITS.add("26.Rxa7 Bxd3");
        VALID_HITS.add("34.fxg6 hxg6");
        VALID_HITS.add("10. O-O O-O");
        VALID_HITS.add("53.d8=Q");
        VALID_HITS.add("3. c2 c5 ");
    }

    /**
     * List of invalid complex hits
     */
    public static List<String> INVALID_HITS = new ArrayList<String>();

    static {
        INVALID_HITS.add("1; e4 e5");
        INVALID_HITS.add("26.Rxahygtr7 Bxd3");
        INVALID_HITS.add("34,fxg6 hxg6");
        INVALID_HITS.add("10. O-O-O1- O-O");
        INVALID_HITS.add("53.d8=QR");
        INVALID_HITS.add("a.v=QR");
    }

    // ***
    // Test methods :-)
    // ***

    /**
     * Test the {@link PGNParser#SINGLE_HIT_PATTERN} with a list of valid hits: all the hits must
     * match with the pattern.
     */
    @Test
    public void testSingleHitPatternWithValidSingleHits() {
        for (String valid : VALID_SINGLE_HITS) {
            assertTrue(PGNParser.SINGLE_HIT_PATTERN.matcher(valid).matches(), valid + " is a valid hit !");
        }
    }

    /**
     * Test the {@link PGNParser#SINGLE_HIT_PATTERN} with a list of invalid hits: all the hits must
     * not
     * match with the pattern.
     */
    @Test
    public void testSingleHitPatternWithInvalidSingleHits() {
        for (String invalid : INVALID_SINGLE_HITS) {
            assertFalse(PGNParser.SINGLE_HIT_PATTERN.matcher(invalid).matches(), invalid + " is not a valid hit !");
        }
    }

    /**
     * Test the {@link PGNParser#HITS_PATTERN} with a list of valid full hits: all the hits must match
     * with the Pattern.
     */
    @Test
    public void testFullHitPatternWithValidFullHits() {
        for (String valid : VALID_HITS) {
            assertTrue(PGNParser.HITS_PATTERN.matcher(valid).matches(), valid + " is a valid hit !");
        }
    }

    /**
     * Test the {@link PGNParser#HITS_PATTERN} with a list of valid full hits: all the hits must match
     * with the Pattern.
     */
    @Test
    public void testFullHitPatternWithInvalidFullHits() {
        for (String valid : INVALID_HITS) {
            assertFalse(PGNParser.HITS_PATTERN.matcher(valid).matches(), valid + " is not a valid hit !");
        }
    }
}
