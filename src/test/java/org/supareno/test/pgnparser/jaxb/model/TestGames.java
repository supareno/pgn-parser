/**
 *
 */
package org.supareno.test.pgnparser.jaxb.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.supareno.pgnparser.model.Game;
import org.supareno.pgnparser.model.Games;
import org.supareno.test.pgnparser.JUnitTestConstants;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

/**
 * @author reno
 *
 */
public class TestGames {

    private Games games1 = null;
    private Games games2 = null;

    @BeforeEach
    void setUpMethod() {
        games1 = new Games();
        games1.getGames().add(null);

        games2 = new Games();
        games2.getGames().add(null);
    }

    /**
     * Tests the equality with a null Game set.
     */
    @Test
    void testEqualityWithNullGame() {
        assertEquals(games1, games2);
        assertEquals(games1, games1);
        assertEquals(games2, games1);
        assertEquals(games2, games2);
    }

    /**
     * Tests the equality with the same Game set (the Game does not contains any Hit).
     */
    @Test
    void testEqualityWithSameGame() {
        games1.getGames().add(JUnitTestConstants.getFilledGame());
        games2.getGames().add(JUnitTestConstants.getFilledGame());
        assertEquals(games1, games2);
        assertEquals(games1, games1);
        assertEquals(games2, games1);
        assertEquals(games2, games2);
    }

    /**
     * Tests the equality with the same Game set (the Game does not contains any Hit).
     */
    @Test
    void testGamesNotEqual01() {
        Game game1 = JUnitTestConstants.getFilledGame();
        Game game2 = JUnitTestConstants.getFilledGame();
        game2.setBlack("otherBlackValue");
        games1.getGames().add(game1);
        games2.getGames().add(game2);
        assertNotSame(games1, games2);
        assertNotSame(games2, games1);
    }

}
