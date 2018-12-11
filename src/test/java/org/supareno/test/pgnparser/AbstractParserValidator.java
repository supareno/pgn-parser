/**
 *
 */
package org.supareno.test.pgnparser;


import org.supareno.pgnparser.jaxb.model.Game;
import org.supareno.pgnparser.jaxb.model.Games;
import org.supareno.test.pgnparser.JUnitTestConstants;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author reno
 *
 */
public abstract class AbstractParserValidator {

    protected void validateGames(Games games) {
        if ((games == null) || (games.getGame() == null) || (games.getGame().size() == 0)) {
            fail("games is null or game list is null or empty :-(");
        }
        Game game = games.getGame().get(0);
        assertEquals(JUnitTestConstants.REFERENCE_GAME_2_2, game);
    }
}
