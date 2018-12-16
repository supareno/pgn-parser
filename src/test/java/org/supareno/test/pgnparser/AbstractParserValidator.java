/**
 *
 */
package org.supareno.test.pgnparser;


import org.supareno.pgnparser.model.Game;
import org.supareno.pgnparser.model.Games;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author reno
 *
 */
public abstract class AbstractParserValidator {

    protected void validateGames(Games games) {
        if ((games == null) || (games.getGames() == null) || (games.getGames().size() == 0)) {
            fail("games is null or game list is null or empty :-(");
        }
        Game game = games.getGames().get(0);
        assertEquals(JUnitTestConstants.REFERENCE_GAME_2_2, game);
    }
}
